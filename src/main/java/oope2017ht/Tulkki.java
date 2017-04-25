package oope2017ht;

import oope2017ht.omalista.OmaLista;
import oope2017ht.tiedot.Hakemisto;
import oope2017ht.tiedot.Tiedosto;
import oope2017ht.tiedot.Tieto;

/**
  * Tulkki hallinnoi hakemistopuuta toteuttamalla hakemistopuuta käsittelevät metodit.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * Viimeksi muokattu 24.4.2017.
  * <p>
  * @author Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
  */

public class Tulkki {

    /*
     * Attribuutit.
     */

    /** Tulkin nykyinen hakemisto, johon kohdistuu suurin osa komennoista.*/
    private Hakemisto tyohakemisto;

    /** Tulkin juurihakemisto on hierarkian ylin hakemisto, joka sisältää kaikki muut hakemistot ja tiedostot.*/
    private Hakemisto juurihakemisto;

    /*
     * Rakentaja.
     */
    public Tulkki() {
        this.juurihakemisto(new Hakemisto());
        this.tyohakemisto(juurihakemisto);
    }

    /*
     * Aksessorit.
     */
    public void tyohakemisto(Hakemisto hakemisto) {
        if (hakemisto != null) {
            this.tyohakemisto = hakemisto;
        }
    }

    public Hakemisto tyohakemisto() {
        return tyohakemisto;
    }

    public void juurihakemisto(Hakemisto juurihakemisto) {
        if (juurihakemisto != null) {
            this.juurihakemisto = juurihakemisto;
        }
    }

    public Hakemisto juurihakemisto() {
        return juurihakemisto;
    }

    /*
     * Apumetodit.
     */

    /** Apumetodi uudelleennimeämis- ja kopioimiskomentojen käytettäväksi.
      * <p>
      * Tutkii onko hakemistossa jo parametrina annetulla nimellä tiedosto tai hakemisto.
      *
      * @param nimi sen tiedon nimi, jota haetaan
      * @return true, jos samanniminen on jo hakemistossa, false jo samannimistä ei ole
      */
    private boolean nimiVarattu(String nimi) {
        // Kutsutaan Hakemiston hae-metodia, joka palauttaa nullin, jos haettavalla nimellä ei löydy Tietoa.
        Tieto alkio = tyohakemisto.hae(nimi);
        // Samannimistä ei löytynyt.
        if (alkio == null) {
            return false;
        // Samanniminen löytyi.
        } else {
            return true;
        }
    }

    /** Metodilla asetetaan työhakemistoksi juurihakemisto.*/
    public void siirryJuurihakemistoon() {
        tyohakemisto(juurihakemisto);
    }

    /**
      *  Metodilla asetetaan työhakemistoksi nykyisen hakemiston ylihakemisto. Paluuarvo on false,
      * @return true, jos hakemistoon siirtyminen onnistui, false, jos ei onnistunut
      */
    public boolean siirryYlihakemistoon() {
        // Juurihakemistosta ei voi siirtyä ylihakemistoon.
        if (tyohakemisto == juurihakemisto) {
            return false;
        } else {
            // Asetetaan viite nykyiseen hakemistoon.
            Hakemisto nykyinenHakemisto = tyohakemisto();
            // Asetetaan työhakemistoksi nykyisen hakemiston ylihakemisto.
            tyohakemisto(nykyinenHakemisto.ylihakemisto());
            return true;
        }
    }

    /**
      *  Metodilla asetetaan työhakemistoksi alihakemisto, jonka nimi saadaan parametrina.
      * @param nimi sen alihakemiston nimi, johon siirrytään
      * @return true, jos alihakemistoon siirtyminen onnistui, false, jos ei onnistunut
      */
    public boolean siirryAlihakemistoon(String nimi) {
        // Haetaan hakemistosta nimellä.
        Tieto alkio = tyohakemisto.hae(nimi);
        // Tarkistetaan, että hakemistosta löytyy sen niminen alihakemisto, johon halutaan siirtyä
        // ja että tieto on tyyppiä Hakemisto. Jos hakemisto löytyi palautetaan true, muuten false.
        if (alkio != null && alkio instanceof Hakemisto) {
            tyohakemisto((Hakemisto)alkio);
            return true;
        } else {
            return false;
        }
    }

