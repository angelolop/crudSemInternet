package crudSemInternet;

import crudSemInternet.application.service.UserService;
import crudSemInternet.infrasctuture.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionFactory.getConnection
                    ("localhost", "5432", "usuarios", "postgres", "mysecretpassword");
            if (connection != null){
                System.out.println("Connected");
                UserService userService = new UserService(connection);
                System.out.println(userService.deleteById(6));
                System.out.println(userService.updateById(3, "update"));
                userService.selectAll().forEach(System.out::println);
                connection.close();
            } else {
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
