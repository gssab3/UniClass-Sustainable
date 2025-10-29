package it.unisa.uniclass.orari.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import it.unisa.uniclass.orari.service.AnnoDidatticoService;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import it.unisa.uniclass.orari.service.RestoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "getResto", value = "/getResto")
public class getResto extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter printWriter = response.getWriter();

        String corsoLaurea = request.getParameter("corsoLaurea");
        CorsoLaureaService corsoLaureaService = new CorsoLaureaService();
        CorsoLaurea corsoL = corsoLaureaService.trovaCorsoLaurea(corsoLaurea);

        JSONArray jsonArray = new JSONArray();

        RestoService restoService = new RestoService();

        List<Resto> resti = restoService.trovaRestiCorsoLaurea(corsoL);


        for(Resto resto : resti) {
            JSONObject restoJson = new JSONObject();
            restoJson.put("id", resto.getId());
            restoJson.put("nome", resto.getNome());
            jsonArray.put(restoJson);
        }


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        printWriter.println(jsonArray.toString());
        printWriter.flush();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}