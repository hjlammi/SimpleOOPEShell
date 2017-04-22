package oope2017ht;

import oope2017ht.tiedot.Hakemisto;

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

    // Metodilla asetetaan työhakemistoksi juurihakemisto.
    public void siirryJuurihakemistoon() {
        tyohakemisto(juurihakemisto);
    }
}
