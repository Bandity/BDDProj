package Test;

import Connections.Jdbc.PostgresConnection;
import Dao.DaoException;
import Dao.Jdbc.AgenceDaoImpl;
import Dao.Jdbc.ClientDaoImpl;
import Dao.Jdbc.VilleDaoImpl;
import Model.Agence;
import Model.Client;
import Model.Entity;
import Model.Ville;

import java.sql.Connection;
import java.util.Collection;

public class ClientDaoJdbcTest {
    public void findAllTest(Connection connection){
        try {
            Collection<Entity> clients;
            clients =new ClientDaoImpl(connection).findAll();
            for (Entity e: clients) {
                Client client = (Client) e;
                System.out.println( client.getId() + " | Name : " + client.getNom()+ " | Address : " + client.getAddress() + " | Zip Code : " + " | Ville Name : " + client.getVille().getNom()+" |");
            }
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void deleteTest(Connection connection, int id){
        try {
            Client client = (Client) new ClientDaoImpl(connection).findById(id);
            new ClientDaoImpl(connection).delete(client);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void creatTest(Connection connection, int id , String name, String address, int zipcode, Ville ville){
        Client client = new Client(id,name,address,zipcode,ville);
        try {
            new ClientDaoImpl(connection).create(client);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void updateTest(Connection connection, int id , String name, String address, int zipcode, Ville ville){
        Client client = new Client(id,name,address,zipcode,ville);
        try {
            new ClientDaoImpl(connection).update(client);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void clientPlusLocations(Connection connection, Agence entity, int annee){
        try{
            System.out.println("");
            System.out.println("Le(s) client(s) ayant r??alis??(s) le plus de locations pour une agence donn??e et pour une ann??e donn??e........");
            new ClientDaoImpl(connection).clientPlusLocations(entity, annee);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void allClientTests(Connection connection){
        try {
            new ClientDaoJdbcTest().findAllTest(connection);
            new ClientDaoJdbcTest().creatTest(connection, 299, "Rabanadas Santos", "41 Rue des fleures", 85845, (Ville) new VilleDaoImpl(connection).findById(3));
            new ClientDaoJdbcTest().updateTest(connection,299, "Milho Mendes", "32 Rue des ave raras", 85123, (Ville) new VilleDaoImpl(connection).findById(3));
            new ClientDaoJdbcTest().deleteTest(connection, 299);
            new ClientDaoJdbcTest().clientPlusLocations(connection, (Agence) new AgenceDaoImpl(connection).findById(3), 2021);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        new ClientDaoJdbcTest().allClientTests(connection);
    }
}
