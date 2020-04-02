package com.lmonkey.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lmonkey.entity.LMONKEY_USER;
import com.lmonkey.service.LMONKEY_USERDao;

/**
 * Servlet implementation class DoUserAdd
 */
@WebServlet("/manage/admin_douseradd")
public class DoUserAdd extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/htm;/Charset=utf-8");
		
		String username = request.getParameter("userName");
		String name = request.getParameter("name");
		String pwd = request.getParameter("password");
//		String rePassword = request.getParameter("rePassword");
		String sex = request.getParameter("sex");
		String year = request.getParameter("birthday");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String address = request.getParameter("address");
		
		LMONKEY_USER u = new LMONKEY_USER(username, name, pwd, sex, year, null, email, mobile, address, 1);
		
		//加入對數據庫的用戶表中
		int count = LMONKEY_USERDao.insert(u);
		if(count>0) {
			response.sendRedirect("admin_user.jsp");
		}
		
//		System.out.println(u);
//		if(true) {
//			response.sendRedirect("admin_user.jsp");
//		} 
			else {
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('Add user fail')");
			out.write("location.href='/manage/admin_useradd.jsp'");
			out.write("</script>");
			
		}
				
	}

}
