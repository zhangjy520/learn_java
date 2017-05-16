package cc.gukeer.open.service;

/**
 * Created by lx on 2016/11/28.
 */

import org.springframework.stereotype.Service;

/**
 *
 * @param mobile 手机号码，多个号码使用","分割
 * @param msg 短信内容
 * @param needstatus 是否需要状态报告，需要true，不需要false
 * @return 返回值定义参见HTTP协议文档
 * @throws Exception
 */
public interface ShortMessageService {
    //有返回信息的get发送
    /*String sendMessageByGet (String mobile,String msg,boolean needstatus,String extno) throws Exception;

    //有返回信息的post发送
    String sendMessageByPost (String mobile,String msg,boolean needstatus,String extno) throws Exception;
*/
    public String batchSend(String url, String account, String pswd, String mobile, String msg,
    boolean needstatus, String extno) throws Exception;

}
