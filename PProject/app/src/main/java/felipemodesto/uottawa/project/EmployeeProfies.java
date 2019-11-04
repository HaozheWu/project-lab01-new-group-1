package felipemodesto.uottawa.project;

import android.util.EventLogTags;

public class EmployeeProfies {
    String Address;
    Long Phonenumber;
    String NameofComapany;
    String GereralInfo;
    Boolean Lincencsed;

    public EmployeeProfies(String Address,Long PhoneNumber, String NameofCompant, String GereralInfo, Boolean Licencsed) {
        this.Address = Address;
        this.GereralInfo = GereralInfo;
        this.NameofComapany = NameofCompant;
        this.Lincencsed = Licencsed;
        this.Phonenumber=PhoneNumber;
    }

    public Long getPhonenumber() {
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

    public void setNameofComapany(String nameofComapany) {
        NameofComapany = nameofComapany;
    }
}
