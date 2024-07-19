package dao;

import dto.Lend;
import helpers.Status;

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
        this.cols = Arrays.asList("id_book","id_user","time","return_date");
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, Lend entity) throws SQLException {
        stmt.setInt(1,entity.id_book());
        stmt.setInt(2,entity.id_user());
        stmt.setDate(3,entity.time());
        stmt.setDate(4,entity.return_date());
    }


    @Override
    protected List<Lend> hydrate(ResultSet res) throws SQLException {
        List<Lend> lends = new ArrayList<>();
        while(res.next()){
            Lend lend = new Lend(
                    res.getInt("id"),
                    res.getInt("id_book"),
                    res.getInt("id_user"),
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

    public List<Lend> selectAll(int dni, int page) throws SQLException{
        int itemsPerPage = Status.getInstance().getItemsPerPage();
        int lowerLimit = itemsPerPage*page;
        int upperLimit = lowerLimit+itemsPerPage;

        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM LEND INNER JOIN USER ON USER.ID = ID_USER WHERE DNI = ? LIMIT ?, ?");
        stmt.setInt(1,dni);
        stmt.setInt(2, lowerLimit);
        stmt.setInt(3,upperLimit);
        return this.hydrate(stmt.executeQuery());
    }

    public boolean isLent(int idBook, int idUser) throws SQLException{
        PreparedStatement stmt = connection.prepareStatement("SELECT ID FROM LEND WHERE ID_BOOK = ? AND ID_USER = ? AND RETURN_DATE IS NULL");
        stmt.setInt(1,idBook);
        stmt.setInt(2,idUser);
        return stmt.executeQuery().isBeforeFirst();
    }

    public int findMaxLendsUser() throws  SQLException{
        PreparedStatement stmt = connection.prepareStatement("SELECT ID_USER, COUNT(1) AS COUNT FROM LEND GROUP BY ID_USER ORDER BY COUNT DESC LIMIT 1");
        return stmt.executeQuery().getInt("id_user");
    }

    public int findMaxLendsBook() throws  SQLException{
        PreparedStatement stmt = connection.prepareStatement("SELECT ID_BOOK, COUNT(1) AS COUNT FROM LEND GROUP BY ID_USER ORDER BY COUNT DESC LIMIT 1");
        return stmt.executeQuery().getInt("id_book");
    }

}
