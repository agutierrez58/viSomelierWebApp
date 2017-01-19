package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import bdatos.DBManager2;
import net.sourceforge.blowfishj.BlowfishEasy;

public class LoginAction {

    public static String think(HttpServletRequest request) throws Exception {

		//recogemos parametros
        String entrenador=request.getParameter("entrenador");
        String password=request.getParameter("password");

		String page;
		BlowfishEasy fish=new BlowfishEasy("secret".toCharArray());
        DBManager2 dbman=new DBManager2();
        if((Integer.parseInt(dbman.executeValue("select count(*) from lebb_entrenadores where entrenador='"+entrenador+"'"))!=0)&&(fish.decryptString(dbman.executeValue("select password from lebb_entrenadores where entrenador='"+entrenador+"'")).equals(password))) {
			HttpSession session=request.getSession();
			session.setAttribute("entrenador",dbman.executeValue("select entrenador from lebb_entrenadores where entrenador='"+entrenador+"'"));
			//session.setAttribute("password",password);
		    page="main.do";
        } else {
            request.setAttribute("error","entrenador o password incorrectos.");
			page="login.jsp";
        }
        dbman.close();
        return page;
    }

}
