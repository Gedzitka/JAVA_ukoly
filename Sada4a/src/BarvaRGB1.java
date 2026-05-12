/**
 * Potomek BarvaRGB – výpočet intenzity jako aritmetický průměr složek R, G, B.
 *
 * Intenzita = (r + g + b) / 3
 */
public class BarvaRGB1 extends BarvaRGB {

    public BarvaRGB1(int r, int g, int b) {
        super(r, g, b);
    }

    /**
     * Intenzita = průměr tří složek.
     */
    @Override
    public double getIntenzita() {
        return (r + g + b) / 3.0;
    }
}
