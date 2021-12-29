package Test;

import Connections.Jdbc.PostgresConnection;
import Dao.DaoException;
import Dao.Jdbc.*;
import Model.*;

import java.sql.Connection;
import java.util.Collection;

public class FactureDaoJdbcTest {

    public void findAllTest(Connection connection){
        try {
            Collection<Entity> factures;
            factures =new FactureDaoImpl(connection).findAll();
            for (Entity e: factures) {
                Facture facture = (Facture) e;
                System.out.println(facture.getId() + " | Montant : " + facture.getMontant() + " | Contrat ID : " + facture.getContrat().getId() +" |");
            }
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void deleteTest(Connection connection, int id){
        try {
            Facture facture = (Facture) new FactureDaoImpl(connection).findById(id);
            new FactureDaoImpl(connection).delete(facture);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void creatTest(Connection connection, int id , float montant, Contrat contrat){
        Facture facture = new Facture(id,montant,contrat);
        try {
            new FactureDaoImpl(connection).create(facture);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void updateTest(Connection connection, int id , float montant, Contrat contrat){
        Facture facture = new Facture(id,montant,contrat);
        try {
            new FactureDaoImpl(connection).update(facture);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void allFactureTests(Connection connection){
        try {
            new FactureDaoJdbcTest().findAllTest(connection);
            new FactureDaoJdbcTest().creatTest(connection, 66, 34789, (Contrat) new ContractDaoImpl(connection).findById(5));
            new FactureDaoJdbcTest().updateTest(connection, 66, 123123, (Contrat) new ContractDaoImpl(connection).findById(5));
            new FactureDaoJdbcTest().deleteTest(connection, 66);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        new FactureDaoJdbcTest().allFactureTests(connection);
    }
}
