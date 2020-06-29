package warcaby;

import java.io.Serializable;

public class Tablica implements Serializable {
	public Integer pole[][];

	public Tablica() {
		pole = new Integer[8][8];
		this.zerowanie();
	}

	public void zerowanie() {
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				pole[i][j] = 0;
			}
		}
	}

	public void wyswietlanie() {
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				System.out.print(pole[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void transpozycja() {
		Integer tmp_pole[][] = new Integer[8][8];
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				tmp_pole[j][i] = pole[i][j];
			}
		}
		pole = tmp_pole;
	}
}