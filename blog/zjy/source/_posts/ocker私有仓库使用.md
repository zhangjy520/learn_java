title: docker私有仓库使用
author: zjy
date: 2017-07-19 18:03:04
tags:
---
# 云环境 docker 私有仓库操作
```

docker私有仓库地址：118.190.66.172:5000



使用步骤，在本机的该文件
centos：/etc/sysconfig/docker
ubuntu: /etc/default/docker

1 在该文件下加上如下配置

	DOCKER_OPTS="--insecure-registry 118.190.66.172:5000"

2 重启本机docker服务

	service docker restart

3 确认修改是否生效
	ps -ef|grep docker

	确保看到的 docker daemon进程 启动参数有刚刚加上的这句话即可，如下即可	
		root      4716     1  6 09:54 ?        00:00:46 /usr/bin/docker daemon --insecure-registry 118.190.66.172:5000


4 查看镜像列表
	
	curl http://118.190.66.172:5000/v2/_catalog
	 
	会返回一个镜像列表，下载需带上  地址/镜像名称
	{"repositories":["registry","tomcat"]}
		
5 下载镜像
	
	docker pull 118.190.66.172:5000/tomcat

6 上传镜像（tag）
	docker tag images 118.190.66.172:5000/yourImageName
	
	docker push 118.190.66.172:5000/yourImageName
```