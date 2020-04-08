<%-- 
    Document   : index
    Created on : Jun 18, 2019, 8:48:51 AM
    Author     : Gianc
--%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.List"%>
<%@page import="gestiondeprocesos.Proceso"%>
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
                        <li class="nav-item">
                            <a class="nav-link" href="index.do">Agregar
                            </a>
                        </li>
                        <li class="nav-item active">
                            <a class="nav-link" href="procesos.do">Procesos
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Page Content -->

        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <table class="mt-5 table table-dark">
                        <thead>
                            <tr>
                                <th scope="col">Pid</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Prioridad</th>
                                <th scope="col">Descripcion</th>
                                <th scope="col">Usuario</th>
                            </tr>
                        </thead>
                        <%
                            List<Proceso> procesos = (List) request.getSession().getAttribute("procesos");
                            if (procesos.size() > -1) {
                        %>
                        <tbody>
                            <%
                                for(Proceso p : procesos){
                            %>
                            <tr>
                                <th scope="row"><%=p.getPid()%></th>
                                <td><%=p.getNombre()%></td>
                                <td><%=p.getPrioridad()%></td>
                                <td><%=p.getDescripcion()%></td>
                                <td><%=p.getUsuario()%></td>
                            </tr>
                            <%
                                }
                            }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="js/bootstrap.bundle.min.js"></script>

    </body>
</html>

