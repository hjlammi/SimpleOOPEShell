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
* Viimeksi muokattu 13.4.2017.
*
*
*/

public class Tulkki {

    private UI ui;

    private Hakemisto juurihakemisto;

    private Hakemisto tyohakemisto;

    /*
     * Rakentaja.
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
        ui.tulosta("Welcome to SOS.");

        String syote;
        do {
            syote = ui.lueSyote(">");
            String osat[] = syote.split(" ");
            try {
                if (syote.endsWith(" ") || syote.startsWith(" ")) {
                    error();
                // Jos käyttäjän syöte on exit, tulostetaan lopetusviesti.
                } else if (osat[0].equals("exit") && osat.length == 1) {
                    ui.tulosta("Shell terminated.");
                // Jos käyttäjän syöte on ls
                } else if (osat[0].equals("ls") && osat.length == 1) {
                    // Viite juurihakemiston tietoihin.
                    OmaLista tiedot = tyohakemisto.tiedot();

                    // Tulostetaan juurihakemiston tiedot alkio kerrallaan.
                    for (int i = 0; i < tiedot.koko(); i++) {
                        ui.tulosta(tiedot.alkio(i).toString());
                    }
                } else if (osat[0].equals("ls") && osat.length == 2) {
                    // Viite listattavan tiedon nimeen.
                    String nimi = osat[1];
                    // Tietoa ei ole vielä löytynyt.
                    boolean loytyi = false;
                    // Käydään hakemistoa läpi, kunnes nimeä vastaava tieto löytyy.
                    for (int i = 0; i < tyohakemisto.tiedot().koko() && !loytyi; i++) {
                        Object tieto = tyohakemisto.tiedot().alkio(i);
                        String tiedonNimi = ((Tieto)tieto).nimi().toString();
                        if (nimi.equals(tiedonNimi)) {
                            ui.tulosta(tyohakemisto.tiedot().alkio(i).toString());
                            loytyi = true;
                        }
                    }
                    // Jos nimeä vastaavaa tietoa ei löytynyt hakemistosta tulostetaan virheilmoitus.
                    if (!loytyi) {
                        error();
                    }
                } else if (osat[0].equals("md") && osat.length == 2) {
                    String nimi = osat[1];
                    Hakemisto lisattava = new Hakemisto(new StringBuilder(nimi), juurihakemisto);
                    boolean onnistui = tyohakemisto.lisaa(lisattava);
                    if (!onnistui) {
                        error();
                    }
                // Jos käyttäjän syöte on mf...
                } else if (osat[0].equals("mf") && osat.length == 3) {
                    String nimi = osat[1];
                    int koko = Integer.parseInt(osat[2]);
                    Tiedosto lisattava = new Tiedosto(new StringBuilder(nimi), koko);
                    boolean onnistui = tyohakemisto.lisaa(lisattava);
                    if (!onnistui) {
                        error();
                    }
                // Jos käyttäjän syöte on mv...
                } else if (osat[0].equals("mv") && osat.length == 3) {
                    String vaihdettavaNimi = osat[1];
                    String uusiNimi = osat[2];
                    if (nimiVarattu(uusiNimi)) {
                        error();
                    } else {
                        for (int i = 0; i < tyohakemisto.tiedot().koko(); i++) {
                            Tieto alkio = (Tieto)tyohakemisto.tiedot().alkio(i);
                            // Jos hakemistosta löytyy tieto annetulla parametrilla,
                            // asetetaan alkiolle uusi nimi.
                            if (vaihdettavaNimi.equals(alkio.nimi().toString())) {
                                alkio.nimi(new StringBuilder(uusiNimi));
                            }
                        }
                    }
                } else if (osat[0].equals("cp") && osat.length == 3) {
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
                } else if (osat[0].equals("rm") && osat.length == 2) {
                    String poistettava = osat[1];
                    Tieto poistettavaTieto = tyohakemisto.poista(poistettava);
                    if (poistettavaTieto == null) {
                        error();
                    }
                } else if (osat[0].equals("cd") && osat.length == 1) {
                    tyohakemisto(juurihakemisto);
                } else if (osat[0].equals("cd") && !osat[1].equals("..") && osat.length == 2) {
                    String nimi = osat[1];
                    Tieto alkio = tyohakemisto.hae(nimi);
                    if (alkio instanceof Hakemisto) {
                        tyohakemisto = (Hakemisto)alkio;
                    }
                } else if (osat[0].equals("cd") && osat[1].equals("..") && osat.length == 2) {
                    Hakemisto nykyinenHakemisto = tyohakemisto();
                    tyohakemisto(nykyinenHakemisto.ylihakemisto());
                // Jos syöte ei ole mikään hyväksytyistä syötteistä tulostetaan
                // virheilmoitus.
                } else {
                    error();
                }
            } catch(IllegalArgumentException e) {
                error();
            }
        // Suoritetaan silmukkaa kunnes syöte on exit.
        } while (!syote.equals("exit"));
    }

    // Apumetodi mv- ja cp-komentojen käytettäväksi. Tutkii onko hakemistossa jo parametrina
    // annetulla nimellä tiedosto tai hakemisto. Jos samanniminen löytyy, palautetaan
    // true, jos samannimistä ei löydy, palautetaan false.
    private boolean nimiVarattu(String uusiNimi) {
        for (int i = 0; i < juurihakemisto.tiedot().koko(); i++) {
            Tieto alkio = (Tieto)juurihakemisto.tiedot().alkio(i);
            if (uusiNimi.equals(alkio.nimi().toString())) {
                return true;
            }
        }
        return false;
    }

    private void error() {
        ui.tulosta("Error!");
    }


}
