title: docker内存满了
author: zjy
date: 2017-08-18 21:00:26
tags:
---
# 启动docker容器不带日志

## docker run 的时候加上参数 --log-driver="none"。


# 查看磁盘使用情况
##		df -lh （查看磁盘使用情况）


# 查看各个目录使用情况
## du -h --max-depth=2  （目录检索深度）


# 批量删除

## find / -name *-json.log |xargs rm -rf


# 查找大于某个大小
## find / -type f -size +1G


# 清理docker-log命令

echo "==================== start clean docker containers logs =========================="  
  
logs=$(find /var/lib/docker/containers/ -name *-json.log)  
  
for log in $logs  
        do  
                echo "clean logs : $log"  
                cat /dev/null > $log  
        done  
  
  
echo "==================== end clean docker containers logs   =========================="



# remove exited containers:
docker ps --filter status=dead --filter status=exited -aq | xargs -r docker rm -v

# remove unused images:
local images=`docker images --no-trunc`;
local lines=$(echo "$images" | awk '{print $2}' | grep -n "<none>" | cut -d: -f1 | sed 's/$/p/g');
lines=`echo $lines`;
lines=${lines// /;};
local image_ids=$(echo "$images" | awk '{print $3}' | sed -n "$lines");
[[ -n "${image_ids[@]}" ]] && docker rmi ${image_ids[@]}

# remove unused volumes:
find '/var/lib/docker/volumes/' -mindepth 1 -maxdepth 1 -type d | grep -vFf <(
  docker ps -aq | xargs docker inspect | jq -r '.[] | .Mounts | .[] | .Name | select(.)'
) | xargs -r rm -fr

。。。。