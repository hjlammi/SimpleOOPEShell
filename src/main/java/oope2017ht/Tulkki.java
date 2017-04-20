package oope2017ht;

import oope2017ht.omalista.OmaLista;
import oope2017ht.tiedot.Hakemisto;
import oope2017ht.tiedot.Tiedosto;
import oope2017ht.tiedot.Tieto;

/*
* Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
*
* Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
*
* Viimeksi muokattu 20.4.2017.
*
*
*/

public class Tulkki {

    /*
     *  Vakiot.
     */

    private static final String REKURSIIVINEN_LISTAAMINEN = "find";


    private static final String HAKEMISTON_VAIHTAMINEN = "cd";


    private static final String POISTAMINEN = "rm";


    private static final String KOPIOIMINEN = "cp";


    private static final String UUDELLEEN_NIMEAMINEN = "mv";


    private static final String TIEDOSTON_LUOMINEN = "mf";


    private static final String HAKEMISTON_LUOMINEN = "md";


    private static final String LISTAAMINEN = "ls";


    private static final String LOPETUS = "exit";




    private static final String KEHOTE = ">";

    private static final String TERVEHDYS = "Welcome to SOS.";

    private static final String LOPETUSVIESTI = "Shell terminated.";


    /*
     * Attribuutit.
     */

    private UI ui;

    private Hakemisto juurihakemisto;

    private Hakemisto tyohakemisto;

    /*
     * Rakentajat.
     */
    public Tulkki(UI ui) {
        this.ui = ui;
        this.juurihakemisto = new Hakemisto(new StringBuilder("/"), null);
        this.tyohakemisto = juurihakemisto;
    }

    public Tulkki(UI ui, Hakemisto juurihakemisto) {
        this.ui = ui;
        this.juurihakemisto = juurihakemisto;
        this.tyohakemisto = juurihakemisto;
    }

    // Aksessorit.

    public Hakemisto tyohakemisto() {
        return tyohakemisto;
    }

    public void tyohakemisto(Hakemisto tyohakemisto) {
        if (tyohakemisto != null) {
            this.tyohakemisto = tyohakemisto;
        }
    }

