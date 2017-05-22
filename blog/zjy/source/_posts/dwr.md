---
title: 基于dwr的消息推送java web
date: 2016-09-03 13:51:49
tags: java
--- 

1. pom.xml  加依赖
    ```
     <!-- https://mvnrepository.com/artifact/org.directwebremoting/dwr -->
        <dependency>
            <groupId>org.directwebremoting</groupId>
            <artifactId>dwr</artifactId>
            <version>3.0.0-RELEASE</version>
        </dependency>
    ```
2. web.xml

    ```
     <!--dwr begin config!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
    <servlet>
        <display-name>DWR Servlet</display-name>
        <servlet-name>dwr-invoker</servlet-name>
        <servlet-class>org.directwebremoting.servlet.DwrServlet</servlet-class>
        <init-param>
            <param-name>debug</param-name>
            <param-value>true</param-value>
        </init-param>
        <init-param>
            <param-name>pollAndCometEnabled</param-name>
            <param-value>true</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>dwr-invoker</servlet-name>
        <url-pattern>/dwr/*</url-pattern>
    </servlet-mapping>
    <!--dwr end config!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
    ```
    
3. dwr.xml（和web.xml同目录） 注意这里的javascript命名必须和前端导入的js名字一致    
    ```
    <!DOCTYPE dwr PUBLIC
        "-//GetAhead Limited//DTD Direct Web Remoting 3.0//EN"
        "http://getahead.org/dwr/dwr30.dtd">

    <dwr>
    <allow>
        <!-- 这里的javascript 值就是页面引用的第三个js文件的名称-->
        <create creator="new" javascript="SendMsg">
            <param name="class">cc.gukeer.common.dwr.SendMsg</param>
        </create>
    </allow>
    </dwr>
    ```
    
4.  cc.gukeer.common.dwr.SendMsg
    ```
        package cc.gukeer.common.dwr;

    import org.directwebremoting.Browser;
    import org.directwebremoting.ScriptBuffer;
    import org.directwebremoting.ScriptSession;
    import org.directwebremoting.ScriptSessionFilter;
    
    import javax.servlet.ServletException;
    import java.util.Collection;
    import java.util.List;
    
    /**
     * Created by Administrator on 2017/5/22.
     */
    public class SendMsg {
        public void send(final String msg,final List<String> uid) {
    
            //过滤器
            ScriptSessionFilter filter = new ScriptSessionFilter() {
                public boolean match(ScriptSession scriptSession) {
                    Object userid= scriptSession.getAttribute("uid");
                    if (userid == null)
                        return false;
                    return uid.contains(userid.toString());
                }
            };
    
    
            Runnable run = new Runnable() {
                private ScriptBuffer script = new ScriptBuffer();
    
                @Override
                public void run() {
                    script.appendCall("show", msg);
    
                    Collection<ScriptSession> sessions = Browser.getTargetSessions();
    
                    for (ScriptSession scriptSession : sessions) {
                        scriptSession.addScript(script);
                    }
    
                }
            };
            Browser. withAllSessionsFiltered(filter, run);    //注意这里调用了有filter功能的方法
    
        }
    
        public void init() throws ServletException {
            InitUtil initUtil = new InitUtil();
            initUtil.init();
        }

    }

    ```
    
5.  InitUtil.java
    ```
    package cc.gukeer.common.dwr;

    import cc.gukeer.smartBoard.persistence.entity.User;
    import org.directwebremoting.Container;
    import org.directwebremoting.ServerContextFactory;
    import org.directwebremoting.WebContextFactory;
    import org.directwebremoting.event.ScriptSessionEvent;
    import org.directwebremoting.event.ScriptSessionListener;
    import org.directwebremoting.extend.ScriptSessionManager;
    import org.directwebremoting.servlet.DwrServlet;
    
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpSession;
    
    /**
     * Created by lx on 2017/3/3.
     */
    public class InitUtil extends DwrServlet {
        @Override
        public void init() throws ServletException {
            Container container = ServerContextFactory.get().getContainer();
            // 工厂方法get()返回ServerContext实例
            ScriptSessionManager manager = container.getBean(ScriptSessionManager.class);
            ScriptSessionListener listener = new ScriptSessionListener() {
                public void sessionCreated(ScriptSessionEvent ev) {
                    HttpSession session = WebContextFactory.get().getSession();
                    User user = (User) session.getAttribute("loginUser");
                    ev.getSession().setAttribute("uid",  user.getId());
                }
                public void sessionDestroyed(ScriptSessionEvent ev) {
                    ev.getSession().removeAttribute("uid");
                }
            };
            manager.addScriptSessionListener(listener);
        }
        }


    ```

6. 后端调用 
    ```
        List<String> users = new ArrayList<String>();
        users.add("6");
        users.add("7");

        new SendMsg().send("测试成功",users);
    ```

7. 页面  注意这三个文件，第三个文件本地不存在，但是它的名字必须和 dwr里定义的那个名字一样，参考第二步骤
    ```
	<script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
    <script type="text/javascript" src="${ctx}/dwr/util.js"></script>
    <script type="text/javascript" src="${ctx}/dwr/interface/SendMsg.js"></script>
	
	<script>
	dwr.engine.setActiveReverseAjax(true);

    dwr.engine.setNotifyServerOnPageUnload(true);

    function show(msg) {
        alert(msg);
    }
	
	 $(function() {

        SendMsg.init();//将当前的uid存到scriptSession中
	  });
	
	</script>
	
    ```
    