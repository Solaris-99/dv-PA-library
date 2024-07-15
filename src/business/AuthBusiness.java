package business;

import dao.Dao;
import dao.EmployeeDao;
import dao.UserDao;
import dto.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class AuthBusiness {
    private final UserDao userDao;
    private final EmployeeDao employeeDao;

    public AuthBusiness(){
        userDao = new UserDao();
        employeeDao = new EmployeeDao();
    }

    public AuthBusiness(UserDao userDao, EmployeeDao employeeDao) {
        this.userDao = userDao;
        this.employeeDao = employeeDao;
    }

    //TODO:
    // login, register, isEmployee;
    public boolean isEmployee(int userId){
        try{
            return employeeDao.isEmployee(userId);
        } catch (SQLException e) {
            System.out.println("Error checking employee");
            return false;
        }
    }

    public boolean login(String email, String password){
        try{
            User user = userDao.select(email,"=","email");
            if(BCrypt.checkpw(password,user.password())){
                //TODO: status
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error authenticating user");
            System.out.println(e.getMessage());
        }
        return false;
    }


}
