package felipemodesto.uottawa.project;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    String Id;
    String Email;
    String Password;
    String Username;
    String Status ;
    String Gender;


    public User(String Id,String Email,String Password,String Gender, String Username, String status) {

        this.Id=Id;
        this.Email=Email;
        this.Password=passwordEncryption(Password);
        this.Gender=Gender;
        this.Username = Username;
        this.Status = status;
    }
    public User(){}

    public void setPassword(String password) {
        Password = passwordEncryption(password);
    }

    public void setId(String Id){
        this.Id=Id;
    }
    public String getId(){
        return this.Id;
    }
    public String getUsername() {
        return Username;
    }
    public String getEmail(){
        return Email;
    }
    public void setEmail(String email){
        this.Email=email;
    }


    public String getPassword(){
        return Password;
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
        return Gender;
    }
    public static String passwordEncryption(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return(generatedPassword);
    }
}


