package Model.thongtincanhiem;

public class TongQuatTaiVietNam extends TongQuanCaNhiem{
    private String date;
    private long avgCases7day;
    private long avgRecovered7day;
    private long avgDeath7day;

    public TongQuatTaiVietNam(long death, long treating, long cases, long recovered, String date, long avgCases7day, long avgRecovered7day, long avgDeath7day) {
        super(death, treating, cases, recovered);
        this.date = date;
        this.avgCases7day = avgCases7day;
        this.avgRecovered7day = avgRecovered7day;
        this.avgDeath7day = avgDeath7day;
    }

    public TongQuatTaiVietNam(String date, long avgCases7day, long avgRecovered7day, long avgDeath7day) {
        this.date = date;
        this.avgCases7day = avgCases7day;
        this.avgRecovered7day = avgRecovered7day;
        this.avgDeath7day = avgDeath7day;
    }

    public TongQuatTaiVietNam() {
        super();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getAvgCases7day() {
        return avgCases7day;
    }

    public void setAvgCases7day(long avgCases7day) {
        this.avgCases7day = avgCases7day;
    }

    public long getAvgRecovered7day() {
        return avgRecovered7day;
    }

    public void setAvgRecovered7day(long avgRecovered7day) {
        this.avgRecovered7day = avgRecovered7day;
    }

    public long getAvgDeath7day() {
        return avgDeath7day;
    }

    public void setAvgDeath7day(long avgDeath7day) {
        this.avgDeath7day = avgDeath7day;
    }
}
