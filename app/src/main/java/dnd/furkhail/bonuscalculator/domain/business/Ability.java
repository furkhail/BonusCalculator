package dnd.furkhail.bonuscalculator.domain.business;

import java.util.ArrayList;
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

    public Ability(String name, int amount) {
        this(name, amount, new ArrayList<>());
    }

    public Ability(String name) {
        this(name, 10);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseAmount() {
        return amount;
    }

    public boolean setAmount(int amount) {
        this.amount = amount;
        return isZero();
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }

    public int getAmount() {
        int tempAmount = amount;
        for (Bonus b : bonuses) {
            if (name.equals(b.getBonusField())) {
                tempAmount += b.getAmmount();
            }
        }
        return tempAmount;
    }

    public boolean addBonus(Bonus b){
        bonuses.add(b);
        return isZero();
    }

    private boolean isZero(){
        return getAmount() <= 0;
    }

    @Override
    public String toString() {
        return "Ability{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
