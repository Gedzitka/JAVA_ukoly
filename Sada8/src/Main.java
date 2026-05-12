import java.util.Arrays;

/**
 * Konzolová aplikace pro vyzkoušení parsovacích metod.
 * Sada č. 8
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("=== Sada 8 – Parsování textových údajů ===");
        System.out.println();

        // -------------------------------------------------------------------------
        // 1a: parseJmenoPrijmeni1 – jméno a příjmení oddělené jednou mezerou
        // -------------------------------------------------------------------------
        System.out.println("--- 1a) parseJmenoPrijmeni1 ---");
        ParseUtils.parseJmenoPrijmeni1("Jan Novák");
        System.out.println();

        // -------------------------------------------------------------------------
        // 1b: parseJmenoPrijmeni2 – libovolný počet mezer, i na krajích
        // -------------------------------------------------------------------------
        System.out.println("--- 1b) parseJmenoPrijmeni2 ---");
        ParseUtils.parseJmenoPrijmeni2("  Jana   Nováková  ");
        System.out.println();

        // Funguje i pro formát z 1a?
        System.out.println("-- parseJmenoPrijmeni2 s jednou mezerou (jako 1a): --");
        ParseUtils.parseJmenoPrijmeni2("Petr Dvořák");
        System.out.println();

        // -------------------------------------------------------------------------
        // 1c: parseJmenoPrijmeniVrat – vrátí hodnoty místo výpisu
        // Návratový typ: String[] – pole dvou prvků
        // -------------------------------------------------------------------------
        System.out.println("--- 1c) parseJmenoPrijmeniVrat ---");
        String[] jmPr = ParseUtils.parseJmenoPrijmeniVrat("  Marie   Horáková  ");
        System.out.println("Jméno:    '" + jmPr[0] + "'");
        System.out.println("Příjmení: '" + jmPr[1] + "'");
        System.out.println();

        // -------------------------------------------------------------------------
        // 1d: parseLogin – loginy oddělené středníkem (klasické pole)
        // -------------------------------------------------------------------------
        System.out.println("--- 1d) parseLogin ---");
        String[] loginy = ParseUtils.parseLogin("jan123;petra_k;tomas99;admin");
        System.out.println("Loginy: " + Arrays.toString(loginy));
        System.out.println();

        // -------------------------------------------------------------------------
        // 1e1: parseCislaInt – celá čísla oddělená středníkem
        // -------------------------------------------------------------------------
        System.out.println("--- 1e1) parseCislaInt ---");
        int[] cislaInt = ParseUtils.parseCislaInt("10;20;-5;100;0");
        System.out.println("Celá čísla: " + Arrays.toString(cislaInt));
        System.out.println();

        // -------------------------------------------------------------------------
        // 1e2: parseCislaDouble – reálná čísla oddělená středníkem
        // -------------------------------------------------------------------------
        System.out.println("--- 1e2) parseCislaDouble ---");
        double[] cislaDouble = ParseUtils.parseCislaDouble("1.5;3.14;-2.7;0.0;100.99");
        System.out.println("Reálná čísla: " + Arrays.toString(cislaDouble));
    }
}
