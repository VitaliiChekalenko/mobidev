package vitaliichekalenko.javafxmobidevtestapp.model;

public class User {
    private String name;
    private int xp;

    public User(String name) {
        this.name = name;
        this.xp = 0;
    }

    public String getName() { return name; }
    public int getXp() { return xp; }
    public void addXp(int amount) { xp += amount; }
}
