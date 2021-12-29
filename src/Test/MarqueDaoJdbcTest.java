package Test;

import Connections.Jdbc.PostgresConnection;
import Dao.DaoException;
import Dao.Jdbc.ContractDaoImpl;
import Dao.Jdbc.MarqueDaoImpl;
import Model.Contrat;
import Model.Entity;
import Model.Marque;

import java.sql.Connection;
import java.util.Collection;

public class MarqueDaoJdbcTest {

    public void findAllTest(Connection connection){
        try {
            Collection<Entity> factures;
            factures =new MarqueDaoImpl(connection).findAll();
            for (Entity e: factures) {
                Marque marque = (Marque) e;
                System.out.println(marque.getId() + " | Name : " + marque.getNom()+ " |");
            }
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void deleteTest(Connection connection, int id){
        try {
            Marque marque = (Marque) new MarqueDaoImpl(connection).findById(id);
            new MarqueDaoImpl(connection).delete(marque);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void creatTest(Connection connection, int id ,String nom){
        Marque marque = new Marque(id,nom);
        try {
            new MarqueDaoImpl(connection).create(marque);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void updateTest(Connection connection, int id ,String nom){
        Marque marque = new Marque(id,nom);
        try {
            new MarqueDaoImpl(connection).update(marque);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void allMarqueTests(Connection connection){
        new MarqueDaoJdbcTest().findAllTest(connection);
        new MarqueDaoJdbcTest().creatTest(connection, 66, "Smart");
        new MarqueDaoJdbcTest().updateTest(connection, 66, "Chevrolet");
        new MarqueDaoJdbcTest().deleteTest(connection, 66);
    }

    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        new MarqueDaoJdbcTest().allMarqueTests(connection);
    }
}

