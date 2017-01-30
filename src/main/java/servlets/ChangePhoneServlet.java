package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

/**
 * Servlet implementation class ChangePhoneServlet
 */
public class ChangePhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePhoneServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO uDao = UserDAO.getUserDAO();
		InputStream is = request.getInputStream();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] buf = new byte[32];
		int r = 0;
		while(r >= 0)
		{
			r = is.read(buf);
			if(r >= 0)
				os.write(buf, 0, r);
		}
		String in = new String(os.toByteArray(), "UTF-8");
		
		StringTokenizer tkn = new StringTokenizer(in, ":");

		if(uDao.updatePhone(tkn.nextToken(), tkn.nextToken())){
			System.out.println("Phone Change Success!");
		} else {
			System.out.println("Phone Change Failed!");
			response.sendError(0);
		}
	}
}
