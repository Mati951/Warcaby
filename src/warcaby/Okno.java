package warcaby;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Label;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import warcaby.komunikacja.Client;
import warcaby.komunikacja.Server;
import warcaby.komunikacja.StanGry;
import warcaby.komunikacja.Sygnaly;
import warcaby.zasady.Bicia;
import warcaby.zasady.Ruchy;

public class Okno extends Frame implements ActionListener, MouseListener {
	Client client;
	Plansza plansza;
	Ruchy ruchy;
	Bicia bicia;
	Button bTest;
	Button bTest_2;
	Button bNowa;
	Label lTekst;

	public Okno(String Nazwa, int szer, int wys) throws UnknownHostException, IOException, ClassNotFoundException {
		super(Nazwa);
		setLayout(null);

		plansza = new Plansza();
		ruchy = new Ruchy();
		bicia = new Bicia();
		client = new Client(new Socket(Server.serverHost, Server.serverPort), false);
		updateStanGry();

		setSize(szer, wys);
		setLocation(10, 10);
		setFont(new Font("Arial", 0, 16));
		setResizable(false);
		setBackground(new Color(220, 220, 220));

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					client.send(Sygnaly.KONIEC);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.exit(0);
			}
		});

		bNowa = new Button("Nowa gra");
		bNowa.setSize(100, 25);
		bNowa.setLocation(400, 45);
		bNowa.addActionListener(this);
		add(bNowa);

		bTest = new Button("Test 1");
		bTest.setSize(100, 25);
		bTest.setLocation(400, 145);
		bTest.addActionListener(this);
		add(bTest);

		bTest_2 = new Button("Test 2");
		bTest_2.setSize(100, 25);
		bTest_2.setLocation(400, 195);
		bTest_2.addActionListener(this);
		add(bTest_2);

		lTekst = new Label("Gracz:");
		lTekst.setSize(50, 25);
		lTekst.setLocation(5, 385);
		add(lTekst);

		addMouseListener(this);

		show();
	}

	public void paint(Graphics g) {
		RysujPlansze(g);
	}

	public void RysujPlansze(Graphics g) {
		System.out.println("############# RysujPlansze #############");
		System.out.println("=== PLANSZA ===");
		plansza.wyswietlanie();
		System.out.println("=== RUCHY ===");
		ruchy.wyswietlanie();
		System.out.println("=== BICIA ===");
		bicia.wyswietlanie();

		System.out.println("=== RUCHY gracy ===");
		System.out.println(ruchy.get_gracz());
		Image img = createImage(getSize().width, getSize().height);

		Graphics2D g2 = (Graphics2D) img.getGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setColor(Color.black);
		g2.fillRect(18, 38, 322, 322);

		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				if (plansza.pole[i][j] == 0)
					g2.setColor(new Color(220, 205, 145)); // jasny
				else
					g2.setColor(new Color(95, 145, 95)); // ciemny

				g2.fillRect(20 + 40 * i, 40 + 40 * j, 38, 38);

				if (plansza.pole[i][j] > 1) {
					g2.setColor(Color.black);
					g2.fillOval(21 + 40 * i, 41 + 40 * j, 36, 36);

					if (plansza.pole[i][j] == 2 || plansza.pole[i][j] == 4)
						g2.setColor(new Color(245, 240, 240));
					if (plansza.pole[i][j] == 3 || plansza.pole[i][j] == 5)
						g2.setColor(new Color(215, 95, 95));

					g2.fillOval(23 + 40 * i, 43 + 40 * j, 32, 32);

					if (plansza.pole[i][j] == 4 || plansza.pole[i][j] == 5) {
						g2.setColor(Color.black);
						g2.fillOval(26 + 40 * i, 46 + 40 * j, 26, 26);
					}

				}

				if (bicia.pole[i][j] != 0) {
					g2.setColor(new Color(215, 95, 95));
					g2.drawRect(20 + 40 * i, 40 + 40 * j, 37, 37);
				}

				if (ruchy.pole[i][j] == 1) {
					g2.setColor(new Color(50, 100, 250));
					g2.drawRect(20 + 40 * i, 40 + 40 * j, 37, 37);
				}

				if (ruchy.pole[i][j] == 2 || ruchy.pole[i][j] == 3) {
					g2.setColor(new Color(150, 150, 150));
					g2.fillRect(20 + 40 * i, 40 + 40 * j, 38, 38);
				}
			}
		}

		g2.setColor(Color.black); // czarny
		g2.fillRect(58, 378, 42, 42);

		g2.setColor(new Color(95, 145, 95)); // ciemny
		g2.fillRect(60, 380, 38, 38);

		if (ruchy.get_gracz() != 0) {
			g2.setColor(Color.black);
			g2.fillOval(61, 381, 36, 36);

			if (ruchy.get_gracz() == 1) {
				g2.setColor(new Color(245, 240, 240));
			} else {
				g2.setColor(new Color(215, 95, 95));
			}
			g2.fillOval(63, 383, 32, 32);
		}

		

		g.drawImage(img, 0, 0, this);

		

	}

	public void update(Graphics g) {
		paint(g);
	}

	public void actionPerformed(ActionEvent ev) {
		Object cel = ev.getSource();
		try {
			if (cel == bNowa) {
				client.send(Sygnaly.BUTTON_NOWA);
			} else if (cel == bTest) {
				client.send(Sygnaly.BUTTON_TEST);
			} else if (cel == bTest_2) {
				client.send(Sygnaly.BUTTON_TEST_2);

			}
			updateStanGry();
			repaint();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	private void updateStanGry() throws ClassNotFoundException, IOException {
		StanGry stanGry = client.reciveStanGry();
		plansza = stanGry.getPlansza();
		ruchy = stanGry.getRuchy();
		bicia = stanGry.getBicia();

		
	}

	public void mouseClicked(MouseEvent ev) {
		Integer x = ev.getX();
		Integer y = ev.getY();

		try {
			client.sendPozycjaGracza(x, y);
			updateStanGry();
			repaint();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}

	}

	public void mousePressed(MouseEvent ev) {
	}

	public void mouseReleased(MouseEvent ev) {
	}

	public void mouseEntered(MouseEvent ev) {
	}

	public void mouseExited(MouseEvent ev) {
	}
}