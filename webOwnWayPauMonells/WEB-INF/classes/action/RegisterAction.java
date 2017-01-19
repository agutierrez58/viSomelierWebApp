package action;

import javax.servlet.http.HttpServletRequest;
import bdatos.DBManager2;
import net.sourceforge.blowfishj.BlowfishEasy;

public class RegisterAction {

    public static String think(HttpServletRequest request) throws Exception {

        String entrenador=request.getParameter("entrenador");
        String password=request.getParameter("password");
        //String email=request.getParameter("email");

		String page;
		BlowfishEasy fish=new BlowfishEasy("secret".toCharArray());
        DBManager2 dbman=new DBManager2();
        if(Integer.parseInt(dbman.executeValue("select count(*) from lebb_entrenadores where entrenador='"+entrenador+"'"))==0) {
            dbman.execute("insert into lebb_entrenadores values ('"+entrenador+"','"+fish.encryptString(password)+"')"); //,'"+email+"')");
            request.setAttribute("error","Registro OK");
            page="login.jsp";
        } else {
            request.setAttribute("error","el entrenador ya existe.");
            page="register.jsp";
        }
        dbman.close();
        return page;
    }

}

