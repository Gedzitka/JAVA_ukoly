class BarvaRGB1 extends BarvaRGB {
    public BarvaRGB1(int r, int g, int b) {
        super(r, g, b);
    }

    @Override
    public double getIntenzita() {
        // Průměr složek RGB
        return (r + g + b) / 3.0;
    }
}
