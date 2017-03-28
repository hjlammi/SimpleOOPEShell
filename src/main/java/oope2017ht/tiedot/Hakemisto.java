package oope2017ht.tiedot;

import oope2017ht.OmaLista;

import apulaiset.Komennettava;
import fi.uta.csjola.oope.lista.LinkitettyLista;

public class Hakemisto extends Tieto implements Komennettava<Tieto> {

    private OmaLista tiedot;

    private Hakemisto ylihakemisto;

    /*
     * Kaksiparametrillinen rakentaja.
     */

    public Hakemisto(StringBuilder nimi, Hakemisto ylihakemisto) {
        super(nimi);
        tiedot = new OmaLista();
        this.ylihakemisto(ylihakemisto);
    }

    /*
     * Aksessorit.
     */

    public void sisalto(OmaLista tiedot) {
        this.tiedot = tiedot;
    }

    public OmaLista tiedot() {
        return tiedot;
    }

    public void ylihakemisto(Hakemisto ylihakemisto) {
        this.ylihakemisto = ylihakemisto;
    }

    public Hakemisto ylihakemisto() {
        return ylihakemisto;
    }

    // Korvattava toString-metodi.
    public String toString() {
        return super.toString() + "/" + EROTIN + tiedot.koko();
    }

    /*
     * Komennettava-rajapinnan korvattavat metodit.
     */

    /* Aksessori, joka antaa viitteen hakemiston sisällön säilövään listaan.
    *
    * @return viite hakemisto-olion osaolioon.
    */
    @Override
    public LinkitettyLista sisalto() {
        return tiedot;
    }


    /* Hakee hakemistosta tiedostoa tai alihakemistoa. Hyödyntää OmaLista-luokan
    * hae-operaatiota. Huomaa, että nimeä käyttäen haun avuksi voidaan luoda
    * väliaikainen tiedosto tai hakemisto.
    *
    * @param nimi haettavan tiedon nimi.
    * @return viite löydettyyn tietoon. Paluuarvo on null, jos tietoa ei löydetä.
    */
    @Override
    public Tieto hae(String nimi) {
        Tieto haettava = new Tiedosto(new StringBuilder(nimi), 1);
        Object loydetty = tiedot.hae(haettava);
        return (Tieto)loydetty;
    }

    /* Lisää hakemistoon tiedoston tai alihakemiston. Hyödyntää OmaLista-luokan
     * lisaa-operaatiota.
     *
     * @param lisattava viite lisättävään tietoon.
     * @return true, jos lisääminen onnistui ja false, jos tieto on null-arvoinen
     * tai hakemistossa on jo tieto parametrina annetulla nimellä.
     */
    @Override
    public boolean lisaa(Tieto lisattava) {
        if (lisattava != null) {
            for (int i = 0; i < tiedot.koko(); i++) {
                Object alkio = tiedot.alkio(i);
                if (lisattava.equals((Tieto)alkio)) {
                    return false;
                }
            }
            tiedot.lisaa(lisattava);
            return true;
        } else {
            return false;
        }
    }

    /* Poistaa hakemistosta tiedoston tai alihakemiston. Hyödyntää OmaLista-luokan
     * poista-operaatiota. Huomaa, että nimeä käyttäen poiston avuksi voidaan luoda
     * väliaikainen tiedosto tai hakemisto.
     *
     * @param nimi poistettavan tiedon nimi.
     * @return viite poistettuun tietoon. Paluuarvo on null, jos tietoa ei löydetä.
     */
    @Override
    public Tieto poista(String nimi) {
        Tieto poistettava = new Tiedosto(new StringBuilder(nimi), 1);
        Object loydetty = tiedot.poista(poistettava);
        return (Tieto)loydetty;
    }

}
