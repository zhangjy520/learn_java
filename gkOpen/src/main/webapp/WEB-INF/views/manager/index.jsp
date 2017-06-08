<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/header.jsp"%>
<%@ include file="../login/login.jsp"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css">
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <meta http-equiv="x-ua-compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <script src="${ctx}/static/another-js/layer.js"></script>
</head>
<body>
<!--导航栏-->
<!--开放文档-->
<style>
    .col-xs-9>h1>input{margin-left:10px;position:relative;top:2px;margin-right:5px;}
    .col-xs-9>h1>label{font-size:14px;color:#333;font-weight:normal;cursor: pointer}
    #document-content section.col-xs-9 > div table tbody tr{background:#fff !important;}
    #document-content section.col-xs-9 > div table tr:first-child{text-align: left;}
</style>
<main class="container">
    <div id="document-content">
        <%@ include file="../common/manager/left_menu.jsp"%>
        <section class="col-xs-9" id="app1">
            <div id="check1">
                <p style="margin-left: auto;">
                    <label for="c1"><input type="radio" name="check1" id="c1" <c:if test="${appStatus == null}">checked</c:if> onclick="window.location.href='${ctx}/manager/index'">&nbsp;全部应用</label>

                    <label for="c2"><input type="radio" name="check1" id="c2" <c:if test="${appStatus == 2}">checked</c:if> onclick="window.location.href='${ctx}/manager/index?status=2'">&nbsp;已通过</label>

                    <label for="c3"><input type="radio" name="check1" id="c3" <c:if test="${appStatus == 1}">checked</c:if> onclick="window.location.href='${ctx}/manager/index?status=1'">&nbsp;审核中</label>

                    <%--<label for="c4"><input type="radio" name="check1" id="c4" <c:if test="${status2 == 0}">checked</c:if> onclick="window.location.href='${ctx}/manager/index?status=' + 0">&nbsp;未提交审核</label>--%>

                    <label for="c5"><input type="radio" name="check1" id="c5" <c:if test="${appStatus == 3}">checked</c:if> onclick="window.location.href='${ctx}/manager/index?status=3'">&nbsp;审核不通过</label>

                    <label for="c6"><input type="radio" name="check1" id="c6" <c:if test="${appStatus == 4}">checked</c:if> onclick="window.location.href='${ctx}/manager/index?status=4'">&nbsp;已删除</label>
            </p>

                <script>
                    $(function(){
//                        $('#check1>p label').click(function(event){
//                            console.log(e.id)
                            var status = '${status2}';
                            if(status==0||status==5){
                                $('#check1>div').hide();
                            }else if(status==2){
                                $('#check1>div').show().html('提示信息：若将应用进行在应用商店的下架操作，请联系管理员：400-100-1111');
                            }else if(status==1){
                                $('#check1>div').show().html('提示信息：若将正在审核的应用进行撤回审核操作，请联系管理员：400-100-1111');
                            }else if(status==3){
                                $('#check1>div').show().html('提示信息：若对审核未通过的应用有疑问，请联系管理员：400-100-1111');
                            }else if(status==4){
                                $('#check1>div').show().html('提示信息：若对被禁用的应用有疑问，请联系管理员：400-100-1111');
                            }
                    })
                </script>
                <style>
                    #document-content section.col-xs-9 > div>div{margin-bottom:0;font-size:13px;color:#ff1b26;display:none;
                        margin-top: 10px;
                    }
                </style>
                <div></div>
                <table style="margin-left: auto;">
                    <thead>
                    <tr>
                        <th width="3%"><input type="checkbox"></th>
                        <th width="10%">序号</th>
                        <th width="18%">应用名称</th>
                        <th width="18%">类别</th>
                        <th width="18%">状态</th>
                        <th width="28%">操作</th>
                    </tr>
                    </thead>
                    <tbody class="tbody">
                        <c:forEach items="${appPageInfo.list}" var="app" varStatus="status">
                            <tr>
                                <td><input type="checkbox"/></td>
                                <td>${status.index+1+(appPageInfo.pageNum-1)*10}</td>
                                <td>${app.name}</td>
                                <c:if test="${app.category == 0}">
                                    <td>教学教务</td>
                                </c:if>
                                <c:if test="${app.category == 1}">
                                    <td>互动空间</td>
                                </c:if>
                                <c:if test="${app.checkStatus == 0}">
                                    <td>未提交审核</td>
                                </c:if>
                                <c:if test="${app.checkStatus == 1}">
                                    <td>审核中</td>
                                </c:if>

                                <c:if test="${app.checkStatus == 2}">
                                    <td>已通过</td>
                                </c:if>
                                <c:if test="${app.checkStatus == 3}">
                                    <td>审核不通过</td>
                                </c:if>
                                <c:if test="${app.checkStatus == 4}">
                                    <td>已删除</td>
                                </c:if>
                                <c:if test="${app.checkStatus == 5}">
                                    <td>修改待审核</td>
                                </c:if>
                                <td>
                                    <span class="app1" onclick="window.location.href='${ctx}/app/manager/detail?id='+'${app.id}'">详情</span>
                                    <c:if test="${app.checkStatus == 2}">
                                        <span class="app2" onclick="window.location.href='${ctx}/app/manager/detail?id='+'${app.id}'+'&status=5'">修改</span>
                                    </c:if>
                                    <c:if test="${app.checkStatus == 0 }">
                                        <c:if test="${app.delFlag==0}">
                                            <span class="app3" data-url ="${ctx}/app/do/delete?id=${app.id}
                                          &&appPageNum=${appPageInfo.pageNum}
                                          &&appStatus=${app.checkStatus}">删除</span>
                                        </c:if>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <footer class="clear" style="margin-top: -15px;">
                    <p  style="margin-left: auto;">每页显示${appPageInfo.pageSize}条，共${appPageInfo.pages}页，共${appPageInfo.total}条记录</p>
                    <div class="fenY"></div>
                </footer>
            </div>
        </section>
        <%--<table>--%>
            <%--<tr class="aa">--%>
                <%--<td class="bb" >11</td>--%>
                <%--<td class="bb" >22</td>--%>
                <%--<td class="bb" >33</td>--%>
                <%--<td class="bb" >删除</td>--%>
            <%--</tr>--%>
        <%--</table>--%>
        <%--<script>--%>
            <%--$(function () {--%>
               <%--var ss = $(".bb");--%>
                <%--ss.each(function (i) {--%>
                   <%--var snum=  ss[i].innerHTML;--%>
                    <%--alert(snum);--%>
                <%--})--%>
            <%--})--%>
        <%--</script>--%>
    </div>

</main>

<!--网页信息-->
<%@ include file="../common/footer.jsp"%>

<!--<script src="js/jquery.min.js"></script>-->
<script>
    $(".fenY").createPage({
        pageCount:'${appPageInfo.pages}',
        current:'${appPageInfo.pageNum}',
        backFn:function(p){

            window.location.href = "${ctx}/manager/index?pageNum=" + p + "&pageSize=10";
        }
    });

    $('.app3').click(function () {
        var url = $(this).attr('data-url');
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除该应用？</p>',
            move: false,
            btn1: function () {
                console.log(url);
                window.location.href = url;
            }
        })
    })

    $(".gotoPage").click(function (){
        var pageNum = $(".go").val();
        if (pageNum <= 0 || pageNum >${appPageInfo.pages}){
            layer.msg("请输入正确的页码")
        } else {
            window.location.href = "${ctx}/manager/index?pageNum="+$(".go").val()+"&pageSize=10";
        }
    });

//$(function () {
//    var myfunc = function () {
//        alert("hello");
//    }
//    alert(typeof(myfunc));
//    myfunc();
//})

</script>
</body>
</html>