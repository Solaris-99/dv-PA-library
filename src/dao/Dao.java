package dao;
import config.DBConnection;
import dto.Entity;
import helpers.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Dao <T extends Entity>{

    protected final Connection connection;
    protected String tableName;
    protected List<String> cols;

    public Dao(){
        this.connection = DBConnection.getConnection();
    }

    public T select(int val, String operator, String column) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM "+ this.getTableName() +" WHERE "+ column + " "+operator+" ?");
        stmt.setInt(1,val);
        return this.hydrate(stmt.executeQuery()).getFirst();
    }

    public T select(String value, String operator, String column) throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM "+ this.getTableName() +" WHERE "+ column + " "+operator+" ?");
        stmt.setString(1,value);
        return this.hydrate(stmt.executeQuery()).getFirst();
    }

    public List<T> selectAll() throws SQLException{
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM "+this.getTableName());
        return this.hydrate(stmt.executeQuery());
    }

    public List<T> selectAll(int page) throws SQLException{
        int itemsPerPage = Status.getInstance().getItemsPerPage();
        int lowerLimit = itemsPerPage*page;
        int upperLimit = lowerLimit+itemsPerPage;
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM "+this.getTableName() + " LIMIT ?, ?");
        stmt.setInt(1,lowerLimit);
        stmt.setInt(2,upperLimit);
        return this.hydrate(stmt.executeQuery());
    }

    public List<T> selectAll(String value, String operator, String column, int page) throws SQLException{
        int itemsPerPage = Status.getInstance().getItemsPerPage();
        int lowerLimit = itemsPerPage*page;
        int upperLimit = lowerLimit+itemsPerPage;
        if(operator.equalsIgnoreCase("like")){
            value = "%"+value+"%";
        }
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM "+ this.getTableName() +" WHERE "+ column + " "+operator+" ? LIMIT ?, ?");
        stmt.setString(1,value);
        stmt.setInt(2,lowerLimit);
        stmt.setInt(3,upperLimit);
        return this.hydrate(stmt.executeQuery());
    }

    public List<T> selectAll(int value, String operator, String column, int page) throws SQLException{
        int itemsPerPage = Status.getInstance().getItemsPerPage();
        int lowerLimit = itemsPerPage*page;
        int upperLimit = lowerLimit+itemsPerPage;
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM "+ this.getTableName() +" WHERE "+ column + " "+operator+" ? LIMIT ?, ?");
        stmt.setInt(1,value);
        stmt.setInt(2, lowerLimit);
        stmt.setInt(3,upperLimit);
        return this.hydrate(stmt.executeQuery());
    }

    public String getTableName() {
        return tableName;
    }

    public T create(T entity) throws SQLException {
        String sql = getInsertQuery();
        PreparedStatement stmt = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        setInsertParameters(stmt, entity);
        stmt.executeUpdate();
        ResultSet res = stmt.getGeneratedKeys();
        res.next();
        int id = res.getInt(1);
        return this.select(id,"=","id");
    }


    public void delete(int id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM " + getTableName() + " WHERE ID = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public T update(String column, int value, int id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("UPDATE " + getTableName() + " SET " + column + " = ? WHERE ID = ?");
        stmt.setInt(1, value);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        return this.select(id, "=", "id");
    }

    public T update(String column, double value, int id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("UPDATE " + getTableName() + " SET " + column + " = ? WHERE ID = ?");
        stmt.setDouble(1, value);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        return this.select(id, "=", "id");
    }

    public T update(String column, String value, int id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("UPDATE " + getTableName() + " SET " + column + " = ? WHERE ID = ?");
        stmt.setString(1, value);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        return this.select(id, "=", "id");
    }

    public T update(String column, Date value, int id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("UPDATE " + getTableName() + " SET " + column + " = ? WHERE ID = ?");
        stmt.setDate(1, value);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        return this.select(id, "=", "id");
    }

    protected String getInsertQuery(){
        String cols = String.join(",",this.cols);
        StringBuilder builder = new StringBuilder("INSERT INTO ");
        builder.append(this.getTableName());
        builder.append(" ( ");
        builder.append(cols);
        builder.append(" ) VALUES (");
        List<String> wildcards = new ArrayList<>();
        for(int i = 0 ; i < this.cols.size(); i++){
            wildcards.add("?");
        }
        builder.append(String.join(",",wildcards));
        builder.append(")");
        System.out.println(builder);
        return builder.toString();
    }

    public int getItemCount() throws SQLException{
        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(1) AS COUNT FROM "+tableName);
        ResultSet res = stmt.executeQuery();
        res.next();
        return res.getInt("COUNT");
    }

    protected abstract void setInsertParameters(PreparedStatement stmt, T entity) throws SQLException;

    protected abstract List<T> hydrate(ResultSet res) throws SQLException;

    public void beginTransaction() throws SQLException {
        this.connection.setAutoCommit(false);
    }

    public void commit() throws SQLException {
        this.connection.commit();
        this.connection.setAutoCommit(true);
    }

    public void rollback() throws SQLException {
        this.connection.rollback();
    }

}
