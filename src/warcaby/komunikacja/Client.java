package warcaby.komunikacja;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import warcaby.Okno;

public class Client {
	private Socket clientSocket;
	private ObjectInputStream clientInputObjStream;
	private ObjectOutputStream clientOutputObjStream;

	public Client(Socket clientSocket, boolean isServer) throws IOException {
		this.clientSocket = clientSocket;
		if (isServer) {
			clientInputObjStream = new ObjectInputStream(this.clientSocket.getInputStream());
			clientOutputObjStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
		} else {
			clientOutputObjStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
			clientInputObjStream = new ObjectInputStream(this.clientSocket.getInputStream());
		}
	}

	public void resetOutputStream() throws IOException {
		clientOutputObjStream.reset();
	}

	public Object recive() throws ClassNotFoundException, IOException {
		return clientInputObjStream.readObject();
	}

	public void send(Object obj) throws IOException {
		clientOutputObjStream.writeObject(obj);
		clientOutputObjStream.flush();
	}

	public void sendPozycjaGracza(Integer x, Integer y) throws IOException {
		send(Sygnaly.POZYCJA_GRACZA);
		send(x);
		send(y);
	}

	public StanGry reciveStanGry() throws ClassNotFoundException, IOException {
		send(Sygnaly.RYSUJ_PLANSZE);
		return (StanGry) recive();
	}

	public static void main(String args[]) {
		try {
			new Okno("Warcaby", 640, 480);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
}
