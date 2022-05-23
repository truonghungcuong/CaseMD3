
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

            <center>
            <div class="col-10">

                <div class="lable">
                    <h3>Tập ${requestScope['part'].getEpisode()} - ${requestScope['part'].getName()}</h3>

                </div>
                <div class="btn-group">

                    <button style="margin-bottom:20px" type="button" class="btn btn-danger">Tập</button>
                    <button  type="button" style="margin-bottom:20px" class="btn btn-danger dropdown-toggle dropdown-toggle-split" data-bs-toggle="dropdown" aria-expanded="false">
                        <span class="visually-hidden">Toggle Dropdown</span>
                    </button>

                    <ul class="dropdown-menu">
                        <c:forEach items="${requestScope['parts']}" var="part">
                            <li><a class="dropdown-item" href="/StoryServlet?action=read&storyId=${part.getStoryID()}&id=${part.getId()}&categoryId=${part.getCategoryId()}">Tập ${part.getEpisode()} - ${part.getName()}</a></li>
                        </c:forEach>

                    </ul>


                </div>
                <div class="product">
                    <c:forEach items="${requestScope['images']}" var="image">

                        <div class="content-img">
                            <img  src="${image.getImg()}" >
                        </div>
                    </c:forEach>
                </div>
            </div>
            </center>

            <div class="col-2">

            </div>
        </div>
    </content>

</div>
</body>
</html>
