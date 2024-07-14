package dao;

import dto.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeDao extends Dao<Employee> {

    public EmployeeDao(){
        super();
        this.tableName = "EMPLOYEE";
        this.cols = Arrays.asList("id","id_user","salary");
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Employee entity) throws SQLException {
        stmt.setInt(1, entity.id_user());
        stmt.setDouble(2,entity.salary());
    }


    @Override
    protected List<Employee> hydrate(ResultSet res) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        while(res.next()){
            Employee employee = new Employee(
                    res.getInt("id"),
                    res.getInt("id_user"),
                    res.getDouble("salary")
            );
            employees.add(employee);
        }
        return employees;
    }
}
