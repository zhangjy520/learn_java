###### ---
title: docker私有库的搭建
date: 2017-05-05 13:51:49
tags: docker  ubuntu
---

# 准备

 server1，server2  （其中server1作为私有库服务器，server2作为普通客户端）
 
## 在server1上

1 下载 registry
	
```
docker pull registry:latest
```

2 配置 /etc/default/docker  因为https需要证书密码等比较复杂，直接加 insecure-registry即可

```
# Docker Upstart and SysVinit configuration file

# Customize location of Docker binary (especially for development testing).
#DOCKER="/usr/local/bin/docker"

# Use DOCKER_OPTS to modify the daemon startup options.
#DOCKER_OPTS="--dns 8.8.8.8 --dns 8.8.4.4"
DOCKER_OPTS="--insecure-registry 127.0.0.1:5000"
# If you need Docker to use an HTTP proxy, it can also be specified here.
#export http_proxy="http://127.0.0.1:3128/"

# This is also a handy place to tweak where Docker's temporary files go.
#export TMPDIR="/mnt/bigdrive/docker-tmp"

```
3 启动registry

```
sudo docker run --name registry -d -p 5000:5000 -v /home/docker_registry:/var/lib/registry --restart=always registry:latest
```

4 tag镜像

```
docker tag redis server1:5000/redis

```
5 推送保存私有镜像
```
docker push server1:5000/redis
```

5.1 查看推送到私有仓库的镜像
```
$ docker search 10.10.105.71:5000/tonybai/busybox/
Error response from daemon: Unexpected status code 404
但通过v2版本的API，我们可以实现相同目的：

$curl  http://10.10.105.71:5000/v2/_catalog
{"repositories":["tonybai/busybox"]}
```

## 在server2（client）上

因为docker Registry中讲到， 如果采用insecure registry的模式，那么所有与Registry交互的主机上的Docker Daemon都要配置：–insecure-registry选项。除了这个模式还可以配置证书，在此不作说明

1 配置 -insecure-registry(centos:/etc/sysconfig/docker   ubuntu:/etc/default/docker)
```
# Docker Upstart and SysVinit configuration file

# Customize location of Docker binary (especially for development testing).
#DOCKER="/usr/local/bin/docker"

# Use DOCKER_OPTS to modify the daemon startup options.
#DOCKER_OPTS="--dns 8.8.8.8 --dns 8.8.4.4"
DOCKER_OPTS="--insecure-registry server1:5000"
# If you need Docker to use an HTTP proxy, it can also be specified here.
#export http_proxy="http://127.0.0.1:3128/"

# This is also a handy place to tweak where Docker's temporary files go.
#export TMPDIR="/mnt/bigdrive/docker-tmp"

```
2 下载

```
docker pull server1:5000/redis
```
3 提交推送

```
docker tag redis server1:5000/redis

docker push server1:5000/redis
```







 参考：
 http://www.cnblogs.com/xcloudbiz/articles/5526262.html