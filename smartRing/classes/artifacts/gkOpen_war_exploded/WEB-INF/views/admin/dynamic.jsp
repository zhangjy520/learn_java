<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/base.jsp" %>
<%@ include file="../common/admin/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="${ctx}/static/css/common.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/checkManage.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/pageDevide.min.css"/>
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/pageDevide.js"></script>
    <script src="${ctx}/static/another-js/layer.js"></script>
    <script src="${ctx}/static/another-js/tinymce.min.js"></script>
    <script src="${ctx}/static/js/alertPopShow.js"></script>
</head>
<style>
    td a{
        color: #666 !important;
    }
    p{
        line-height: normal !important;
    }
</style>
<body>
<%@ include file="../common/admin/menu.jsp" %>
<main id="pf-state">
    <p class="operation">
        <button class="publish" data-url="${ctx}/admin/dynamic/publish">发布</button>
        <button class="delete">删除</button></p>
    </p>
    <section>
        <table>
            <thead>
            <tr>
                <th width="1%">
                    <input type="checkbox" class="allCheck" id="selectAll">
                </th>
                <th width="3.5%">序号</th>
                <th width="30%">标题</th>
                <th width="20%">发布时间</th>
                <th width="10%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listView}" var="dynamic" varStatus="status">
                <tr>
                    <td><input type="checkbox" name="tableHeaderCheckBox" value="${dynamic.dynamic.id}"></td>
                    <td>${status.index+1+(pageInfo.pageNum-1)*10}</td>
                    <td><a href="${ctx}/dynamic/detail?id=${dynamic.dynamic.id}">${dynamic.dynamic.title}</a></td>
                    <td>
                        <p>${dynamic.releaseTimeExt}&nbsp;&nbsp;</p>
                    </td>
                    <td>
                        <span class="deleteSingle" data-url="${ctx}/dynamic/del/one?id=${dynamic.dynamic.id}"
                              >删除</span>
                        <input type="hidden" id="ids">
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>
    <footer class="clear" style="margin-top:23px;">
        <p style="color:#999;font-size:13px;float:left;">
            每页显示${pageInfo.pageSize}条，共${pageInfo.pages}页，共${pageInfo.total}条记录</p>
        <div style="float:right;" class="fenY"></div>
    </footer>
</main>

<script>

    $(".allCheck").on("click", function () {
        if (this.checked == true) {
            $("input[type='checkbox']").prop("checked", true);
        } else {
            $("input[type='checkbox']").prop("checked", false);
        }
    });

    $(function () {
        var table = $('table');
        for (var i = 0; i < table.length; i++) {
            var td = $(table[i]).find('tbody tr');
            if (td.length == 0) {
                $(table[i]).siblings('section').before('<span style="font-size:13px;color:#666;">暂无数据</span>')
            }
        }
    })
    $(".fenY").createPage({
        pageCount: '${pageInfo.pages}',
        current: '${pageInfo.pageNum}',
        backFn: function (p) {
            window.location.href = "${ctx}/admin/dynamic?pageNum=" + p + "&pageSize=10";
        }
    });
    $('.publish').click(function () {
        var url = $(this).attr('data-url');
        var content = '<main id="content_body">' +
                '<div style="overflow:hidden;font-size:13px;margin-bottom:20px;">' +
                '<span style="float:left;color:#525252;" >标题：</span>' +
                '<input type="text" id="hr" style="padding-left:5px;height:28px;width:200px;border:1px solid #ddd;outline: none;">' +
                '</div><div style="overflow:hidden;font-size:13px;margin-bottom:20px;">' +
                '<span style="float:left;">内容：</span>' +
                '<div style="float:left;"><textarea id="textareaContent"></textarea></div>' +
                '</div>' +
                '<script>tinymce.init({selector:"textarea",height:140,width:700,language:"zh_CN" });<\/script>' +
                '</main>';
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['800px', '450px'],
            content: content,
            move: true,
            success: function (layero, index) {
                //初始化的函数
            }, cancel: function () {
                //点击×时的回掉函数
                tinymce.remove();
            },
            btn1: function () {
                //btn1 点击确认的回掉
                var hr = $("#hr").val();
                var text = tinyMCE.activeEditor.getContent();
                if (hr == "" ||hr == null) {
                    webToast("标题不能为空", "middle", 3000);
//                    alert("标题不能为空");
                    return false;
                }
                if (text == "" || text.length<=100) {
//                    webToast("内容不能为空且不能低于50字", "middle", 3000);
                    alert("内容不能为空且不能低于50字");
                    return false;
                }
                $.post("${ctx}/admin/dynamic/publish", {
                    hr: hr,
                    content: text
                }, function (data) {
                    window.location.href = "";
                })
            },
            btn2: function () {
                //点击取消时候的回掉
                tinymce.remove();
            }
        })
    })

    $('.deleteSingle').click(function () {
        var url = $(this).attr('data-url');
        console.log("adsfasdf" + url);
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除该动态？</p>',
            move: false,
            btn1: function () {
                console.log(url);
                window.location.href = url;
            }
        })
    })

    $(".gotoPage").click(function () {
        var pageNum = $(".go").val();
        if (pageNum <= 0 || pageNum >${pageInfo.pages}) {
            layer.msg("请输入正确的页码")
        } else {
            window.location.href = "${ctx}/admin/dynamic?pageNum=" + pageNum + "&pageSize=10";
        }
    });


    //选中的是哪个复选框
    $(".delete").click(function () {
        var inputs = $("input[name='tableHeaderCheckBox']:checked");
        var url = null;
        var tempIds =0;
        inputs.each(function () { // 遍历选中的checkbox
            var singleId = $(this).val();
            tempIds += ","+singleId;
        });
        $.get(postPath+"/dynamic/del",{
            ids:tempIds
        },function (data) {
            if(data.code ==0){
                alert(data.msg);
                window.location.href=postPath+"/"+data.data;
            }else {
                alert(data.msg);
            }
        })
    })



    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
</script>
</body>
</html>