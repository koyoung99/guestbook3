package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.util.WebUtil;

@WebServlet("/gtc")
public class GuestController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		
		if ("aform".equals(action)) {
			WebUtil.forward(request, response, "/addList.jsp");
			
			int no=Integer.parseInt(request.getParameter("no"));
			String name=request.getParameter("name");
			String pw=request.getParameter("password");
			String content=request.getParameter("content");
			String reg_date=request.getParameter("reg_date");
			
			GuestDao guestDao=new GuestDao();
			
			
		}else if("insert".equals(action)) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}

}
