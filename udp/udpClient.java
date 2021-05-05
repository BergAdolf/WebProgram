import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class udpClient {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket ds = new DatagramSocket();
		ds.setSoTimeout(1000);//���ͺ���յĵȴ�ʱ�䲻�ᳬ��1�롣
		ds.connect(InetAddress.getByName("localhost"), 6666);//���������ӣ���Ҫ�Ǳ������˵ĵ�ַ�Ͷ˿ڣ�
		//���UDP��Ҫ��������˿ڣ��ͱ��봴������ʵ����
		
		
		
		//����
		byte[] data = "Hello".getBytes();
		DatagramPacket packet = new DatagramPacket(data, data.length);
		ds.send(packet);
		
		//����
		byte[] buffer = new byte[1024];
		packet = new DatagramPacket(buffer, buffer.length);
		ds.receive(packet);
		String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
		System.out.print(resp);
		ds.disconnect();//ֻ������˷���˵���Ϣ������ʹ������˿ڼ�������UDPͨ��
		
	}
}	
