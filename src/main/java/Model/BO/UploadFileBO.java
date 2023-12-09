package Model.BO;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import Model.Bean.user;
import Model.DAO.UploadFileDAO;
import _CONSTAINT.CONSTAINT;

public class UploadFileBO implements Runnable {
	HttpServletRequest request;
	user user;

	public UploadFileBO(HttpServletRequest request, user user) {
		this.request = request;
		this.user = user;
	}

	@Override
	public void run() {
		try {
			for (Part part : request.getParts()) {
				if (part.getName().equals("files_upload")) {
					String filename = extractFileName(part);
					filename = new File(filename).getName();

					try {
						File file = new File(getFolderUpload(), filename);
						Files.copy(part.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);
		                UploadFileDAO.upload(filename, user, CONSTAINT.PROCESSING);
					} catch (Exception e) {
						e.printStackTrace();
		                UploadFileDAO.upload(filename, user, CONSTAINT.UPLOAD_ERROR);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		new ConvertFileBO(user).run();
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	private File getFolderUpload() {
		File folderUpload = new File(System.getProperty("user.home") + "/pdfs");
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}

}