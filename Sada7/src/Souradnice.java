/**
 * Třída pro uchování dvojice souřadnic [x, y] na šachovnici.
 * Má pouze parametrický konstruktor (bez výchozího).
 */
public class Souradnice {

    private final int x;
    private final int y;

    /**
     * Vytvoří souřadnici s hodnotami x a y.
     *
     * @param x řádková souřadnice
     * @param y sloupcová souřadnice
     */
    public Souradnice(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Formát výstupu: [x;y]
     */
    @Override
    public String toString() {
        return "[" + x + ";" + y + "]";
    }
}
