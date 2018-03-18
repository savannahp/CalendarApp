package teamproject.savannahpyle.calendarapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by paulland on 2/28/18.
 *
 * Not all of the tests in here will compile because we have not created the
 * associated classes. Some of the logic of the tests is commented out because
 * it is just the idea of the test, and not actually implemented as of yet.
 *
 * @author Paul and Savannah
 */

public class CalendarAppTests {

    @Test
    public void UserIsNotNull() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        assertNotNull(user);
    }

    @Test
    public void TaskIsDeletedAfterThreeDaysOfBeingCompleted() {
        Task task = new Task();
        task.setComplete(true);

        task.setNumDaysCompleted(4);

        task.update();

        assertEquals(null, task);
    }

    @Test
    public void EventDateIsCurrentDateOrGreater() {
//        Date date = new Date(/*currentDate*/);
//
//        Task task = new Task();
//
//        task.dueDate = date;
//
//        assertEquals(task.dueDate, currentDateOrGreaterThanTheCurrentDate);
    }

}
