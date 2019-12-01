package felipemodesto.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static felipemodesto.uottawa.project.Second.idforsignup;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class emploeeprofiles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploeeprofiles);
    }

    DatabaseReference DatabaseUser;


    public void click(View v) {
        dialog();
    }
    public void checking(){
        String Address=getUserAddress();
        String Phone=getUserphone();
        String Company=getcompany();
        String licenced=getStatus();
        String Generalinfos=getgeneralinfo();

        if (licenced.equals("Empty")) {
            Toast.makeText(emploeeprofiles.this, "Licenced Status Should be selected", Toast.LENGTH_LONG).show();
        }else if(isNumeric(Phone)==false){
            Toast.makeText(emploeeprofiles.this, "Incorrect Phone type", Toast.LENGTH_LONG).show();
        }else if (Phone.equals("")) {
            Toast.makeText(emploeeprofiles.this, "Phone number can not be empty", Toast.LENGTH_LONG).show();
        } else if (Address.equals("")) {
            Toast.makeText(emploeeprofiles.this, "Address is invalid", Toast.LENGTH_LONG).show();
        } else if (Company.equals("")) {
            Toast.makeText(emploeeprofiles.this, "Company is empty", Toast.LENGTH_LONG).show();
        }
        else{

            DatabaseUser=FirebaseDatabase.getInstance().getReference("Users").child(idforsignup).child("Information");
            String id = DatabaseUser.push().getKey();
            Employeeprofile a = new Employeeprofile(id,Address,Phone,Company,licenced,Generalinfos);
            DatabaseUser.child(id).setValue(a);
            Toast.makeText(emploeeprofiles.this, "You have successfully fill the Information, Please sign in", Toast.LENGTH_LONG).show();
            back();
        }
    }
    protected void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(emploeeprofiles.this);
        builder.setMessage("After Submit Your Profile, You are not eligible to modified these Information");
        builder.setTitle("Warning");
        builder.setPositiveButton("I Have Confirmed what I filled", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                checking();
            }});
        builder.setNegativeButton("I want to Check, Submit later", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
    public String getUserAddress() {
        EditText et1 = (EditText) findViewById(R.id.Addressinput);
        String Address = et1.getText().toString();
        return Address;
    }

    public String getUserphone() {
        EditText et1 = (EditText) findViewById(R.id.phone);
        String phone = et1.getText().toString();
        return phone;
    }

    public String getcompany() {
        EditText et1 = (EditText) findViewById(R.id.company);
        String company = et1.getText().toString();
        return company;
    }

    public String getStatus() {
        RadioGroup status = (RadioGroup) findViewById(R.id.checkchose);
        int pos = status.getCheckedRadioButtonId();
        int b =R.id.check_Licenced;
        int c =R.id.check_notLicenced;
        if(pos==b){
            return "YES";
        }else if(pos==c){
            return "NO";
        }
        return "Empty";
    }
    public String getgeneralinfo() {
        EditText et1 = (EditText) findViewById(R.id.generalinfo);
        String info = et1.getText().toString();
        return info;
    }
    public void back() {
        Intent register = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(register);
    }




}
