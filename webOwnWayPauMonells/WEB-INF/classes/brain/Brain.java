package brain;


import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


public class Brain extends HttpServlet {


    private ServletContext sc;


    public void init(ServletConfig config) throws ServletException {
        sc=config.getServletContext();
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        forward(request, response);
        } catch(Exception e) {
			throw new ServletException(e);
		}
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        forward(request, response);
        } catch(Exception e) {
			throw new ServletException(e);
		}
    }


    public void forward (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
		Element wish=null;
        Document doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(Brain.class.getResourceAsStream("../../wishes.xml"));
        NodeList nl;
        Element el;
        nl=doc.getDocumentElement().getElementsByTagName("wish");
        for(int i=0;i<nl.getLength();i++) {
            el=(Element)nl.item(i);
            if(request.getServletPath().equals(el.getAttribute("name")+".do"))
				wish=el;
        }
		if(wish.getAttribute("class").equals("")) {
			request.setAttribute("error","");
			sc.getRequestDispatcher("/"+wish.getAttribute("page")).forward(request,response);
		} else {
			String destiny="";
			destiny=(String)Class.forName(wish.getAttribute("class")).getMethod("think",HttpServletRequest.class).invoke(wish.getAttribute("class"),new Object[]{request});
			if((destiny.matches(".+\\.jsp"))||(destiny.matches(".+\\.jsp\\?.*")))
				sc.getRequestDispatcher("/"+destiny).forward(request,response);
			else
				response.sendRedirect(destiny);
		}
    }


}

