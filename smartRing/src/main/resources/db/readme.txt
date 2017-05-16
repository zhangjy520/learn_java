登陆管理员账户：
    账号：root
    密码：000000


1. createDatabase.sql 为数据库初始创建脚本;
2. migrations 下面的 *.sql 为数据库迁移记录脚本;

执行脚本:
1. 确保数据库已经建立，并且编译项目;
2. 修改 db.properties 中 jdbc.schema、jdbc.driver、jdbc.url、jdbc.username、jdbc.password、jdbc.script
3. 执行 cc.gukeer.common.db.DBMigrate main 方法即可将数据库版本同步.

数据库地址配置文件 ：db.properties
db.properties   数据库属性配置文件

jdbc.schema  //schema
jdbc.script   //数据库脚本文件路径 执行 DBMigrate.java main方法，可以将未同步的数据库脚本执行到指定 schema的数据库
jdbc.driver  //数据库驱动
jdbc.url   //数据库链接地址
jdbc.username  //用户名
jdbc.password  //密码
drive.class.path  //数据库jar驱动地址，使用mybatis-generator需要链接数据库，此为驱动jar包路径
target.project   //target
model.package   // mybatis-generator 生成的entity类路径
dao.package    // mybatis-generator 生成的dao类路径
xml.mapper.package    // mybatis-generator 生成的mapper路径
vfsroot.windows   //文件上传windows路径
vfsroot.linux  //文件上传 服务器路径
develop_path   //项目部署域名后缀



