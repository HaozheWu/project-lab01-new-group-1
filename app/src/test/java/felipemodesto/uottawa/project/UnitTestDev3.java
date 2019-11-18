package felipemodesto.uottawa.project;
import org.junit.Test;

import static org.apache.maven.artifact.ant.shaded.StringUtils.isNumeric;
import static org.junit.Assert.*;
public class UnitTestDev3 {

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
        Time time  = new Time(id,weekday,starthour,startminute,endhour,endminute);
        assertNotNull(time);
        assertEquals(weekday, time.getWeekday());
        assertEquals(starthour, time.getStarthour());
        assertEquals(startminute, time.getStartminute());
        assertEquals(endhour, time.getEndhour());
        assertEquals(endminute, time.getEndminute());
        assertNotEquals(id, time.getId());
    }



}
