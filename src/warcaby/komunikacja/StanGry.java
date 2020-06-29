package warcaby.komunikacja;

import java.io.Serializable;

import warcaby.Plansza;
import warcaby.zasady.Bicia;
import warcaby.zasady.Ruchy;

public class StanGry implements Serializable {
	private Plansza plansza;
	private Ruchy ruchy;
	private Bicia bicia;

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
}
