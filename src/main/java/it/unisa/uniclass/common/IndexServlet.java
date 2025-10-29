package it.unisa.uniclass.common;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import it.unisa.uniclass.orari.service.CorsoService;
import it.unisa.uniclass.orari.service.RestoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.io.IOException;
import java.util.List;

@WebServlet("/Home")
public class IndexServlet extends HttpServlet {

    CorsoLaureaService corsoLaureaService = new CorsoLaureaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        corsoLaureaService = new CorsoLaureaService();
        List<CorsoLaurea> corsi = corsoLaureaService.trovaTutti();
        System.out.println(corsi);
        request.setAttribute("corsi", corsi);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
