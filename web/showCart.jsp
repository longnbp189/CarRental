<%-- 
    Document   : showCart
    Created on : Feb 28, 2021, 4:30:53 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show cart</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <style>
            <%@include file="css/mystyle.css"%>
            th {
                background-color: #425d8a;
                color: white;
            }
            tr:hover {
                background-color: #e8e9f2;
            }
        </style>

    </head>
    <body class="text-center">
        <div class="cover-container p-3 mx-auto">
            <section class="jumbotron" style="background: #e8e9f2">
                <c:set var="user" value="${sessionScope.USER}"/>
                <div class="container">
                    <h1>CART DETAIL </h1>
                    <h3><font color="green">Hello, ${user.fullname}</font></h3> <br/>
                    <div class="row mx-auto" style="width: 30%">
                        <div class="col-md-4">
                            <c:url var="searchPage" value="search">
                                <c:param name="txtCarName" value="${requestScope.SEARCH_NAME}"/>
                                <c:param name="txtQuantity" value="${requestScope.QUANTITY}"/>
                                <c:param name="txtStartDate" value="${requestScope.START_DATE}"/>
                                <c:param name="txtEndDate" value="${requestScope.END_DATE}"/>
                                <c:param name="cbType" value="${requestScope.TYPE}"/>
                                <c:param name="btnPage" value="${requestScope.PAGE}"/>
                            </c:url>
                            <h5><a href="${searchPage}">Back</a></h5>
                        </div>
                        <div class="col-md-4">
                        </div>
                        <div class="col-md-4">
                            <h5><a href="logout">Logout</a></h5>
                        </div>
                    </div>
                    <br/>
                    <!--Get discount form-->
                    <form action="check-discount" style="width: 50%" class="mx-auto">
                        <div class="row">
                            <div class="col-md-3">
                                <p><strong>Discount</strong></p> 
                            </div>
                            <div class="col-md-6">
                                <input type="text" name="txtDiscount" value="" class="form-control"/>
                            </div>
                            <div class="col-md-3">
                                <input type="submit" value="Check" class="btn btn1" style="margin-top: -3px"/>
                                <input type="hidden" name="searchName" value="${requestScope.SEARCH_NAME}"/>
                                <input type="hidden" name="quantity" value="${requestScope.QUANTITY}"/>
                                <input type="hidden" name="srartDate" value="${requestScope.START_DATE}"/>
                                <input type="hidden" name="endDate" value="${requestScope.END_DATE}"/>
                                <input type="hidden" name="type" value="${requestScope.TYPE}"/>
                                <input type="hidden" name="page" value="${requestScope.PAGE}"/>
                            </div>
                            <div class="col-md-12" style="padding: 10px">
                                <c:if test="${not empty DISCOUNT_ERR}">
                                    <font color="red">
                                    ${requestScope.DISCOUNT_ERR}
                                    </font> 
                                </c:if>
                                <c:if test="${empty DISCOUNT_ERR}">
                                    <br/>
                                </c:if>
                            </div>
                        </div>
                    </form>
                </div>
            </section>
            <div class="py-5">
                <div class="row">
                    <div class="mx-auto">
                        <c:set var="cart" value="${sessionScope.CART}"/>
                        <c:if test="${empty cart}">
                            <h2>Cart Empty</h2>
                        </c:if>
                        <c:if test="${not empty cart}">
                            <!--remove item remote form-->
                            <form action="remove-items" method="POST" id="deleteForm">
                            </form>
                            <table>
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Car Name</th>
                                        <th>Start date</th>
                                        <th>End date</th>
                                        <th>Quantity</th>
                                        <th>Price</th>
                                        <th>Update</th>
                                        <th>Remove</th>
                                    </tr>
                                </thead>
                                <c:set var="list" value="${cart.carList}"/>
                                <tbody>
                                    <!--Show cart--> 
                                    <c:forEach var="car" items="${list}" varStatus="counter">
                                        <!--update item form--> 
                                    <form action="update-cart" method="POST">
                                        <c:set var="dto" value="${car.value}"/>
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${dto.carName}</td>
                                            <td>
                                                <input type="datetime-local" name="txtChangeStartDate" value="${dto.startDate}" class="form-control"/>
                                            </td>
                                            <td>
                                                <input type="datetime-local" name="txtChangeEndDate" value="${dto.endDate}" class="form-control"/>
                                            </td>
                                            <td style="width: 50px">
                                                <input type="hidden" name="txtCarId" value="${car.key}" />
                                                <input type="text"  name="txtChangeQuantity" value="${dto.quantity}" class="form-control" min="1"
                                                       onkeydown="javascript: return event.keyCode === 8 ||
                                                                       event.keyCode === 46 ? true : !isNaN(Number(event.key))"/>
                                            </td>
                                            <td>${dto.price}</td>
                                            <td>
                                                <input type="hidden" name="searchName" value="${requestScope.SEARCH_NAME}"/>
                                                <input type="hidden" name="quantity" value="${requestScope.QUANTITY}"/>
                                                <input type="hidden" name="srartDate" value="${requestScope.START_DATE}"/>
                                                <input type="hidden" name="endDate" value="${requestScope.END_DATE}"/>
                                                <input type="hidden" name="type" value="${requestScope.TYPE}"/>
                                                <input type="hidden" name="page" value="${requestScope.PAGE}"/>
                                                <input type="submit" value="Update" name="btAction" class="btn btn1" />
                                                <br/>
                                                <c:if test="${requestScope.OUTOFSTOCK_ERROR == car.key}">
                                                    <font color="red">
                                                    Out of stock
                                                    </font> 
                                                </c:if>
                                                <c:if test="${requestScope.UPDATE_ERROR == car.key}">
                                                    <font color="red">
                                                    Start date can not > end date
                                                    </font> 
                                                </c:if>
                                                <c:if test="${requestScope.QUANTITY_ERROR == car.key}">
                                                    <font color="red">
                                                    Quantity can not = 0
                                                    </font> 
                                                </c:if>
                                            </td>
                                    </form>
                                    <!--end update item form-->
                                    <td><input type="checkbox" name="chkRemove" value="${car.key}" form="deleteForm"/></td>
                                    </tr>

                                </c:forEach>
                                <!--end show cart-->
                                <tr>
                                    <td colspan="5"><h2>Discount</h2></td>
                                    <c:if test="${cart.discount != 0}">
                                        <td colspan="1">-${(cart.discount)}%</td>
                                    </c:if>
                                    <c:if test="${cart.discount == 0}">
                                        <td colspan="1">0</td>
                                    </c:if>
                                    <td colspan="2"></td>
                                </tr>
                                <tr>
                                    <td colspan="5"><h2>Total</h2></td>
                                    <td colspan="1">${cart.total}</td>
                                    <td></td>
                                    <td colspan="1">
                                        <input type="button" value="Remove" name="btAction"
                                               class="btn btn1" data-toggle="modal" data-target="#exampleModalLong"/>
                                    </td>

                                </tr>

                                <!-- Modal -->
                                <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <input type="hidden" name="searchName" value="${requestScope.SEARCH_NAME}" form="deleteForm"/>
                                                <input type="hidden" name="quantity" value="${requestScope.QUANTITY}" form="deleteForm"/>
                                                <input type="hidden" name="srartDate" value="${requestScope.START_DATE}" form="deleteForm"/>
                                                <input type="hidden" name="endDate" value="${requestScope.END_DATE}" form="deleteForm"/>
                                                <input type="hidden" name="type" value="${requestScope.TYPE}" form="deleteForm"/>
                                                <input type="hidden" name="page" value="${requestScope.PAGE}" form="deleteForm"/>
                                                Do you want to remove?
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                <input type="submit" value="Remove" name="btAction" class="btn btn1" form="deleteForm"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                </tbody>
                            </table>

                            <form action="checkout">
                                <input type="submit" value="Checkout" name="btAction" class="btn btn1"
                                       <c:if test="${not empty requestScope.ERROR}"> disabled</c:if>/>
                                </form>
                            </div>
                    </c:if>
                </div>
            </div>
        </div>

    </body>
</html>
