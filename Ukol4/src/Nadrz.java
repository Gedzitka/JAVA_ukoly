import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class Nadrz implements NadrzOperace, Cloneable {
    private double stav;
    private final int kapacita;
    private final String oznaceni;
    private final TypObsahu typObsahu;
    private final List<ZaznamOperace> historiePridani;
    private final List<ZaznamOperace> historieOdebrani;

    public Nadrz(int kapacita, String oznaceni) {
        this(kapacita, oznaceni, 0.0);
    }

    public Nadrz(int kapacita, String oznaceni, double stav) {
        this(kapacita, oznaceni, stav, null);
    }

    public Nadrz(int kapacita, TypObsahu typObsahu) {
        this(kapacita, typObsahu == null ? null : typObsahu.name(), 0.0, typObsahu);
    }

    public Nadrz(int kapacita, TypObsahu typObsahu, double stav) {
        this(kapacita, typObsahu == null ? null : typObsahu.name(), stav, typObsahu);
    }

    public Nadrz(int kapacita, TypObsahu typObsahu, int stav) {
        this(kapacita, typObsahu == null ? null : typObsahu.name(), stav, typObsahu);
    }

    private Nadrz(int kapacita, String oznaceni, double stav, TypObsahu typObsahu) {
        if (kapacita <= 0) {
            throw new IllegalArgumentException("Kapacita nádrže musí být kladná.");
        }
        if (oznaceni == null || oznaceni.trim().isEmpty()) {
            throw new IllegalArgumentException("Označení nádrže nesmí být prázdné.");
        }
        if (stav < 0) {
            throw new IllegalArgumentException("Stav nádrže nesmí být záporný.");
        }
        if (stav > kapacita) {
            throw new IllegalArgumentException("Stav nádrže nesmí být větší než kapacita.");
        }

        this.kapacita = kapacita;
        this.oznaceni = oznaceni.trim();
        this.typObsahu = typObsahu;
        this.stav = stav;
        this.historiePridani = new ArrayList<>();
        this.historieOdebrani = new ArrayList<>();
    }

    private Nadrz(Nadrz other) {
        this.kapacita = other.kapacita;
        this.oznaceni = other.oznaceni;
        this.typObsahu = other.typObsahu;
        this.stav = other.stav;
        this.historiePridani = new ArrayList<>();
        for (ZaznamOperace zaznam : other.historiePridani) {
            this.historiePridani.add(zaznam.kopie());
        }
        this.historieOdebrani = new ArrayList<>();
        for (ZaznamOperace zaznam : other.historieOdebrani) {
            this.historieOdebrani.add(zaznam.kopie());
        }
    }

    @Override
    public void plnit(double mnozstvi) throws PlnaNadrzException {
        if (mnozstvi < 0) {
            throw new IllegalArgumentException("Množství pro plnění nesmí být záporné.");
        }
        if (stav + mnozstvi > kapacita) {
            throw new PlnaNadrzException("Nelze plnit nádrž " + getStav() + ". Požadováno přidání: " + formatCislo(mnozstvi) + ".");
        }
        stav += mnozstvi;
        historiePridani.add(new ZaznamOperace(LocalDateTime.now(), mnozstvi));
    }

    @Override
    public void odebrat(double mnozstvi) throws PrazdnaNadrzException {
        if (mnozstvi < 0) {
            throw new IllegalArgumentException("Množství pro odebrání nesmí být záporné.");
        }
        if (stav - mnozstvi < 0) {
            throw new PrazdnaNadrzException("Nelze odebírat z nádrže " + getStav() + ". Požadováno odebrání: " + formatCislo(mnozstvi) + ".");
        }
        stav -= mnozstvi;
        historieOdebrani.add(new ZaznamOperace(LocalDateTime.now(), mnozstvi));
    }

    @Override
    public double getAktualniStav() {
        return stav;
    }

    @Override
    public int getKapacita() {
        return kapacita;
    }

    @Override
    public String getOznaceni() {
        return oznaceni;
    }

    @Override
    public String getStav() {
        return oznaceni + " : " + formatCislo(stav) + " / " + kapacita;
    }

    public List<ZaznamOperace> getHistoriePridani() {
        return kopieHistorie(historiePridani);
    }

    public List<ZaznamOperace> getHistorieOdebrani() {
        return kopieHistorie(historieOdebrani);
    }

    public List<ZaznamOperace> getHistorieVse() {
        List<ZaznamOperace> vse = new ArrayList<>(historiePridani.size() + historieOdebrani.size());
        vse.addAll(getHistoriePridani());
        vse.addAll(getHistorieOdebrani());
        vse.sort(Comparator.comparing(ZaznamOperace::getDatumCas));
        return vse;
    }

    @Override
    public String getSouhrnPridani() {
        return vytvorSouhrn("Přidání", getHistoriePridani());
    }

    @Override
    public String getSouhrnOdebrani() {
        return vytvorSouhrn("Odebrání", getHistorieOdebrani());
    }

    @Override
    public String getSouhrnVse() {
        List<ZaznamOperace> vse = getHistorieVse();
        return vytvorSouhrn("Vše", vse);
    }

    @Override
    public Nadrz clone() {
        return new Nadrz(this);
    }

    public Nadrz kopie() {
        return clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Nadrz)) {
            return false;
        }
        Nadrz nadrz = (Nadrz) o;
        return kapacita == nadrz.kapacita
                && Double.compare(nadrz.stav, stav) == 0
                && Objects.equals(oznaceni, nadrz.oznaceni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kapacita, oznaceni, stav);
    }

    @Override
    public String toString() {
        return getStav();
    }

    static String formatCislo(double hodnota) {
        if (Double.isNaN(hodnota) || Double.isInfinite(hodnota)) {
            return String.valueOf(hodnota);
        }
        return java.math.BigDecimal.valueOf(hodnota).stripTrailingZeros().toPlainString();
    }

    private static List<ZaznamOperace> kopieHistorie(List<ZaznamOperace> historie) {
        List<ZaznamOperace> kopie = new ArrayList<>(historie.size());
        for (ZaznamOperace zaznam : historie) {
            kopie.add(zaznam.kopie());
        }
        return kopie;
    }

    private String vytvorSouhrn(String nadpis, List<ZaznamOperace> historie) {
        double celkem = 0.0;
        StringBuilder builder = new StringBuilder();
        builder.append(nadpis).append(" [").append(oznaceni).append("]");
        if (historie.isEmpty()) {
            return builder.append(": žádné záznamy").toString();
        }

        for (ZaznamOperace zaznam : historie) {
            celkem += zaznam.getMnozstvi();
            builder.append(System.lineSeparator()).append(zaznam);
        }
        builder.append(System.lineSeparator())
                .append("Celkem: ")
                .append(formatCislo(celkem));
        return builder.toString();
    }
}
