<%@page import="com.example.mvcdb.LoginBean"%>
<form action="HelloServlet" method="post">
    Name:<label>
    <input type="text" name="name">
</label><br>
    Password:<label>
    <input type="password" name="password">
</label><br>
    <input type="submit" value="login">
</form>