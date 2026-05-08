import java.util.Scanner;
import java.util.NoSuchElementException;

public class NacteniHodnot {
    private final Scanner scanner;

    public NacteniHodnot() {
        this.scanner = new Scanner(System.in);
    }

    public int nactiCislo(String vyzva) {
        while (true) {
            System.out.print(vyzva);
            try {
                String vstup = nactiRadek();
                return Integer.parseInt(vstup);
            } catch (NumberFormatException vyjimka) {
                System.out.println("Neplatný vstup, zadejte celé číslo.");
            }
        }
    }

    public String nactiText(String vyzva) {
        System.out.print(vyzva);
        return nactiRadek();
    }

    public void uzavri() {
        scanner.close();
    }

    private String nactiRadek() {
        try {
            return scanner.nextLine().trim();
        } catch (NoSuchElementException vyjimka) {
            throw new IllegalStateException("Vstup byl ukončen.", vyjimka);
        }
    }
}
