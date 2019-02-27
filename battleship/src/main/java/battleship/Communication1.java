package battleship;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Communication1 {
	private static Socket socket = null;
	private static ServerSocket server = null;
	private static DataInputStream serverIn = null;
	private static DataOutputStream serverOut = null;
	private static DataInputStream input = null;
	
	
	public static void server(int port) throws IOException {
		input = new DataInputStream(System.in);
		try {
			server = new ServerSocket(port);
			System.out.println("Waiting for player2");
			socket = server.accept();
			System.out.println("player2 joined");
			String line = "";
			
			serverIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			serverOut = new DataOutputStream(socket.getOutputStream());
			
			while (!line.equals("Over")) {
				try {
					while(line != null)
						line = input.readLine();
					serverOut.writeUTF("Player1 : " + line);
					line = serverIn.readUTF();
					System.out.println(line);
					line = null;
					
				} catch (IOException i) {
					break;
				}
			}
			System.out.println("Closing connection");
			socket.close();
			serverIn.close();
			input.close();
		} catch (IOException i) {
			throw i;
		}
	}

	public static void client(String address, int port) {
		try {
			socket = new Socket(address, port);
			System.out.println("Connected");

			input = new DataInputStream(System.in);
			serverIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			serverOut = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException u) {
			System.out.println(u);
		} catch (IOException i) {
			System.out.println(i);
		}

		String line = "";

		while (!line.equals("Over")) {
			try {
				line = serverIn.readUTF();
				System.out.println(line);
				line = null;
				while(line != null)
					line = input.readLine();
				serverOut.writeUTF("client sent : " + line);
			} catch (IOException i) {
				System.out.println(i);
				break;
			}
		}

		try {
			input.close();
			serverOut.close();
			socket.close();
		} catch (IOException i) {
			System.out.println(i);
		}
	}

	
	
	
	
	
	
	public static void main(String args[]) {

		try {
			server(5000);
		} catch (IOException e) {
			if (e instanceof BindException) {
				client("127.0.0.1", 5000);	
			}
		}

	}
}
