<%-- 
    Document   : header
    Created on : Sep 1, 2024, 9:29:31 AM
    Author     : T
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="header">
    <div class="container">
        <div class="row align-items-center">
            <div class="col-xl-2 col-lg-2 col-sm-2 col-12">
                <div class="inner-logo">
                    <a href="/Shop/home"><img style="width:150px" src="/Shop/client/images/logo-shop.png" alt="logo"/></a>
                </div>
            </div>

            <div class="col-xl-5 col-lg-5 col-sm-5 col-12">
                <div class="search">
                    <form id="form-search" action="home">
                        <input class="form-control" type="text" placeholder="Hôm nay bạn cần tìm gì" name="keyword" value="${requestScope.keyword}"/>
                        <button button-search type="submit">
                            <i  class="fa-solid fa-magnifying-glass"></i>
                        </button>
                    </form>
                </div>
            </div>

            <div class="col-xl-1 col-lg-1 col-sm-1 col-12">
                <div class="cart">
                    <a href="/Shop/cart">
                        <i class="fa-solid fa-cart-shopping"></i>
                    </a>
                </div>
            </div>
            <div class="col-xl-1 col-lg-1 col-sm-1 col-12">
                <div class="order">
                    <a href="/Shop/order">
                        <i class="fa-solid fa-truck-fast"></i>
                    </a>
                </div>
            </div>

            <div class="col-xl-3 col-lg-3 col-sm-3 col-12 text-right">
                <c:if test="${requestScope.User != null}">
                    <div class="dropdown">
                        <img src="${requestScope.User.avatar}" class="dropdown-toggle" 
                             data-toggle="dropdown" aria-expanded="false" 
                             style="width:40px"/>
                        <div class="dropdown-menu">
                            <span class="btn btn-sm btn-info text-center">${requestScope.User.fullName}</span>
                            <a class="dropdown-item" href="/Shop/detail-user">Chi tiết tài khoản</a>
                            <a class="dropdown-item" href="/Shop/change-password">Đổi mật khẩu</a>
                            <a class="dropdown-item" href="/Shop/logout">Logout</a>
                        </div>
                    </div>
                </c:if> 
                
                <c:if test="${requestScope.User == null}">
                    <a href="/Shop/login" class="btn btn-outline-primary btn-sm mr-2">Login</a>
                    <a href="/Shop/register" class="btn btn-outline-secondary btn-sm">Register</a>
                </c:if>
            </div>
        </div>
    </div>
</div>
