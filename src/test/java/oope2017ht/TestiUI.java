package oope2017ht;

/*
* Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
*
* Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
*
* Viimeksi muokattu 5.4.2017.
*
*
*/

import oope2017ht.OmaLista;
import oope2017ht.UI;

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
