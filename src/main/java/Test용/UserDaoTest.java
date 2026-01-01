package Test용;

import member.dao.UserDAO;
import member.dto.UserDTO;

import java.sql.Date;
import java.util.UUID;

public class UserDaoTest {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        // 1. ȸ������ �׽�Ʈ
        UserDTO newUser = new UserDTO();
//        newUser.setId("test01");
//        String testId = "test_" + System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        String testId = "test_" + uuid;
        newUser.setId(testId);

        newUser.setPassword("1234");
        newUser.setEmail("test01@test.com");
        newUser.setFull_name("�׽�Ʈ����");
        newUser.setBirth(Date.valueOf("2000-01-01"));
        newUser.setGender("M");
        newUser.setNationality("K");
        newUser.setPhonenumber("01012345678");

        int signupResult = dao.signup(newUser);
        System.out.println("ȸ������ ���: " + signupResult); // 1�̸� ����

        // 2. �α��� �׽�Ʈ
        UserDTO loginUser = dao.select(testId);

        if (loginUser != null) {
            System.out.println("�α��� ����: " + loginUser.getFull_name());
        } else {
            System.out.println("�α��� ����");
        }

        // 3. ���������� ��ȸ �׽�Ʈ
        UserDTO userInfo = dao.select(testId);

        if (userInfo != null) {
            System.out.println("ȸ������ ��ȸ ����");
            System.out.println("�̸�: " + userInfo.getFull_name());
            System.out.println("�̸���: " + userInfo.getEmail());
        } else {
            System.out.println("ȸ������ ��ȸ ����");
        }

        // 4. ���������� ���� �׽�Ʈ
        userInfo.setEmail("updated"+uuid+"@test.com");
        userInfo.setPhonenumber("01099998888");

        int updateResult = dao.mypageupdate(userInfo);
        System.out.println("ȸ������ ���� ���: " + updateResult); // 1�̸� ����
    }
}
