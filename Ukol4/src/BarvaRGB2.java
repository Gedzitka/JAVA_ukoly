class BarvaRGB2 extends BarvaRGB {
    public BarvaRGB2(int r, int g, int b) {
        super(r, g, b);
    }

    @Override
    public double getIntenzita() {
        // Vážený průměr složek RGB
        return 0.3 * r + 0.59 * g + 0.11 * b;
    }
}
