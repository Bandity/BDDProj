package Test;

import Connections.Jdbc.PostgresConnection;
import Dao.DaoException;
import Dao.Jdbc.CategorieDaoImpl;

import Model.Categorie;
import Model.Entity;

import java.sql.Connection;
import java.util.Collection;

public class CategorieDaoJdbcTest {

    public void findAllTest(Connection connection){
        try {
            Collection<Entity> categories;
            categories =new CategorieDaoImpl(connection).findAll();
            for (Entity e: categories) {
                Categorie categorie = (Categorie) e;
                System.out.println( categorie.getId() + " | " + categorie.getLibelle() + " |");
           }
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void deleteTest(Connection connection, int id){
        try {
            Categorie categorie = (Categorie) new CategorieDaoImpl(connection).findById(id);
            new CategorieDaoImpl(connection).delete(categorie);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void creatTest(Connection connection, int id ,String libelle){
        Categorie categorie = new Categorie(id,libelle);
        try {
            new CategorieDaoImpl(connection).create(categorie);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void updateTest(Connection connection, int id ,String libelle){
        Categorie categorie = new Categorie(id,libelle);
        try {
            new CategorieDaoImpl(connection).update(categorie);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void allCategoriesTests(Connection connection){
        new CategorieDaoJdbcTest().findAllTest(connection);
        new CategorieDaoJdbcTest().creatTest(connection, 310, "SWAT");
        new CategorieDaoJdbcTest().updateTest(connection,310, "IDK");
        new CategorieDaoJdbcTest().deleteTest(connection, 310);
    }

    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        new CategorieDaoJdbcTest().allCategoriesTests(connection);
    }
}
