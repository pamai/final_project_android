package kmitl.paniti58070080.pocketschedule.validation;

import kmitl.paniti58070080.pocketschedule.exception.EmptyLocationException;
import kmitl.paniti58070080.pocketschedule.exception.EmptySubjectException;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;


public class EmptyLocationValidation implements ScheduleValidation {
    @Override
    public void validate(ScheduleInfo data) throws EmptyLocationException {
        if (data.getLocation().isEmpty()){
            throw new EmptyLocationException("Are you study at Pluto? Don't be shy to put it in!!");
        }
    }
}
