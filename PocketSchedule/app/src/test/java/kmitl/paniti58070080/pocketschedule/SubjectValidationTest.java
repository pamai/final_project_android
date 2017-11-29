package kmitl.paniti58070080.pocketschedule;


import org.junit.Test;


import kmitl.paniti58070080.pocketschedule.exception.EmptySubjectException;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;
import kmitl.paniti58070080.pocketschedule.validation.EmptySubjectValidation;

public class SubjectValidationTest {

    @Test(expected = EmptySubjectException.class)
    public void EmptySubjectMustThrowError() throws EmptySubjectException {
        ScheduleInfo scheduleInfo = new ScheduleInfo("Monday", "09.00", "11.00", "M04", "", "key");
        EmptySubjectValidation emptySubjectValidation = new EmptySubjectValidation();
        emptySubjectValidation.validate(scheduleInfo);
    }
}
