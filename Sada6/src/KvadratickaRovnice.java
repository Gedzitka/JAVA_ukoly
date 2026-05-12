/**
 * Třída reprezentující kvadratickou rovnici ve tvaru ax² + bx + c = 0.
 * Umožňuje vytvořit objekt rovnice, číst a měnit hodnoty parametrů a, b, c.
 */
public class KvadratickaRovnice {

    private double a;
    private double b;
    private double c;

    /**
     * Vytvoří kvadratickou rovnici se zadanými koeficienty.
     *
     * @param a koeficient u x²
     * @param b koeficient u x
     * @param c absolutní člen
     */
    public KvadratickaRovnice(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // -------------------------------------------------------------------------
    // Gettery
    // -------------------------------------------------------------------------

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }

    // -------------------------------------------------------------------------
    // Settery – umožní změnu parametrů
    // -------------------------------------------------------------------------

    public void setA(double a) { this.a = a; }
    public void setB(double b) { this.b = b; }
    public void setC(double c) { this.c = c; }

    /**
     * Vrátí textovou reprezentaci rovnice, např. "2.0x² + 3.0x - 1.0 = 0".
     */
    @Override
    public String toString() {
        return a + "x^2 + " + b + "x + " + c + " = 0";
    }
}
