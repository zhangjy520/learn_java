<%@ page contentType="text/html;charset=UTF-8" %>
<nav>
    <div class="container">
        <div class="roll-manage-title">教务管理</div>
        <div class="roll-manage-menu">
            <ul>
                <shiro:hasPermission name="teach:task:base">
                    <li id="base">
                        <a class="down-a">基础管理</a>
                        <ul class="second-menu">
                            <li id="baseMenu1"><a href="${ctx}/teach/task/cycle/index">教学周期</a></li>
                            <li id="baseMenu2"><a href="${ctx}/teach/task/room/index">教室管理</a></li>
                            <li id="baseMenu3"><a href="${ctx}/teach/task/course/index">课程安排</a></li>
                            <li id="baseMenu4"><a href="${ctx}/teach/task/master/index">班主任安排</a></li>
                            <li id="baseMenu5"><a href="${ctx}/teach/task/course/teacher/index">任课教师安排</a></li>
                            <li id="baseMenu6"><a href="${ctx}/teach/task/daily/hour">班级日常课时</a></li>
                            <li id="baseMenu7"><a href="${ctx}/teach/task/node/new">课节设置</a></li>
                        </ul>
                    </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="teach:task:zonghe">
                    <li id="all">
                        <a class="down-a">综合管理</a>
                        <ul class="second-menu">
                            <li><a href="${ctx}/teach/task/ref/class/room/index">班级教室安排</a></li>
                            <li><a href="${ctx}/teach/task/course/hour">科目课时安排</a></li>
                        </ul>
                    </li>
                </shiro:hasPermission>

                <shiro:hasPermission name="teach:task:role">
                    <li id="role"><a href="${ctx}/teach/task/rolefp/index">角色分配</a></li>
                </shiro:hasPermission>
            </ul>
        </div>
    </div>
</nav>