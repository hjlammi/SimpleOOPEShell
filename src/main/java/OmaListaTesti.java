// Otetaan käyttöön In-luokka.
import apulaiset.*;
import oope2017ht.omalista.OmaLista;

/*
 * Harjoitustyö.
 *
 * Olio-ohjelmoinnin perusteet, kevät 2017, Jorma Laurikkala, jorma.laurikkala@uta.fi.
 *
 * Omaa listaa testaava luokka, jota EI OLE SYYTÄ MUUTTAA MILLÄÄN TAVALLA,
 * koska WETO käyttää aina tämän luokan alkuperäistä versiota.
 *
 * Testit ovat main-metodissa. WETO käy läpi testit alla annetussa järjestyksessä.
 *
 * Aja luokka hakemistossa, jonka välittöminä alihakemistoina ovat oope2017ht-
 * apulaiset- ja lista-pakkausten hakemistot.
 *
 */

public class OmaListaTesti {

   /* Apumetodi listan tulostamiseen.
    */
   private static void tulosta(OmaLista lista) {
      if (lista != null) {
         System.out.print("[ ");
         for (int i = 0; i < lista.koko(); i++)
            System.out.print(lista.alkio(i) + " ");
         System.out.println("]");
      }
   }

   /* Apumetodi listan metodin testaamiseen.
    */
   private static void testaaLisaamista(Ooperoiva lista, Object uusi) {
      if (lista != null) {
         System.out.println("Adding " + uusi + ".");
         boolean tulos = lista.lisaa(uusi);
         System.out.println(tulos);
         tulosta((OmaLista)lista);
      }
   }

   /* Apumetodi listan metodin testaamiseen.
    */
   private static void testaaHakua(Ooperoiva lista, Object haettava) {
      if (lista != null) {
         tulosta((OmaLista)lista);
         System.out.println("Searching " + haettava + ".");
         Object tulos = lista.hae(haettava);
         System.out.println(tulos);
      }
   }

   /* Apumetodi listan metodin testaamiseen.
    */
   private static void testaaPoistoa(Ooperoiva lista, Object poistettava) {
      if (lista != null) {
         System.out.println("Removing " + poistettava + ".");
         Object tulos = lista.poista(poistettava);
         System.out.println(tulos);
         tulosta((OmaLista)lista);
      }
   }

