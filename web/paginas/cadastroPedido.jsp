<%-- 
    Document   : cadastroPedido
    Created on : 10/10/2018, 13:44:03
    Author     : bianc
--%>

<%@page import="Entidades.Funcionario"%>
<%@page import="Entidades.Cliente"%>
<%@page import="Entidades.PratoPrincipal"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    DAOs.DAOFuncionario daoFunc = new DAOs.DAOFuncionario();
    List<Funcionario> func = daoFunc.listInOrderId();

    DAOs.DAOCliente daoCliente = new DAOs.DAOCliente();
    List<Cliente> client = daoCliente.listInOrderId();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


        <!-- Bootstrap Core CSS -->
        <link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="../dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

        <link href="../vendor/cabecalho.css" rel="stylesheet" type="text/css">

        <!-- Morris Charts CSS -->
        <link href="../vendor/morrisjs/morris.css" rel="stylesheet">


        <link href="../vendor/arrumaMenuTabela.css" rel="stylesheet" type="text/css">

        <link rel="shortcut icon" type="image/png" href="logo.png"/>
        
        <title>Cadastro - PratoPrincipal </title>
    </head>
    <body>
        <div id="cabecalho">
            <p><img id="imgLogo" src="logo.png" alt="logo"/></p> 
            <h1 id="textoCabecalho" class="fa-4x"> G.S. Refeições </h1>
        </div>
        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">

                <ul class="nav" id="side-menu">

                    <li>
                        <a><i class="fa fa-4x fa-fw"></i> Menu</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-bar-chart-o fa-fw"></i> Cadastros <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="cadastroMarmita.jsp">Marmita</a></li>
                            <li><a href="cadastroTamanhoMarmita.jsp">Tamanho Marmita</a></li>
                            <li><a href="cadastroPrecoProduto.jsp">Preço Marmita</a></li>
                            <li><a href="cadastroPratoPrincipal.jsp">Prato Principal</a></li>
                            <li><a href="cadastroCliente.jsp">Cliente</a></li>
                            <li><a href="cadastroFuncionario.jsp"> Funcionário </a></li>
                            <li><a href="cadastroStatus.jsp"> Status Funcionário </a></li>
                        </ul>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-calendar-check-o fa-fw"></i> Listas <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">

                            <li><a href="../marmita">Marmita</a></li>
                            <li><a href="../tamanhoMarmita">Tamanho Marmita</a></li>
                            <li><a href="../precoProduto">Preço Marmita</a></li>
                            <li><a href="../pratoPrincipal">Prato Principal</a></li>
                            <li><a href="../cliente">Cliente</a></li>
                            <li><a href="../funcionario"> Funcionário </a></li>
                            <li><a href="listaStatus.jsp"> Status Funcionário </a></li>

                        </ul>
                        <!-- /.nav-second-level -->
                    </li>

                    <li>
                        <a href="#"><i class="fa fa-calendar-check-o fa-fw"></i> Pedidos <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="listaPedido.jsp">Lista de pedidos</a></li>
                            <li><a href="cadastroPedido.jsp">Fazer pedidos</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="Pagina02.jsp"><i class="fa fa-edit fa-fw"></i> Relatórios</a>
                    </li>
                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>




        <div class="panel panel-default" id="help">
            <div class="panel-heading">
                Fazer Pedido
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-lg-6">
                        <form role="form" method="post" action="${pageContext.request.contextPath}/pedido">
                            <div class="form-group">
                                <label>ID </label>
                                <% DAOs.DAOPedido dao = new DAOs.DAOPedido();%>
                                <input class="form-control" type="text" name="id" value="<%=dao.autoIdPedido() %>" readonly="">
                            </div>
                            <div class="form-group">
                                <label>Funcionário </label>
                                <select class="form-control" name="funcionario">
                                    <%
                                        for (Funcionario f : func) {
                                    %>


                                    <option value="<%= f.getIdFuncionario() %>"> <%=f.getNomeFuncionario() %> </option>

                                    <%}%>
                                </select>
                            </div>
                                
                            <div class="form-group">
                                <label>Cliente </label>
                                <select class="form-control" name="cliente">
                                    <%
                                        for (Cliente c : client) {
                                    %>

                                    <option value="<%= c.getIdCliente() %>"> <%=c.getNomeCliente() %> </option>

                                    <%}%>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Data </label>
                                <input class="form-control" type="text" name="data">
                            </div>
                            <input type="submit"  name="ok">
                        </form>
                    </div>
                    <!-- /.col-lg-6 (nested) -->
                </div>
                <!-- /.row (nested) -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->


        <!-- jQuery -->
        <script src="../vendor/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="../vendor/metisMenu/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="../dist/js/sb-admin-2.js"></script> 

    </body>
</html>

