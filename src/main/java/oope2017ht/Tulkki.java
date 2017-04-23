package oope2017ht;

import oope2017ht.omalista.OmaLista;
import oope2017ht.tiedot.Hakemisto;
import oope2017ht.tiedot.Tiedosto;
import oope2017ht.tiedot.Tieto;

public class Tulkki {

    /*
     *  Vakiot.
     */

    static final String REKURSIIVINEN_LISTAAMINEN = "find";
    static final String HAKEMISTON_VAIHTAMINEN = "cd";
    static final String POISTAMINEN = "rm";
    static final String KOPIOIMINEN = "cp";
    static final String UUDELLEEN_NIMEAMINEN = "mv";
    static final String TIEDOSTON_LUOMINEN = "mf";
    static final String HAKEMISTON_LUOMINEN = "md";
    static final String LISTAAMINEN = "ls";
    static final String LOPETUS = "exit";
    static final String KEHOTE = ">";
    static final String TERVEHDYS = "Welcome to SOS.";
    static final String LOPETUSVIESTI = "Shell terminated.";
    static final String VIRHEILMOITUS = "Error!";

    Hakemisto tyohakemisto;
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
    boolean nimiVarattu(String nimi) {
        // Kutsutaan Hakemiston hae-metodia, joka palauttaa nullin, jos haettavalla nimellä ei löydy Tietoa.
        Tieto alkio = tyohakemisto.hae(nimi);
        // Samannimistä ei löytynyt.
        if (alkio == null) {
            return false;
        } else {
            return true;
        }
    }

    // Metodilla asetetaan työhakemistoksi juurihakemisto.
    public void siirryJuurihakemistoon() {
        tyohakemisto(juurihakemisto);
    }

    // Metodilla asetetaan työhakemistoksi nykyisen hakemiston ylihakemisto.
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

    // Metodilla asetetaan työhakemistoksi alihakemisto, jonka nimi on parametrina saadun taulukon toinen alkio.
    public boolean siirryAlihakemistoon(String nimi) {
        // Haetaan hakemistosta nimellä.
        Tieto alkio = tyohakemisto.hae(nimi);
        // Tarkistetaan, että hakemistosta löytyy senniminen alihakemisto, johon halutaan siirtyä
        // ja että tieto on tyyppiä Hakemisto.
        if (alkio != null && alkio instanceof Hakemisto) {
            tyohakemisto((Hakemisto)alkio);
            return true;
        } else {
            return false;
        }
    }

    // Metodi poistaa hakemistosta tiedon, jonka nimi on parametrina saadun taulukon toinen alkio.
    public boolean poista(String poistettava) {
        // Kutsutaan Hakemiston metodia, joka poistaa nimeä vastaavan olion.
        Tieto poistettavaTieto = tyohakemisto.poista(poistettava);
        // Jos poisto ei onnistunut on paluuarvo null ja tulostetaan virheilmoitus.
        if (poistettavaTieto == null) {
            return false;
        } else {
            return true;
        }
    }

    // Metodi kopioi tiedoston, jonka nimi on parametrina saadun taulukon ensimmäinen alkio.
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

