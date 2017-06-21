<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../common/common.jsp" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctxStaticNew}/js/openDialog.js"></script>
</head>
<style>
form {
    font-size: 14px;
    color: #525252;
}
    form{
        width: 80%;
    }
    form input[type='checkbox']{
        margin: 8px 5px 8px 0;
    }
    .school-type{
        font-size: 16px;
        margin-top: 20px;
    }
    ul{
        margin-top: 0 !important;
    }
    ul span{
        display: inline-block;
        width: 88px;
    }
</style>
<body>
<div>
    <form action="${ctx}/teach/task/course/class/add?courseId=${courseId}" method="post" class="courseClass">
        <ul>
            <%--
                    <c:if test="${gradeClassExtention.xdName=='小学'}">
                        <span>${gradeClassExtention.xdName}</span><br>
                        <c:if test="${gradeClassExtention.nj ==1}">
                            <input type="checkbox">一年级
                        </c:if>
                <c:if test="${gradeClassExtention.nj ==2}">
                        <input type="checkbox">二年级
                    </c:if>
                <c:if test="${gradeClassExtention.nj ==2}">
                        <input type="checkbox">三年级
                    </c:if>

                        <input type="checkbox">四年级
                        <input type="checkbox">五年级
                        <input type="checkbox">六年级
                    </c:if>
                    <br>
                    <c:if test="${section.name=='初中'}">
                        <span>${section.name}</span><br>
                        <input type="checkbox">一年级
                        <input type="checkbox">二年级
                        <input type="checkbox">三年级
                    </c:if>
                    <c:if test="${section.name=='高中'}">
                        <span>${section.name}</span><br>
                        <input type="checkbox">一年级
                        <input type="checkbox">二年级
                        <input type="checkbox">三年级
                    </c:if>
                    &lt;%&ndash;<input type="checkbox" name="Jszzdm"/>${sectionView.name}&ndash;%&gt;
                    &lt;%&ndash;<c:forEach items='${sectionView.njview}' var='njview'>&ndash;%&gt;
                    &lt;%&ndash;<input type="checkbox" name="Jszzdm"/>${njview.njname}&ndash;%&gt;
                    &lt;%&ndash;<c:forEach items='${njview.banjiview}' var='banJiView'>&ndash;%&gt;
                    &lt;%&ndash;<input type="checkbox" name="classId" value="${banJiView.id}"&ndash;%&gt;
                    &lt;%&ndash;<c:if test="${banJiView.id==banJiView.selectedId}">checked</c:if>&ndash;%&gt;
                    &lt;%&ndash;/>${banJiView.name}&ndash;%&gt;
                    &lt;%&ndash;</c:forEach>&ndash;%&gt;
                    &lt;%&ndash;</c:forEach>&ndash;%&gt;
            </c:forEach>--%>
            <c:forEach items="${sectionList}" var="section">
                <c:if test="${section.name=='小学'}">
                    <input type="checkbox" value="${section.id}" class="xiaoXue"><span class="school-type">${section.name}</span><br>
                    <c:forEach items="${gradeClassList}" var="gradeClassExtention">
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==1}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="xiaoXueF"
                                   val="aa" <c:if test="${gradeClassExtention.id==gradeClassExtention.selectedId}">checked</c:if>><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==2}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="xiaoXueF"
                                   val="aa" <c:if test="${gradeClassExtention.id==gradeClassExtention.selectedId}">checked</c:if>><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==3}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="xiaoXueF" class="classId"
                                   val="aa"><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==4}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="xiaoXueF" class="classId"
                                   val="aa"><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==5}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="xiaoXueF" class="classId"
                                   val="aa"><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==6}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="xiaoXueF" class="classId"
                                   val="aa"><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                    </c:forEach>
                </c:if>

                <c:if test="${section.name=='初中'}">
                    <input type="checkbox" value="${section.id}" class="chuZhong"><span class="school-type">${section.name}</span><br>
                    <c:forEach items="${gradeClassList}" var="gradeClassExtention">
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==1}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="chuZhongF" class="classId"
                                   val="aa" <c:if test="${gradeClassExtention.id==gradeClassExtention.selectedId}">checked</c:if>><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==2}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="chuZhongF" class="classId"
                                   val="aa"><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==3}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="chuZhongF" class="classId"
                                   val="aa"><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                    </c:forEach>
                </c:if>

                <c:if test="${section.name=='高中'}">
                    <input type="checkbox" value="${section.id}" class="gaoZhong"><span class="school-type">${section.name}</span><br>
                    <c:forEach items="${gradeClassList}" var="gradeClassExtention">
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==1}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="gaoZhongF" class="classId"
                                   val="aa" <c:if test="${gradeClassExtention.id==gradeClassExtention.selectedId}">checked</c:if>><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==2}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="gaoZhonF" class="classId"
                                   val="aa"><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                        <c:if test="${section.id==gradeClassExtention.xd && gradeClassExtention.nj==2}">
                            <input type="checkbox" value="${gradeClassExtention.id}" name="gaoZhongF" class="classId"
                                   val="aa"><span>${gradeClassExtention.nj}年级${gradeClassExtention.name}</span>
                        </c:if>
                    </c:forEach>
                </c:if>
            </c:forEach>
        </ul>
    </form>
</div>
</body>
<script>
    $(".xiaoXue").on("click", function () {
        if (this.checked == true) {
            $("input[name='xiaoXueF']").prop("checked", true);
        } else {
            $("input[name='xiaoXueF']").prop("checked", false);
        }
    });


    $(".chuZhong").on("click", function () {
        if (this.checked == true) {
            $("input[name='chuZhongF']").prop("checked", true);
        } else {
            $("input[name='chuZhongF']").prop("checked", false);
        }
    });

    $(".gaoZhong").on("click", function () {
        if (this.checked == true) {
            $("input[name='gaoZhongF']").prop("checked", true);
        } else {
            $("input[name='gaoZhongF']").prop("checked", false);
        }
    });


    var strPath = window.document.location.pathname;
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    function doSubmit() {
        debugger;
        var classIds = "";
        $("input[val=aa]").each(function (i) {
            if ($(this).attr("checked")) {
                if (i==0){
                    classIds = $(this).val();
                }else {
                    classIds += "," + $(this).val();
                }

            }
        });
        $.post(postPath + "/teach/task/course/class/add", {
            courseId: '${courseId}',
            classIds: classIds
        }, function (data) {

        });
        return true;
    }
</script>
</html>
