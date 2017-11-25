package kmitl.paniti58070080.pocketschedule.validation;

import kmitl.paniti58070080.pocketschedule.exception.EmptyLocationException;
import kmitl.paniti58070080.pocketschedule.exception.EmptySubjectException;
import kmitl.paniti58070080.pocketschedule.exception.InvalidClassTimeException;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;

public class TimeLeapValidation implements ScheduleValidation {
    @Override
    public void validate(ScheduleInfo data) throws EmptySubjectException, EmptyLocationException, InvalidClassTimeException {
        if (data.getTime_start().compareTo(data.getTime_end()) >= 0){
            throw new InvalidClassTimeException("Back to the future?");
        }
    }
}
