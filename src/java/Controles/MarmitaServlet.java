/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAOs.DAOMarmita;
import DAOs.DAOPratoPrincipal;
import DAOs.DAOTamanhoMarmita;
import Entidades.Marmita;
import java.util.List;

/**
 *
 * @author bianc
 */
@WebServlet(name = "MarmitaServlet", urlPatterns = {"/marmita"})
public class MarmitaServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            DAOMarmita daoMarmita = new DAOMarmita();
            Marmita marmita = new Marmita();
            String tabela = "";
            String id = request.getParameter("id");
            String pratoPrinc = request.getParameter("prato");
            String tamanhoMarmita = request.getParameter("tamanho");
            String status = request.getParameter("status");
            // List<Marmita> lista = daoMarmita.listInOrderId();
            if (request.getParameter("id") == "" || request.getParameter("id") == null) {
                List<Marmita> lista = daoMarmita.listInOrderId();
                for (Marmita p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdMarmita() + "</td>"
                            + "<td>" + p.getPratoPrincipalIdPratoPrincipal().getNomePratoPrincipal() + "</td>"
                            + "<td>" + p.getTamanhoMarmitaIdTamanhoMarmita().getNomeTamanhoMarmita() + "</td>"
                            + "<td>" + p.getStatus() + "</td>"
                            + "</tr>";
                }
            } else {
                inserir(id, pratoPrinc, tamanhoMarmita, status);

                List<Marmita> lista = daoMarmita.listInOrderId();
                for (Marmita p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdMarmita() + "</td>"
                            + "<td>" + p.getPratoPrincipalIdPratoPrincipal().getNomePratoPrincipal() + "</td>"
                            + "<td>" + p.getTamanhoMarmitaIdTamanhoMarmita().getNomeTamanhoMarmita() + "</td>"
                            + "<td>" + p.getStatus() + "</td>"
                            + "</tr>";
                }

            }

            request.getSession().setAttribute("resultado", tabela);
            response.sendRedirect(request.getContextPath() + "/paginas/listaMarmita.jsp");
            
            id = "";
            tamanhoMarmita = "";
            pratoPrinc = "";
            status = "";
            

        }
    }
    
    public void inserir(String id, String prato, String tamanho, String status) {
        DAOMarmita daoMarmita = new DAOMarmita();
        DAOPratoPrincipal daoPratoPrincipal = new DAOPratoPrincipal();
        DAOTamanhoMarmita daoTamanhoMarmita = new DAOTamanhoMarmita();
        Marmita marmita = new Marmita();
        marmita.setIdMarmita(Integer.valueOf(id));
        marmita.setPratoPrincipalIdPratoPrincipal(daoPratoPrincipal.obter(Integer.valueOf(prato)));
        marmita.setTamanhoMarmitaIdTamanhoMarmita(daoTamanhoMarmita.obter(Integer.valueOf(tamanho)));
        marmita.setStatus(Short.valueOf(status));
        daoMarmita.inserir(marmita);
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
