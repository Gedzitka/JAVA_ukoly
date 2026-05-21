public enum SmerPohybu {
    left('L'),
    right('R'),
    up('U'),
    down('D');

    private final char znak;

    SmerPohybu(char znak) {
        this.znak = znak;
    }

    public char getZnak() {
        return znak;
    }

    public static SmerPohybu fromVstup(String vstup) {
        if (vstup == null || vstup.trim().isEmpty()) {
            throw new IllegalArgumentException("Směr nebyl zadán.");
        }

        switch (vstup.trim().toUpperCase()) {
            case "L":
                return left;
            case "R":
                return right;
            case "U":
                return up;
            case "D":
                return down;
            default:
                throw new IllegalArgumentException("Neznámý směr pohybu: " + vstup);
        }
    }
}
