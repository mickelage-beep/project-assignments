package se.jensen.mikael.dicegame;


public class Player {
    private String firstName;
    private String lastName;
    private int score = 0;

    public Player(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void addToScore(int points){
        this.score += points;
    }

    public int getScore() {
        return score;
    }

    public String getFullName(){
        return "Namn: " + firstName + " " + lastName;

    }



}
