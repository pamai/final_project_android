package kmitl.paniti58070080.pocketschedule.validation;


import kmitl.paniti58070080.pocketschedule.exception.EmptyLocationException;
import kmitl.paniti58070080.pocketschedule.exception.EmptySubjectException;
import kmitl.paniti58070080.pocketschedule.exception.InvalidClassTimeException;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;

public interface ScheduleValidation {
    void validate(ScheduleInfo data) throws EmptySubjectException, EmptyLocationException, InvalidClassTimeException;
}
