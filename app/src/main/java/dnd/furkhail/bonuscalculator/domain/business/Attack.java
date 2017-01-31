package dnd.furkhail.bonuscalculator.domain.business;

import java.util.List;

public class Attack {

    private String name;
    private int base;
    private List<Bonus> bonuses;
    private String ability;
    private int range;
    private String crit;
    private String type;
    private String damage;

    public Attack(String name, int base, List<Bonus> bonuses, String ability, int range, String crit, String type, String damage) {
        this.name = name;
        this.base = base;
        this.bonuses = bonuses;
        this.ability = ability;
        this.range = range;
        this.crit = crit;
        this.type = type;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getCrit() {
        return crit;
    }

    public void setCrit(String crit) {
        this.crit = crit;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }
}
