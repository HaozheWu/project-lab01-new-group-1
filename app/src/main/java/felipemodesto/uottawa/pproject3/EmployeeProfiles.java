package felipemodesto.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static felipemodesto.uottawa.project.Second.idforsignup;

public class EmployeeProfiles extends AppCompatActivity {
    DatabaseReference databaseServices;
    String Address;
    String Phonenumber;
    String NameofComapany;
    String GereralInfo;
    Boolean Lincencsed;
    FirebaseDatabase mDatabase;
    DatabaseReference DatabaseUser;


    public void Onclick_signUp(View v) {
        String phone=getPhonenumber();
        if (status.equals("Empty")) {
            Toast.makeText(Second.this, "Status Should be selected", Toast.LENGTH_LONG).show();
        }else if(test(username)==false){
            Toast.makeText(Second.this, "Correct user name should be input", Toast.LENGTH_LONG).show();
        }else if (email.equals("")) {
            Toast.makeText(Second.this, "Email/Password is empty", Toast.LENGTH_LONG).show();
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            Toast.makeText(Second.this, "Email is invalid", Toast.LENGTH_LONG).show();
        } else if (passward.equals("")) {
            Toast.makeText(Second.this, "Email/Password is empty", Toast.LENGTH_LONG).show();
        } else if (username.equals("")) {
            Toast.makeText(Second.this, "Username is empty", Toast.LENGTH_LONG).show();
        } else if (gender.equals("Empty")) {
            Toast.makeText(Second.this, "Gender should be selected", Toast.LENGTH_LONG).show();
        }
        else{
            mDatabase = FirebaseDatabase.getInstance();
            DatabaseUser=FirebaseDatabase.getInstance().getReference("Users").child(idforsignup);
            String id = DatabaseUser.push().getKey();
            result  = new User(id,email,passward,gender, username, status);
            DatabaseUser.child(id).setValue(result);
        }
    }
    public  static   boolean   test(String   s)
    { char   c   =   s.charAt(0);
        int   i   =(int)c;
        if((i>=65&&i<=90)||(i>=97&&i<=122))
        { return   true;
        }
        else
        { return   false;
        }
    }
    public String getPhonenumber() {
        EditText et1 = (EditText) findViewById(R.id.emploeephone);
        String Phonenumber =  et1.getText().toString();
        return Phonenumber;
    }

    public void setPhonenumber(Long phonenumber) {
        Phonenumber = phonenumber;
    }

    public Boolean getLincencsed() {
        return Lincencsed;
    }

    public String getAddress() {
        return Address;
    }

    public String getGereralInfo() {
        return GereralInfo;
    }

    public String getNameofComapany() {
        return NameofComapany;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setGereralInfo(String gereralInfo) {
        GereralInfo = gereralInfo;
    }

    public void setLincencsed(Boolean lincencsed) {
        Lincencsed = lincencsed;
    }
    public Boolean getLicencsed(){
        return Lincencsed;
    }

    public void setNameofComapany(String nameofComapany) {
        NameofComapany = nameofComapany;
    }



}