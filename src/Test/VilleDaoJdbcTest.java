package Test;

import Connections.Jdbc.PostgresConnection;
import Dao.DaoException;
import Dao.Jdbc.VilleDaoImpl;
import Model.Entity;
import Model.Ville;

import java.sql.Connection;
import java.util.Collection;

public class VilleDaoJdbcTest {
    public void findAllTest(Connection connection){
        try {
            Collection<Entity> viles;
            viles =new VilleDaoImpl(connection).findAll();
            for (Entity e: viles) {
                Ville ville = (Ville) e;
                System.out.println(ville.getId() + " | Libelle : " + ville.getNom()+ " | Nombre d'habitants : " + ville.getNombreHabitants()+" |");
            }
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void deleteTest(Connection connection, int id){
        try {
            Ville ville = (Ville) new VilleDaoImpl(connection).findById(id);
            new VilleDaoImpl(connection).delete(ville);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void creatTest(Connection connection, int id ,String nom, int nombreHabitants){
        Ville ville = new Ville(id,nom,nombreHabitants);
        try {
            new VilleDaoImpl(connection).create(ville);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void updateTest(Connection connection, int id ,String nom, int nombreHabitants){
        Ville ville = new Ville(id,nom,nombreHabitants);
        try {
            new VilleDaoImpl(connection).update(ville);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void allVilleTests(Connection connection){
        new VilleDaoJdbcTest().findAllTest(connection);
        new VilleDaoJdbcTest().creatTest(connection, 777, "California", 39519000);
        new VilleDaoJdbcTest().updateTest(connection, 777, "Florida", 21480000);
        new VilleDaoJdbcTest().deleteTest(connection, 777);
    }

    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        new VilleDaoJdbcTest().allVilleTests(connection);
    }
}

