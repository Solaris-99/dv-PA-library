package business;

import dao.Dao;
import dto.Entity;
import helpers.Status;

import java.sql.SQLException;
import java.util.List;

public abstract class Business<T extends Dao<E>, E extends Entity>{
    protected T dao;

    public Business(T dao){
        this.dao = dao;
    }


    public E select(int value, String operator, String column){
        try{
            return this.dao.select(value, operator, column);
        }
        catch (SQLException e){
            System.out.println("Error fetching item");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public E select(String value, String operator, String column){
        try{
            return this.dao.select(value, operator, column);
        }
        catch (SQLException e){
            System.out.println("Error fetching item");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<E> selectAll(String value, String operator, String column, int page){
        try{
            return this.dao.selectAll(value, operator, column, page);
        }
        catch (SQLException e){
            System.out.println("Error fetching items");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<E> selectAll(int value, String operator, String column, int page){
        try{
            return this.dao.selectAll(value,operator,column, page);
        }
        catch (SQLException e){
            System.out.println("Error fetching items");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<E> selectAll(int page){
        try{
            return this.dao.selectAll(page);
        }
        catch (SQLException e){
            System.out.println("Error fetching all items");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public E create(E entity){
        try{
            return this.dao.create(entity);
        }
        catch(SQLException e){
            System.out.println("Error creating item");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void delete(int id){
        try{
            this.dao.delete(id);
        }
        catch(SQLException e){
            System.out.println("Error deleting item");
            System.out.println(e.getMessage());
        }
    }

    public E update(String column, int value, int id){
        try{
            return this.dao.update(column, value, id);
        }
        catch (SQLException e){
            System.out.println("Error updating item");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int getTotalPages(){
        try{
            return (int) Math.ceil((double) dao.getItemCount() / Status.getInstance().getItemsPerPage());
        }
        catch (SQLException e){
            System.out.println("error getting number of pages");
            System.out.println(e.getMessage());
        }
        return -1;
    }


}
