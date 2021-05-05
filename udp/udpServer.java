import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class udpServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//UDP所用端口与TCP的不冲突
		//在Java中使用UDP编程，仍然需要使用Socket，因为应用程序在使用UDP时必须指定网络接口（IP）和端口号
		DatagramSocket ds = new DatagramSocket(6666);//监听指定端口
		
		for(;;) {
			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			ds.receive(packet);//收取一个UDP数据包
			//将收取的数据存储在buffer中，由packet.getOffset()等指定缓冲区的界限
			String s = new String(packet.getData(), packet.getOffset(), packet.getLength());
		
			//发送数据
			String ack = "ACK: " + "connect from"+ packet.getSocketAddress();
			byte[] data = ack.getBytes(StandardCharsets.UTF_8);
			packet.setData(data);
			ds.send(packet);
		}
	}

}
