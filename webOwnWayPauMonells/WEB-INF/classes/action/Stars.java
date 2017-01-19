package action;

import javax.servlet.http.HttpServletRequest;
import bloodbowl.Parser;

public class Stars {

    public static String think(HttpServletRequest request) throws Exception {
        request.setAttribute("stars",Parser.getStars());
        return "stars.jsp";
    }

}
