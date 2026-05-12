/**
 * Statická třída s výpočty pro kvadratickou rovnici ax² + bx + c = 0.
 *
 * Metody jsou dostupné ve dvou variantách:
 *   a) přijímají koeficienty a, b, c jako samostatné parametry
 *   b) přijímají objekt KvadratickaRovnice
 */
public class KvadratickaRovniceVypocty {

    // Třída je čistě statická – zabraňujeme instanciaci
    private KvadratickaRovniceVypocty() {
    }

    // =========================================================================
    // f(x) = ax² + bx + c
    // =========================================================================

    /**
     * Spočítá hodnotu f(x) pro zadanou kvadratickou rovnici a hodnotu x.
     */
    public static double fx(double a, double b, double c, double x) {
        return a * x * x + b * x + c;
    }

    /** Varianta s objektem rovnice. */
    public static double fx(KvadratickaRovnice rovnice, double x) {
        return fx(rovnice.getA(), rovnice.getB(), rovnice.getC(), x);
    }

    // =========================================================================
    // Diskriminant
    // =========================================================================

    /** Spočítá diskriminant D = b² - 4ac. */
    public static double diskriminant(double a, double b, double c) {
        return b * b - 4 * a * c;
    }

    /** Varianta s objektem rovnice. */
    public static double diskriminant(KvadratickaRovnice rovnice) {
        return diskriminant(rovnice.getA(), rovnice.getB(), rovnice.getC());
    }

    // =========================================================================
    // Počet kořenů
    // =========================================================================

    /**
     * Určí počet reálných kořenů rovnice:
     *   D > 0  → 2 kořeny
     *   D = 0  → 1 kořen (dvojnásobný)
     *   D < 0  → žádný reálný kořen
     *
     * Pokud a = 0, jde o lineární rovnici – vrací speciální hodnotu -1.
     */
    public static int pocetKorenu(double a, double b, double c) {
        if (a == 0) {
            return -1; // není kvadratická rovnice
        }
        double d = diskriminant(a, b, c);
        if (d > 0) return 2;
        if (d == 0) return 1;
        return 0;
    }

    /** Varianta s objektem rovnice. */
    public static int pocetKorenu(KvadratickaRovnice rovnice) {
        return pocetKorenu(rovnice.getA(), rovnice.getB(), rovnice.getC());
    }

    // =========================================================================
    // Hodnoty kořenů
    // =========================================================================

    /**
     * Vrátí pole reálných kořenů rovnice:
     *   - prázdné pole, pokud D < 0 nebo a = 0
     *   - pole s jedním prvkem, pokud D = 0
     *   - pole se dvěma prvky, pokud D > 0
     *
     * Kořeny: x = (-b ± √D) / (2a)
     */
    public static double[] koreny(double a, double b, double c) {
        if (a == 0) {
            return new double[0];
        }
        double d = diskriminant(a, b, c);
        if (d < 0) {
            return new double[0];
        }
        if (d == 0) {
            return new double[]{ -b / (2 * a) };
        }
        double sqrtD = Math.sqrt(d);
        return new double[]{
            (-b + sqrtD) / (2 * a),
            (-b - sqrtD) / (2 * a)
        };
    }

    /** Varianta s objektem rovnice. */
    public static double[] koreny(KvadratickaRovnice rovnice) {
        return koreny(rovnice.getA(), rovnice.getB(), rovnice.getC());
    }
}
