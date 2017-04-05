package oope2017ht;

import oope2017ht.UI;
import oope2017ht.omalista.OmaLista;

public class TestiUI implements UI {
    public OmaLista tulosteet = new OmaLista();
    public OmaLista syotteet = new OmaLista();
    public OmaLista kehotteet = new OmaLista();

    @Override
    public void tulosta(String tuloste) {
        tulosteet.lisaaLoppuun(tuloste);
    }

    @Override
    public String lueSyote(String kehote) {
        kehotteet.lisaaLoppuun(kehote);
        return (String)syotteet.poistaAlusta();
    }
}
