package Client;

import java.awt.Point;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Client {
	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 600;
	public static final int MAX_PLAYER = 4;
	public static final int MAX_SWORD = 2;
	public static int playerNum;
	public static int command = 0;
	public static ClientPlayer[] players = new ClientPlayer[MAX_PLAYER];
	public static ClientSword[] swords = new ClientSword[MAX_SWORD];
	public static Point[] swordpoint = new Point[MAX_SWORD];
	public static boolean startflag = false;

	public static void main(String[] args) {
		try {


				String serverIp = "127.0.0.1";
				Socket socket = new Socket(serverIp, 7777);
				DataInputStream in = new DataInputStream(socket.getInputStream());
				playerNum = in.readInt();

				for (int i = 0; i < MAX_PLAYER; i++) {
					if (i == MAX_PLAYER - 1) {
						players[i] = new ClientPlayer(in.readInt(), in.readInt(), 0);
					} else {
						players[i] = new ClientPlayer(in.readInt(), in.readInt(), i + 1);
					}
				}
				for (int i = 0; i < MAX_SWORD; i++) {
					swords[i] = new ClientSword(in.readInt(), in.readInt());
				}
				new Game();
				Thread sender = new Thread(new ClientSender(socket));
				Thread receiver = new Thread(new ClientReceiver(socket));
				sender.start();
				receiver.start();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class ClientSender extends Thread {
		Socket socket;
		DataOutputStream out;

		ClientSender(Socket socket) {
			this.socket = socket;
			try {
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
			}
		}

		public void run() {
			try {
				if (out != null) {
					out.writeInt(playerNum);
				}
				while (out != null) {
					if (command != 0) {
						out.writeInt(command);
						command = 0;
					}
					System.out.print("");
				}

			} catch (Exception e) {

			}
		}
	}

	static class ClientReceiver extends Thread {
		Socket socket;
		DataInputStream in;

		ClientReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
			}
		}

		public void run() {
			while (in != null) {
				try {
					int recvcommand = in.readInt();
					players[recvcommand / 10].inputCommand(recvcommand % 10);
				} catch (Exception e) {
				}
			}
		}

	}
}
