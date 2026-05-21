import java.util.Arrays;

public class HraPosunPoSachovnici {
    private final int vyska;
    private final int sirka;
    private int x, y;
    private String[] historiePohybu;
    private int pocetZaznamu;

    public HraPosunPoSachovnici(int vyska, int sirka) {
        if (vyska <= 0 || sirka <= 0) {
            throw new IllegalArgumentException("Rozměry šachovnice musí být kladné.");
        }

        this.vyska = vyska;
        this.sirka = sirka;
        this.x = 0;
        this.y = 0;
        this.historiePohybu = new String[8];
        this.pocetZaznamu = 0;
        pridatDoHistorie();
    }

    public boolean posun(SmerPohybu smer) {
        int novyX = x;
        int novyY = y;

        switch (smer) {
            case left:
                if (y > 0) {
                    novyY--;
                }
                break;
            case right:
                if (y < sirka - 1) {
                    novyY++;
                }
                break;
            case up:
                if (x > 0) {
                    novyX--;
                }
                break;
            case down:
                if (x < vyska - 1) {
                    novyX++;
                }
                break;
        }

        if (novyX == x && novyY == y) {
            return false;
        }

        x = novyX;
        y = novyY;
        pridatDoHistorie();
        return true;
    }

    public String zobrazAktualniPolohu() {
        return "[" + x + ";" + y + "]";
    }

    public String[] zobrazHistorii() {
        return Arrays.copyOf(historiePohybu, pocetZaznamu);
    }

    public String zobrazHistoriiJakoText() {
        return String.join(" -> ", zobrazHistorii());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVyska() {
        return vyska;
    }

    public int getSirka() {
        return sirka;
    }

    private void pridatDoHistorie() {
        if (pocetZaznamu == historiePohybu.length) {
            historiePohybu = Arrays.copyOf(historiePohybu, historiePohybu.length * 2);
        }

        historiePohybu[pocetZaznamu] = zobrazAktualniPolohu();
        pocetZaznamu++;
    }
}
