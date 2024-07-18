package dto;

import business.BookBusiness;
import business.UserBusiness;

import java.sql.Date;

public record Lend(int id, int id_book, int id_user, int id_employee, Date time, Date return_date) implements Entity {

    public String getBookTitle(){
        BookBusiness bookBusiness = new BookBusiness();
        Book book = bookBusiness.select(id_book,"=","id");
        return book.getAuthor() + " - " + book.title();
    }

    public String getUserIdentity(){
        UserBusiness userBusiness = new UserBusiness();
        User user = userBusiness.select(id_user,"=","id");
        return user.getFullName() + " - DNI: "+user.DNI();
    }

}
