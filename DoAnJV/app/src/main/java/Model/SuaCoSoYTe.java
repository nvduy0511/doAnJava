package Model;

import java.io.Serializable;

public class SuaCoSoYTe implements Serializable {
    private String name;
    private String diachi;

    public SuaCoSoYTe(String name, String diachi) {
        this.name = name;
        this.diachi = diachi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
