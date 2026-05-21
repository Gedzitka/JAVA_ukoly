/**
 * PREZENTAČNÍ VRSTVA
 *
 * Konzolová aplikace pro generování pokladního dokladu (účtenky).
 *
 * Architektura (třívrstvá):
 *   Prezentační vrstva : RealizaceKonzola  – komunikace s uživatelem
 *   Logická vrstva     : PokladniDoklad    – zpracování a sestavení dokladu
 *   Datová vrstva      : PolozkaDokladu    – data jedné nákupní položky
 */
public class RealizaceKonzola {

    public static void main(String[] args) {

        NacteniHodnot nacteni = new NacteniHodnot();
        PokladniDoklad doklad = new PokladniDoklad();

        System.out.println("======================================");
        System.out.println("  POKLADNI SYSTEM – zadavani polozek");
        System.out.println("======================================");
        System.out.println("Zadavejte nakupni polozky.");
        System.out.println("Pro ukonceni zadavani nechte nazev prazdny (stisknete Enter).");
        System.out.println();

        // Smyčka pro zadávání položek
        while (true) {
            System.out.println("--- Nova polozka ---");
            String nazev = nacteni.nactiText("Nazev zbozi (nebo Enter pro konec): ");

            // Prázdný název = konec zadávání
            if (nazev.isEmpty()) {
                break;
            }

            int    mnozstvi  = nacteni.nactiIntMin("Pocet kusu: ", 1);
            double cena      = nacteni.nactiDoubleMin("Cena za kus bez DPH (Kc): ", 0.0);
            double sazbaDph  = nactiSazbuDph(nacteni);

            doklad.pridejPolozku(nazev, mnozstvi, cena, sazbaDph);
            System.out.println("Polozka pridana.\n");
        }

        // Vygenerování a tisk dokladu
        System.out.println();
        String textDokladu = doklad.generujPokladniDoklad();
        tiskPokladniDoklad(textDokladu);

        nacteni.uzavri();
    }

    /**
     * Vytiskne hotový pokladní doklad na standardní výstup.
     *
     * @param pokladniDoklad text dokladu vygenerovaný metodou generujPokladniDoklad()
     */
    public static void tiskPokladniDoklad(String pokladniDoklad) {
        System.out.println(pokladniDoklad);
    }

    // -------------------------------------------------------------------------
    // Pomocné metody prezentační vrstvy
    // -------------------------------------------------------------------------

    /**
     * Zobrazí nabídku sazeb DPH a nechá uživatele vybrat.
     * Běžné sazby DPH v ČR: 0 %, 12 %, 21 %
     */
    private static double nactiSazbuDph(NacteniHodnot nacteni) {
        System.out.println("Sazba DPH:");
        System.out.println("  1) 21 %  (standardni)");
        System.out.println("  2) 12 %  (snizena)");
        System.out.println("  3)  0 %  (osvobozeno)");
        System.out.println("  4) Zadat vlastni");

        while (true) {
            int volba = nacteni.nactiInt("Vase volba (1-4): ");
            switch (volba) {
                case 1: return 21.0;
                case 2: return 12.0;
                case 3: return 0.0;
                case 4: return nacteni.nactiDoubleMin("Zadejte sazbu DPH (%): ", 0.0);
                default:
                    System.out.println("Neplatna volba, zadejte 1-4.");
            }
        }
    }
}
