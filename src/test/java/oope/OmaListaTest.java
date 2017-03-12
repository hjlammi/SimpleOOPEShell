package oope;
import static org.junit.Assert.*;

import org.junit.Test;

public class OmaListaTest {

	@Test
	public void tyhja() {
		OmaLista lista = new OmaLista();
		assertEquals(true, lista.onkoTyhja());
	}

	@Test
	public void yksi() {
		OmaLista lista = new OmaLista();
		String alkio = "abc";
		lista.lisaaAlkuun(alkio);
		assertEquals(false, lista.onkoTyhja());
	}
}
