/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package example.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Класс HelloServlet
 */
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest reg, HttpServletResponse resp) throws ServletException, IOException {
resp.getWriter().write("hello World");
	}
}