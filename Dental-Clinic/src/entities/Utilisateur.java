package entities;

public class Utilisateur extends Personne {

    private Integer id;
    private String login;
    private String motDePasse;
    // other fields specific to Utilisateur, getters, setters


    // other fields and methods

    // Constructor
    public Utilisateur(Integer id) {
        this.id = id;
    }

    // Getter and Setter for id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
