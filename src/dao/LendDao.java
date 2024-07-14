package dao;

import dto.Lend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LendDao extends Dao<Lend>{

    public LendDao(){
        super();
        this.tableName = "LEND";
        this.cols = Arrays.asList("id","id_copy","id_user","id_employee","time","returned","return_date");
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Lend entity) throws SQLException {
        stmt.setInt(1,entity.id_copy());
        stmt.setInt(2,entity.id_user());
        stmt.setInt(3,entity.id_employee());
        stmt.setDate(4,entity.time());
        stmt.setBoolean(5, entity.returned());
        stmt.setDate(6,entity.return_date());

    }


    @Override
    protected List<Lend> hydrate(ResultSet res) throws SQLException {
        List<Lend> lends = new ArrayList<>();
        while(res.next()){
            Lend lend = new Lend(
                    res.getInt("id"),
                    res.getInt("id_copy"),
                    res.getInt("id_user"),
                    res.getInt("id_employee"),
                    res.getDate("time"),
                    res.getBoolean("returned"),
                    res.getDate("return_date")
            );
            lends.add(lend);
        }
        return lends;
    }
}
