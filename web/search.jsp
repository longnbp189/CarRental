<%-- 
    Document   : search
    Created on : Feb 27, 2021, 4:28:25 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="<c:url value="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />" rel="stylesheet">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" crossorigin="anonymous"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            <%@include file="css/mystyle.css"%>
        </style>
        <title>Home Page</title>
    </head>
    <body class="text-center">
        <div class="cover-container p-3 mx-auto">
            <section class="jumbotron" style="background: #e8e9f2">
                <div class="container">
                    <h1>Home Page</h1>
                    <c:set var="user" value="${sessionScope.USER}"/>
                    <c:set var="cList" value="${requestScope.CATEGORY_LIST}"/>
                    <c:if test="${not empty user}">
                        <h3><font color="green">Welcome, ${user.fullname}</font></h3> <br/>
                        </c:if>
                    <div class="row mx-auto" style="width: 75%">
                        <c:if test="${not empty user && not user.role}">
                            <div class="col-md-4">
                                <c:url var="viewHis" value="rent-history">
                                    <c:param name="searchName" value="${param.txtCarName}"/>
                                    <c:param name="quantity" value="${requestScope.QUANTITY}"/>
                                    <c:param name="srartDate" value="${requestScope.START}"/>
                                    <c:param name="endDate" value="${requestScope.END}"/>
                                    <c:param name="type" value="${requestScope.SELECTED_TYPE}"/>
                                    <c:param name="page" value="${param.btnPage}"/>
                                </c:url>
                                <h5><a href="${viewHis}">View History</a></h5>
                            </div>
                        </c:if>
                        <c:if test="${not empty user}">
                            <div class="col-md-4 mx-auto">
                                <c:url var="logoutUrl" value="logout">
                                    <c:param name="btAction" value="Logout"/>
                                </c:url>
                                <h5><a href="${logoutUrl}">Logout</a></h5>
                            </div>
                        </c:if>
                        <c:if test="${not empty user && not user.role}">
                            <div class="col-md-4">
                                <c:url var="showCart" value="show-cart">
                                    <c:param name="searchName" value="${param.txtCarName}"/>
                                    <c:param name="quantity" value="${requestScope.QUANTITY}"/>
                                    <c:param name="srartDate" value="${requestScope.START}"/>
                                    <c:param name="endDate" value="${requestScope.END}"/>
                                    <c:param name="type" value="${requestScope.SELECTED_TYPE}"/>
                                    <c:param name="page" value="${param.btnPage}"/>
                                </c:url>
                                <h5><a href="${showCart}">View your cart</a></h5>
                            </div>
                        </c:if>

                        <c:if test="${empty user}">
                            <div class="col-md-3 mx-auto">
                                <h5><a href="try">Login</a></h5>
                            </div>
                        </c:if>
                    </div>

                    <!--search form-->
                    <form action="search">
                        <div class="row mx-auto" style="width: 75%">
                            <div class="col-md-8">
                                <p><strong>Car name: </strong></p>
                                <input type="text" name="txtCarName" value="${param.txtCarName}" class="form-control"/>
                            </div>
                            <br/>
                            <div class="col-md-4">
                                <p><strong>Quantity: </strong></p>
                                <input type="text" name="txtQuantity" value="${requestScope.QUANTITY}" class="form-control" min="1"
                                       onkeydown="javascript: return event.keyCode === 8 ||
                                                       event.keyCode === 46 ? true : !isNaN(Number(event.key))"/>
                                ${requestScope.QUANTITY_ERR}
                            </div>
                            <br/>
                        </div>
                        <br/>
                        <div class="row mx-auto" style="width: 75%">
                            <div class="col-md-4">
                                <p><strong>Start date: </strong></p>
                                <input type="datetime-local" name="txtStartDate" value="${requestScope.START}" class="form-control"/>
                            </div>
                            <br/>
                            <div class="col-md-4">
                                <p><strong>End date: </strong></p>
                                <input type="datetime-local" name="txtEndDate" value="${requestScope.END}" class="form-control"/>
                            </div>
                            <br/>
                            <div class="col-md-4">
                                <p><strong>Type: </strong></p>
                                <c:set var="selectedType" value="${requestScope.SELECTED_TYPE}"/>
                                <c:set var="typeList" value="${requestScope.TYPE_LIST}"/>

                                <select name="cbType" class="form-control">
                                    <option value="" <c:if test="${selectedType == ''}">
                                            selected="true"</c:if>>
                                            All type
                                        </option>
                                    <c:forEach var="tDTO" items="${typeList}">
                                        <option value="${tDTO.id}"<c:if test="${selectedType == tDTO.id}">
                                                selected="true"</c:if>>
                                            ${tDTO.typeName}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <br/>
                        </div>
                        <input type="submit" value="Search" name="btAction" class="btn btn1" style="margin-top: 30px"/>
                    </form>
                    <!--end search form-->
                </div>
            </section>

            <c:set var="list" value="${requestScope.LIST_CAR}"/>
            <c:if test="${not empty list}">
                <div style="padding-top: 30px">
                    <!--show car list-->
                    <c:forEach var="dto" items="${list}" >
                        <div style="margin : 20px 0px 20px 50px" class="row">
                            <div class="col-md-5">
                                <div class="col-md-12"><img src="${dto.picture}" style="width: 100%"/></div>
                            </div>
                            <div class="col-md-7">
                                <div class="row">
                                    <div class="col-md-4"><p><strong>Car name: </strong></p> </div>${dto.carName}
                                </div>
                                <div class="row">
                                    <div class="col-md-4"><p><strong>Car color: </strong></p> </div>${dto.color}
                                </div>
                                <div class="row">
                                    <div class="col-md-4"><p><strong>Category name: </strong></p> </div>${dto.categoryName}
                                </div>
                                <div class="row">
                                    <div class="col-md-4"><p><strong>Type name: </strong></p> </div>${dto.typeName}
                                </div>
                                <div class="row">
                                    <div class="col-md-4"><p><strong>Year: </strong></p> </div>${dto.year}
                                </div>
                                <div class="row">
                                    <div class="col-md-4"><p><strong>Price per hours: </strong></p> </div>${dto.price}$
                                </div>
                                <div class="row">
                                    <div class="col-md-4"><p><strong>Quantity: </strong></p> </div>${dto.quantity}
                                </div>
                                <br/>

                                <c:if test="${not user.role || empty user}">

                                    <!--add to cart form-->
                                    <form action="add-to-cart" method="POST">
                                        <div class="row" style="margin-left: -11px">
                                            <input type="hidden" name="txtCarRentID" value="${dto.id}" />

                                            <input type="hidden" value="${param.txtCarName}" name="txtCarName" />
                                            <input type="hidden" value="${requestScope.START}" name="txtStartDate" />
                                            <input type="hidden" value="${requestScope.END}" name="txtEndDate" />
                                            <input type="hidden" value="${requestScope.QUANTITY}" name="txtQuantity" />
                                            <input type="hidden" value="${param.cbType}" name="cbType" />
                                            <input type="hidden" name="btnPage" value="${requestScope.PAGE_NUM}" />

                                            <div class="col-md-5">
                                                <p><strong>Start date: </strong></p>
                                                <input type="datetime-local" name="txtStartRentDate" value="${requestScope.START}" class="form-control"/>
                                            </div>
                                            <div class="col-md-5">
                                                <p><strong>End date: </strong></p>
                                                <input type="datetime-local" name="txtEndRentDate" value="${requestScope.END}" class="form-control"/>
                                                <br/>
                                            </div> 
                                            <br/>
                                            <c:if test="${not empty user && not user.role}">
                                                <div class="col-md-1" style="margin-top: 18px">
                                                    <input type="submit" value="Add" name="addToCart" class="btn" style="margin-top: 20px"/>
                                                </div>
                                            </c:if>
                                            <c:if test="${empty user}">
                                                <div class="col-md-1" style="margin-top: 18px">
                                                    <a href="try" class="btn">Add</a>
                                                </div>
                                            </c:if>
                                            <c:if test="${requestScope.EXIST_ERROR == dto.id}">
                                                <font color="red">
                                                Car is existed
                                                </font> 
                                            </c:if>
                                            <c:if test="${requestScope.DATE_ERROR == dto.id}">
                                                <font color="red">
                                                Start date can not > end date 
                                                </font> 
                                            </c:if>
                                            <c:if test="${requestScope.CREATE_QUANTITY_ERROR == dto.id}">
                                                <font color="red">
                                                Out of stock
                                                </font> 
                                            </c:if>
                                        </div> 
                                    </form>
                                </c:if>
                            </div>
                        </div>
                                <hr/>
                    </c:forEach>
                    <!--end show cart list-->
                    <div class="col-md-12">
                        <!--search by page form-->
                        <form action="search" method="POST">
                            <c:set var="countPage" value="${requestScope.PAGE_COUNT}"/>
                            <div>
                                <input type="hidden" value="${param.txtCarName}" name="txtCarName" />
                                <input type="hidden" value="${requestScope.START}" name="txtStartDate" />
                                <input type="hidden" value="${requestScope.END}" name="txtEndDate" />
                                <input type="hidden" value="${requestScope.QUANTITY}" name="txtQuantity" />
                                <input type="hidden" value="${param.cbType}" name="cbType" />
                                <c:forEach var="num" begin="${1}" end="${countPage}">
                                    <input type="submit" value="${num}" class="btn btn2" name="btnPage"/>
                                </c:forEach>
                            </div>
                        </form>    
                        <!--end search by page form-->
                    </div>  
                </div>
            </c:if>
            <c:if test="${empty list}">
                <c:if test="${not empty requestScope.DATE_ERR}">
                    <h5 style="padding: 50px">${requestScope.DATE_ERR}</h5>
                </c:if>
                <c:if test="${empty requestScope.DATE_ERR}">
                    <h5 style="padding: 50px">Not Found</h5>
                </c:if>
            </c:if>


        </div>

    </body>
</html>
