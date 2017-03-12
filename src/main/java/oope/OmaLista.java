package oope;

import apulaiset.Ooperoiva;

import fi.uta.csjola.oope.lista.LinkitettyLista;

public class OmaLista extends LinkitettyLista implements Ooperoiva {

    public Object hae(Object haettava) {
        if (haettava == null) {
            return null;
        } else if (onkoTyhja()) {
            return null;
        } else {
            for (int i = 0; i < koko(); i++) {
                if (haettava.equals(alkio(i))) {
                    return alkio(i);
                }
            }
            return null;
        }
    }

    public boolean lisaa(Object uusi) {
        // TODO Auto-generated method stub
        return false;
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
