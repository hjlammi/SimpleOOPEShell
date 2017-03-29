package oope2017ht;

import oope2017ht.OmaLista;
import oope2017ht.UI;

public class TestiUI implements UI {
    OmaLista tulosteet = new OmaLista();

    @Override
    public void tulosta(String tuloste) {
        tulosteet.lisaaLoppuun(tuloste);
    }
}
