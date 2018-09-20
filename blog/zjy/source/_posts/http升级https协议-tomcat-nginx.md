title: http升级https协议-tomcat-nginx
author: zjy
date: 2018-08-14 10:20:12
tags:
---
# 需求
## 想做个小程序，发现小程序接口服务器域名只支持https和wss协议。所以要对现有的http进行升级。



# tomcat7升级https配置步骤

##  1. 用JDK自带的keytool来生成私有密钥和自签发的证书，如下： 

	keytool -genkey -alias sdtx -keyalg RSA -keystore /home/apache-tomcat-7.0.64/tomcat.keystore
    //生成过程中会让填写信息，我这里全部填的localhost。主要是firstname。要填域名信息
    
## 2.配置tomcat/server.xml
	找到server.xml里面将该节点配置改为如下：
      <Connector port="8443" protocol="org.apache.coyote.http11.Http11Protocol"
               maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
               clientAuth="false" sslProtocol="TLS" keystoreFile="/home/apache-tomcat-7.0.64/tomcat.keystore" keystorePass="123456" cliphers="123456"/>
   
    并且注释掉

    <!--  <Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="on" />
	-->  
    
## 3. 访问：https://localhost:8443/yourwebapp
  ps：https和http访问是可以共存的。
  如果没有注释掉8080那个端口的http请求。需要注意的是生成密钥的时候，记得firstname写域名
  https://localhost:8443/yourwebapp   等价于    http://localhost:8080/yourwebapp

访问效果如图：

![upload successful](pasted-2.png) 



# nginx https代理配置步骤

## 1. 创建证书：
	  mkdir /etc/nginx/ssl
  	openssl req -x509 -nodes -days 3650 -newkey rsa:2048 -keyout nginx.key -out nginx.crt
    //解释：创建一个有效期 10 年，加密强度为 RSA2048 SSL 密钥 nginx.key 和 X509 证书文件 nginx.crt。
## 2.配置nginx.conf
    server {
    listen 8086 ssl;
    server_name blog.zhangjianyu.top;

    ssl_certificate /etc/nginx/ssl/nginx.crt;
    ssl_certificate_key /etc/nginx/ssl/nginx.key;
    ssl_protocols TLSv1 TLSv1.1 TLSv1.2;
    ssl_ciphers HIGH:!aNULL:!MD5;

    root /www/nginx/https;
    index index.html index.jsp;
        
    keepalive_timeout 70;
        
    server_tokens off;
    location / {
            alias /home/ligu/attach/;
            autoindex on;
        }

    access_log /var/log/nginx/www.hao.com.access.log;
    error_log /var/log/nginx/www.hao.com.error.log;
	}
    
    
## 3.访问效果图


![upload successful](pasted-3.png)