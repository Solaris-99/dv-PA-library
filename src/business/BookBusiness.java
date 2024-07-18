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

    public void updateCopies(int bookId, boolean borrowing){
        try {
            Book book = this.dao.select(bookId,"=","id");
            int copies = book.available_copies();
            if(copies < 1 && borrowing){
                throw new IllegalStateException("Borrowing book that is not available");
            }
            int newCopies = borrowing? copies-1 : copies+1;
            dao.update("available_copies", newCopies,bookId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
