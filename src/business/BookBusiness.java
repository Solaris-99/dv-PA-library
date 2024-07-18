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

}
