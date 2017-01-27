package libfunctions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class containing session management related methods for use by servlets.
 * @author cavan
 */
public class SessionManagement {

	/**
	 * Master request handler for session management functions.
	 * @param req HTTPS request. If not HTTPS, connection rejected. May disable this check for initial testing.
	 * @param res HTTPS response.
	 * @return boolean value representing whether handling operations were successfully performed.
	 */
	public static boolean requestHandler(HttpServletRequest req, HttpServletResponse res){
		Cookie rev = null;
		
		//Rejects HTTP requests. Only accepts HTTPS
		if(!req.isSecure())
			return false;
		
		
		Cookie[] cookies = req.getCookies();
		
		for(Cookie c : cookies){
			if(c.getName().equals("Revature"))
				{rev = c;
				break;
				}
		}
		
		
		
		return true;
	}
	
	
	
	
	//authenticate cookie
	
	//create cookie
	
	//
	
	
	
}
