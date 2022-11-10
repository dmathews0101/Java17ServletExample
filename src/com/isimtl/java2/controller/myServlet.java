package com.isimtl.java2.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class myServlet
 */
@WebServlet("/myServlet")
public class myServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Cookie ColorCookie;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public myServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title>Color Form</title></head>");
		response.setContentType("text/html");
		
		Cookie[] cookies= request.getCookies();
		if(cookies==null)
		{
			out.println("<form action='myServlet' method='post'>"
					+ "UserName : <input type='text' name='firstname'><br>"
					+ "Fav Color : <input type='text' name='color'><br>"
					+ "<input type='submit' value='submit'></form>");
			
		}
		else
		{
			for (int i=0; i<cookies.length ; i++) 
			{
				String nameCookie = cookies[i].getName();
				if (nameCookie.equals("color")) 
				{
					out.println("<body bgcolor="+cookies[i].getValue());
					cookies[i].setMaxAge(-1);
				}
				if (nameCookie.equals("firstname")) 
				{

					out.println("<h3>Hey "+cookies[i].getValue()+"</h3>");
					cookies[i].setMaxAge(-1);

				}				
			}
		}				
		out.println("</body></html>");				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ColorCookie=new Cookie("username",request.getParameter("firstname"));
		ColorCookie.setMaxAge(60*2);
		response.addCookie(ColorCookie);

		
		ColorCookie=new Cookie("color",request.getParameter("color"));
		ColorCookie.setMaxAge(60*2);
		response.addCookie(ColorCookie);
		
		doGet(request, response);
	}

}
