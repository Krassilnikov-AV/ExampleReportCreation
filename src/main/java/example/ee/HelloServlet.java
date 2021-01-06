/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package example.ee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

/**
 * Класс HelloServlet
 */
//@WebServlet("HelloServlet")
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		printWriter.write("hello World");
	}
}