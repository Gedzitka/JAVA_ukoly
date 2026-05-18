import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * POMOCNÁ TŘÍDA (datová/prezentační vrstva)
 *
 * Zajišťuje načítání hodnot různých typů ze standardního vstupu.
 * Ošetřuje chybové stavy – při špatném formátu vstupu vyzve uživatele znovu.
 */
public class NacteniHodnot {

    private final Scanner scanner;

    public NacteniHodnot() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Načte celé číslo ze vstupu. Opakuje výzvu při špatném formátu.
     */
    public int nactiInt(String vyzva) {
        while (true) {
            System.out.print(vyzva);
            try {
                return Integer.parseInt(nactiRadek());
            } catch (NumberFormatException e) {
                System.out.println("Spatny format – zadejte cele cislo.");
            }
        }
    }

    /**
     * Načte celé číslo větší než zadané minimum.
     */
    public int nactiIntMin(String vyzva, int min) {
        while (true) {
            int hodnota = nactiInt(vyzva);
            if (hodnota >= min) {
                return hodnota;
            }
            System.out.println("Hodnota musi byt alespon " + min + ".");
        }
    }

    /**
     * Načte reálné číslo ze vstupu. Opakuje výzvu při špatném formátu.
     * Přijímá jak tečku, tak čárku jako desetinný oddělovač.
     */
    public double nactiDouble(String vyzva) {
        while (true) {
            System.out.print(vyzva);
            try {
                String vstup = nactiRadek().replace(',', '.');
                return Double.parseDouble(vstup);
            } catch (NumberFormatException e) {
                System.out.println("Spatny format – zadejte realne cislo (napr. 19.90).");
            }
        }
    }

    /**
     * Načte reálné číslo větší nebo rovno zadanému minimu.
     */
    public double nactiDoubleMin(String vyzva, double min) {
        while (true) {
            double hodnota = nactiDouble(vyzva);
            if (hodnota >= min) {
                return hodnota;
            }
            System.out.printf("Hodnota musi byt alespon %.2f.%n", min);
        }
    }

    /**
     * Načte textový řetězec ze vstupu.
     */
    public String nactiText(String vyzva) {
        System.out.print(vyzva);
        return nactiRadek();
    }

    /**
     * Načte neprázdný textový řetězec.
     */
    public String nactiTextNeprazdny(String vyzva) {
        while (true) {
            String vstup = nactiText(vyzva);
            if (!vstup.isEmpty()) {
                return vstup;
            }
            System.out.println("Hodnota nesmi byt prazdna.");
        }
    }

    public void uzavri() {
        scanner.close();
    }

    private String nactiRadek() {
        try {
            return scanner.nextLine().trim();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("Vstup byl ukoncen.", e);
        }
    }
}
