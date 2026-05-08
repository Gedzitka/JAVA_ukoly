abstract class BarvaRGB extends Barva {
    protected int r, g, b;

    public BarvaRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    // Metoda pro převod barvy na hexadecimální formát
    @Override
    public String toHex() {
        return String.format("r:%02X;g:%02X;b:%02X", r, g, b);
    }
}
