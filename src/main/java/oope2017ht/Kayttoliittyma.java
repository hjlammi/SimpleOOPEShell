package oope2017ht;

import apulaiset.In;
import oope2017ht.omalista.OmaLista;

/**
  * Käyttöliittymä vastaa vuorovaikutuksesta käyttäjän kanssa. Käyttäjä antaa käyttöliittymän välityksellä
  * ohjelmalle komentoja, jotka käyttöliittymä välittää tulkille toteutettavaksi.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * Viimeksi muokattu 24.4.2017.
  * <p>
  * @author Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
  */

public class Kayttoliittyma {

    /*
     *  Vakiot.
     */

    // Käyttäjän antamat komennot.

    /** Komennolla listataan nykyisen hakemiston hakemistopuu rekursiivisesti esijärjestyksessä. */
    private static final String REKURSIIVINEN_LISTAAMINEN = "find";

    /** Komennolla vaihdetaan hakemistoa.*/
    private static final String HAKEMISTON_VAIHTAMINEN = "cd";

    /** Komennolla poistetaan hakemisto tai tiedosto.*/
    private static final String POISTAMINEN = "rm";

    /** Komennolla kopioidaan hakemisto tai tiedosto.*/
    private static final String KOPIOIMINEN = "cp";

    /** Komennolla nimetään hakemisto tai tiedosto uudelleen.*/
    private static final String UUDELLEEN_NIMEAMINEN = "mv";

    /** Komennolla luodaan tiedosto.*/
    private static final String TIEDOSTON_LUOMINEN = "mf";

    /** Komennolla luodaan hakemisto.*/
    private static final String HAKEMISTON_LUOMINEN = "md";

    /** Komennolla listataan hakemiston sisältö tai tulostetaan parametrina annetun tiedon merkkijonoesitys.*/
    private static final String LISTAAMINEN = "ls";

    /** Komennolla poistutaan ohjelmasta.*/
    private static final String LOPETUS = "exit";


    // Tulosteet käyttäjälle.

    /** Merkin jälkeen odotetaan käyttäjän syötettä.*/
    private static final String KEHOTE = ">";

    /** Tervehdysteksti ohjelman alussa.*/
    private static final String TERVEHDYS = "Welcome to SOS.";

    /** Lopetusviesti poistumiskomennon jälkeen.*/
    private static final String LOPETUSVIESTI = "Shell terminated.";

    /** Virheilmoitus.*/
    private static final String VIRHEILMOITUS = "Error!";


    /*
     * Attribuutit.
     */

    /** Käyttöliittymä komentaa tulkkia, joka toteuttaa ohjelman komennot.*/
    private Tulkki tulkki;

    /*
     * Rakentajat.
     */

    public Kayttoliittyma() {
        this.tulkki = new Tulkki();
    }

