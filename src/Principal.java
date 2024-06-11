import database.ConnectionFactory;
import database.dao.UsuarioDAO;
import database.model.UsuarioModel;

import java.sql.Connection;
import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Connection conexao = ConnectionFactory.getConnection
                    ("localhost", "5432", "usuarios", "postgres", "mysecretpassword");
            if (conexao != null){
                System.out.println("conectado");
                UsuarioDAO usuarioDao = new UsuarioDAO(conexao);
                System.out.println(usuarioDao.deleteById(6));
                System.out.println(usuarioDao.updateById(3));
                usuarioDao.selectAll().forEach(System.out::println);
                conexao.close();
            } else {
                System.out.println("falha");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
