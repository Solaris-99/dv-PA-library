package dao;

import dto.Book;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookDao extends Dao<Book> {

    public BookDao(){
        super();
        this.tableName = "BOOK";
        this.cols = Arrays.asList("id","title","year","id_author","id_publisher");
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Book entity) throws SQLException {
        stmt.setString(1, entity.title());
        stmt.setInt(2,entity.year());
        stmt.setInt(3,entity.id_author());
        stmt.setInt(4,entity.id_publisher());
    }


    @Override
    protected List<Book> hydrate(ResultSet res) throws SQLException {
        List<Book> books = new ArrayList<>();
        while(res.next()){
            Book book = new Book(
                    res.getInt("id"),
                    res.getString("title"),
                    res.getInt("year"),
                    res.getInt("id_author"),
                    res.getInt("id_publisher")
            );
            books.add(book);
        }
        return books;
    }

}
