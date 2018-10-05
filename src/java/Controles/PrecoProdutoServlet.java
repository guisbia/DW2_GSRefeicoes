/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controles;

import DAOs.DAOMarmita;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import DAOs.DAOPrecoProduto;
import DAOs.DAOTamanhoMarmita;
import Entidades.Marmita;
import Entidades.PrecoProduto;
import Entidades.PrecoProdutoPK;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bianc
 */
@WebServlet(name = "PrecoProdutoServlet", urlPatterns = {"/precoProduto"})
public class PrecoProdutoServlet extends HttpServlet {

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
            DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
            DAOTamanhoMarmita daoTamanhoMarmita = new DAOTamanhoMarmita();
            PrecoProduto precoProduto = new PrecoProduto();
            String tabela = "";
            String data = request.getParameter("data");
            String idMarmita = request.getParameter("idTamanhoMarmita");
            String preco = request.getParameter("preco");
            System.out.println("ID DA MARMITA AQUI:" + idMarmita);

            if (request.getParameter("data") == "" || request.getParameter("data") == null) {
                List<PrecoProduto> lista = daoPrecoProduto.listInOrderId();
                for (PrecoProduto p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + sdf.format(p.getPrecoProdutoPK().getDataPrecoProduto()) + "</td>"
                            + "<td>" + daoTamanhoMarmita.obter(p.getPrecoProdutoPK().getTamanhoMarmitaIdTamanhoMarmita()).getNomeTamanhoMarmita()+ "</td>"
                            + "<td>" + p.getPreco() + "</td>"
                            + "</tr>";
                }
            } else {
                DAOMarmita daoMarmita = new DAOMarmita();
                inserir(data, idMarmita, preco);
                List<PrecoProduto> lista = daoPrecoProduto.listInOrderId();
                Marmita marmita = new Marmita();
                marmita = daoMarmita.obter(Integer.valueOf(idMarmita));
                for (PrecoProduto p : lista) {
                    tabela += "<tr class=\"gradeA\">"
                            + "<td>" + sdf.format(p.getPrecoProdutoPK().getDataPrecoProduto()) + "</td>"
                            + "<td>" + daoTamanhoMarmita.obter(p.getPrecoProdutoPK().getTamanhoMarmitaIdTamanhoMarmita()).getNomeTamanhoMarmita()+ "</td>"
                            + "<td>" + p.getPreco() + "</td>"
                            + "</tr>";
                }
            }

            request.getSession().setAttribute("resultado", tabela);
            response.sendRedirect(request.getContextPath() + "/paginas/listaPrecoProduto.jsp");
            data = "";
            idMarmita = "";
            preco = "";
        }
    }

    public void inserir(String data, String id, String preco) {
        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
        PrecoProduto precoProduto = new PrecoProduto();
        PrecoProdutoPK precoProdutoPK = new PrecoProdutoPK();
        Date dataConvert = null;
        int idMarmita = Integer.valueOf(id);
        try {
            // precoProdutoPK.setDataPrecoProduto(sdf.parse(data));
            dataConvert = sdf.parse(data);
            precoProdutoPK.setDataPrecoProduto(dataConvert);
            precoProduto.setPrecoProdutoPK(new PrecoProdutoPK(dataConvert, idMarmita));
        } catch (ParseException ex) {
            Logger.getLogger(PrecoProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        precoProdutoPK = new PrecoProdutoPK(dataConvert, idMarmita);
        //precoProdutoPK.setTamanhoMarmitaIdTamanhoMarmita(Integer.valueOf(idMarmita;
        precoProduto.setPrecoProdutoPK(precoProdutoPK);
        precoProduto.setPreco(Double.valueOf(preco));
        System.out.println("PREÇO PRODUTO: " + precoProduto.getPrecoProdutoPK().getDataPrecoProduto() + precoProduto.getPrecoProdutoPK().getTamanhoMarmitaIdTamanhoMarmita() + precoProduto.getTamanhoMarmita());
        daoPrecoProduto.inserir(precoProduto);
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
