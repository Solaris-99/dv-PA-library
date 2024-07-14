package test;

import dao.Dao;
import dto.Entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DummyDao <T extends Entity> extends Dao<T> {
    private List<T> entities;

    public DummyDao(){throw new IllegalArgumentException("No return entity provided to DummyDao");}

    public DummyDao(T e){
        entities = new ArrayList<>();
        entities.add(e);
    }

    @Override
    public T select(int val, String operator, String column) throws SQLException {
        System.out.println("select - args: val:"+val+" operator: "+operator + " column: "+column);
        return entities.getFirst();
    }

    @Override
    public T select(String value, String operator, String column) throws SQLException{
        System.out.println("select - args: val:"+value+" operator: "+operator + " column: "+column);
        return entities.getFirst();
    }

    @Override
    public List<T> selectAll() throws SQLException{
        return entities;
    }

    @Override
    public List<T> selectAll(String value, String operator, String column) throws SQLException{
        System.out.println("select - args: val:"+value+" operator: "+operator + " column: "+column);
        return entities;
    }

    @Override
    public T create(T entity) throws SQLException {
        return entity;
    }

    @Override
    public void delete(int id) throws SQLException {
        System.out.println("deleted item, id: "+ id);
    }

    //todo: value string, value date, value double, value boolean
    @Override
    public T update(String column, int value, int id) throws SQLException {
        return entities.getFirst();
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, T entity) throws SQLException {

    }

    @Override
    protected List<T> hydrate(ResultSet res) throws SQLException {
        return List.of();
    }


    @Override
    public void beginTransaction() throws SQLException {
        System.out.println("beginning dummy transaction");
    }

    @Override
    public void commit() throws SQLException {
        System.out.println("ending dummy transaction");
    }

    @Override
    public void rollback() throws SQLException {
        System.out.println("rolling back on dummy transaction");
    }

}
