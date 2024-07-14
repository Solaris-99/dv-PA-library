package business;

import dao.EmployeeDao;
import dao.UserDao;

public class AuthBusiness {
    private UserDao userDao;
    private EmployeeDao employeeDao;

    public AuthBusiness(UserDao userDao, EmployeeDao employeeDao){
        this.userDao = userDao;
        this.employeeDao = employeeDao;
    }

    //TODO:
    // login, register, isEmployee;

}
