<%@ page import="cc.gukeer.common.utils.ConstantUtil" %>
<%@ page import="cc.gukeer.smartRing.common.RStatusType" %>
<%@ include file="../common/newHeader.jsp" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>手环管理</title>
    <link rel="stylesheet" href="${ctxStatic}/css/basicManage.css"/>
    <!--<link rel="stylesheet" href="css/daterangepicker.css"/>-->
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
    <script src="${ctxStatic}/js/openDialog.js"></script>
    <style>
        body{padding-bottom:80px;}
        #sh-lose>header{
            font-size:13px;margin:20px 0;
        }
        #sh-lose>header span{
            margin-right:6px;
            color:#676767;
        }
        #sh-lose>header select{
            margin-right:15px;
            border:1px solid #ddd;
            border-radius:3px;
            color:#525252;
            outline: none;
            width:112px;height:28px;
        }
        #sh-lose>p:after{left:85px;top:1px;}
        #sh-lose>p button{margin-right:15px;}
        #sh-lose>header,#sh-lose>div,#sh-lose>footer{padding:0 25px;}
        #sh-lose>header section{height:30px;}
        #sh-lose>header section button.rl{
            border:1px solid #1AB394;color:#fff;padding:0 15px;background: #1AB394;height:28px;border-radius:3px;margin-left:13px;
        }
        #sh-lose>header section input[type=text]{color:#525252;width:200px;border:1px solid #ddd;border-radius:3px;height:28px;}
    </style>
</head>
<body>


<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a href="#">基础管理</a></li>
        <li class="child-nav  active"><a href="#">手环管理</a></li>
    </ul>
