import java.io.*;
import java.net.*;


public class ClientSide implements Runnable{

	InetAddress address;
	int port;

	Thread task1;
	Thread task2;

	DatagramSocket socket;
	byte[] buffer;

	DatagramPacket packet;
	
	String str;
	BufferedReader br;


	public ClientSide(String adrs, String prt){

		try{
			
			address = InetAddress.getByName(adrs);
			port = Integer.parseInt(prt);

		}catch(Exception e){
			System.out.println("Error : " + e);
		}

		System.out.println("1: --------------");
			
			System.out.println(address);
			System.out.println(port);
			System.out.println(buffer);

		task1 = new Thread(this, "join");
		
		task2 = new Thread(this, "2");
		System.out.println(task1);
		//System.out.println(task2);

		task1.start();
		//task2.start();
	}



	@Override
	public void run() {
		String taskName = Thread.currentThread().getName();
		if (taskName.equals("join")){
			System.out.println(taskName + " : I started");

			join();

		}

		if (taskName.equals("2")){
			System.out.println("I started : " + taskName);

			for(int i=0; i<5000; i++)
				System.out.print(taskName);
		}
	}

	public void join(){

		

		try{

			socket = new DatagramSocket();

			while(true){

				br = new BufferedReader(new InputStreamReader(System.in));

			
				buffer = new byte[65507];

				System.out.println("Print text : ");
				str = br.readLine();


				if (str.equals("exit")) break;

				buffer = str.getBytes();
		

				packet = new DatagramPacket(buffer, buffer.length, 
					address, port);
			
				socket.send(packet);

			}

			
			socket.close();

		}catch (Exception e){
			System.out.println("Error : " + e);
		}

	}


}