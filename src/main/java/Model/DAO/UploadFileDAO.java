package Model.DAO;

import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.Statement;

import Model.Bean.user;

public class UploadFileDAO {

	 public static void upload(String filename, user user, int fstatus) {
	        try (Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pdf2word", "root", "");
	             Statement stmt = con.createStatement()) {

	            String sql;
	            if (!isFileExist(filename, stmt)) {
	                sql = "INSERT INTO `uploadfile`(`uid`, `fname`, `fstatus`) VALUES (" + user.getUid() + ",'" + filename
	                        + "'," + fstatus + ")";
	            } else {
	                sql = "UPDATE uploadfile SET fstatus = " + fstatus + " WHERE fname = '" + filename + "'";
	            }
	            stmt.execute(sql);
	        } catch (Exception e) {
	            e.printStackTrace();
        }
	    }

	 private static boolean isFileExist(String filename, Statement stmt) {
	        try {
	            String sql = "SELECT * FROM uploadfile WHERE fname = '" + filename + "'";
	            ResultSet rs = stmt.executeQuery(sql);
	            return rs.next();
	        } catch (Exception e) {
	            return false;
	        }
	    }

}