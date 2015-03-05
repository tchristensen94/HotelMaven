<%-- Document : calc Created on : Jan 27, 2015, 2:00:30 PM Author : tim --%>
<%@page import="hotel.web.model.HotelModel;"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hotel Management</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <style>
            tr {
                cursor: pointer;
            }
        </style>
    </head>

    <body>
        <header class="header text-center">
            <h1>Hotel Management</h1>
        </header>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-8">
                    <form class="form-inline" id="form-hotel-search" name="form-hotel" method="POST" action="${pageContext.request.contextPath}/HotelControl">
                        <div class="form-group">
                            <label for="searchByCity">Search by: City</label>
                            <input type="text" class="form-control" name="searchByCity" id="searchByCity" value="${citySearch}">
                        </div>
                        <div class="form-group">
                            <label for="searchByState">State</label>
                            <input type="text" class="form-control" name="searchByState" id="searchByState" value="${stateSearch}">
                        </div>
                        <button type="submit" class="btn btn-default">Search</button>
                    </form>
                        <br />
                    <table class="table table-striped hotelList">
                        <tr>
                            <th>Name</th>
                            <th>Address</th>
                            <th>City</th>
                            <th>State</th>
                            <th>ZIP Code</th>
                            <th>Notes</th>
                        </tr>
                        <c:forEach var="hotel" items="${hotels}" >
                            <tr>
                                <td>
                                    <a href="HotelControl?id=${hotel.id}">${hotel.name}</a>
                                </td>
                                <td>
                                    ${hotel.address}
                                </td>
                                <td>
                                    ${hotel.city}
                                </td>
                                <td>
                                    ${hotel.state}
                                </td>
                                <td>
                                    ${hotel.zip}
                                </td>
                                <td>
                                    ${hotel.notes}
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <div class="col-md-4">
                    <form id="form-hotel" name="form-hotel" method="POST" action="${pageContext.request.contextPath}/HotelControl">
                        <input type="hidden" name="hotelID" id="hotelID" value="${hotel.id}" />
                        <input type="hidden" name="searchByCity" id="searchByCity" value="${citySearch}" />
                        <input type="hidden" name="searchByState" id="searchByState" value="${stateSearch}" />
                        <input type="hidden" name="option" id="option" value="create" />
                        <div class="form-group">
                            <label for="hotelName">Hotel Name</label>
                            <input type="text" id="hotelName" name="hotelName" placeholder="Grand Hotel" class="form-control" value="${hotel.name}">

                        </div>
                        <div class="form-group">
                            <label for="hotelAddress">Hotel Address</label>
                            <input type="text" id="hotelAddress" name="hotelAddress" placeholder="555 Somewhere St." class="form-control" value="${hotel.address}">

                        </div>
                        <div class="form-group">
                            <label for="hotelName">Hotel City</label>
                            <input type="text" id="hotelCity" name="hotelCity" placeholder="Milwaukee" class="form-control" value="${hotel.city}">

                        </div>
                        <div class="form-group">
                            <label for="hotelZip">Hotel State</label>
                            <input type="text" id="hotelState" name="hotelState" placeholder="Wisconsin" class="form-control" value="${hotel.state}">

                        </div>
                        <div class="form-group">
                            <label for="hotelZip">Hotel ZIP Code</label>
                            <input type="text" id="hotelZip" name="hotelZip" placeholder="53226" class="form-control" value="${hotel.zip}">

                        </div>
                        <div class="form-group">
                            <label for="hotelNotes">Notes</label>
                            <textarea id="hotelNotes" name="hotelNotes" placeholder="Some notes..." class="form-control">${hotel.notes}</textarea>

                        </div>
                        <button type="submit" id="createButton" class="btn btn-primary">Create</button>
                        <button type="submit" id="updateButton" class="btn btn-warning">Update</button>
                        <button type="submit" id="deleteButton" class="btn btn-danger">Delete</button>

                    </form>
                </div>
            </div>
        </div>
        <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/hotel/hotel.js"></script>

        <script type="text/javascript">
            $("table").on("click", "tr", function (e) {
                if ($(e.target).is("a,input")) // anything else you don't want to trigger the click
                    return;

                location.href = $(this).find("a").attr("href");
            });
        </script>

    </body>

</html>
