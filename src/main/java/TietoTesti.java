// Hierarkian luokkien oletetaan olevan oope2017ht.tiedot-pakkauksessa.
import oope2017ht.tiedot.*;

// Otetaan käyttöön In-luokka.
import apulaiset.*;

/*
 * Harjoitustyö.
 *
 * Olio-ohjelmoinnin perusteet, kevät 2017, Jorma Laurikkala, jorma.laurikkala@uta.fi.
 *
 * Luokkahierarkiaa testaava luokka, jota EI OLE SYYTÄ MUUTTAA MILLÄÄN TAVALLA,
 * koska WETO käyttää aina tämän luokan alkuperäistä versiota.
 *
 * Testit ovat main-metodissa. WETO käy läpi testit alla annetussa järjestyksessä.
 *
 * Aja luokka hakemistossa, jonka välittöminä alihakemistoina ovat oope2017ht-
 * apulaiset- ja lista-pakkausten hakemistot.
 *
 */

public class TietoTesti {

   // Pitkän pitkä testirakenne.
   public static void main(String[] args) {
      // Vakiot testien pienimmälle ja suuremmalle testinumerolle.
      final int MINNRO = 19;
      final int MAXNRO = 24;

      // Luetaan testinumero.
      System.out.println("Enter test number (" + MINNRO + " - " + MAXNRO + "):");
      int testinumero = In.readInt();

      /*
       * Lisäystestit.
       *
       */

      // Testi 19.
      if (testinumero == 19) {
         Tiedosto tiedosto = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
         // grumpy_cat.jpeg 335932
         System.out.println(tiedosto);
         Hakemisto hakemisto = new Hakemisto(new StringBuilder("cats"), null);
         // cats/ 0
         System.out.println(hakemisto);
      }

      // Testi 20.
      else if (testinumero == 20) {
         try {
            // Expected exception.
            Tiedosto tiedosto = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), -1);
         }
         catch (IllegalArgumentException e) {
            System.out.println("Expected exception.");
         }
         catch (Exception e) {
            System.out.println("Error!");
         }
      }

      // Testi 21.
      else if (testinumero == 21) {
         try {
            // Expected exception.
            Hakemisto hakemisto = new Hakemisto(new StringBuilder("cat$"), null);
         }
         catch (IllegalArgumentException e) {
            System.out.println("Expected exception.");
         }
         catch (Exception e) {
            System.out.println("Error!");
         }
      }

      // Testi 22.
      else if (testinumero == 22) {
         Object tiedosto1 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
         Object tiedosto2 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335000);
         Object tiedosto3 = new Tiedosto(new StringBuilder("cannot_be_unseen.jpeg"), 29614);
         Object tiedosto4 = new Tiedosto(new StringBuilder("abc"), 42);
         Object hakemisto1 = new Hakemisto(new StringBuilder("cats"), null);
         Object hakemisto2 = new Hakemisto(new StringBuilder("cats"), null);
         Object hakemisto3 = new Hakemisto(new StringBuilder("dogs"), null);
         Object hakemisto4 = new Hakemisto(new StringBuilder("abc"), null);

         // true
         // true
         // true
         // false
         // false
         System.out.println(tiedosto1.equals(tiedosto1));
         System.out.println(tiedosto1.equals(tiedosto2));
         System.out.println(tiedosto2.equals(tiedosto1));
         System.out.println(tiedosto1.equals(tiedosto3));
         System.out.println(tiedosto3.equals(tiedosto1));

         // true
         // true
         // true
         // false
         // false
         System.out.println(hakemisto1.equals(hakemisto1));
         System.out.println(hakemisto1.equals(hakemisto2));
         System.out.println(hakemisto2.equals(hakemisto1));
         System.out.println(hakemisto1.equals(hakemisto3));
         System.out.println(hakemisto3.equals(hakemisto1));

         // false
         // false
         // true
         // true
         System.out.println(tiedosto1.equals(hakemisto1));
         System.out.println(hakemisto1.equals(tiedosto1));
         System.out.println(tiedosto4.equals(hakemisto4));
         System.out.println(hakemisto4.equals(tiedosto4));

         // false
         // false
         System.out.println(tiedosto4.equals(null));
         System.out.println(hakemisto1.equals(null));

         // false
         System.out.println(tiedosto1.equals(new StringBuilder("X")));
      }

      // Testi 5. String-luokan Comparable-toteutus palautaa arvot < 0, 0 ja > 0.
      else if (testinumero == 23) {
         Comparable<Tieto> tiedosto1 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
         Comparable<Tieto> tiedosto2 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335000);
         Comparable<Tieto> tiedosto3 = new Tiedosto(new StringBuilder("cannot_be_unseen.jpeg"), 29614);
         Comparable<Tieto> tiedosto4 = new Tiedosto(new StringBuilder("abc"), 42);
         Comparable<Tieto> hakemisto1 = new Hakemisto(new StringBuilder("cats"), null);
         Comparable<Tieto> hakemisto2 = new Hakemisto(new StringBuilder("cats"), null);
         Comparable<Tieto> hakemisto3 = new Hakemisto(new StringBuilder("dogs"), null);
         Comparable<Tieto> hakemisto4 = new Hakemisto(new StringBuilder("abc"), null);

         // 0
         // 0
         // 0
         // 4
         // -4
         System.out.println(tiedosto1.compareTo((Tieto)tiedosto1));
         System.out.println(tiedosto1.compareTo((Tieto)tiedosto2));
         System.out.println(tiedosto2.compareTo((Tieto)tiedosto1));
         System.out.println(tiedosto1.compareTo((Tieto)tiedosto3));
         System.out.println(tiedosto3.compareTo((Tieto)tiedosto1));

         // 0
         // 0
         // 0
         // -1
         // 1
         System.out.println(hakemisto1.compareTo((Tieto)hakemisto1));
         System.out.println(hakemisto1.compareTo((Tieto)hakemisto2));
         System.out.println(hakemisto2.compareTo((Tieto)hakemisto1));
         System.out.println(hakemisto1.compareTo((Tieto)hakemisto3));
         System.out.println(hakemisto3.compareTo((Tieto)hakemisto1));

         // 4
         // -4
         // 0
         // 0
         System.out.println(tiedosto1.compareTo((Tieto)hakemisto1));
         System.out.println(hakemisto1.compareTo((Tieto)tiedosto1));
         System.out.println(tiedosto4.compareTo((Tieto)hakemisto4));
         System.out.println(hakemisto4.compareTo((Tieto)tiedosto4));
      }

      // Testi 6.
      else if (testinumero == 24) {
         Tiedosto tiedosto = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
         Tiedosto kopio = new Tiedosto(tiedosto);
         // 24
         // grumpy_cat.jpeg 335932
         // grumpy_cat.jpeg 335932
         // true
         // 0
         System.out.println(tiedosto);
         System.out.println(kopio);
         System.out.println(tiedosto.equals(kopio));
         System.out.println(tiedosto.compareTo(kopio));
      }

      // Tuntematon testi.
      else
         System.out.println("Erroneus test number.");
   }
}
