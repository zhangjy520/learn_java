---
title: ubuntu14.04 安装配置JDK1.7
date: 2017-05-05 13:51:49
tags: server,linux,ubuntu
---

# ubuntu14.04 安装配置JDK1.7

## 1,下载jdk-7u45-linux-x64.tar.gz 
    
	http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
    
---
## 2, 解压JDK 
```
sudo tar zxvf jdk-7u45-linux-x64.tar.gz  -C /usr/lib/jvm   
```

---
##  3,设置环境变量（全局） 
```
sudo gedit  /etc/profile  
```
   打开profile文件输入
```
export JAVA_HOME=/usr/lib/jvm/jdk1.7.0_65  
export CLASSPATH=".:$JAVA_HOME/lib:$CLASSPATH"  
export PATH="$JAVA_HOME/bin:$PATH"  
``` 
##  4,设置系统默认JDK 
```
sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk1.7.0_65/bin/java 300  
sudo update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/jdk1.7.0_5/bin/javac 300  
sudo update-alternatives --config java
```
##  5, 验证JDK
   输入命令 
 ```
java -version  
```
   见到JDK的信息则表示成功。