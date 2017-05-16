package cc.gukeer.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by conn on 2016/8/6.
 */
public class Test{

    public static Integer asleepTimeMin(Integer hour,Integer min) {
        Integer total = 0 ;
        if(hour<8){
            total = hour * 60 + 24 * 60;
        }else{
            total = hour * 60;
        }
        total += min;
        return  total;
    }
    public static void main(String args[]) throws IOException {
        Date nowTime=new Date();
        System.out.println(nowTime);
        SimpleDateFormat time=new SimpleDateFormat("yyyy MM dd HH mm ss");
        System.out.println(time.format(nowTime));
    }


}
