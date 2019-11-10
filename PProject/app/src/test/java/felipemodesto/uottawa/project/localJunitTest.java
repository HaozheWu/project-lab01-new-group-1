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
        String Email = "email@email.com";
        String Passward="#io12365";
        String Username="belly";
        String Status = "admin";
        String Gender = "female" ;

        User user =new User(Email,Passward,Gender,Username,Status);
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
         String _Servicesname = "Allergy";
         String  _emploeename = "Liliya";
         String  _emploeerole = "employee";
         double _price = 112.25 ;

        Services service = new Services( _Servicesname, _price,_emploeename,_emploeerole);
        assertNotNull(service);
        assertEquals(_Servicesname,service.getServicesName());
        assertEquals(_emploeename,service.get_emploeename());
        assertEquals(_emploeerole,service.get_emploeerole());
        assertEquals(_price,service.getPrice(),0);
    }



    }