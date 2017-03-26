package oope2017ht.tiedot;

public abstract class Tieto implements Comparable<Tieto>{

    // Atribuutti nimelle.
    private StringBuilder nimi;

    // Rakentajat.

    public Tieto(StringBuilder nimi) {
        this.nimi(nimi);
    }

    // Aksessorit.

    public void nimi(StringBuilder nimi) {
    }

    public StringBuilder nimi() {
        return nimi;
    }

    /*
     * Apumetodit nimen testaamiseen. Public static -määreet, jotta voi käyttää yksikkötestausta.
     */

    // Tutkii, onko parametrina saatu merkkijono ok ja palauttaa tosi, jos merkkijono on ok
    // ja epätosi, jos merkkijono ei ole ok.
    public static boolean nimiOk(StringBuilder nimi) {
        if ((nimi.length() > 0) && (vainSallittujaMerkkeja(nimi)) && (pisteitaMaxYksi(nimi))) {
            return true;
        } else {
            return false;
        }
    }

    // Tutkii onko merkkijonossa vain sallittuja merkkejä. Palauttaa tosi, jos kaikki merkit ovat ok
    // false, jos löytyy yksikin väärä merkki.
    public static boolean vainSallittujaMerkkeja(StringBuilder nimi) {
        boolean merkitOk = true;
        boolean loytyiVaaraMerkki = false;
        for (int i = 0; i < nimi.length() && !loytyiVaaraMerkki; i++) {
            if ((nimi.charAt(i) >= 'a' && nimi.charAt(i) <= 'z') ||
            (nimi.charAt(i) >= 'A' && nimi.charAt(i) <= 'Z') ||
            (nimi.charAt(i) >= '0' && nimi.charAt(i) <= '9') ||
            (nimi.charAt(i) == '_') || (nimi.charAt(i) == '.')){
                merkitOk = true;
            } else {
                merkitOk = false;
                loytyiVaaraMerkki = true;
            }
        }
        return merkitOk;
    }

    // Tutkii onko nimessä piste-merkkejä vain yksi ja ettei piste ole nimen ainut merkki.
    // Paluu arvo on false, jos pisteitä on enemmän kuin yksi tai jos piste on nimen ainut merkki.
    public static boolean pisteitaMaxYksi(StringBuilder nimi) {
        int pisteidenLkm = 0;
        if (nimi.length() == 1 && nimi.charAt(0) == '.') {
            return false;
        } else {
            for (int i = 0; i < nimi.length(); i++) {
                if (nimi.charAt(i) == '.') {
                    pisteidenLkm++;
                }
            }
            System.out.println(pisteidenLkm);

            if (pisteidenLkm > 1) {
                return false;
            } else {
                return true;
            }
        }
    }
}
