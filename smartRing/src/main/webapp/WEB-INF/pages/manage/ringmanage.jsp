<%@ page import="cc.gukeer.common.utils.ConstantUtil" %>
<%@ page import="cc.gukeer.smartRing.common.RStatusType" %>
<%@ include file="../common/newHeader.jsp" %>
<c:set var="njList" value="<%=ConstantUtil.njList%>"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>手环管理</title>
    <link rel="stylesheet" href="${ctxStatic}/css/basicManage.css"/>
    <!--<link rel="stylesheet" href="css/daterangepicker.css"/>-->
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
    <script src="${ctxStatic}/js/openDialog.js"></script>
    <!--<script src="js/plugins/jquery.daterangepicker.js"></script>-->
</head>
<body>
<style>
    #sh-lose-1 table th, #sh-lose-2 table th {
        width: 16.66%;
    }
</style>

<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a href="#">基础管理</a></li>
        <li class="child-nav  active"><a href="#">手环挂失和绑定</a></li>
    </ul>
</header>
<div id="bodyContainer">
    <main id="sh-lose" class="fix-width">
        <ul class="sh-tabs clear">
            <li><a href="#" data="sh-lose-1" id="sh-using">挂失手环</a></li>
            <li><a href="#" data="sh-lose-2" id="sh-lost">解除挂失</a></li>
            <li><a href="#" data="sh-lose-3" id="sh-bunding">绑定手环</a></li>
            <%--<li><a href="#" data="sh-lose-4">上交手环</a></li>--%>
        </ul>
        <div class="sh-tab-item" id="sh-lose-1" style="display: none">
            <div>
                <p class="clear">
                    <button class="rl research" id="searchBtn"></button>
                    <input type="text" placeholder="学生姓名/学号" class="rl" id="search" value="${search}"/>
                </p>
                <table class="table">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>班级</th>
                        <th>学号</th>
                        <th>手环编号</th>
                        <th>类别</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach items="${using.list}" var="using">
                        <tr>
                            <td>${using.stName}</td>
                            <td>${using.xdName}
                                <c:forEach items="${njList}" var="nj">
                                    <c:if test="${using.nj == nj.key}">${nj.value}</c:if>
                                </c:forEach>
                                    ${using.className}</td>
                            <td>${using.xh}</td>
                            <td>${using.mac}</td>
                            <td><c:if test="${using.type == 0}">个人手环</c:if>
                                <c:if test="${using.type == 1}">临时手环</c:if></td>
                            <td><span class="sh-operation" id="reportLoss" onclick="reportLoss('${using.id}', <%=RStatusType.STATUS_LOSING%>)">挂失</span>
                            </td>
                        </tr>
                    </c:forEach>


                    </tbody>
                </table>
            </div>
            <footer class="pagination clear">
                <c:if test="${using.pages!=0}">
                    <div class="lf">
                        共${using.total}条记录，每页显示
                        <select id="usingSize">
                            <option value="10" <c:if test="${using.pageSize==10}">selected</c:if>>10</option>
                            <option value="20" <c:if test="${using.pageSize==20}">selected</c:if>>20</option>
                            <option value="50" <c:if test="${using.pageSize==50}">selected</c:if>>50</option>
                        </select>
                        条记录
                    </div>
                    <div class="rl fenY">

                        <script>
                            $(".fenY").createPage({
                                pageCount: ${using.pages},
                                current: ${using.pageNum},
                                backFn: function (p) {
                                    window.location.href = "${ctx}/manage/ring/index?usingNum=" + p + "&usingSize=" + $('#usingSize').val() + "&search=" + encodeURI(encodeURI($('#search').val())) + "&which=1";
                                }
                            });
                        </script>
                    </div>
                </c:if>
            </footer>
        </div>
        <div class="sh-tab-item" id="sh-lose-2" style="display: none">
            <div>
                <p class="clear">
                    <button class="rl research" id="lostBtn"></button>
                    <input type="text" placeholder="学生姓名/学号" class="rl" value="${lostSearch}" id="lostSearch"/>
                </p>
                <table class="table">
                    <thead>
                    <tr>
                        <th>姓名</th>
                        <th>班级</th>
                        <th>学号</th>
                        <th>手环编号</th>
                        <th>类别</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${lost.list}" var="lost">
                        <tr>
                            <td>${lost.stName}</td>
                            <td>${lost.xdName}
                                <c:forEach items="${njList}" var="nj">
                                    <c:if test="${lost.nj == nj.key}"> ${nj.value} </c:if>
                                </c:forEach>
                                    ${lost.className}</td>
                            <td>${lost.xh}</td>
                            <td>${lost.mac}</td>
                            <td><c:if test="${lost.type == 0}">个人手环</c:if>
                                <c:if test="${lost.type == 1}">临时手环</c:if></td>
                            <td>
                                    <%--<span class="sh-operation">操作</span>--%>
                                <span class="sh-release" onclick="reportLoss('${lost.id}',
                                <c:if test="${lost.type==0}"><%=RStatusType.STATUS_USED%></c:if>
                                <c:if test="${lost.type==1}"><%=RStatusType.STATUS_UNUSED%></c:if>
                                        )">解除挂失</span>
                                    <%--<span class="sh-confirm" onclick="reportLoss(${lost.id},2)">确认丢失</span>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <footer class="pagination clear">
                <c:if test="${lost.pages!=0}">
                    <div class="lf">
                        共${lost.total}条记录，每页显示
                        <select id="lostSize">
                            <option value="10" <c:if test="${lost.pageSize==10}">selected</c:if>>10</option>
                            <option value="20" <c:if test="${lost.pageSize==20}">selected</c:if>>20</option>
                            <option value="50" <c:if test="${lost.pageSize==50}">selected</c:if>>50</option>
                        </select>
                        条记录
                    </div>
                    <div class="rl fenY1">
                        <script>
                            $(".fenY1").createPage({
                                pageCount: ${lost.pages},
                                current: ${lost.pageNum},
                                backFn: function (p) {
                                    window.location.href = "${ctx}/manage/ring/index?lostNum=" + p + "&lostSize=" + $('#lostSize').val() + "&lostSearch=" + encodeURI(encodeURI($('#lostSearch').val())) + "&which=2";
                                }
                            });
                        </script>
                    </div>
                </c:if>
            </footer>
        </div>
        <div class="sh-tab-item" id="sh-lose-3" style="display: none">
            <p class="clear">
                <button class="rl button" style="margin-left:15px;"
                        onclick="window.location.href='${ctx}/manage/bundlingtemplate'">下载模板
                </button>
                <button id="multibundling" class="rl button">批量绑定</button>
            </p>
            <div>
                <span>学号</span>
                <input type="text" id="xh"/>
            </div>
            <div>
                <span>手环编号</span>
                <input type="text" id="mac"/>
            </div>
            <div class="sh-type">
                <span>手环种类</span>
                <label for="sh-personal" class="checked">
                    <input type="radio" id="sh-personal" hidden="hidden" value="0" name="type" checked/>
                    <span>个人手环</span>
                </label>
                <label for="sh-temporary">
                    <input type="radio" id="sh-temporary" hidden="hidden" name="type" value="1"/>
                    <span>临时手环</span>
                </label>
            </div>
            <div>
                <button class="button" onclick="bundling()">绑定</button>
            </div>
        </div>
    </main>
