package test.dummyDao;

import dao.UserDao;
import dto.Employee;
import dto.User;

import java.util.ArrayList;
import java.util.List;

public class DummyUserDao extends UserDao {
    private List<User> users;

    public DummyUserDao(){
        throw new IllegalArgumentException("No return object provided to dummy dao");
    }

    public DummyUserDao(User user){
        users = new ArrayList<>();
        users.add(user);
    }

    @Override
    public User select(String value, String operator, String column){
        System.out.println("value: "+ value + " operator: " + operator + " column: "+ column);
        return users.getFirst();
    }

    @Override
    public User select(int value, String operator, String column){
        System.out.println("value: "+ value + " operator: " + operator + " column: "+ column);
        return users.getFirst();
    }

    @Override
    public void delete(int id){
        System.out.println("deleted: id: "+id);
    }

    @Override
    public void beginTransaction(){
        System.out.println("Beginning transaction");
    }

    @Override
    public void commit(){
        System.out.println("transaction commited");
    }

    @Override
    public void rollback(){
        System.out.println("rolling back transaction");
    }


}
