import javax.swing.SwingUtilities;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String rezim = args.length > 0 ? args[0].trim().toLowerCase() : vyberRezim();

        if ("gui".equals(rezim)) {
            SwingUtilities.invokeLater(() -> new NadrzGUI().setVisible(true));
            return;
        }

        if (!"konsole".equals(rezim)) {
            System.out.println("Neznámý režim, spouštím konzolovou ukázku.");
        }

        Nadrz nadrz = new Nadrz(100, "Hlavní nádrž", 25.5);
        System.out.println("Vytvořena nádrž: " + nadrz.getStav());

        try {
            nadrz.plnit(30.25);
            System.out.println("Po naplnění: " + nadrz.getStav());

            nadrz.odebrat(20.5);
            System.out.println("Po odebrání: " + nadrz.getStav());

            Nadrz kopie = nadrz.kopie();
            System.out.println("Kopie: " + kopie.getStav());
            System.out.println("Stejný objekt: " + (nadrz == kopie));
            System.out.println("Stejný obsah: " + nadrz.equals(kopie));

            nadrz.odebrat(60);
        } catch (PlnaNadrzException | PrazdnaNadrzException e) {
            System.err.println(e.getMessage());
        }

        try {
            nadrz.plnit(80);
        } catch (PlnaNadrzException e) {
            System.err.println(e.getMessage());
        }

        System.out.println(nadrz.getSouhrnPridani());
        System.out.println(nadrz.getSouhrnOdebrani());
        System.out.println(nadrz.getSouhrnVse());
    }

    private static String vyberRezim() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Zadejte režim spuštění (konsole/gui): ");
        String vstup = scanner.nextLine().trim().toLowerCase();
        if (!"gui".equals(vstup) && !"konsole".equals(vstup)) {
            return "konsole";
        }

        return vstup;
    }
}
