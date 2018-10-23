<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>jpa</title>
	<script type="text/javascript" src="<%=path %>/resources/js/jquery-3.2.0.js"></script>
	<script type="text/javascript">
		// 测试框架
		//var restType = "jersey";
		//var restType = "restlet";
		//var restType = "resteasy";
		//var restType = "restCxf";
		//var restType = "restMvc";
		var restType = "testSpringboot/jpa";
		
		// 初始化加载
		$(document).ready(function(){
			list();
			var ajaxOption = null;
			$("#saveButton").click(function(){
				save();
			});
		});
		// GET /users
		function list(){
			$.ajax({
				url:"../"+ restType +"/users/page?page="+$("#page").val() + "&size="+$("#size").val(),
				dataType:"json",
				success:function(data){
					$("#listBody").empty();
					$("#page").val(data.pageable.pageNumber);
					$("#size").val(data.pageable.pageSize);
					$("#totalElements").html(data.totalElements);
					$("#totalPages").html(data.totalPages);
					$("#offset").html(data.pageable.offset+1);
					$("#offsetEnd").html(data.pageable.offset+data.numberOfElements);
					//$("#pagePrevious").css("display","none");
					if(data.pageable.pageNumber>0){
						//$("#pagePrevious").css("display","block");
						$("#pagePrevious").attr("href", "javascript:listPage("+(data.pageable.pageNumber-1)+")");
					}else{
						$("#pagePrevious").attr("href", "javascript:listPage(0)");
					}
					$("#pageCurrent").html(data.pageable.pageNumber+1);
					//$("#pageNext").css("display","none");
					if(data.pageable.pageNumber<(data.totalPages-1)){
						//$("#pageNext").css("display","block");
						$("#pageNext").attr("href", "javascript:listPage("+(data.pageable.pageNumber+1)+")");
					}else{
						$("#pageNext").attr("href", "javascript:listPage("+(data.totalPages-1)+")");
					}
					$("#pageLast").attr("href", "javascript:listPage("+(data.totalPages-1)+")");
					$.each(data.content, function(i, item){
						var userTr = "<tr><form name='updateForm_"+item.id+"' action='user/update' method='POST'>"
							+"<td><input type='hidden' name='id' id='id_"+item.id+"' value='"+item.id+"'/>"+item.id+"</td>"
							+"<td><input type='text' name='name' id='name_"+item.id+"' value='"+item.name+"' /></td>"
							+"<td><a href='javascript:void(0);' onclick='get("+item.id+");'>get</a>&nbsp;"
							+"<a href='javascript:void(0);' onclick='remove("+item.id+");'>remove</a>&nbsp;"
							+"<input type='submit' value='update' id='updateButton_"+item.id+"' onclick='update("+item.id+");' /></td></tr>";
						$("#listBody").append(userTr);
					});
				}
			});
		}
		// POST /users
		function save(){
			$("#saveButton").attr("disabled", true);
			$.ajax({
				url:"../"+ restType +"/users",
				type:"POST",
				data:"name="+$("#name").val(),
				dataType:"json",
				complete:function(){
					$("#saveButton").attr("disabled", false);
				},
				success:function(data){
					alert(data.status);
					$("#name").val('');
					list();
				}
			});
		}
		// GET /users/{id}
		function get(id){
			$.ajax({
				url:"../"+ restType +"/user/"+id,
				dataType:"json",
				success:function(data){
					var userInfo = "id:"+data.id+"\nname:"+data.name;
					alert(userInfo);
				}
			});
		}
		// DELETE /users/{id}
		function remove(id){
			$.ajax({
				url:"../"+ restType +"/user/"+id,
				type:"DELETE",
				dataType:"json",
				success:function(data){
					alert(data.status);
					list();
				}
			});
		}
		// PUT /users/{id}
		function update(id){
			$("#updateButton_"+id).attr("disabled", true);
			$.ajax({
				url:"../"+ restType +"/user/"+id,
				type:"PUT",
				data:"&name="+$("#name_"+id).val(),
				dataType:"json",
				complete:function(data){
					$("#updateButton_"+id).attr("disabled", false);
				},
				success:function(data){
					alert(data.status);
					list();
				}
			});
		}
		
	    function listPage(page) {
	    	$("#page").val(page);
	    	//alert('listpage:'+page)
	        list();
	    };
	</script>
</head>
<body>
	<input type="hidden" name="page" id="page" value="0"/> 
	<input type="hidden" name="size" id="size" value=""/> 
	<br/>
	save
	<form name="saveForm" action="jpa/users" method="POST">
		name:<input type="text" name="name" id="name" /> 
		<br /> 
		<input type="button" value="save" id="saveButton" />
	</form>
	<br/>
	list
	<div class="col-sm-4">
		显示  <nobr id="offset">0</nobr> - <nobr id="offsetEnd">0</nobr> 共 <nobr id="totalElements">0</nobr> 条 共 <nobr id="totalPages">0</nobr> 页
		<a id="pageFirst" href="javascript:listPage(0)" tabindex="0">首页</a>
		<a id="pagePrevious" href="javascript:listPage(0)" tabindex="0">&lt;</a>
		<a id="pageCurrent" href="javascript:listPage(0)" tabindex="0">0</a>
		<a id="pageNext" href="javascript:listPage(0)" tabindex="0">&gt;</a>
		<a id="pageLast" href="javascript:listPage(0)" tabindex="0">尾页</a>
	</div>
	<table border="1">
		<thead>
			<tr>
				<td>id</td>
				<td>name</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody id="listBody">
			<!-- 无用，通过js增加行 -->
			<c:forEach items="${list }" var="user">
				<tr>
					<form name="updateForm_${user.id}" action="user/update" method="POST">
					<td><input type="hidden" name="id" id="id" value="${user.id}" />${user.id}</td>
					<td><input type="text" name="name" id="name" value="${user.name}" /></td>
					<td><a href="javascript:void(0);" onclick="get(${user.id});">get</a>&nbsp;<a href="javascript:void(0);" onclick="remove(${user.id});">remove</a>&nbsp;<input type="submit" value="update" id="updateButton_${user.id}" /></td>
					</form>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>