package oope2017ht.tiedot;

/**
  * Tiedosto-luokka periytyy Tieto-luokasta ja korvaa toString-metodin.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * Viimeksi muokattu 28.3.2017.
  * <p>
  * @author Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
  */

public class Tiedosto extends Tieto {

    /*
     * Attribuutti.
     */

    /**
      * Tiedoston koko tavuina.
      */
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
        this.koko(toinen.koko);
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

    /**
     * Korvattu toString-metodi kutsuu yliluokan toString-metodia.
     * @return merkkijono, joka koostuu tiedoston nimestä, välilyönnistä ja tiedoston koosta.
     */
    @Override
    public String toString() {
        return super.toString() + EROTIN + koko;
    }
}
