---
title: ubuntu14.04 安装配置mysql5.7
date: 2017-05-05 13:51:49
tags: server,linux,ubuntu
---

# 服务器装mysql
 
1  yum list installed |grep mysql
2  wget dev.mysql.com/get/mysql-community-release-el6-5.noarch.rpm
3  yum localinstall mysql-community-release-el6-5.noarch.rpm
4  yum repolist all|grep mysql
5  yum-config-manager --disable mysql55-community
6  yum-config-manager --disable mysql56-community
7  yum-config-manager --enable mysql57-community-dmr

![logo](mysql/1.png)
![logo](mysql/2.png)