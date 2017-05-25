<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/pages/include/taglib.jsp"%>
<html>
<head>
	<title>分班步骤</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$("input[name='condition']").click(function(){
			var count = $("input:checkbox[name='condition']:checked").length;
			if(count>0){
				$("#startFb").attr("disabled", false);
				$("#startFb").removeClass("notCz");
			}else{
				$("#startFb").attr("disabled", true);
				$("#startFb").addClass("notCz");
			}
		});
	});
	
	function startDevide(){
		var dc = "";
		$("input[name='condition']").each(function(){
			if($(this).attr("checked") == "checked"){
				dc += $(this).val();
				dc += ","
			}
		});
		$("#devideCondition").val(dc.substring(0,dc.length-1));

		$("#devideForm").submit();
	}
	</script>
	</head>
	<body class="thisBody">
		<sys:message content="${message}"/>
		<div style="height:78px;width:1200px;margin:0px auto">
			<div style="z-index:9999;position:fixed;margin:0px 0px">
				<div class="app_store_headerMenu fenBan-header">
					<div class="appStoreLeft fenBanLeft">
						<img src="${ctxStatic }/fb/image/fenban/fenban.png" />&nbsp;<a style="line-height: 49px;font-size:24px;color: #1AB394;">分班系统</a>
					</div>
				</div>
			</div>
		</div>

		<div class="mainContain fenbanMain fenbanStep">
			<div class="forBeauty" style="background:#ffffff;">
				<div class="mainTable">
					
					<div class="noData">
						<div class="taskInfo">
							<label>任务名称：${zsTask.rwmc }</label>		
							<label>创建时间：<fmt:formatDate value="${zsTask.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></label>		
							<label>创建人：${fns:getUser().name }</label>		
						</div>
						<div class="chuangjian taskStep">
							
							<div class="jinDuWithLabel active">
								<div class="specialJinDu">
									1
								</div>
								<p class="jinDuLabel">导入数据</p>
							</div><div id="yiJian" class="jinDuWithLabel active1">
								<div class="specialJinDu">
									2
								</div>
								<p class="jinDuLabel">一键分班</p>
							</div><div id="fbResult" class="jinDuWithLabel noActive">
								<div class="specialJinDu">
									3
								</div>
								<p class="jinDuLabel">分班结果</p>
							</div>
						</div>
						<!--第二步 一键分班-->
						<form:form  action="${ctx}/zsfb/zsStudent/devide" method="post" id="devideForm" onsubmit="loading('正在分班，请稍等...');">
						<input type="hidden" value="${zsTask.id }" name="taskId">
						<input type="hidden" name="devideCondition" id="devideCondition">
						<div class="notice taskNotice step2">
							<span class="step-1 step-2">
								<p>
									提示：系统已将姓名同音自动分在不同班级
								</p>	
							</span>	
							<span class="step-1 step-2">
								<p>
									分班个数：&nbsp;&nbsp;
									<select name="bjCount">
										<c:forEach begin="1" end="30" varStatus="classCount">
											<option value="${classCount.index }">${classCount.index }</option>
										</c:forEach>
									</select>											
								</p>	
							</span>
							<span class="step-1 step-2">	
								<p>
									分班准则：&nbsp;&nbsp;&nbsp;
									<div class="checkboxEs">
										<c:choose>
											<c:when test="${fns:getUser().school.xxlx == '1'}">
												<input type="checkbox" name="condition" value="sex" checked/><tap></tap><label>男女比例</label><tap></tap>
												<input type="checkbox" name="condition" value="jtdz" checked/><tap></tap><label>家庭地址</label><tap></tap>
												<input type="checkbox" name="condition" value="sqzn" checked/><tap></tap><label>随迁子女</label><doubleTap></doubleTap>
												<input type="checkbox" name="condition" value="deformity" checked/><tap></tap><label>随班就读</label><doubleTap></doubleTap>
												<input type="checkbox" name="condition" value="twins" checked/><tap></tap><label>双胞胎</label><br><br>
												<input type="checkbox" name="condition" value="cm" checked/><tap></tap><label>重名</label><doubleTap></doubleTap>
												<input type="checkbox" name="condition" value="armys" checked/><tap></tap><label>军人子女</label><tap></tap>
												<input type="checkbox" name="condition" value="tchild" checked/><tap></tap><label>教师子女</label><doubleTap></doubleTap>
												<input type="checkbox" name="condition" value="wjchild" checked/><tap></tap><label>外籍子女</label><doubleTap></doubleTap>
											</c:when>
											<c:otherwise>
												<input type="checkbox" name="condition" value="school" checked/><tap></tap><label>学校拆分</label><tap></tap>
												<input type="checkbox" name="condition" value="sex" checked/><tap></tap><label>男女比例</label><tap></tap>
												<input type="checkbox" name="condition" value="deformity" checked/><tap></tap><label>随班就读</label><doubleTap></doubleTap>
												<input type="checkbox" name="condition" value="twins" checked/><tap></tap><label>双胞胎</label><doubleTap></doubleTap><br><br>
												<input type="checkbox" name="condition" value="score" checked/><tap></tap><label>综合素质</label><tap></tap>
												<input type="checkbox" name="condition" value="cm" checked/><tap></tap><label>重名</label><doubleTap></doubleTap>
												<input type="checkbox" name="condition" value="armys" checked/><tap></tap><label>军人子女</label><doubleTap></doubleTap>
												<input type="checkbox" name="condition" value="tchild" checked/><tap></tap><label>教师子女</label><tap></tap>
											</c:otherwise>
										</c:choose>
										<a style="color: #1AB394;">(至少选择一项)</a>
									</div>
								</p>	
							</span>	
							<div class="czButtons" style="margin-top: 250px;">
								<input class="canCz" onclick="window.location.href='${ctx}/zsfb/zsTask/first?id=${zsTask.id }'" type="button" value="上一步"/>
								<input id="startFb" class="canCz" type="button" onclick="javascript:startDevide()" value="开始分班"/>
							</div>
						</div>
						</form:form>
						<!-- 分班准则 -->
					</div>
				</div>
			</div>
		</div>
	</body>
</html>