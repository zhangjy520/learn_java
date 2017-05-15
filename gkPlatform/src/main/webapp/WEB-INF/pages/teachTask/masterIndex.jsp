<%@ include file="../common/headerXf.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>班主任管理</title>
    <link rel="stylesheet" href="${ctxStaticNew}/css/personnel.min.css"/>
</head>
<style>
    .searchh button:after {
        content: '';
        display: inline-block;
        width: 16px;
        height: 16px;
        position: absolute;
        top: 8px;
        left: 10px;
        background: url(../../../assetsNew/images/icon-2.png) no-repeat -1px -65px
    }

    .searchh button {
        float: right;
        height: 30px;
        background: #54ab37;
        width: 35px;
        position: relative;
        border: 1px solid #54ab37;
        border-top-right-radius: 2px;
        border-bottom-right-radius: 2px
    }

    .searchh input {
        float: right;
        height: 31px;
        border: 1px solid #dadada;
        border-right: 0;
        width: 152px;
        padding-left: 5px;
        outline: 0
    }

    #generated {
        padding-top: 20px;
    }

    .row {
        margin: 0;
    }

    .but {
        padding-left: 15px;
    }

    #zh-manage .stu-num-manage-menu {
        margin-top: 0;
    }

    /*tab页*/
    * {
        margin: 0;
        padding: 0;
        list-style: none;
    }

    body {
        font: 12px/1.5 Tahoma;
    }

    #outer {
        width: 450px;
        margin: 150px auto;
    }

    #tab {
        overflow: hidden;
        zoom: 1;
        background: #000;
        border: 1px solid #000;
    }

    #tab li {
        float: left;
        color: #fff;
        height: 30px;
        cursor: pointer;
        line-height: 30px;
        padding: 0 20px;
    }

    #tab li.current {
        color: #000;
        background: #ccc;
    }

    #content {
        border: 1px solid #000;
        border-top-width: 0;
    }

    #content ul {
        line-height: 25px;
        display: none;
        margin: 0 30px;
        padding: 10px 0;
    }
</style>
<body>
<%@ include file="../common/sonHead/teachTaskHead.jsp" %>
<main class="container" id="zh-manage">
    <div class="search-box">
        <div class="stu-num-manage-menu" style="display: inline-block">
            学年：
            <select name="cycleYear" class="cycleYear">
                <c:forEach items="${yearList}" var="year">
                    <option name="cycleYear"
                            <c:if test="${cycleYear ==year}">selected</c:if> value="${year}">${year}</option>
                </c:forEach>
            </select>
            学期：
            <select name="cycleSemester" class="cycleSemester">
                <c:forEach items="${semesterList}" var="cycle">
                    <option name="cycleSemester" value="${cycle.cycleSemester}"
                            <c:if test="${cycleSemester ==cycle.cycleSemester}">selected</c:if>
                            value="${cycle.cycleSemester}">${cycle.cycleSemester}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="roll-operation">
            <button onclick="openDialog('导入数据','${ctx}/class/fileImport?url=${ctx}/teach/task/master/import','500px','350px')">
                导入
            </button>
            <button onclick="window.location.href='${ctx}/teach/task/master/moban/download'">下载模板</button>
        </div>
        <div class="searchh" style="display: inline-block;float: right;">
            <input type="hidden" id="searchHidden" value="${teacherName}"/>
            <button class="summitButton" onclick="searchTeacher()"></button>
            <input class="searchInput" type="text" name="zhiGong" placeholder="请输入老师姓名"/>
        </div>
    </div>
    <section id="generated" class="row">

        <span class="but">
            <c:forEach items="${classSectionList}" var="classSection">
                <c:if test="${classSection.sectionYear == 6}">
                    <button value="${classSection.id}" value="${classSection.id}"
                            onclick="njButton(${classSection.id},1)">一年级</button>
                    <button value="${classSection.id}" onclick="njButton(${classSection.id},2)">二年级</button>
                    <button value="${classSection.id}" onclick="njButton(${classSection.id},3)">三年级</button>
                    <button value="${classSection.id}" onclick="njButton(${classSection.id},4)">四年级</button>
                    <button value="${classSection.id}" onclick="njButton(${classSection.id},5)">五年级</button>
                    <button value="${classSection.id}" onclick="njButton(${classSection.id},6)">六年级</button>
                </c:if>
                <c:if test="${classSection.sectionYear == 3 &&classSection.name=='初中' }">
                    <button value="${classSection.id}" onclick="njButton(${classSection.id},1)">初中一年级</button>
                    <button value="${classSection.id}" onclick="njButton(${classSection.id},2)">初中二年级</button>
                    <button value="${classSection.id}" onclick="njButton(${classSection.id},3)">初中三年级</button>
                </c:if>
                <c:if test="${classSection.sectionYear == 3 &&classSection.name=='高中' }">
                    <button value="${classSection.id}" onclick="njButton(${classSection.id},1)">高中一年级</button>
                    <button value="${classSection.id}" onclick="njButton(${classSection.id},2)">高中二年级</button>
                    <button value="${classSection.id}" onclick="njButton(${classSection.id},3)">高中三年级</button>
                </c:if>
            </c:forEach>
        </span>
        <div class="row">
            <table class="normal">
                <thead>
                <th width="5%">序号</th>
                <th>班级</th>
                <th>班主任</th>
                <th>副班主任</th>
                <th>操作</th>
                </thead>
                <tbody>
                <c:forEach items="${bzrViewPageList}" var="BZR" varStatus="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${BZR.className}</td>
                        <td>${BZR.masterName}</td>
                        <td>${BZR.deputymasterName}</td>
                        <td><span
                                onclick="openDialog('编辑','${ctx}/teach/task/master/edit/pop?classId=${BZR.classId}&&deputyName=${BZR.deputymasterName}&&master=${BZR.masterName}&&teacherId=${BZR.teacherId}&&deputyIds=${BZR.deputyIds}&&cycleId=${BZR.cycleId}','500px','352px');"
                                style="background-color: #6b828e;float: left;margin-top: 10px;">编辑</span></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="fenye" style="width:98.5%;padding-left:15px;">
            <c:if test="${gukeer:notEmptyString(teacherClassPageInfo.pages)}">
                <div class="fenYDetail">共${teacherClassPageInfo.total}条记录，本页${teacherClassPageInfo.size}条</div>
            </c:if>
            <div class="fenY2" id="fenY2">
            </div>
        </div>
    </section>
