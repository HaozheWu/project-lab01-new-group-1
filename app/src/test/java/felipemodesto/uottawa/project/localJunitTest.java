package felipemodesto.uottawa.project;



import org.junit.Test;

import static org.apache.maven.artifact.ant.shaded.StringUtils.isNumeric;
import static org.junit.Assert.*;


public class localJunitTest {


    @Test
    public void isEmail() {
        String eamilStr = "00000@.com";
        String eamilStr1 = "00000@qq.com";
        System.out.println("email is valid: ===" + RegTool.isEmail(eamilStr));
        System.out.println("email is valid: ===" + RegTool.isEmail(eamilStr1));
    }

    /**
     * md5
     *
     */
    @Test
    public void isCorrectMD5() {
        String pwd = "123456";
        String pwdMD5Str32 = "e10adc3949ba59abbe56e057f20f883e";//123456md5  32passwordEncryption
        String pwdMD5Str16 = "49ba59abbe56e057";//123456md5  16passwordEncryption
        passwordEncryption passwordEncryption = new passwordEncryption();
        assertEquals(pwdMD5Str32, passwordEncryption.passwordEncryption(pwd));
        assertNotEquals(pwdMD5Str16, passwordEncryption.passwordEncryption(pwd));
    }

    /**
     * price is number or not
     *
     */
    @Test
    public void isNumberPrice() {
        String price = "000.09";
        System.out.println("price is number==" + isNumeric(price));
    }

    /**
     * Test user
     */
    @Test
    public void checkUser(){
        String id = "12";
        String Email = "email@email.com";
        String Passward="#io12365";
        String Username="belly";
        String Status = "admin";
        String Gender = "female" ;

        User user =new User(id,Email,Passward,Gender,Username,Status);
        assertNotNull(user);
        assertEquals(Email,user.getEmail());
        //using not equal Since passwordEncryption
        assertNotEquals(Passward,user.getPassword());
        assertEquals(Username,user.getUsername());
        assertEquals(Status,user.getStatus());
        assertEquals(Gender,user.getGender());


    }

    /**
     * Test services
     */
    @Test
    public void checkServices(){
        String _id = "13";
        String _Servicesname = "Allergy";
        String  _emploeename = "Liliya";
        String  _emploeerole = "employee";
        String  _price = "112.25" ;

        Services service = new Services( _id, _Servicesname, _price,_emploeename,_emploeerole);
        assertNotNull(service);
        assertEquals(_Servicesname,service.getServicesName());
        assertEquals(_emploeename,service.get_emploeename());
        assertEquals(_emploeerole,service.get_emploeerole());
        assertEquals(_price,service.getPrice());



    }
    
    //two more tests for dev3
    @Test
    // test employee profile
    public void checkEmployeeProfile(){
        String id = "203";
        String Company = "Spark";
        String Address ="182 Riverside";
        String Phone = "1234567890";
        String Licenced ="yes";
        String Generalinfo = "I have been a dentist 10 years";

        Employeeprofile employeeprofile = new Employeeprofile(id,Address,Phone,Company,Licenced,Generalinfo);
        assertNotNull(employeeprofile);
        assertEquals(Company, employeeprofile.getCompany());
        assertEquals(Address, employeeprofile.getAddress());
        assertEquals(Phone, employeeprofile.getPhone());
        assertEquals(Licenced, employeeprofile.getLicenced());
        assertEquals(Generalinfo, employeeprofile.getGeneralinfo());
    }

    @Test
    //test time
    public void checkTime(){
        String weekday = "Friday";
        String starthour = "9";
        String startminute = "30";
        String endhour = "18";
        String endminute = "30";
        String id = "5";
        String publicid = "12";
        Time time  = new Time(id,weekday,starthour,startminute,endhour,endminute,publicid);
        assertNotNull(time);
        assertEquals(weekday, time.getWeekday());
        assertEquals(starthour, time.getStarthour());
        assertEquals(startminute, time.getStartminute());
        assertEquals(endhour, time.getEndhour());
        assertEquals(endminute, time.getEndminute());
        assertNotEquals(id, time.getId());
    }

    // ten more tests for dev4
    @Test
    //check appoinment
    public void checkAppoinment(){
        String emploee = "Leo";
        String waitingtime = "10";
        String customerid = "12";
        String emploeeid = "21";
        String appointmentid = "3";
        String email = "test@test.ca";
        String gender = "female";
        appointment myappoinment = new appointment(emploee, waitingtime, customerid, emploeeid, appointmentid, email, gender);
        assertNotNull(myappoinment);
        assertEquals(emploee, myappoinment.getEmploee());
        assertEquals(waitingtime, myappoinment.getWaitingtime());
        assertEquals(customerid, myappoinment.getCustomerid());
        assertEquals(emploeeid, myappoinment.getEmploeeid());
        assertEquals(appointmentid, myappoinment.getAppointmentid());
        assertEquals(email, myappoinment.getEmail());
        assertEquals(gender, myappoinment.getGender());
    }
    
    public void checkCheckTimePara(){
        String day="";
        String starttime = "9";
        String endtime = "6";
        String startminute = "30";
        String endminute = "30";
        boolean result;
        boolean real;
        real = false;
        result = timecontrol.checkTimePara(day, starttime, startminute, endtime, endminute);
        assertEquals(result,real);
    }
}
