package Controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.BO.GetUserBO;
import Model.BO.UploadFileBO;
import Model.Bean.user;

@WebServlet("/UploadFileServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 500) // 500MB
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadFileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        if (request.getPart("files_upload").getSize() != 0) {
	            String username = request.getParameter("username");
	            user user = GetUserBO.GetUser(username);
	            Thread t = new Thread(new UploadFileBO(request, user));
	            t.start();
	            request.getSession().setAttribute("message", "Uploading...");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.getSession().setAttribute("message", "Upload failed. Please check the logs for details.");
	    } finally {
	        response.sendRedirect("HomePage.jsp");
	    }
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}

}