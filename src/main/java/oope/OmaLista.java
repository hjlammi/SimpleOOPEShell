package oope;

/*
 * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
 *
 * Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
 *
 * Viimeksi muokattu 13.3.2017.
 *
 * OmaLista-luokka periytyy LinkitettyLista-luokasta ja toteuttaa Ooperoiva-rajapinnan.
 */

import apulaiset.Ooperoiva;

import fi.uta.csjola.oope.lista.LinkitettyLista;

public class OmaLista extends LinkitettyLista implements Ooperoiva {

    /*
     * (non-Javadoc)
     * @see apulaiset.Ooperoiva#hae(java.lang.Object)
     */
    public Object hae(Object haettava) {
        if (haettava != null && !onkoTyhja()) {
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

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean lisaa(Object uusi) {
        if (uusi instanceof Comparable) {
            if (onkoTyhja()) {
                lisaaAlkuun(uusi);
                return true;
            } else {
                for (int i = 0; i < koko(); i++) {
                    if (uusi.getClass().equals(alkio(i).getClass())) {
                        if (((Comparable)uusi).compareTo(alkio(i)) < 0) {
                            lisaa(i, uusi);
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                lisaaLoppuun(uusi);
                return true;
            }
        } else {
            return false;
        }
    }

    public Object poista(Object poistettava) {
        if (poistettava != null) {
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