    /** Metodi pyytää käyttäjältä komentoja ja kutsuu komentoja vastaavia tulkkiluokan metodeja.
      * Metodin suoritusta jatketaan, kunnes käyttäjä syöttää lopetuskomennon.
      */
    public void suorita() {
        // Kun ohjelma käynnistyy tulostetaan tervehdysviesti.
        System.out.println(TERVEHDYS);

        // Luodaan muuttuja käyttäjän antamalle syötteelle.
        String syote;

        do {
            // Kutsutaan metodia, tulostaa näytölle nykyisen työhakemiston hakemistopolun sekä kehotteen ja
            // lukee käyttäjän antaman syötteen  syote-muuttujaan.
            System.out.print(tulkki.tyohakemisto().hakemistopolku() + KEHOTE);
            syote = In.readString();
            // Pilkotaan syöte osiin välilyöntien kohdalta ja tallennetaan syötteen osat taulukkoon.
            String osat[] = syote.split(" ");
            try {
                // Jos syöte alkaa tai loppuu välilyöntiin, kutsutaan error-metodia, joka tulostaa virheilmoituksen.
                if (syote.endsWith(" ") || syote.startsWith(" ")) {
                    error();
                // Jos käyttäjä haluaa poistua ohjelmasta tulostetaan lopetusviesti.
                } else if (osat[0].equals(LOPETUS) && osat.length == 1) {
                    System.out.println(LOPETUSVIESTI);
                // Jos käyttäjä haluaa listata hakemiston sisällön, komento ei sisällä komentoriviparametreja.
                } else if (osat[0].equals(LISTAAMINEN) && osat.length == 1) {
                    // Kutsutaan metodia, joka listaa hakemiston sisällön.
                    listaaHakemistonSisalto();
                // Jos käyttäjä haluaa tulostaa näytölle tiedon merkkijonoesityksen, hän antaa
                // tiedon nimen komentoriviparametrina.
                } else if (osat[0].equals(LISTAAMINEN) && osat.length == 2) {
                    // Tiedon nimi.
                    String nimi = osat[1];
                    tulostaTietoMjonona(nimi);
                // Jos käyttäjä haluaa luoda hakemiston komentoriviparametrina antamallansa nimellä:
                } else if (osat[0].equals(HAKEMISTON_LUOMINEN) && osat.length == 2) {
                    // Tiedon nimi.
                    String nimi = osat[1];
                    // Jos tulkin luoHakemisto-metodi palauttaa falsen, hakemiston luominen ei onnistunut,
                    // ja tulostetaan virheilmoitus.
                    if (!tulkki.luoHakemisto(nimi)) {
                        error();
                    }
                // Jos käyttäjä haluaa luoda tiedoston komentoriviparametreinaan antamillaan nimellä ja koolla:
                } else if (osat[0].equals(TIEDOSTON_LUOMINEN) && osat.length == 3) {
                    // Luotavan tiedoston nimi.
                    String nimi = osat[1];
                    // Luotavan tiedoston koko.
                    int koko = Integer.parseInt(osat[2]);
                    // Kutsutaan tulkin luoTiedosto-metodia, joka saa parametreinaan nimen ja koon.
                    // Jos paluuarvo on false, tiedoston luominen epäonnistui ja tulostetaan virheilmoitus.
                    if (!tulkki.luoTiedosto(nimi, koko)) {
                        error();
                    }
                // Jos käyttäjä haluaa uudelleennimetä olemassa olevan tiedon:
                } else if (osat[0].equals(UUDELLEEN_NIMEAMINEN) && osat.length == 3) {
                    // Ensimmäinen komentoriviparametri on vaihdettavan tiedon nimi.
                    String vaihdettavaNimi = osat[1];
                    // Toinen komentoriviparametri on uusi nimi tiedolle.
                    String uusiNimi = osat[2];
                    // Kutsutaan tulkin metodia, joka nimeää tiedon uudelleen.
                    // Jos paluuarvo on false, uudelleennimeäminen epäonnistui ja tulostetaan virheilmoitus.
                    if (!tulkki.nimeaUudelleen(vaihdettavaNimi, uusiNimi)) {
                        error();
                    }
                // Jos käyttäjä haluaa kopioida tiedoston:
                } else if (osat[0].equals(KOPIOIMINEN) && osat.length == 3) {
                    // Kopioitavan tiedoston nimi on ensimmäinen komentoriviparametri.
                    String nimi = osat[1];
                    // Kopion nimi on toinen komentoriviparametri.
                    String kopioNimi = osat[2];
                    // Kutsutaan tulkin metodia, joka kopioi tiedon, jonka saa ensimmäisenä parametrina
                    // ja antaa uudelle tiedolle nimen, jonka saa toisena parametrina.
                    // Jos paluuarvo on false, kopiointi epäonnistui ja tulostetaan virheilmoitus.
                    if (!tulkki.kopioiTiedosto(nimi, kopioNimi)) {
                        error();
                    }
                // Jos käyttäjä haluaa poistaa tiedon, jonka nimen antaa ensimmäisenä komentoriviparametrina:
                } else if (osat[0].equals(POISTAMINEN) && osat.length == 2) {
                    // Poistettavan tiedon nimi.
                    String poistettava = osat[1];
                    // Jos poisto ei onnistunut, tulostetaan virheilmoitus.
                    if (!tulkki.poista(poistettava)) {
                        error();
                    }
                // Jos käyttäjä haluaa siirtyä takaisin juurihakemistoon, kutsutaan tulkin metodia, joka
                // vaihtaa työhakemistoksi juurihakemiston.
                } else if (osat[0].equals(HAKEMISTON_VAIHTAMINEN) && osat.length == 1) {
                    tulkki.siirryJuurihakemistoon();
                // Jos käyttäjä haluaa siirtyä johonkin alihakemistoistaan,
                // ensimmäinen komentoriviparametri on sen alihakemiston nimi, johon halutaan siirtyä.
                // Tarkistetaan kuitenkin ettei parametri ole "..", jolla siirrytään ylihakemistoon.
                } else if (osat[0].equals(HAKEMISTON_VAIHTAMINEN) && !osat[1].equals("..")
                && osat.length == 2) {
                    // Alihakemiston nimi.
                    String nimi = osat[1];
                    // Kutsutaan tulkin metodia, joka saa parametrina alihakemiston nimen, jonne siirrytään.
                    // Jos paluuarvo on false, siirtyminen ei onnistunut ja tulostetaan virheimoitus.
                    if (!tulkki.siirryAlihakemistoon(nimi)) {
                        error();
                    }
                // Jos käyttäjä haluaa siirtyä nykyisen hakemiston ylihakemistoon, komentoriviparametri on "..".
                } else if (osat[0].equals(HAKEMISTON_VAIHTAMINEN) && osat[1].equals("..")
                && osat.length == 2) {
                    // Kutsutaan tulkin metodia, joka vaihtaa työhakemistoksi nykyisen hakemiston ylihakemiston.
                    // Jos paluuarvo on false, siirtyminen ei onnistunut ja tulostetaan virheilmoitus.
                    if (!tulkki.siirryYlihakemistoon()) {
                        error();
                    }
                // Jos käyttäjä haluaa listata nykyisen hakemiston hakemistopuun:
                } else if (osat[0].equals(REKURSIIVINEN_LISTAAMINEN) && osat.length == 1) {
                    // Kutsutaan metodia, joka listaa hakemistopuun rekursiivisesti.
                    listaaRekursiivisesti();
                // Jos syöte ei ole mikään hyväksytyistä syötteistä tulostetaan
                // virheilmoitus.
                } else {
                    error();
                }
            // Napataan rakentajan tai setterin heittämä poikkeus, jos yritetään luoda tietoa virheellisellä nimellä.
            // Tulostetaan virheilmoitus.
            } catch(IllegalArgumentException e) {
                error();
            }
        // Suoritetaan silmukkaa kunnes käyttäjä syöttää lopetuskomennon.
        } while (!syote.equals(LOPETUS));
    }


