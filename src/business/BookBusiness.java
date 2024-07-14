package business;

import dao.BookDao;
import dto.Book;

public class BookBusiness extends Business<BookDao, Book> {

    public BookBusiness(BookDao dao) {
        super(dao);
    }
}
