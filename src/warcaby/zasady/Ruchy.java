package warcaby.zasady;

import java.io.Serializable;

import warcaby.Plansza;
import warcaby.Tablica;

public class Ruchy extends Tablica implements Serializable {
	private int old_x;
	private int old_y;
	private int gracz;

	public Ruchy() {
		this.set_gracz(0);
	}

	public void klikniecie(int x, int y, Plansza p, Bicia b) {
		b.sprawdz_bicia(gracz, p);

		if (this.pole[x][y] == 0) {
			this.zerowanie();
			if (p.pole[x][y] > 1) // je¿eli w polu znajduje siê pionek
			{
				this.pole[x][y] = 1; // obecnie zaznaczony pionek

				// je¿eli jest to pionek gracza 1
				if (p.pole[x][y] == 2 && this.gracz == 1) {
					if (x > 0 && y > 0 && p.pole[x - 1][y - 1] == 1 && !b.przymus)
						this.pole[x - 1][y - 1] = 2;

					if (x < 7 && y > 0 && p.pole[x + 1][y - 1] == 1 && !b.przymus)
						this.pole[x + 1][y - 1] = 2;

					if (x > 1 && y > 1 && (p.pole[x - 1][y - 1] == 3 || p.pole[x - 1][y - 1] == 5)
							&& p.pole[x - 2][y - 2] == 1) {
						this.pole[x - 2][y - 2] = 3;
					}

					if (x < 6 && y > 1 && (p.pole[x + 1][y - 1] == 3 || p.pole[x + 1][y - 1] == 5)
							&& p.pole[x + 2][y - 2] == 1) {
						this.pole[x + 2][y - 2] = 3;
					}

					if (x > 1 && y < 6 && (p.pole[x - 1][y + 1] == 3 || p.pole[x - 1][y + 1] == 5)
							&& p.pole[x - 2][y + 2] == 1) {
						this.pole[x - 2][y + 2] = 3;
					}

					if (x < 6 && y < 6 && (p.pole[x + 1][y + 1] == 3 || p.pole[x + 1][y + 1] == 5)
							&& p.pole[x + 2][y + 2] == 1) {
						this.pole[x + 2][y + 2] = 3;
					}

				}
				// je¿eli jest to pionek racza 2
				else if (p.pole[x][y] == 3 && this.gracz == 2) {
					if (x > 0 && y < 7 && p.pole[x - 1][y + 1] == 1 && !b.przymus)
						this.pole[x - 1][y + 1] = 2;

					if (x < 7 && y < 7 && p.pole[x + 1][y + 1] == 1 && !b.przymus)
						this.pole[x + 1][y + 1] = 2;

					if (x > 1 && y < 6 && (p.pole[x - 1][y + 1] == 2 || p.pole[x - 1][y + 1] == 4)
							&& p.pole[x - 2][y + 2] == 1) {
						this.pole[x - 2][y + 2] = 3;
					}

					if (x < 6 && y < 6 && (p.pole[x + 1][y + 1] == 2 || p.pole[x + 1][y + 1] == 4)
							&& p.pole[x + 2][y + 2] == 1) {
						this.pole[x + 2][y + 2] = 3;
					}

					if (x > 1 && y > 1 && (p.pole[x - 1][y - 1] == 2 || p.pole[x - 1][y - 1] == 4)
							&& p.pole[x - 2][y - 2] == 1) {
						this.pole[x - 2][y - 2] = 3;
					}

					if (x < 6 && y > 1 && (p.pole[x + 1][y - 1] == 2 || p.pole[x + 1][y - 1] == 4)
							&& p.pole[x + 2][y - 2] == 1) {
						this.pole[x + 2][y - 2] = 3;
					}
				}
				// je¿eli jest to damka gracza 1 lub gracza 2
				else if ((p.pole[x][y] == 4 && this.gracz == 1) || (p.pole[x][y] == 5 && this.gracz == 2)) {
					int i = 0;
					int j = 0;
					int add = (gracz == 1) ? 1 : 0;

					while (!b.przymus && x + i > 0 && y + j > 0) {
						if (p.pole[x + i - 1][y + j - 1] == 1) {
							this.pole[x + i - 1][y + j - 1] = 2;
							--i;
							--j;
						} else
							break;
					}

					i = 0;
					j = 0;

					while (!b.przymus && x + i < 7 && y + j > 0) {
						if (p.pole[x + i + 1][y + j - 1] == 1) {
							this.pole[x + i + 1][y + j - 1] = 2;
							++i;
							--j;
						} else
							break;
					}

					i = 0;
					j = 0;

					while (!b.przymus && x + i > 0 && y + j < 7) {
						if (p.pole[x + i - 1][y + j + 1] == 1) {
							this.pole[x + i - 1][y + j + 1] = 2;
							--i;
							++j;
						} else
							break;
					}

					i = 0;
					j = 0;

					while (!b.przymus && x + i < 7 && y + j < 7) {
						if (p.pole[x + i + 1][y + j + 1] == 1) {
							this.pole[x + i + 1][y + j + 1] = 2;
							++i;
							++j;
						} else
							break;
					}

					boolean bij = false;
					i = 0;
					j = 0;

					while (x + i > 0 && y + j > 0) {
						if (p.pole[x + i - 1][y + j - 1] == 1) {
							if (bij)
								this.pole[x + i - 1][y + j - 1] = 3;
							--i;
							--j;
						} else if (p.pole[x + i - 1][y + j - 1] == 2 + add || p.pole[x + i - 1][y + j - 1] == 4 + add) {
							if (!bij) {
								bij = true;
								--i;
								--j;
							} else
								break;
						} else
							break;
					}

					bij = false;
					i = 0;
					j = 0;

					while (x + i < 7 && y + j > 0) {
						if (p.pole[x + i + 1][y + j - 1] == 1) {
							if (bij)
								this.pole[x + i + 1][y + j - 1] = 3;
							++i;
							--j;
						} else if (p.pole[x + i + 1][y + j - 1] == 2 + add || p.pole[x + i + 1][y + j - 1] == 4 + add) {
							if (!bij) {
								bij = true;
								++i;
								--j;
							} else
								break;
						} else
							break;
					}

					bij = false;
					i = 0;
					j = 0;

					while (x + i > 0 && y + j < 7) {
						if (p.pole[x + i - 1][y + j + 1] == 1) {
							if (bij)
								this.pole[x + i - 1][y + j + 1] = 3;
							--i;
							++j;
						} else if (p.pole[x + i - 1][y + j + 1] == 2 + add || p.pole[x + i - 1][y + j + 1] == 4 + add) {
							if (!bij) {
								bij = true;
								--i;
								++j;
							} else
								break;
						} else
							break;
					}

					bij = false;
					i = 0;
					j = 0;

					while (x + i < 7 && y + j < 7) {
						if (p.pole[x + i + 1][y + j + 1] == 1) {
							if (bij)
								this.pole[x + i + 1][y + j + 1] = 3;
							++i;
							++j;
						} else if (p.pole[x + i + 1][y + j + 1] == 2 + add || p.pole[x + i + 1][y + j + 1] == 4 + add) {
							if (!bij) {
								bij = true;
								++i;
								++j;
							} else
								break;
						} else
							break;
					}
				}

				this.wyswietlanie();
			}

			old_x = x;
			old_y = y;
		} 
		else if (this.pole[x][y] == 2) {
			p.pole[x][y] = p.pole[old_x][old_y];
			p.pole[old_x][old_y] = 1;
			this.zerowanie();
			zamien_pionki_na_damki(p);
			this.zmiana_gracza();
		} else if (this.pole[x][y] == 3) {
			p.pole[x][y] = p.pole[old_x][old_y];
			int i = old_x;
			int j = old_y;
			int add_i = (old_x < x) ? 1 : -1;
			int add_j = (old_y < y) ? 1 : -1;
			int kill_x = i + add_i;
			int kill_y = j + add_j;
			while (kill_x != x && kill_y != y) {
				p.pole[kill_x][kill_y] = 1;
				kill_x += add_i;
				kill_y += add_j;
				System.out.println("kiler : " + kill_x + " " + kill_y);
			}
			p.pole[old_x][old_y] = 1;
			this.zerowanie();
			b.sprawdz_bicia(gracz, p, x, y);
			if (!b.przymus) {
				zamien_pionki_na_damki(p);
				this.zmiana_gracza();
			} else {
				klikniecie(x, y, p, b);
			}
		}

		b.sprawdz_bicia(gracz, p);
	}

	public void set_gracz(int gracz) {
		this.gracz = gracz;
		System.out.println("Gracz " + this.gracz);
	}

	public int get_gracz() {
		return this.gracz;
	}

	public void zmiana_gracza() {
		if (this.gracz == 1) {
			this.set_gracz(2);
		} else if (this.gracz == 2) {
			this.set_gracz(1);
		}
	}

	public void zamien_pionki_na_damki(Plansza p) {
		if (gracz == 1) {
			for (int i = 0; i < 8; i++)
				if (p.pole[i][0] == 2)
					p.pole[i][0] = 4;
		} else if (gracz == 2) {
			for (int i = 0; i < 8; i++)
				if (p.pole[i][7] == 3)
					p.pole[i][7] = 5;
		}
		p.wyswietlanie();
	}

}