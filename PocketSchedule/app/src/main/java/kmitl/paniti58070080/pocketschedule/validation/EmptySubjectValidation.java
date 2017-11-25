package kmitl.paniti58070080.pocketschedule.validation;

import kmitl.paniti58070080.pocketschedule.exception.EmptySubjectException;
import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;


public class EmptySubjectValidation implements ScheduleValidation {
    @Override
    public void validate(ScheduleInfo data) throws EmptySubjectException {
        if (data.getSubject().isEmpty()){
            throw new EmptySubjectException("empty subject");
        }
    }
}
