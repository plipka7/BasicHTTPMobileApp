import java.io.*; 
import java.net.*;

public class JavaServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(3000);
			System.out.println("Server established on port 3000");
			while(true) {
				try(Socket serverSocket = server.accept()) {
					System.out.println("Connected!!!");
					DataInputStream dis = new DataInputStream(serverSocket.getInputStream());
					System.out.println("MESSAGE RECEIVED: " + dis.readUTF());
				} catch(Exception e) {
					e.printStackTrace();
					System.out.println("Exception has occurred");
				}
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
			System.exit(1);
		}
	}
}