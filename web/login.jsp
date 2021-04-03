<%-- 
    Document   : login
    Created on : Feb 27, 2021, 4:37:07 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <style>
            <%@include file="css/mystyle.css"%>
            body{
                background: grey;
            }
            form{
                width:  350px;
                border: 3px solid #f1f1f1;
                padding: 20px;
                left: 38%;
                top: 15%;
                position: absolute;
                background: white;
                height: 475px;
                border-radius: 10px;
            }
            #submit-btn{
                cursor: not-allowed;
            }
        </style>
    </head>
    <body class="text-center">
        <form action="login" method="POST">
            <h1>Login</h1>
            <p><strong> Username: </strong></p>
            <input type="text" name="txtUsername" value="" placeholder="Username" required autofocus class="form-control"/><br/>
            <p><strong> Password: </strong></p>
            <input type="password" name="txtPassword" value="" placeholder="Password" required class="form-control"/><br/>
            <div class="g-recaptcha" 
                 data-sitekey="6Lcbd2oaAAAAAPCcH8cpuG4mJJmiBFDaRTt89XcI"
                 data-callback="recapcha_callback"></div>
            <input type="submit" value="Login" name="btAction" class="btn btn1" id="submit-btn" disabled=""/>
            <br/>
            <a href="sign-up">Sign Up</a> <br/>
            <c:if test="${not empty sessionScope.LOGIN_ERROR}">
                <font color="red">
                ${sessionScope.LOGIN_ERROR}
                </font>
            </c:if>
        </form>


        <script>
            function recapcha_callback() {
                var submitBtn = document.querySelector('#submit-btn');
                submitBtn.removeAttribute('disabled');
                submitBtn.style.cursor = 'pointer';
    
}
        </script>
    </body>
</html>
