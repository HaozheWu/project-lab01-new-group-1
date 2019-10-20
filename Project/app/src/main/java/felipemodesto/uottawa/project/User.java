package felipemodesto.uottawa.project;

public class User {
    String Username;
    String Status ;
    String Gender;

    public User(String Gender, String Username, String status) {
        this.Gender=Gender;
        this.Username = Username;
        this.Status = status;
    }
    public User(){}
    public String getUsername() {
        return Username;
    }

   public void setUsername(String username){
        this.Username=username;
   }
   public String getStatus() {
        return Status;
    }

    public void setSatus(String status){
        this.Status=status;
    }

    public void setGender(String Gender){
        this.Gender=Gender;
    }

    public String getGender(){
        return this.Gender;
    }


}