    // Metodi nimeää uudelleen tiedon, jonka nimi on parametrina saadun taulukon ensimmäinen alkio.
    public boolean nimeaUudelleen(String[] osat) {
        // Vaihdettava nimi.
        String vaihdettavaNimi = osat[1];
        // Uusi nimi on komennon toinen parametri eli taulukon kolmas alkio.
        String uusiNimi = osat[2];
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
                    alkio.nimi(new StringBuilder(uusiNimi));
                    return true;
                }
        }
    }

 // Metodi luo tiedoston, jonka nimi on parametrina saadun taulukon toinen alkio.
    public boolean luoTiedosto(String[] osat) {
        // Tiedoston nimi.
        String nimi = osat[1];
        // Tiedoston koko on taulukon kolmas alkio.
        int koko = Integer.parseInt(osat[2]);
        // Luodaan uusi tiedosto-olio ja annetaan rakentajalle parametreina käyttäjän antamat nimi ja koko.
        Tiedosto lisattava = new Tiedosto(new StringBuilder(nimi), koko);
        // Kutsutaan Hakemiston lisaa-metodia, joka lisää tiedoston työhakemistoon.
        // Paluuarvo on true, jos lisääminen onnistui.
        boolean onnistui = tyohakemisto.lisaa(lisattava);
             // Jos paluuarvo oli false, lisääminen ei onnistunut ja tulostetaan virheilmoitus.
        if (onnistui) {
            return true;
        } else {
            return false;
        }
    }

    // Metodi luo hakemiston, jonka nimi on parametrina saadun taulukon toinen alkio.
    public boolean luoHakemisto(String nimi) {
        // Luodaan uusi hakemisto-olio parametrina annetulla nimellä.
        Hakemisto lisattava = new Hakemisto(new StringBuilder(nimi), tyohakemisto);
        // Kutsutaan Hakemiston lisaa-metodia, joka lisää hakemiston työhakemistoon.
        // Paluuarvo on true, jos lisääminen onnistuu.
        // Jos paluuarvo oli false, lisääminen ei onnistunut ja tulostetaan virheilmoitus.
        return tyohakemisto.lisaa(lisattava);
    }

    // Metodi tulostaa merkkijonona sen tiedoston tai hakemiston tiedot,
    // jonka nimi on parametrina saadun taulukon ensimmäinen alkio.
    public String tietoMjonona(String[] osat) {
        // Tulostettavan tiedon nimi.
        String nimi = osat[1];
        // Haetaan hakemistosta tietoa nimellä hyödyntäen Hakemiston hae-metodia.
        Tieto alkio = tyohakemisto.hae(nimi);
        // Jos nimeä vastaavaa tietoa ei löytynyt hakemistosta, tulostetaan virheilmoitus.
        // Muussa tapauksessa tulostetaan tiedon merkkijonoesitys.
        if (alkio == null) {
            return null;
        } else {
            return alkio.toString();
        }
    }

    // Metodi listaa nykyisen hakemiston sisällön.
    public OmaLista hakemistonSisalto() {
        // Viite nykyisen hakemiston tietoihin.
        OmaLista tiedot = tyohakemisto.tiedot();
        OmaLista tulos = new OmaLista();
        // Tulostetaan työhakemiston tiedot alkio kerrallaan.
        for (int i = 0; i < tiedot.koko(); i++) {
            tulos.lisaaLoppuun(tiedot.alkio(i).toString());
        }
        return tulos;
    }

    public OmaLista hakemistopuunSisalto(Hakemisto hakemisto) {
        OmaLista tulos = new OmaLista();
        hakemistopuunSisalto(hakemisto, tulos);
        return tulos;
    }

    // Metodi tulostaa parametrina saamansa hakemiston hakemistopuun rekursiivisesti.
    private void hakemistopuunSisalto(Hakemisto hakemisto, OmaLista tulos) {
        // Asetetaan viite parametrina saadun hakemiston tietoihin.
        OmaLista sisalto = hakemisto.tiedot();
        // Käydään hakemiston sisältö läpi.
        for (int i = 0; i < sisalto.koko(); i++) {
            // Asetetaan viite tietoalkioon.
            Tieto tieto = (Tieto)sisalto.alkio(i);
            // Kutsutaan terminaalin tulostusmetodia, joka ottaa parametrina merkkijonon.
            // Merkkijono koostuu nykyisen hakemiston hakemistopolusta sekä tiedon merkkijonoesityksestä.
            tulos.lisaaLoppuun(hakemisto.hakemistopolku() + tieto.toString());
            // Jos tieto on Hakemisto-tyyppinen, metodi kutsuu itseään ja antaa tiedon parametrina.
            if (tieto instanceof Hakemisto) {
                hakemistopuunSisalto((Hakemisto)tieto, tulos);
            }
        }
    }
}
