import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

final class ZaznamOperace {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final LocalDateTime datumCas;
    private final double mnozstvi;

    ZaznamOperace(LocalDateTime datumCas, double mnozstvi) {
        this.datumCas = datumCas;
        this.mnozstvi = mnozstvi;
    }

    ZaznamOperace(ZaznamOperace other) {
        this(other.datumCas, other.mnozstvi);
    }

    LocalDateTime getDatumCas() {
        return datumCas;
    }

    double getMnozstvi() {
        return mnozstvi;
    }

    ZaznamOperace kopie() {
        return new ZaznamOperace(this);
    }

    @Override
    public String toString() {
        return FORMATTER.format(datumCas) + " | " + Nadrz.formatCislo(mnozstvi);
    }
}