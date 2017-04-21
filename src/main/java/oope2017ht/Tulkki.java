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
* Viimeksi muokattu 20.4.2017.
*
*
*/

public class Tulkki {

    /*
     *  Vakiot.
     */



    private static final String REKURSIIVINEN_LISTAAMINEN = "find";


    private static final String HAKEMISTON_VAIHTAMINEN = "cd";


    private static final String POISTAMINEN = "rm";


    private static final String KOPIOIMINEN = "cp";


    private static final String UUDELLEEN_NIMEAMINEN = "mv";


    private static final String TIEDOSTON_LUOMINEN = "mf";


    private static final String HAKEMISTON_LUOMINEN = "md";


    private static final String LISTAAMINEN = "ls";


    private static final String LOPETUS = "exit";




    private static final String KEHOTE = ">";

    private static final String TERVEHDYS = "Welcome to SOS.";

    private static final String LOPETUSVIESTI = "Shell terminated.";

    private static final String VIRHEILMOITUS = "Error!";

    /*
     * Attribuutit.
     */

    private UI ui;

    private Hakemisto juurihakemisto;

    private Hakemisto tyohakemisto;

    /*
     * Rakentajat.
     */
    public Tulkki(UI ui) {
        this.ui = ui;
        this.juurihakemisto = new Hakemisto();
        this.tyohakemisto = juurihakemisto;
    }

    public Tulkki(UI ui, Hakemisto juurihakemisto) {
        this.ui = ui;
        this.juurihakemisto = juurihakemisto;
        this.tyohakemisto = juurihakemisto;
    }

    // Aksessorit.

    public Hakemisto tyohakemisto() {
        return tyohakemisto;
    }

    public void tyohakemisto(Hakemisto tyohakemisto) {
        if (tyohakemisto != null) {
            this.tyohakemisto = tyohakemisto;
        }
    }

