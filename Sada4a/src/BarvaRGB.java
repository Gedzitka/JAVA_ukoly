/**
 * Třída reprezentující barvu v modelu RGB.
 * Složky r, g, b jsou v rozsahu <0; 255>.
 *
 * Formát stavu: r:HH;g:HH;b:HH nebo #HHHHHH (hex).
 *
 * Hodnotu barvy lze měnit (mutery setR, setG, setB).
 * Potomci BarvaRGB1 a BarvaRGB2 realizují konkrétní výpočet intenzity.
 *
 * Pozn.: použijeme int (ne byte) kvůli znaménkovému rozsahu byte v Javě (<-128;127>).
 *        Hodnoty jsou interně omezeny na <0;255>.
 */
public class BarvaRGB extends Barva {

    protected int r;
    protected int g;
    protected int b;

    /**
     * Vytvoří barvu RGB se zadanými složkami.
     * Hodnoty mimo rozsah <0;255> jsou automaticky oříznuty.
     */
    public BarvaRGB(int r, int g, int b) {
        this.r = orez(r);
        this.g = orez(g);
        this.b = orez(b);
    }

    // Gettery
    public int getR() { return r; }
    public int getG() { return g; }
    public int getB() { return b; }

    // Settery – hodnotu barvy lze měnit
    public void setR(int r) { this.r = orez(r); }
    public void setG(int g) { this.g = orez(g); }
    public void setB(int b) { this.b = orez(b); }

    /**
     * Výchozí výpočet intenzity: průměr složek.
     * Potomci mohou přepsat tuto metodu.
     */
    @Override
    public double getIntenzita() {
        return (r + g + b) / 3.0;
    }

    /**
     * Stav ve formátu r:HH;g:HH;b:HH (hexadecimální hodnoty).
     */
    @Override
    public String getStav() {
        return String.format("r:%02X;g:%02X;b:%02X", r, g, b);
    }

    /**
     * Alternativní formát #HHHHHH.
     */
    public String getStavHex() {
        return String.format("#%02X%02X%02X", r, g, b);
    }

    // -------------------------------------------------------------------------
    // Privátní pomocná metoda
    // -------------------------------------------------------------------------

    /** Ořízne hodnotu do rozsahu <0; 255>. */
    protected static int orez(int hodnota) {
        return Math.max(0, Math.min(255, hodnota));
    }
}
