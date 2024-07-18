package business;

import dao.LendDao;
import dto.Lend;
import helpers.Status;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public class LendBusiness extends Business<LendDao, Lend>{

    public LendBusiness(){
        super(new LendDao());
    }

    public LendBusiness(LendDao dao) {
        super(dao);
    }

    public List<Lend> selectAll(String DNI, int page){
        try{
            int dni = Integer.parseInt(DNI);
            return dao.selectAll(dni,page);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean isLent(int idBook){
        try {
            return dao.isLent(idBook, Status.getInstance().getUserId());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        catch (NoSuchElementException ex){
            return false;
        }
    }

    public void makeLend(int idUser, int idBook){
        try {
            BookBusiness bookBusiness = new BookBusiness();
            Lend newLend = new Lend(-1,idBook,idUser,new Date(System.currentTimeMillis()),null);
            dao.beginTransaction();
            dao.create(newLend);
            bookBusiness.updateCopies(idBook,true);
            dao.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }


}
