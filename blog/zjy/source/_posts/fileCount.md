title: 递归统计（spring bean扫描也用的这种模式）
tags: java
date: 2017-05-03 13:51:49
---
 刚开始项目经理需要==统计项目代码行数==，所以写了这个类用来实现这个需求

## 原理：
参数  项目路径，需要统计的文件后缀名的集合
  递归遍历项目路径下的所有文件直到找不到文件为止！
## 核心方法：
-   new File(path).listFiles();   获取这个路径下的文件数组  File[] 
-   遍历这个文件数组，如果是文件并且符合后缀名集合，加入到 全局变量 List<File> 里面去。如果是文件夹继续递归判断
-   最终得到一个符合 所有后缀名，并且在指定路径下的所有文件 集合
-   最后将这个集合通过文件流读取，来计算行数 FileReader（file）
  
  
## 拓展: 
在使用fileReader的时候，因为读取文件是按行。所以可作为全文索引工具类。
全局搜索项目里面的字符串。定位哪个文件的哪一行有指定的字符串

## 类似应用：

spring的包扫描原理
扫描controller包下的所有class文件，并且判断它们是否是 isAnnoationCLass（Controller.class）注解类  递归

仿照spring做的类扫描器代码如下：

 ```   
 package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public final class ClassUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CastUtil.class);
	
	/**
	 * 获取类加载器
	 */
	public static ClassLoader getClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**
	 * 加载类
	 */
	public static Class<?> loadClass(String className, boolean isInitialized){
		Class<?> cls;
		try {
			cls = Class.forName(className, isInitialized, getClassLoader());
		} catch (Exception e) {
			LOGGER.error("load class fail", e);
			e.printStackTrace();
			throw new RuntimeException();
		}
		return cls;
	}
	
	/**
	 * 加载类
	 * @param className
	 * @return
	 */
	public static Class<?> loadClass(String className) {
		return loadClass(className, true);
	}
	
	/**
	 * 获取指定包下的所有类
	 */
	public static Set<Class<?>> getClassSet(String packageName){
		Set<Class<?>> classSet = new HashSet<Class<?>>();
		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));
			while(urls.hasMoreElements()){
				URL url = urls.nextElement();
				if(null != url){
					String protocol = url.getProtocol();
					if("file".equals(protocol)){
						String packagePath = url.getPath().replaceAll("%20", "");
						addClass(classSet, packagePath, packageName);
					}else if("jar".equals(protocol)){
						JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
						if(null!=jarURLConnection){
							JarFile jarFile = jarURLConnection.getJarFile();
							if(null!=jarFile){
								Enumeration<JarEntry> jarEntries = jarFile.entries();
								while(jarEntries.hasMoreElements()){
									JarEntry jarEntry = jarEntries.nextElement();
									String jarEntryName = jarEntry.getName();
									if(".class".endsWith(jarEntryName)){
										String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
									addClass(classSet, className);
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("get class set fail", e);
			e.printStackTrace();
			throw new RuntimeException();
		}
		return classSet;
	}
	
	private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName){
		File[] files = new File(packagePath).listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class")) || (
						file.isDirectory());
			}
			
		});
		
		for(File file : files){
			String fileName = file.getName();
			if(file.isFile()){
				String className = fileName.substring(0, fileName.lastIndexOf("."));
				if(StringUtil.isNotEmpty(className)){
					className = packageName + "." + className;
				}
				addClass(classSet, className);
			}else{
				String subPackagePath = fileName;
				if(StringUtil.isNotEmpty(packagePath)){
					subPackagePath = packagePath + "/" + subPackagePath;
				}
				String subPackageName = fileName;
				if(StringUtil.isNotEmpty(packageName)){
					subPackageName = packageName + "." + subPackageName;
				}
				addClass(classSet, subPackagePath, subPackageName);
			}
		}
		
	}
	
	private static void addClass(Set<Class<?>> classSet, String className){
		Class<?> cls = loadClass(className, false);
		classSet.add(cls);
	}
}

 ```  



