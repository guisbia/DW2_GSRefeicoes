/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOTamanhoMarmita;
import Entidades.TamanhoMarmita;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "TamanhoMarmitaServlet", urlPatterns = {"/tamanhoMarmita"})
public class TamanhoMarmitaServlet extends HttpServlet {

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
            DAOTamanhoMarmita daoTamanhoMarmita = new DAOTamanhoMarmita();
            TamanhoMarmita tamanhoMarmita = new TamanhoMarmita();
            String tabela = "";
            String id = request.getParameter("id");
            String tamanho = request.getParameter("tamanho");

            if (request.getParameter("id") == "" || request.getParameter("id") == null) {
                List<TamanhoMarmita> lista = daoTamanhoMarmita.listInOrderId();
                for (TamanhoMarmita p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdTamanhoMarmita() + "</td>"
                            + "<td>" + p.getNomeTamanhoMarmita() + "</td>"
                            + "</tr>";
                }
            } else {
                inserir(id, tamanho);

                List<TamanhoMarmita> lista = daoTamanhoMarmita.listInOrderId();
                for (TamanhoMarmita p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdTamanhoMarmita() + "</td>"
                            + "<td>" + p.getNomeTamanhoMarmita() + "</td>"
                            + "</tr>";
                }
            }

            request.getSession().setAttribute("resultado", tabela);
            response.sendRedirect(request.getContextPath() + "/paginas/listaTamanhoMarmita.jsp");
            id = "";
            tamanho = "";
            
        }
    }
    
     public void inserir (String id, String tamanho){
        DAOTamanhoMarmita daoTamanhoMarmita = new DAOTamanhoMarmita();
        TamanhoMarmita tamanhoMarmita = new TamanhoMarmita();
        
        tamanhoMarmita.setIdTamanhoMarmita(Integer.valueOf(id));
        tamanhoMarmita.setNomeTamanhoMarmita(tamanho);
        daoTamanhoMarmita.inserir(tamanhoMarmita);
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