</div>
<script>
    $(function () {
        if (${gukeer:emptyString(which)} || ${which == 1}) {
            $('.sh-tabs a').removeClass('active');
            $("#sh-using").addClass('active');
            var data = $("#sh-using").attr('data');
            var div = $('#' + data);
            $('#' + div[0].id).show();
            $('#' + div[0].id).siblings('div').hide();
        } else if (${which == 2}) {
            $('.sh-tabs a').removeClass('active');
            $("#sh-lost").addClass('active');
            var data = $("#sh-lost").attr('data');
            var div = $('#' + data);
            $('#' + div[0].id).show();
            $('#' + div[0].id).siblings('div').hide();
        } else {
            $('.sh-tabs a').removeClass('active');
            $("#sh-bunding").addClass('active');
            var data = $("#sh-bunding").attr('data');
            var div = $('#' + data);
            $('#' + div[0].id).show();
            $('#' + div[0].id).siblings('div').hide();
        }

    })
    $("#usingSize").change(function () {
        window.location.href = "${ctx}/manage/ring/index?usingNum=" + 1 + "&usingSize=" + $('#usingSize').val() + "&search=" + encodeURI(encodeURI($('#search').val())) + "&which=1";
    });
    $('#searchBtn').click(function () {
        window.location.href = "${ctx}/manage/ring/index?usingNum=" + 1 + "&usingSize=" + $('#usingSize').val() + "&search=" + encodeURI(encodeURI($('#search').val())) + "&which=1";
    })
    $("#lostSize").change(function () {
        window.location.href = "${ctx}/manage/ring/index?lostNum=" + 1 + "&lostSize=" + $('#lostSize').val() + "&lostSearch=" + encodeURI(encodeURI($('#lostSearch').val())) + "&which=2";
    });
    $('#lostBtn').click(function () {
        window.location.href = "${ctx}/manage/ring/index?lostNum=" + 1 + "&lostSize=" + $('#lostSize').val() + "&lostSearch=" + encodeURI(encodeURI($('#lostSearch').val())) + "&which=2";
    })
    function reportLoss(sid, status) {
        $.post("${ctx}/manage/ring/report", {
            id: sid,
            status: status
        }, function (res) {
            if (res.code == 0) {
                layer.msg(res.msg);
                setTimeout(function () {
                    if (status == <%=RStatusType.STATUS_UNUSED%>) {
                        window.location.href = "${ctx}/manage/ring/index?usingNum=" + 1 + "&usingSize=" + $('#usingSize').val() + "&search=" + encodeURI(encodeURI($('#search').val())) + "&which=1";
                    }
                    else {
                        window.location.href = "${ctx}/manage/ring/index?lostNum=" + 1 + "&lostSize=" + $('#lostSize').val() + "&lostSearch=" + encodeURI(encodeURI($('#lostSearch').val())) + "&which=2";
                    }
                }, 1500)
            } else if (res.code == -1) {
                layer.open({
                    type: 2,
                    title: '绑定手环',
                    shadeClose: true,
                    resize:false,
                    move: false,
                    btn: ['确认', '取消'],
                    area: ['380px', '430px'],
                    content: '${ctx}/manage/ring/erromsg?xh=' + res.msg,
                    //move: '.mine-move',
                    btn1: function () {
                        var h = layer.getChildFrame('.sh-type');
                        var length = h.length;
                        var data = [];
                        for (var i = 0; i < length; i++) {
                            var each = new Object();
                            var id = h[i].querySelector('.ringmac').getAttribute('name');
                            ;
                            var target = h[i].querySelector('label[class=checked] input').getAttribute('value');
                            each.id = id;
                            each.target = target;
                            data.push(each);
                        }

                        $.post('${ctx}/manage/ring/again', {
                            data: JSON.stringify(data),
                            id:sid,
                            status: status,
                            flag:true
                        }, function (res) {
                            if (res.code == 0) {
                                layer.msg(res.msg, {
                                    time: 1500 //2秒关闭（如果不配置，默认是3秒）
                                }, function(){
                                    window.location.href='${ctx}/manage/ring/index?which=2';
                                });
                            } else {
                                layer.msg("失败，请重试", {
                                    time: 1500 //2秒关闭（如果不配置，默认是3秒）
                                }, function(){
                                    window.location.href='${ctx}/manage/ring/index?which=2';
                                });
                            }
                        })
                    }
                });
            }
        })
    }
    function bundling() {
        var xh = $('#xh').val();
        if (xh == null || xh == "") {
            layer.msg("请填写学号");
            return false;
        }

        var mac = $('#mac').val();
        if (mac == null || mac == "") {
            layer.msg("请填写手环编号");
            return false;
        }

        var type = $('input[name="type"]:checked').val();

        $.post("${ctx}/manage/ring/bundling", {
            xh: xh,
            mac: mac,
            type: type
        }, function (res) {
            if (res.code != -2) {
                layer.msg(res.msg, {
                    time: 1500
                }, function(){
                    window.location.href='${ctx}/manage/ring/index?which=3';
                });
            } else {
                layer.open({
                    type: 2,
                    title: '绑定手环',
                    shadeClose: true,
                    shade: 0.8,
                    resize:false,
                    move: false,
                    btn: ['确认', '取消'],
                    area: ['380px', '430px'],
                    content: '${ctx}/manage/ring/erromsg?xh=' + xh,
                    //move: '.mine-move',
                    btn1: function () {
                        var h = layer.getChildFrame('.sh-type');
                        var length = h.length;
                        var data = [];
                        for (var i = 0; i < length; i++) {
                            var each = new Object();
                            var id = h[i].querySelector('.ringmac').getAttribute('name');

                            var target = h[i].querySelector('label[class=checked] input').getAttribute('value');
                            each.id = id;
                            each.target = target;
                            data.push(each);
                        }
                        $.post('${ctx}/manage/ring/again', {
                            data: JSON.stringify(data),
                            xh: xh,
                            mac: mac,
                            type: type
                        }, function (res) {
                            if (res.code == 0) {
                                layer.msg(res.msg, {
                                    time: 1500 //2秒关闭（如果不配置，默认是3秒）
                                }, function(){
                                    window.location.href='${ctx}/manage/ring/index?which=3'
                                });
                            } else {
                                layer.msg("失败，请重试", {
                                    time: 1500 //2秒关闭（如果不配置，默认是3秒）
                                }, function(){
                                    <%--window.location.href='${ctx}/manage/ring/index?which=3';--%>
                                });
                            }
                        })
                    }
                });
            }
        });
    }


    $('#multibundling').click(function () {
        layer.open({
            type: 2,
            title: '批量绑定手环',
            shadeClose: true,
            shade: 0.8,
            resize:false,
            move: false,
            btn: ['确认', '取消'],
            area: ['380px', '240px'],
            content: '${ctx}/manage/importuser',
//            //move: '.mine-move',
            btn1: function () {
//                var index = layer.msg('导入中', {icon: 16, shade: 0.5, time: 1000000000});//当生成完成这个对话框才被关掉
                var index = layer.load();
                $.ajax({
                    url: '${ctx}/manage/multibundling/save',
                    type: 'POST',
                    cache: false,
                    data: new FormData(layer.getChildFrame('#form')[0]),
                    processData: false,
                    contentType: false
                }).done(function (res) {
                    layer.close(index);
                    if (res.code == 0) {
                        layer.msg(res.msg, {
                            time: 1500 //2秒关闭（如果不配置，默认是3秒）
                        }, function(){
                            window.location.href='${ctx}/manage/ring/index?which=3';
                        });
                    } else {
                        var tem = res.data;
                        if(tem == undefined){
                            layer.msg("文件有误");
                            return false;
                        }
                        var datamsg = '';
                        for (var i = 0; i < tem.length; i++) {
                            datamsg += '<tr>' +
                                    '<td>' + tem[i].mac + '</td>' +
                                    '<td>' + tem[i].xh + '</td>' +
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
                                '         <th style="width:33.33%;">手环编号</th>' +
                                '         <th style="width:33.33%;">学号</th>' +
                                '         <th style="width:33.33%;">失败原因</th>' +
                                '         </tr>' +
                                '         </thead>' +
                                '         <tbody>' +
                                datamsg +
                                ' </tbody>' +
                                ' </table>' +
                                ' </main>';
                        layer.open({
                            type: 1,
                            title: '批量绑定手环',
                            shadeClose: true,
                            shade: 0,
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
                                form.attr("action","${ctx}/manage/ring/erroexport");
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
</script>
</body>
</html>
