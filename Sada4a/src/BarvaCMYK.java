/**
 * Třída reprezentující barvu v modelu CMYK.
 * Složky c (azurová), m (purpurová), y (žlutá), k (černá) jsou v rozsahu <0.0; 1.0>.
 *
 * Formát stavu: c:0.00;m:0.00;y:0.00;k:0.00
 */
public class BarvaCMYK extends Barva {

    private double c;  // Cyan
    private double m;  // Magenta
    private double y;  // Yellow
    private double k;  // Key (Black)

    /**
     * Vytvoří barvu CMYK. Všechny složky musí být v rozsahu <0.0; 1.0>.
     */
    public BarvaCMYK(double c, double m, double y, double k) {
        this.c = orez(c);
        this.m = orez(m);
        this.y = orez(y);
        this.k = orez(k);
    }

    // Gettery
    public double getC() { return c; }
    public double getM() { return m; }
    public double getY() { return y; }
    public double getK() { return k; }

    // Settery
    public void setC(double c) { this.c = orez(c); }
    public void setM(double m) { this.m = orez(m); }
    public void setY(double y) { this.y = orez(y); }
    public void setK(double k) { this.k = orez(k); }

    /**
     * Intenzita = 1 - k (čím větší složka K/černá, tím nižší intenzita).
     */
    @Override
    public double getIntenzita() {
        return 1.0 - k;
    }

    /**
     * Stav ve formátu c:0.00;m:0.00;y:0.00;k:0.00.
     */
    @Override
    public String getStav() {
        return String.format("c:%.2f;m:%.2f;y:%.2f;k:%.2f", c, m, y, k);
    }

    /** Ořízne hodnotu do rozsahu <0.0; 1.0>. */
    private static double orez(double hodnota) {
        return Math.max(0.0, Math.min(1.0, hodnota));
    }
}
