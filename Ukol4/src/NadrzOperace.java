interface NadrzOperace {
    void plnit(double mnozstvi) throws PlnaNadrzException;

    void odebrat(double mnozstvi) throws PrazdnaNadrzException;

    double getAktualniStav();

    int getKapacita();

    String getOznaceni();

    String getStav();

    String getSouhrnPridani();

    String getSouhrnOdebrani();

    String getSouhrnVse();
}