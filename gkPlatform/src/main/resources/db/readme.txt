1. createDatabase.sql 为数据库初始创建脚本;
2. migrations 下面的 *.sql 为数据库迁移记录脚本;
3.init_data_201611071342.sql为基础sql数据

执行脚本:
1. 确保数据库已经建立，并且编译项目;
2. 修改 db.properties 中 jdbc.schema、jdbc.driver、jdbc.url、jdbc.username、jdbc.password、jdbc.script
3. 执行 cn.gukeer.common.db.DBMigrate main 方法即可将数据库版本同步.

