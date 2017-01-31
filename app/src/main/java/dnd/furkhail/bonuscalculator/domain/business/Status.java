package dnd.furkhail.bonuscalculator.domain.business;

import java.util.List;

public class Status {
    private String name;
    private boolean active;
    private String duration;
    private List<Bonus> bonuses;

    public Status(String name, boolean active, String duration, List<Bonus> bonuses) {
        this.name = name;
        this.active = active;
        this.duration = duration;
        this.bonuses = bonuses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }
}
