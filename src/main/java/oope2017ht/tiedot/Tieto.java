package oope2017ht.tiedot;

/**
  * Tieto-luokka, jonka ainoa attribuutti on nimi. Luokka toteuttaa Comparable-rajapinnan ja
  * korvaa toString- ja equals-metodit.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * Viimeksi muokattu 5.4.2017.
  * <p>
  * @author Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
  */

public abstract class Tieto implements Comparable<Tieto>{

    /** Vakioitu erotin toString-metodin käytettäväksi.*/
    protected final String EROTIN = " ";

    /** Tiedon nimi*/
    private StringBuilder nimi;

    /*
     * Rakentajat.
     */

    /** Oletusrakentaja juurihakemiston nimen asettamista varten.*/
    public Tieto() {
        this.nimi = new StringBuilder("/");
    }

    public Tieto(StringBuilder nimi) {
        this.nimi(nimi);
    }

    // Kopiorakentaja.
    public Tieto(Tieto toinen) throws IllegalArgumentException {
        if (toinen == null) {
            throw new IllegalArgumentException();
        }
        this.nimi = new StringBuilder(toinen.nimi.toString());
    }

    /*
     * Aksessorit.
     */

    /**
     * Setteri nimelle. Kutsuu metodia, joka tarkistaa, että parametrina saatu nimi on oikeanlainen.
     * @param nimi
     * @throws IllegalArgumentException, jos nimi ei ole kelvollinen
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

    /**
     * Tutkii, onko parametrina saatu merkkijono ok. Tarkistaa, että nimi on vähintään yhden merkin mittainen ja
     * kutsuu apumetodeja, jotka tarkistavat, että nimessä on vain sallittuja merkkejä ja että
     * nimessä on maksimissaan yksi piste, mutta myös muita merkkejä kuin piste. ja palauttaa tosi, jos merkkijono on ok
     * @param nimi tarkistettava nimi
     * @return true, jos merkkijono on ok, muuten false
     */
    public static boolean nimiOk(StringBuilder nimi) {
        if ((nimi.length() > 0) && (vainSallittujaMerkkeja(nimi)) &&
           (pisteitaMaxYksi(nimi))) {
            return true;
        } else {
            return false;
        }
    }

    /**
      *  Tutkii onko merkkijonossa vain sallittuja merkkejä eli a-z, A-Z, 0-9, alaviiva tai piste.
      * @param nimi tarkistettava nimi
      * @return true, jos kaikki merkit ovat sallittuja, muuten false
      */
    public static boolean vainSallittujaMerkkeja(StringBuilder nimi) {
        boolean merkitOk = true;
        boolean loytyiVaaraMerkki = false;
        // Käydään nimeä läpi merkki merkiltä, kunnes löytyy vääränlainen merkki tai nimi on käyty läpi.
        for (int i = 0; i < nimi.length() && !loytyiVaaraMerkki; i++) {
            if ((nimi.charAt(i) >= 'a' && nimi.charAt(i) <= 'z') ||
            (nimi.charAt(i) >= 'A' && nimi.charAt(i) <= 'Z') ||
            (nimi.charAt(i) >= '0' && nimi.charAt(i) <= '9') ||
            (nimi.charAt(i) == '_') || (nimi.charAt(i) == '.')){
                // Jos tutkittava merkki on jokin ylläolevista, merkit ovat edelleen ok.
                merkitOk = true;
            } else {
                // Jos merkki on jokin muu kuin yllämainitut merkit, kaikki merkit eivät ole oikeanlaisia,
                // poistutaan silmukasta ja palautetaan false.
                merkitOk = false;
                loytyiVaaraMerkki = true;
            }
        }
        return merkitOk;
    }

    /**
     *  Tutkii onko nimessä piste-merkkejä vain yksi ja ettei piste ole nimen ainut merkki.
     * @param nimi tarkistettava nimi
     * @return false, jos pisteitä on enemmän kuin yksi tai jos piste on nimen ainut merkki, muuten true
     */
    public static boolean pisteitaMaxYksi(StringBuilder nimi) {
        // Muuttuja pisteiden lukumäärälle.
        int pisteidenLkm = 0;
        // Tarkistetaan, ettei piste ole nimen ainut merkki. Jos on, poistutaan metodista ja palautetaan false.
        if (nimi.length() == 1 && nimi.charAt(0) == '.') {
            return false;
        } else {
            // Käydään nimi läpi ja kasvatetaan laskuria, jos kohdataan piste.
            for (int i = 0; i < nimi.length(); i++) {
                if (nimi.charAt(i) == '.') {
                    pisteidenLkm++;
                }
            }

            // Jos pisteiden lukumäärä on enemmän kuin yksi, paluuarvo on false.
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

    /**
     * Korvattu toString-metodi, joka palauttaa tiedon nimen merkkijonona.
     * @return merkkijono, joka koostuu tiedon nimestä.
     */
    @Override
    public String toString() {
        return nimi.toString();
    }

    /**
     * Korvattu compareTo-metodi, jolla vertaillaan tietojen nimiä keskenään.
     * @return vertailtavien merkkijonojen etäisyys niiden merkkien välisenä etäisyytenä.
     */
    @Override
    public int compareTo(Tieto t) {
        String tama = this.nimi().toString();
        String toinen = t.nimi().toString();
        // Vertaillaan merkkijonoja toisiinsa ja paluuarvo on vertailtavien merkkijonojen etäisyys.
        return tama.compareTo(toinen);
    }

    /**
     * Korvattu equals-metodi, jolla vertaillaan tietojen nimiä keskenään: oliot ovat samat,
     * jos niiden nimet ovat merkilleen samat.
     * @return true, jos olioiden nimet ovat samat, false, jos nimissä on eroa
     */
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
