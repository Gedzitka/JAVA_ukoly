class Nadrz implements NadrzOperace {
    private int stav;
    private final int kapacita;
    private final TypObsahu typObsahu;

    public Nadrz(int kapacita, TypObsahu typObsahu) {
        this(kapacita, typObsahu, 0);
    }

    public Nadrz(int kapacita, TypObsahu typObsahu, int stav) {
        if (kapacita <= 0) {
            throw new IllegalArgumentException("Kapacita nádrže musí být kladná.");
        }
        if (typObsahu == null) {
            throw new IllegalArgumentException("Typ obsahu nesmí být prázdný.");
        }
        if (stav < 0) {
            throw new IllegalArgumentException("Stav nádrže nesmí být záporný.");
        }
        if (stav > kapacita) {
            throw new IllegalArgumentException("Stav nádrže nesmí být větší než kapacita.");
        }

        this.kapacita = kapacita;
        this.typObsahu = typObsahu;
        this.stav = stav;
    }

    @Override
    public void plnit(int mnozstvi) throws MyException_PlnaNadrz {
        if (mnozstvi < 0) {
            throw new IllegalArgumentException("Množství pro plnění nesmí být záporné.");
        }
        if (stav + mnozstvi > kapacita) {
            throw new MyException_PlnaNadrz("Nelze plnit nádrž " + getStav() + ". Požadováno přidání: " + mnozstvi + ".");
        }
        stav += mnozstvi;
    }

    @Override
    public void odebrat(int mnozstvi) throws MyException_PrazdnaNadrz {
        if (mnozstvi < 0) {
            throw new IllegalArgumentException("Množství pro odebrání nesmí být záporné.");
        }
        if (stav - mnozstvi < 0) {
            throw new MyException_PrazdnaNadrz("Nelze odebírat z nádrže " + getStav() + ". Požadováno odebrání: " + mnozstvi + ".");
        }
        stav -= mnozstvi;
    }

    @Override
    public int getAktualniStav() {
        return stav;
    }

    @Override
    public int getKapacita() {
        return kapacita;
    }

    @Override
    public TypObsahu getTypObsahu() {
        return typObsahu;
    }

    @Override
    public String getStav() {
        int procenta = (int) Math.round((stav * 100.0) / kapacita);
        return String.format("%d/%d(%d %%),%s", stav, kapacita, procenta, typObsahu);
    }

    @Override
    public String toString() {
        return getStav();
    }
}
