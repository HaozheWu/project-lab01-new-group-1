package felipemodesto.uottawa.project;

public class Employeeprofile {
    String Id;
    String Company;
    String Address;
    String Phone;
    String Licenced;
    String Generalinfo;



    public Employeeprofile(String id,String Address,String Phone,String Company,String Licenced,String Generalinfo){
        this.Id=id;
        this.Company=Company;
        this.Address=Address;
        this.Phone=Phone;
        this.Licenced=Licenced;
        this.Generalinfo=Generalinfo;

    }

    public void setId(String id) {
        Id = id;
    }

    public String getAddress() {
        return Address;
    }

    public String getCompany() {
        return Company;
    }

    public String getGeneralinfo() {
        return Generalinfo;
    }

    public String getId() {
        return Id;
    }

    public String getLicenced() {
        return Licenced;
    }

    public String getPhone() {
        return Phone;
    }

}

