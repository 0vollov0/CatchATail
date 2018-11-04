package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class Server {
	public static final int MAX_PLAYER = 4;
	public static final int MAX_SWORD = 2;
	public static ServerPlayer[] players = new ServerPlayer[MAX_PLAYER];
	public static ServerSword[] swords = new ServerSword[MAX_SWORD];
	public static int playerNum = 0;
	public static int command;
	public static HashMap<Integer, DataOutputStream> clients;

	Server() {
		for (int i = 0; i < MAX_PLAYER; i++) {
			players[i] = new ServerPlayer();
		}

		for (int i = 0; i < MAX_SWORD; i++) {
			swords[i] = new ServerSword();
		}

		clients = new HashMap<Integer, DataOutputStream>();
		Collections.synchronizedMap(clients);
	}


	public void start() {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			serverSocket = new ServerSocket(7777);

			while (true) {
				socket = serverSocket.accept();
				System.out.println("[" + socket.getInetAddress() + ":" + socket.getPort() + "]" + "에서 접속하였습니다.");
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				out.writeInt(playerNum++);
				for (int i = 0; i < MAX_PLAYER; i++) {
					out.writeInt(players[i].getPosX());
					out.writeInt(players[i].getPosY());
				}
				for (int i = 0; i < MAX_SWORD; i++) {
					out.writeInt(swords[i].getPosX());
					out.writeInt(swords[i].getPosY());
				}
				ServerReceiver thread = new ServerReceiver(socket);
				thread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void sendToAll(int id,int command) {
		// TODO Auto-generated method stub
		Iterator<Integer> it = clients.keySet().iterator();
		while (it.hasNext()) {
			try {
				DataOutputStream out = (DataOutputStream) clients.get(it.next());
				out.writeInt(id*10+command);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Server().start();
	}

	class ServerReceiver extends Thread {
		Socket socket;
		DataInputStream in;
		DataOutputStream out;

		ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				in = new DataInputStream(socket.getInputStream());
				out = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				int id = in.readInt();
				clients.put(id, out);
				while (in != null) {
					int command = in.readInt();
					System.out.println(command);
					sendToAll(id,command);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}