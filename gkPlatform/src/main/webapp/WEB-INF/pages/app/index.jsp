<%@ include file="../common/headerXf.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>应用商店</title>
    <style>
        #app-index main.container{border-top:none;}
        .layui-layer.layui-layer-iframe.layer-anim {
            top: 6% !important;
        }
        .row{
            margin:0;
        }
    </style>
</head>
<body>
<nav>
    <div class="container">
        <div class="app-store-nav">
            <div><span>应用商店</span></div>
        </div>
        <div>
            <ul class="app-store-menu">
                <li>
                    <a onclick="menuShift(this)" data="app-index" id="appIndexPage">应用首页</a>
                </li>
                <li>
                    <a onclick="menuShift(this)" data="app-myApp" id="allAppsPage">全部应用</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div id="app-index" style="display: none">
    <div class="app-store-banner row">
        <img class="img-responsive" src="${ctxStaticNew}/images/appStore-banner.png" alt=""/>
    </div>
    <main class="container">
        <div class="app-store-title">
            <span>推荐应用</span>
        </div>
        <div class="row app-store-app">
            <ul>
                <c:forEach items="${appAllList}" var="appAll" varStatus="status">
                    <li class="activity-center" onclick="openDialogView('应用详情','${ctx}/app/showappdetails?id=${appAll.id}','70%','90%');">
                        <c:if test="${not empty appAll.iconUrl}">
                            <c:if test="${appAll.sfczxmz==0}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctx}/file/pic/show?picPath=${appAll.iconUrl}" /></c:if>
                            <c:if test="${appAll.sfczxmz==1}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctxStatic}/image/appDetails/${appAll.iconUrl}" /></c:if>
                            <c:if test="${appAll.sfczxmz==2}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${appAll.iconUrl}" /></c:if>
                        </c:if>
                        <p>${appAll.name}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </main>
</div>

<div id="app-myApp" class="container"  style="display: none">
    <div class="app-myApp-content">
        <div class="app-store-search">
            <input type="hidden" id="searchHidden" value="${name}">
            <button class="summitButton" id="searchName" ></button>
            <input class="searchInput" type="text" style="padding-left: 5px;" placeholder="搜索应用名称" value="" name="searchName"/>
        </div>
        <div class="app-store-type">
            <span>应用类别</span>
            <ul>
                <li>
                    <a href="#" class="active" flag="0">全部</a>
                </li>
                <li>
                    <a href="#" flag="1">教务教学</a>
                </li>
                <li>
                    <a href="#" flag="2">互动空间</a>
                </li>
            </ul>
        </div>
        <div class="app-store-target">
            <span>应用对象</span>
            <ul>
                <li>
                    <a href="#" class="active" flag="0">全部</a>
                </li>
                <li>
                    <a href="#" flag="1" >老师</a>
                </li>
                <li>
                    <a href="#" flag="2" >学生</a>
                </li>
                <li>
                    <a href="#" flag="3" >家长</a>
                </li>
            </ul>
        </div>
        <%--<div class="app-store-area">--%>
            <%--<span>应用地区</span>--%>
            <%--<ul>--%>
                <%--<li>--%>
                    <%--<a href="#" class="active" flag="0">全部</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="#" flag="1" >本校</a>--%>
                <%--</li>--%>
                <%--<li>--%>
                    <%--<a href="#" flag="2" >非本校</a>--%>
                <%--</li>--%>
            <%--</ul>--%>
        <%--</div>--%>
    </div>


    <div class="app-store-apps">
        <p id="hiddentips" style="display: none">搜索到${size}个“${name}”相关结果</p>
        <p id="hidden1" style="display: none">该分类下目前无应用</p>
        <ul>

            <c:forEach items="${appList}" var="app" varStatus="status">
                <li class="alertAppDetil" onclick="openDialogView('应用详情','${ctx}/app/showappdetails?id=${app.id}','70%','90%');">
                    <c:if test="${not empty app.iconUrl}">
                        <c:if test="${app.sfczxmz==0}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctx}/file/pic/show?picPath=${app.iconUrl}" /></c:if>
                        <c:if test="${app.sfczxmz==1}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${ctxStatic}/image/appDetails/${app.iconUrl}" /></c:if>
                        <c:if test="${app.sfczxmz==2}"><img class="img-responsive" style="width: 68px;height: 68px;" src="${app.iconUrl}" /></c:if>
                    </c:if>
                    <p>${app.name}</p>
                </li>
            </c:forEach>

        </ul>
    </div>

</div>

</div>

<script>
    $(function(){
        //首次进入，切换到系统应用
        if(${empty category && empty name}){
            menuShift($("#appIndexPage"));
        }

        //按应用类别和应用对象搜索时，搜索对象回显在页面上
        if(${category != ""}){
            menuShift($("#allAppsPage"));
            $(".app-store-type a[flag = 0]").removeClass('active');
            $(".app-store-target a[flag = 0]").removeClass('active');
//            $(".app-store-area a[flag = 0]").removeClass('active');
            $(".app-store-type a[flag = ${category}]").addClass('active');
            $(".app-store-target a[flag = ${targetUser}]").addClass('active');
            <%--$(".app-store-area a[flag = ${area}]").addClass('active');--%>
            if(${size==0 && name== ""} ){
                $("#hidden1").show();
            }
        }

        //搜索框关键字回显
        $("input[name='searchName']").val($("#searchHidden").val());
        //提示搜索结果
        if('${name}' != ""){
            menuShift($("#allAppsPage"));
            $("#hiddentips").show();
        }

        // 按应用对象和应用类别搜索时，先显示高亮，获取参数后后台查找
        $('.app-store-target a,.app-store-type a<%--, .app-store-area a--%>').click(function(){
            $(this).addClass('active');
            $(this).parent().siblings().children().removeClass('active');
            //按应用对象和应用类别搜索
            var searchin=$("ul li a.active");
            for(var i=0;i<searchin.length;i++){
                if(i>0){
                    //alert($(searchin[i]).attr("flag"));
                    searchin[i-1] = $(searchin[i]).attr("flag")
                }

            }
            window.location.href="${ctx}/app/appstore/index?category="+searchin[0]+"&targetUser="+searchin[1];//+"&area="+searchin[2];
        })

        /*查询搜索*/
        $(".summitButton").click(function () {
            var name=$("input[name='searchName']").val() ;
            if(name != ""){
                window.location.href="${ctx}/app/appstore/index?name="+encodeURI(encodeURI(name));
            }
        });



    });
    //切换推荐应用和全部应用
    function menuShift(obj){
        $(obj).addClass('active');
        $(obj).parent().siblings().children().removeClass('active');
        var href=$(obj).attr('data');
        var div=$('div[id='+href+']');
//        console.log(div[0].id);
        if(href==div[0].id){
            $('div[id^=app]').hide();
            $('div[id='+href+']').show();
        }
    }
</script>
</body>
</html>