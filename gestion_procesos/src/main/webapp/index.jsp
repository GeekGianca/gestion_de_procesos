<%-- 
    Document   : index
    Created on : Jun 18, 2019, 8:48:51 AM
    Author     : Gianc
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion de procesos</title>
        <link href="css/bootstrap.css" rel="stylesheet">
    </head>
    <body>
        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
            <div class="container">
                <a class="navbar-brand" href="#">Gestor De Procesos</a>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="index.do">Agregar
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="procesos.do">Procesos
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Page Content -->
        <div class="container">
            <div class="row">
                <div class="col-lg-12 mt-5">
                    <form action="registrarproceso.do" method="POST">
                        <div class="form-group">
                            <label for="quantum">Quantum General</label>
                            <small id="quantumHelp" class="form-text text-muted">Solo 1 por el proceso.</small>
                        </div>
                        <div class="form-group">
                            <label for="pidEntrada">Cantidad de procesos</label>
                            <input required type="text" class="form-control" name="cantidad" id="cantidad" aria-describedby="pidHelp" placeholder="Cantidad de procesos">
                            <small id="emailHelp" class="form-text text-muted">Agregue la cantidad de procesos que desea capturar.</small>
                        </div>

                        <button type="submit" class="btn btn-primary">Agregar</button>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/bootstrap.bundle.min.js"></script>

    </body>
</html>

<!--<p><a href="webapi/myresource">Jersey resource</a>-->