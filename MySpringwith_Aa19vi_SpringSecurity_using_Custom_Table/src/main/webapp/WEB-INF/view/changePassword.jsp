<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
 
<%@ page isELIgnored= "false" %>

    <!-- jstl uri -->
   <%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>



 <!--responsive purpose  -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<br>
<div style="text-align: center">


<h1> Reset Password </h1>

<c:if test="${param.invalidPassword != null }">
		<span style="color:red">Failed to change the password, Old Password doesn't matches</span><br><br>
	</c:if>

<c:if test="${msg != null }">
		<span style="color:red">${msg}</span><br><br>
	</c:if>

<form:form action="updatePassword" method="POST" modelAttribute="passwordDetails">

<label>Old Password: </label>
<input type="text" name="oldPassword"  required/>
<br><br>
<label>New Password: </label>
<input type= "text" name="newPassword" required/>
<br><br>
<label>Confirm Password: </label>
<input type="text" name="confirmPassword" required/>
<br><br>
<input type="submit" value="Update Password">



</form:form>

<br>
<a href="#" onclick="goBack()" /><button>Cancel</button></a>
<script>
function goBack() {
	  window.history.back()
	}</script>
<br><br>
		<a href="${pageContext.request.contextPath}/"><button>Back To Home</button></a>



</div>

</body>
</html>