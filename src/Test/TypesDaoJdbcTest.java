package Test;

import Connections.Jdbc.PostgresConnection;
import Dao.DaoException;
import Dao.Jdbc.CategorieDaoImpl;
import Dao.Jdbc.TypesDaoImpl;
import Model.Entity;
import Model.Types;

import java.sql.Connection;
import java.util.Collection;

public class TypesDaoJdbcTest {

    public void findAllTest(Connection connection){
        try {
            Collection<Entity> types;
            types =new TypesDaoImpl(connection).findAll();
            for (Entity e: types) {
                Types type = (Types) e;
                System.out.println(type.getId() + " | Libelle : " + type.getLibelle()+ " |");
            }
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void deleteTest(Connection connection, int id){
        try {
            Types type = (Types) new TypesDaoImpl(connection).findById(id);
            new TypesDaoImpl(connection).delete(type);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void creatTest(Connection connection, int id ,String libelleType){
        Types type = new Types(id,libelleType);
        try {
            new TypesDaoImpl(connection).create(type);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void updateTest(Connection connection, int id ,String libelleType){
        Types type = new Types(id,libelleType);
        try {
            new TypesDaoImpl(connection).update(type);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void chiffreAffairesTest(Connection connection){
        try {
            System.out.println("");
            System.out.println("Le chiffre d’affaire par type.....");
            new TypesDaoImpl(connection).chiffreAffaires();
        }
        catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void allTypesTests(Connection connection){
        new TypesDaoJdbcTest().findAllTest(connection);
        new TypesDaoJdbcTest().creatTest(connection, 312, "Kerosene");
        new TypesDaoJdbcTest().updateTest(connection, 312, "Gasoline 98");
        new TypesDaoJdbcTest().deleteTest(connection, 312);
        new TypesDaoJdbcTest().chiffreAffairesTest(connection);
    }

    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        new TypesDaoJdbcTest().allTypesTests(connection);
    }
}

