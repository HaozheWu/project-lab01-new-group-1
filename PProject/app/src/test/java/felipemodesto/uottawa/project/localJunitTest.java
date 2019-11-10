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
     *
     */


}