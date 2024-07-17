package business;

import dao.Dao;
import dao.EmployeeDao;
import dao.UserDao;
import dto.User;
import helpers.Status;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.NoSuchElementException;

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
            boolean isEmp = employeeDao.isEmployee(userId);
            Status.getInstance().setEmployee(isEmp);
            return isEmp;
        } catch (SQLException e) {
            System.out.println("Error checking employee");
            return false;
        }
    }

    public boolean login(String email, String password){
        try{
            User user = userDao.select(email,"=","email");
            if(BCrypt.checkpw(password,user.password())){
                isEmployee(user.id());
                Status.getInstance().setUserId(user.id());
                return true;
            }
        }
        catch (NoSuchElementException nse){
            System.out.println("password/email not found");
            return false;
        }
        catch (SQLException e) {
            System.out.println("Error authenticating user");
            System.out.println(e.getMessage());
        }


        return false;
    }


}
