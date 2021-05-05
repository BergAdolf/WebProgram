import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class tcpServer {
	public static void main(String[] args) throws IOException{
		ServerSocket ss = new ServerSocket(3333);//ָ�������˿�
		System.out.println("Server is running ");
		
		for(;;) {
			Socket sock = ss.accept();
			System.out.print("connect from" + sock.getRemoteSocketAddress());
			Thread t = new Handler(sock);
			t.start();
		}
	}
}
class Handler extends Thread{
	Socket sock;
	
	public Handler(Socket sock) {
		this.sock = sock;
	}
	
	@Override
	public void run() {
		try(InputStream input = this.sock.getInputStream()){
			try(OutputStream output = this.sock.getOutputStream()){
				handle(input, output);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void handle(InputStream input, OutputStream output) throws IOException {
		var writer =  new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
		var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
	
		writer.write("Hello\n");
		writer.flush();
		
		for(;;) {
			String s = reader.readLine();
			if(s.equals("bye")) {
				writer.write("bye\n");
				writer.flush();
				break;
				
			}
			writer.write("ok:" + s + "\n");
			writer.flush();
		}
		
		
	}
	
	
}





