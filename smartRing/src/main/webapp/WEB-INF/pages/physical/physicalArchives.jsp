<%@ include file="../common/newHeader.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生体质档案</title>
    <link rel="stylesheet" href="${ctxStatic}/css/physicalHealth.css"/>
</head>
<body>
<header id="breadcrumb-nav" class="clear">
                <span class="lf current-position">
                    当前位置：
                </span>
    <ul class="lf breadcrumb clear">
        <li class="root-nav"><a href="#">体质健康</a></li>
        <li class="child-nav  active"><a href="#">学生体质档案</a></li>
    </ul>
</header>
<main class="physicalHealth3 fix-width">
    <section class="items-selector module-bg">
        <div class="clear">
            <button onclick="pageReload(1,1)" class="button search rl"></button>
            <input style="border-right:none;margin-top:0;" type="text" id="stuNameOrNo" class="rl search" value="${stuNameOrNo}" placeholder="学生姓名/学号"/>
        </div>
        <div class="clear">
            <span class="lf">年级</span>
            <ul id="njSelect" class="lf clear select-items">
                <c:forEach items="${njList}" var="nj">
                    <li
                            <c:if test="${njChoose eq nj}">class="active" </c:if> >${nj}</li>
                </c:forEach>
            </ul>
            <div class="header-select-more">更多</div>
        </div>
        <div class="clear">
            <span class="lf">班级</span>
            <ul id="bjSelect" class="lf clear select-items">
                <li
                        <c:if test="${bjChoose eq 0}">class="active" </c:if> id="0">全部
                </li>
                <c:forEach items="${bjList}" var="bj">
                    <li
                            <c:if test="${bjChoose eq bj.classId}">class="active" </c:if>
                            id="${bj.classId}">${bj.className}</li>
                </c:forEach>
            </ul>
            <div class="header-select-more">更多</div>
        </div>
    </section>
    <section class="ph-section module-bg-full">
        <p>日常健康</p>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th width="12.5%">姓名</th>
                    <th width="12.5%">年级</th>
                    <th width="12.5%">班级</th>
                    <th width="12.5%">学号</th>
                    <th width="12.5%">运动时长</th>
                    <th width="12.5%">入睡时间</th>
                    <th width="12.5%">睡眠时长</th>
                    <th width="12.5%">操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${dailyListView}" var="daily">
                    <tr>
                        <td>${daily.xsxm}</td>
                        <td>${daily.indexName}</td>
                        <td>${daily.className}</td>
                        <td>${daily.xh}</td>
                        <td>${daily.sportStandard}</td>
                        <td>${daily.asleepStandard}</td>
                        <td>${daily.sleepLongStandard}</td>
                        <td>
                            <span class="change" onclick="window.open('${ctx}/physical/archives/detail/${daily.xh}')">查看详情</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <footer class="pagination clear">
                <div class="lf">
                    共${dailyPageInfo.total}条记录，每页显示
                    <select class="pageSize" id="dailyPageSe">
                        <option <c:if test="${dailyPageInfo.pageSize ==10 }"> selected</c:if> value="10">10</option>
                        <option <c:if test="${dailyPageInfo.pageSize ==20 }"> selected</c:if> value="20">20</option>
                        <option <c:if test="${dailyPageInfo.pageSize ==50 }"> selected</c:if> value="50">50</option>
                    </select>
                    条记录
                </div>
                <div class="rl fenY">
                    <script>
                        $(".fenY").createPage({
                            pageCount: ${dailyPageInfo.pages},
                            current: ${dailyPageInfo.pageNum},
                            backFn: function (p) {
                                pageReload(1,p);
                            }
                        });
                    </script>
                </div>
            </footer>
        </div>
    </section>
    <section class="ph-section module-bg-full" style="margin-bottom:80px;">
        <p class="clear">
            体育测试
            <select id="itemSelect" class="rl">
                <c:forEach items="${itemList}" var="item">
                    <option <c:if test="${itemChoose eq item.itemId}">selected</c:if> value="${item.itemId}">${item.itemName}</option>
                </c:forEach>
            </select>
        </p>
        <div>
            <table class="table">
                <thead>
                <tr>
                    <th width="8%">姓名</th>
                    <th width="8%">年级</th>
                    <th width="8%">班级</th>
                    <th width="15%">学号</th>
                    <th width="15%">项目</th>
                    <th width="8%">测试成绩</th>
                    <th width="8%">成绩单位</th>
                    <th width="8%">分数</th>
                    <th width="8%">等级</th>
                    <th width="15%">操作</th>
                </tr>
                </thead>
                <tbody id="pe_test_table">
                <c:forEach items="${peList}" var="pe">
                    <tr>
                        <td>${pe.xsxm}</td>
                        <td>${pe.njName}</td>
                        <td>${pe.className}</td>
                        <td>${pe.stuNum}</td>
                        <td>${pe.itemName}</td>
                        <c:if test="${pe.itemUnit.indexOf('分')>=0}">
                            <td>${gukeer:unitTranslate(pe.mark)}</td>
                        </c:if>
                        <c:if test="${pe.itemUnit.indexOf('分')<0}">
                            <td>${pe.mark}</td>
                        </c:if>
                        <td>${pe.itemUnit}</td>
                        <td>${pe.stuScore}</td>
                        <td>${pe.stuLevel}</td>
                        <td>
                            <span class="change" onclick="window.open('${ctx}/physical/archives/detail/${pe.stuNum}')">查看详情</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <footer class="pagination clear">
                <div class="lf">
                    共${pePageInfo.total}条记录，每页显示
                    <select class="pageSize" id="pePageSize">
                        <option <c:if test="${pePageInfo.pageSize ==10 }"> selected</c:if> value="10">10</option>
                        <option <c:if test="${pePageInfo.pageSize ==20 }"> selected</c:if> value="20">20</option>
                        <option <c:if test="${pePageInfo.pageSize ==50 }"> selected</c:if> value="50">50</option>
                    </select>
                    条记录
                </div>
                <div class="rl fenY1">
                    <script>
                        $(".fenY1").createPage({
                            pageCount: ${pePageInfo.pages},
                            current: ${pePageInfo.pageNum},
                            backFn: function (p) {
                                pageReload(p,1);
                            }
                        });
                    </script>
                </div>
            </footer>
        </div>
    </section>
