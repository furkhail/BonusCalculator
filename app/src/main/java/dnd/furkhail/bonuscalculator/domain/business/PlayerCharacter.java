package dnd.furkhail.bonuscalculator.domain.business;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class PlayerCharacter {

    private static final String TAG = "PlayerCharacter";

    private String name;
    private String size;
    private String race;
    private List<Ability> abilities;
    private List<Attack> attacks;
    private List<Stat> stats;
    private List<Status> statuses;

    public PlayerCharacter(String name, String size, String race, List<Ability> abilities, List<Attack> attacks, List<Stat> stats, List<Status> statuses) {
        this.name = name;
        this.size = size;
        this.race = race;
        this.abilities = abilities;
        this.attacks = attacks;
        this.stats = stats;
        this.statuses = statuses;
    }

    public PlayerCharacter(){
        this("","","",initAbilitiesPathfinder(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
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

    public ArrayList<Ability> getAbilities() {
        return (ArrayList<Ability>) abilities;
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

    private static List<Ability> initAbilitiesPathfinder(){
        List<Ability> list = new ArrayList<>();
        list.add(new Ability(NameFieldPathfinder.ABILITY_NAME_STRENGHT, 10));
        list.add(new Ability(NameFieldPathfinder.ABILITY_NAME_DEXTERITY, 10));
        list.add(new Ability(NameFieldPathfinder.ABILITY_NAME_CONSTITUTION, 10));
        list.add(new Ability(NameFieldPathfinder.ABILITY_NAME_INTELLIGENCE, 10));
        list.add(new Ability(NameFieldPathfinder.ABILITY_NAME_WISDOM, 10));
        list.add(new Ability(NameFieldPathfinder.ABILITY_NAME_CHARISMA, 10));
        Log.d(TAG, "initAbilitiesPathfinder() called: "+list);
        return list;
    }

    @Override
    public String toString() {
        return "PlayerCharacter{" +
                "name='" + name + '\'' +
                ", abilities=" + abilities +
                '}';
    }
}
