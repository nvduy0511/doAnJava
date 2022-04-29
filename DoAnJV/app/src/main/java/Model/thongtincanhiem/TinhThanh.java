package Model.thongtincanhiem;

public class TinhThanh {
    private String name;
    private long death;
    private long treating;
    private long cases;
    private long recovered;
    private long casesToday;

    public TinhThanh(String name, long death, long treating, long cases, long recovered, long casesToday) {
        this.name = name;
        this.death = death;
        this.treating = treating;
        this.cases = cases;
        this.recovered = recovered;
        this.casesToday = casesToday;
    }

    public TinhThanh() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDeath() {
        return death;
    }

    public void setDeath(long death) {
        this.death = death;
    }

    public long getTreating() {
        return treating;
    }

    public void setTreating(long treating) {
        this.treating = treating;
    }

    public long getCases() {
        return cases;
    }

    public void setCases(long cases) {
        this.cases = cases;
    }

    public long getRecovered() {
        return recovered;
    }

    public void setRecovered(long recovered) {
        this.recovered = recovered;
    }

    public long getCasesToday() {
        return casesToday;
    }

    public void setCasesToday(long casesToday) {
        this.casesToday = casesToday;
    }
}
