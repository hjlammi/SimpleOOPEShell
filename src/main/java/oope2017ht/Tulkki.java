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
    }

}
