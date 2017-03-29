package oope2017ht;

/**
 * Hello world!
 *
 */
public class Oope2017HT
{
    public static void main(String[] args) {
        UI ui = new KonsoliUI();
        Tulkki tulkki = new Tulkki(ui);

        tulkki.suorita();
    }
}
