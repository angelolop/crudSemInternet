package crudSemInternet.application.service;

import crudSemInternet.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

    private final String selectAll = "select * from users";
    private PreparedStatement pstSelectAll;
    private final String insert = "insert into users(username,password) values (?,?)";
    private PreparedStatement pstInsert;
    private final String delete = "delete from users where id = ?";
    private PreparedStatement pstDelete;
    private final String update = "update users set username = ? where id = ?";
    private PreparedStatement pstUpdate;

    public UserService(final Connection conexao) throws SQLException {
        pstSelectAll = conexao.prepareStatement(selectAll);
        pstInsert = conexao.prepareStatement(insert);
        pstDelete = conexao.prepareStatement(delete);
        pstUpdate = conexao.prepareStatement(update);
    }

    public int insert(User user) throws SQLException {
        pstInsert.clearParameters();
        pstInsert.setString(1, user.getUsername());
        pstInsert.setString(2, user.getPassword());
        return pstInsert.executeUpdate();
    }

    public ArrayList<User> selectAll() throws SQLException {
        ArrayList<User> userList = new ArrayList<>();
        ResultSet resultSet = pstSelectAll.executeQuery();
        while(resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            userList.add(user);
        }

        return userList;
    }

    public int deleteById(int id) throws SQLException {
        pstDelete.setInt(1, id);
        return pstDelete.executeUpdate();
    }

    public int updateById(int id, String newName) throws SQLException {
        pstUpdate.setString(1, newName);
        pstUpdate.setInt(2, id);
        return pstUpdate.executeUpdate();
    }
}
