<%-- 
    Document   : rentDetail
    Created on : Mar 5, 2021, 10:32:46 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title>Detail</title>
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
            <div class="container">
                <h1>RENTAL DETAIL</h1>
                <c:set var="user" value="${sessionScope.USER}"/>
                <h3><font color="green">Hello, ${user.fullname}</font></h3> <br/>
                <div class="row mx-auto" style="width: 50%">
                    <div class="col-md-4">
                        <c:url var="back" value="rent-history">
                            <c:param value="${requestScope.SEARCH_DATE}" name="txtDate"/>
                            <c:param name="searchName" value="${requestScope.SEARCH_NAME}"/>
                            <c:param name="quantity" value="${requestScope.QUANTITY}"/>
                            <c:param name="srartDate" value="${requestScope.START_DATE}"/>
                            <c:param name="endDate" value="${requestScope.END_DATE}"/>
                            <c:param name="type" value="${requestScope.TYPE}"/>
                            <c:param name="page" value="${requestScope.PAGE}"/>
                        </c:url>
                        <h5><a href="${back}">Back</a></h5>
                    </div>
                    <div class="col-md-4">
                        <c:url var="searchPage" value="search">
                            <c:param name="txtCarName" value="${requestScope.SEARCH_NAME}"/>
                            <c:param name="txtQuantity" value="${requestScope.QUANTITY}"/>
                            <c:param name="txtStartDate" value="${requestScope.START_DATE}"/>
                            <c:param name="txtEndDate" value="${requestScope.END_DATE}"/>
                            <c:param name="cbType" value="${requestScope.TYPE}"/>
                            <c:param name="btnPage" value="${requestScope.PAGE}"/>
                        </c:url>
                        <h5><a href="${searchPage}">Search car</a></h5>
                    </div>
                    <div class="col-md-4">
                        <h5><a href="logout">Logout</a></h5>
                    </div>
                </div>
            </div>
        </section>

        <div class="row">
            <div class="mx-auto row">
                <div class="col-md-10" style="margin-left: 75px">
                    <c:set var="list" value="${requestScope.DETAIL_LIST}"/>

                    <!--table history-->
                    <table style="margin-top: 50px">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Car name</th>
                                <th>Quantity</th>
                                <th>Start date</th>
                                <th>End date</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${list}" varStatus="counter">
                                <tr>
                                    <td>${counter.count}</td>
                                    <td>${dto.carName}</td>
                                    <td>${dto.quantity}</td>
                                    <td>${dto.startDate}</td>
                                    <td>${dto.endDate}</td>
                                    <td>${dto.price}</td>

                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <strong>Rating</strong> <br/>
                                        ${dto.rating} start
                                    </td>
                                    <td colspan="3">
                                        <strong>Feedback</strong> <br/>
                                        ${dto.feedbackContent}
                                    </td>
                                    <td>
                                        <input type="button" value="Send feedback" name="btAction" style="margin-top: 15px"
                                               class="btn btn1" data-toggle="modal" data-target="#exampleModalLong"/>

                                        <!-- Modal -->
                                        <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                                            <div class="modal-dialog" role="document">
                                                <div class="modal-content">
                                                    <form action="get-feedback" method="POST">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <input type="hidden" value="${requestScope.SEARCH_DATE}" name="searchDate"/>
                                                        <input type="hidden" name="searchName" value="${requestScope.SEARCH_NAME}"/>
                                                        <input type="hidden" name="quantity" value="${requestScope.QUANTITY}"/>
                                                        <input type="hidden" name="srartDate" value="${requestScope.START_DATE}"/>
                                                        <input type="hidden" name="endDate" value="${requestScope.END_DATE}"/>
                                                        <input type="hidden" name="type" value="${requestScope.TYPE}"/>
                                                        <input type="hidden" name="page" value="${requestScope.PAGE}"/>
                                                        <div class="modal-body" style="text-align: left">
                                                            <p><strong>Rating</strong></p>
                                                            <c:forEach begin="1" end="10" varStatus="counter">
                                                                <input type="radio" name="chkRating" value="${counter.count}" style="margin-left: 15px"/>
                                                                ${counter.count} start
                                                                <c:if test="${counter.count == 5}">
                                                                    <br/>
                                                                </c:if>
                                                            </c:forEach>
                                                            <p><strong>Feedback</strong></p>
                                                            <textarea class="form-control" name="txtFeedback"></textarea>
                                                            <input type="hidden" name="rentDetailId" value="${dto.id}" />
                                                            <input type="hidden" name="txtRentalId" value="${requestScope.RENT_ID}" />
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                                            <input type="submit" value="Feedback" name="btAction" class="btn btn1"/>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>

</body>
</html>
