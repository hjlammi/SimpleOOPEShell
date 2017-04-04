// Hierarkian luokkien oletetaan olevan oope2017ht.tiedot-pakkauksessa.
import oope2017ht.tiedot.*;

// Otetaan käyttöön In-luokka.
import apulaiset.*;

// Otetaan käyttöön lista-pakkaus.
import fi.uta.csjola.oope.lista.*;

/*
 * Harjoitustyö.
 *
 * Olio-ohjelmoinnin perusteet, kevät 2017, Jorma Laurikkala, jorma.laurikkala@uta.fi.
 *
 * Hakemistoa testaava luokka, jota EI OLE SYYTÄ MUUTTAA MILLÄÄN TAVALLA,
 * koska WETO käyttää aina tämän luokan alkuperäistä versiota.
 *
 * Testit ovat main-metodissa. WETO käy läpi testit alla annetussa järjestyksessä.
 *
 * Aja luokka hakemistossa, jonka välittöminä alihakemistoina ovat oope2017ht-
 * apulaiset- ja lista-pakkausten hakemistot.
 *
 */

public class HakemistoTesti {

   /* Apumetodi listan tulostamiseen.
    */
   private static void tulosta(LinkitettyLista lista) {
      if (lista != null) {
         System.out.print("[ ");
         for (int i = 0; i < lista.koko(); i++) {
            System.out.print(lista.alkio(i));
            if (i < lista.koko() - 1)
               System.out.print(", ");
         }
         System.out.println(" ]");
      }
   }

