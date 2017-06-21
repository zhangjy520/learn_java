<%@ include file="../../common/common.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="login">
    <meta name="author" content="lexi">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no">
    <style>


        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
            font-family: "Microsoft YaHei", Arial, STXihei, '华文细黑', 'Microsoft YaHei', SimSun, '宋体', Heiti, '黑体', sans-serif;
        }

        span {
            display: inline-block;
        }

        .popup-main {
            background: #fff;
            padding: 35px 0px 10px 25px;
            font-size: 13px !important;
            color: #525252 !important;
        }

        table {
            /*width: 100%;*/
        }

        table td {
            font-size: 13px;
            text-align: right;
            padding: 10px 0;
        }

        table td span {
            width: 88px;
            text-align: right;
        }

        table td input[type="text"], table td select {
            width: 150px;
            height: 28px;
            line-height: 28px;
            margin-left: 12px;
            padding-left: 10px;
            outline: none;
            border: 1px solid #dadada;
            border-radius: 2px;
            color: #333;
        }

        table td input[type="radio"] {
            margin-right: 8px;
            display: none;
        }

        label {
            padding-left: 20px;
            cursor: pointer;
            background: url(${ctxStaticNew}/images/nocheck.png) no-repeat left;
        }

        label.checked {
            background: url(${ctxStaticNew}/images/check.png) no-repeat left;
        }

        textarea {
            width: 415px;
            vertical-align: top;
            margin-left: 12px;
            height: 100px;
            outline: none;
            border: 1px solid #dadada;
            resize: none;
            color: #333;
            padding: 0 10px;
            tab-index: 2em;
        }

        table td span:before {
            content: '*';
            color: red;
            vertical-align: middle;
            margin-right: 3px;
        }

        .bodys {
            padding: 50px 0 0 50px;
        }

        .datainp {
            width: 200px;
            height: 30px;
            border: 1px #A5D2EC solid;
        }

        .datep {
            margin-bottom: 40px;
            line-height: 24px;
            margin-right: 15px;
        }

        .wicon {
            background-image: url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABkAAAAQCAYAAADj5tSrAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEgAACxIB0t1+/AAAABZ0RVh0Q3JlYXRpb24gVGltZQAwNi8xNS8xNGnF/oAAAAAcdEVYdFNvZnR3YXJlAEFkb2JlIEZpcmV3b3JrcyBDUzVxteM2AAAAoElEQVQ4jWPceOnNfwYqAz9dYRQ+E7UtwAaGjyUsDAyYYUgJ2HT5LXZLcEmSCnA6duOlN///////H0bDALl8dPH/////Z8FuNW6Qtvw2nL3lyjsGBgYGhlmRqnj1kGwJuqHIlhJlCXq8EOITEsdqCXLEbbr8FisfFkTo+vBZRFZwERNEFFkCiw90nxJtCalxQmzegltCzVyP1RJq5HZ8AABuNZr0628DMwAAAABJRU5ErkJggg==");
            background-repeat: no-repeat;
            background-position: right center;
        }
    </style>

    <script type="text/javascript" src="${ctxStaticNew}/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/fenBan.js"></script>
    <!-- jQuery -->

    <%--<script src="${ctxStatic}/js/jquery.js"></script>--%>
    <script type="text/javascript" src="${ctxStaticNew}/js/layer/layer.js"></script>

    <script src="${ctxStaticNew}/js/alertPopShow.js"></script>

    <script type="text/javascript" src="${ctxStaticNew}/js/jquery.jedate.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctxStaticNew}/css/jedate.css">
</head>

<body>
<div class="container">
    <form  action="${ctx}/teach/task/node/edit" id="inputForm" method="post">
        <div class="popup-main ">
            <input type="hidden" class="id" name="courseNode.id" value="${courseNodeView.courseNode.id}">
            <table>
                <tbody>
                <ul>
                    <li class="datep">
                        <span>节次</span>
                        <input type="text" value="${courseNodeView.courseNode.node}" name="courseNode.node"><i>早自习时节次为0</i>
                    </li>
                    <li class="datep">
                        <span>开始时间</span>
                        <input type="text"  value="${courseNodeView.startTime}" name="startTime">
                    </li>

                    <li class="datep">
                        <span>结束时间</span>
                        <input type="text"  value="${courseNodeView.endTime}" name="endTime">
                    </li>
                    <li class="datep">
                        <span>上午/下午/晚上</span>
                        <input type="text" value="${courseNodeView.courseNode.morningAfternoon}" name="courseNode.morningAfternoon">
                    </li>

                </ul>
                </tbody>
            </table>
        </div>
    </form>
</div>
</body>
<script>
    function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        $("#inputForm").submit();
        return true;
    }
</script>
</html>
