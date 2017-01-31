package dnd.furkhail.bonuscalculator.domain.business;

import java.util.List;

public class Ability {

    private String name;
    private int amount;
    private List<Bonus> bonuses;

    public Ability(String name, int amount, List<Bonus> bonuses) {
        this.name = name;
        this.amount = amount;
        this.bonuses = bonuses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }
}
