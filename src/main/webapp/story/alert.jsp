
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
        <div class="login">
            <ul>
                <li><a href="/StoryServlet?action=login">Đăng nhập</a></li>
                <li><a href="/StoryServlet?action=register">Đăng ký</a></li>
            </ul>
        </div>

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
                <h3>CẢNH BÁO</h3>
                <div class = "message">
                    <c:if test="${requestScope['message']!= null}">
                        <div class="alert alert-warning">
                            <strong>Bạn cần đăng nhập mới có thể sử dụng chức năng này!</strong>
                        </div>
                    </c:if>
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
