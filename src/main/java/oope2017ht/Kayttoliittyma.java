package oope2017ht;

import oope2017ht.omalista.OmaLista;
import oope2017ht.tiedot.Hakemisto;

/*
* Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
*
* Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
*
* Viimeksi muokattu 21.4.2017.
*
*
*/

public class Kayttoliittyma {

    /*
     * Attribuutit.
     */

    private Terminaali terminaali;

    Tulkki tulkki;

    /*
     * Rakentajat.
     */
    public Kayttoliittyma(Terminaali terminaali) {
        this.tulkki = new Tulkki();
        this.terminaali = terminaali;
        tulkki.juurihakemisto = new Hakemisto();
        tulkki.tyohakemisto = tulkki.juurihakemisto;
    }

    public Kayttoliittyma(Terminaali terminaali, Hakemisto juurihakemisto) {
        this.tulkki = new Tulkki();
        this.terminaali = terminaali;
        tulkki.juurihakemisto = juurihakemisto;
        tulkki.tyohakemisto = juurihakemisto;
    }

    public void suorita() {
        // Kun ohjelma käynnistyy tulostetaan tervehdysviesti.
        terminaali.tulosta(Tulkki.TERVEHDYS);

        String syote;
        do {
            // Kutsutaan metodia, tulostaa näytölle nykyisen työhakemiston hakemistopolun sekä kehotteen ja
            // lukee käyttäjän antaman syötteen.
            syote = terminaali.lueSyote(tulkki.tyohakemisto.hakemistopolku() + Tulkki.KEHOTE);
            // Pilkotaan syöte osiin välilyöntien kohdalta ja tallennetaan syötteen osat taulukkoon.
            String osat[] = syote.split(" ");
            try {
                // Jos syöte alkaa tai loppuu välilyöntiin, kutsutaan error-metodia, joka tulostaa virheilmoituksen.
                if (syote.endsWith(" ") || syote.startsWith(" ")) {
                    error();
                // Jos käyttäjä haluaa poistua ohjelmasta tulostetaan lopetusviesti.
                } else if (osat[0].equals(Tulkki.LOPETUS) && osat.length == 1) {
                    terminaali.tulosta(Tulkki.LOPETUSVIESTI);
                // Jos käyttäjä haluaa listata hakemiston sisällön:
                } else if (osat[0].equals(Tulkki.LISTAAMINEN) && osat.length == 1) {
                    OmaLista sisalto = tulkki.hakemistonSisalto();
                    for (int i = 0; i < sisalto.koko(); i++) {
                        terminaali.tulosta(sisalto.alkio(i).toString());
                    }
                // Jos käyttäjä haluaa tulostaa näytölle tiedon merkkijonoesityksen:
                } else if (osat[0].equals(Tulkki.LISTAAMINEN) && osat.length == 2) {
                    String mjono = tulkki.tietoMjonona(osat);
                    if (mjono == null) {
                        error();
                    } else {
                        terminaali.tulosta(mjono);
                    }
                // Jos käyttäjä haluaa luoda hakemiston parametrina antamallansa nimellä:
                } else if (osat[0].equals(Tulkki.HAKEMISTON_LUOMINEN) && osat.length == 2) {
                    String nimi = osat[1];
                    if (!tulkki.luoHakemisto(nimi)) {
                        error();
                    }
                // Jos käyttäjä haluaa luoda tiedoston antamallaan nimellä ja koolla:
                } else if (osat[0].equals(Tulkki.TIEDOSTON_LUOMINEN) && osat.length == 3) {
                    if (!tulkki.luoTiedosto(osat)) {
                        error();
                    }
                // Jos käyttäjä haluaa uudelleennimetä olemassa olevan tiedon:
                } else if (osat[0].equals(Tulkki.UUDELLEEN_NIMEAMINEN) && osat.length == 3) {
                    if (!tulkki.nimeaUudelleen(osat)) {
                        error();
                    }
                // Jos käyttäjä haluaa kopioida tiedoston:
                } else if (osat[0].equals(Tulkki.KOPIOIMINEN) && osat.length == 3) {
                    if (!tulkki.kopioiTiedosto(osat)) {
                        error();
                    }
                // Jos käyttäjä haluaa poistaa tiedon:
                } else if (osat[0].equals(Tulkki.POISTAMINEN) && osat.length == 2) {
                    if (!tulkki.poista(osat)) {
                        error();
                    }
                // Jos käyttäjä haluaa siirtyä takaisin juurihakemistoon, asetetaan työhakemistoksi juurihakemisto.
                } else if (osat[0].equals(Tulkki.HAKEMISTON_VAIHTAMINEN) && osat.length == 1) {
                    tulkki.siirryJuurihakemistoon();
                // Jos käyttäjä haluaa siirtyä johonkin alihakemistoistaan:
                } else if (osat[0].equals(Tulkki.HAKEMISTON_VAIHTAMINEN) && !osat[1].equals("..") && osat.length == 2) {
                    String nimi = osat[1];
                    if (!tulkki.siirryAlihakemistoon(nimi)) {
                        error();
                    }
                // Jos käyttäjä haluaa siirtyä nykyisen hakemiston ylihakemistoon:
                } else if (osat[0].equals(Tulkki.HAKEMISTON_VAIHTAMINEN) && osat[1].equals("..") && osat.length == 2) {
                    if (!tulkki.siirryYlihakemistoon()) {
                        error();
                    }
                } else if (osat[0].equals(Tulkki.REKURSIIVINEN_LISTAAMINEN) && osat.length == 1) {
                    OmaLista hakemistopuu = tulkki.hakemistopuunSisalto(tulkki.tyohakemisto);
                    for (int i = 0; i < hakemistopuu.koko(); i++) {
                        terminaali.tulosta(hakemistopuu.alkio(i).toString());
                    }
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
        } while (!syote.equals(Tulkki.LOPETUS));
    }


    // Metodi kutsuu terminaalin tulosta-metodia, joka tulostaa virheilmoituksen.
    private void error() {
        terminaali.tulosta(Tulkki.VIRHEILMOITUS);
    }

}
