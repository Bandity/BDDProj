package Test;

import Connections.Jdbc.PostgresConnection;

import java.sql.Connection;

public class ModeDaoJdbcTest {

    public void findAllTest(Connection connection){

    }
    public void deleteTest(Connection connection, int id){

    }
    public void creatTest(Connection connection, int id, String nom,int nombreHabitants){

    }

    public void updateTest(Connection connection){

    }

    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        new VilleDaoJdbcTest().findAllTest(connection);/*
        new VilleDaoJdbcTest().creatTest(connection);
        new VilleDaoJdbcTest().updateTest(connection);
        new VilleDaoJdbcTest().deleteTest(connection);*/

    }
}
