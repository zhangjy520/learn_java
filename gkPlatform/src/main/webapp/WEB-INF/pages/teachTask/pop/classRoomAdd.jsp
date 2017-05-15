<%@ include file="../../common/common.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="login">
<meta name="author" content="lexi">

    <style>

        <%--span.error{color:#C00; padding:0 6px;display: inline}--%>
        <%--body{height:auto !important;font-size:0.75em;color:#666;font-family: "Roboto", "Noto Sans CJK SC", "Nato Sans CJK TC", "Nato Sans CJK JP", "Nato Sans CJK KR", -apple-system, ".SFNSText-Regular", "Helvetica Neue", "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "WenQuanYi Zen Hei", Arial, sans-serif;}--%>
        <%--.container{width:800px;padding:5px 8px;margin:0 auto}--%>
        <%--input{margin:0;padding:0;border:1px solid #999;height:23px;padding:0 3px;border-radius:3px;width: 190px}--%>
        <%--.container ul{list-style:none;width:50%;margin:0;padding:0;float:left;}--%>
        <%--.container form>ul>li{margin:15px 0;}--%>
        <%--.fSpan{display: inline-block;width:27%;text-align:right;}--%>
        <%--.container ul li span b{color:#f00;margin-right:8px;}--%>
        <%--select{width: 190px;}--%>
        <%--.img-list-icon{--%>
            <%--background: url(${ctxStaticNew}/images/default_tou.png) no-repeat 10px 12px !important;--%>
        <%--}--%>

        *{
            padding:0;
            margin:0;
            box-sizing: border-box;
            font-family: "Microsoft YaHei",Arial, STXihei, '华文细黑', 'Microsoft YaHei', SimSun, '宋体', Heiti, '黑体', sans-serif;
        }
        span{display:inline-block;}
        .popup-main{
            background: #fff;
            padding: 35px 0px 10px 25px;
            font-size: 13px !important;
            color: #525252 !important;
        }
        table{
            /*width: 100%;*/
        }
        table td{
            font-size: 13px;
            text-align: right;
            padding: 10px 0;
        }
        table td span{
            width: 88px;
            text-align: right;
        }
        table td input[type="text"],table td select{
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
        table td input[type="radio"]{
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

        textarea{
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
        table td span:before{
            content: '*';
            color: red;
            vertical-align: middle;
            margin-right: 3px;
        }
    </style>
    <script type="text/javascript" src="${ctxStatic}/js/fenBan.js"></script>
    <!-- jQuery -->
    <script src="${ctxStatic}/js/jquery.js"></script>
    <script type="text/javascript" src="${ctxStatic}/js/layer/layer.js"></script>
<script>

    $(function(){
        $("input[name='schoolType']").val($("select[name='schoolTypeSelect']").val());
        $("input[name='schoolTypeName']").val($("select[name='schoolTypeSelect'] option:selected").text());

        $("input[name='roomType']").val($("select[name='roomTypeSelect']").val());
        $("input[name='roomTypeName']").val($("select[name='roomTypeSelect'] option:selected").text());

        $("select[name='schoolTypeSelect']").change(function () {
            $("input[name='schoolType']").val($("select[name='schoolTypeSelect']").val());
            $("input[name='schoolTypeName']").val($("select[name='schoolTypeSelect'] option:selected").text());
        })

        $("select[name='roomTypeSelect']").change(function () {
            $("input[name='roomType']").val($("select[name='roomTypeSelect']").val());
            $("input[name='roomTypeName']").val($("select[name='roomTypeSelect'] option:selected").text());
        })
    })
    function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
        $("#inputForm").submit();
        return true;
    }
</script>
<script>
    $(function() {
        $('label').click(function(){
            var radioId = $(this).attr('name');
            $('label').removeAttr('class') && $(this).attr('class', 'checked');
            $('input[type="radio"]').removeAttr('checked') && $('#' + radioId).attr('checked', 'checked');
        });
    });
</script>
</head>

<body>
<div class="container">
    <form method="post" action="${ctx}/teach/task/room/update" id="inputForm">
        <input type="hidden" name="roomId" value="${room.roomId}">
        <%--<ul>--%>
            <%--<li>--%>
                <%--<span class="fSpan">--%>
                    <%--所在校区：--%>
                <%--</span>--%>
                <%--<select name="schoolTypeSelect">--%>
                    <%--<c:forEach items="${schoolTypeList}" var="schoolType">--%>
                        <%--<option value="${schoolType.id}">${schoolType.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
                <%--<input name="schoolType" type="hidden"/>--%>
                <%--<input name="schoolTypeName" type="hidden"/>--%>
            <%--</li>--%>

            <%--<li>--%>
                <%--<span class="fSpan">教室类型：</span>--%>
                <%--<select name="roomTypeSelect">--%>
                    <%--<c:forEach items="${roomTypeList}" var="roomType">--%>
                        <%--<option value="${roomType.id}">${roomType.name}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
                <%--<input name="roomType" type="hidden"/>--%>
                <%--<input name="roomTypeName" type="hidden"/>--%>
            <%--</li>--%>

            <%--<li>--%>
                <%--<span class="fSpan">楼层：</span>--%>
                <%--<input type="text" name="floor" value="${room.floor}">--%>
            <%--</li>--%>

            <%--<li>--%>
                <%--<span class="fSpan">教室号：</span>--%>
                <%--<input type="text" name="roomNum" value="${room.roomNum}">--%>
            <%--</li>--%>

            <%--<li>--%>
                <%--<span class="fSpan">容纳人数：</span>--%>
                <%--<input type="text" name="count" value="${room.count}">--%>
            <%--</li>--%>

            <%--<li>--%>
                <%--<span class="fSpan">有效座位数：</span>--%>
                <%--<input type="text" name="availableSeat" value="${room.availableSeat}">--%>
            <%--</li>--%>

            <%--<li>--%>
                <%--<span class="fSpan">考试座位数：</span>--%>
                <%--<input type="text" name="examSeat" value="${room.examSeat}">--%>
            <%--</li>--%>

            <%--<li>--%>
                <%--<span class="fSpan">是否用于选课：</span><br>--%>
                <%--<input type="radio" name="courseSelect" value="1">是<br>--%>
                <%--<input type="radio" name="courseSelect" value="2">否--%>
            <%--</li>--%>

            <%--<li>--%>
                <%--<span class="fSpan">备注：</span>--%>
                <%--<textarea name="remarks">${room.remarks}</textarea>--%>
            <%--</li>--%>
        <%--</ul>--%>

        <div class="popup-main">
            <table>
                <tbody>
                <tr>
                    <td>
                        <span>所在校区</span>
                        <select name="schoolTypeSelect">
                            <c:forEach items="${schoolTypeList}" var="schoolType">
                            <option value="${schoolType.id}">${schoolType.name}</option>
                            </c:forEach>
                        </select>
                        <input name="schoolType" type="hidden"/>
                        <input name="schoolTypeName" type="hidden"/>
                    </td>
                    <td>
                        <span>教室类型</span>
                        <select name="roomTypeSelect" id="">
                            <c:forEach items="${roomTypeList}" var="roomType">
                            <option value="${roomType.id}">${roomType.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span>所在楼</span>
                        <input type="text">
                    </td>
                    <td>
                        <span>容纳人数</span>
                        <input type="text" name="count" value="${room.count}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <span>楼层</span>
                        <input type="text" name="floor" value="${room.floor}">
                    </td>
                    <td>
                        <span>有效座位数</span>
                        <input type="text" name="availableSeat" value="${room.availableSeat}">
                    </td>
                </tr>
                <tr>
                    <td>
                        <span>教室</span>
                        <input type="text" name="roomNum" value="${room.roomNum}">
                    </td>
                    <td>
                        <span>考试座位数</span>
                        <input type="text" name="examSeat" value="${room.examSeat}">
                    </td>
                </tr>
                <tr>
                    <td style="text-align: left; padding: 20px 0 20px 0;">
                        <span>是否用于选课</span>
                        <label name="yes" for="yes" style="margin-left: 12px;"></label>
                        <input type="radio" name="courseSelect" value="1" id="yes">是
                        <label name="no" for="no" style="margin-left: 50px;"></label>
                        <input type="radio" name="courseSelect" value="2" id="no">否
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <span style="width: auto">备注</span>
                        <textarea name="remarks">${room.remarks}</textarea>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>
</body>
</html>
