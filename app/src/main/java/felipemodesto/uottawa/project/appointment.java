package felipemodesto.uottawa.project;

public class appointment {
    private String emploee;
    private String waitingtime;
    private String customerid;
    private String emploeeid;
    private String appointmentid;
    private String email;
    private String gender;
    public appointment(){}
    public appointment(String emploee,String waitingtime,String customerid,String emploeeid,String appointmentid,String email,String gender){
    this.customerid=customerid;
    this.emploee=emploee;
    this.emploeeid=emploeeid;
    this.waitingtime=waitingtime;
    this.appointmentid=appointmentid;
    this.email=email;
    this.gender=gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAppointmentid() {
        return appointmentid;
    }

    public void setAppointmentid(String appointmentid) {
        this.appointmentid = appointmentid;
    }

    public String getEmploee() {
        return emploee;
    }

    public String getCustomerid() {
        return customerid;
    }

    public String getEmploeeid() {
        return emploeeid;
    }

    public String getWaitingtime() {
        return waitingtime;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public void setEmploee(String emploee) {
        this.emploee = emploee;
    }

    public void setEmploeeid(String emploeeid) {
        this.emploeeid = emploeeid;
    }

    public void setWaitingtime(String waitingtime) {
        this.waitingtime = waitingtime;
    }
}


