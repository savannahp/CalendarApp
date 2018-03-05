package teamproject.savannahpyle.calendarapp;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by paulland on 2/28/18.
 *
 * This is the first test class to test our test classes
 * so we can test our test classes while we test our classes.
 *
 * @author Paul Land
 */

public class TeamActivityTestClass {

    @Test
    public void addTest() {

        assertEquals(4, 2 + 2);

    }

    @Test
    public void boolTest() {
        assertFalse(false);
        assertTrue(true);
    }

    @Test
    public void doesFalseEqualTrue() {
        assertFalse(true);
    }

    @Test
    public void doesTrueEqualFalse() {
        assertTrue(false);
    }

    @Test
    public void doesTwoPlusThreeEqualFour() {
        assertEquals(4, 1 + 2);
    }
}
