package cc.gukeer.syncdata.dbmain;


import cc.gukeer.syncdata.annotation.Increment;
import cc.gukeer.syncdata.annotation.NoSync;
import cc.gukeer.syncdata.annotation.PrimaryKey;
import cc.gukeer.syncdata.annotation.TableSync;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by lx on 2016/11/29.
 */
public class MakeTigger  {

    public static Map<String,String>  make(Class<?> clazz){

        Map<String,String> data = new HashMap<>();
        //表名描述
        TableSync info = clazz.getAnnotation(TableSync.class);
        //自增描述
        Increment increment = clazz.getAnnotation(Increment.class);
        //主键描述
        PrimaryKey primaryKey = clazz.getAnnotation(PrimaryKey.class);
        //插入触发器名称
        StringBuffer tiggerName_i = new StringBuffer(clazz.getSimpleName()+"_i");
        //修改触发器名称
        StringBuffer tiggerName_m = new StringBuffer(clazz.getSimpleName()+"_m");
        //删除触发器名称
        StringBuffer tiggerName_d = new StringBuffer(clazz.getSimpleName()+"_d");
        //需要同步的表
        String tableName = info.SyncTableName();
        //同步产生的中间表
        String targeName = info.TargetName();
        //生成表列名
        StringBuffer columnName = new StringBuffer("");
        //触发器的值
        StringBuffer valueName = new StringBuffer("");
        //sql值
        StringBuffer sqlVlaue = new StringBuffer("`id`"+ " int(11) AUTO_INCREMENT,");
        //主键带单引号
        StringBuffer Key = new StringBuffer("");
        //主键
        StringBuffer _Key = new StringBuffer("");
        //主键新名称带","
        StringBuffer Key_d = new StringBuffer("");
        //主键新名称
        StringBuffer _Key_d = new StringBuffer("");
        //删除触发器的值
        StringBuffer valueName_d = new StringBuffer("");
        Field [] field = clazz.getDeclaredFields();
        for (int i = 0; i < field.length; i++) {

            NoSync noSync = field[i].getAnnotation(NoSync.class);
            if (noSync==null) {
                if (field[i].getAnnotation(PrimaryKey.class) != null) {
                    columnName.append("`old_table_key_"+trans(field[i].getName())+"`,");
                    valueName.append("new." + trans(field[i].getName()) + ",");
                    continue;
                }else {
                    columnName.append("`" + trans(field[i].getName()) + "`,");
                    valueName.append("new." + trans(field[i].getName()) + ",");
                }
            }
        }

        for (int j = 0; j < field.length; j++) {

            if(field[j].getAnnotation(NoSync.class)==null) {
                if (field[j].getAnnotation(PrimaryKey.class) != null) {
                    Key.append("`"+trans(field[j].getName())+"`,");
                    _Key.append(trans(field[j].getName()));
                    Key_d.append("`old_table_key_"+trans(field[j].getName())+"`,");
                    _Key_d.append("`old_table_key_"+trans(field[j].getName())+"`");
                    valueName_d.append("old."+trans(field[j].getName())+",");

                }
                if (field[j].getType().getSimpleName().equals("Integer") || field[j].getType().getSimpleName().equals("int")) {
                    if (field[j].getAnnotation(PrimaryKey.class) != null) {
                        sqlVlaue.append("`old_table_key_"+trans(field[j].getName())+"`" + "int(11),");
                        continue;
                    }
                    sqlVlaue.append("`" + trans(field[j].getName()) + "`" + "int(11),");
                    continue;
                }
                if (field[j].getType().getSimpleName().equals("String")) {
                    if (field[j].getAnnotation(PrimaryKey.class) != null) {
                        sqlVlaue.append("`old_table_key_"+trans(field[j].getName())+"`" + " varchar(255),");
                        continue;
                    }
                    sqlVlaue.append("`" + trans(field[j].getName()) + "`" + "varchar(255), ");
                    continue;
                }
                if (field[j].getType().getSimpleName().equals("Long") || field[j].getType().getSimpleName().equals("long")) {
                    sqlVlaue.append("`" + trans(field[j].getName()) + "`" + "bigint(255), ");
                    continue;
                } else {
                    sqlVlaue.append("`" + trans(field[j].getName()) + "`" + "varchar(255), ");
                }
            }
        }

        int _key = Key.lastIndexOf(",");
        Key.deleteCharAt(_key);


        columnName.append("`event`, `versions`, `sync-date` ,`sync_del_flag`");
        String key_d = Key_d+"`event`, `versions`, `sync-date` ,`sync_del_flag`";
        String valueName_i = valueName+"'INSERT','0',NOW(),'0'";
        String valueName_m = valueName+"'MODIFY','0',NOW(),'0'";
        String _valueName_d = valueName_d+"'DELETE','0',NOW(),'0'";
        //String valueName_d = valueName+"'DELETE','0',NOW(),'0'";
        String istiggerSql_i = "DROP TRIGGER IF EXISTS `"+tiggerName_i+"`;";
        String tiggerSql_i = "CREATE TRIGGER`"+tiggerName_i+ "`AFTER INSERT ON `"+tableName+"` FOR EACH ROW BEGIN\n" +
                             " INSERT INTO `"+targeName+"`("+columnName+")\n" +
                             "VALUES ("+valueName_i+");\n"+
                             "END\n";

        String istiggerSql_m = "DROP TRIGGER IF EXISTS `"+tiggerName_m+"`;" ;
        String tiggerSql_m = "CREATE TRIGGER `"+tiggerName_m+"` AFTER UPDATE ON `"+tableName+"` FOR EACH ROW BEGIN\n" +
                             "DELETE FROM `"+targeName+ "` where "+_Key_d+" = new."+_Key+";"+
                             "INSERT INTO `"+targeName+"`("+columnName+")\n" +
                             "VALUES ("+valueName_m+");\n" +
                             "END\n";

        String istiggerSql_d = "DROP TRIGGER IF EXISTS `"+tiggerName_d+"`;" ;
        String tiggerSql_d = "CREATE TRIGGER `"+tiggerName_d+"` AFTER DELETE  ON `"+tableName+"` FOR EACH ROW BEGIN\n" +
                "INSERT INTO `"+targeName+"`("+key_d+")\n" +
                "VALUES ("+_valueName_d+");\n" +
                "END\n";

        sqlVlaue.append("`event` varchar(255), `versions` int(11), `sync-date`  timestamp,`sync_del_flag` varchar(255),");
        String isExistsTable = "DROP TABLE IF EXISTS `"+targeName+"`";
        String createSql = "CREATE TABLE `"+targeName+"` ("+sqlVlaue+"PRIMARY KEY (`id`))";
        System.out.printf(createSql+"\n");
        System.out.printf(tiggerSql_i);

        data.put("isExistsTable",isExistsTable);
        data.put("createSql",createSql);
        data.put("istiggerSql_i",istiggerSql_i);
        data.put("tiggerSql_i",tiggerSql_i);
        data.put("istiggerSql_m",istiggerSql_m);
        data.put("tiggerSql_m",tiggerSql_m);
        data.put("istiggerSql_d",istiggerSql_d);
        data.put("tiggerSql_d",tiggerSql_d);
        return data;
    }
    //大写字母转下划线
    public  static String trans(String str){
        List record =new ArrayList();
        for(int i=0;i<str.length();i++)
        {
            char tmp =str.charAt(i);

            if((tmp<='Z')&&(tmp>='A'))
            {
                record.add(i);//记录每个大写字母的位置
            }
        }
       /* if(record.size() > 0) {
            record.remove(0);//第一个不需加下划线
        }*/
        str= str.toLowerCase();
        char[] charofstr = str.toCharArray();
        String[] t =new String[record.size()];
        for(int i=0;i<record.size();i++)
        {
            t[i]="_"+charofstr[(int)record.get(i)];//加“_”
        }
        String result ="";
        int flag=0;
        for(int i=0;i<str.length();i++)
        {
            if((flag<record.size())&&(i==(int)record.get(flag))){
                result+=t[flag];
                flag++;
            }
            else
                result+=charofstr[i];
        }
        return result;
    }

    public static  int count(String a,String b){
        int temp = 0;
        int count = 0;
        while (a.indexOf("b",temp)!=-1){
            int c = a.indexOf("b",temp);
            count = count+1;
            temp=c+1;
        }
        return count;
    }

    public static void main(String [] args){
        MakeTigger mt = new MakeTigger();
    }
}
