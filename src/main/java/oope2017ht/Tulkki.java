package oope2017ht;

import oope2017ht.omalista.OmaLista;
import oope2017ht.tiedot.Hakemisto;
import oope2017ht.tiedot.Tiedosto;
import oope2017ht.tiedot.Tieto;

/*
* Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
*
* Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
*
* Viimeksi muokattu 24.4.2017.
*
* Tulkki hallinnoi hakemistopuuta toteuttamalla hakemistopuuta käsittelevät metodit.
*/

public class Tulkki {

    /*
     * Attribuutit.
     */

    // Tulkin nykyinen hakemisto.
    Hakemisto tyohakemisto;

    // Tulkin juurihakemisto.
    Hakemisto juurihakemisto;

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

    /*
     * Apumetodit.
     */

    // Apumetodi uudelleennimeämis- ja kopioimiskomentojen käytettäväksi. Tutkii onko hakemistossa jo parametrina
    // annetulla nimellä tiedosto tai hakemisto. Jos samanniminen löytyy, palautetaan
    // true, jos samannimistä ei löydy, palautetaan false.
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

    // Metodilla asetetaan työhakemistoksi juurihakemisto.
    public void siirryJuurihakemistoon() {
        tyohakemisto(juurihakemisto);
    }

    // Metodilla asetetaan työhakemistoksi nykyisen hakemiston ylihakemisto. Paluuarvo on false,
    // jos siirtyminen ei onnistunut ja true, jos onnistui.
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

    // Metodilla asetetaan työhakemistoksi alihakemisto, jonka nimi saadaan parametrina.
    // Jos alihakemistoon siirtyminen ei onnistunut, palautetaan false, jos onnistui, palautetaan true.
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

    // Metodi poistaa hakemistosta tiedon, jonka nimi saadaan parametrina. Jos tietoa ei onnistuttu
    // poistamaan palautetaan false, muussa tapauksessa palautetaan true.
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

    // Metodi kopioi tiedoston. Kopioitavan tiedoston nimi ja kopiolle annettava nimi saadaan parametreina.
    // Jos kopiointi onnistui palautetaan true, jos ei onnistunut, palautetaan false.
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

    // Metodi nimeää tiedon uudelleen. Vaihdettavan tiedon vanha ja uusi nimi saadaan parametreina.
    // Jos vaihtaminen onnistui palautetaan true. Jos uusi nimi on varattu tai vaihdettavan nimistä
    // tietoa ei löydy hakemistosta, palautetaan false.
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
                    // Poistetaan alkio, jonka nimi halutaan vaihtaa, jotta saadaan lisättyä uudenniminen tieto
                    // aakkosissa oikealle kohdalle hakemistossa.
                    tyohakemisto.poista(vaihdettavaNimi);
                    // Tarkistetaan, onko tieto Tiedosto- vai Hakemisto-tyyppinen.
                    if (alkio instanceof Tiedosto) {
                        // Pätkitään alkion tiedot eli nimi ja koko taulukkoon.
                        String tiedot[] = alkio.toString().split(" ");
                        // Tiedoston koko on toisessa indeksissä.
                        int koko = Integer.parseInt(tiedot[1]);
                        // Luodaan uuden niminen, mutta vanhan kokoinen tiedosto-olio.
                        Tiedosto uusi = new Tiedosto(new StringBuilder(uusiNimi), koko);
                        // Lisätään uusi tiedosto työhakemistoon.
                        tyohakemisto.lisaa(uusi);
                    } else {
                        // Jos kyseessä ei ollut Tiedosto, luodaan uusi hakemisto ja lisätään se työhakemistoon.
                        Hakemisto uusi = new Hakemisto(new StringBuilder(uusiNimi), tyohakemisto);
                        tyohakemisto.lisaa(uusi);
                    }
                    return true;
                }
        }
    }

    // Metodi luo tiedoston, jonka nimi ja koko saadaan parametreina. Jos tiedoston lisääminen hakemistoon
    // onnistui palautetaan true, muuten palautetaan false.
    public boolean luoTiedosto(String nimi, int koko) {
        // Luodaan uusi tiedosto-olio ja annetaan rakentajalle parametreina käyttäjän antamat nimi ja koko.
        Tiedosto lisattava = new Tiedosto(new StringBuilder(nimi), koko);
        // Kutsutaan Hakemiston lisaa-metodia, joka lisää tiedoston työhakemistoon.
        // Paluuarvo on true, jos lisääminen onnistui.
        return tyohakemisto.lisaa(lisattava);
    }

    // Metodi luo hakemiston, jonka nimi saadaan parametrina. Jos hakemiston lisääminen nykyiseen hakemistoon onnistui,
    // palautetaan true, jos ei onnistunut, palautetaan false.
    public boolean luoHakemisto(String nimi) {
        // Luodaan uusi hakemisto-olio parametrina annetulla nimellä.
        Hakemisto lisattava = new Hakemisto(new StringBuilder(nimi), tyohakemisto);
        // Kutsutaan Hakemiston lisaa-metodia, joka lisää hakemiston työhakemistoon.
        // Paluuarvo on true, jos lisääminen onnistui ja false, jos lisääminen ei onnistunut.
        return tyohakemisto.lisaa(lisattava);
    }

    // Metodi tulostaa merkkijonona sen tiedoston tai hakemiston tiedot,
    // jonka nimi annetaan parametrina. Jos haettavaa tietoa ei löydy, palautetaan null,
    // jos löytyy, palautetaant tiedon merkkijonoesitys.
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

    // Metodi listaa nykyisen hakemiston koko sisällön aakkosjärjestyksessä.
    // Paluuarvo on OmaLista-tyyppinen lista hakemiston sisällöstä.
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

    // Metodi tallentaa parametrina saamansa hakemiston hakemistopuun sisällön OmaLista-tyyppiseen
    // muuttujaan, jonka antaa kutsujalle parametrina.
    public OmaLista hakemistopuunSisalto(Hakemisto hakemisto) {
        // Luodaan uusi OmaLista-olio.
        OmaLista tulos = new OmaLista();
        // Kutsutaan kuormitettua metodia, joka saa parametreina hakemiston sekä OmaListan.
        hakemistopuunSisalto(hakemisto, tulos);
        // Paluuarvo on hakemistopuun sisältö OmaLista-muuttujassa.
        return tulos;
    }

    // Yksityinen metodi listaa parametrina saamansa hakemiston hakemistopuun rekursiivisesti
    // OmaLista-tyyppiselle listalle, jonka antaa paluuarvona kutsuvaan paikkaan. Ylihakemisto
    // listataan ennen alihakemistojaan.
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
