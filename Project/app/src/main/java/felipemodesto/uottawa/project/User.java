package felipemodesto.uottawa.project;

public class User {
    String UserEmail;
    String Passward;
    String Username;
    String Status ;

    public User(String UserEmail, String Passward, String Username, String status) {
        this.UserEmail = UserEmail;
        this.Passward = Passward;
        this.Username = Username;
        this.Status = status;
    }


    public String getUserEmail() {
        return UserEmail;
    }

    public void setUsername(String username){
        this.Username=username;
    }

    public void setSatus(String status){
        this.Status=status;
    }


    public String getPassward() {
        return Passward;
    }

    public String getUsername() {
        return Username;
    }

    public String getStatus() {
        return Status;
    }
}


