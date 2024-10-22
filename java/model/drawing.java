package model;

import java.util.ArrayList;
import java.util.List;

public class drawing {
    String name;
    boolean status;
    List<drawingVersion> versions = new ArrayList<>();

    public drawing(String name, boolean status, List<drawingVersion> versions) {
        this.name = name;
        this.status = status;
        this.versions = versions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<drawingVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<drawingVersion> versions) {
        this.versions = versions;
    }
}
