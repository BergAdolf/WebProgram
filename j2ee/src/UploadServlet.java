import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response){
		
		String filename = null;
		
		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			//�����ϴ��ļ��Ĵ�С����Ϊ1M;
			factory.setSizeThreshold(1024 * 1024);
			
			java.util.List items =  null;
			try {
				items = (java.util.List) upload.parseRequest(request);
			}catch(FileUploadException e)
			{
				e.printStackTrace();
			}
			Iterator iter = ((java.util.List)items).iterator();
			while(iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
                if (!item.isFormField()) {
  
                    // ����ʱ�������ͷ���ļ�
                    filename = System.currentTimeMillis() + ".jpg";
                     
                    //ͨ��getRealPath��ȡ�ϴ��ļ��У������Ŀ��e:/project/j2ee/web,��ô�ͻ��Զ���ȡ�� e:/project/j2ee/web/uploaded
                    String photoFolder =request.getServletContext().getRealPath("uploaded");
                     
                    File f = new File(photoFolder, filename);
                    f.getParentFile().mkdirs();
  
                    // ͨ��item.getInputStream()��ȡ������ϴ����ļ���������
                    InputStream is = item.getInputStream();
  
                    // �����ļ�
                    FileOutputStream fos = new FileOutputStream(f);
                    byte b[] = new byte[1024 * 1024];
                    int length = 0;
                    while (-1 != (length = is.read(b))) {
                        fos.write(b, 0, length);
                    }
                    fos.close();
  
                } else {
                    System.out.println(item.getFieldName());
                    String value = item.getString();
                    value = new String(value.getBytes("ISO-8859-1"), "UTF-8");
                    System.out.println(value);
                }
			}
            String html = "<img width='200' height='150' src='uploaded/%s' />";
            response.setContentType("text/html");
            PrintWriter pw= response.getWriter();
             
            pw.format(html, filename);
            
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}
	
}

