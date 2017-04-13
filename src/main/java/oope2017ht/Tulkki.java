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
* Viimeksi muokattu 5.4.2017.
*
*
*/

public class Tulkki {

    private UI ui;

    private Hakemisto juurihakemisto;

    /*
     * Rakentaja.
     */
    public Tulkki(UI ui) {
        this.ui = ui;
        this.juurihakemisto = new Hakemisto(new StringBuilder("/"), null);
    }

    public Tulkki(UI ui, Hakemisto juurihakemisto) {
        this.ui = ui;
        this.juurihakemisto = juurihakemisto;
    }

    public void suorita() {
        ui.tulosta("Welcome to SOS.");

        String syote;
        do {
            syote = ui.lueSyote(">");
            String osat[] = syote.split(" ");
            if (syote.endsWith(" ") || syote.startsWith(" ")) {
                error();
            // Jos käyttäjän syöte on exit, tulostetaan lopetusviesti.
            } else if (osat[0].equals("exit") && osat.length == 1) {
                ui.tulosta("Shell terminated.");
            // Jos käyttäjän syöte on ls
            } else if (osat[0].equals("ls") && osat.length == 1) {
                // Viite juurihakemiston tietoihin.
                OmaLista tiedot = juurihakemisto.tiedot();

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
                for (int i = 0; i < juurihakemisto.tiedot().koko() && !loytyi; i++) {
                    Object tieto = juurihakemisto.tiedot().alkio(i);
                    String tiedonNimi = ((Tieto)tieto).nimi().toString();
                    if (nimi.equals(tiedonNimi)) {
                        ui.tulosta(juurihakemisto.tiedot().alkio(i).toString());
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
                boolean onnistui = juurihakemisto.lisaa(lisattava);
                if (!onnistui) {
                    error();
                }
            // Jos käyttäjän syöte on mf...
            } else if (osat[0].equals("mf") && osat.length == 3) {
                String nimi = osat[1];
                int koko = Integer.parseInt(osat[2]);
                Tiedosto lisattava = new Tiedosto(new StringBuilder(nimi), koko);
                juurihakemisto.lisaa(lisattava);
            // Jos syöte ei ole mikään hyväksytyistä syötteistä tulostetaan
            // virheilmoitus.
            } else {
                error();
            }
        // Suoritetaan silmukkaa kunnes syöte on exit.
        } while (!syote.equals("exit"));
    }

    private void error() {
        ui.tulosta("Error!");
    }

}
