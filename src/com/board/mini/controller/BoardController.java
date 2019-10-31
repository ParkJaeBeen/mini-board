package com.board.mini.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.mini.service.BoardService;


public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(7);
		BoardService bs = new BoardService();
		String path = "/views/board/list";
		
		if("list".equals(cmd))
		{
			request.setAttribute("btList", bs.getBoardList());
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cmd = uri.substring(7);
		BoardService bs = new BoardService();
		String path = "/views/board/list";
		String msg = "저장 완료";
		if("insert".equals(cmd))
		{
			Map<String,String> board = new HashMap<>();
			board.put("btTitle",request.getParameter("btTitle"));
			board.put("btContent",request.getParameter("btContent"));
			HttpSession hs = request.getSession();
			Map<String,Object> user = (Map<String,Object>)hs.getAttribute("user");
			board.put("utNum",user.get("utNum").toString());
			int rs = bs.setBoardList(board);
			if(rs != 1)
			{
				path="/views/board/insert";
				msg = "저장 실패";
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}