    /**
      *  Metodi poistaa hakemistosta tiedon, jonka nimi saadaan parametrina.
      * @param poistettava tieto, joka poistetaan
      * @return true, jos poisto onnistui, false, jos ei onnistunut
      */
    public boolean poista(String poistettava) {
        // Kutsutaan Hakemiston metodia, joka poistaa nimeä vastaavan olion.
        Tieto poistettavaTieto = tyohakemisto.poista(poistettava);
        // Jos poisto ei onnistunut on paluuarvo null ja metodi palauttaa falsen.
        if (poistettavaTieto == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
      *  Metodi kopioi tiedoston. Kopioitavan tiedoston nimi ja kopiolle annettava nimi saadaan parametreina.
      * @param nimi sen tiedoston nimi, joka halutaan kopioida
      * @param kopioNimi nimi, joka annetaan kopiolle
      * @return true, jos kopiointi onnistui, false, jos ei onnistunut
      */
    public boolean kopioiTiedosto(String nimi, String kopioNimi) {
        // Haetaan hakemistosta kopioitava tiedosto nimen perusteella.
        Tieto kopioitava = tyohakemisto.hae(nimi);
        // Tarkistetaan, että tieto löytyi, että se on Tiedosto-tyyppinen
        // ja ettei kopion nimi ole jo käytössä hakemistossa.
        if (kopioitava != null && kopioitava instanceof Tiedosto && !nimiVarattu(kopioNimi)) {
            // Syväkopioidaan tiedosto.
            Tiedosto kopio = new Tiedosto((Tiedosto)kopioitava);
            // Annetaan kopiolle nimeksi komentoriviparametrina saatu uusi nimi.
            kopio.nimi(new StringBuilder(kopioNimi));
            // Lisätään kopio hakemistoon.
            tyohakemisto.lisaa(kopio);
            return true;
        } else {
            return false;
        }
    }

    /**
      *  Metodi nimeää hakemistossa olevan tiedon uudelleen.
      * @param vaihdettavaNimi sen tiedon nimi, joka halutaan vaihtaa
      * @param uusiNimi tiedon uusi nimi
      * @return true, jos vaihtaminen onnistui, false, jos uusi nimi on varattu tai vaihdettavan
      * nimistä ei löydy hakemistosta, jolloin vaihtaminen epäonnistuu
      */
    public boolean nimeaUudelleen(String vaihdettavaNimi, String uusiNimi) {
        // Kutsutaan metodia, joka tutkii onko parametrina saamansa uusi nimi jo varattu. Jos nimi on varattu,
        // paluuarvo on true ja tulostetaan virheilmoitus.
        if (nimiVarattu(uusiNimi)) {
            return false;
        } else {
            // Jos hakemistosta löytyy vaihdettavan nimen mukainen tieto, asetetaan alkiolle uusi nimi.
            Tieto alkio = (Tieto)tyohakemisto.hae(vaihdettavaNimi);
                // Jos haetun nimistä tietoa ei löytynyt tulostetaan virheilmoitus.
                if (alkio == null) {
                    return false;
                } else {
                    // Asetetaan alkiolle uusi nimi.
                    alkio.nimi(new StringBuilder(uusiNimi));
                    return true;
                }
        }
    }

    /**
      *  Metodi luo tiedoston ja lisää sen työhakemistoon.
      * @param nimi luotavan tiedoston nimi
      * @param koko luotavan tiedoston koko
      * @return true, jos tiedoston lisääminen onnistui, false, jos ei onnistunut
      */
    public boolean luoTiedosto(String nimi, int koko) {
        // Luodaan uusi tiedosto-olio ja annetaan rakentajalle parametreina käyttäjän antamat nimi ja koko.
        Tiedosto lisattava = new Tiedosto(new StringBuilder(nimi), koko);
        // Kutsutaan Hakemiston lisaa-metodia, joka lisää tiedoston työhakemistoon.
        // Paluuarvo on true, jos lisääminen onnistui.
        return tyohakemisto.lisaa(lisattava);
    }

    /**
      *  Metodi luo hakemiston ja lisää sen työhakemistoon.
      * @param nimi luotavana hakemiston nimi
      * @return true, jos hakemiston lisääminen onnistui, false, jos ei onnistunut
      */
    public boolean luoHakemisto(String nimi) {
        // Luodaan uusi hakemisto-olio parametrina annetulla nimellä.
        Hakemisto lisattava = new Hakemisto(new StringBuilder(nimi), tyohakemisto);
        // Kutsutaan Hakemiston lisaa-metodia, joka lisää hakemiston työhakemistoon.
        // Paluuarvo on true, jos lisääminen onnistui ja false, jos lisääminen ei onnistunut.
        return tyohakemisto.lisaa(lisattava);
    }

    /**
      * Metodi tulostaa merkkijonona sen tiedoston tai hakemiston tiedot, jonka nimi annetaan parametrina.
      * @param nimi sen tiedoston tai hakemiston nimi, jonka tiedot halutaan merkkijonona
      * @return null, jos haettavaa tietoa ei ole hakemistossa tai tieto merkkijonona, jos tieto löytyy hakemistosta
      */
    public String tietoMjonona(String nimi) {
        // Haetaan hakemistosta tietoa nimellä hyödyntäen Hakemiston hae-metodia.
        Tieto alkio = tyohakemisto.hae(nimi);
        // Jos nimeä vastaavaa tietoa ei löytynyt hakemistosta, palautetaan null.
        // Muussa tapauksessa palautetaan tiedon merkkijonoesitys.
        if (alkio == null) {
            return null;
        } else {
            return alkio.toString();
        }
    }

    /**
      *  Metodi listaa nykyisen hakemiston koko sisällön aakkosjärjestyksessä.
      * @return tulos eli lista hakemiston sisällöstä.
      */
    public OmaLista hakemistonSisalto() {
        // Viite nykyisen hakemiston tietoihin.
        OmaLista tiedot = tyohakemisto.tiedot();
        // Luodaan uusi OmaLista-olio.
        OmaLista tulos = new OmaLista();
        // Lisätään työhakemiston tiedot alkio kerrallaan tulos-listalle.
        for (int i = 0; i < tiedot.koko(); i++) {
            tulos.lisaaLoppuun(tiedot.alkio(i).toString());
        }
        // Palautetaan tulos.
        return tulos;
    }

    /**
      *  Metodi tallentaa parametrina saamansa hakemiston hakemistopuun sisällön OmaLista-tyyppiseen muuttujaan.
      * @param hakemisto sen hakemiston nimi, jonka hakemistopuu halutaan tallentaa listalle.
      * @return tulos eli hakemistopuu tallennettuna listalle.
      */
    public OmaLista hakemistopuunSisalto(Hakemisto hakemisto) {
        // Luodaan uusi OmaLista-olio.
        OmaLista tulos = new OmaLista();
        // Kutsutaan kuormitettua metodia, joka saa parametreina hakemiston sekä OmaListan.
        hakemistopuunSisalto(hakemisto, tulos);
        // Paluuarvo on hakemistopuun sisältö OmaLista-muuttujassa.
        return tulos;
    }

    /**
      *  Metodi listaa parametrina saamansa hakemiston hakemistopuun rekursiivisesti esijärjestyksessä
      *  OmaLista-tyyppiselle listalle.
      * @param hakemisto sen hakemiston nimi, jonka hakemistopuu halutaan lisätä listaan.
      * @param tulos eli lista, johon hakemistopuu tallennetaan
      */
    private void hakemistopuunSisalto(Hakemisto hakemisto, OmaLista tulos) {
        // Asetetaan viite parametrina saadun hakemiston tietoihin.
        OmaLista sisalto = hakemisto.tiedot();
        // Käydään hakemiston sisältö läpi.
        for (int i = 0; i < sisalto.koko(); i++) {
            // Asetetaan viite tietoalkioon.
            Tieto tieto = (Tieto)sisalto.alkio(i);
            // Kutsutaan metodia, joka lisää listan loppuun merkkijonon, joka
            // koostuu nykyisen hakemiston hakemistopolusta sekä tiedon merkkijonoesityksestä.
            tulos.lisaaLoppuun(hakemisto.hakemistopolku() + tieto.toString());
            // Jos tieto on Hakemisto-tyyppinen, metodi kutsuu itseään ja antaa hakemiston sekä
            // listan parametreina.
            if (tieto instanceof Hakemisto) {
                hakemistopuunSisalto((Hakemisto)tieto, tulos);
            }
        }
    }
}
