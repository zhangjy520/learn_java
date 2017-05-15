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
	 * ??????????
	 */
	public static ClassLoader getClassLoader(){
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**
	 * ??????(????????????)
	 */
	public static Class<?> loadClass(String className, boolean isInitialized){
		Class<?> cls;
		try {
			cls = Class.forName(className, isInitialized, getClassLoader());
		} catch (Exception e) {
			LOGGER.error("????????...", e);
			e.printStackTrace();
			throw new RuntimeException();
		}
		return cls;
	}
	
	/**
	 * ??????(?????????)
	 * @param className
	 * @return
	 */
	public static Class<?> loadClass(String className) {
		return loadClass(className, true);
	}
	
	/**
	 * ????????????????§Ö???
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
			LOGGER.error("??????????§Ö??????...", e);
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
