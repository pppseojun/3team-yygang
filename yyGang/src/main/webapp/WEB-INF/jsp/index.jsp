<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>yyGang</title>
</head>
<body>
<h2>yyGang</h2>
<h3>영양제 관련 내용 삽입 예정</h3>

<form action="save" method="post">
    아이디   : <input type="text" name="userId" /> <br>
    비밀번호 :  <input type="text" name="passWord" />
    <button type="submit">로그인</button>
</form>

<p>
<h4>회원 가입</h4>
<a href="<%= request.getContextPath()%>/views/login/join.jsp"> 회원가입 </a>
</p>

<p>
<h4>마이 페이지</h4>
<a href="<%= request.getContextPath()%>/views/login/myPage.jsp"> 마이 페이지 </a>
</p>


</body>
</html>