    /*
     * Apumetodit suorita-metodin käytettäväksi.
     */

    /** Metodi tulostaa hakemistopuun sisällön rekursiivisesti niin,
      * että ylihakemisto tulostetaan ennen alihakemistojaan.
      */
    private void listaaRekursiivisesti() {
        // Kutsutaan tulkin metodia, joka tallentaa OmaLista-tyyppiseen muuttujaan
        // viitteen hakemistopuun sisältöön.
        OmaLista hakemistopuu = tulkki.hakemistopuunSisalto(tulkki.tyohakemisto());
        // Tulostetaan hakemistopuun sisältö alkio kerrallaan.
        for (int i = 0; i < hakemistopuu.koko(); i++) {
            System.out.println(hakemistopuu.alkio(i).toString());
        }
    }

    /** Metodi tulostaa parametrina saamaansa nimeä vastaavan tiedon merkkijonoesityksen.
      *
      * @param nimi viittaa tietoon, joka tulostetaan merkkijonona.
      */
    private void tulostaTietoMjonona(String nimi) {
        if (nimi == null) {
            error();
        } else {
            // Kutsutaan tulkin metodia, joka saa parametrina tiedon nimen
            // ja palauttaa sen merkkijonoesityksen.
            String mjono = tulkki.tietoMjonona(nimi);
            // Jos metodi palautti nullin eli tietoa ei saatu merkkijonona, tulostetaan virheilmoitus.
            if (mjono == null) {
                error();
            // Jos tiedon merkkijonoesitys saatiin paluuarvona, tulostetaan se.
            } else {
                System.out.println(mjono);
            }
        }
    }

    /** Metodi tulostaa näytölle hakemiston sisällön nousevassa aakkosjärjestyksessä
      * (koska tiedot ovat hakemistossa valmiiksi aakkosjärjestyksessä).
      */
    private void listaaHakemistonSisalto() {
        // Kutsutaan tulkin metodia, joka antaa hakemiston sisällön paluuarvona.
        OmaLista sisalto = tulkki.hakemistonSisalto();
        // Käydään paluuarvona saatu lista läpi ja tulostetaan listan alkiot.
        for (int i = 0; i < sisalto.koko(); i++) {
            System.out.println(sisalto.alkio(i).toString());
        }
    }

    /** Metodi tulostaa virheilmoituksen.*/
    private void error() {
        System.out.println(VIRHEILMOITUS);
    }

}