    public void suorita() {
        ui.tulosta(TERVEHDYS);

        String syote;
        do {
            // Kutsutaan metodia, tulostaa näytölle nykyisen työhakemiston hakemistopolun sekä kehotteen ja
            // asettaa viitten käyttäjän antamaan syötteeseen.
            syote = ui.lueSyote(tyohakemisto.hakemistopolku() + KEHOTE);
            // Pilkotaan syöte osiin välilyöntien kohdalta ja tallennetaan syötteen osat taulukkoon.
            String osat[] = syote.split(" ");
            try {
                // Jos syöte alkaa tai loppuu välilyöntiin, kutsutaan error-metodia, joka tulostaa virheilmoituksen.
                if (syote.endsWith(" ") || syote.startsWith(" ")) {
                    error();
                // Jos käyttäjän syöte on exit eikä syötteessä ollut muita osia, tulostetaan lopetusviesti.
                } else if (osat[0].equals(LOPETUS) && osat.length == 1) {
                    ui.tulosta(LOPETUSVIESTI);
                // Jos käyttäjän syöte on ls
                } else if (osat[0].equals(LISTAAMINEN) && osat.length == 1) {
                    // Viite työhakemiston tietoihin.
                    OmaLista tiedot = tyohakemisto.tiedot();

                    // Tulostetaan työhakemiston tiedot alkio kerrallaan.
                    for (int i = 0; i < tiedot.koko(); i++) {
                        ui.tulosta(tiedot.alkio(i).toString());
                    }
                } else if (osat[0].equals(LISTAAMINEN) && osat.length == 2) {
                    // Viite listattavan tiedon nimeen.
                    String nimi = osat[1];
                    Tieto alkio = tyohakemisto.hae(nimi);
                    // Jos nimeä vastaavaa tietoa ei löytynyt hakemistosta tulostetaan virheilmoitus.
                    if (alkio == null) {
                        error();
                    } else {
                        ui.tulosta(alkio.toString());
                    }
                } else if (osat[0].equals(HAKEMISTON_LUOMINEN) && osat.length == 2) {
                    String nimi = osat[1];
                    Hakemisto lisattava = new Hakemisto(new StringBuilder(nimi), tyohakemisto);
                    boolean onnistui = tyohakemisto.lisaa(lisattava);
                    if (!onnistui) {
                        error();
                    }
                // Jos käyttäjän syöte on mf...
                } else if (osat[0].equals(TIEDOSTON_LUOMINEN) && osat.length == 3) {
                    String nimi = osat[1];
                    int koko = Integer.parseInt(osat[2]);
                    Tiedosto lisattava = new Tiedosto(new StringBuilder(nimi), koko);
                    boolean onnistui = tyohakemisto.lisaa(lisattava);
                    if (!onnistui) {
                        error();
                    }
                // Jos käyttäjän syöte on mv...
                } else if (osat[0].equals(UUDELLEEN_NIMEAMINEN) && osat.length == 3) {
                    String vaihdettavaNimi = osat[1];
                    String uusiNimi = osat[2];
                    if (nimiVarattu(uusiNimi)) {
                        error();
                    } else {
                        // Jos hakemistosta löytyy tieto annetulla parametrilla,
                        // asetetaan alkiolle uusi nimi.
                        Tieto alkio = (Tieto)tyohakemisto.hae(vaihdettavaNimi);
                           if (alkio == null) {
                               error();
                           } else {
                               alkio.nimi(new StringBuilder(uusiNimi));
                           }
                    }
                } else if (osat[0].equals(KOPIOIMINEN) && osat.length == 3) {
                    String nimi = osat[1];
                    String kopioNimi = osat[2];
                    Tieto kopioitava = tyohakemisto.hae(nimi);
                    // Vain Tiedoston voi kopioida, joten tarkistetaan että ollaan kopioimassa
                    // tiedostoa.
                    if (kopioitava != null && kopioitava instanceof Tiedosto && !nimiVarattu(kopioNimi)) {
                        // Syväkopioidaan tiedosto.
                        Tiedosto kopio = new Tiedosto((Tiedosto)kopioitava);
                        // Annetaan kopiolle nimeksi komentoriviparametrina saatu uusi nimi.
                        kopio.nimi(new StringBuilder(kopioNimi));
                        // Lisätään kopio hakemistoon.
                        tyohakemisto.lisaa(kopio);
                    } else {
                        error();
                    }
                } else if (osat[0].equals(POISTAMINEN) && osat.length == 2) {
                    String poistettava = osat[1];
                    Tieto poistettavaTieto = tyohakemisto.poista(poistettava);
                    if (poistettavaTieto == null) {
                        error();
                    }
                } else if (osat[0].equals(HAKEMISTON_VAIHTAMINEN) && osat.length == 1) {
                    tyohakemisto(juurihakemisto);
                } else if (osat[0].equals(HAKEMISTON_VAIHTAMINEN) && !osat[1].equals("..") && osat.length == 2) {
                    String nimi = osat[1];
                    Tieto alkio = tyohakemisto.hae(nimi);
                    // Tarkistetaan, että hakemistosta löytyy senniminen alihakemisto, johon halutaan siirtyä.
                    if (alkio != null && alkio instanceof Hakemisto) {
                        tyohakemisto = (Hakemisto)alkio;
                    } else {
                        error();
                    }
                } else if (osat[0].equals(HAKEMISTON_VAIHTAMINEN) && osat[1].equals("..") && osat.length == 2) {
                    if (tyohakemisto == juurihakemisto) {
                        error();
                    } else {
                        Hakemisto nykyinenHakemisto = tyohakemisto();
                        tyohakemisto(nykyinenHakemisto.ylihakemisto());
                    }
                } else if (osat[0].equals(REKURSIIVINEN_LISTAAMINEN) && osat.length == 1) {
                    puunTulostus(tyohakemisto);
                // Jos syöte ei ole mikään hyväksytyistä syötteistä tulostetaan
                // virheilmoitus.
                } else {
                    error();
                }
            } catch(IllegalArgumentException e) {
                error();
            }
        // Suoritetaan silmukkaa kunnes syöte on exit.
        } while (!syote.equals(LOPETUS));
    }

    private void puunTulostus(Hakemisto tyohakemisto) {
        OmaLista sisalto = tyohakemisto.tiedot();
        int i = 0;
        while (i < sisalto.koko()) {
            Tieto tieto = (Tieto)sisalto.alkio(i);
            ui.tulosta(tyohakemisto.hakemistopolku() + tieto.toString());
            if (tieto instanceof Hakemisto) {
                puunTulostus((Hakemisto)tieto);
            }
            i++;
        }
    }

    // Apumetodi mv- ja cp-komentojen käytettäväksi. Tutkii onko hakemistossa jo parametrina
    // annetulla nimellä tiedosto tai hakemisto. Jos samanniminen löytyy, palautetaan
    // true, jos samannimistä ei löydy, palautetaan false.
    private boolean nimiVarattu(String uusiNimi) {
        Tieto alkio = (Tieto)tyohakemisto.hae(uusiNimi);
        if (alkio == null) {
            return false;
        } else {
            return true;
        }
    }

    private void error() {
        ui.tulosta("Error!");
    }


}
