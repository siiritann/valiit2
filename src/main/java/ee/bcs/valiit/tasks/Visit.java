package ee.bcs.valiit.tasks;

public class Visit /*implements Comparable<Visit> */ {
    // klassi muutujad
    private String date;
    private int visits;

    public Visit(String date, int visits) {
        this.date = date;
        this.visits = visits;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

/*    public int compareTo(Visit v) {
        if (visits == v.visits) {
            return 0;
        } else if (visits > v.visits) {
            return 1;
        } else return -1;
    }*/
}
