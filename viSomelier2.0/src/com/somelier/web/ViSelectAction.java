package com.somelier.web;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.*;

import com.somelier.model.ViExpert;

import javax.servlet.*;
import java.io.*;

public class ViSelectAction extends HttpServlet {
	// "Controller"
	String PAGINA_MOSTRA_VINS="/mostraVins.jsp";

	// Comunicació HTTP amb el "Web Container"
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("Vi Selection Advice<br>");
		String c = request.getParameter("color");
		
		ViExpert suggereixVi = new ViExpert();
		
		/*
		// NOTA:(canvis en el servlet automaticament es recompilen i carreguen ("Reloading")) 
		//      Prova de modificar la línea de suggerències quan hagis arrencat l'app!
		out.println("Suggerències:");  
		List marques = suggereixVi.getBrands(c);
		Iterator it = marques.iterator();
		while (it.hasNext()){			
			out.println("<br> Prova: " + it.next());
		} */
		// Recuperem la informació del nostre sistema expert
		//   (en aquest cas el PlainOld Java Object (POJO) ViExpert)
		//    Podria de manera interna atacar a una base de dades, arxiu XML, etc..
		request.setAttribute("llistaVins", suggereixVi.getBrands(c));
		
		RequestDispatcher view = request.getRequestDispatcher(PAGINA_MOSTRA_VINS);
		view.forward(request, response);
		
	}
}
