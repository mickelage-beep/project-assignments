package se.jensen.mikael.dicegame;


public class Player {
    private String firstName;
    private String lastName;
    private int score = 0;

    public Player(){
    }

    public void setFirstName(String firstName) throws IllegalArgumentException {
        if (firstName == null || firstName.equals("")){
            throw new IllegalArgumentException("Förnamn får inte vara tomt.");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws IllegalArgumentException {
        if (lastName == null || firstName.equals("")) {
            throw new IllegalArgumentException("Efternamn får inte vara tomt. Försök igen");
        }
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
    public void resetScore() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public String getFullName(){
        return "Namn: " + firstName + " " + lastName;

    }



}
