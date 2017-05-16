<%--
  Created by IntelliJ IDEA.
  User: lx
  Date: 2016/12/22
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/base.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑</title>
    <style>
        #all{
            width: 500px;
            margin-left: auto;
            margin-right: auto;
        }
        input[type="submit"]{
            color: rgba(0, 0, 0, 0.77);
            width: 400px;
            height: 35px;
            font-family: -webkit-body;
            box-shadow: -3px 2px 12px 1px black;
            text-shadow: 0px 1px 13px black;
            color: #020415
        }
        input{
            width: 180px;
            height: 23px;
            background-color: rgba(255, 255, 255, 0.39);
            border-color: lightcyan;
        }
        p{
            font-family: -webkit-body;
            font-style: inherit;
            color: #020415;
        }
        #islogin{
            width: 180px;
            height: 23px;
            background-color: rgba(255, 255, 255, 0.39);
            border-color: lightcyan;
        }
    </style>
    <script src="${ctx}/static/js/jquery.min.js"></script>
</head>
<body style="background-color: rgba(158, 158, 158, 0.39);">
<div id="all"style="height: 100%;width: 100%">
    <div>
        <select id="adressList">
            <option><a href="#">地址列表</a></option>
            <option value="${ctx}/layout/edit/sns">sns地址</option>
            <option value="${ctx}/layout/edit/ckmooc">ckmooc地址</option>
        </select>
    </div>
    <div style="margin-right: auto;margin-left: auto;width: 280px;height: 100%;width: 50%;padding-left: auto;padding-left: 500;padding-top: 140;">
        <div>
            <h1>${appName}</h1>
        </div>
        <form id="edit" action="${ctx}/layout/doedit">
            <div style="float: left;display: inline">
            <span>
                <p>创客社区链接：</p>
                <input type="text" name="cksq" value="${link.cksq}"/>
            </span>
            <span>
                <p>创客Mooc链接：</p>
                <input type="text" name="ckmc" value="${link.ckmc}"/>
            </span>
            <span>
                <p>智能编程云链接：</p>
                <input type="text" name="znbcy" value="${link.znbcy}"/>
            </span>
            <span>
                <p>创客官网链接：</p>
                <input type="text" name="ckgw" value="${link.ckgw}"/>
            </span>
            <span>
                <p>中小学创客教育执委会链接：</p>
                <input type="text" name="zxxck" value="${link.zxxck}"/>
            </span>
            </div>
            <div style="float: left">
            <span class="loginspan">
                <p>用户中心链接：</p>
                <input  class="login" type="text" name="yhzx" value="${link.yhzx}" />
            </span>
            <span class="loginspan">
                <p>消息中心链接：</p>
                <input class="login" type="text" name="xxzx" value="${link.xxzx}"/>
            </span>
            <span class="loginspan">
                <p>我的收藏链接：</p>
                <input class="login" type="text" name="wdsc" value="${link.wdsc}"/>
            </span>
            <span class="loginspan">
                <p>我的头衔链接：</p>
                <input class="login" type="text" name="wdtx" value="${link.wdtx}"/>
            </span>

            </div>
            <input type="hidden" name="appName" value="${appName}">
            <div style="float: left;width: 100%; margin-top: 3% " >
                <input type="submit" value="提交"/>
            </div>
        </form>
    </div>
</div>

<script>
    $(function(){
        if (${empty link}){
            $(".login").attr("value","http://");
        }
    });

    $("#adressList").change(function () {
        var href = $("option:selected").val();
        console.log(href);
        window.location.href=href;

    })

</script>

</body>
</html>
