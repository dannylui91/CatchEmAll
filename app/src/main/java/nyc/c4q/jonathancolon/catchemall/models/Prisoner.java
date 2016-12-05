package nyc.c4q.jonathancolon.catchemall.models;

import java.util.Calendar;

/**
 * Created by dannylui on 12/5/16.
 */

public class Prisoner {
    private Long _id;
    private String name;
    private Long startTime;
    public Prisoner() {
        this.name = "unknown";
        this.startTime = Calendar.getInstance().getTimeInMillis();
    }

    public Prisoner(String name, Long startTime) {
        this.name = name;
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String toString() {
        return name + " " + startTime;
    }
}
