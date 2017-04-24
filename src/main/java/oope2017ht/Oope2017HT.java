package oope2017ht;

/**
  * Ajoluokan tehtävänä on luoda käyttöliittymä ja suorittaa ohjelma.
  * <p>
  * Harjoitustyö, Olio-ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * Viimeksi muokattu 23.4.2017.
  * <p>
  * @author Heidi Lammi-Mihaljov, Lammi-Mihaljov.Heidi.J@student.uta.fi.
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
