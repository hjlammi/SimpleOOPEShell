package oope2017ht.tiedot;

/*
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
 *
 * Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
 *
 * Viimeksi muokattu 5.4.2017.
 *
 * Tieto-luokka, jonka ainoa attribuutti on nimi. Luokka toteuttaa Comparable-rajapinnan ja
 * korvaa toString- ja equals-metodit.
 */

public abstract class Tieto implements Comparable<Tieto>{

    // Vakioitu erotin toString-metodin käytettäväksi.
    protected final String EROTIN = " ";

    // Atribuutti nimelle.
    private StringBuilder nimi;

    /*
     * Rakentajat.
     */

    public Tieto(StringBuilder nimi) {
        this.nimi(nimi);
    }

    // Kopiorakentaja.
    public Tieto(Tieto toinen) {
        if (toinen == null) {
            throw new IllegalArgumentException();
        }
        this.nimi = new StringBuilder(toinen.nimi.toString());
    }

    /*
     * Aksessorit.
     */

    public void nimi(StringBuilder nimi) throws IllegalArgumentException{
        if (nimi != null && nimiOk(nimi)) {
            this.nimi = nimi;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public StringBuilder nimi() {
        return nimi;
    }

    /*
     * Apumetodit nimen oikeellisuuden tarkistamiseen. Public static -määreet, jotta voi käyttää yksikkötestausta.
     */

    // Tutkii, onko parametrina saatu merkkijono ok ja palauttaa tosi, jos merkkijono on ok
    // ja epätosi, jos merkkijono ei ole ok. Tarkistaa, että nimi on vähintään yhden mittainen ja
    // kutsuu apumetodeja, jotka tarkistavat, että nimessä on vain sallittuja merkkejä ja että
    // nimessä on maksimissaan yksi piste, mutta myös muita merkkejä kuin piste.
    public static boolean nimiOk(StringBuilder nimi) {
        if (nimi.toString().equals("/")) {
            return true;
        } else if ((nimi.length() > 0) && (vainSallittujaMerkkeja(nimi)) &&
           (pisteitaMaxYksi(nimi))) {
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
    // Paluuarvo on false, jos pisteitä on enemmän kuin yksi tai jos piste on nimen ainut
    // merkki.
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

            if (pisteidenLkm > 1) {
                return false;
            } else {
                return true;
            }
        }
    }

    /*
     * Korvatut metodit.
     */

    @Override
    public String toString() {
        return nimi.toString();
    }

    @Override
    public int compareTo(Tieto t) {
        String tama = this.nimi().toString();
        String toinen = t.nimi().toString();
        return tama.compareTo(toinen);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof Tieto) {
            String tama = this.nimi().toString();
            String toinen = ((Tieto)obj).nimi().toString();
            return tama.equals(toinen);
        } else {
            return false;
        }
    }

}
