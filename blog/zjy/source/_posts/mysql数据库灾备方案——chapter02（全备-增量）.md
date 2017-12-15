title: mysql数据库灾备方案——chapter02（全备+增量）
author: zjy
date: 2017-11-21 11:05:22
tags:
---
# 1 开启二进制文件
	my.cnf里添加:
	log-bin=mysql-bin

# 2 修改脚本里面的路径
	mysql_full_backup.sh里：
		BACKUP_DIR：备份的全量sql存放路径
		KEEP_MYSQL：备份全量sql保存的天数
		MYSQL_HOST：mysql地址
		MYSQL_USER：mysql用户
		MYSQL_PASS：mysql密码
		MYSQL_PORT：mysql端口 
		
	
	mysql_incres_bin.sh里：
		MYSQL_HOST:mysql地址
		MYSQL_USER:mysql用户
		MYSQL_PASS:mysql密码
		MYSQL_PORT:mysql端口 

		BakDir:增量备份的文件 mysql-bin.0000*文件存放地址
		LogFile:备份过程发生的日志文件路径
		BinDir:需要备份数据库的二进制文件目录
		
		
# 3 数据恢复：
	使用全量备份sql恢复：
		对应的sql文件对应着数据库名字，直接运行sql脚本即可
		
	使用增量恢复：（mysql-bin.0000**路径自己看着写，然后日期自己看着写）
		mysqlbinlog --start-datetime='2010-01-01 00:00:02'  mysql-bin.*|mysql -uroot -p
		
		

# 4 开启定时任务：
	$ crontab -e
	
	#每周五凌晨2:00全量备份
	0 2 * * 5 /home/task_script/mysql_full_backup.sh >/home/backup/mysqlback.log>&1 &
	
	#周一到周六凌晨2:00做增量备份
	0 2 * * 1-6 /home/task_script/mysql_incres_bin.sh >/dev/null 2>&1
    
    
    
# 附件1(全备份脚本)
	
    
	#!/bin/bash
    # mysql_full_backup.sh 全量备份mysql，定时删除备份文件
    # BEGIN CONFIGURATION ==========================================================

    BACKUP_DIR="/home/backup"  # The directory in which you want backups placed
    KEEP_MYSQL="14" # How many days worth of mysql dumps to keep

    MYSQL_HOST="127.0.0.1"
    MYSQL_USER="root"
    MYSQL_PASS="123"
    MYSQL_PORT="3308"

    MYSQL_BACKUP_DIR="$BACKUP_DIR/mysql/"

    # You probably won't have to change these
    THE_DATE="$(date '+%Y-%m-%d')"

    MYSQL_PATH="$(which mysql)"
    MYSQLDUMP_PATH="$(which mysqldump)"
    FIND_PATH="$(which find)"
    TAR_PATH="$(which tar)"
    RSYNC_PATH="$(which rsync)"

    # END CONFIGURATION ============================================================



    # Announce the backup time
    echo "Backup Started: $(date)"

    # Create the backup dirs if they don't exist
    if [[ ! -d $BACKUP_DIR ]]
      then
      mkdir -p "$BACKUP_DIR"
    fi
    if [[ ! -d $MYSQL_BACKUP_DIR ]]
      then
      mkdir -p "$MYSQL_BACKUP_DIR"
    fi

    # Get a list of mysql databases and dump them one by one
    echo "------------------------------------"
    ALL_DBS=($($MYSQL_PATH -h $MYSQL_HOST -u$MYSQL_USER -p$MYSQL_PASS -Bse 'show databases'))
    SYSTEM_DBS=("information_schema" "mysql" "performance_schema" "test")
    DBS=()

    for i in "${ALL_DBS[@]}"; do
      skip=
      for j in "${SYSTEM_DBS[@]}"; do
        [[ $i == $j ]] && { skip=1; break; }
      done
      [[ -n $skip ]] || DBS+=("$i")
    done

    for db in "${DBS[@]}"; do
      echo "Dumping: $db..."
      $MYSQLDUMP_PATH --opt --skip-add-locks -h $MYSQL_HOST -u$MYSQL_USER -p$MYSQL_PASS $db | gzip > $MYSQL_BACKUP_DIR$db\_$THE_DATE.sql.gz
    done

    # Delete old dumps
    echo "------------------------------------"
    echo "Deleting old backups..."
    # List dumps to be deleted to stdout (for report)
    $FIND_PATH $MYSQL_BACKUP_DIR*.sql.gz -mtime +$KEEP_MYSQL
    # Delete dumps older than specified number of days
    $FIND_PATH $MYSQL_BACKUP_DIR*.sql.gz -mtime +$KEEP_MYSQL -exec rm {} +


    # Announce the completion time
    echo "------------------------------------"
    echo "Backup Completed: $(date)"
    
    
    
# 附件2（增量备份脚本）
	# cat /root/DBDailyBak.sh //内容为下
    #!/bin/bash 
	# mysql_incres_bin.sh
    MYSQL_HOST="127.0.0.1"
    MYSQL_USER="root"
    MYSQL_PASS="123"
    MYSQL_PORT="3308"

    BakDir=/home/backup/mysql
    LogFile=/home/backup/mysql/bak.log
    BinDir=/home/mysql/master/data/db-data

    BinFile=$BinDir/mysql-bin.index
    MYSQLADMIN_PATH="$(which mysqladmin)"
    THE_DATE="$(date '+%Y-%m-%d')"




    $MYSQLADMIN_PATH -h$MYSQL_HOST -P$MYSQL_PORT -u$MYSQL_USER -p$MYSQL_PASS flush-logs
    #这个是用于产生新的mysql-bin.00000*文件
    Counter=`wc -l $BinFile |awk '{print $1}'`
    NextNum=0
    #这个for循环用于比对$Counter,$NextNum这两个值来确定文件是不是存在或最新的。
    for file in  `cat $BinFile`
    do
            base=`basename $file`
            #basename用于截取mysql-bin.00000x文件名，去掉./mysql-bin.000005前面的./
            NextNum=`expr $NextNum + 1`
            if [ $NextNum -eq $Counter ]
            then
                    echo $base skip!  >> $LogFile
            else
                    dest=$BakDir/$base
                    if(test -e $dest)
                    #test -e用于检测目标文件是否存在，存在就写exist!到$LogFile去。
                    then
                            echo  $base exist! >> $LogFile
                    else
                            cp $BinDir/$base $BakDir
                            echo $base copying >> $LogFile
                    fi
            fi
    done
    echo `date +"%Y年%m月%d日 %H:%M:%S"` $Next Bakup succ! >> $LogFile

    #mysqlbinlog --start-datetime='2010-01-01 00:00:02' mysql-bin.*|mysql -uroot -p
	