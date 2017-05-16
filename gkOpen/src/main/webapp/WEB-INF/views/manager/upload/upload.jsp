<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../../common/header.jsp"%>
<%@ include file="../../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/js/less.js"></script>
    <script src="${ctx}/static/js/action.js"></script>
    <style>
        .attention {
            font-size: 14px;
            color:#000000;
        }
        .attention p{
            line-height: 30px !important;
            margin-left: 0 !important;
        }
        h5{
            margin-top: 20px !important;
            margin-bottom: 15px !important;
        }
        table td{
            padding:0 !important;
            padding-left: 10px !important;
        }
        table td p{
            margin: auto !important;
            padding:10px 10px 10px 0 !important;
        }
        table tr:first-child{
            line-height: 43px;
            font-size: 16px;
        }
    </style>
</head>
<body>

<main class="container">
    <div id="document-content">
        <%@ include file="../../common/manager/left_menu.jsp" %>
        <section class="col-xs-9">
            <h1>文档下载中心</h1>
            <div>
                <h3>一、注意事项</h3>
                <h5>该平台是由重混智能官方提供的参考文档，使用时请注意</h5>
                <div class="attention">
                    <%--<p><b>1:</b>以下是由<img src="${ctx}/static/images/tuzi.jpg" width="16px" height="16px">重混智能团队提供</p>--%>
                    <p><b>1:</b> 欢迎广大开发爱好者分享到本平台</p>
                    <p><b>2:</b>使用时遇到问题请直接与我们联系，重混智能公司将不提供相关的技术支持</p>
                    <%--<p><b>step4:</b>通过step2获取的access_token和refresh_token</p>--%>
                    <%--<p><b>step5:</b>通过step2获取的access_token判断登录状态，判断token有效性,若有效授权登陆，无效则重新获取access_token</p>--%>
                </div>
            </div>
            <div>
                <h3>二、文档下载</h3>
                <table style="margin-top:25px;width: 96%;">
                    <tr>
                        <td width="12%">文件名称</td>
                        <td width="12%">更新时间</td>
                        <td width="12%">文件大小</td>
                        <td width="60%">文档说明</td>
                        <%--<td width="10%">文件下载</td>--%>
                    </tr>
                    <tr>
                        <td><a onclick="window.open('http://file6.ckmooc.com/%E6%95%B0%E6%8D%AE%E6%8E%A8%E9%80%81%E6%96%87%E6%A1%A3.pdf')" onmouseover="this.style.cursor='hand'"
                               onmouseout="this.style.cursor='normal'" style="color: #54ab37">数据同步</a><img src="${ctx}/static/images/pdf.jpg"  style="width: 16px" height="16px"></td>
                        <td>2017-03-22</td>
                        <td>279kb</td>
                        <td><p>由于项目系统之间有些数据是共有的，例如人员、组织，在使用其它项目系统时，人员、组织也需要，这就需要将人员、组织的数据同步，人员、组织的数据项很多，而其它系统需要的很少，可能只需要人员和组织的名称及其标识列，并且数据量不大，不会一次性发送上百个人员或者组织的信息，基于这个考虑，通过将人员、组织信息的数据放在消息内放到消息中件上，各个系统通过订阅的方式获取消息中的数据。</p></td>
                    </tr>
                    <tr>
                        <td><a onclick="window.open('http://file6.ckmooc.com/oauth2.0002.pdf')" onmouseover="this.style.cursor='hand'"
                                   onmouseout="this.style.cursor='normal'" style="color: #54ab37;width: 100%;">授权登陆</a><img src="${ctx}/static/images/pdf.jpg"  style="width: 16px" height="16px"></td>
                        <td >2017-03-22</td>
                        <td>279kb</td>
                        <td><p>OAuth（开放授权）是一个开放标准，允许用户授权在教育云平台注册的第三方应用，访问他们存储在另外的服务提供者上的信息，而不需要将用户名和密码提供给第三方应用或分享他们数据的所有内容。</p></td>
                        <%--<td><a onmouseover="this.style.cursor='hand'"--%>
                               <%--onmouseout="this.style.cursor='normal'" style="color: #54ab37">www.qiniu.com</a></td>--%>
                    </tr>
                </table>
            </div>
        </section>
    </div>
</main>
<!--网页信息-->
<%@ include file="../../common/footer.jsp"%>
</body>

</html>