/**
 * Třída reprezentující komplexní číslo ve tvaru a + bi.
 * Umožňuje sčítání, odčítání a násobení komplexních čísel navzájem i se skalárem.
 */
public class KomplexniCislo {

    private double realna;
    private double imaginarni;

    /**
     * Vytvoří komplexní číslo se zadanou reálnou a imaginární složkou.
     *
     * @param realna     reálná složka
     * @param imaginarni imaginární složka
     */
    public KomplexniCislo(double realna, double imaginarni) {
        this.realna = realna;
        this.imaginarni = imaginarni;
    }

    // -------------------------------------------------------------------------
    // Sčítání
    // -------------------------------------------------------------------------

    /**
     * Vrátí součet tohoto a zadaného komplexního čísla.
     * (a + bi) + (c + di) = (a+c) + (b+d)i
     */
    public KomplexniCislo plus(KomplexniCislo other) {
        return new KomplexniCislo(this.realna + other.realna,
                                  this.imaginarni + other.imaginarni);
    }

    /**
     * Vrátí součet tohoto komplexního čísla a reálného skaláru.
     * (a + bi) + k = (a+k) + bi
     */
    public KomplexniCislo plus(double skalar) {
        return new KomplexniCislo(this.realna + skalar, this.imaginarni);
    }

    // -------------------------------------------------------------------------
    // Odčítání
    // -------------------------------------------------------------------------

    /**
     * Vrátí rozdíl tohoto a zadaného komplexního čísla.
     * (a + bi) - (c + di) = (a-c) + (b-d)i
     */
    public KomplexniCislo minus(KomplexniCislo other) {
        return new KomplexniCislo(this.realna - other.realna,
                                  this.imaginarni - other.imaginarni);
    }

    /**
     * Vrátí rozdíl tohoto komplexního čísla a reálného skaláru.
     * (a + bi) - k = (a-k) + bi
     */
    public KomplexniCislo minus(double skalar) {
        return new KomplexniCislo(this.realna - skalar, this.imaginarni);
    }

    // -------------------------------------------------------------------------
    // Násobení
    // -------------------------------------------------------------------------

    /**
     * Vrátí součin tohoto a zadaného komplexního čísla.
     * (a + bi)(c + di) = (ac - bd) + (ad + bc)i
     */
    public KomplexniCislo krat(KomplexniCislo other) {
        double novaRealna     = this.realna * other.realna - this.imaginarni * other.imaginarni;
        double novaImaginarni = this.realna * other.imaginarni + this.imaginarni * other.realna;
        return new KomplexniCislo(novaRealna, novaImaginarni);
    }

    /**
     * Vrátí součin tohoto komplexního čísla a reálného skaláru.
     * (a + bi) * k = (a*k) + (b*k)i
     */
    public KomplexniCislo krat(double skalar) {
        return new KomplexniCislo(this.realna * skalar, this.imaginarni * skalar);
    }

    // -------------------------------------------------------------------------
    // Pomocné metody
    // -------------------------------------------------------------------------

    /** Vrátí absolutní hodnotu (modulus) komplexního čísla: sqrt(a² + b²). */
    public double absolutniHodnota() {
        return Math.sqrt(realna * realna + imaginarni * imaginarni);
    }

    public double getRealna() {
        return realna;
    }

    public double getImaginarni() {
        return imaginarni;
    }

    /**
     * Vrátí textovou reprezentaci ve tvaru "a + bi" nebo "a - bi".
     */
    @Override
    public String toString() {
        if (imaginarni >= 0) {
            return realna + " + " + imaginarni + "i";
        } else {
            return realna + " - " + (-imaginarni) + "i";
        }
    }
}
