<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>Login</h1>
  	${SPRING_SECURITY_LAST_EXCEPTION.message}
   <form action="/Products" method='GET'>
   
      <table>
         <tr>
            <td>Enter User Name:</td>
            <td><input type='text' name='username' value=''></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
         </tr>
      </table>
  </form>
</body>
</html>