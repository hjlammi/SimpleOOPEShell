package oope2017ht;

/*
* Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
*
* Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
*
* Viimeksi muokattu 23.4.2017.
*
* Ajoluokan tehtävänä on luoda käyttöliittymä ja kutsua sen suorita-metodia.
*/

public class Oope2017HT
{
    public static void main(String[] args) {
        // Luodaan Kayttoliittyma-olio.
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();

        // Kutsutaan olion suorita-metodia.
        kayttoliittyma.suorita();
    }
}
