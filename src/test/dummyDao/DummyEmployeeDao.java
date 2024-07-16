package test.dummyDao;

import dao.EmployeeDao;
import dto.Employee;

import java.util.ArrayList;
import java.util.List;

public class DummyEmployeeDao extends EmployeeDao {
    private List<Employee> employees;

    public DummyEmployeeDao(){
        throw new IllegalArgumentException("No return object provided to dummy dao");
    }

    public DummyEmployeeDao(Employee employee){
        employees = new ArrayList<>();
        employees.add(employee);
    }

    @Override
    public Employee select(String value, String operator, String column){
        System.out.println("value: "+ value + " operator: " + operator + " column: "+ column);
        return employees.getFirst();
    }

    @Override
    public Employee select(int value, String operator, String column){
        System.out.println("value: "+ value + " operator: " + operator + " column: "+ column);
        return employees.getFirst();
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
