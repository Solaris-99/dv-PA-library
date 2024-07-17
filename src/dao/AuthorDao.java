package dao;

import dto.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorDao extends Dao<Author> {

    public AuthorDao(){
        super();
        this.tableName = "AUTHOR";
        this.cols = List.of("name");
    }


    @Override
    protected void setInsertParameters(PreparedStatement stmt, Author entity) throws SQLException {
        stmt.setString(1, entity.name());
    }

    @Override
    protected List<Author> hydrate(ResultSet res) throws SQLException {
        List<Author> authors = new ArrayList<>();
        while(res.next()){
            Author author = new Author(
                    res.getInt("id"),
                    res.getString("name")
            );
            authors.add(author);
        }
        return authors;
    }

}
