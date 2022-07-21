package BigInteger;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.DisableOnDebug;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;

import java.sql.Time;
import java.util.Random;

import static org.junit.Assert.assertEquals;


public class BigIntegerTest {
    @Test
    public void testInit() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int rand = random.nextInt(-100000000, 100000000);
            BigInteger bigInteger = new BigInteger(20, rand);
            assertEquals(rand, bigInteger.toInteger());
        }
        int big = 2147483647;
        BigInteger bigInteger = new BigInteger(20, big);
        assertEquals(big, bigInteger.toInteger());
        big = -2147483648;
        bigInteger = new BigInteger(20, big);
        assertEquals(big, bigInteger.toInteger());
    }

    @Test
    public void testAdd() {
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int rand1 = random.nextInt(-100000000, 100000000);
            BigInteger bigInteger1 = new BigInteger(20, rand1);
            int rand2 = random.nextInt(-100000000, 100000000);
            BigInteger bigInteger2 = new BigInteger(20, rand2);
            assertEquals("a is " + rand1 + " b is " + rand2, rand1 + rand2, bigInteger1.add(bigInteger2).toInteger());
        }
    }
}
