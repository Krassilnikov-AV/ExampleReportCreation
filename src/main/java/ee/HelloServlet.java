/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package ee;

import com.ibm.useful.http.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.logging.*;

/**
 * Класс HelloServlet
 */
//@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		if (request.getContentType().contains("multipart/form-data")) {
			PostData multidata = new PostData(request);
			String path = multidata.getParameter("path");
			System.out.println("Server tries to save your file into " + path);
			FileData tempFile = multidata.getFileData("fileToUpload");
			if (tempFile != null) {
				saveFile(tempFile, path);
			}
		}
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet UpLoadServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet UpLoadServlet at " + request.getContextPath() + "</h1>");
			out.println("</body>");
			out.println("</html>");
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

	private void saveFile(FileData tempFile, String path) {
		String filename = path + File.separator + tempFile.getFileName();
		File f = new File(filename);
		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(f);
		} catch (FileNotFoundException ex) {
			Logger.getLogger(HelloServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (fos != null) {
			try {
				fos.write(tempFile.getByteData());     // паолучение данных из файла
				fos.close();
			} catch (IOException ex) {
				Logger.getLogger(HelloServlet.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

}