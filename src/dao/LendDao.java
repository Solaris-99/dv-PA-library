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
        this.cols = Arrays.asList("id_book","id_user","id_employee","time","return_date");
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Lend entity) throws SQLException {
        stmt.setInt(1,entity.id_book());
        stmt.setInt(2,entity.id_user());
        stmt.setInt(3,entity.id_employee());
        stmt.setDate(4,entity.time());
        stmt.setDate(5,entity.return_date());
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
                    res.getDate("return_date")
            );
            lends.add(lend);
        }
        return lends;
    }

    public int lendCount() throws SQLException{
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(1) AS COUNT FROM LEND");
        ResultSet res = stmt.executeQuery();
        res.next();
        return res.getInt("COUNT");
    }

}
