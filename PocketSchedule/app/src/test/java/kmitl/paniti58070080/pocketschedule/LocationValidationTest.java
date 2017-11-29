package kmitl.paniti58070080.pocketschedule;

import org.junit.Test;

import kmitl.paniti58070080.pocketschedule.exception.EmptyLocationException;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;
import kmitl.paniti58070080.pocketschedule.validation.EmptyLocationValidation;


public class LocationValidationTest {

    @Test(expected = EmptyLocationException.class)
    public void EmptyLocationMustThrowError() throws EmptyLocationException {
        ScheduleInfo scheduleInfo = new ScheduleInfo("Monday", "09.00", "11.00", "", "Security", "key");
        EmptyLocationValidation emptyLocationValidation = new EmptyLocationValidation();
        emptyLocationValidation.validate(scheduleInfo);
    }
}
