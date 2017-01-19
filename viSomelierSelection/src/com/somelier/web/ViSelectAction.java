package com.somelier.web;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.*;

import com.somelier.model.ViExpert;

import javax.servlet.*;
import java.io.*;

public class ViSelectAction extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		out.print("Vi Selection Advice<br>");
		String c = request.getParameter("color");
		
		ViExpert suggereixVi = new ViExpert();
		
		// Suggerències
		out.println("Suggerències:");
		List marques = suggereixVi.getBrands(c);
		Iterator it = marques.iterator();
		while (it.hasNext()){			
			out.println("<br> Prova: " + it.next());
		}

	}
}
