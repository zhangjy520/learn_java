<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<aside class="col-xs-3">
    <ul class="document-grade-1">
        <li>
            <a href="#" style="display: block">概述</a>
            <ul class="document-grade-2" style="display: block">
                <li><a href="${ctx}/document/index" class="active" id="gkSummarize">开放平台概述</a></li>
            </ul>
        </li>
        <li>
            <a href="#">接入指南</a>
            <ul class="document-grade-2" style="display: block">
                <li><a href="${ctx}/document/join/register" id="register1">开发者注册</a></li>
                <li><a href="${ctx}/document/join" id="standard">应用接入规范</a></li>
                <%--<li><a href="${ctx}/document/run" id="run">应用运营管理</a></li>--%>
            </ul>
        </li>
        <li>
            <a href="#">标准规范</a>
            <ul class="document-grade-2" style="display:block;">
                <li ><a href="${ctx}/document/treaty" id="treaty">开发者协议</a></li>
                <li ><a href="${ctx}/document/ilegal" id="ilegal">违规处理规范</a></li>
                <li><a href="${ctx}/document/service" id="service">登录服务协议</a></li>
                <%--<li><a href="${ctx}/document/code/back" id="back">公共返回码说明</a></li>--%>
                <li><a href="${ctx}/document/plate" id="plate">空间动态模板技术规范</a></li>
            </ul>
        </li>
        <li>
            <a href="#">API索引</a>
            <ul class="document-grade-2" style="display:block;">
                <li><a href="${ctx}/document/api" id="api">API</a></li>
                <%--<li><a href="http://omumqy8sj.bkt.clouddn.com/data.zip" id="SDK">数据同步文档下载</a></li>--%>
                <li><a href="${ctx}/document/code" id="codeback">公共返回码说明</a></li>
            </ul>
        </li>
        <%--<li>--%>
            <%--<a href="#">API索引</a>--%>
            <%--<ul class="document-grade-2" style="display:block;">--%>
                <%--<li ><a href="${ctx}/document/api" id="api">API索引</a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
    </ul>
</aside>
<script>
    $(function () {
        if("${treaty}" == "treaty") {
//            $("#treaty").attr("checked",true);
            $("#gkSummarize").removeClass('active');
            $("#treaty").addClass('active');
        }

        if("${ilegal}" == "ilegal") {
            $("#gkSummarize").removeClass('active');
            $("#ilegal").addClass('active');
        }

        if("${register}" == "register") {
            $("#gkSummarize").removeClass('active');
            $("#register1").addClass('active');
        }
        if("${join}" == "join") {
            $("#gkSummarize").removeClass('active');
            $("#standard").addClass('active');
        }
        if("${run}" == "run") {
            $("#gkSummarize").removeClass('active');
            $("#run").addClass('active');
        }
        if("${service}" == "service") {
            $("#gkSummarize").removeClass('active');
            $("#service").addClass('active');
        }
        <%--if("${codeback}" == "codeback") {--%>
            <%--$("#gkSummarize").removeClass('active');--%>
            <%--$("#back").addClass('active');--%>
        <%--}--%>
        if("${plate}" == "plate") {
            $("#gkSummarize").removeClass('active');
            $("#plate").addClass('active');
        }
        <%--if("${ilegal}" == "ilegal") {--%>
            <%--$("#gkSummarize").removeClass('active');--%>
            <%--$("#ilegal").addClass('active');--%>
        <%--}--%>
        if("${api}" == "api") {
            $("#gkSummarize").removeClass('active');
            $("#api").addClass('active');
        }


    });
</script>