</header>
<div id="bodyContainer">
    <main id="sh-lose" class="fix-width module-bg-full">
        <p style="border-bottom: 1px solid #ddd;" class="clear">
            手环管理
            <button class="rl" style="margin-right:0;" onclick="window.location.href='${ctx}/manage/download/ring'">下载模板</button>
            <button class="rl" id="multibundling">导入</button>
            <button style="margin-left:0;border-radius:0 3px 3px 0;" class="search rl" id="searchBtn"></button>
            <input placeholder="学号/姓名/手环编号" style="border-right:none;margin-top:-3px;" class="rl" type="text" id="search" value="${search}"/>
        </p>
        <header class="clear">
            <aside class="lf">
                <span>类别</span>
                <select id="sh-type">
                    <option value="">全部</option>
                    <option value="0">个人手环</option>
                    <option value="1">临时手环</option>
                    <option value="2">未使用</option>
                </select>
                <span>状态</span>
                <select id="sh-status">
                    <option value="">全部</option>
                </select>
            </aside>
            <%--<section class="rl clear">--%>
                <%--<button style="margin-left:0;border-radius:0 3px 3px 0;" class="search rl" id="searchBtn"></button>--%>
                <%--<input placeholder="学号/姓名/手环编号" style="border-right:none;margin-top:0;" class="rl" type="text" id="search" value="${search}"/>--%>
                <%--&lt;%&ndash;<i class="search-item" style="right:7px ;top:7px;"></i>&ndash;%&gt;--%>
            <%--</section>--%>
        </header>
        <%--<header class="clear" style="margin-bottom:0;padding-bottom:12px;border-bottom:1px solid #ddd;">--%>
            <%--<section class="rl">--%>
                <%--<button class="rl" onclick="window.location.href='${ctx}/manage/download/ring'">下载模板</button>--%>
                <%--<button class="rl" id="multibundling">导入</button>--%>
                <%--&lt;%&ndash;<button class="rl">导出</button>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<button class="rl">新增</button>&ndash;%&gt;--%>
            <%--</section>--%>
        <%--</header>--%>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th width="13%">手环编号</th>
                    <th width="9%">姓名</th>
                    <th width="9%">年级</th>
                    <th width="6%">班级</th>
                    <th width="10%">学号</th>
                    <th width="8%">类别</th>
                    <th width="8%">状态</th>
                    <th width="28%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ringViewPage.list}" var="ringView">
                    <tr>
                        <td>${ringView.mac}</td>
                        <td>${ringView.stName}</td>
                        <td>${ringView.xdName}
                            <c:forEach items="${njList}" var="nj">
                                <c:if test="${ringView.nj == nj.key}">
                                    ${nj.value}
                                </c:if>
                            </c:forEach>
                        </td>
                        <td>${ringView.className}</td>
                        <td>${ringView.xh}</td>
                        <td>
                            <c:if test="${ringView.type == 0}">个人手环</c:if>
                            <c:if test="${ringView.type == 1}">临时手环</c:if>
                            <c:if test="${ringView.type == 2}">未使用</c:if>
                        </td>
                        <td>
                            <c:if test="${ringView.status == 0}">空闲中</c:if>
                            <c:if test="${ringView.status == 1}">使用中</c:if>
                            <c:if test="${ringView.status == 2}">挂失中</c:if>
                            <c:if test="${ringView.status == 3}">已丢失</c:if>
                        </td>
                        <td>
                            <c:if test="${ringView.type == 0 && ringView.status ==1}">
                                <span class="sh-operation" onclick="reportLoss('${ringView.id}')">挂失</span>
                            </c:if>
                            <c:if test="${ringView.type == 0 && ringView.status ==2}">
                                <span class="sh-operation1" onclick="openDialog('解除挂失', '${ctx}/manage/ring/releaseloss/${ringView.id}/${ringView.xh}','470px','550px')">解除挂失</span>
                            </c:if>
                            <%--<c:if test="${ringView.type == 0 && ringView.status ==3}"></c:if>--%>
                            <c:if test="${ringView.type == 1 && ringView.status ==0}">
                                <span class="sh-operation1" onclick="bindingring('${ringView.id}',<%=RStatusType.TYPE_TEMP%>)">绑定</span>
                            </c:if>
                            <c:if test="${ringView.type == 1 && ringView.status ==1}">
                                <span class="sh-operation" onclick="reportLoss('${ringView.id}')">挂失</span>
                            </c:if>
                            <c:if test="${ringView.type == 1 && ringView.status ==2}">
                                <span class="sh-operation1" onclick="releaseTemp('${ringView.id}')">解除挂失</span>
                            </c:if>
                            <%--<c:if test="${ringView.type == 1 && ringView.status ==3}"></c:if>--%>
                            <c:if test="${ringView.type == 2}">
                                <span class="sh-operation2" onclick="delRing('${ringView.id}')">删除</span>
                                <span class="sh-operation1" onclick="bindingring('${ringView.id}',<%=RStatusType.TYPE_FREE%>)">绑定</span>
                                <span class="sh-operation1" onclick="transformTemp('${ringView.id}')">转做临时手环</span>
                            </c:if>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <footer class="pagination clear" style="padding:0 25px;">
            <c:if test="${ringViewPage.pages!=0}">
                <div class="lf">
                    共${ringViewPage.total}条记录，每页显示
                    <select id="pageSize">
                        <option value="10" <c:if test="${ringViewPage.pageSize==10}">selected</c:if>>10</option>
                        <option value="20" <c:if test="${ringViewPage.pageSize==20}">selected</c:if>>20</option>
                        <option value="50" <c:if test="${ringViewPage.pageSize==50}">selected</c:if>>50</option>
                    </select>
                    条记录
                </div>
                <div class="rl fenY">

                    <script>
                        $(".fenY").createPage({
                            pageCount: ${ringViewPage.pages},
                            current: ${ringViewPage.pageNum},
                            backFn: function (p) {
                                window.location.href = "${ctx}/manage/ring/index?pageNum=" + p + "&pageSize=" + $('#pageSize').val() 
                                        +"&search=" + encodeURI(encodeURI($('#search').val()))
                                        +"&type="+$('#sh-type').val()
                                        +"&status="+$('#sh-status').val();

                            }
                        });
                    </script>
                </div>
            </c:if>
        </footer>
    </main>
