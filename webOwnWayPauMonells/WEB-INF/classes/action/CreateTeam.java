package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.ResultSet2;
import bloodbowl.Parser;


public class CreateTeam {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
    if(request.getSession().getAttribute("entrenador")==null) request.getSession().setAttribute("entrenador","guest");
        String entrenador=(String)request.getSession().getAttribute("entrenador");
        String raza=request.getParameter("raza");

		//cuerpo
		ResultSet2 rsetJugadores=bloodbowl.Parser.parseJugadores(raza);
		String otros=Parser.parseOtros(raza);
        request.setAttribute("raza",raza);
        request.setAttribute("rsetJugadores",rsetJugadores);
        request.setAttribute("preciorerolls",otros.split("#")[0]);
        request.setAttribute("medicodisp",otros.split("#")[1]);

        return "createteam.jsp";
    }

}
