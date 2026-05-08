interface NadrzOperace {
    void plnit(int mnozstvi) throws MyException_PlnaNadrz;

    void odebrat(int mnozstvi) throws MyException_PrazdnaNadrz;

    int getAktualniStav();

    int getKapacita();

    TypObsahu getTypObsahu();

    String getStav();
}