</div>
<script>
    $(function () {

        $("#basicManage").addClass("active");
        $("#basicManage ul li:nth-child(1)>a").addClass("active");

        if(${gukeer:emptyString(type) || type == 2}){
            $('#sh-status option').remove();
            $('#sh-status').append('<option value="">全部</option>')
        } else if(${type == 0}){
            $('#sh-status option').remove();
            $('#sh-status').append('<option value="">全部</option>' +
                    '<option value="1">使用中</option>' +
                    '<option value="2">挂失中</option>' +
                    '<option value="3">已丢失</option>'
            )
        }else if(${type == 1}){
            $('#sh-status option').remove();
            $('#sh-status').append('<option value="">全部</option>' +
                    '<option value="0">空闲中</option>' +
                    '<option value="1">使用中</option>' +
                    '<option value="2">挂失中</option>' +
                    '<option value="3">已丢失</option>'
            )

        }
        $('#sh-type').val('${type}');
        $('#sh-status').val('${status}');

        $("#pageSize").change(function () {
            window.location.href = "${ctx}/manage/ring/index?pageNum=" + 1 + "&pageSize=" + $('#pageSize').val()
                    +"&search=" + encodeURI(encodeURI($('#search').val()))
                    +"&type="+$('#sh-type').val()
                    +"&status="+$('#sh-status').val();
        })

        $("#searchBtn").click(function () {
            window.location.href = "${ctx}/manage/ring/index?search=" + encodeURI(encodeURI($('#search').val()));
        })
    })
//    var allRings=$('#sh-status option');
    var shType=document.getElementById('sh-type');
