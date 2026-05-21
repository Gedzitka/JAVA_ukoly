# Odpověď na otázky ze zadání

## Třída `HraPosunPoSachovnici`
Tato třída realizuje samotnou logiku hry a je nezávislá na typu uživatelského rozhraní.

### Atributy
- `vyska` – výška šachovnice, tedy počet řádků
- `sirka` – šířka šachovnice, tedy počet sloupců
- `x` – aktuální řádková souřadnice
- `y` – aktuální sloupcová souřadnice
- `historiePohybu` – pole pro ukládání historie pohybu
- `pocetZaznamu` – počet uložených záznamů historie

### Metody
- `HraPosunPoSachovnici(int vyska, int sirka)` – konstruktor, nastaví rozměry a výchozí polohu `[0;0]`
- `boolean posun(SmerPohybu smer)` – provede pohyb daným směrem, pokud to dovolí okraj šachovnice
- `String zobrazAktualniPolohu()` – vrátí aktuální polohu ve formátu `[x;y]`
- `String[] zobrazHistorii()` – vrátí historii pohybu jako pole řetězců
- `String zobrazHistoriiJakoText()` – vrátí historii jako jeden textový řetězec
- `int getX()` – vrátí aktuální hodnotu `x`
- `int getY()` – vrátí aktuální hodnotu `y`
- `int getVyska()` – vrátí výšku šachovnice
- `int getSirka()` – vrátí šířku šachovnice

## Třída `RealizaceHryKonzola`
Tato třída realizuje prezentační vrstvu a zajišťuje komunikaci s uživatelem v konzoli.

### Atributy
- vlastní atributy nejsou nutné, případně jen pomocná instance `NacteniHodnot`

### Metody
- `main(String[] args)` – hlavní metoda programu

### Úloha třídy
- načte výšku a šířku šachovnice
- vytvoří instanci hry `HraPosunPoSachovnici`
- zpracovává vstupy `L`, `R`, `U`, `D`, `K`
- po každém posunu vypíše aktuální polohu i historii pohybu
- ukončí hru po stisku `K`

## Třída `NacteniHodnot`
Tato třída slouží jako pomocná datová vstupní vrstva pro načítání hodnot ze standardního vstupu.

### Metody
- `int nactiCislo(String vyzva)` – načte celé číslo a ošetří neplatný vstup
- `String nactiText(String vyzva)` – načte textový vstup
- `void uzavri()` – uzavře vstupní scanner

## Výčtový typ `SmerPohybu`
Výčtový typ reprezentuje povolené směry pohybu.

### Hodnoty
- `left`
- `right`
- `up`
- `down`

### Metody
- `char getZnak()` – vrátí znak směru
- `static SmerPohybu fromVstup(String vstup)` – převede vstup `L`, `R`, `U`, `D` na odpovídající směr
