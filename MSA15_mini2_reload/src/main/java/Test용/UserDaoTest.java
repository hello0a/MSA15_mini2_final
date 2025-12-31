package Test용;

import reservation.dao.UserDAO;
import reservation.dao.UserDAOImpl;
import reservation.dto.UserDTO;

import java.sql.Date;
import java.util.UUID;

public class UserDaoTest {

    public static void main(String[] args) {

        UserDAO dao = new UserDAOImpl();

        // 1. 회원가입 테스트
        UserDTO newUser = new UserDTO();
//        newUser.setId("test01");
//        String testId = "test_" + System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String testId = "test_" + uuid;
        newUser.setId(testId);

        newUser.setPassword("1234");
        newUser.setEmail("test01@test.com");
        newUser.setFull_name("테스트유저");
        newUser.setBirth(Date.valueOf("2000-01-01"));
        newUser.setGender("M");
        newUser.setNationality("K");
        newUser.setPhonenumber("01012345678");

        int signupResult = dao.signup(newUser);
        System.out.println("회원가입 결과: " + signupResult); // 1이면 성공

        // 2. 로그인 테스트
        UserDTO loginUser = dao.selectById(testId);

        if (loginUser != null) {
            System.out.println("로그인 성공: " + loginUser.getFull_name());
        } else {
            System.out.println("로그인 실패");
        }

        // 3. 마이페이지 조회 테스트
        UserDTO userInfo = dao.selectById(testId);

        if (userInfo != null) {
            System.out.println("회원정보 조회 성공");
            System.out.println("이름: " + userInfo.getFull_name());
            System.out.println("이메일: " + userInfo.getEmail());
        } else {
            System.out.println("회원정보 조회 실패");
        }

        // 4. 마이페이지 수정 테스트
        userInfo.setEmail("updated"+uuid+"@test.com");
        userInfo.setPhonenumber("01099998888");

        int updateResult = dao.mypageupdate(userInfo);
        System.out.println("회원정보 수정 결과: " + updateResult); // 1이면 성공
    }
}
