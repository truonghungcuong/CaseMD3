
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="/story/style1.css">
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<div class="container">
    <header>
        <div class="logo">
            <a href="#"><img src="image/logo_transparent-removebg-preview (1).png"></a>


        </div>
        <c:choose>

            <c:when test="${requestScope['user']  == null}">
                <div class="login">
                    <ul>
                        <li><a href="/StoryServlet?action=login">Đăng nhập</a></li>
                        <li><a href="/StoryServlet?action=register">Đăng ký</a></li>
                    </ul>
                </div>
            </c:when>
            <c:otherwise>
                <div class="username">
                    <!-- Example split danger button -->
                    <div class="btn-group" style="float: right">
                        <div class="avatar">
                            <img src="/avatar/minh94.png" class="img-circle" alt="Cinque Terre" width="50" height="50" style="   border: 1px #d4d4d4 solid; padding: 1px;
    border-radius:50%;
    -moz-border-radius:50%;
    -webkit-border-radius:50%;
margin-top:5px;
margin-right:10px">
                        </div>
                        <h5 style="margin-top: 20px ; font-size: 15px">
                            Chào: ${requestScope['user'].getUsername()}
                        </h5>
                        <button style="margin-top: 10px" type="button"
                                class="btn  dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown"
                                aria-expanded="false">
                            <span class="visually-hidden">Toggle Dropdown</span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="#">Thông tin tài khoản</a></li>
                            <li><a class="dropdown-item" href="/StoryServlet?action=logOut">Đăng xuất</a></li>

                        </ul>
                    </div>
                </div>

            </c:otherwise>
        </c:choose>



    </header>
    <nav>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/StoryServlet">Trang chủ</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Danh mục truyện
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="/StoryServlet?action=viewByCategory&id=1">Truyện tranh</a></li>
                                <li><a class="dropdown-item" href="/StoryServlet?action=viewByCategory&id=2">Tiểu thuyết</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex" method="post" action="/StoryServlet?action=find">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="name">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                </div>
            </div>
        </nav>
    </nav>

    <div class="slide">
        <div id="carouselExampleSlidesOnly" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="image/hinh-nen-luffy-one-piece-29.jpg" class="d-block w-100" >
                </div>
                <div class="carousel-item">
                    <img src="image/4568110 (1).jpg" class="d-block w-100" >
                </div>
                <div class="carousel-item">
                    <img src="image/bo-hinh-nen-truyen-tranh-hap-dan-nguoi-xem-nhat-26.jpg" class="d-block w-100" >
                </div>
            </div>
        </div>
    </div>

    <content>
        <div class="row">


            <div class="col-10">
                <h3>ĐẶT HÀNG</h3>
                <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
                <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
                <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
                <!------ Include the above in your HEAD tag ---------->

                <div class="container">
                    <div class="row">

                        <div class="col-xs-8">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    <div class="panel-title">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <h5><span class="glyphicon glyphicon-shopping-cart"></span> Giỏ hàng</h5>
                                            </div>
                                            <div class="col-xs-6">

                                                <a href="/StoryServlet"><button type="button" class="btn btn-primary btn-sm btn-block">
                                                    <span class="glyphicon glyphicon-share-alt"></span> Tiếp tục mua truyện
                                                </button></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <form method="post" action="/StoryServlet?action=editCart">
                                    <c:forEach items="${requestScope['cartList']}" var = "cart">

                                    <div class="row">
                                        <div class="col-xs-2"><img class="img-responsive" src="${cart.getImg()}">
                                        </div>
                                        <div class="col-xs-4">
                                            <h4 class="product-name"><strong>${cart.getStoryName()}</strong></h4><h4><small>${cart.getStoryPart()}</small></h4>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="col-xs-3 text-right">
                                                <h6 name=""><strong name="price">${cart.getPrice()}<span class="text-muted"> x</span></strong></h6>
                                            </div>
                                            <div class="col-xs-2">
                                                <input type="text" class="form-control input-sm"  value="${cart.getQuantity()}" name="quantity">
                                            </div>
                                            <div class="col-xs-3 text-right">
                                                <h6 name=""><strong name="price">= ${cart.getPayMoney()}<span class="text-muted"></span></strong></h6>
                                            </div>

                                            <div class="col-xs-4">
                                                <a href="/CartServlet?action=editCart&id=${cart.getId()}"> <button type="button" class="btn btn-link btn-xs">
                                                    <span class="glyphicon glyphicon-pencil"></span>
                                                </button></a>
                                                <a href="/StoryServlet?action=deleteCart&id=${cart.getId()}"> <button type="button" class="btn btn-link btn-xs">
                                                    <span class="glyphicon glyphicon-trash"> </span></button></a>

                                            </div>
                                        </div>
                                    </div>
                                    </c:forEach>
                                    </form>

                                    <hr>
                                    <div class="row">
                                        <div class="text-center">


                                        </div>
                                    </div>
                                </div>
                                <div class="panel-footer">
                                    <div class="row text-center">
                                        <div class="col-xs-9">
                                            <h4 class="text-right">Tổng số tiền phải trả: <strong>${requestScope['totalPayMoney']}(VND)</strong></h4>
                                        </div>
                                        <div class="col-xs-3">
                                            <a href=""><button type="button" class="btn btn-success btn-block">
                                                Xác nhận
                                            </button></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>
                </div>

            </div>
            <div class="col-2">
                <div class="sidebar">
                    <ul class="nav flex-column1">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="/StoryServlet">Trang chủ</a>
                        </li>
                        <li class="nav-item">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Danh mục truyện
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="/StoryServlet?action=viewByCategory&id=1">Truyện tranh</a></li>
                                <li><a class="dropdown-item" href="/StoryServlet?action=viewByCategory&id=2">Tiểu thuyết</a></li>
                            </ul>
                        </li>
                        </li>

                        <li class="nav-item">
                            <form class="d-flex" method="post" action="/StoryServlet?action=find">
                                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" name="name">
                                <button class="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </li>

                    </ul>

                </div>
            </div>
        </div>
    </content>

</div>
</body>
</html>
