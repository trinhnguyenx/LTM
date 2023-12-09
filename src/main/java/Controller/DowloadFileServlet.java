package Controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.BO.DowloadFileBO;
import Model.Bean.uploadfile;

@WebServlet("/DownloadFileServlet")
public class DowloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DowloadFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fid = 0;
		
		try {
			fid = Integer.parseInt(request.getParameter("fid"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(fid != 0) {
			uploadfile file = DowloadFileBO.GetFile(fid);
			
			String scrpath = GetFolderPath("docxs").getAbsolutePath() + File.separator + file.getFname();
			java.nio.file.Path path = Paths.get(scrpath);
			byte[] data = Files.readAllBytes(path);
			
			response.setContentType("application/octet-stream");
		    response.setHeader("Content-disposition", "attachment; filename=" + file.getFname());
		    response.setContentLength(data.length);
		    InputStream inputStream = new BufferedInputStream(new ByteArrayInputStream(data));
		    
		    OutputStream outStream = response.getOutputStream();
		    byte[] buffer = new byte[4096];
		    int bytesRead = -1;
		    
		    while ((bytesRead = inputStream.read(buffer)) != -1) {
		      outStream.write(buffer, 0, bytesRead);
		    }
		    
		    inputStream.close();
		    outStream.close();
			
		}else {
			request.getSession().setAttribute("message", "File không tồn tại hoặc đã bị xóa!");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private File GetFolderPath(String folder) {
	    File folderUpload = new File(System.getProperty("user.home") + "/" + folder);
	    if (!folderUpload.exists()) {
	      folderUpload.mkdirs();
	    }
	    return folderUpload;
	  }

}