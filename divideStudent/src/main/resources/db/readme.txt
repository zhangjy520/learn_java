1. createDatabase.sql 为数据库初始创建脚本;
2. migrate 下面的 *.sql 为数据库迁移记录脚本;

执行脚本:
1. 确保数据库已经建立;
2. 修改 cn.gukeer.common.utils.DBMigrate url,username,password;
3. 修改 cn.gukeer.common.utils.DBMigrate 的 scriptPath 为实际对于的路径;
4. 执行 cn.gukeer.common.utils.DBMigrate main 方法即可将数据库版本同步.

