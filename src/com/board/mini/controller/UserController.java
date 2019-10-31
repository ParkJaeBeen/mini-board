package com.board.mini.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.mini.service.UserService;

public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String utName = request.getParameter("utName");
		String utId = request.getParameter("utId");
		String utPwd = request.getParameter("utPwd");
		UserService us = new UserService();
		String uri = request.getRequestURI();
		uri = uri.substring(6);
		String url = "/views/user/login";
		String msg = "id,pwd 를 확인해주세요";
		if("login".equals(uri))
		{
			Map<String,Object> user = us.doLogin(utId, utPwd);
			if(user != null)
			{
				HttpSession hs = request.getSession();
				hs.setAttribute("user", user);
				url = "/views/index";
				msg = user.get("utName") + "님 환영합니다";
			}
			
		}
		else if("signup".equals(uri))
		{
			Map<String,Object> rMap = us.doSignup(utName, utId, utPwd);
			if(rMap != null)
			{
				url = (String)rMap.get("url");
				msg = (String)rMap.get("msg");
			}
			else
			{
				url = "/views/user/signup";
				msg = "회원가입 실패, 정보를 다시 확인하세요";
			}
		}
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}

