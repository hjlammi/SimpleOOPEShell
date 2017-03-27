package oope2017ht.tiedot;

import oope2017ht.OmaLista;

import apulaiset.Komennettava;

public class Hakemisto extends Tieto /*implements Komennettava<Tieto>*/ {

    private OmaLista sisalto;

    private Hakemisto ylihakemisto;

    /*
     * Rakentajat.
     */

    public Hakemisto(StringBuilder nimi, Hakemisto ylihakemisto) {
        super(nimi);
        sisalto = new OmaLista();
        this.ylihakemisto(ylihakemisto);
    }

    /*
     * Aksessorit.
     */
    public void sisalto(OmaLista sisalto) {
        this.sisalto = sisalto;
    }

    public OmaLista sisalto() {
        return sisalto;
    }

    public void ylihakemisto(Hakemisto ylihakemisto) {
        this.ylihakemisto = ylihakemisto;
    }

    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }

    // Korvattava toString-metodi.
    public String toString() {
        return super.toString() + "/" + EROTIN + sisalto.koko();
    }
}
