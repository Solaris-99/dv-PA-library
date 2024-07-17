package dao;

import dto.Book;
import helpers.Status;

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
        this.cols = Arrays.asList("title","year","id_author","id_publisher","total_copies","available_copies");
    }
    //TODO: NUMBER OF COPIES


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
                    res.getInt("id_publisher"),
                    res.getInt("total_copies"),
                    res.getInt("available_copies")
            );
            books.add(book);
        }
        return books;
    }

    public List<Book> selectAll(String name, int page) throws SQLException{
        int itemsPerPage = Status.getInstance().getItemsPerPage();
        name = "%"+name+"%";
        int lowerLimit = itemsPerPage*page;
        int upperLimit = lowerLimit+itemsPerPage;
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM BOOK WHERE TITLE LIKE ? LIMIT ?, ?");
        stmt.setString(1,name);
        stmt.setInt(2,lowerLimit);
        stmt.setInt(3,upperLimit);
        return hydrate(stmt.executeQuery());
    }

    public int bookCount() throws SQLException{
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(1) AS COUNT FROM BOOK");
        ResultSet res = stmt.executeQuery();
        res.next();
        return res.getInt("COUNT");
    }

}
