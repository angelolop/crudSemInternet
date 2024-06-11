package database.dao;

import database.model.UsuarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    private String comandoSelectAll = "select * from usuarios";
    private PreparedStatement pstSelectAll;
    private String comandoInsert = "insert into usuarios(usuario,senha) values (?,?)";
    private PreparedStatement pstInsert;
    private String comandoDelete = "delete from usuarios where id = ?";
    private PreparedStatement pstDelete;
    private String comandoUpdate = "update usuarios set usuario = ? where id = ?";
    private PreparedStatement pstUpdate;

    public UsuarioDAO(final Connection conexao) throws SQLException {
        pstSelectAll = conexao.prepareStatement(comandoSelectAll);
        pstInsert = conexao.prepareStatement(comandoInsert);
        pstDelete = conexao.prepareStatement(comandoDelete);
        pstUpdate = conexao.prepareStatement(comandoUpdate);
    }

    public int insert(UsuarioModel usuarioModel) throws SQLException {
        pstInsert.clearParameters();
        pstInsert.setString(1, usuarioModel.getUsuario());
        pstInsert.setString(2, usuarioModel.getSenha());
        return pstInsert.executeUpdate();
    }

    public ArrayList<UsuarioModel> selectAll() throws SQLException {
        ArrayList<UsuarioModel> listaLocal = new ArrayList<>();
        ResultSet resultSet = pstSelectAll.executeQuery();
        while(resultSet.next()) {
            UsuarioModel usuarioModel = new UsuarioModel();
            usuarioModel.setId(resultSet.getInt("id"));
            usuarioModel.setUsuario(resultSet.getString("usuario"));
            usuarioModel.setSenha(resultSet.getString("senha"));
            listaLocal.add(usuarioModel);
        }

        return listaLocal;
    }

    public int deleteById(int id) throws SQLException {
        pstDelete.setInt(1, id);
        return pstDelete.executeUpdate();
    }

    public int updateById(int id) throws SQLException {
        String update = "update";
        pstUpdate.setString(1, update);
        pstUpdate.setInt(2, id);
        return pstUpdate.executeUpdate();
    }
}
