<%-- 
    Document   : validatePage
    Created on : Feb 27, 2021, 6:14:14 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
        <title>Validate Page</title>
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
                height: 275px;
                border-radius: 10px;
            }
            #submit-btn{
                cursor: not-allowed;
            }
        </style>
    </head>
    <body class="text-center">
        <!--form validate-->
        <form action="validate">
            <h1>Validation</h1>
            <p><strong>Input code: </strong></p>
            <input type="text" name="txtCode" value=" " class="form-control"/>
            <input type="submit" value="Submit" class="btn btn1"/>
            <c:url var="sendAgain" value="validate">
                <c:param name="txtCode" value=""/>
            </c:url>
            <a href="${sendAgain}" class="btn btn1">Send code again</a>
            <c:if test="${not empty requestScope.INVALID}">
                <font color="red">${requestScope.INVALID}</font>
            </c:if>
        </form>
        
    </body>
</html>
