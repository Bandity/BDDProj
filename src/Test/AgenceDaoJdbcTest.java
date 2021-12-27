package Test;

import Connections.Jdbc.PostgresConnection;
import Dao.DaoException;
import Dao.Jdbc.AgenceDaoImpl;
import Dao.Jdbc.VilleDaoImpl;
import Model.Agence;
import Model.Entity;
import Model.Ville;

import java.sql.Connection;
import java.util.Collection;

public class AgenceDaoJdbcTest {

    public void findAllTest(Connection connection){
        try {
            Collection<Entity> agences;
            agences =new AgenceDaoImpl(connection).findAll();
            for (Entity e: agences) {
                Agence agence = (Agence) e;
                System.out.println(agence.getId() +" | Nombre Employées :" +agence.getNbEmployes() +" | Ville ID :"+agence.getVille().getId()+" | " + "Ville Name :"+agence.getVille().getNom()+" | "+"Ville Nb Habitants :"+agence.getVille().getNombreHabitants()+" |");
            }
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void deleteTest(Connection connection, int id){
        try {
            Agence agence = (Agence) new AgenceDaoImpl(connection).findById(id);
            new AgenceDaoImpl(connection).delete(agence);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void creatTest(Connection connection, int id ,int nbEmployees, Ville ville){
        Agence agence = new Agence(id,nbEmployees,ville);
        try {
            new AgenceDaoImpl(connection).create(agence);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void updateTest(Connection connection, int id ,int nbEmployees, Ville ville){
        Agence agence = new Agence(id,nbEmployees,ville);
        try {
            new AgenceDaoImpl(connection).update(agence);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void allAgenceTests(Connection connection){
        try {
            new AgenceDaoJdbcTest().findAllTest(connection);
            new AgenceDaoJdbcTest().creatTest(connection, 340, 1000, (Ville) new VilleDaoImpl(connection).findById(1));
            new AgenceDaoJdbcTest().updateTest(connection,340, 100, (Ville) new VilleDaoImpl(connection).findById(1));
            new AgenceDaoJdbcTest().deleteTest(connection, 340);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        new AgenceDaoJdbcTest().allAgenceTests(connection);
    }
}