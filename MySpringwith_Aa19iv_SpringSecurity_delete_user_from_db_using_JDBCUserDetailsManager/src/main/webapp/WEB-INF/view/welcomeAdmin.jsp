<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
 
<%@ page isELIgnored= "false" %>

    <!-- jstl uri -->
   <%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>


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
<div style="text-align:center">
<h2>Welcome To Admin Panel</h2>

<c:if test="${username != null }">
		<h4>Hii ${username}</h4>
		<h4>Roles Assigned: ${userauthorities}</h4>
	</c:if>
	
	<br>
<form:form action="logout" method="POST">
<input type="submit" value="Logout">

&ensp;
<a href ="deleteUser?username=${username}"><button>Delete Account</button></a>

</form:form>




<br>
		<a href="${pageContext.request.contextPath}/"><button>Back To Home</button></a>


</div>
</body>
</html>