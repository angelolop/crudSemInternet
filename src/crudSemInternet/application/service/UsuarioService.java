package crudSemInternet.application.service;

import crudSemInternet.domain.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioService {

    private String comandoSelectAll = "select * from usuarios";
    private PreparedStatement pstSelectAll;
    private String comandoInsert = "insert into usuarios(usuario,senha) values (?,?)";
    private PreparedStatement pstInsert;
    private String comandoDelete = "delete from usuarios where id = ?";
    private PreparedStatement pstDelete;
    private String comandoUpdate = "update usuarios set usuario = ? where id = ?";
    private PreparedStatement pstUpdate;

    public UsuarioService(final Connection conexao) throws SQLException {
        pstSelectAll = conexao.prepareStatement(comandoSelectAll);
        pstInsert = conexao.prepareStatement(comandoInsert);
        pstDelete = conexao.prepareStatement(comandoDelete);
        pstUpdate = conexao.prepareStatement(comandoUpdate);
    }

    public int insert(Usuario usuario) throws SQLException {
        pstInsert.clearParameters();
        pstInsert.setString(1, usuario.getUsuario());
        pstInsert.setString(2, usuario.getSenha());
        return pstInsert.executeUpdate();
    }

    public ArrayList<Usuario> selectAll() throws SQLException {
        ArrayList<Usuario> listaLocal = new ArrayList<>();
        ResultSet resultSet = pstSelectAll.executeQuery();
        while(resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(resultSet.getInt("id"));
            usuario.setUsuario(resultSet.getString("usuario"));
            usuario.setSenha(resultSet.getString("senha"));
            listaLocal.add(usuario);
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
