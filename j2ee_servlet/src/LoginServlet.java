import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet{
	public LoginServlet() {
		System.out.println("LoginServlet ���췽�� ������");
	}
	
	public void init(ServletConfig config) {
		//�ڳ�ʼ������֮�����
		System.out.println("init(ServletConfig)");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		byte[] bytes = name.getBytes("ISO-8859-1");
		name = new String(bytes, "UTF-8");
		
		String html = null;
		if("name".equals(name) && "password".equals(password)) {
			html = "<div style='color.green'>success</div>";
		}else {
			html = "<div style='color.green'>fail</div>";
		}
		PrintWriter pw = response.getWriter();
		pw.println(html);
		System.out.println(name);
		System.out.print(password);
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
		throws IOException, ServletException{
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		System.out.println("�������������ʱ������URL������Э�� ������ �˿�(�����): " + request.getRequestURL());
		System.out.println("����������������Դ�����֣�ȥ����Э���������: " + request.getRequestURI());
		System.out.println("�������еĲ�������: " + request.getQueryString());
		System.out.println("����������ڵĿͻ�����IP��ַ: " + request.getRemoteAddr());
		System.out.println("����������ڵĿͻ�����������: " + request.getRemoteHost());
		System.out.println("����������ڵĿͻ���ʹ�õ�����˿�: " + request.getRemotePort());
		System.out.println("��������IP��ַ: " + request.getLocalAddr());
		System.out.println("��������������: " + request.getLocalName());
		System.out.println("�õ��ͻ�������ʽ: " + request.getMethod());
		
		if("admin".equals(name) && "123".equals(password)) {
			//����˵���ת
			request.getRequestDispatcher("success.html").forward(request, response);
		}else {
			//�ͻ��˵���ת
			response.sendRedirect("fail.html");
		}
	}
}
