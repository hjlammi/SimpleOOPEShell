package oope2017ht;

import oope2017ht.omalista.OmaLista;
import oope2017ht.tiedot.Hakemisto;
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
            if (syote.endsWith(" ")) {
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
                String nimi = osat[1];
                for (int i = 0; i < juurihakemisto.tiedot().koko(); i++) {
                    Object tieto = juurihakemisto.tiedot().alkio(i);
                    String tiedonNimi = ((Tieto)tieto).nimi().toString();
                    if (nimi.equals(tiedonNimi)) {
                        ui.tulosta(juurihakemisto.tiedot().alkio(i).toString());
                    }
                }
            } else if (osat[0].equals("md") && osat.length == 2) {
                String nimi = osat[1];
                Hakemisto lisattava = new Hakemisto(new StringBuilder(nimi), juurihakemisto);
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
