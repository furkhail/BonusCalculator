package dnd.furkhail.bonuscalculator.domain.business;

import java.util.List;

public class PlayableCharacter {

    private String name;
    private String size;
    private String race;
    private List<Ability> abilities;
    private List<Attack> attacks;
    private List<Stat> stats;
    private List<Status> statuses;

    public PlayableCharacter(String name, String size, String race, List<Ability> abilities, List<Attack> attacks, List<Stat> stats, List<Status> statuses) {
        this.name = name;
        this.size = size;
        this.race = race;
        this.abilities = abilities;
        this.attacks = attacks;
        this.stats = stats;
        this.statuses = statuses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Attack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Attack> attacks) {
        this.attacks = attacks;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}
