<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta http-equiv="x-ua-compatible" content="ie=edge">
  <title>Material Design Bootstrap</title>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<c:url value="/resources/https://use.fontawesome.com/releases/v5.8.1/css/all.css"/>">
  <!-- Bootstrap core CSS -->
  <link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
  <!-- Material Design Bootstrap -->
  <link href="<c:url value="/resources/css/mdb.min.css"/>" rel="stylesheet">
  <!-- Your custom styles (optional) -->
  <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
</head>

<body class="fixed-sn navy-blue-skin" aria-busy="true">

  <header>
    <div id="slide-out" class="side-nav sn-bg-4 fixed" style="transform: translateX(0%);">
      <ul class="custom-scrollbar">
        <!-- Logo -->
        <li>
          <div class="logo-wrapper waves-light waves-effect waves-light">
            <a href="#"><img src="https://mdbootstrap.com/img/logo/mdb-transparent.png" class="img-fluid flex-center"></a>
          </div>
        </li>
        <!--/. Logo -->
        <!--Social-->
        <li>
          <ul class="social">
            <li><a href="#" class="icons-sm fb-ic"><i class="fab fa-facebook-f"> </i></a></li>
            <li><a href="#" class="icons-sm pin-ic"><i class="fab fa-pinterest"> </i></a></li>
            <li><a href="#" class="icons-sm gplus-ic"><i class="fab fa-google-plus-g"> </i></a></li>
            <li><a href="#" class="icons-sm tw-ic"><i class="fab fa-twitter"> </i></a></li>
          </ul>
        </li>
        <!--/Social-->
        <!--Search Form-->
        <li>
          <form class="search-form" role="search">
            <div class="form-group md-form mt-0 pt-1 waves-light waves-effect waves-light">
              <input type="text" class="form-control" placeholder="Search">
            </div>
          </form>
        </li>
        <!--/.Search Form-->
        <!-- Side navigation links -->
        <li>
          <ul class="collapsible collapsible-accordion">
            <li><a class="collapsible-header waves-effect arrow-r"><i class="fas fa-chevron-right"></i> Submit blog<i class="fas fa-angle-down rotate-icon"></i></a>
              <div class="collapsible-body">
                <ul class="list-unstyled">
                  <li><a href="#" class="waves-effect">Submit listing</a>
                  </li>
                  <li><a href="#" class="waves-effect">Registration form</a>
                  </li>
                </ul>
              </div>
            </li>
            <li><a class="collapsible-header waves-effect arrow-r"><i class="far fa-hand-pointer"></i> Instruction<i class="fas fa-angle-down rotate-icon"></i></a>
              <div class="collapsible-body">
                <ul class="list-unstyled">
                  <li><a href="#" class="waves-effect">For bloggers</a>
                  </li>
                  <li><a href="#" class="waves-effect">For authors</a>
                  </li>
                </ul>
              </div>
            </li>
            <li><a class="collapsible-header waves-effect arrow-r"><i class="fas fa-eye"></i> About<i class="fas fa-angle-down rotate-icon"></i></a>
              <div class="collapsible-body">
                <ul class="list-unstyled">
                  <li><a href="#" class="waves-effect">Introduction</a>
                  </li>
                  <li><a href="#" class="waves-effect">Monthly meetings</a>
                  </li>
                </ul>
              </div>
            </li>
            <li><a class="collapsible-header waves-effect arrow-r"><i class="far fa-envelope"></i> Contact me<i class="fas fa-angle-down rotate-icon"></i></a>
              <div class="collapsible-body">
                <ul class="list-unstyled">
                  <li><a href="#" class="waves-effect">FAQ</a>
                  </li>
                  <li><a href="#" class="waves-effect">Write a message</a>
                  </li>
                  <li><a href="#" class="waves-effect">FAQ</a>
                  </li>
                  <li><a href="#" class="waves-effect">Write a message</a>
                  </li>
                  <li><a href="#" class="waves-effect">FAQ</a>
                  </li>
                  <li><a href="#" class="waves-effect">Write a message</a>
                  </li>
                  <li><a href="#" class="waves-effect">FAQ</a>
                  </li>
                  <li><a href="#" class="waves-effect">Write a message</a>
                  </li>
                </ul>
              </div>
            </li>
          </ul>
        </li>
        <!--/. Side navigation links -->
      </ul>
      <div class="sidenav-bg mask-strong"></div>
    </div>
    <!-- Sidebar navigation --><!--/. Sidebar navigation -->
    <!-- Navbar -->
    <nav class="navbar fixed-top navbar-toggleable-md navbar-expand-lg scrolling-navbar double-nav">
      <!-- SideNav slide-out button -->
      <div class="float-left">
        <a href="#" data-activates="slide-out" class="button-collapse"><i class="fas fa-bars"></i></a>
      </div>
      <!-- Breadcrumb-->
      <div class="breadcrumb-dn mr-auto">
        <p>Material Design for Bootstrap</p>
      </div>
      <ul class="nav navbar-nav nav-flex-icons ml-auto">
        <li class="nav-item">
          <a class="nav-link waves-effect waves-light"><i class="fas fa-envelope"></i> <span class="clearfix d-none d-sm-inline-block">Contact</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link waves-effect waves-light"><i class="far fa-comments"></i> <span class="clearfix d-none d-sm-inline-block">Support</span></a>
        </li>
        <li class="nav-item">
          <a class="nav-link waves-effect waves-light"><i class="fas fa-user"></i> <span class="clearfix d-none d-sm-inline-block">Account</span></a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle waves-effect waves-light" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Dropdown
          </a>
          <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
            <a class="dropdown-item waves-effect waves-light" href="#">Action</a>
            <a class="dropdown-item waves-effect waves-light" href="#">Another action</a>
            <a class="dropdown-item waves-effect waves-light" href="#">Something else here</a>
          </div>
        </li>
      </ul>
    </nav>
    <!-- /.Navbar -->
  </header>

  <!-- SCRIPTS -->
  <!-- JQuery -->
  <script type="text/javascript" src="<c:url value="/resources/js/jquery-3.3.1.min.js"/>"></script>
  <!-- Bootstrap tooltips -->
  <script type="text/javascript" src="<c:url value="/resources/js/popper.min.js"/>"></script>
  <!-- Bootstrap core JavaScript -->
  <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  <!-- MDB core JavaScript -->
  <script type="text/javascript" src="<c:url value="/resources/js/mdb.min.js"/>"></script>
</body>

</html>
