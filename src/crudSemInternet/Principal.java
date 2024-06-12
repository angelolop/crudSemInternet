package crudSemInternet;

import crudSemInternet.application.service.UsuarioService;
import crudSemInternet.infrasctuture.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) {
        try {
            Connection conexao = ConnectionFactory.getConnection
                    ("localhost", "5432", "usuarios", "postgres", "mysecretpassword");
            if (conexao != null){
                System.out.println("conectado");
                UsuarioService usuarioService = new UsuarioService(conexao);
                System.out.println(usuarioService.deleteById(6));
                System.out.println(usuarioService.updateById(3));
                usuarioService.selectAll().forEach(System.out::println);
                conexao.close();
            } else {
                System.out.println("falha");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
