package dao;

import dto.Copy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CopyDao extends Dao<Copy>{

    public CopyDao(){
        super();
        this.tableName = "COPY";
        this.cols = Arrays.asList("id","id_book");
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Copy entity) throws SQLException {
        stmt.setInt(1,entity.id_book());
    }


    @Override
    protected List<Copy> hydrate(ResultSet res) throws SQLException {
        List<Copy> copies = new ArrayList<>();
        while(res.next()){
            Copy copy = new Copy(
                    res.getInt("id"),
                    res.getInt("id_book")
            );
            copies.add(copy);
        }
        return copies;
    }

}
