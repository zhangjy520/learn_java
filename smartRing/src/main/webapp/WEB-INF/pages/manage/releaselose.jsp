<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="cc.gukeer.smartRing.common.RStatusType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <style>
        .container{width:350px;margin:0 auto;}
        .container>p{font-size:14px;color:#fc2f5b;margin-bottom:20px;}
        .container>div span{display: inline-block;margin-right:20px;color:#676767;}
        ul{background:#F7F9FB;margin-top:15px;list-style: none;font-size:13px;padding:0;}
        ul>li{line-height: 40px;color:#676767;}
        label{cursor: pointer}
        .sh-type>span{display:inline-block;width:145px;padding-left:8px;}
        .sh-type label { padding-left: 20px;cursor: pointer;position: relative;margin-right: 30px;
        }
        .sh-type label:before{content:'';position:absolute;width:13px;height:13px;
            left:0;top:2px;background:url(${ctx}/assets/images/icon.png) no-repeat -15px -34px;}
        .sh-type .checked:before{background-position: 0 -34px;}
        .stuMsg{font-size:13px;}
        /*.stuMsg span{}*/

    </style>
    <script src="${ctxStatic}/js/plugins/jquery-3.1.1.js"></script>
    <script src="${ctxStatic}/js/layer/layer.js"></script>
</head>
<body>
<main class="container">
    <div class="stuMsg">
        <span>${stName}</span>
        <span>${grade}</span>
        <span>${xh}</span>
    </div>
    <input type="hidden" id="ringId" value="${ringId}">
    <input type="hidden" id="type" value="${type}">
    <input type="hidden" id="" value="">
    <p>请确认该学生归还所有临时手环</p>
    <ul>
        <c:forEach items="${ringList}" var="ring">
            <c:if test="${ring.type == 1 && (ring.status == 1 || ring.status == 2)}">
                <li class="sh-type">
                    <span>${ring.mac}</span>
                    <label class="checked">
                        <input type="radio"  name="${ring.id}" hidden="hidden" value="<%=RStatusType.STATUS_UNUSED%>" checked/>
                        <span>归还</span>
                    </label>
                    <label >
                        <input type="radio" name="${ring.id}" hidden="hidden" value="<%=RStatusType.STATUS_LOST%>"/>
                        <span>丢失</span>
                    </label>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</main>
<script>
    $('.sh-type label').click(function(){
        $(this).addClass('checked');
        $(this).siblings('label').removeClass('checked');
//        console.log($(this).children('input').attr('name'))
    })

    function doSubmit() {//回调函数，在编辑和保存动作时，供openDialog调用提交表单。

        var temp=null;
        $('.sh-type label.checked').each(function () {
            if ( null == temp){
                temp = $(this).children('input').attr('name')+","+$(this).children('input').val();
            } else {
                temp = temp +","+$(this).children('input').attr('name')+","+$(this).children('input').val();
            }
        });

        $.post('${ctx}/manage/ring/release/loss/${ringId}/${type}', {
            temp:temp,
            studentId:${studentId}
        }, function (res) {
            if (res.code == '-1') {
                layer.msg(res.msg,{time:1500});
            } else {
                layer.msg(res.msg,{time:1500},function () {
                    parent.location.reload();
                });
            }
        })
    }
</script>
</body>
</html>
