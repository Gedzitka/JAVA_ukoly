/**
 * Abstraktní třída reprezentující barvu obecně.
 * Rodičovská třída pro BarvaRGB a BarvaCMYK.
 *
 * Objektový model:
 *   Barva -> BarvaRGB -> BarvaRGB1, BarvaRGB2
 *         -> BarvaCMYK
 */
public abstract class Barva {

    /**
     * Vrátí intenzitu barvy (reálné číslo).
     * Každý potomek definuje vlastní způsob výpočtu.
     */
    public abstract double getIntenzita();

    /**
     * Vrátí textový popis stavu objektu (formát závisí na potomkovi).
     */
    public abstract String getStav();

    @Override
    public String toString() {
        return getStav();
    }
}
