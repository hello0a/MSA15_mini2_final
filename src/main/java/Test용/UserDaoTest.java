<<<<<<< HEAD
package Testìš©;

import member.dao.UserDAO;
import member.dto.UserDTO;
=======
package Test¿ë;

import reservation.dao.UserDAO;
import reservation.dao.UserDAOImpl;
import reservation.dto.UserDTO;
>>>>>>> khsoo

import java.sql.Date;
import java.util.UUID;

public class UserDaoTest {

    public static void main(String[] args) {

<<<<<<< HEAD
        UserDAO dao = new UserDAO();

        // 1. È¸ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½×½ï¿½Æ®
=======
        UserDAO dao = new UserDAOImpl();

        // 1. È¸¿ø°¡ÀÔ Å×½ºÆ®
>>>>>>> khsoo
        UserDTO newUser = new UserDTO();
//        newUser.setId("test01");
//        String testId = "test_" + System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String testId = "test_" + uuid;
        newUser.setId(testId);

        newUser.setPassword("1234");
        newUser.setEmail("test01@test.com");
<<<<<<< HEAD
        newUser.setFull_name("ï¿½×½ï¿½Æ®ï¿½ï¿½ï¿½ï¿½");
=======
        newUser.setFull_name("Å×½ºÆ®À¯Àú");
>>>>>>> khsoo
        newUser.setBirth(Date.valueOf("2000-01-01"));
        newUser.setGender("M");
        newUser.setNationality("K");
        newUser.setPhonenumber("01012345678");

        int signupResult = dao.signup(newUser);
<<<<<<< HEAD
        System.out.println("È¸ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½: " + signupResult); // 1ï¿½Ì¸ï¿½ ï¿½ï¿½ï¿½ï¿½

        // 2. ï¿½Î±ï¿½ï¿½ï¿½ ï¿½×½ï¿½Æ®
        UserDTO loginUser = dao.select(testId);

        if (loginUser != null) {
            System.out.println("ï¿½Î±ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½: " + loginUser.getFull_name());
        } else {
            System.out.println("ï¿½Î±ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½");
        }

        // 3. ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È¸ ï¿½×½ï¿½Æ®
        UserDTO userInfo = dao.select(testId);

        if (userInfo != null) {
            System.out.println("È¸ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È¸ ï¿½ï¿½ï¿½ï¿½");
            System.out.println("ï¿½Ì¸ï¿½: " + userInfo.getFull_name());
            System.out.println("ï¿½Ì¸ï¿½ï¿½ï¿½: " + userInfo.getEmail());
        } else {
            System.out.println("È¸ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½È¸ ï¿½ï¿½ï¿½ï¿½");
        }

        // 4. ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½×½ï¿½Æ®
=======
        System.out.println("È¸¿ø°¡ÀÔ °á°ú: " + signupResult); // 1ÀÌ¸é ¼º°ø

        // 2. ·Î±×ÀÎ Å×½ºÆ®
        UserDTO loginUser = dao.selectById(testId);

        if (loginUser != null) {
            System.out.println("·Î±×ÀÎ ¼º°ø: " + loginUser.getFull_name());
        } else {
            System.out.println("·Î±×ÀÎ ½ÇÆÐ");
        }

        // 3. ¸¶ÀÌÆäÀÌÁö Á¶È¸ Å×½ºÆ®
        UserDTO userInfo = dao.selectById(testId);

        if (userInfo != null) {
            System.out.println("È¸¿øÁ¤º¸ Á¶È¸ ¼º°ø");
            System.out.println("ÀÌ¸§: " + userInfo.getFull_name());
            System.out.println("ÀÌ¸ÞÀÏ: " + userInfo.getEmail());
        } else {
            System.out.println("È¸¿øÁ¤º¸ Á¶È¸ ½ÇÆÐ");
        }

        // 4. ¸¶ÀÌÆäÀÌÁö ¼öÁ¤ Å×½ºÆ®
>>>>>>> khsoo
        userInfo.setEmail("updated"+uuid+"@test.com");
        userInfo.setPhonenumber("01099998888");

        int updateResult = dao.mypageupdate(userInfo);
<<<<<<< HEAD
        System.out.println("È¸ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½ï¿½ ï¿½ï¿½ï¿½: " + updateResult); // 1ï¿½Ì¸ï¿½ ï¿½ï¿½ï¿½ï¿½
=======
        System.out.println("È¸¿øÁ¤º¸ ¼öÁ¤ °á°ú: " + updateResult); // 1ÀÌ¸é ¼º°ø
>>>>>>> khsoo
    }
}
