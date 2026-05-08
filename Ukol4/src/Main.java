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

        NadrzOperace nadrz = new Nadrz(100, TypObsahu.VODA, 25);
        System.out.println("Vytvořena nádrž: " + nadrz.getStav());

        try {
            nadrz.plnit(30);
            System.out.println("Po naplnění: " + nadrz.getStav());

            nadrz.odebrat(20);
            System.out.println("Po odebrání: " + nadrz.getStav());

            nadrz.odebrat(60);
        } catch (MyException_PlnaNadrz | MyException_PrazdnaNadrz e) {
            System.err.println(e.getMessage());
        }

        try {
            nadrz.plnit(80);
        } catch (MyException_PlnaNadrz e) {
            System.err.println(e.getMessage());
        }
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
