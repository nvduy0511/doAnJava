package com.example.covidapp.model.thongtincanhiem;

public class TongQuanCaNhiem {
    protected long death;
    protected long treating;
    protected long cases;
    protected long recovered;

    public TongQuanCaNhiem(long death, long treating, long cases, long recovered) {
        this.death = death;
        this.treating = treating;
        this.cases = cases;
        this.recovered = recovered;
    }

    public TongQuanCaNhiem() {
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
}
