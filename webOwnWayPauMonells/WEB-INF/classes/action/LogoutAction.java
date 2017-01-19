package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutAction {

    public static String think(HttpServletRequest request) throws Exception {

		HttpSession session=request.getSession();
		session.setAttribute("entrenador","guest");
		//session.setAttribute("password","guest");

        return "login.do";
    }

}
