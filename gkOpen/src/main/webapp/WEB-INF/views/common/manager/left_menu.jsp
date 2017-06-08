<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<link rel="stylesheet" href="${ctx}/static/css/toastr.css">
<script type="text/javascript" src="${ctx}/dwr/engine.js"></script>
<script type="text/javascript" src="${ctx}/dwr/util.js"></script>
<script type="text/javascript" src="${ctx}/dwr/interface/MessagePush.js"></script>
<script src="${ctx}/static/js/toastr.js"></script>
<script src="${ctx}/static/js/alertPopShow.js"></script>
<script>
    if (${sessionScope.openUser == null }){
        webToast("登陆超时，请重新登陆", "top", 2300);
        window.location.href ="${ctx}/doLogout";
    }
</script>

<aside class="col-xs-3">
    <ul class="document-grade-1">
        <c:if test="${openUser.status==2}">
        <li>
            <a href="#">我的应用</a>
            <ul class="document-grade-2" style="display:block;">
                <li><a href="${ctx}/manager/index" id="appManager" class="active"> 应用管理</a></li>
                    <li><a href="${ctx}/manager/app/add" class="addApp">创建应用</a></li>
            </ul>
            <a href="#">文档下载</a>
            <ul class="document-grade-2" style="display:block;">
                <li><a  href="${ctx}/manager/upload" id="upload"> 文档下载中心</a></li>
                <%--onclick="window.open('http://omumqy8sj.bkt.clouddn.com/%E6%95%B0%E6%8D%AE%E6%8E%A8%E9%80%81%E6%96%87%E6%A1%A3.pdf')"--%>
                <%--<li><a href="${ctx}/manager/upload"  id="oauth">授权登陆</a></li>--%>
            </ul>
        </li>
        </c:if>
        <li>
            <a href="#">账号中心</a>
            <ul class="document-grade-2" style="display: block">
                <li><a href="${ctx}/manager/basic" <c:if test="${openUser.status != 2}">class="active"</c:if> id="basic">基本资料</a></li>
                <li><a href="${ctx}/manager/password/page" class="updatePWD">密码修改</a></li>
            </ul>
        </li>
        <li>
            <a href="#">消息中心</a>
            <ul class="document-grade-2" style="display: block">

                <li><a href="${ctx}/manager/info/index?type=2" class="info" style="position: relative">消息中心
                    <c:if test="${unReadCount>0}">
                        <i style="display: inline-block;width:20px;height:20px;font-style:normal;line-height: 20px;text-align: center;color:#fff;background: red;position:absolute;border-radius: 50%;top:10px;left:70px">${unReadCount}</i>
                    </c:if></a></li>
            </ul>
        </li>
    </ul>
    <span id="content"></span>
</aside>
<script>
    $("#syncronism").click(function () {
        $("#appManager").removeClass('active');
        $("#syncronism").addClass('active');
    })

    $("#oauth").click(function () {
        $("#appManager").removeClass('active');
        $("#oauth").addClass('active');
    })
    $(function () {
        if (${openUser.status != 2}) {
            if ("${addApp}" == "addApp") {
                $("#basic").removeClass('active');
                $(".addApp").addClass('active');
            }
            if ("${all}" == "all") {
                $("#basic").removeClass('active');
                $(".info").addClass('active');
            }
            if ("${PWDPage}" == "PWDPage") {
                $("#basic").removeClass('active');
                $(".updatePWD").addClass('active');
            }
        }
        if ("${infoDetail}" == "infoDetail") {
            $("#appManager").removeClass('active');
            $(".info").addClass('active');
        }

        if ("${upload}" == "upload") {
            $("#appManager").removeClass('active');
            $("#upload").addClass('active');
        }
        if ("${basic}" == "basic") {
            $("#appManager").removeClass('active');
            $("#basic").addClass('active');
        }
        if ("${addApp}" == "addApp") {
            $("#appManager").removeClass('active');
            $(".addApp").addClass('active');
        }
        if ("${all}" == "all") {
            $("#appManager").removeClass('active');
            $(".info").addClass('active');
        }
        if ("${PWDPage}" == "PWDPage") {
            $("#appManager").removeClass('active');
            $(".updatePWD").addClass('active');
        }
    })

    $(function(){
        MessagePush.initId("${openUser.id}");
    });
    
    //这个方法用来启动该页面的ReverseAjax功能
    dwr.engine.setActiveReverseAjax( true);
    //设置在页面关闭时，通知服务端销毁会话
    dwr.engine.setNotifyServerOnPageUnload( true);
    //这个函数是提供给后台推送的时候 调用的
    function showSuccess(content,info){
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "positionClass": "toast-bottom-right",
            "onclick": function functionName() {
                window.location.reload();
            },
            "showDuration": "10000",
            "hideDuration": "0",
            "timeOut": "",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }
        toastr.success("您的应用<strong>"+content+"</strong>通过审核<a href='${ctx}/manager/info/index?type=2'>查看详情</a>")
    }
    function showFail(content,info){
        toastr.options = {
            "closeButton": true,
            "debug": false,
            "positionClass": "toast-bottom-right",
            "onclick": function functionName() {
                window.location.reload();
            },
            "showDuration": "10000",
            "hideDuration": "0",
            "timeOut": "",
            "extendedTimeOut": "1000",
            "showEasing": "swing",
            "hideEasing": "linear",
            "showMethod": "fadeIn",
            "hideMethod": "fadeOut"
        }
        toastr.error("您的应用<strong>"+content+"</strong>未通过审核<a href='${ctx}/manager/info/index?type=2'>查看详情</a>")
    }
</script>
