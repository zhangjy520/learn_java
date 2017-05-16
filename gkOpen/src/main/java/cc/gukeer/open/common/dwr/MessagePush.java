package cc.gukeer.open.common.dwr;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;

import javax.servlet.ServletException;
import java.util.Collection;

/**
 * Created by lx on 2017/3/3.
 */
public class MessagePush {

   /* public void Send(final String str){
            Runnable run = new Runnable() {
                private ScriptBuffer script = new ScriptBuffer();

                public void run() {
                    //设置要调用的 js及参数
                    script.appendCall("show", str);
                    //得到所有ScriptSession
                    Collection<ScriptSession> sessions = Browser.getTargetSessions();
                    //遍历每一个ScriptSession
                    for (ScriptSession scriptSession : sessions) {
                        String a = (String) scriptSession.getAttribute("uid");
                        scriptSession.addScript(script);
                    }
                }
            };
            //执行推送
            Browser.withAllSessions(run);
    }*/

    public void sendToId(final String content, final String id, final int isSuccess){
        //过滤器
        ScriptSessionFilter filter = new ScriptSessionFilter() {
            public boolean match(ScriptSession scriptSession) {
                String userid= (String)scriptSession.getAttribute("uid" );
                System.out.println(userid);
                    return id .equals(userid);
            }
        };

        Runnable run = new Runnable(){
            private ScriptBuffer script = new ScriptBuffer();
            public void run() {
                //设置要调用的 js及参数
                if (isSuccess == 1) {
                    script.appendCall("showSuccess", content);
                }else {
                    script.appendCall("showFail", content);
                }
                //得到所有ScriptSession
                Collection<ScriptSession> sessions = Browser.getTargetSessions();
                //遍历每一个ScriptSession
                for (ScriptSession scriptSession : sessions){
                    scriptSession.addScript( script);
                }
            }
        };
        //执行推送
        Browser. withAllSessionsFiltered(filter, run);    //注意这里调用了有filter功能的方法
    }

    public void initId(final String str) throws ServletException {
        InitUtil initUtil = new InitUtil();
        initUtil.init();
    }

   /* public  void Send(String msg) {
        WebContext webContext = WebContextFactory.get();
        Collection<ScriptSession> sessions = webContext.getAllScriptSessions();
        // 构建发送所需的JS脚本
        ScriptBuffer scriptBuffer = new ScriptBuffer();
        // 调用客户端的js脚本函数
        scriptBuffer.appendScript("show(");
        // 这个msg可以被过滤处理一下，或者做其他的处理操作。这视需求而定。
        scriptBuffer.appendData(msg);
        scriptBuffer.appendScript(")");
        // 为所有的用户服务
        Util util = new Util(sessions);
        util.addScript(scriptBuffer);
    }*/

    }
