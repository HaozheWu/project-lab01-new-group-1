package felipemodesto.uottawa.project;

public class User {
    String UserEmail;
    String Passward;
    String Username;
    String Emploee;

    public User(String UserEmail, String Passward, String Username, String Emploee) {
        this.UserEmail = UserEmail;
        this.Passward = Passward;
        this.Username = Username;
        this.Emploee = Emploee;
    }

    public User() {
    }

    ;

    public String getUserEmail() {
        return UserEmail;
    }
    public void setUsername(String username){
        this.Username=username;
    }
    public void setEmploee(String Emploee){
        this.Emploee=Emploee;
    }
    public String getPassward() {
        return Passward;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmploee() {
        return Emploee;
    }
}


