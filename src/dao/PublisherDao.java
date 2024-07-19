package dao;

import dto.Publisher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PublisherDao extends Dao<Publisher> {

    public PublisherDao(){
        super();
        this.tableName = "publisher";
        this.cols = List.of("name");
    }


    @Override
    protected void setInsertParameters(PreparedStatement stmt, Publisher entity) throws SQLException {
        stmt.setString(1, entity.name());
    }

    @Override
    protected List<Publisher> hydrate(ResultSet res) throws SQLException {
        List<Publisher> publishers = new ArrayList<>();
        while(res.next()){
            Publisher publisher = new Publisher(
                    res.getInt("id"),
                    res.getString("name")
            );
            publishers.add(publisher);
        }
        return publishers;
    }
}
