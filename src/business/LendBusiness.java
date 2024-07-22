package business;

import dao.LendDao;
import dto.Book;
import dto.Lend;
import dto.User;
import helpers.Status;

import javax.swing.*;
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
        catch (NumberFormatException ex){
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null,"Por favor, introduzca solo números en la busqueda","Error",JOptionPane.ERROR_MESSAGE);
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

    public void markAsReturned(Lend lend){
        try {
            BookBusiness bookBusiness = new BookBusiness();
            dao.beginTransaction();
            bookBusiness.updateCopies(lend.id_book(),false);
            update("return_date",new Date(System.currentTimeMillis()),lend.id());
            dao.commit();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Error updating lend");
        }
    }

    public String generateStatistics(){
        try {
            //possible todo: add book with max active lends, user with max active lends
            UserBusiness userBusiness = new UserBusiness();
            BookBusiness bookBusiness = new BookBusiness();
            User user = userBusiness.select(dao.findMaxLendsUser(),"=","id");
            Book book = bookBusiness.select(dao.findMaxLendsBook(),"=","id");
            return String.format("<html>Historico:<br>Libro más solicitado: %s - %s<br>Usuario con más libros prestados: %s, %s</html>",book.getAuthor().name(),book.title(),user.getFullName(), user.email());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
