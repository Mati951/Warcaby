package warcaby.zasady;

import warcaby.Plansza;

public class Zasady {

	private Plansza plansza = new Plansza();
	private Ruchy ruchy = new Ruchy();
	private Bicia bicia = new Bicia();

	public Plansza getPlansza() {
		return plansza;
	}

	public void setPlansza(Plansza plansza) {
		this.plansza = plansza;
	}

	public Ruchy getRuchy() {
		return ruchy;
	}

	public void setRuchy(Ruchy ruchy) {
		this.ruchy = ruchy;
	}

	public Bicia getBicia() {
		return bicia;
	}

	public void setBicia(Bicia bicia) {
		this.bicia = bicia;
	}

	public void sprZasady(Integer x, Integer y) {

		if (x >= 20 && x < 340 && y >= 40 && y < 360 && (x + 22) % 40 != 0 && (x + 21) % 40 != 0 && (y + 2) % 40 != 0
				&& (y + 1) % 40 != 0) {
			int _x, _y;
			_x = (x - 20) / 40;
			_y = (y - 40) / 40;
			System.out.println(_x + " " + _y);
			bicia.sprawdz_bicia(ruchy.get_gracz(), plansza);
			ruchy.klikniecie(_x, _y, plansza, bicia);
			
		}
	}

	public void bNowaAction() {
		plansza.rozpoczecie();
		ruchy.zerowanie();
		ruchy.set_gracz(1);
		bicia.zerowanie();
	}

	public void bTestAction() {
		Integer tmp_pole[][] = { { 0, 3, 0, 1, 0, 4, 0, 3 }, { 1, 0, 1, 0, 3, 0, 1, 0 }, { 0, 3, 0, 1, 0, 1, 0, 1 },
				{ 3, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 3, 0, 1, 0, 1 }, { 1, 0, 1, 0, 1, 0, 2, 0 },
				{ 0, 2, 0, 1, 0, 1, 0, 2 }, { 1, 0, 2, 0, 1, 0, 2, 0 }, };

		plansza.pole = tmp_pole;
		plansza.transpozycja();
		plansza.wyswietlanie();
		ruchy.zerowanie();
		ruchy.set_gracz(1);
		bicia.zerowanie();
	}

	public void bTest2Action() {
		Integer tmp_pole[][] = { { 0, 3, 0, 1, 0, 4, 0, 3 }, { 1, 0, 1, 0, 1, 0, 1, 0 }, { 0, 3, 0, 1, 0, 1, 0, 1 },
				{ 3, 0, 1, 0, 1, 0, 1, 0 }, { 0, 1, 0, 3, 0, 4, 0, 1 }, { 1, 0, 1, 0, 1, 0, 2, 0 },
				{ 0, 2, 0, 1, 0, 1, 0, 2 }, { 1, 0, 2, 0, 5, 0, 2, 0 }, };

		plansza.pole = tmp_pole;
		plansza.transpozycja();
		plansza.wyswietlanie();
		ruchy.zerowanie();
		ruchy.set_gracz(1);
		bicia.zerowanie();
	}

}
