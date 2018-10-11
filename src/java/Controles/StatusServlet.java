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
import DAOs.DAOStatusFuncionario;
import Entidades.StatusFuncionario;
import java.util.List;

/**
 *
 * @author bianc
 */
@WebServlet(name = "StatusServlet", urlPatterns = {"/status"})
public class StatusServlet extends HttpServlet {

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
            DAOStatusFuncionario daoStatusFuncionario = new DAOStatusFuncionario();
            StatusFuncionario statusFuncionario = new StatusFuncionario();
            String tabela = "";
            String id = request.getParameter("id");
            String status = request.getParameter("status");
            
            if (request.getParameter("id") == "" || request.getParameter("id") == null) {
                List<StatusFuncionario> lista = daoStatusFuncionario.listInOrderId();
                for (StatusFuncionario p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdStatus() + "</td>"
                            + "<td>" + p.getNomeStatus() + "</td>"
                            + "</tr>";
                }
            } else {
                inserir(id, status);
                
                List<StatusFuncionario> lista = daoStatusFuncionario.listInOrderId();
                for (StatusFuncionario p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdStatus() + "</td>"
                            + "<td>" + p.getNomeStatus() + "</td>"
                            + "</tr>";
                }
            }
            
            request.getSession().setAttribute("resultado", tabela);
            response.sendRedirect(request.getContextPath() + "/paginas/listaStatus.jsp");
            
            id = "";
            status = "";
            
        }
    }
    
    public void inserir(String id, String status) {
        DAOStatusFuncionario daoStatusFuncionario = new DAOStatusFuncionario();
        StatusFuncionario statusFuncionario = new         StatusFuncionario();
        
        statusFuncionario.setIdStatus(Integer.valueOf(id));
        statusFuncionario.setNomeStatus(status);
        daoStatusFuncionario.inserir(statusFuncionario);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    public String getServletInfo() {
        return "Short description";
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
}
