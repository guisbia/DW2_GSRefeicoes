/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOCliente;
import DAOs.DAOFuncionario;
import DAOs.DAOPedido;
import Entidades.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bianc
 */
@WebServlet(name = "PedidoServlet", urlPatterns = {"/pedido"})
public class PedidoServlet extends HttpServlet {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOPedido daoPedido = new DAOPedido();
            Pedido pedido = new Pedido();
            String tabela = "";
            String id = request.getParameter("id");
            String func = request.getParameter("funcionario");
            String cliente = request.getParameter("cliente");
            String data = request.getParameter("data");

            if (request.getParameter("id") == "" || request.getParameter("id") == null) {
                List<Pedido> lista = daoPedido.listInOrderId();
                for (Pedido p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdPedido() + "</td>"
                            + "<td>" + p.getFuncionarioIdFuncionario().getNomeFuncionario() + "</td>"
                            + "<td>" + p.getClienteIdCliente().getNomeCliente() + "</td>"
                            + "<td>" + sdf.format(p.getDataPedido()) + "</td>"
                            + "</tr>";
                }

            } else {
                inserir(id, func, cliente, data);

                List<Pedido> lista = daoPedido.listInOrderId();
                for (Pedido p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdPedido() + "</td>"
                            + "<td>" + p.getFuncionarioIdFuncionario().getNomeFuncionario() + "</td>"
                            + "<td>" + p.getClienteIdCliente().getNomeCliente() + "</td>"
                            + "<td>" + sdf.format(p.getDataPedido()) + "</td>"
                            + "</tr>";
                }

            }

   //         request.getSession().setAttribute("resultado", tabela);
            request.getSession().setAttribute("idPedido", Integer.valueOf(id));
     //       response.sendRedirect(request.getContextPath() + "/paginas/listaPedido.jsp");
            response.sendRedirect(request.getContextPath() + "/paginas/cadastroItensPedido.jsp");
            id = "";
            cliente = "";
            func = "";
            data = "";
        }
    }
    
    public void inserir(String id, String func, String cliente, String data) {
        DAOPedido daoPedido = new DAOPedido();
        DAOCliente daoCliente = new DAOCliente();
        DAOFuncionario daoFuncionario = new DAOFuncionario();
        Pedido pedido = new Pedido();
        pedido.setIdPedido(Integer.valueOf(id));
        pedido.setClienteIdCliente(daoCliente.obter(Integer.valueOf(cliente)));
        pedido.setFuncionarioIdFuncionario(daoFuncionario.obter(Integer.valueOf(func)));
        try {
            pedido.setDataPedido(sdf.parse(data));
        } catch (ParseException ex) {
            Logger.getLogger(FuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        daoPedido.inserir(pedido);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
