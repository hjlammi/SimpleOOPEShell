package oope2017ht;

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

    /*
     * Rakentaja.
     */
    public Tulkki(UI ui) {
        this.ui = ui;
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
            // Jos syöte ei ole mikään hyväksytyistä syötteistä tulostetaan
            // virheilmoitus.
            } else {
                ui.tulosta("Error!");
            }
        // Suoritetaan silmukkaa kunnes syöte on exit.
        } while (!syote.equals("exit"));
    }

}
