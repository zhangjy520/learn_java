---
title: ubuntu14.04 安装配置JDK1.7，更换openjdk
date: 2017-05-05 13:51:49
tags: server,linux,ubuntu
---

# ubuntu14.04 安装配置JDK1.7

## 1,下载jdk-7u45-linux-x64.tar.gz 
```    
	http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
```  
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
   
   
   
   
# 将openjdk更换为 oracle jdk

## 1 下载jdk并解压，修改环境变量配置文件/etc/profile，在文件末尾加入以下几行信息：（其中JAVA_HOME=/usr/java/******，为jdk实际安装路径）
```
export JAVA_HOME=/usr/java/jdk1.7.0_79
export CLASSPATH=.:$JAVA_HOME/jre/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin
```

## 2 修改默认的jdk。即设置java和javac的符号连接，这是把默认的openjdk1.6改成刚新装的jdk1.7。
```
 cd /usr/bin/
 mv java  javabak_open1.6
 mv javac  javacbak_open1.6

[root@localhost bin]# ln -s  /usr/java/jdk1.7.0_79/jre/bin/java
[root@localhost bin]# ln -s  /usr/java/jdk1.7.0_79/bin/javac
```

## 3检查
```
[root@real-node1 bin]# java -version
java version "1.7.0_79"
Java(TM) SE Runtime Environment (build 1.7.0_79-b15)
Java HotSpot(TM) 64-Bit Server VM (build 24.79-b02, mixed mode)   
```