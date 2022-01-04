package Test;

import Connections.Jdbc.PostgresConnection;
import Dao.DaoException;
import Dao.Jdbc.*;
import Model.*;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

public class VehiculeDaoJdbcTest {


    public void findAllTest(Connection connection){
        try {
            Collection<Entity> vehicules;
            vehicules =new VehiculeDaoImpl(connection).findAll();
            for (Entity e: vehicules) {
                Vehicule vehicule = (Vehicule) e;
                System.out.println(vehicule.getImmatriculation() + " | Date Mise En Circulation : "+ new java.sql.Date(vehicule.getMiseEnCirculation().getTime()) + " | État : "+vehicule.getEtat()+" | Kilometres : " + vehicule.getNbKilometres()+ " | Prix Par Jour De Location : "+vehicule.getPrixParJourDeLocation() +" | Marque ID : " + vehicule.getMarque().getId() + " | Modele ID : " + vehicule.getModele().getId() + " | Categorie ID : "+vehicule.getCategorie().getId()+" | Type ID : " + vehicule.getType().getId() + " | Agence ID : " + vehicule.getAgence().getId() +" |");
            }
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void deleteTest(Connection connection, String immatriculation){
        try {
            Vehicule vehicule = (Vehicule) new VehiculeDaoImpl(connection).findById(immatriculation);
            new VehiculeDaoImpl(connection).delete(vehicule);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void creatTest(Connection connection, String immatriculation, Date miseEnCirculation, String etat, float nbKilometres, float prixParJourDeLocation, Marque marque,Modele modele,Categorie categorie,Types type, Agence agence) {
        Vehicule vehicule = new Vehicule(immatriculation,miseEnCirculation,etat,nbKilometres, prixParJourDeLocation, marque, modele, categorie, type, agence);
    try {
            new VehiculeDaoImpl(connection).create(vehicule);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void updateTest(Connection connection, String immatriculation, Date miseEnCirculation, String etat, float nbKilometres, float prixParJourDeLocation, Marque marque,Modele modele,Categorie categorie,Types type, Agence agence) {
        Vehicule vehicule = new Vehicule(immatriculation,miseEnCirculation,etat,nbKilometres, prixParJourDeLocation, marque, modele, categorie, type, agence);
        try {
            new VehiculeDaoImpl(connection).update(vehicule);
        }catch (DaoException e){
            e.printStackTrace();
        }
    }

    public void vehiculesParMarqueTest(Connection connection){
        try {
            System.out.println("");
            System.out.println("Vehicules par Marque.....");
            new VehiculeDaoImpl(connection).vehiculesParMarque();
        }catch (DaoException e){
            e.printStackTrace();
        }
    }
    public void lastLocationTest(Connection connection){
        try {
            System.out.println("");
            System.out.println("Dernier Vehicule loué......");
            new VehiculeDaoImpl(connection).lastLocation();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
    public void allVehiculeTests(Connection connection){
        try {
            new VehiculeDaoJdbcTest().findAllTest(connection);
            new VehiculeDaoJdbcTest().creatTest(connection, "477-AE-307Q", (Date) new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime(),"neuf",0.0f,5000.12f,(Marque) new MarqueDaoImpl(connection).findById(7),(Modele) new ModeleDaoImpl(connection).findById(1), (Categorie) new CategorieDaoImpl(connection).findById(4),(Types) new TypesDaoImpl(connection).findById(1),(Agence) new AgenceDaoImpl(connection).findById(7));
            new VehiculeDaoJdbcTest().updateTest(connection, "477-AE-307Q", (Date) new GregorianCalendar(2011, Calendar.FEBRUARY, 14).getTime(),"usée",11980.98f,5000.12f,(Marque) new MarqueDaoImpl(connection).findById(7),(Modele) new ModeleDaoImpl(connection).findById(1), (Categorie) new CategorieDaoImpl(connection).findById(4),(Types) new TypesDaoImpl(connection).findById(1),(Agence) new AgenceDaoImpl(connection).findById(7));
            new VehiculeDaoJdbcTest().deleteTest(connection, "477-AE-307Q");
            new VehiculeDaoJdbcTest().vehiculesParMarqueTest(connection);
            new VehiculeDaoJdbcTest().lastLocationTest(connection);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        new VehiculeDaoJdbcTest().allVehiculeTests(connection);
    }
}

