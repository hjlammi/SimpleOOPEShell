package oope2017ht;

import apulaiset.In;

public class KonsoliUI implements UI {

    @Override
    public void tulosta(String tuloste) {
        System.out.println(tuloste);
    }

    @Override
    public String lueSyote(String kehote) {
        System.out.print(kehote);
        return In.readString();
    }

}
