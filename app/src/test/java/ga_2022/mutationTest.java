package ga_2022;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class mutationTest {
    @Test
    void testPunktMutation() {
        App classUnderTest = new App();
        Faltung f0 = classUnderTest.generateRandomFaltung();
        Faltung f1 = classUnderTest.punktMutation(classUnderTest.generateRandomFaltung());
        assertEquals(f0.size(), f1.size());
    }
    @Test
    void testOnePointCrossover() {
        App classUnderTest = new App();
        Faltung f0 = classUnderTest.generateRandomFaltung();
        Faltung f1 = classUnderTest.generateRandomFaltung();
        Faltung f2 = classUnderTest.onePointCrossover(f0,f1);
        assertEquals(f0.size(), f2.size());
    }
}
