package battleship;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication implements CommunicationI {

	private static Socket socket = null;
	private static ServerSocket server = null;
	private static DataInputStream serverIn = null;
	private static DataOutputStream serverOut = null;
	//private static DataInputStream input = null;

	private static void server(int port) throws IOException {
		//input = new DataInputStream(System.in);
		try {
			server = new ServerSocket(port);
			System.out.println("Waiting for player2");
			socket = server.accept();
			System.out.println("player2 joined");
			serverIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			serverOut = new DataOutputStream(socket.getOutputStream());

		} catch (IOException e) {
			throw e;
		}
	}

	public static void client(String address, int port) throws Exception {
		try {
			socket = new Socket(address, port);
			System.out.println("Playing with Player1");
			//input = new DataInputStream(System.in);
			serverIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			serverOut = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			throw e;
		}

	}

	public Communication() {
		this("127.0.0.1", 5000);
	}

	public Communication(String ip, int port) {
		try {
			server(port);
		} catch (Exception e) {
			if (e instanceof BindException) {
				try {
					client(ip, port);
				} catch (Exception e1) {
				}
			}
		}
	}

	public void speak(String text) {
		try {
			serverOut.writeUTF(text);
		} catch (IOException e) {
		}
	}

	public String listen() {
		String line = null;
		try {
			line = serverIn.readUTF();
		} catch (IOException e) {
		}
		return line;

	}

	public void close() {
		try {
			//input.close();
			serverIn.close();
			serverOut.close();
			if(socket !=null && !socket.isClosed())
				socket.close();
			if(server!= null && !server.isClosed())
				server.close();
		} catch (Exception e) {
			System.out.println("Error closing ..");
		}
	}

	public boolean isFirstPlayer() {
		if(this.server == null)
			return false;
		return true;
	}

}
