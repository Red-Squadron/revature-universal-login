package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		
		UserDAO dao = getUserDAO();
		Boolean isValid = dao.validateLogin(tkn.nextToken(), tkn.nextToken());
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write(isValid.toString());
		out.flush();
		out.close();
	}

}
