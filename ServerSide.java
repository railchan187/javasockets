import java.io.*;
import java.net.*;




public class ServerSide implements Runnable{

	Thread task1;
	Thread task2;

	DatagramSocket socket = null;
	DatagramPacket packet;
	int port;
	byte[] buffer;

	String str = null;

	public ServerSide(int port){

		this.port = port;



		task1 = new Thread(this, "listen");
		
		task2 = new Thread(this, "2");
		System.out.println(task1);
		//System.out.println(task2);

		task1.start();
		//task2.start();
	}





	@Override
	public void run() {
		String taskName = Thread.currentThread().getName();
		if (taskName.equals("listen")){
			System.out.println(taskName + " : I started");

			listen();

		}

		if (taskName.equals("2")){
			System.out.println("I started : " + taskName);

			for(int i=0; i<5000; i++)
				System.out.print(taskName);
		}
	}

	public void listen(){
		try{

			socket = new DatagramSocket(port);

			while(true){

				

				buffer = new byte[65507];

				packet = new DatagramPacket(buffer, buffer.length);

				System.out.println(Thread.currentThread().getName() + 
					" : I listening");
				
				socket.receive(packet);

				if(packet == null) break;

				str = new String(packet.getData(), 0, 
                                     packet.getLength(), "UTF-8");

				System.out.println( packet.getAddress() + " : " + str );

			}

			
			socket.close();

		}catch (Exception e){
			System.out.println("Error : " + e);
		}

	}

}