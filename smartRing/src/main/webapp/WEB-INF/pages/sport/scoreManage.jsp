<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>成绩管理</title>
    <link rel="stylesheet" href="${ctxStatic}/css/sportstest.css"/>
    <%--<script src="${ctxStat}/js/jquery.min.js"></script>--%>
    <script src="${ctxStatic}/js/plugins/highcharts.js"></script>
    <script src="${ctxStatic}/js/plugins/highcharts-more.js"></script>
    <script src="${ctxStatic}/js/plugins/layer.js"></script>
    <script src="${ctxStatic}/js/openDialog.js"></script>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
        <span class="lf current-position">
            当前位置：
        </span>
    <ul class="lf breadcrumb clear">
        <%-- <li class="root-nav"><a href="${ctx}/sport/test/index">体育测试</a></li>--%>
        <li class="root-nav"><a href="#">体育测试</a></li>
        <li class="child-nav  active"><a href="${ctx}/sport/scoremange/index">成绩管理</a></li>
    </ul>
</header>
<main id="gradeManage" class="fix-width">
    <ul class="sh-tabs clear">
        <li><a onclick="tapC(this)" id="1" data="test1">成绩修改/查看</a></li>
        <li><a onclick="tapC(this)" id="3" data="test3">成绩映射规则</a></li>
        <li><a onclick="tapC(this)" id="4" data="test4">测试项目</a></li>
    </ul>
    <div id="test1" class="fix-width module-bg" style="display: none">
        <header class="clear">
            <aside class="lf">
                <span>测试项目</span>
                <select id="itemName">
                    <c:forEach items="${sportItemList}" var="sportItem">
                        <option <c:if test="${itemName==sportItem.itemName}"> selected</c:if>
                                value="${sportItem.itemId}">${sportItem.itemName}</option>
                    </c:forEach>
                </select>
                <span>测试次序</span>
                <select id="testCount">
                    <c:forEach items="${testCxList}" var="testCx">
                        <option <c:if test="${testCount==testCx}"> selected</c:if> value="${testCx}">${testCx}</option>
                    </c:forEach>
                </select>

                <span>年级</span>
                <select id="nj">
                    <option value="">全部</option>
                    <c:forEach items="${njList1}" var="nj">
                        <option <c:if test="${nj eq chooseNj}">selected</c:if>>${nj}</option>
                    </c:forEach>
                </select>

                <c:if test="${gukeer:notEmptyString(bjList1)}">
                    <span>班级</span>
                    <select id="bj">
                        <option value="">全部</option>
                        <c:forEach items="${bjList1}" var="bj">
                            <option
                                    <c:if test="${bj.classId eq chooseBj}">selected</c:if>
                                    value="${bj.classId}">${bj.className}</option>
                        </c:forEach>
                    </select>
                </c:if>

            </aside>
            <section class="rl clear">
                <button onclick="pageTap(1,1)" class="search"></button>
                <input type="text" id="stuNameOrNo" value="${stuNameOrNo}" placeholder="学生姓名/学号"/>
            </section>
        </header>
        <header class="clear">
            <aside class="lf">
                <p>体育测试成绩</p>
            </aside>
            <section class="rl">
                <button onclick="window.location.href='${ctx}/sport/stuScore/download'">下载模板</button>
                <button class="export" id="scoreImport"
                        onclick="openDialog('导入体育测试成绩','${ctx}/sport/score/import/index','380px','240px');">导入
                </button>
                <button onclick="scoreExport()">导出</button>
                <button class="addNew" onclick="openDialog('添加体育测试成绩','${ctx}/sport/score/add/index','430px','420px');">
                    新增
                </button>
                <div class=" hiddenDelete" style="display:none; float: left;" id="hiddenScore" name="hiddenDelete"><span class="delete" id="deleteScoreStu" type="hidden" style="padding: 0 8px;line-height: 28px;font-size: 12px;margin-left: 5px;">删除</span></div>
            </section>
        </header>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th><input type="checkbox"  name="selectAll2"></th>
                    <th width="8%">测试次序</th>
                    <th width="8%">项目</th>
                    <th width="10%">测试时间</th>
                    <th width="10%">学生姓名</th>
                    <th width="12%">学号</th>
                    <th width="8%">测试成绩</th>
                    <th width="8%">测试单位</th>
                    <th width="9%">分数</th>
                    <th width="9%">等级</th>
                    <th width="13%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${gukeer:notEmptyString(scoreList)}">
                    <c:forEach items="${scoreList}" var="score">
                        <tr>
                            <td><input type="checkbox" id="${score.prim}" name="box2" value="${score.prim}" onclick="countTotal2()"></td>
                                <%--  ${score.itemName}--%>
                            <td>${score.testCount}</td>
                            <td>${score.itemName}</td>
                            <td>${score.testDate}</td>
                            <td>${score.xsxm}</td>
                            <td>${score.stuNum}</td>
                            <c:choose>
                                <c:when test="${score.itemUnit.indexOf('分')>=0}">
                                    <td>${gukeer:unitTranslate(score.mark)}</td>
                                </c:when>
                                <c:otherwise>
                                    <td>${score.mark}</td>
                                </c:otherwise>
                            </c:choose>
                                <%--<td>${score.mark}</td>--%>
                            <td>${score.itemUnit}</td>
                            <td>${score.stuScore}</td>
                            <td>${score.stuLevel}</td>
                            <td>
                                <span class="change"
                                      onclick="openDialog('修改体育测试成绩','${ctx}/sport/score/edit/index?testSeq=${score.prim}','430px','420px');">修改</span>
                                <span class="delete" onclick="deleteScore('${score.prim}')">删除</span>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
        <footer class="pagination clear">
            <div class="lf">
                共${pageInfo.total}条记录，每页显示
                <select id="pageSize">
                    <option <c:if test="${pageSize ==10 }"> selected</c:if> value="">10</option>
                    <option <c:if test="${pageSize ==20 }"> selected</c:if> value="">20</option>
                    <option <c:if test="${pageSize ==50 }"> selected</c:if> value="">50</option>
                </select>
                条记录
            </div>
            <div class="rl fenY">
                <script>
                    <c:if test="${gukeer:notEmptyString(pageInfo.pages)}">
                    $(".fenY").createPage({
                        pageCount:${pageInfo.pages},//总页数
                        current:${pageInfo.pageNum},//当前页面
                        backFn: function (p) {
                            pageTap(1, p, 10);
                        }
                    });
                    </c:if>
                </script>
            </div>
        </footer>
    </div>
    <div id="test3" class="fix-width module-bg" style="display: none">
        <header class="clear">
            <aside class="lf">
                <span>项目名称</span>
                <select id="mapItemName">
                    <c:forEach items="${sportItemList}" var="sportItem">
                        <option <c:if test="${mapItemName==sportItem.itemName}"> selected</c:if>
                                value="${sportItem.itemId}">${sportItem.itemName}</option>
                    </c:forEach>
                </select>
                <span>年级</span>
                <select id="gradeName">
                    <c:forEach items="${njList}" var="nj">
                        <option <c:if test="${gradeName==nj}"> selected</c:if> value="">${nj}</option>
                    </c:forEach>
                </select>
                <span>性别</span>
                <select id="gender">
                    <option <c:if test="${gender ==1 }"> selected</c:if> value="1">男</option>
                    <option <c:if test="${gender ==2 }"> selected</c:if> value="2">女</option>
                </select>
            </aside>

        </header>
        <header class="clear">
            <aside class="lf">
                <p>体育测试成绩</p>
            </aside>
            <section class="rl clear">
                <button onclick="window.location.href='${ctx}/sport/scoreMapRule/download'">下载模板</button>
                <button id="mapRuleImport"
                        onclick="openDialog('导入成绩映射规则','${ctx}/sport/rule/import/index','380px','240px');">导入
                </button>
                <button onclick="scoreMapExport()">导出</button>
                <button onclick="openDialog('添加成绩映射规则','${ctx}/sport/rule/add/index?itemName='+encodeURI(encodeURI('${mapItemName}'))+'&nj='+encodeURI(encodeURI('${gradeName}'))+'&gender='+${gender},'430px','355px');">
                    新增
                </button>
                <div class=" hiddenDelete" style="display:none; float: left;" id="deleteScoreRule" name="hiddenDelete"><span class="delete" type="hidden" id="deleteRule" style="padding: 0 8px;line-height: 28px;font-size: 12px;margin-left: 5px;">删除</span></div>
            </section>
        </header>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th><input type="checkbox"  name="selectAll3"></th>
                    <th width="16.66%">项目</th>
                    <th width="16.66%">成绩</th>
                    <th width="16.66%">单位</th>
                    <th width="16.66%">分数</th>
                    <th width="16.66%">等级</th>
                    <th width="16.66%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${scoreMapRuleList}" var="scoreMapRule">
                    <tr>
                        <td><input type="checkbox" id="${scoreMapRule.ruleId}" name="box3" value="${scoreMapRule.ruleId}" onclick="countTotal3()"></td>
                        <td>${scoreMapRule.itemName}</td>
                        <c:choose>
                            <c:when test="${scoreMapRule.remark.indexOf('秒')>=0}">
                                <td>${gukeer:unitTranslate(scoreMapRule.mark)}</td>
                            </c:when>
                            <c:otherwise>
                                <td>${scoreMapRule.mark}</td>
                            </c:otherwise>
                        </c:choose>
                            <%--<td>${scoreMapRule.mark}</td>--%>
                        <td>${scoreMapRule.remark}</td>
                        <td>${scoreMapRule.score}</td>
                        <td>${scoreMapRule.level}</td>
                        <td>
                            <span class="change"
                                  onclick="openDialog('修改映射规则','${ctx}/sport/rule/edit/index?ruleId=${scoreMapRule.ruleId}&itemUnit='+encodeURI(encodeURI('${scoreMapRule.remark}')),'430px','420px');">修改</span>
                            <span class="delete" onclick="deleteRule('${scoreMapRule.ruleId}')">删除</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <footer class="pagination clear">
            <div class="lf">
                共${pageInfo2.total}条记录，每页显示
                <select id="pageSize2">
                    <option <c:if test="${pageSize2 ==10 }"> selected</c:if> value="10">10</option>
                    <option <c:if test="${pageSize2 ==20 }"> selected</c:if> value="20">20</option>
                    <option <c:if test="${pageSize2 ==50 }"> selected</c:if> value="50">50</option>
                </select>
                条记录
            </div>
            <div class="rl fenY1">
                <script>
                    $(".fenY1").createPage({
                        pageCount:${pageInfo2.pages},//总页数
                        current:${pageInfo2.pageNum},//当前页面
                        backFn: function (p) {
                            pageTap(3, p);
                        }
                    });
                </script>
            </div>
        </footer>
    </div>
    <div id="test4" class="fix-width module-bg" style="display: none">
        <header class="clear">
            <aside class="lf">
                <p>项目列表</p>
            </aside>
            <section class="rl">
                <div class=" hiddenDelete" style="display:none; float: left;" id="hiddenDelete" name="hiddenDelete"><span class="delete" id="deletebatchItem" type="hidden" style="padding: 0 8px;line-height: 28px;font-size: 12px;margin-left: 5px;">删除</span></div>
                <span class="changeadd" onclick="openDialog('添加测试项目','${ctx}/sport/Item/addItem','430px','420px');">添加项目</span>
            </section>
        </header>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th><input type="checkbox" id="selectAll" name="selectAll"></th>
                    <th width="30%">项目名称</th>
                    <th width="50%">单位</th>
                    <th width="20%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${gukeer:notEmptyString(sportItems)}">
                    <c:forEach items="${sportItems}" var="sportItem">
                        <tr>
                            <td><input type="checkbox" id="${sportItem.itemId}" name="box" value="${sportItem.itemId}" onclick="countTotal()"></td>

                            <td>${sportItem.itemName}</td>
                            <td>${sportItem.itemUnit}</td>
                            <td>
                                <span class="change" onclick="openDialog('修改体育项目','${ctx}/sport/item/update?sportItemName='+encodeURI(encodeURI('${sportItem.itemName}')),'430px','420px');">修改</span>
                                <span class="delete" onclick="deleteItem('${sportItem.itemId}')">删除</span>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
        <footer class="pagination clear">
            <div class="lf">
                共${pageInfo3.total}条记录，每页显示
                <select id="pageSize3">
                    <option <c:if test="${pageSize3 ==10 }"> selected</c:if> value="10">10</option>
                    <option <c:if test="${pageSize3 ==20 }"> selected</c:if> value="20">20</option>
                    <option <c:if test="${pageSize3 ==50 }"> selected</c:if> value="50">50</option>
                </select>
                条记录
            </div>
            <div class="rl fenY3">
                <script>
                    <c:if test="${gukeer:notEmptyString(pageInfo3.pages)}">
                    $(".fenY3").createPage({
                        pageCount:${pageInfo3.pages},//总页数
                        current:${pageInfo3.pageNum},//当前页面
                        backFn: function (p) {
                            pageTap(4,p);
                        }
                    });
                    </c:if>
                </script>
            </div>
        </footer>

    </div>
