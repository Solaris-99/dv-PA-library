package dao;

import dto.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDao extends Dao<User>{

    public UserDao(){
        super();
        this.tableName = "USER";
        this.cols = Arrays.asList("id","name","surname","email","password","dni");
    }

    @Override
    protected void setInsertParameters(PreparedStatement stmt, User entity) throws SQLException {
        stmt.setString(1, entity.name());
        stmt.setString(2, entity.surname());
        stmt.setString(3, entity.email());
        stmt.setString(4, entity.password());
        stmt.setInt(5, entity.DNI());
    }


    @Override
    protected List<User> hydrate(ResultSet res) throws SQLException {
        List<User> users = new ArrayList<>();
        while(res.next()){
            User user = new User(
                    res.getInt("id"),
                    res.getString("name"),
                    res.getString("surname"),
                    res.getString("email"),
                    res.getString("password"),
                    res.getInt("DNI")
            );
            users.add(user);
        }
        return users;
    }
}