</main>
<script>
    $("#physicalHealth").addClass("active");
    $("#physicalHealth ul li:nth-child(3)>a").addClass("active");

    $('.select-items li').click(function () {
        $(this).addClass('active');
        $(this).siblings().removeClass('active');

        $("#stuNameOrNo").val("");
        pageReload(1,1);
    })

    $("select").change(function () {

        if ($(this).attr("class") != "pageSize") {
            $("#stuNameOrNo").val("");
        }
        pageReload(1,1);
    });

    //班级年级更多
    function isHide(grade){
        if(grade.length == 0)
            return;
        var length=0;
        grade.map(function(i,key){
            var length=0,pLength=grade.parent()[0].clientWidth;
            grade.map(function(i,key){
                length+=key.clientWidth;
                if(length>=pLength){
                    $(grade).parent().css('height','25px');
                    $(grade).parent().css('overflow','hidden');
                    var selectMore=$(grade).parent().next();
                    selectMore.show();
                    selectMore.click(function(){
                        if($(grade).parent().css('height')=='25px'){
                            $(grade).parent().css('height','auto');
                        }else{
                            $(grade).parent().css('height','25px');
                        }
                    })
                }
            })
    })
    }
    isHide($('#njSelect li'))
    isHide($('#bjSelect li'))

    function pageReload(p1,p2) {
        var stuNameOrNo = $("#stuNameOrNo").val();
        var nj = $("#njSelect >.active").text();
        var bj = $("#bjSelect >.active").attr("id");
        var itemId = $("#itemSelect option:selected").val();
        var pePageSize = $("#pePageSize option:selected").val();
        var pePageNum = p1;
        var dayPageSize = $("#dailyPageSe option:selected").val();
        var dayPageNum = p2;
        window.location.href = "${ctx}/physical/archives/index?nj=" + encodeURI(encodeURI(nj)) +
                "&bj=" + bj +
                "&itemId=" + itemId +
                "&pePageSize=" + pePageSize +
                "&pePageNum=" + pePageNum +
                "&dayPageSize=" + dayPageSize +
                "&dayPageNum=" + dayPageNum +
                "&stuNameOrNo=" + encodeURI(encodeURI(stuNameOrNo));
    }


    /*function peTable(p,bol) {
     var classId = $("#bjSelect >.active").attr("id");
     var itemId = $("#itemSelect option:selected").val();
     var stuNameOrNo = $("#stuNameOrNo").val();
     var pageSize = $("#pePageSize option:selected").val();

     $.post("${ctx}/physical/peTest/table/index", {
     xdId: "${xdId}",
     njId: "${njId}",
     classId: classId,
     itemId: itemId,
     stuNameOrNo: stuNameOrNo,
     pageSize: pageSize,
     pageNum: p,
     }, function (retJson) {
     $("#pe_test_table").html("");

     $("#peTotal").html(retJson.total);

     var data = retJson.data;
     for (var i = 0; i < data.length; i++) {
     $("#pe_test_table").append("<tr>" +
     "<td>" + data[i].xsxm + "</td>" +
     "<td>" + data[i].njName + "</td>" +
     "<td>" + data[i].className + "</td>" +
     "<td>" + data[i].stuNum + "</td>" +
     "<td>" + data[i].itemName + "</td>" +
     "<td>" + data[i].mark + "</td>" +
     "<td>" + data[i].itemUnit + "</td>" +
     "<td>" + data[i].stuScore + "</td>" +
     "<td>" + data[i].stuLevel + "</td>" +
     "<td><span class='change'>查看详情</span></td>" +
     "</tr>");
     }

     });
     }*/

    /*function initPage(obj,pageCount,pageNum) {
     $(obj).createPage({
     pageCount: pageCount,
     current: pageNum,
     backFn: function (p) {
     peTable(p,false);
     //console.log(new Date())
     }
     });
     }*/
</script>
</body>
</html>