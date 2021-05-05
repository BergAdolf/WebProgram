import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class tcpClient {
	public static void main(String[] args) throws IOException {
		Socket sock = new Socket("localhost",3333);
		try(InputStream input = sock.getInputStream()){
			try(OutputStream output = sock.getOutputStream()){
				handle(input, output);
			}
		}
		sock.close();
		System.out.print("disconnected");
	}
	
	private static void handle(InputStream input, OutputStream output) throws IOException {
        var writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
	
        Scanner scanner = new Scanner(System.in);
        System.out.println("[server]" + reader.readLine());
        
        for (;;) {
            System.out.print(">>> "); // ��ӡ��ʾ
            String s = scanner.nextLine(); // ��ȡһ������
            writer.write(s);
            writer.newLine();
            writer.flush();
            String resp = reader.readLine();
            System.out.println("<<< " + resp);
            if (resp.equals("bye")) {
                break;
            }
        }
	}
}
