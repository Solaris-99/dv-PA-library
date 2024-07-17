package business;

import dao.BookDao;
import dto.Book;
import helpers.Status;

import java.sql.SQLException;
import java.util.List;

public class BookBusiness extends Business<BookDao, Book> {

    public BookBusiness(){
        super(new BookDao());
    }

    public BookBusiness(BookDao dao) {
        super(dao);
    }

    public List<Book> selectAll(String name,int page){
        try {
            return dao.selectAll(name, page);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getTotalPages(){
        try{
            return (int) Math.ceil(dao.bookCount()/ Status.getInstance().getItemsPerPage());
        }
        catch (SQLException e){
            System.out.println("error getting number of pages");
            System.out.println(e.getMessage());
        }
        return -1;
    }


}
