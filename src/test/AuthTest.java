package test;

import business.AuthBusiness;
import dto.Employee;
import dto.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import test.dummyDao.DummyEmployeeDao;
import test.dummyDao.DummyUserDao;

public class AuthTest {
    private AuthBusiness authBusiness;

    @BeforeEach
    public void setUp(){
        DummyUserDao dummyUserDao = new DummyUserDao(new User(1,"pepe","perez","pepe@gmail.com", BCrypt.hashpw("1234",BCrypt.gensalt()),1234567));
        DummyEmployeeDao dummyEmployeeDao = new DummyEmployeeDao(new Employee(1,1,300));
        authBusiness = new AuthBusiness(dummyUserDao,dummyEmployeeDao);
    }

    @Test
    public void loginTest(){
        boolean loginTrue = authBusiness.login("pepe@gmail.com","1234");
        boolean loginFalse = authBusiness.login("pepe@gmail.com","123456");
        Assertions.assertAll(
                ()->{
                    Assertions.assertTrue(loginTrue);
                    Assertions.assertFalse(loginFalse);
                }

        );
    }

}
