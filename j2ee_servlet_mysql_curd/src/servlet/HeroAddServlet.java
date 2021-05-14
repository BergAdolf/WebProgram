package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import bean.Hero;
import dao.HeroDAO;

public class HeroAddServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		request.setCharacterEncoding("UTF-8");
		
		Hero h1 = new Hero();
		h1.setName(request.getParameter("name"));
		h1.setHp(Float.parseFloat(request.getParameter("damage")));
		h1.setDamage(Integer.parseInt(request.getParameter("damage")));
		
		new HeroDAO().add(h1);
		response.sendRedirect("/j2ee/listHero");
	}
}
