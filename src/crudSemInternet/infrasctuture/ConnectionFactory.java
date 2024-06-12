package crudSemInternet.infrasctuture;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection(final String enderecoIp,
                                           final String enderecoPorta,
                                           final String nomeBanco,
                                           final String usuario,
                                           final String senha) throws SQLException {
        return DriverManager.getConnection
                ("jdbc:postgresql://" + enderecoIp + ":"
                        + enderecoPorta + "/" + nomeBanco,
                        usuario, senha);
    }
}
