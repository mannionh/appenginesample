package com.howard.test;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppEngineTestServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			resp.getWriter().write("Testing 1, 2, 3, 5..");
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		
	}

}