   // Pitkän pitkä testirakenne.
   public static void main(String[] args) {
      // Vakiot testien pienimmälle ja suuremmalle testinumerolle.
      final int MINNRO = 1;
      final int MAXNRO = 18;

      // Luodaan lista.
      Ooperoiva lista = new OmaLista();

      // Luetaan testinumero.
      System.out.println("Enter test number (" + MINNRO + " - " + MAXNRO + "):");
      int testinumero = In.readInt();

      /*
       * Lisäystestit.
       *
       */

      // Testi 1.
      if (testinumero == 1) {
         // Adding C.
         // true
         // [ C ]
         testaaLisaamista(lista, "C");
      }

      // Testi 2.
      else if (testinumero == 2) {
         // Adding C.
         // true
         // [ C ]
         // Adding A.
         // true
         // [ A C ]
         testaaLisaamista(lista, "C");
         testaaLisaamista(lista, "A");
      }

      // Testi 3.
      else if (testinumero == 3) {
         // Adding C.
         // true
         // [ C ]
         // Adding A.
         // true
         // [ A C ]
         // Adding B.
         // true
         // [ A B C ]
         testaaLisaamista(lista, "C");
         testaaLisaamista(lista, "A");
         testaaLisaamista(lista, "B");
      }

      // Testi 4.
      else if (testinumero == 4) {
         // Adding C.
         // true
         // [ C ]
         // Adding A.
         // true
         // [ A C ]
         // Adding B.
         // true
         // [ A B C ]
         // Adding D.
         // true
         // [ A B C D ]
         testaaLisaamista(lista, "C");
         testaaLisaamista(lista, "A");
         testaaLisaamista(lista, "B");
         testaaLisaamista(lista, "D");
      }

      // Testi 5.
      else if (testinumero == 5) {
         // Adding 3.
         // true
         // [ 3 ]
         // Adding 0.
         // true
         // [ 0 3 ]
         // Adding 2.
         // true
         // [ 0 2 3 ]
         testaaLisaamista(lista, 3);
         testaaLisaamista(lista, 0);
         testaaLisaamista(lista, 2);
      }

      // Testi 6.
      else if (testinumero == 6) {
         // Adding null.
         // false
         // [ ]
         testaaLisaamista(lista, null);
      }

      /*
       * Hakutestit.
       *
       */

      // Testi 7.
      else if (testinumero == 7) {
         lista.lisaa(3);
         lista.lisaa(2);
         lista.lisaa(1);
         // [ 1 2 3 ]
         // Searching 1.
         // 1
         testaaHakua(lista, 1);
      }

      // Testi 8.
      else if (testinumero == 8) {
         lista.lisaa(3);
         lista.lisaa(2);
         lista.lisaa(1);
         // [ 1 2 3 ]
         // Searching 2.
         // 2
         testaaHakua(lista, 2);
      }

      // Testi 9.
      else if (testinumero == 9) {
         lista.lisaa(3);
         lista.lisaa(2);
         lista.lisaa(1);
         // [ 1 2 3 ]
         // Searching 3.
         // 3
         testaaHakua(lista, 3);
      }

      // Testi 10.
      else if (testinumero == 10) {
         lista.lisaa(3);
         lista.lisaa(2);
         lista.lisaa(1);
         // [ 1 2 3 ]
         // Searching 4.
         // null
         testaaHakua(lista, 4);
      }

      // Testi 11.
      else if (testinumero == 11) {
         lista.lisaa("abc");
         // [ abc ]
         // Searching null.
         // null
         testaaHakua(lista, null);
      }

      // Testi 12.
      else if (testinumero == 12) {
         // [ ]
         // Searching X.
         // null
         testaaHakua(lista, "X");
      }

      /*
       * Poistotestit.
       *
       */

      // Testi 13.
      else if (testinumero == 13) {
         lista.lisaa("enten");
         lista.lisaa("tenten");
         lista.lisaa("viipula");
         // Removing tenten.
         // tenten
         // [ enten viipula ]
         testaaPoistoa(lista, "tenten");
      }

      // Testi 14.
      else if (testinumero == 14) {
         lista.lisaa("enten");
         lista.lisaa("tenten");
         lista.lisaa("viipula");
         // Removing enten.
         // enten
         // [ tenten viipula ]
         testaaPoistoa(lista, "enten");
      }

      // Testi 15.
      else if (testinumero == 15) {
         lista.lisaa("enten");
         lista.lisaa("tenten");
         lista.lisaa("viipula");
         // Removing viipula.
         // viipula
         // [ enten tenten ]
         testaaPoistoa(lista, "viipula");
      }

      // Testi 16.
      else if (testinumero == 16) {
         lista.lisaa("enten");
         lista.lisaa("tenten");
         lista.lisaa("viipula");
         // Removing venten.
         // null
         // [ enten tenten viipula ]
         testaaPoistoa(lista, "venten");
      }

      // Testi 17.
      else if (testinumero == 17) {
         lista.lisaa("enten");
         lista.poista("enten");
         // Removing null.
         // null
         // [ ]
         testaaPoistoa(lista, null);
      }

      // Testi 18.
      else if (testinumero == 18) {
         lista.lisaa("enten");
         lista.poista("enten");
         // Removing enten.
         // null
         // [ ]
         testaaPoistoa(lista, "enten");
      }

      // Tuntematon testi.
      else
         System.out.println("Erroneus test number.");
   }
}