扫描文件统计代码行数代码如下：
 ```  
 package cn.gukeer.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;


/*
*   添加所有文件方法addFiles，是否是目录判断方法 isDirectory ,读取行数方法readLinePerFile
*
*
* */
public class HowManyLine {
    List<File> list = new ArrayList<File>();
    int linenumber = 0;

    FileReader fr = null;
    BufferedReader br = null;

    /**
     * @param path，fileSuffixList path:需要统计的路径
     *                            fileSuffixList:需要统计的文件后缀List
     */
    public void counter(String path, List<String> fileSuffixList, String target) {
        long begin = System.currentTimeMillis();
        System.out.println("\n开始读取" + path + "下的文件,若文件较多，耗时可能较长，请您耐心等待。。。。\n");
        File file = new File(path);
        File files[] = null;
        files = file.listFiles();
        addFile(files);//将目录下所有文件加入到list中
        isDirectory(files);//所有文件列表进行遍历判断，若是文件夹，继续将该文件夹下的所有文件加入到list中，递归判断
        Map map = readLinePerFile(fileSuffixList, target);
        Set keys = map.keySet();
        for (Object key : keys) {
            System.out.println(key + "共：" + map.get(key) + "行");
        }
        long end = System.currentTimeMillis();
        System.out.println("\n读取完毕，共统计" + list.size() + "个文件，耗时" + (end - begin) + "毫秒");
    }

    // 判断是否是目录 ，递归判断
    public void isDirectory(File[] files) {
        for (File s : files) {
            if (s.isDirectory()) {
                File file[] = s.listFiles();
                addFile(file);
                isDirectory(file);
                continue;
            }
        }
    }

    //将文件列表添加到list中
    public void addFile(File file[]) {
        for (int index = 0; index < file.length; index++) {
            list.add(file[index]);
        }
    }

    //读取非空白行
    public Map readLinePerFile(List<String> fileSuffixList, String target) {
        Map map = new TreeMap();
        try {
            for (String suffix : fileSuffixList) {
                int suffixNum = 0;
                for (File s : list) {
                    int yuan = linenumber;
                    if (s.isDirectory()) {
                        continue;
                    }

                    if (!suffix.equals(".*")){
                        if (s.getName().lastIndexOf(suffix) <= 0) {
                            continue;
                        }
                    }

                    fr = new FileReader(s);
                    br = new BufferedReader(fr);
                    String i = "";
                    int lineNum = 0;
                    while ((i = br.readLine()) != null) {
                        lineNum++;
                        if (!isBlankLine(i)) {
                            if (StringUtils.isNotEmpty(target))
                                if (i.contains(target))
                                    System.out.println(s.getName() + "的第" + lineNum + "行有你需要搜索的字符串");

                            linenumber++;
                            suffixNum++;

                        }
                    }
                    /*System.out.print(s.getName());
                    System.out.println("\t\t有" + (linenumber - yuan) + "行");*/
                }
                map.put(suffix, suffixNum);
            }
            map.put("total", linenumber);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (Exception e) {
                }
            }
        }
        return map;
    }

    //是否是空行,是空行返回true
    public boolean isBlankLine(String i) {
        if (i.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //全文索引
    public static void main(String args[]) {
        HowManyLine lineCounter = new HowManyLine();
        List<String> fileSuffixList = new ArrayList<String>();
        fileSuffixList.add(".*");
       //fileSuffixList.add(".jsp");
        //fileSuffixList.add(".css");
       // fileSuffixList.add(".js");
        //fileSuffixList.add(".xml");
        //fileSuffixList.add(".*");
       // lineCounter.counter("D:\\javaProjects\\git\\gkPlatform", fileSuffixList, "好不好");
        lineCounter.counter("D:\\blog\\zjy\\themes\\concise2", fileSuffixList, "o.com/weiboshow/ind");
    }
}
 
 ```