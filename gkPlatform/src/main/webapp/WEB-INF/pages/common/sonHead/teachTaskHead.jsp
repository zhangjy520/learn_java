<%@ page contentType="text/html;charset=UTF-8" %>
<nav>
    <div class="container">
        <div class="roll-manage-title">教务管理</div>
        <div class="roll-manage-menu">
            <ul>

                <li id="base">
                    <a class="down-a">基础管理</a>
                    <ul class="second-menu">
                        <li><a href="${ctx}/teach/task/room/index">教室管理</a></li>
                        <%--<li><a href="">课节设置</a></li>--%>
                        <li><a href="${ctx}/teach/task/course/index">课程安排</a></li>
                        <li><a href="${ctx}/teach/task/master/index">班主任安排</a></li>
                        <li><a href="${ctx}/teach/task/course/teacher/index">任课教师安排</a></li>
                        <li><a href="${ctx}/teach/task/cycle/index">教学周期</a></li>
                    </ul>
                </li>

                <li id="all">
                    <a class="down-a">综合管理</a>
                    <ul class="second-menu">
                        <li><a href="${ctx}/teach/task/ref/class/room/index">班级教室安排</a></li>
                        <li><a href="${ctx}/teach/task/course/hour">科目课时安排</a></li>
                    </ul>
                </li>

                <li id="role"><a href="${ctx}/teach/task/rolefp/index">角色分配</a></li>

            </ul>
        </div>
    </div>
</nav>