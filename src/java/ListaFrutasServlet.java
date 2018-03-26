/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ice
 */
@WebServlet(urlPatterns = {"/frutas.html", "/alfabetica.html", "/numeroletras.html"})
public class ListaFrutasServlet extends HttpServlet {

    private List<String> frutas = new ArrayList<String>() {
        {
            add("Banana");
            add("Maça");
            add("Uva");
            add("Melancia");
            add("Laranja");
            add("Limão");
        }
    };

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListaFrutasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListaFrutasServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListaFrutasServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de Frutas:</h1>");
            out.println("<ul>");

            if (request.getServletPath().equals("/alfabetica.html")) {
                java.util.Collections.sort(frutas);

                for (int i = 0; i < frutas.size(); i++) {
                    out.println("<li>" + frutas.get(i) + "</li>");
                }
            } else if (request.getServletPath().equals("/numeroletras.html")) {
                List<String> aux = new ArrayList<String>(frutas);
                
                while (!aux.isEmpty()) {
                    String maior = "";
                    
                    for (String auxString : aux) {
                        if (auxString.length() > maior.length()) {
                            maior = auxString;
                        }
                    }
                    
                    out.println("<li>" + maior + "</li>");
                    
                    while (aux.contains(maior)) {
                        aux.remove(maior);
                    }
                }
            } else if (request.getServletPath().equals("/frutas.html")) {
                ordenarAleatorio(frutas);

                for (int i = 0; i < frutas.size(); i++) {
                    out.println("<li>" + frutas.get(i) + "</li>");
                }
            }

            out.println("</ul>");
            out.println("<a href='alfabetica.html'>Ordem Alfabética</a>");
            out.println("<a href='numeroletras.html'>Número de Letras</a>");
            out.println("<a href='frutas.html'>Aleatório</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static void ordenarAleatorio(List<String> array) {
        Random rnd = ThreadLocalRandom.current();

        for (int i = array.size() - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);

            String aux = array.get(index);
            array.set(index, array.get(i));
            array.set(i, aux);
        }
    }
}
