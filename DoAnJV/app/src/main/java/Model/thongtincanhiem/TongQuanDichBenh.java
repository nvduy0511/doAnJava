package Model.thongtincanhiem;

public class TongQuanDichBenh {
    private TongQuanCaNhiem internal;
    private TongQuanCaNhiem world;

    public TongQuanDichBenh() {
    }

    public TongQuanDichBenh(TongQuanCaNhiem internal, TongQuanCaNhiem world) {
        this.internal = internal;
        this.world = world;
    }

    public TongQuanCaNhiem getInternal() {
        return internal;
    }

    public void setInternal(TongQuanCaNhiem internal) {
        this.internal = internal;
    }

    public TongQuanCaNhiem getWorld() {
        return world;
    }

    public void setWorld(TongQuanCaNhiem world) {
        this.world = world;
    }
}
