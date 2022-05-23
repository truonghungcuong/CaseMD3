<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Tempusdominus Bootstrap 4 -->
    <link rel="stylesheet" href="../plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="../plugins/icheck-bootstrap/icheck-bootstrap.min.css">
    <!-- JQVMap -->
    <link rel="stylesheet" href="../plugins/jqvmap/jqvmap.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="../dist/css/adminlte.min.css">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="../plugins/overlayScrollbars/css/OverlayScrollbars.min.css">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="../plugins/daterangepicker/daterangepicker.css">
    <!-- summernote -->
    <link rel="stylesheet" href="../plugins/summernote/summernote-bs4.min.css">
</head>

<style>
    th {
        text-align: center;
    }
    .user{float: left}

</style>
<body>

<div class="wrapper">

    <aside class="main-sidebar sidebar-dark-primary elevation-4">
        <!-- Brand Logo -->
        <a href="index3.html" class="brand-link">
            <img src="../dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
                 style="opacity: .8">
            <span class="brand-text font-weight-light">Admin System</span>
        </a>

        <!-- Sidebar -->
        <div class="sidebar">
            <!-- Sidebar user panel (optional) -->
            <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                <div class="image">
                    <img src="${requestScope['user'].getImg()}" class="img-circle elevation-2" alt="User Image">
                </div>

                <div class="info">
                    <a href="#" class="d-block">Chào: ${requestScope['user'].getUsername()}</a>
                </div>
                <li style="margin-bottom:20px" class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown2" role="button"
                       data-bs-toggle="dropdown" aria-expanded="false">

                    </a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <li style="margin-bottom:60px"><a style="color:black; font-weight: bold" class="dropdown-item"
                                                          href="/StoryServlet?action=logOut">Đăng xuất</a></li>

                    </ul>
                </li>
            </div>







            <!-- SidebarSearch Form -->
            <div class="form-inline">
                <form action="stories">
                    <div class="input-group" data-widget="sidebar-search">
                        <input class="form-control form-control-sidebar" name="q" type="search" placeholder="Search"
                               aria-label="Search">
                        <div class="input-group-append">
                            <button class="btn btn-sidebar">
                                <i class="fas fa-search fa-fw">Tìm Kiếm</i>
                            </button>
                        </div>
                    </div>
                </form>
            </div>

            <!-- Sidebar Menu -->
            <nav class="mt-2">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/StoryServlet">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Quản lý truyện
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a style="color:black; font-weight: bold" class="dropdown-item" href="/stories">Hiển thị
                                danh sách truyện</a></li>
                            <li><a style="color:black; font-weight: bold" class="dropdown-item"
                                   href="/stories?action=create">Thêm mới truyện</a></li>
                            <li><a style="color:black; font-weight: bold" class="dropdown-item" href="/categories">Quản
                                lý danh mục truyện</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown4" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Quản lý User
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a style="color:black; font-weight: bold" class="dropdown-item" href="/userservlet">Hiển
                                thị danh sách user</a></li>
                            <li><a style="color:black; font-weight: bold" class="dropdown-item"
                                   href="/userservlet?action=create">Thêm mới user</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown5" role="button"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            Quản lý đơn hàng
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <li><a style="color:black; font-weight: bold" class="dropdown-item" href="/userservlet">Hiển
                                thị danh sách đơn hàng</a></li>
                        </ul>
                    </li>
                    </li>

                    <li class="nav-item">
                    </li>

                </ul>
            </nav>
            <!-- /.sidebar-menu -->
        </div>
        <!-- /.sidebar -->
    </aside>
<div class = "wrapper">


    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <!-- /.content-header -->

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">
                <div class="container">
                    <h1>TẠO MỚI DANH MỤC</h1>
                    <a href="categories">To Back</a>
                    <form action="categories?action=create" method="post">
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label">Tên Danh Mục</label>
                            <input type="text" class="form-control" id="exampleInputPassword1" name="name">
                        </div>
                        <button type="submit" class="btn btn-primary">Tạo mới</button>
                    </form>
                </div>
            </div><!-- /.container-fluid -->
        </section>
        <!-- /.content -->
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</div>
</body>
</html>
