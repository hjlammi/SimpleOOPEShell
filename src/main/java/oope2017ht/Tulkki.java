package oope2017ht;

import oope2017ht.tiedot.Hakemisto;
import oope2017ht.tiedot.Tieto;

public class Tulkki {

    /*
     *  Vakiot.
     */

    static final String REKURSIIVINEN_LISTAAMINEN = "find";
    static final String HAKEMISTON_VAIHTAMINEN = "cd";
    static final String POISTAMINEN = "rm";
    static final String KOPIOIMINEN = "cp";
    static final String UUDELLEEN_NIMEAMINEN = "mv";
    static final String TIEDOSTON_LUOMINEN = "mf";
    static final String HAKEMISTON_LUOMINEN = "md";
    static final String LISTAAMINEN = "ls";
    static final String LOPETUS = "exit";
    static final String KEHOTE = ">";
    static final String TERVEHDYS = "Welcome to SOS.";
    static final String LOPETUSVIESTI = "Shell terminated.";
    static final String VIRHEILMOITUS = "Error!";

    Hakemisto tyohakemisto;
    Hakemisto juurihakemisto;

    /*
     * Aksessorit.
     */
    public void tyohakemisto(Hakemisto hakemisto) {
        if (hakemisto != null) {
            this.tyohakemisto = hakemisto;
        }
    }

    public Hakemisto tyohakemisto() {
        return tyohakemisto;
    }

    /*
     * Apumetodit.
     */

    // Apumetodi uudelleennimeämis- ja kopioimiskomentojen käytettäväksi. Tutkii onko hakemistossa jo parametrina
    // annetulla nimellä tiedosto tai hakemisto. Jos samanniminen löytyy, palautetaan
    // true, jos samannimistä ei löydy, palautetaan false.
    boolean nimiVarattu(String nimi) {
        // Kutsutaan Hakemiston hae-metodia, joka palauttaa nullin, jos haettavalla nimellä ei löydy Tietoa.
        Tieto alkio = tyohakemisto.hae(nimi);
        // Samannimistä ei löytynyt.
        if (alkio == null) {
            return false;
        } else {
            return true;
        }
    }

    // Metodilla asetetaan työhakemistoksi juurihakemisto.
    public void siirryJuurihakemistoon() {
        tyohakemisto(juurihakemisto);
    }

    // Metodilla asetetaan työhakemistoksi nykyisen hakemiston ylihakemisto.
    public boolean siirryYlihakemistoon() {
        // Juurihakemistosta ei voi siirtyä ylihakemistoon.
        if (tyohakemisto == juurihakemisto) {
            return false;
        } else {
            // Asetetaan viite nykyiseen hakemistoon.
            Hakemisto nykyinenHakemisto = tyohakemisto();
            // Asetetaan työhakemistoksi nykyisen hakemiston ylihakemisto.
            tyohakemisto(nykyinenHakemisto.ylihakemisto());
            return true;
        }
    }

    // Metodilla asetetaan työhakemistoksi alihakemisto, jonka nimi on parametrina saadun taulukon toinen alkio.
    public boolean siirryAlihakemistoon(String[] osat) {
        // Sen alihakemiston nimi, johon halutaan siirtyä.
        String nimi = osat[1];
        // Haetaan hakemistosta nimellä.
        Tieto alkio = tyohakemisto.hae(nimi);
        // Tarkistetaan, että hakemistosta löytyy senniminen alihakemisto, johon halutaan siirtyä
        // ja että tieto on tyyppiä Hakemisto.
        if (alkio != null && alkio instanceof Hakemisto) {
            tyohakemisto((Hakemisto)alkio);
            return true;
        } else {
            return false;
        }
    }
}
