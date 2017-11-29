package kmitl.paniti58070080.pocketschedule;

import org.junit.Test;

import kmitl.paniti58070080.pocketschedule.exception.InvalidClassTimeException;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;
import kmitl.paniti58070080.pocketschedule.validation.TimeLeapValidation;


public class InvalidTimeValidation {

    @Test(expected = InvalidClassTimeException.class)
    public void WrongTimeMustThrowError() throws InvalidClassTimeException {
        ScheduleInfo scheduleInfo = new ScheduleInfo("Monday", "11.00", "09.00", "M04", "Security", "key");
        TimeLeapValidation timeLeapValidation = new TimeLeapValidation();
        timeLeapValidation.validate(scheduleInfo);
    }
}
