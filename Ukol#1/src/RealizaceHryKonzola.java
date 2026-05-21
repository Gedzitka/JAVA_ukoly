public class RealizaceHryKonzola {
    public static void main(String[] args) {
        NacteniHodnot nacteni = new NacteniHodnot();
        try {
            int vyska = nacteni.nactiCislo("Zadejte výšku šachovnice (nx): ");
            int sirka = nacteni.nactiCislo("Zadejte šířku šachovnice (ny): ");

            HraPosunPoSachovnici hra = new HraPosunPoSachovnici(vyska, sirka);

            System.out.println("Hra začíná. Použijte <L>, <R>, <U>, <D> pro pohyb. <K> pro ukončení.");
            System.out.println("Aktuální poloha: " + hra.zobrazAktualniPolohu());
            System.out.println("Historie pohybu: " + hra.zobrazHistoriiJakoText());

            while (true) {
                String vstup = nacteni.nactiText("Vaše volba: ").toUpperCase();

                if (vstup.equals("K")) {
                    System.out.println("Hra ukončena.");
                    break;
                }

                try {
                    SmerPohybu smer = SmerPohybu.fromVstup(vstup);
                    boolean provedeno = hra.posun(smer);

                    if (!provedeno) {
                        System.out.println("Pohyb není možný, jste na okraji šachovnice.");
                    }

                    System.out.println("Aktuální poloha: " + hra.zobrazAktualniPolohu());
                    System.out.println("Historie pohybu: " + hra.zobrazHistoriiJakoText());
                } catch (IllegalArgumentException vyjimka) {
                    System.out.println(vyjimka.getMessage());
                }
            }
        } catch (IllegalStateException vyjimka) {
            System.out.println("Chyba vstupu: " + vyjimka.getMessage());
        } finally {
            nacteni.uzavri();
        }
    }
}
