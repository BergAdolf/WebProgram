import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class udpServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//UDP���ö˿���TCP�Ĳ���ͻ
		//��Java��ʹ��UDP��̣���Ȼ��Ҫʹ��Socket����ΪӦ�ó�����ʹ��UDPʱ����ָ������ӿڣ�IP���Ͷ˿ں�
		DatagramSocket ds = new DatagramSocket(6666);//����ָ���˿�
		
		for(;;) {
			byte[] buffer = new byte[1024];
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			ds.receive(packet);//��ȡһ��UDP���ݰ�
			//����ȡ�����ݴ洢��buffer�У���packet.getOffset()��ָ���������Ľ���
			String s = new String(packet.getData(), packet.getOffset(), packet.getLength());
		
			//��������
			String ack = "ACK: " + "connect from"+ packet.getSocketAddress();
			byte[] data = ack.getBytes(StandardCharsets.UTF_8);
			packet.setData(data);
			ds.send(packet);
		}
	}

}
