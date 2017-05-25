$(document).ready(function(){
	$("#province").change(function(){
		var provinceId = $(this).val().trim();
		ajax(provinceId, "city");
	});
	
	$("#city").change(function(){
		var cityId = $(this).val().trim();
		ajax(cityId, "county");
	});
	
	jQuery.validator.addMethod("endTimeValidate", function(value,element) {
		var beginDate=$("#beginDate").val().trim();
		var date = new Date();
		var now = date.getFullYear();
		now = now + "-" + ((date.getMonth()+1)<10?"0"+(date.getMonth()+1):(date.getMonth()+1));
		now = now + "-" + ((date.getDate()<10?"0"+date.getDate():date.getDate()));
		return this.optional(element) || ((value.trim() > beginDate) && (value.trim() > now)); 
	}, $.validator.format(" 开通终止时间要大于起始时间且大于当前时间 "));
	jQuery.validator.addMethod("beginTimeValidate", function(value,element) {
		var endDate=$("#endDate").val().trim();
		return this.optional(element) || value.trim() < endDate; 
	}, $.validator.format("开通起始时间要小于终止时间 "));
});

function ajax(parentId, id){
	
	$.ajax({
        type:"GET",
        url:"getAreasByParentId?parentId="+parentId,
        datatype: "json",
        success:function(data){
        	var message = eval('('+data+')');
        	var now= $("#"+id);
        	now.find("option").remove();
        	now.append("<option value=''>-----请选择-----</option>")
        	for(var i = 0; i < message.length; i++){
        		now.append("<option value="+message[i].id+">"+message[i].name+"</option>")
        	}
        	
        },
        
        //调用出错执行的函数
        error: function(){
            //请求出错处理
        	alert("请求失败！");
        }        
     });
	
	
}