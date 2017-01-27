package libfunctions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class containing session management related methods for use by servlets.
 * @author cavan
 */
public class SessionManagement {

	private HashMap<String, RULUser> authmap;
	
	private SessionManagement manager;
	
	private SessionManagement(){
		authmap = new HashMap<String, RULUser>();
	}
	
	public SessionManagement getSessionManager(){
		if(manager==null)
			manager = SessionManagement();
		return manager;
	}
	
	
	
}
