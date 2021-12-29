package Test;

import Connections.Jdbc.PostgresConnection;
import Dao.DaoException;
import Dao.Jdbc.ModeleDaoImpl;
import Model.Entity;
import Model.Modele;

import java.sql.Connection;
import java.util.Collection;

public class ModeleDaoJdbcTest {


    public void findAllTest(Connection connection){
        try {
            Collection<Entity> factures;
            factures =new ModeleDaoImpl(connection).findAll();
            for (Entity e: factures) {
                Modele modele = (Modele) e;
                System.out.println(modele.getId() + " | Determinations : " + modele.getDeterminations()+ " | Puissance Fiscale : " + modele.getPuissanceFiscale() + " |");
            }
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void deleteTest(Connection connection, int id){
        try {
            Modele modele = (Modele) new ModeleDaoImpl(connection).findById(id);
            new ModeleDaoImpl(connection).delete(modele);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void creatTest(Connection connection, int id ,String determinations, int puissanceFiscale){
        Modele modele = new Modele(id,determinations,puissanceFiscale);
        try {
            new ModeleDaoImpl(connection).create(modele);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void updateTest(Connection connection, int id ,String determinations, int puissanceFiscale){
        Modele modele = new Modele(id,determinations,puissanceFiscale);
        try {
            new ModeleDaoImpl(connection).update(modele);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void allModeleTests(Connection connection){
        new ModeleDaoJdbcTest().findAllTest(connection);
        new ModeleDaoJdbcTest().creatTest(connection, 69, "Porche 911", 12);
        new ModeleDaoJdbcTest().updateTest(connection, 69, "CAMARO ZL1", 10);
        new ModeleDaoJdbcTest().deleteTest(connection, 69);
    }

    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        new ModeleDaoJdbcTest().allModeleTests(connection);
    }
}

