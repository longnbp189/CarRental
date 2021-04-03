<%-- 
    Document   : rentHistory
    Created on : Mar 4, 2021, 2:30:59 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
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
                    <h1>HISTORY DETAIL</h1>
                    <h3><font color="green">Hello, ${user.fullname}</font></h3> <br/>
                    <div class="row mx-auto" style="width: 50%">
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
                    <!--remove remote form-->
                    <form action="remove-rental" method="POST" id="deleteForm">
                    </form>
                    <div class="row">
                        <!--search history form-->
                        <form action="rent-history" method="POST" class="col-lg-9 mx-auto">
                            <div class="row">
                                <!--                                <div class="col-md-5">
                                                                    <p><strong>Name:</strong></p>
                                                                    <input type="text" class="form-control" placeholder="Search" value="${param.txtSearch}" name="txtSearch">
                                                                    <br/>
                                                                </div>-->
                                <div class="row col-md-7 mx-auto">
                                    <div class="col-md-7">
                                        <p><strong>Rent date: </strong></p>
                                        <input type="date" name="txtDate" value="${param.txtDate}" class="form-control"/>
                                    </div>
                                    <input type="hidden" name="searchName" value="${requestScope.SEARCH_NAME}"/>
                                    <input type="hidden" name="quantity" value="${requestScope.QUANTITY}"/>
                                    <input type="hidden" name="srartDate" value="${requestScope.START_DATE}"/>
                                    <input type="hidden" name="endDate" value="${requestScope.END_DATE}"/>
                                    <input type="hidden" name="type" value="${requestScope.TYPE}"/>
                                    <input type="hidden" name="page" value="${requestScope.PAGE}"/>
                                    <div class="col-md-3">
                                        <input type="submit" value="Search History" name="btAction" class="btn btn1" style="margin-top: 42px"/>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <!--end search history form-->
                    </div>
                </div>
            </section>
            <section class="py-5 container">
                <div class="row">
                    <div class="mx-auto">
                        <c:set var="list" value="${requestScope.RENTAL_LIST}"/>
                        <c:if test="${not empty list}">
                            <!--table history-->
                            <table>
                                <thead>
                                    <tr>
                                        <th>No.</th>
                                        <th>Rental date</th>
                                        <th>Discount</th>
                                        <th>Total</th>
                                        <th>Remove</th>
                                        <th>Show detail</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="dto" items="${list}" varStatus="counter">
                                        <tr>
                                            <td>${counter.count}</td>
                                            <td>${dto.rentalDate}</td>
                                            <td>${dto.discountPercent}%</td>
                                            <td>${dto.total}</td>
                                            <td><input type="checkbox" name="chkRemove" value="${dto.id}" form="deleteForm"/></td>
                                            <td>
                                                <c:url var="rentalDetail" value="rental-detail">
                                                    <c:param name="txtRentalId" value="${dto.id}"/>
                                                    <c:param name="searchDate" value="${param.txtDate}"/>
                                                    <c:param name="searchName" value="${param.txtCarName}"/>
                                                    <c:param name="quantity" value="${requestScope.QUANTITY}"/>
                                                    <c:param name="srartDate" value="${requestScope.START_DATE}"/>
                                                    <c:param name="endDate" value="${requestScope.END_DATE}"/>
                                                    <c:param name="type" value="${requestScope.SELECTED_TYPE}"/>
                                                    <c:param name="page" value="${requestScope.PAGE}"/>
                                                </c:url>
                                                <a href="${rentalDetail}">Detail</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <input type="button" value="Remove" name="btAction" style="margin-top: 50px"
                                   class="btn btn1" data-toggle="modal" data-target="#exampleModalLong"/>
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
                                            <input type="hidden" name="txtDate" value="${param.txtDate}" form="deleteForm"/>
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
                        </c:if>
                        <c:if test="${empty list}">
                            <h3>Can not found</h3>
                        </c:if>
                    </div>
                </div>
            </section>
        </div>
    </body>
</html>