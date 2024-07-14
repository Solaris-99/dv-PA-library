package business;

import dao.EmployeeDao;
import dto.Employee;

public class EmployeeBusiness extends Business<EmployeeDao, Employee>{
    public EmployeeBusiness(EmployeeDao dao) {
        super(dao);
    }
}
