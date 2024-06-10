<%@page import="java.util.List"%>
<%@page import="elements.*"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <%
        Qualite appelQualite = new Qualite();
        List<Qualite> ListeQualite = appelQualite.SelectQualite();
        Route appelRoute = new Route();
        List<Route> ListeRoute = appelRoute.SelectRoute();
    %>
    <meta charset="utf-8">
    <title>Insertion de type</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free HTML Templates" name="keywords">
    <meta content="Free HTML Templates" name="description">

    <!-- Favicon -->
    <link href="css/img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="/css/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Topbar Start -->
    <div class="container-fluid">
        <div class="row bg-secondary py-2 px-xl-5">
            <div class="col-lg-6 d-none d-lg-block">
                <div class="d-inline-flex align-items-center">
                    <a class="text-dark" href="">Construction</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="">Lalana</a>
                    <span class="text-muted px-2">|</span>
                    <a class="text-dark" href="">Route</a>
                </div>
            </div>
            <div class="col-lg-6 text-center text-lg-right">
                <div class="d-inline-flex align-items-center">
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                    <a class="text-dark px-2" href="">
                        <i class="fab fa-instagram"></i>
                    </a>
                    <a class="text-dark pl-2" href="">
                        <i class="fab fa-youtube"></i>
                    </a>
                </div>
            </div>
        </div>
        <div class="row align-items-center py-3 px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a href="" class="text-decoration-none">
                    <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Lalana</h1>
                </a>
            </div>
            <div class="col-lg-6 col-6 text-left">
                <form action="">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="">
                        <div class="input-group-append">
                            <span class="input-group-text bg-transparent text-primary">
                                <i class="fa fa-search"></i>
                            </span>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-lg-3 col-6 text-right">
                <a href="" class="btn border">
                    <i class="fas fa-heart text-primary"></i>
                    <span class="badge">0</span>
                </a>
                <a href="" class="btn border">
                    <i class="fas fa-shopping-cart text-primary"></i>
                    <span class="badge">0</span>
                </a>
            </div>
        </div>
    </div>
    <!-- Topbar End -->


    <!-- Navbar Start -->
    <div class="container-fluid mb-5">
        <div class="row border-top px-xl-5">
            <div class="col-lg-3 d-none d-lg-block">
                <a class="btn shadow-none d-flex align-items-center justify-content-between bg-primary text-white w-100" data-toggle="collapse" href="#navbar-vertical" style="height: 65px; margin-top: -1px; padding: 0 30px;">
                    <h6 class="m-0">Pages</h6>
                    <i class="fa fa-angle-down text-dark"></i>
                </a>
                <nav class="collapse show navbar navbar-vertical navbar-light align-items-start p-0 border border-top-0 border-bottom-0" id="navbar-vertical">
                    
                        <a href="InsertionType.jsp" class="nav-item nav-link">Inserer Type</a>
                        <a href="InsertionQualite.jsp" class="nav-item nav-link">Inserer les qualités</a>
                        <a href="InsertionRoute.jsp" class="nav-item nav-link">Inserer des routes</a>
                        <a href="InsertionMaterielType.jsp" class="nav-item nav-link">Donner des materiels/Route</a>
                        
                        <a href="InsertRouteQualite.jsp" class="nav-item nav-link">Inserer Route par qualite</a>
                        <a href="filtrePrix.jsp" class="nav-item nav-link">Filtre entre deux Prix</a>
                        <a href="AjouterStock.jsp" class="nav-item nav-link">Ajouter Stock</a>
                        <a href="insertPersonnel.jsp" class="nav-item nav-link">Inserer des types de personnels</a>
                        <a href="InsertionEmployeTravaillant.jsp" class="nav-item nav-link">Inserer employer travaillant</a>
                        <a href="FiltreBenefice.jsp" class="nav-item nav-link">Filtre Benefice</a>
                        <a href="ListePostePersonne.jsp" class="nav-item nav-link">Inserer Type de personnel</a>
                        <a href="FabriquerRoute.jsp" class="nav-item nav-link">Fabriquer Route</a>
                        <a href="InsertClient.jsp" class="nav-item nav-link">Inserer Client</a>
                        <a href="InsertPrixVente.jsp" class="nav-item nav-link">Donner prix de vente</a>
                        <a href="InsertVente.jsp" class="nav-item nav-link">Faire une vente</a>
                        <a href="ListeStockProduit.jsp" class="nav-item nav-link">Stock des produits</a> 
                        <a href="Vente.jsp" class="nav-item nav-link">Vente</a>
                        <a href="pourcentage.jsp" class="nav-item nav-link">Statistique</a>

                        
                </nav>
            </div>
            <div class="col-lg-9">
                <nav class="navbar navbar-expand-lg bg-light navbar-light py-3 py-lg-0 px-0">
                    <a href="" class="text-decoration-none d-block d-lg-none">
                        <h1 class="m-0 display-5 font-weight-semi-bold"><span class="text-primary font-weight-bold border px-3 mr-1">E</span>Shopper</h1>
                    </a>
                    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="navbar-nav mr-auto py-0">
                            <a href="" class="nav-item nav-link active">Home</a>
                            <a href="" class="nav-item nav-link">Shop</a>
                            <a href="" class="nav-item nav-link">Shop Detail</a>
                            <div class="nav-item dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown">Pages</a>
                                <div class="dropdown-menu rounded-0 m-0">
                                    <a href="" class="dropdown-item">Shopping Cart</a>
                                    <a href="" class="dropdown-item">Checkout</a>
                                </div>
                            </div>
                            <a href="" class="nav-item nav-link">Contact</a>
                        </div>
                    </div>
                </nav>
                
    <!-- Navbar End -->


    


    <!-- ------------------------------------------------atooooo---------------------------------------->
    <div class="container-fluid my-5">
        
            <div class="row justify-content-center w-100">
                <div class="col-md-12">
                    <h3>Inserer une route</h3>
                    <form action="InsertRouteQualite.jsp" method="post" class="question-form w-100">

                        <div class="mb-3">
                            <select name="idRoute" class="form-control" required>
                                <% for (int i = 0; i < ListeRoute.size(); i++) { %>
                                    <option value="<%=ListeRoute.get(i).getIdRoute()%>"><%= ListeRoute.get(i).getNomRoute()%></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="mb-3">
                            <select name="idQualite" class="form-control" required>
                                <% for (int i = 0; i < ListeQualite.size(); i++) { %>
                                    <option value="<%=ListeQualite.get(i).getIdQualite()%>"><%= ListeQualite.get(i).getNomQualite()%></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary btn-block">Valider</button>
                        </div>
                    </form>
                    <%
                        if( request.getParameter("idRoute") != null && request.getParameter("idQualite") != null ){
                            out.print("eto");
                            RouteQualite appelRouteQ = new RouteQualite(request.getParameter("idRoute"),request.getParameter("idQualite"));
                            appelRouteQ.InsertRouteQualite(null);
                        }
                    %>
                </div>

            </div>
    </div>

    <!-- -----------------------------------------------atooooooooooo------------------------------------------------- -->


   

    
    <!-- Footer Start -->
    
    <!-- Footer End -->


    <!-- Back to Top -->
    <a href="#" class="btn btn-primary back-to-top"><i class="fa fa-angle-double-up"></i></a>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="/css/lib/easing/easing.min.js"></script>
    <script src="/css/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="css/mail/jqBootstrapValidation.min.js"></script>
    <script src="css/mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="css/js/main.js"></script>
</body>

</html>

