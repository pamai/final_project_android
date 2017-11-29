package kmitl.paniti58070080.pocketschedule;


import org.junit.Test;

import kmitl.paniti58070080.pocketschedule.model.ScheduleInfo;

import static junit.framework.Assert.assertEquals;

public class GetScheduleTest {

    @Test
    public void getScheduleInfo(){
        ScheduleInfo scheduleInfo = new ScheduleInfo("Monday", "09.00", "11.00", "M04", "Security", "key");
        assertEquals("Monday", scheduleInfo.getDay());
        assertEquals("09.00", scheduleInfo.getTime_start());
        assertEquals("11.00", scheduleInfo.getTime_end());
        assertEquals("M04", scheduleInfo.getLocation());
        assertEquals("Security", scheduleInfo.getSubject());
        assertEquals("key", scheduleInfo.getKey());
    }
}
