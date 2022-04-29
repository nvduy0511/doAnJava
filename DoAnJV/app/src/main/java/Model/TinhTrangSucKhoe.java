package Model;

public class TinhTrangSucKhoe{

    private String date;
    private String name;
    private String time;

    public TinhTrangSucKhoe()
    {

    }

    public TinhTrangSucKhoe(String date, String name, String time)
    {
        this.date = date;
        this.name = name;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
