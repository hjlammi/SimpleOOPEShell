package oope2017ht.tiedot;

public class Tiedosto extends Tieto {

    // Attribuutti tiedoston koolle tavuina.
    private int koko;

    /*
     * Rakentajat.
     */

    public Tiedosto(StringBuilder nimi, int koko) {
        super(nimi);
        this.koko(koko);
    }

    // Kopiorakentaja.
    public Tiedosto(Tiedosto toinen) {
        super(toinen);
        this.koko(koko);
    }

    /*
     * Aksessorit.
     */

    public void koko(int koko) throws IllegalArgumentException {
        if (koko >= 0) {
            this.koko = koko;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int koko() {
        return koko;
    }

    /*
     * Korvatut metodit.
     */
    @Override
    public String toString() {
        return super.toString() + EROTIN + koko;
    }
}
