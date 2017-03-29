package oope2017ht;

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
            syote = ui.lueSyote();
            if (!syote.equals("exit")) {
                ui.tulosta("Error!");
            }
        } while (!syote.equals("exit"));
    }

}