</main>

<script>

    var tapIndex = "${whichTap}";
    tapC($("#" + tapIndex));

    $("#peTest").addClass("active");
    $("#peTest ul li:nth-child(3)>a").addClass("active");

    $("select").change(function () {
        if ($(this).attr("id") != "itemUnitAdd") {
            if ($(this).attr("id") != "pageSize") {
                $("#stuNameOrNo").val("");
            }
            pageTap(tapIndex, 1);
        }
    });

    //成绩映射规则导出功能
    function scoreMapExport() {
        var mapItemName = $("#mapItemName option:selected").text();
        var gradeName = $("#gradeName option:selected").text();
        var gender = $("#gender option:selected").val();

        window.location.href = '${ctx}/sport/scoreMapRule/export?itemName=' + encodeURI(encodeURI(mapItemName)) + "&gradeName=" + encodeURI(encodeURI(gradeName)) + "&gender=" + gender;
    }
    //成绩列表，导出
    function scoreExport() {
        var itemName = $("#itemName option:selected").text();
        var testCount = $("#testCount option:selected").text();
        var stuNameOrNo = $("#stuNameOrNo").val();
        var nj = $("#nj option:selected").text();
        var bj = $("#bj option:selected").val();

        window.location.href = "${ctx}/sport/stuScore/export?itemName=" + encodeURI(encodeURI(itemName)) +
                "&nj=" + encodeURI(encodeURI(nj)) +
                "&bj=" + bj +
                "&stuNameOrNo=" + encodeURI(encodeURI(stuNameOrNo)) +
                "&testCount=" + testCount;
    }

    //分页切换
    function pageTap(tap, p) {

        var itemName = $("#itemName option:selected").text();
        var testCount = $("#testCount option:selected").text();
        var nj = $("#nj option:selected").text();
        var bj = $("#bj option:selected").val();
        var stuNameOrNo = $("#stuNameOrNo").val();
        var pageSize = $("#pageSize option:selected").text();

        var mapItemName = $("#mapItemName option:selected").text();
        var gradeName = $("#gradeName option:selected").text();
        var gender = $("#gender option:selected").val();
        var pageSize2 = $("#pageSize2 option:selected").text();

        var pageSize3 = $("#pageSize3 option:selected").text();

        window.location.href = "${ctx}/sport/scoremange/index?whichTap=" + tap +
                "&pageSize=" + pageSize +
                "&pageSize2=" + pageSize2 +
                "&pageSize3=" + pageSize3 +
                "&gradeName=" + encodeURI(encodeURI(gradeName)) +
                "&nj=" + encodeURI(encodeURI(nj)) +
                "&bj=" + bj +
                "&gender=" + gender +
                "&pageNum=" + p + "" +
                "&pageNum2=" + p + "" +
                "&pageNum3=" + p + "" +
                "&mapItemName=" + encodeURI(encodeURI(mapItemName)) +
                "&stuNameOrNo=" + encodeURI(encodeURI(stuNameOrNo)) +
                "&testCount=" + testCount +
                "&itemName=" + encodeURI(encodeURI(itemName));
    }






    //选中checkbox删除体育项目
    $('#deletebatchItem').click(function () {
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除该项目？</p>',
            move: false,
            btn1: function () {
                var inputs = $("input[name='box']:checked");
                var tempIds = 0;
                inputs.each(function () { // 遍历选中的checkbox
                    var singleId = $(this).val();
                    tempIds += "," + singleId;
                });
                $.get(postPath + "/sport/Item/deleteBatch", {
                    itemId: tempIds
                }, function (retJson) {
                    layer.msg("删除成功", {
                        time: 1000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        window.location.reload(true);
                    });
                })
            }
        })
    })

    $('#deleteRule').click(function () {
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除该规则？</p>',
            move: false,
            btn1: function () {
                var inputs = $("input[name='box3']:checked");
                var tempIds = 0;
                inputs.each(function () { // 遍历选中的checkbox
                    var singleId = $(this).val();
                    tempIds += "," + singleId;
                });
                $.post(postPath + "/sport/rule/deleteBatch", {
                    ruleId: tempIds
                }, function (retJson) {
                    layer.msg("删除成功", {
                        time: 1000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        window.location.reload(true);
                    });
                })
            }
        })
    })
    $('#deleteScoreStu').click(function () {
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除选中成绩？</p>',
            move: false,
            btn1: function () {
                var inputs = $("input[name='box2']:checked");
                var tempIds = 0;
                inputs.each(function () { // 遍历选中的checkbox
                    var singleId = $(this).val();
                    tempIds += "," + singleId;
                });
                $.post(postPath + "/sport/stuScore/deleteBatch", {
                    scoreId: tempIds
                }, function (retJson) {
                    layer.msg("删除成功", {
                        time: 1000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        window.location.reload(true);
                    });
                })
            }
        })
    })



    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);



    //单个删除体育项目
    function deleteItem(str) {
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除该项目？</p>',
            move: false,
            btn1: function () {
                $.post("${ctx}/sport/Item/delete", {
                    itemId: str
                },function (retJson) {
                    layer.msg("ok", {
                        time: 1000 //2秒关闭（如果不配置，默认是3秒）
                    }, function () {
                        window.location.reload(true);
                    });
                });
            }
        })
    }

    function deleteScore(str) {
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除该成绩？</p>',
            move: false,
            btn1: function () {
        $.post("${ctx}/sport/stuScore/delete", {
            testSeq: str
        }, function (retJson) {
            layer.msg("删除成功", {
                time: 2000 //2秒关闭（如果不配置，默认是3秒）
            }, function () {
                window.location.reload(true);
            });
        });
            }
        });
    }

    function deleteRule(str) {
        layer.open({
            title: '提示信息',
            shadeClose: true,
            shade: 0.6,
            btn: ['确认', '取消'],
            area: ['380px', '200px'],
            content: '<p style="color:#525252;font-size:14px;">您确定删除该规则？</p>',
            move: false,
            btn1: function () {
        $.post("${ctx}/sport/rule/delete", {
            ruleId: str
        }, function (retJson) {
            layer.msg("删除成功", {
                time: 2000 //2秒关闭（如果不配置，默认是3秒）

            }, function () {
                window.location.reload(true);
            });
        });
            }
        });
    }

    //添加测试项目
    function addItem() {
        // var itemUnit = $("#itemUnitAdd option:selected").text();
        $.ajax({
            type:"post",
            url:"${ctx}/sport/item/add",
            data:{
                itemName: $('#itemNameAdd').val(),
                itemUnit: $('#itemUnitAdd').val(),
            },
            dataType:"json",
            success:function(data) {
                layer.msg(data.msg, {
                    time: 2000 //2秒关闭（如果不配置，默认是3秒）
                }, function () {
                    if (data.code ==0){
                        $("#itemNameAdd").val("");
                    }else{
                        pageTap(2, 1)
                    }
                });
            },
            error:function(e) {
            }
        });
    }
    function resetInput() {
        $("#itemNameAdd").val("");
    }

    // 体育测试成绩管理下tab页切换
    function tapC(obj) {
        var activeTap = $(".sh-tabs li .active").attr("id");
        var tap = $(obj).attr("id");
        var flag = "undefined" != typeof activeTap;
        if (tap != activeTap && "undefined" != typeof activeTap) {
            var url = window.location.href;
            var urlChange = "";
            urlChange = url.replace(/whichTap=[1-9]/, "whichTap=" + tap);
            if (url == urlChange) {
                urlChange += "?whichTap=" + tap;
            }
            window.location.href = urlChange;
        } else {
            tapIndex = tap;
            $('.sh-tabs a').removeClass('active');
            $(obj).addClass('active');
            var tabItem = $('#gradeManage div[id*=test]');
            //console.log(tabItem)
            var data = $(obj).attr('data');
            for (var i = 0; i < tabItem.length; i++) {
                if (tabItem[i].id == data) {
                    $(tabItem[i]).show();
                    $(tabItem[i]).siblings('div').hide();
                }
            }
        }
    }

    //全选框
    $(function() {
        $("input[name='selectAll']").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
                hiddenDelete.style.display="block";
            } else {
                $("input[type='checkbox']").prop("checked", false);
                hiddenDelete.style.display="none";
            }
        });
    })
    $(function() {
        $("input[name='selectAll2']").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
                hiddenScore.style.display="block";
            } else {
                $("input[type='checkbox']").prop("checked", false);
                hiddenScore.style.display="none";
            }
        });
    })
    $(function() {
        $("input[name='selectAll3']").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);

                deleteScoreRule.style.display="block";
            } else {
                $("input[type='checkbox']").prop("checked", false);
                deleteScoreRule.style.display="none";
            }
        });
    })
    $("input[name='box']").click(function() {
        var $subs = $("input[name='box']");
        $("input[name='selectAll']").prop("checked" , $subs.length == $subs.filter(":checked").length ? true :false);
    });

    $("input[name='box2']").click(function() {
        var $subs = $("input[name='box2']");
        $("input[name='selectAll2']").prop("checked" , $subs.length == $subs.filter(":checked").length ? true :false);
    });

    $("input[name='box3']").click(function() {
        var $subs = $("input[name='box3']");
        $("input[name='selectAll3']").prop("checked" , $subs.length == $subs.filter(":checked").length ? true :false);
    });

    //记录选择checkbox的数目，大于一显示删除按钮
    function countTotal(){
        var count = 0;
        $.each($("input[name='box']:checked"), function() {
            count = count + 1;
        });
        if(count>1){
            hiddenDelete.style.display="block";
        }else {
            hiddenDelete.style.display="none";

        }
    }
    function countTotal2(){
        var count = 0;
        $.each($("input[name='box2']:checked"), function() {
            count = count + 1;
        });
        if(count>1){
            hiddenScore.style.display="block"
        }else {
            hiddenScore.style.display="none"
        }

    }
    function countTotal3(){
        var count = 0;
        $.each($("input[name='box3']:checked"), function() {
            count = count + 1;
        });
        if(count>1){
            deleteScoreRule.style.display="block";
        }else {
            deleteScoreRule.style.display="none";
        }

    }

</script>
</body>
</html>