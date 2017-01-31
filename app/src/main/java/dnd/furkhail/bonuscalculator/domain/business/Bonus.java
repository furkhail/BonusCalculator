package dnd.furkhail.bonuscalculator.domain.business;

public class Bonus {
    private int ammount;
    private String bonusField;
    private String bonusType;

    public Bonus(int ammount, String bonusField, String bonusType) {
        this.ammount = ammount;
        this.bonusField = bonusField;
        this.bonusType = bonusType;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public String getBonusField() {
        return bonusField;
    }

    public void setBonusField(String bonusField) {
        this.bonusField = bonusField;
    }

    public String getBonusType() {
        return bonusType;
    }

    public void setBonusType(String bonusType) {
        this.bonusType = bonusType;
    }
}
