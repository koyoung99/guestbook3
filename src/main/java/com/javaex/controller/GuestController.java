package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestVo;

@WebServlet("/gtc")
public class GuestController extends HttpServlet {
	
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String action = request.getParameter("action");
			
			if("aform".equals(action)) {
				
				
				GuestDao guestDao = new GuestDao();
				
				List<GuestVo> guestList = guestDao.guestSelect();
				
				request.setAttribute("guestList", guestList);
				
				WebUtil.forward(request, response, "/addList.jsp");
				
				
			}else if("insert".equals(action)) {//등록
				
				String name= request.getParameter("name");
				String pw= request.getParameter("password");
				String content= request.getParameter("content");
				String regDate = request.getParameter("reg_date");
				
				GuestVo guestVo = new GuestVo(name, pw, content, regDate);
				
				GuestDao guestDao = new GuestDao();
				
				guestDao.guestInsert(guestVo);
				
				WebUtil.redirect(request, response, "/guestbook3/gtc?action=aform");
				
				
			}else if("delete".equals(action)) {
				System.out.println("delete");
				
				int no = Integer.parseInt(request.getParameter("no"));
				
				request.setAttribute("no", no);

				
				WebUtil.forward(request, response, "/deleteForm.jsp");
				
				
			}else if("gdelete".equals(action)) {
				int no = Integer.parseInt(request.getParameter("no"));
				String pw=request.getParameter("password");
				
				
				
				
				// db사용
				GuestDao guestDao = new GuestDao();

				// 삭제
				guestDao.guestDelete(no, pw);
				
				
				WebUtil.forward(request, response, "/guestbook3/gtc?action=aform");
			}
			
			
		}


		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

	}