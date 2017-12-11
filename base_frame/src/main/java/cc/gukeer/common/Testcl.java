package cc.gukeer.common;

import cc.gukeer.common.utils.RegUtil;
import nettySDK.API.MessageHandler;
import nettySDK.API.NettyBootStrap;
import nettySDK.factory.ClientNettyBootStrapFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by conn on 2016/8/6.
 */
public class Testcl {
    private static List<ArticleCount> list = new ArrayList();
    private static int index = -1;

    public static void rep(String filePath,String big){
        try {
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));//数据流读取文件

            StringBuffer strBuffer = new StringBuffer();
            String empty = "";
            String tihuan = "";
            int i = 0;
            String regex = ">(.*?)<";
            String regexNo = "no='(.*?)'>";


            String text = "";
            for (String temp = null; (temp = bufReader.readLine()) != null; temp = null) {

                //替换代码-------------------------------------------------
                /*if(temp.indexOf("<article no=") != -1 && temp.indexOf("<article no=") != -1){ //判断当前行是否存在想要替换掉的字符 -1表示存在
                    i++;
                    tihuan = temp.substring(0, 10);
                    temp = temp.replace(tihuan, i+tihuan);//替换为你想要的东东
                }*/


                //------------------------------转换实体类代码------------------
                temp = temp.trim();
                temp = RegUtil.replaceSpace(temp);
                if (temp.contains("article")){
                    index++;
                    ArticleCount article = new ArticleCount();
                    article.setBigArticle(big);
                    article.setArticle(RegUtil.getSubUtilSimple(temp,regexNo));
                    list.add(index,article);
                }else {
                    if (index==-1)
                        continue;

                    ArticleCount article = (ArticleCount) list.get(index);

                    if (temp.contains("<text>")||temp.contains("sentence")){
                        text+=RegUtil.getSubUtilSimple(temp,regex);
                        continue;
                    }
                    if (temp.contains("</text>")){
                        text = text.replaceAll(" ","");
                        text = text.replaceAll("　","");
                        text = text.replaceAll("：","");
                        text = text.replaceAll("（","");
                        text = text.replaceAll("）","");
                        text = text.replaceAll("。","");
                        text = text.replaceAll("、","");
                        text = text.replaceAll("，","");

                        article.setText(text);
                        article.setTextCount(text.length());
                        text=new String();
                        continue;
                    }

                    if (temp.contains("genre"))
                        article.setGenre(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("style"))
                        article.setStyle(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("mode"))
                        article.setMode(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("topic"))
                        article.setTopic(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("class"))
                        article.setClasse(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("medium"))
                        article.setMedium(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("name"))
                        article.setName(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("sex"))
                        article.setSex(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("nationality"))
                        article.setNationality(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("nativelang"))
                        article.setNativelang(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("publisher"))
                        article.setPublisher(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("publishlocation"))
                        article.setPublishlocation(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("publishdate"))
                        article.setPublishdate(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("edition"))
                        article.setEdition(RegUtil.getSubUtilSimple(temp,regex));
                    if (temp.contains("title"))
                        article.setTitle(RegUtil.getSubUtilSimple(temp,regex));

                }

//                strBuffer.append(temp);
//                strBuffer.append(System.getProperty("line.separator"));//行与行之间的分割
            }
            List res = new CopyOnWriteArrayList();
            res.addAll(list);


            list.removeIf(new java.util.function.Predicate<ArticleCount>() {
                @Override
                public boolean test(ArticleCount article) {
                    if (article.getArticle().isEmpty())
                        return true;
                    return false;
                }
            });

            //bufReader.close();
            //PrintWriter printWriter = new PrintWriter(filePath);//替换后输出的文件位置
            //printWriter.write(strBuffer.toString().toCharArray());
            //printWriter.flush();
            //printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {


        NettyBootStrap nettyBootStrap  = ClientNettyBootStrapFactory.getNettyBootStrap("127.0.0.1",10001,true);
        nettyBootStrap.connect("an");
        nettyBootStrap.listenerMessage(new MessageHandler() {
            public void onMessageReceive(Object message) {
                System.out.println("服务器发来" + message);
            }
        });

    }


}


