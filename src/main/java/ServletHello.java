/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

/**
 * Класс ServletHello
 */
public class ServletHello extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printWriter = resp.getWriter();
		printWriter.write("hello World");
	}
}