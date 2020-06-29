package warcaby.komunikacja;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

import warcaby.zasady.Zasady;

public class Server {
	public final static String serverHost = "localhost";
	public final static int serverPort = 8080;
	private ServerSocket serverSocket;
	private Client client;
	private Zasady zasady;

	Server() {
		try {
			serverSocket = new ServerSocket();
			InetSocketAddress isa = new InetSocketAddress(serverHost, serverPort);

			serverSocket.bind(isa);
			zasady = new Zasady();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Sygnaly reciveSignal() throws ClassNotFoundException, IOException {
		return (Sygnaly) client.recive();
	}

	public void serveSignal() throws ClassNotFoundException, IOException {
		Sygnaly sygnal;
		while (!(sygnal = reciveSignal()).equals(Sygnaly.KONIEC)) {
			System.out.println("Server: " + sygnal);
			switch (sygnal) {
			case KONIEC:
				break;
			case POZYCJA_GRACZA:
				Integer x, y;
				x = (Integer) client.recive();
				y = (Integer) client.recive();
				zasady.sprZasady(x, y);

				break;
			case RYSUJ_PLANSZE:
				StanGry stanGry = new StanGry();
				stanGry.setBicia(zasady.getBicia());
				stanGry.setPlansza(zasady.getPlansza());
				stanGry.setRuchy(zasady.getRuchy());

				System.out.println("=== SERV PLANSZA ===");
				stanGry.getPlansza().wyswietlanie();
				System.out.println("=== SERV RUCHY ===");
				stanGry.getRuchy().wyswietlanie();
				System.out.println("=== SERV BICIA ===");
				stanGry.getBicia().wyswietlanie();
				client.resetOutputStream();
				client.send(stanGry);
				break;
			case START:
				break;

			case BUTTON_NOWA:
				zasady.bNowaAction();
				break;

			case BUTTON_TEST:
				zasady.bTestAction();
				break;

			case BUTTON_TEST_2:
				zasady.bTest2Action();
				break;

			default:
				break;

			}
		}
	}

	public void prepareClient() throws IOException {
		System.out.println("Server czeka na klienta...");
		client = new Client(serverSocket.accept(), true);
	}

	public void startGame() throws ClassNotFoundException, IOException {
		serveSignal();
	}

	public static void main(String[] args) {

		Server server = new Server();
		try {
			server.prepareClient();
			server.startGame();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

	}

}
