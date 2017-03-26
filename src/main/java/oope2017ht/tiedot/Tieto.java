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
    public static boolean nimiOk(StringBuilder mjono) {
        if (mjono.length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    // Tutkii onko merkkijonossa vain sallittuja merkkejä. Palauttaa tosi, jos kaikki merkit ovat ok
    // false, jos löytyy yksikin väärä merkki.
    public static boolean vainSallittujaMerkkeja(StringBuilder mjono) {
        boolean merkitOk = true;
        boolean loytyiVaaraMerkki = false;
        for (int i = 0; i < mjono.length() && !loytyiVaaraMerkki; i++) {
            if ((mjono.charAt(i) >= 'a' && mjono.charAt(i) <= 'z') ||
            (mjono.charAt(i) >= 'A' && mjono.charAt(i) <= 'Z') ||
            (mjono.charAt(i) >= '0' && mjono.charAt(i) <= '9') ||
            mjono.charAt(i) == '_'){
                merkitOk = true;
            } else {
                merkitOk = false;
                loytyiVaaraMerkki = true;
            }
        }
        return merkitOk;
    }

    public static boolean pisteitaMaxYksi(StringBuilder mjono) {
        int pisteidenLkm = 0;
        for (int i = 0; i < mjono.length(); i++) {
            if (mjono.charAt(i) == '.') {
                pisteidenLkm++;
            }
        }

        if (pisteidenLkm > 1) {
            return false;
        } else {
            return true;
        }
    }
}
