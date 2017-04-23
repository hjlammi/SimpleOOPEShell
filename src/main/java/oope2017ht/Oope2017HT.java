package oope2017ht;

/*
* Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
*
* Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
*
* Viimeksi muokattu 12.4.2017.
*
*
*/

public class Oope2017HT
{
    public static void main(String[] args) {
        StdTerminaali terminaali = new StdTerminaali();
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(terminaali);

        kayttoliittyma.suorita();
    }
}
