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
public class SubmitServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
        request.setCharacterEncoding("UTF-8");

        String data = request.getParameter("data");
        System.out.print("���������յ��������ǣ�" + data);
        
        JSONObject json = JSONObject.fromObject(data);
        System.out.print("ת��ΪJson����֮��" + json);
        
        Hero hero = (Hero)JSONObject.toBean(json, Hero.class);
        System.out.print("ת��ΪHero����֮����" + hero);
	}
}