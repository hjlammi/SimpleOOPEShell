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
            if (!syote.equals("exit")) {
                ui.tulosta("Error!");
            }
        } while (!syote.equals("exit"));
    }

}
