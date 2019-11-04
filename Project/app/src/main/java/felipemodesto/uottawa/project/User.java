package felipemodesto.uottawa.project;

public class User {
    String Id;
    String Email;
    String Passward;
    String Username;
    String Status ;
    String Gender;
    passwordEncryption a;

    public User(String Id,String Email,String Passward,String Gender, String Username, String Status) {
        a=new passwordEncryption();
        this.Id=Id;
        this.Email=Email;
        this.Passward=a.passwordEncryption(Passward);
        this.Gender=Gender;
        this.Username = Username;
        this.Status = status;
    }
    public User(){}
    public void setId(String Id){
        this.Id=Id;
    }
    public String getPassward(){
        return this.Passward;
    }
    public String getId(){
        return this.Id;
    }
    public String getUsername() {
        return Username;
    }
    public String getEmail(){
        return this.Email;
    }
    public void setEmail(String email){
        this.Email=email;
    }
    public void setPassward(String passward){
        this.Passward=a.passwordEncryption(passward);
    }

   public void setUsername(String username)
   {
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


