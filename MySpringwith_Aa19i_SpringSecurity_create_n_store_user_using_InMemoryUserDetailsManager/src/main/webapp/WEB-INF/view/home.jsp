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
<title>Home</title>
</head>
<body>
<br>
<div style="text-align: center">


<h1> Welcome To Learning Spring Security </h1>




<c:if test="${username != null }">
		<h4>Hii ${username}</h4>
		<h4>Roles Assigned: ${userauthorities}</h4>
	</c:if>


<sec:authorize access="hasAuthority('ROLE_ADMIN')">
<a href="admin"><button> Admin Panel</button></a> 
</sec:authorize>

&ensp;

<sec:authorize access="hasAuthority('ROLE_NORMAL')">
<a href="normal"><button> Normal Panel</button></a>
</sec:authorize>


<br><br>
<div style="margin-right: 112px">
		<a href="myLogin"><button>Login</button></a>
<br><br>
		<a href="signup"><button>Signup</button></a>
</div>	  


</div>
</body>
</html>