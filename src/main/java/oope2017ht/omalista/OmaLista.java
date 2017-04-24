package oope2017ht.omalista;

import apulaiset.Ooperoiva;
import fi.uta.csjola.oope.lista.LinkitettyLista;

/**
  * OmaLista-luokka periytyy LinkitettyLista-luokasta ja toteuttaa Ooperoiva-rajapinnan.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * Viimeksi muokattu 13.3.2017.
  * <p>
  * @author Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
  */

public class OmaLista extends LinkitettyLista implements Ooperoiva {

    /**
      * Hakee listalta parametri-oliota equals-mielessä vastaavan olion, johon antaa viitteen paluuarvona.
      * Oletetaan, että listassa on korkeintaan yksi samanlainen olio. Paluuarvo on null,
      * jos parametri on null, jos lista on tyhjä tai jos vastaavaa alkiota ei löydy.
      */
    public Object hae(Object haettava) {
        // Tarkistetaan ettei parametri ole null-arvoinen ja ettei lista ole tyhjä.
        if (haettava != null && !onkoTyhja()) {
            // Käydään lista läpi ja verrataan haettavaa jokaiseen listan alkioon.
            // Jos haettava ja alkio vastaavat toisiaan palautetaan viite alkioon.
            for (int i = 0; i < koko(); i++) {
                if (haettava.equals(alkio(i))) {
                    return alkio(i);
                }
            }
            return null;
        } else {
            return null;
        }
    }

    /**
      * Lisaa-metodi vertailee parametrina saamaansa oliota ja listassa olevaa olioita compareTo-metodilla
      * ja sijoittaa parametri-olion kaikkien itseään pienempien tai yhtä suurien alkioiden
      * jälkeen ja ennen kaikkia itseään suurempia alkioita. Metodilla saadaan siis tehtyä
      * kasvavassa suuruusjärjestyksessä oleva lista. Paluuarvo on true, jos lisäys onnistui
      * ja false, jos parametri on null, jos olioita ei voitu vertailla tai jos oliot eivät ole samantyyppisiä,
      * jolloin niitä ei voi vertailla keskenään.
      */

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean lisaa(Object uusi) {
        // Tarkistetaan, että olio toteuttaa Comparable-rajapinnan.
        if (uusi instanceof Comparable) {
            // Jos lista on tyhjä, lisätään uusi-alkio listan alkuun ja palautetaan true.
            if (onkoTyhja()) {
                lisaaAlkuun(uusi);
                return true;
            // Jos lista ei ole tyhjä, käydään se läpi ja verrataan uusi-alkiota listan paikassa i
            // olevaan alkioon. Jos uusi on pienempi kuin alkio, lisätään uusi paikkaan i ja vanha
            // alkio siirtyy yhden paikan eteenpäin. Palautetaan true lisäämisen onnistumisen merkiksi.
            // Jos uusi-alkio ei ole pienempi kuin yksikään alkio listassa, lisätään se listan loppuun
            // ja palautetaan true.
            } else {
                for (int i = 0; i < koko(); i++) {
                    if (((Comparable)uusi).compareTo(alkio(i)) < 0) {
                        lisaa(i, uusi);
                        return true;
                    }
                }
                lisaaLoppuun(uusi);
                return true;
            }
        } else {
            return false;
        }
    }

    /**
      * Poistaa listalta parametri-oliota equals-mielessä vastaavan olion, johon antaa viitteen paluuarvona.
      * Oletetaan, että listassa on korkeintaan yksi samanlainen olio. Paluuarvo on null,
      * jos parametri on null, jos lista on tyhjä tai jos poistettavaa alkiota ei löydy.
      */
    public Object poista(Object poistettava) {
        // Tarkistetaan, ettei poistettava ole null-arvoinen eikä taulukko ole tyhjä.
        if (poistettava != null && !onkoTyhja()) {
            // Käydään lista läpi ja jos paikassa i oleva alkio vastaa poistettavaa alkiota,
            // palautetaan alkio paikasta i. Jos vastaavaa alkiota ei löydy palautetaan null.
            for (int i = 0; i < koko(); i++) {
                if (poistettava.equals(alkio(i))) {
                    return poista(i);
                }
            }
            return null;
        } else {
            return null;
        }
    }

}