   // Pitkän pitkä testirakenne.
   public static void main(String[] args) {
      // Vakiot testien pienimmälle ja suuremmalle testinumerolle.
      final int MINNRO = 25;
      final int MAXNRO = 30;

      // Luetaan testinumero.
      System.out.println("Enter test number (" + MINNRO + " - " + MAXNRO + "):");
      int testinumero = In.readInt();

      // Testi 25.
      if (testinumero == 25) {
         Hakemisto hakemisto = new Hakemisto(new StringBuilder("cats"), null);
         Tiedosto tiedosto = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
         // true
         // cats/ 1
         // [ grumpy_cat.jpeg 335932 ]
         System.out.println(hakemisto.lisaa(tiedosto));
         System.out.println(hakemisto);
         tulosta(hakemisto.sisalto());
      }

      // Testi 26.
      else if (testinumero == 26) {
         Hakemisto hakemisto = new Hakemisto(new StringBuilder("cats"), null);
         Tiedosto tiedosto1 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
         Tiedosto tiedosto2 = new Tiedosto(new StringBuilder("cannot_be_unseen.jpeg"), 29614);
         Tiedosto tiedosto3 = new Tiedosto(new StringBuilder("ceiling_cat.gif"), 3677);
         Tiedosto tiedosto4 = new Tiedosto(new StringBuilder("dangerous_kitten.jpg"), 13432);
         // true
         // true
         // true
         // true
         // false
         // false
         // cats/ 4
         // [ cannot_be_unseen.jpeg 29614, ceiling_cat.gif 3677, dangerous_kitten.jpg 13432, grumpy_cat.jpeg 335932 ]
         System.out.println(hakemisto.lisaa(tiedosto1));
         System.out.println(hakemisto.lisaa(tiedosto2));
         System.out.println(hakemisto.lisaa(tiedosto3));
         System.out.println(hakemisto.lisaa(tiedosto4));
         System.out.println(hakemisto.lisaa(tiedosto4));  // Yritetään lisätä sama tieto.
         System.out.println(hakemisto.lisaa(null));
         System.out.println(hakemisto);
         tulosta(hakemisto.sisalto());
      }

      // Testi 27.
      else if (testinumero == 27) {
         Hakemisto hakemisto = new Hakemisto(new StringBuilder("cats"), null);
         Tiedosto tiedosto1 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
         Tiedosto tiedosto2 = new Tiedosto(new StringBuilder("cannot_be_unseen.jpeg"), 29614);
         Tiedosto tiedosto3 = new Tiedosto(new StringBuilder("ceiling_cat.gif"), 3677);
         Tiedosto tiedosto4 = new Tiedosto(new StringBuilder("dangerous_kitten.jpg"), 13432);
         // true
         // true
         // true
         // true
         // cats/ 4
         // [ cannot_be_unseen.jpeg 29614, ceiling_cat.gif 3677, dangerous_kitten.jpg 13432, grumpy_cat.jpeg 335932 ]
         // grumpy_cat.jpeg 335932
         // cannot_be_unseen.jpeg 29614
         // ceiling_cat.gif 3677
         // dangerous_kitten.jpg 13432
         // null
         // null
         System.out.println(hakemisto.lisaa(tiedosto1));
         System.out.println(hakemisto.lisaa(tiedosto2));
         System.out.println(hakemisto.lisaa(tiedosto3));
         System.out.println(hakemisto.lisaa(tiedosto4));
         System.out.println(hakemisto);
         tulosta(hakemisto.sisalto());
         System.out.println(hakemisto.hae("grumpy_cat.jpeg"));
         System.out.println(hakemisto.hae("cannot_be_unseen.jpeg"));
         System.out.println(hakemisto.hae("ceiling_cat.gif"));
         System.out.println(hakemisto.hae("dangerous_kitten.jpg"));
         System.out.println(hakemisto.hae("worlds_ugliest_dog.jpg"));
         System.out.println(hakemisto.hae(null));
      }

      // Testi 28.
      else if (testinumero == 28) {
         Hakemisto hakemisto = new Hakemisto(new StringBuilder("cats"), null);
         Tiedosto tiedosto1 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
         Tiedosto tiedosto2 = new Tiedosto(new StringBuilder("cannot_be_unseen.jpeg"), 29614);
         Tiedosto tiedosto3 = new Tiedosto(new StringBuilder("ceiling_cat.gif"), 3677);
         Tiedosto tiedosto4 = new Tiedosto(new StringBuilder("dangerous_kitten.jpg"), 13432);

         // true
         // true
         // true
         // true
         // cats/ 4
         // [ cannot_be_unseen.jpeg 29614, ceiling_cat.gif 3677, dangerous_kitten.jpg 13432, grumpy_cat.jpeg 335932 ]
         System.out.println(hakemisto.lisaa(tiedosto1));
         System.out.println(hakemisto.lisaa(tiedosto2));
         System.out.println(hakemisto.lisaa(tiedosto3));
         System.out.println(hakemisto.lisaa(tiedosto4));
         System.out.println(hakemisto);
         tulosta(hakemisto.sisalto());
         
         // null
         // cats/ 4
         // [ cannot_be_unseen.jpeg 29614, ceiling_cat.gif 3677, dangerous_kitten.jpg 13432, grumpy_cat.jpeg 335932 ]
         System.out.println(hakemisto.poista("worlds_ugliest_dog.jpg"));
         System.out.println(hakemisto);
         tulosta(hakemisto.sisalto());

         // null
         // cats/ 4
         // [ cannot_be_unseen.jpeg 29614, ceiling_cat.gif 3677, dangerous_kitten.jpg 13432, grumpy_cat.jpeg 335932 ]
         System.out.println(hakemisto.poista(null));
         System.out.println(hakemisto);
         tulosta(hakemisto.sisalto());

         // cannot_be_unseen.jpeg 29614
         // cats/ 3
         // [ ceiling_cat.gif 3677, dangerous_kitten.jpg 13432, grumpy_cat.jpeg 335932 ]
         System.out.println(hakemisto.poista("cannot_be_unseen.jpeg"));
         System.out.println(hakemisto);
         tulosta(hakemisto.sisalto());

         // grumpy_cat.jpeg 335932
         // cats/ 2
         // [ ceiling_cat.gif 3677, dangerous_kitten.jpg 13432 ]
         System.out.println(hakemisto.poista("grumpy_cat.jpeg"));
         System.out.println(hakemisto);
         tulosta(hakemisto.sisalto());

         // ceiling_cat.gif 3677
         // cats/ 1
         // [ dangerous_kitten.jpg 13432 ]
         System.out.println(hakemisto.poista("ceiling_cat.gif"));
         System.out.println(hakemisto);
         tulosta(hakemisto.sisalto());

         // dangerous_kitten.jpg 13432
         // cats/ 0
         // [  ]
         System.out.println(hakemisto.poista("dangerous_kitten.jpg"));
         System.out.println(hakemisto);
         tulosta(hakemisto.sisalto());

         // null
         // cats/ 0
         // [  ]
         System.out.println(hakemisto.poista("dangerous_kitten.jpg"));
         System.out.println(hakemisto);
         tulosta(hakemisto.sisalto());
     }

      // Testi 29.
      else if (testinumero == 29) {
         // Luodaan hakemistot. Alihakemistoista asetetaan viitteet ylihakemistoon
         // ja alihakemistot lisätään ylihakemistoon.
         Hakemisto ylihakemisto = new Hakemisto(new StringBuilder("pics"), null);
         Hakemisto alihakemisto1 = new Hakemisto(new StringBuilder("cats"), ylihakemisto);
         Hakemisto alihakemisto2 = new Hakemisto(new StringBuilder("dogs"), ylihakemisto);
         System.out.println(ylihakemisto.lisaa(alihakemisto1));
         System.out.println(ylihakemisto.lisaa(alihakemisto2));

         // true
         // true
         // pics/ 2
         // cats/ 0
         // dogs/ 0
         // [ cats/ 0, dogs/ 0 ]
         System.out.println(ylihakemisto);
         System.out.println(alihakemisto1);
         System.out.println(alihakemisto2);
         tulosta(ylihakemisto.sisalto());
      }

      // Testi 30.
      else if (testinumero == 30) {
         // Luodaan hakemistot. Alihakemistoista asetetaan viitteet ylihakemistoon
         // ja alihakemistot lisätään ylihakemistoon.
         Hakemisto ylihakemisto = new Hakemisto(new StringBuilder("pics"), null);
         Hakemisto alihakemisto1 = new Hakemisto(new StringBuilder("cats"), ylihakemisto);
         Hakemisto alihakemisto2 = new Hakemisto(new StringBuilder("dogs"), ylihakemisto);
         ylihakemisto.lisaa(alihakemisto1);
         ylihakemisto.lisaa(alihakemisto2);

         // Lisätään tiedostoja alihakemistohin.
         Tiedosto tiedosto1 = new Tiedosto(new StringBuilder("grumpy_cat.jpeg"), 335932);
         Tiedosto tiedosto2 = new Tiedosto(new StringBuilder("cannot_be_unseen.jpeg"), 29614);
         Tiedosto tiedosto3 = new Tiedosto(new StringBuilder("ceiling_cat.gif"), 3677);
         Tiedosto tiedosto4 = new Tiedosto(new StringBuilder("dangerous_kitten.jpg"), 13432);
         Tiedosto tiedosto5 = new Tiedosto(new StringBuilder("worlds_ugliest_dog.jpg"), 118088);
         alihakemisto1.lisaa(tiedosto1);
         alihakemisto1.lisaa(tiedosto2);
         alihakemisto1.lisaa(tiedosto3);
         alihakemisto1.lisaa(tiedosto4);
         alihakemisto2.lisaa(tiedosto5);

         // pics/ 2
         // cats/ 4
         // dogs/ 1
         // [ cats/ 4, dogs/ 1 ]
         // [ cannot_be_unseen.jpeg 29614, ceiling_cat.gif 3677, dangerous_kitten.jpg 13432, grumpy_cat.jpeg 335932 ]
         // [ worlds_ugliest_dog.jpg 118088 ]
         System.out.println(ylihakemisto);
         System.out.println(alihakemisto1);
         System.out.println(alihakemisto2);
         tulosta(ylihakemisto.sisalto());
         tulosta(alihakemisto1.sisalto());
         tulosta(alihakemisto2.sisalto());
      }
   }
}
