/**
 * Potomek BarvaRGB – výpočet intenzity jako vnímaná jasnost (luminance).
 *
 * Intenzita = 0.299*R + 0.587*G + 0.114*B
 *
 * Koeficienty odpovídají citlivosti lidského oka – zelená složka je vnímána
 * jako nejjasnější, modrá jako nejméně jasná.
 */
public class BarvaRGB2 extends BarvaRGB {

    public BarvaRGB2(int r, int g, int b) {
        super(r, g, b);
    }

    /**
     * Intenzita = vážený průměr dle luminance (BT.601).
     */
    @Override
    public double getIntenzita() {
        return 0.299 * r + 0.587 * g + 0.114 * b;
    }
}
