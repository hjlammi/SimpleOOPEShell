package oope2017ht.tiedot;

public abstract class Tieto implements Comparable<Tieto>{

    // Atribuutti nimelle.
    private StringBuilder nimi;

    // Rakentajat.

    public Tieto(StringBuilder nimi) {
        this.nimi(nimi);
    }

    // Aksessorit.

    public void nimi(StringBuilder nimi) {
    }

    public StringBuilder nimi() {
        return nimi;
    }

    // Tutkii, onko parametrina saatu merkkijono ok ja palauttaa tosi, jos merkkijono on ok
    // ja epätosi, jos merkkijono ei ole ok.
    private boolean nimiOk(StringBuilder mjono) {
        return true;
    }
}
