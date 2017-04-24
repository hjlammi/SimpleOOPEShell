package oope2017ht.tiedot;

/**
  * Tieto-luokka, jonka attribuutit ovat OmaLista tiedoille, jotka hakemisto sisältää sekä ylihakemisto.
  * Luokka korvaa toString-metodin ja toteuttaa Komennettava-rajapinnan.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * Viimeksi muokattu 21.4.2017.
  * <p>
  * @author Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
  */

import apulaiset.Komennettava;
import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2017ht.omalista.OmaLista;

public class Hakemisto extends Tieto implements Komennettava<Tieto> {

    /*
     * Attribuutit.
     */

    /** OmaLista-tyyppinen attribuutti säilöö listan sisällön eli listassa olevat tiedot.*/
    private OmaLista tiedot;

    /** Se hakemisto, joka sisältää tämän hakemiston.*/
    private Hakemisto ylihakemisto;

    /*
     * Rakentajat.
     */

    /** Oletusrakentaja juurihakemiston luomista varten.*/
    public Hakemisto() {
        super();
        tiedot = new OmaLista();
        this.ylihakemisto(null);
    }

    // Kaksiparametrillinen rakentaja.
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
    @Override
    public String toString() {
        return super.toString() + "/" + EROTIN + tiedot.koko();
    }

    /*
     * Komennettava-rajapinnan korvattavat metodit.
     */

    /*
     * Aksessori, joka antaa viitteen hakemiston sisällön säilövään listaan.
     */
    @Override
    public LinkitettyLista sisalto() {
        return tiedot;
    }

    /* Hakee hakemistosta parametrina saatua nimeä vastaavaa tiedostoa tai alihakemistoa.
     * Hyödyntää OmaLista-luokan hae-operaatiota. Paluuarvo on viite löydettyyn tietoon tai null,
     * jos tietoa ei löydetä.
     */
    @Override
    public Tieto hae(String nimi) {
        if (nimi != null) {
            // Luodaan apuolio, jolle annetaan nimeksi parametrina saatu nimi.
            Tieto haettava = new Tiedosto(new StringBuilder(nimi), 1);
            // Käytetään apuna OmaLista-luokan hae-metodia, joka hakee hakemistosta
            // parametrina saatua nimeä vastaavaa tietoa.
            // Paluuarvona saadaan viite haettavaa vastaavaan tietoon tai null, jos tietoa ei löydy.
            Object loydetty = tiedot.hae(haettava);
            return (Tieto)loydetty;
        } else {
            return null;
        }
    }

    /* Lisää hakemistoon parametrina annetun tiedoston tai alihakemiston. Hyödyntää OmaLista-luokan
     * lisaa-operaatiota. Paluuarvo on true, jos lisääminen onnistui ja false, jos tieto on null-arvoinen
     * tai hakemistossa on jo tieto parametrina annetulla nimellä.
     */
    @Override
    public boolean lisaa(Tieto lisattava) {
        // Tarkistetaan, ettei lisättävä ole null-arvoinen.
        if (lisattava != null) {
            // Käydään hakemiston sisältö läpi ja verrataan lisättävää listassa jo oleviin tietoihin.
            for (int i = 0; i < tiedot.koko(); i++) {
                Object alkio = tiedot.alkio(i);
                // Jos hakemistosta löytyy jo samanniminen tieto, palautetaan false. Muuten lisätään
                // lisättävä tieto OmaLista-luokan lisaa-metodilla ja palautetaan true.
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

    /* Poistaa hakemistosta parametrina saatua nimeä vastaavan tiedoston tai alihakemiston.
     * Hyödyntää OmaLista-luokan poista-operaatiota. Paluuarvo on viite poistettuun tietoon tai
     * null, jos tietoa ei löydetä.
     */
    @Override
    public Tieto poista(String nimi) {
        if (nimi != null) {
            // Luodaan apuolio, jolle annetaan nimeksi parametrina saatu nimi.
            Tieto poistettava = new Tiedosto(new StringBuilder(nimi), 1);
            // Käytetään apuna OmaLista-luokan poista-metodia, joka poistaa hakemistosta
            // poistettavan nimisen tiedoston tai alihakemiston ja antaa viitteen siihen paluuarvona.
            // Paluuarvo on null, jos nimeä vastaavaa tietoa ei löydy.
            Object loydetty = tiedot.poista(poistettava);
            // Muutetaan saatu tieto Tieto-tyyppiseksi ja annetaan se paluuarvona.
            return (Tieto)loydetty;
        } else {
            return null;
        }
    }

    /** Metodi palauttaa merkkijonona hakemiston hakemistopolun.
      *
      * @return hakemiston hakemistopolku
      */
    public String hakemistopolku() {
        // Jos ylihakemisto on null (eli kyseessä on juurihakemisto), palautetaan hakemiston nimi merkkijonona.
        if (ylihakemisto == null) {
            return nimi().toString();
        // Jos kyseessä ei ole juurihakemisto, kutsutaan ylihakemistolle hakemistopolku-metodia
        // ja tulokseen lisätään nykyisen hakemiston nimi merkkijonona sekä kauttaviiva.
        } else {
            return ylihakemisto.hakemistopolku() + nimi().toString() + "/";
        }
    }
}
