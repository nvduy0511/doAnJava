package Model.thongtincanhiem;

import java.util.List;

public class ThongTinCaNhiem {
    private TongQuanDichBenh total;
    private TongQuanDichBenh today;
    private List<TongQuatTaiVietNam> overview;
    private List<TinhThanh> locations;

    public ThongTinCaNhiem(TongQuanDichBenh total, TongQuanDichBenh today, List<TongQuatTaiVietNam> overview, List<TinhThanh> locations) {
        this.total = total;
        this.today = today;
        this.overview = overview;
        this.locations = locations;
    }

    public ThongTinCaNhiem() {
    }

    public TongQuanDichBenh getTotal() {
        return total;
    }

    public void setTotal(TongQuanDichBenh total) {
        this.total = total;
    }

    public TongQuanDichBenh getToday() {
        return today;
    }

    public void setToday(TongQuanDichBenh today) {
        this.today = today;
    }

    public List<TongQuatTaiVietNam> getOverview() {
        return overview;
    }

    public void setOverview(List<TongQuatTaiVietNam> overview) {
        this.overview = overview;
    }

    public List<TinhThanh> getLocations() {
        return locations;
    }

    public void setLocations(List<TinhThanh> locations) {
        this.locations = locations;
    }
}