</main>
<script>
    //id为sectionId,nj为传入的nj的
    function njButton(id, nj) {
        debugger;
        var cycleYear = $(".cycleYear").find("option:selected").val();
        var cycleSemester = $(".cycleSemester").find("option:selected").val();
//        if ($(this).attr("name") == "cycle_year") {
//            cycleSemester = "";
//        }
        window.location.href = "${ctx}/teach/task/master/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester + "&&nj=" + nj + "&&sectionId=" + id;
    }

    $(function () {
        $("select").change(function () {
            var cycleSemester = $("select[name='cycleSemester']").val();
            var cycleYear = $("select[name='cycleYear']").val();
            window.location.href = "${ctx}/teach/task/master/index?cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
        });

        <c:if test="${TeacherClassPageInfo!=null&&TeacherClassPageInfo.pages != 0}">
        $(".fenY2").createPage({
            pageCount:${TeacherClassPageInfo.pages},//总页数
            current:${TeacherClassPageInfo.pageNum},//当前页面
            backFn: function (p) {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/master/index?pageNum=" + p + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
            }
        });
        </c:if>

        $(".headerCheck").on("click", function () {
            if (this.checked == true) {
                $("input[type='checkbox']").prop("checked", true);
            } else {
                $("input[type='checkbox']").prop("checked", false);
            }
        });

        <c:if test="${TeacherClassPageInfo!=null&&TeacherClassPageInfo.pages != 0}">
        $(".gotoPage").click(function () {
            var pageNum = $(".fenY2go").val();
            if (pageNum <= 0 || pageNum >${TeacherClassPageInfo.pages}) {
                layer.msg("请输入正确的页码")
            } else {
                var cycleSemester = $("select[name='cycleSemester']").val();
                var cycleYear = $("select[name='cycleYear']").val();
                window.location.href = "${ctx}/teach/task/master/index?pageNum=" + $(".fenY2go").val() + "&cycleYear=" + cycleYear + "&cycleSemester=" + cycleSemester;
            }
        });
        </c:if>
    });

    function searchTeacher() {
        var cycleSemester = $("select[name='cycleSemester']").val();
        var cycleYear = $("select[name='cycleYear']").val();
        var name = $("input[name='zhiGong']").val();
        window.location.href = "${ctx}/teach/task/master/search?name=" + encodeURI(encodeURI(name)) + "&&cycleSemester=" + cycleSemester + "&&cycleYear=" + cycleYear;
    }


    function importCallBack(res) {
        layer.closeAll();
        layer.confirm(res.msg, {
            btn: ['下载失败列表', '关闭'] //按钮
        }, function () {
            var form = $("<form>");//定义一个form表单
            form.attr("style", "display:none");
            form.attr("target", "");
            form.attr("method", "post");
            form.attr("action", "${ctx}/teach/task/room/error/export");
            var input1 = $("<input>");
            input1.attr("type", "hidden");
            input1.attr("name", "msg");
            input1.attr("value", JSON.stringify(res.errorList));
            $("body").append(form);//将表单放置在web中
            form.append(input1);
            form.submit();//表单提交
            return false;
        }, function () {
            window.location.reload(true);
        });
    }
</script>
</body>
</html>