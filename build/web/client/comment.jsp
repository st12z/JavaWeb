<%-- 
    Document   : comment
    Created on : Sep 21, 2024, 8:12:09 PM
    Author     : T
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row d-flex justify-content-center">
    <div class="col-md-8 col-lg-6">
        <div class="card shadow-0 border" style="background-color: #f0f2f5;">
            <div class="card-body p-4">
                <form class="form-group" action="comment" method="post">
                    <div data-mdb-input-init class="form-outline mb-4">
                        <input type="text" name="comment" class="form-control" placeholder="Type comment..." />
                    </div>
                    <c:forEach items="${requestScope.listComment}" var="i">
                        <div class="card mb-4">
                            <div class="card-body">
                                <img src="${i.customer.avatar}" alt="avatar" width="25"
                                     height="25" />
                                <span class="small mb-0 ms-2">${i.customer.customerName}</span>
                                <p>${i.content}</p>
                                <p>${i.timesince}</p>

                                <c:if test="${i.customerID==Customer.id}">
                                    <a href="delete?commentID=${i.commentID}">Delete</a>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </form>

            </div>
        </div>
    </div>
</div>

