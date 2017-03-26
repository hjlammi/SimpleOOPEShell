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
    // false, jos löytyy vääriä merkkejä.
    public static boolean vainSallittujaMerkkeja(StringBuilder mjono) {
        boolean vainKirjaimiaOk = true;
        boolean loytyiVaaraMerkki = false;
        for (int i = 0; i < mjono.length() && !loytyiVaaraMerkki; i++) {
            if ((mjono.charAt(i) >= 'a' && mjono.charAt(i) <= 'z') ||
               (mjono.charAt(i) >= 'A' && mjono.charAt(i) <= 'Z')) {
                vainKirjaimiaOk = true;
            } else {
                vainKirjaimiaOk = false;
                loytyiVaaraMerkki = true;
            }
        }
        return vainKirjaimiaOk;
    }
}
