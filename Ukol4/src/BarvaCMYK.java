class BarvaCMYK extends Barva {
    private double c, m, y, k;

    public BarvaCMYK(double c, double m, double y, double k) {
        this.c = c;
        this.m = m;
        this.y = y;
        this.k = k;
    }

    @Override
    public double getIntenzita() {
        return 1 - Math.min(1, c + k); // Intenzita v modelu CMYK
    }

    @Override
    public String toHex() {
        return String.format("c:%.2f;m:%.2f;y:%.2f;k:%.2f", c, m, y, k);
    }
}
