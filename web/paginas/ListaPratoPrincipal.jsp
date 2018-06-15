<%-- 
    Document   : ListaPratoPrincipal
    Created on : 28/05/2018, 14:56:34
    Author     : bianc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="../vendor/ListaPratoPrinc.css" rel="stylesheet" type="text/css">
        <link href="../vendor/cabecalho.css" rel="stylesheet" type="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="cabecalho">
            <p><img id="imgLogo" src="logo.png" alt="logo"/></p> 
            <h1 id="textoCabecalho" class="fa-4x"> G.S. Refeições </h1>
        </div>
        <div id="menu">
            <h2> Menu </h2>
            <ul class="menu-sanfona">
                <li tabindex="0"> Cadastros 
                    <ul>
                        <li><a href="Pagina02.jsp">Marmita</a></li>
                        <li><a href="Pagina02.jsp">Tamanho Marmita</a></li>
                        <li><a href="Pagina02.jsp">Preço Marmita</a></li>
                        <li><a href="Pagina02.jsp">Prato Principal</a></li>
                        <li><a href="Pagina02.jsp">Cliente</a></li>
                        <li><a href="Pagina02.jsp"> Funcionário </a></li>
                        <li><a href="Pagina02.jsp"> Status Funcionário </a></li>
                    </ul>
                </li>    
                <li tabindex="1"> Listas 
                    <ul>
                        <li><a href="Pagina02.jsp">Marmita</a></li>
                        <li><a href="Pagina02.jsp">Tamanho Marmita</a></li>
                        <li><a href="Pagina02.jsp">Preço Marmita</a></li>
                        <li><a href="Pagina02.jsp">Prato Principal</a></li>
                        <li><a href="Pagina02.jsp">Cliente</a></li>
                        <li><a href="Pagina02.jsp"> Funcionário </a></li>
                        <li><a href="Pagina02.jsp"> Status Funcionário </a></li>
                    </ul>
                </li>
                <li tabindex="2"> <a href="Pagina02.jsp">Pedidos </a></li>
                <li tabindex="3"> <a href="Pagina02.jsp">Relatório </a></li>
            </ul>
        </div>
        <div class="w3-responsive" id="tabela">
            <h1>Prato Principal</h1>
            <table class="w3-table-all">
                <tr>
                    <th> Id </th>
                    <th> Nome </th>
                    <th> Status </th>
                </tr>
                <tbody>
                    ${resultado}
                </tbody>
            </table>
        </div>
    </body>
</html>