//    var shStatus=document.getElementById('sh-status');
    shType.onchange=function(){
        window.location.href = "${ctx}/manage/ring/index?type="+$('#sh-type').val();
    }

    var shStatus=document.getElementById('sh-status');
    shStatus.onchange=function(){
        window.location.href = "${ctx}/manage/ring/index?type="+$('#sh-type').val()+"&status="+$('#sh-status').val();
    }

    $('#multibundling').click(function () {
        layer.open({
            type: 2,
            title: '批量导入手环',
            shadeClose: true,
            shade: 0.8,
            move:false,
            resize:false,
            btn: ['确认', '取消'],
            area: ['380px', '240px'],
            content: '${ctx}/manage/importring',
    //            //move: '.mine-move',
            btn1: function () {
    //                var index = layer.msg('导入中', {icon: 16, shade: 0.5, time: 1000000000});//当生成完成这个对话框才被关掉
                var index = layer.load();
                $.ajax({
                    url: '${ctx}/manage/import/ring',
                    type: 'POST',
                    cache: false,
                    data: new FormData(layer.getChildFrame('#form2')[0]),
                    processData: false,
                    contentType: false
                }).done(function (res) {
                    layer.close(index);
                    if (res.code == '0') {
                        layer.msg(res.msg, {
                            time: 1500 //2秒关闭（如果不配置，默认是3秒）
                        }, function(){
                            window.location.href='${ctx}/manage/ring/index';
                        });
                    } else if (res.code == '-2') {
                        layer.msg(res.msg, {
                            time: 2000 //2秒关闭（如果不配置，默认是3秒）
                        });
                    } else if (res.code == '-1') {
                        var tem = res.data;
                        if(tem == undefined){
                            layer.msg("文件有误");
                            return false;
                        }
                        var datamsg = '';
                        for (var i = 0; i < tem.length; i++) {
                            datamsg += '<tr>' +
                                    '<td>' + tem[i].mac + '</td>' +
                                    '<td>' + tem[i].msg + '</td>' +
                                    '</tr>';
                        }
                        var html = ' <main style="padding:15px; font-size: 13px;">' +
                                ' <p style="margin-bottom:15px;">' +
                                ' 成功<span style="color: #19be9d;">' + res.msg + '</span>条，失败<span style="color: #fc2f5b;">' + res.data.length + '</span>条' +
                                '         </p>' +
                                '         <table class="table">' +
                                '         <thead>' +
                                '         <tr>' +
                                '         <th style="width:40%;">手环编号</th>' +
                                '         <th style="width:60%;">失败原因</th>' +
                                '         </tr>' +
                                '         </thead>' +
                                '         <tbody>' +
                                datamsg +
                                ' </tbody>' +
                                ' </table>' +
                                ' </main>';
                        layer.open({
                            type: 1,
                            title: '导入失败列表',
                            shadeClose: true,
                            shade: 0,
                            move:false,
                            resize:false,
                            btn: ['确认', '下载失败列表'],
                            area: ['480px', '460px'],
                            content: html,
                            btn1: function () {
                                parent.location.reload();
                            },
                            btn2: function () {
                                var form=$("<form>");//定义一个form表单
                                form.attr("style","display:none");
                                form.attr("target","");
                                form.attr("method","post");
                                form.attr("action","${ctx}/manage/ring/errorring");
                                var input1=$("<input>");
                                input1.attr("type","hidden");
                                input1.attr("name","msg");
                                input1.attr("value",JSON.stringify(res.data));
                                $("body").append(form);//将表单放置在web中
                                form.append(input1);
                                form.submit();//表单提交
                                return false;
                            }
                        })
                    }
                }).fail(function (res) {
                    layer.msg("模板异常，导入失败！");
                    setTimeout(function () {
                        layer.closeAll();
                    }, 500)
                });


            }
        });
    })

    function delRing( id) {
        layer.open({
            type:1,
            title:'确认删除',
            btn:['确认','取消'],
            area:['380px','240px'],
            content:'<div style="padding:25px">' +
            '<p style="font-size:13px;">' +
            ' <span style="color:#F6182B;font-size:18px;">确认删除该手环？</span><br><br> '+
            /*'删除角色后，该角色的教师用户必须重新分配角色才可以正常使用系统功能' +*/
            '</p>' +
            '</div>',
            btn1:function(){
                $.post( "${ctx}/manage/ring/del/"+id, function (res) {
                    layer.msg(res.msg);
                    setTimeout(function () {
                        window.location.reload();
                    },1000);
                })
            }
        })

    }

    function transformTemp(id) {
        $.post( "${ctx}/manage/ring/transformtemp/"+id, function (res) {
            layer.msg(res.msg);
            setTimeout(function () {
                window.location.reload();
            },1000);
        })
    }

    //挂失
    function reportLoss(id) {
        $.post( "${ctx}/manage/ring/loss/"+id, function (res) {
            layer.msg(res.msg);
            setTimeout(function () {
                window.location.reload();
            },1000);
        })
    }
    //临时手环解除挂失
    function releaseTemp(id) {
        $.post( "${ctx}/manage/ring/release/loss/"+id+"/"+<%=RStatusType.TYPE_TEMP%>, function (res) {
            layer.msg(res.msg);
            setTimeout(function () {
                window.location.reload();
            },1000);
        })
    }
    //绑定
    function bindingring( id, type) {
        var title = '';
        if(type == <%=RStatusType.TYPE_FREE%>){
            title = '绑定个人手环';
        } else {
            title = '绑定临时手环';
        }
        layer.open({
//            type:2,//弹出页面，默认是弹出内容
            type:1,
            title: title,
            shadeClose: true,
            shade: 0.8,
            move:false,
            resize:false,
            btn: ['确认', '取消'],
            area: ['380px', '240px'],
            content: '<div style="text-align:center; margin-top: 30px;">' +
            '<span style="font-size:13px;color:#525252;">学号</span>' +
            '<input type="text" placeholder="学生学号" id="xh" style="margin-left:10px;height:28px;border:1px solid #ddd;border-radius:3px;width:230px;padding-left:5px;">' +
            '</div>',
            btn1: function () {
                var xh = $("#xh").val();
                if ("" == xh || xh == undefined){
                    layer.msg('请填写学号',{time:1500});
                } else {
                    if (type == <%=RStatusType.TYPE_FREE%>){
                        $.post( '${ctx}/manage/ring/binding/temp/'+id+'/'+xh,{
                            type:type
                        }, function (res) {
                            if (res.code == '-1') {
                                layer.msg(res.msg,{time:2500});
                            } else if (res.code == '0'){
                                openDialog('绑定个人手环',  '${ctx}/manage/ring/releaseloss/'+id+'/'+xh,'470px','550px');
                            }
                        })

                    } else {
                        $.post( '${ctx}/manage/ring/binding/temp/'+id+'/'+xh,{
                            type:type
                        }, function (res) {
                            if (res.code == '-1') {
                                layer.msg(res.msg,{time:2500});
                            } else if (res.code == '0'){
                                layer.msg(res.msg,{time:1500},function () {
                                    parent.location.reload();
                                });
                            }
                        })
                    }

                }
            }
        })
    }
</script>
</body>
</html>
