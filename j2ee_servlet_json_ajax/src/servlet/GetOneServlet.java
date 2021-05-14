package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import bean.Hero;
import dao.HeroDAO;
import net.sf.json.JSONObject;
//use ajax to submit the json data;
public class GetOneServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
        request.setCharacterEncoding("UTF-8");
        
        Hero hero = new Hero();
        hero.setName("hero1");
        hero.setHp(1);
        
        JSONObject json = new JSONObject();
        json.put("hero", JSONObject.fromObject(hero));
        
        response.setContentType("text/html; charsets=utf-8");
        response.getWriter().println(json);
	}
}