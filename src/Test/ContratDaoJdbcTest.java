package Test;

import Connections.Jdbc.PostgresConnection;
import Dao.DaoException;
import Dao.Jdbc.*;
import Model.*;

import java.sql.Connection;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

public class ContratDaoJdbcTest {

    public void findAllTest(Connection connection){
        try {
            Collection<Entity> contrats;
            contrats =new ContractDaoImpl(connection).findAll();
            for (Entity e: contrats) {
                Contrat contrat = (Contrat) e;
                System.out.println(contrat.getId() +" | Date De Retrait : "+ contrat.getDateDeRetrait()+ " | Date De Retour : "+contrat.getDateDeRetour()+" | Km Retrait : " + contrat.getKmRetrait()+ " | Km Retour : " + contrat.getKmRetour() + " | Client ID : " + contrat.getClient().getId()+" | Immaticulation : " +contrat.getVehicule().getImmatriculation() + " | Agence ID : "+ contrat.getAgence().getId());
            }
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void deleteTest(Connection connection, int id){
        try {
            Contrat contrat = (Contrat) new ContractDaoImpl(connection).findById(id);
            new ContractDaoImpl(connection).delete(contrat);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void creatTest(Connection connection, int id , Date dateDeRetrait, Date dateDeRetour, float kmRetrait, float kmRetour, Client client, Vehicule vehicule, Agence agence){
        Contrat contrat = new Contrat(id,dateDeRetrait,dateDeRetour,kmRetrait,kmRetour,client,vehicule,agence);
        try {
            new ContractDaoImpl(connection).create(contrat);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void updateTest(Connection connection, int id , Date dateDeRetrait, Date dateDeRetour, float kmRetrait, float kmRetour, Client client, Vehicule vehicule, Agence agence){
        Contrat contrat = new Contrat(id,dateDeRetrait,dateDeRetour,kmRetrait,kmRetour,client,vehicule,agence);
        try {
            new ContractDaoImpl(connection).update(contrat);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void allContratTests(Connection connection){
        try {
            new ContratDaoJdbcTest().findAllTest(connection);
            new ContratDaoJdbcTest().creatTest(connection, 78, (Date) new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime(), (Date) new GregorianCalendar(2014, Calendar.SEPTEMBER, 7).getTime(), 8584, 10534, (Client) new ClientDaoImpl(connection).findById(12),(Vehicule) new VehiculeDaoImpl(connection).findById("397-73-5066"),(Agence) new AgenceDaoImpl(connection).findById(1));
            new ContratDaoJdbcTest().updateTest(connection, 78, (Date) new GregorianCalendar(2004, Calendar.JANUARY, 1).getTime(), (Date) new GregorianCalendar(2004, Calendar.DECEMBER, 31).getTime(), 1584, 12034, (Client) new ClientDaoImpl(connection).findById(12),(Vehicule) new VehiculeDaoImpl(connection).findById("397-73-5066"),(Agence) new AgenceDaoImpl(connection).findById(1));
            new ContratDaoJdbcTest().deleteTest(connection, 78);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        new ContratDaoJdbcTest().allContratTests(connection);
    }
}
