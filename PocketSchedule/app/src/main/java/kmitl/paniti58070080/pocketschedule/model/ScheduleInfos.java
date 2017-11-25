package kmitl.paniti58070080.pocketschedule.model;

import java.util.ArrayList;

public class ScheduleInfos {
    private ArrayList<ScheduleInfo> infos = new ArrayList<>();

    public void addInfo(ScheduleInfo info){
        infos.add(info);
    }

    public void removeInfo(int position){
        infos.remove(position);
    }

    public ArrayList<ScheduleInfo> getInfos(){
        return this.infos;
    }

}
