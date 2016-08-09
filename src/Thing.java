
public class Thing {
    private String id;
    private String name;
    private String desc;
    private String zone;
    private int timestamp;

    public Thing(String id, int timestamp) {
        this.id = id;
        this.name = id;
        this.desc = "";
        this.zone = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "Thing [id=" + id + ", name=" + name + ", desc=" + desc + ", zone=" + zone + "]";
    }

}