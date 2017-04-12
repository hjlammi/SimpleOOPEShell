package oope2017ht;

import oope2017ht.omalista.OmaLista;
import oope2017ht.tiedot.Hakemisto;

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
            // Jos käyttäjän syöte on exit, tulostetaan lopetusviesti.
            if (syote.equals("exit")) {
                ui.tulosta("Shell terminated.");
            // Jos käyttäjän syöte on ls
            } else if (syote.equals("ls")) {
                // Viite juurihakemiston tietoihin.
                OmaLista tiedot = juurihakemisto.tiedot();

                // Tulostetaan juurihakemiston tiedot alkio kerrallaan.
                for (int i = 0; i < tiedot.koko(); i++) {
                    ui.tulosta(tiedot.alkio(i).toString());
                }
            // Jos syöte ei ole mikään hyväksytyistä syötteistä tulostetaan
            // virheilmoitus.
            } else {
                ui.tulosta("Error!");
            }
        // Suoritetaan silmukkaa kunnes syöte on exit.
        } while (!syote.equals("exit"));
    }

}
