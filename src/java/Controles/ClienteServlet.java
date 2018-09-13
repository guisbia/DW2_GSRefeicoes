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
import DAOs.DAOCliente;
import Entidades.Cliente;
import java.util.List;

/**
 *
 * @author bianc
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/cliente"})
public class ClienteServlet extends HttpServlet {

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
            DAOCliente daoCliente = new DAOCliente();
            Cliente cliente = new Cliente();
            String tabela = "";
            String id = request.getParameter("id");
            String nome = request.getParameter("nome");
            String telefone = request.getParameter("telefone");
            String endereco = request.getParameter("endereco");

            if (request.getParameter("id") == "" || request.getParameter("id") == null) {
                List<Cliente> lista = daoCliente.listInOrderId();
                for (Cliente p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdCliente() + "</td>"
                            + "<td>" + p.getNomeCliente() + "</td>"
                            + "<td>" + p.getTelefoneCliente() + "</td>"
                            + "<td>" + p.getEnderecoCliente() + "</td>"
                            + "</tr>";
                }

            }else{
                inserir(id, nome, telefone, endereco);
                
                List<Cliente> lista = daoCliente.listInOrderId();
                for (Cliente p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + p.getIdCliente() + "</td>"
                            + "<td>" + p.getNomeCliente() + "</td>"
                            + "<td>" + p.getTelefoneCliente() + "</td>"
                            + "<td>" + p.getEnderecoCliente() + "</td>"
                            + "</tr>";
                }
                
            }

            request.getSession().setAttribute("resultado", tabela);
            response.sendRedirect(request.getContextPath() + "/paginas/listaCliente.jsp");
            id = "";
            nome = "";
            telefone = "";
            endereco = "";
            
        }
    }
    
    public void inserir (String id, String nome, String telefone, String endereco){
        DAOCliente daoCliente = new DAOCliente();
        Cliente cliente = new Cliente();
        
        cliente.setIdCliente(Integer.valueOf(id));
        cliente.setNomeCliente(nome);
        cliente.setEnderecoCliente(endereco);
        cliente.setTelefoneCliente(telefone);
        daoCliente.inserir(cliente);
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
