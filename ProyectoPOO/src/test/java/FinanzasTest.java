import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class FinanzasTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void testAgregarDineroConCantidadPositiva() {
        String input = "50\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Finanzas.anadirDinero();

        assertEquals("Fondos totales: $50.0\n", outContent.toString());
    }

    @Test
    public void testAgregarDineroConCantidadNegativa() {
        String input = "-50\n50\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Finanzas.anadirDinero();

        assertEquals("No puedes añadir una cantidad negativa o nula\nFondos totales: $50.0\n", outContent.toString());
    }

    @Test
    public void testRestarDineroConCantidadPositiva() {
        String input = "50\n2\n50\nProducto\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Finanzas.restarDinero();

        assertEquals("Categorías disponibles:\n1. Alimentación\n2. Transporte\n3. Entretenimiento\n4. Educación\n5. Otras\nSelecciona la categoría en la que gastaste: Gasto registrado en la categoría: Transporte\nFondos totales: $0.0\n", outContent.toString());
    }

    @Test
    public void testRestarDineroConCantidadNegativa() {
        String input = "-50\n50\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Finanzas.restarDinero();

        assertEquals("No puedes restar una cantidad negativa o nula.\n", outContent.toString());
    }
}
