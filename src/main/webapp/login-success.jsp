<%@page import="com.example.mvcdb.LoginBean"%>

<p>You are successfully logged in! <b>${requestScope.user}</b></p>



<script type="text/javascript">
    setTimeout(function () {
        window.location.href = "http://localhost:8080/MVCDB_war_exploded/";
    }, 10000); // Redirect after 10 seconds
</script>

