package oope2017ht;

import apulaiset.In;

public class StdTerminaali {

    public void tulosta(String tuloste) {
        System.out.println(tuloste);
    }

    public String lueSyote(String kehote) {
        System.out.print(kehote);
        return In.readString();
    }

}
