package warcaby.zasady;

import java.io.Serializable;

import warcaby.Plansza;
import warcaby.Tablica;

public class Bicia extends Tablica implements Serializable {

	public boolean przymus;

	public Bicia() {
		przymus = false;
	}

	private int get_bicia(int x, int y, Plansza p) {
		int wynik = 0;

		if (p.pole[x][y] == 2) {
			if (x > 1 && y > 1 && (p.pole[x - 1][y - 1] == 3 || p.pole[x - 1][y - 1] == 5) && p.pole[x - 2][y - 2] == 1)
				wynik = wynik + 1;

			if (x < 6 && y > 1 && (p.pole[x + 1][y - 1] == 3 || p.pole[x + 1][y - 1] == 5) && p.pole[x + 2][y - 2] == 1)
				wynik = wynik + 2;

			if (x > 1 && y < 6 && (p.pole[x - 1][y + 1] == 3 || p.pole[x - 1][y + 1] == 5) && p.pole[x - 2][y + 2] == 1)
				wynik = wynik + 4;

			if (x < 6 && y < 6 && (p.pole[x + 1][y + 1] == 3 || p.pole[x + 1][y + 1] == 5) && p.pole[x + 2][y + 2] == 1)
				wynik = wynik + 8;
		} else if (p.pole[x][y] == 3) {
			if (x > 1 && y < 6 && (p.pole[x - 1][y + 1] == 2 || p.pole[x - 1][y + 1] == 4) && p.pole[x - 2][y + 2] == 1)
				wynik = wynik + 1;

			if (x < 6 && y < 6 && (p.pole[x + 1][y + 1] == 2 || p.pole[x + 1][y + 1] == 4) && p.pole[x + 2][y + 2] == 1)
				wynik = wynik + 2;

			if (x > 1 && y > 1 && (p.pole[x - 1][y - 1] == 2 || p.pole[x - 1][y - 1] == 4) && p.pole[x - 2][y - 2] == 1)
				wynik = wynik + 4;

			if (x < 6 && y > 1 && (p.pole[x + 1][y - 1] == 2 || p.pole[x + 1][y - 1] == 4) && p.pole[x + 2][y - 2] == 1)
				wynik = wynik + 8;
		} else if (p.pole[x][y] == 4) {
			int i = 0;
			int j = 0;
			while (x + i > 1 && y + j > 1) {
				if ((p.pole[x + i - 1][y + j - 1] == 3 || p.pole[x + i - 1][y + j - 1] == 5)
						&& p.pole[x + i - 2][y + j - 2] == 1) {
					wynik = wynik + 1;
					break;
				} else if (p.pole[x + i - 1][y + j - 1] != 1) {
					break;
				} else {
					--i;
					--j;
				}
			}

			i = 0;
			j = 0;

			while (x + i < 6 && y + j > 1) {
				if ((p.pole[x + i + 1][y + j - 1] == 3 || p.pole[x + i + 1][y + j - 1] == 5)
						&& p.pole[x + i + 2][y + j - 2] == 1) {
					wynik = wynik + 2;
					break;
				} else if (p.pole[x + i + 1][y + j - 1] != 1) {
					break;
				} else {
					++i;
					--j;
				}
			}

			i = 0;
			j = 0;

			while (x + i > 1 && y + j < 6) {
				if ((p.pole[x + i - 1][y + j + 1] == 3 || p.pole[x + i - 1][y + j + 1] == 5)
						&& p.pole[x + i - 2][y + j + 2] == 1) {
					wynik = wynik + 4;
					break;
				} else if (p.pole[x + i - 1][y + j + 1] != 1) {
					break;
				} else {
					--i;
					++j;
				}
			}

			i = 0;
			j = 0;

			while (x + i < 6 && y + j < 6) {
				if ((p.pole[x + i + 1][y + i + 1] == 3 || p.pole[x + i + 1][y + j + 1] == 5)
						&& p.pole[x + j + 2][y + j + 2] == 1) {
					wynik = wynik + 8;
					break;
				} else if (p.pole[x + i + 1][y + j + 1] != 1) {
					break;
				} else {
					++i;
					++j;
				}
			}

		} else if (p.pole[x][y] == 5) {
			int i = 0;
			int j = 0;
			while (x + i > 1 && y + j > 1) {
				if ((p.pole[x + i - 1][y + j - 1] == 2 || p.pole[x + i - 1][y + j - 1] == 4)
						&& p.pole[x + i - 2][y + j - 2] == 1) {
					wynik = wynik + 4;
					break;
				} else if (p.pole[x + i - 1][y + j - 1] != 1) {
					break;
				} else {
					--i;
					--j;
				}
			}

			i = 0;
			j = 0;

			while (x + i < 6 && y + j > 1) {
				if ((p.pole[x + i + 1][y + j - 1] == 2 || p.pole[x + i + 1][y + j - 1] == 4)
						&& p.pole[x + i + 2][y + j - 2] == 1) {
					wynik = wynik + 8;
					break;
				} else if (p.pole[x + i + 1][y + j - 1] != 1) {
					break;
				} else {
					++i;
					--j;
				}
			}

			i = 0;
			j = 0;

			while (x + i > 1 && y + j < 6) {
				if ((p.pole[x + i - 1][y + j + 1] == 2 || p.pole[x + i - 1][y + j + 1] == 4)
						&& p.pole[x + i - 2][y + j + 2] == 1) {
					wynik = wynik + 1;
					break;
				} else if (p.pole[x + i - 1][y + j + 1] != 1) {
					break;
				} else {
					--i;
					++j;
				}
			}

			i = 0;
			j = 0;

			while (x + i < 6 && y + j < 6) {
				if ((p.pole[x + i + 1][y + i + 1] == 2 || p.pole[x + i + 1][y + j + 1] == 4)
						&& p.pole[x + j + 2][y + j + 2] == 1) {
					wynik = wynik + 2;
					break;
				} else if (p.pole[x + i + 1][y + j + 1] != 1) {
					break;
				} else {
					++i;
					++j;
				}
			}

		}

		if (wynik != 0)
			przymus = true;

		return wynik;
	}

	public void sprawdz_bicia(int gracz, Plansza p) {
		zerowanie();
		przymus = false;

		for (int j = 0; j < 8; j++)
			for (int i = 0; i < 8; i++)
				if ((gracz + 1) == p.pole[i][j] || (gracz + 3) == p.pole[i][j]) {
					this.pole[i][j] = get_bicia(i, j, p);
				} else {
					this.pole[i][j] = 0;
				}
		
	}

	public void sprawdz_bicia(int gracz, Plansza p, int x, int y) {
		zerowanie();
		przymus = false;

		this.pole[x][y] = get_bicia(x, y, p);
	}

}
