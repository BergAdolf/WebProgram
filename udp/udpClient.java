import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class udpClient {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket ds = new DatagramSocket();
		ds.setSoTimeout(1000);//发送后接收的等待时间不会超过1秒。
		ds.connect(InetAddress.getByName("localhost"), 6666);//不是真连接，主要是保存服务端的地址和端口，
		//如果UDP需要传播多个端口，就必须创建两个实例。
		
		
		
		//发送
		byte[] data = "Hello".getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length);
		ds.send(packet);
		
		//接受
		byte[] buffer = new byte[1024];
		packet = new DatagramPacket(buffer, buffer.length);
		ds.receive(packet);
		String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
		System.out.print(resp);
		ds.disconnect();//只是清除了服务端的信息，可以使用这个端口继续创建UDP通信
		
	}
}	