    public void suorita() {
        ui.tulosta(TERVEHDYS);

        String syote;
        do {
            // Kutsutaan metodia, tulostaa näytölle nykyisen työhakemiston hakemistopolun sekä kehotteen ja
            // asettaa viitten käyttäjän antamaan syötteeseen.
            syote = ui.lueSyote(tyohakemisto.hakemistopolku() + KEHOTE);
            // Pilkotaan syöte osiin välilyöntien kohdalta ja tallennetaan syötteen osat taulukkoon.
            String osat[] = syote.split(" ");
            try {
                // Jos syöte alkaa tai loppuu välilyöntiin, kutsutaan error-metodia, joka tulostaa virheilmoituksen.
                if (syote.endsWith(" ") || syote.startsWith(" ")) {
                    error();
                // Jos käyttäjä haluaa poistua ohjelmasta tulostetaan lopetusviesti.
                } else if (osat[0].equals(LOPETUS) && osat.length == 1) {
                    ui.tulosta(LOPETUSVIESTI);
                // Jos käyttäjä haluaa listata hakemiston sisällön:
                } else if (osat[0].equals(LISTAAMINEN) && osat.length == 1) {
                    listaaHakemistonSisalto();
                // Jos käyttäjä haluaa tulostaa näytölle tiedon merkkijonoesityksen:
                } else if (osat[0].equals(LISTAAMINEN) && osat.length == 2) {
                    tulostaTietoMjonona(osat);
                // Jos käyttäjä haluaa luoda hakemiston parametrina antamallansa nimellä:
                } else if (osat[0].equals(HAKEMISTON_LUOMINEN) && osat.length == 2) {
                    luoHakemisto(osat);
                // Jos käyttäjä haluaa luoda tiedoston antamallaan nimellä ja koolla:
                } else if (osat[0].equals(TIEDOSTON_LUOMINEN) && osat.length == 3) {
                    luoTiedosto(osat);
                // Jos käyttäjä haluaa uudelleennimetä olemassa olevan tiedon:
                } else if (osat[0].equals(UUDELLEEN_NIMEAMINEN) && osat.length == 3) {
                    nimeaUudelleen(osat);
                // Jos käyttäjä haluaa kopioida tiedoston:
                } else if (osat[0].equals(KOPIOIMINEN) && osat.length == 3) {
                    kopioTiedosto(osat);
                // Jos käyttäjä haluaa poistaa tiedon:
                } else if (osat[0].equals(POISTAMINEN) && osat.length == 2) {
                    poista(osat);
                // Jos käyttäjä haluaa siirtyä takaisin juurihakemistoon, asetetaan työhakemistoksi juurihakemisto.
                } else if (osat[0].equals(HAKEMISTON_VAIHTAMINEN) && osat.length == 1) {
                    siirryJuurihakemistoon();
                // Jos käyttäjä haluaa siirtyä johonkin alihakemistoistaan:
                } else if (osat[0].equals(HAKEMISTON_VAIHTAMINEN) && !osat[1].equals("..") && osat.length == 2) {
                    siirryAlihakemistoon(osat);
                // Jos käyttäjä haluaa siirtyä nykyisen hakemiston ylihakemistoon:
                } else if (osat[0].equals(HAKEMISTON_VAIHTAMINEN) && osat[1].equals("..") && osat.length == 2) {
                    siirryYlihakemistoon();
                } else if (osat[0].equals(REKURSIIVINEN_LISTAAMINEN) && osat.length == 1) {
                    puunTulostus(tyohakemisto);
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
        // Suoritetaan silmukkaa kunnes käyttäjä syötteen lopetuskomennon.
        } while (!syote.equals(LOPETUS));
    }

    private void siirryJuurihakemistoon() {
        tyohakemisto(juurihakemisto);
    }

    private void siirryYlihakemistoon() {
        // Juurihakemistosta ei voi siirtyä ylihakemistoon.
        if (tyohakemisto == juurihakemisto) {
            error();
        } else {
            // Asetetaan viite nykyiseen hakemistoon.
            Hakemisto nykyinenHakemisto = tyohakemisto();
            // Asetetaan työhakemistoksi nykyisen hakemiston ylihakemisto.
            tyohakemisto(nykyinenHakemisto.ylihakemisto());
        }
    }

    private void siirryAlihakemistoon(String[] osat) {
        // Alihakemiston nimi saadaan parametrina.
        String nimi = osat[1];
        // Haetaan hakemistosta nimellä.
        Tieto alkio = tyohakemisto.hae(nimi);
        // Tarkistetaan, että hakemistosta löytyy senniminen alihakemisto, johon halutaan siirtyä
        // ja että tieto on tyyppiä Hakemisto.
        if (alkio != null && alkio instanceof Hakemisto) {
            tyohakemisto((Hakemisto)alkio);
        } else {
            error();
        }
    }

    private void poista(String[] osat) {
        // Poistettavan nimi on saadaan parametrina käyttäjältä.
        String poistettava = osat[1];
        // Kutsutaan Hakemiston metodia, joka poistaa nimeä vastaavan olion.
        Tieto poistettavaTieto = tyohakemisto.poista(poistettava);
        // Jos poisto ei onnistunut, palautetaan null ja tulostetaan virheilmoitus.
        if (poistettavaTieto == null) {
            error();
        }
    }

    private void kopioTiedosto(String[] osat) {
        // Kopioitavan tiedoston nimi on ensimmäinen parametri.
        String nimi = osat[1];
        // Kopion nimi on toinen parametri.
        String kopioNimi = osat[2];
        // Haetaan hakemistosta kopioitava tiedosto nimen perusteella.
        Tieto kopioitava = tyohakemisto.hae(nimi);
        // Tarkistetaan, että tieto löyty, että se on Tiedosto-tyyppinen
        // ja ettei kopion nimi ole jo käytössä hakemistossa.
        if (kopioitava != null && kopioitava instanceof Tiedosto && !nimiVarattu(kopioNimi)) {
            // Syväkopioidaan tiedosto.
            Tiedosto kopio = new Tiedosto((Tiedosto)kopioitava);
            // Annetaan kopiolle nimeksi komentoriviparametrina saatu uusi nimi.
            kopio.nimi(new StringBuilder(kopioNimi));
            // Lisätään kopio hakemistoon.
            tyohakemisto.lisaa(kopio);
        } else {
            error();
        }
    }

    private void nimeaUudelleen(String[] osat) {
        // Vaihdettava nimi on komennon ensimmäinen parametri.
        String vaihdettavaNimi = osat[1];
        // Uusi nimi on komennon toinen parametri.
        String uusiNimi = osat[2];
        // Kutsutaan metodia, joka tutkii onko parametrina saamansa uusi nimi jo varattu. Jos nimi on varattu,
        // paluuarvo on true ja tulostetaan virheilmoitus.
        if (nimiVarattu(uusiNimi)) {
            error();
        } else {
            // Jos hakemistosta löytyy vaihdettavan nimen mukainen tieto, asetetaan alkiolle uusi nimi.
            Tieto alkio = (Tieto)tyohakemisto.hae(vaihdettavaNimi);
                // Jos haetun nimistä tietoa ei löytynyt tulostetaan virheilmoitus.
                if (alkio == null) {
                    error();
                } else {
                    alkio.nimi(new StringBuilder(uusiNimi));
                }
        }
    }

    private void luoTiedosto(String[] osat) {
        // Nimi on komennon ensimmäinen parametri.
        String nimi = osat[1];
        // Koko on komennon toinen parametri.
        int koko = Integer.parseInt(osat[2]);
        // Luodaan uusi tiedosto-olio ja annetaan rakentajalle parametreina käyttäjän antamat nimi ja koko.
        Tiedosto lisattava = new Tiedosto(new StringBuilder(nimi), koko);
        // Kutsutaan Hakemiston lisaa-metodia, joka lisää tiedoston työhakemistoon.
        // Paluuarvo on true, jos lisääminen onnistuu.
        boolean onnistui = tyohakemisto.lisaa(lisattava);
             // Jos paluuarvo oli false, lisääminen ei onnistunut ja tulostetaan virheilmoitus.
        if (!onnistui) {
            error();
        }
    }

    private void luoHakemisto(String[] osat) {
        // Hakemiston nimi saadaan parametrina.
        String nimi = osat[1];
        // Luodaan uusi hakemisto-olio parametrina annetulla nimellä.
        Hakemisto lisattava = new Hakemisto(new StringBuilder(nimi), tyohakemisto);
        // Kutsutaan Hakemiston lisaa-metodia, joka lisää hakemiston työhakemistoon.
        // Paluuarvo on true, jos lisääminen onnistuu.
        boolean onnistui = tyohakemisto.lisaa(lisattava);
        // Jos paluuarvo oli false, lisääminen ei onnistunut ja tulostetaan virheilmoitus.
        if (!onnistui) {
            error();
        }
    }

    private void tulostaTietoMjonona(String[] osat) {
        // Tulostettavan tiedon nimi saadaan parametrina.
        String nimi = osat[1];
        // Haetaan hakemistosta tietoa nimellä hyödyntäen Hakemiston hae-metodia.
        Tieto alkio = tyohakemisto.hae(nimi);
        // Jos nimeä vastaavaa tietoa ei löytynyt hakemistosta tulostetaan virheilmoitus.
        // Muussa tapauksessa tulostetaan tiedon merkkijonoesitys.
        if (alkio == null) {
            error();
        } else {
            ui.tulosta(alkio.toString());
        }
    }

    private void listaaHakemistonSisalto() {
        // Viite nykyisen hakemiston tietoihin.
        OmaLista tiedot = tyohakemisto.tiedot();
        // Tulostetaan työhakemiston tiedot alkio kerrallaan.
        for (int i = 0; i < tiedot.koko(); i++) {
            ui.tulosta(tiedot.alkio(i).toString());
        }
    }

    // Metodi tulostaa parametrina saamansa hakemiston hakemistopuun rekursiivisesti.
    private void puunTulostus(Hakemisto hakemisto) {
        // Asetetaan viite parametrina saadun hakemiston tietoihin.
        OmaLista sisalto = hakemisto.tiedot();
        // Käydään hakemiston sisältö läpi.
        for (int i = 0; i < sisalto.koko(); i++) {
            // Asetetaan viite tietoalkioon.
            Tieto tieto = (Tieto)sisalto.alkio(i);
            // Kutsutaan käyttöliittymän tulostusmetodia, joka ottaa parametrina merkkijonon.
            // Merkkijono koostuu nykyisen hakemiston hakemistopolusta sekä tiedon merkkijonoesityksestä.
            ui.tulosta(hakemisto.hakemistopolku() + tieto.toString());
            // Jos tieto on Hakemisto-tyyppinen, metodi kutsuu itseään ja antaa tiedon parametrina.
            if (tieto instanceof Hakemisto) {
                puunTulostus((Hakemisto)tieto);
            }
        }
    }

    // Apumetodi uudelleennimeämis- ja kopioimiskomentojen käytettäväksi. Tutkii onko hakemistossa jo parametrina
    // annetulla nimellä tiedosto tai hakemisto. Jos samanniminen löytyy, palautetaan
    // true, jos samannimistä ei löydy, palautetaan false.
    private boolean nimiVarattu(String nimi) {
        // Kutsutaan Hakemiston hae-metodia, joka palauttaa nullin, jos haettavalla nimellä ei löydy Tietoa.
        Tieto alkio = tyohakemisto.hae(nimi);
        // Samannimistä ei löytynyt.
        if (alkio == null) {
            return false;
        } else {
            return true;
        }
    }

    // Metodi kutsuu käyttöliittymän tulosta-metodia, joka tulostaa virheilmoituksen.
    private void error() {
        ui.tulosta(VIRHEILMOITUS);
    }


}
