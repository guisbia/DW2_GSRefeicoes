/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import Entidades.PedidoHasMarmita;
import Entidades.PedidoHasMarmitaPK;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bianc
 */
@WebServlet(name = "ItensPedidoServlet", urlPatterns = {"/itensPedido"})
public class ItensPedidoServlet extends HttpServlet {

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
            PedidoHasMarmita pedidoHasMarmita = new PedidoHasMarmita();
            DAOs.DAOPedidoHasMarmita daoPedidoHasMarmita = new DAOs.DAOPedidoHasMarmita();
            String tabela = "";
            String idPedido = request.getParameter("id");
            String idMarmita = request.getParameter("marmita");
            String qtde = request.getParameter("qtde");
            String valor = request.getParameter("valor");
            String desconto = request.getParameter("desconto");

            if (request.getParameter("id") == "" || request.getParameter("id") == null) {
                List<PedidoHasMarmita> lista = daoPedidoHasMarmita.listInOrderId();
                for (PedidoHasMarmita p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getPedidoHasMarmitaPK().getPedidoIdPedido() + "</td>"
                            + "<td>" + p.getPedidoHasMarmitaPK().getMarmitaIdMarmita() + "</td>"
                            + "<td>" + p.getQtde() + "</td>"
                            + "<td>" + p.getValor() + "</td>"
                            + "<td>" + p.getDesconto() + "</td>"
                            + "</tr>";
                }

            } else {
                inserir(idPedido, idMarmita, qtde, valor, desconto);

                List<PedidoHasMarmita> lista = daoPedidoHasMarmita.listInOrderId();
                for (PedidoHasMarmita p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getPedidoHasMarmitaPK().getPedidoIdPedido() + "</td>"
                            + "<td>" + p.getPedidoHasMarmitaPK().getMarmitaIdMarmita() + "</td>"
                            + "<td>" + p.getQtde() + "</td>"
                            + "<td>" + p.getValor() + "</td>"
                            + "<td>" + p.getDesconto() + "</td>"
                            + "</tr>";
                }

            }

            request.getSession().setAttribute("resultado", tabela);
            response.sendRedirect(request.getContextPath() + "/paginas/cadastroItensPedido.jsp");
            idPedido = "";
            idMarmita = "";
            valor = "";
            qtde = "";
            desconto = "";
        }
    }

    public void inserir(String idPedido, String idMarmita, String qtde, String valor, String desconto) {
        DAOs.DAOPedidoHasMarmita daoPedidoHasMarmita = new DAOs.DAOPedidoHasMarmita();
        PedidoHasMarmita pedidoHasMarmita = new PedidoHasMarmita();
        PedidoHasMarmitaPK pedidoHasMarmitaPK = new PedidoHasMarmitaPK();
        Date dataConvert = null;
        pedidoHasMarmitaPK.setPedidoIdPedido(Integer.valueOf(idPedido));
        pedidoHasMarmitaPK.setMarmitaIdMarmita(Integer.valueOf(idMarmita));
        pedidoHasMarmita.setPedidoHasMarmitaPK(pedidoHasMarmitaPK);
        pedidoHasMarmita.setValor(Double.valueOf(valor));
        pedidoHasMarmita.setQtde(Integer.valueOf(qtde));
        pedidoHasMarmita.setDesconto(Double.valueOf(desconto));
        daoPedidoHasMarmita.inserir(pedidoHasMarmita);
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
