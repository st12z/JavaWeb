<%-- 
    Document   : header
    Created on : Sep 1, 2024, 9:29:31 AM
    Author     : T
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .info-customer{
        display:flex;
        justify-content: center;
        align-items: center;
    }
</style>
<div class="header">
    <div class="container">
        <div class="row">
            <div class="col-xl-3 col-lg-3 col-sm-3 col-12">
                <div class="inner-logo">
                    <a href="home"><img src="client/images/icon-logo.png" alt="logo"/></a>
                </div>
            </div>
            <div class="col-xl-4 col-lg-4 col-sm-4 col-12">
                <div class="search">
                    <form id="form-search" action="search">
                        <input type="text" placeholder="Hôm nay bạn cần tìm gì" name="keyword" value="${requestScope.keyword}"/>
                        <button type="submit">
                            <i class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>

                </div>
            </div>


            <c:if test="${requestScope.Customer==null}" >
                <div class="col-1">
                    <a href="login">Login</a>
                </div> 
            </c:if>
            <c:if test="${requestScope.Customer!=null}" >
                <div class="col-1">
                    <a href="logout">Logout</a>
                </div> 
            </c:if>
            <c:if test="${requestScope.Customer==null}" >
                <div class="col-1">
                    <a href="register">Register</a>
                </div> 
            </c:if>           

            <div class="col-xl-1 col-lg-1 col-sm-1 col-12">
                <div class="cart">
                    <a href="cart">
                        <i class="fa-solid fa-cart-shopping"></i>
                        <span>${requestScope.items.size()}</span>
                    </a>
                </div>
            </div>
            <div class="col-xl-1 col-lg-1 col-sm-1 col-12">
                <div class="order">
                    <a href="order">
                        <i class="fa-solid fa-truck-fast"></i>
                        <span>(${requestScope.countItems})</span>
                    </a>
                </div>
            </div>
            <div class="col-xl-2 col-lg-2 col-sm-2 col-12">
                <c:if test="${requestScope.Customer!=null}" >
                    <a href="info-customer">
                        <div class="info-customer">
                            <i class="fa-regular fa-user"></i>
                            <span>${requestScope.Customer.customerName}</span>
                        </div>
                    </a>
                </c:if> 
            </div>


        </div>
    </div>
</div>
