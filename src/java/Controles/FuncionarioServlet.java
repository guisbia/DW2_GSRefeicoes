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
import DAOs.DAOFuncionario;
import DAOs.DAOStatusFuncionario;
import Entidades.Funcionario;
import Entidades.StatusFuncionario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bianc
 */
@WebServlet(name = "FuncionarioServlet", urlPatterns = {"/funcionario"})
public class FuncionarioServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            DAOFuncionario daoFuncionario = new DAOFuncionario();
            Funcionario funcionario = new Funcionario();
            String tabela = "";
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String salario = request.getParameter("salario");
            String telefone = request.getParameter("telefone");
            String dataInicio = request.getParameter("dataInicio");
            String nascimento = request.getParameter("nascimento");
            String status = request.getParameter("status");

            if (request.getParameter("id") == "" || request.getParameter("id") == null) {
                List<Funcionario> lista = daoFuncionario.listInOrderId();
                for (Funcionario p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdFuncionario() + "</td>"
                            + "<td>" + p.getNomeFuncionario() + "</td>"
                            + "<td>" + p.getSalarioFuncionario() + "</td>"
                            + "<td>" + p.getTelefoneFuncionario() + "</td>"
                            + "<td>" + sdf.format(p.getDataInicioFuncionario()) + "</td>"
                            + "<td>" + sdf.format(p.getNascimentoFuncionario()) + "</td>"
                            + "<td>" + p.getStatusFuncionarioIdStatus().getNomeStatus() + "</td>"
                            + "</tr>";
                }
            } else {
                inserir(id, nome, salario, telefone, dataInicio, nascimento, status);

                List<Funcionario> lista = daoFuncionario.listInOrderId();
                for (Funcionario p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdFuncionario() + "</td>"
                            + "<td>" + p.getNomeFuncionario() + "</td>"
                            + "<td>" + p.getSalarioFuncionario() + "</td>"
                            + "<td>" + p.getTelefoneFuncionario() + "</td>"
                            + "<td>" + sdf.format(p.getDataInicioFuncionario()) + "</td>"
                            + "<td>" + sdf.format(p.getNascimentoFuncionario()) + "</td>"
                            + "<td>" + p.getStatusFuncionarioIdStatus().getNomeStatus() + "</td>"
                            + "</tr>";
                }
            }

            request.getSession().setAttribute("resultado", tabela);
            response.sendRedirect(request.getContextPath() + "/paginas/listaFuncionario.jsp");
            id = "";
            nome = "";
            salario = "";
            telefone = "";
            dataInicio = "";
            nascimento = "";
            status = "";
        }
    }

    public void inserir(String id, String nome, String salario, String telefone, String dataInicio, String nascimento, String status) {
        DAOFuncionario daoFuncionario = new DAOFuncionario();
        DAOStatusFuncionario daoStatusFuncionario = new DAOStatusFuncionario();
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(Integer.valueOf(id));
        funcionario.setNomeFuncionario(nome);
        funcionario.setSalarioFuncionario(Double.valueOf(salario));
        funcionario.setTelefoneFuncionario(telefone);
        try {
            funcionario.setDataInicioFuncionario(sdf.parse(dataInicio));
        } catch (ParseException ex) {
            Logger.getLogger(FuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            funcionario.setNascimentoFuncionario(sdf.parse(nascimento));
        } catch (ParseException ex) {
            Logger.getLogger(FuncionarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        funcionario.setStatusFuncionarioIdStatus(daoStatusFuncionario.obter(Integer.valueOf(status)));
        daoFuncionario.inserir(funcionario);
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
