package ch.zhaw.iwi.devops.demo;

public class Fahrrad {
    private int id;
    private String modell;

    public Fahrrad() {}

    public Fahrrad(int id, String modell) {
        this.id = id;
        this.modell = modell;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }
}
