package Dao.Jdbc;

import Dao.DaoException;
import Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class ContractDaoImpl extends JdbcDao{
    private ClientDaoImpl clientDao;
    private VehiculeDaoImpl vehiculeDao;
    private AgenceDaoImpl agenceDao;
    public ContractDaoImpl(Connection connection) {
        super(connection);
        this.clientDao = new ClientDaoImpl(connection);
        this.agenceDao = new AgenceDaoImpl(connection);
        this.vehiculeDao = new VehiculeDaoImpl(connection);
    }
    public Entity locationDeVoitureClient(int id, Date date) throws  DaoException{
        ContractDaoImpl contrat = null;
        String sqlReq = "SELECT * FROM contrat";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            //statement.setInt(1, id);
            ResultSet resultSet;
        }catch (SQLException e ){
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> contrats = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Contrat");
            while (resultSet.next()) {
                Contrat contrat = new Contrat();
                contrat.setId(resultSet.getInt("idContrat"));
                contrat.setDateDeRetrait(resultSet.getDate("dateDeRetrait"));
                contrat.setDateDeRetour(resultSet.getDate("dateDeRetour"));
                contrat.setKmRetrait(resultSet.getFloat("kmRetrait"));
                contrat.setKmRetour(resultSet.getFloat("kmRetour"));
                Client client = (Client) new ClientDaoImpl(connection).findById(resultSet.getInt("idClient"));
                Vehicule vehicule = (Vehicule) new VehiculeDaoImpl(connection).findById(resultSet.getString("immatriculation"));
                Agence agence = (Agence) new AgenceDaoImpl(connection).findById(resultSet.getInt("idAgence"));
                contrat.setClient(client);
                contrat.setVehicule(vehicule);
                contrat.setAgence(agence);
                contrats.add(contrat);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return contrats;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Contrat contrat = null;
        String sqlReq = "SELECT * FROM Contrat WHERE  idContrat= ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                contrat = new Contrat();
                contrat.setId(resultSet.getInt("idContrat"));
                contrat.setDateDeRetrait(resultSet.getDate("dateDeRetrait"));
                contrat.setDateDeRetour(resultSet.getDate("dateDeRetour"));
                contrat.setKmRetrait(resultSet.getInt("kmRetrait"));
                contrat.setKmRetour(resultSet.getInt("kmRetour"));
                Client client = (Client) clientDao.findById(resultSet.getInt("idClient"));
                Vehicule vehicule = (Vehicule) vehiculeDao.findById(resultSet.getString("immatriculation"));
                Agence agence = (Agence) agenceDao.findById(resultSet.getInt("idAgence"));
                contrat.setClient(client);
                contrat.setVehicule(vehicule);
                contrat.setAgence(agence);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contrat;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Contrat contrat = (Contrat) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into Contrat(idContrat ,dateDeRetrait, dateDeRetour,kmRetrait,kmRetour,idClient,immatriculation,idAgence) values (?, ?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, contrat.getId());
            statement.setDate(2,   new java.sql.Date(contrat.getDateDeRetrait().getTime()));
            statement.setDate(3,  new java.sql.Date(contrat.getDateDeRetour().getTime()));
            statement.setFloat(4, contrat.getKmRetrait());
            statement.setFloat(5, contrat.getKmRetour());
            statement.setInt(6, contrat.getClient().getId());
            statement.setString(7, contrat.getVehicule().getImmatriculation());
            statement.setInt(8, contrat.getAgence().getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Created");
            System.out.println(contrat.getId() +" | Date De Retrait : "+ new java.sql.Date(contrat.getDateDeRetrait().getTime())+ " | Date De Retour : "+new java.sql.Date(contrat.getDateDeRetour().getTime())+" | Km Retrait : " + contrat.getKmRetrait()+ " | Km Retour : " + contrat.getKmRetour() + " | Client ID : " + contrat.getClient().getId()+" | Immaticulation : " +contrat.getVehicule().getImmatriculation() + " | Agence ID : "+ contrat.getAgence().getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Contrat contrat = (Contrat) entity;
        PreparedStatement statement = null;
        String sqlReq = "update Contrat set dateDeRetrait=?, dateDeRetour=?,kmRetrait=?,kmRetour=?,idClient=?,immatriculation=?,idAgence = ? WHERE idContrat = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setDate(1, new java.sql.Date(contrat.getDateDeRetrait().getTime()));
            statement.setDate(2, new java.sql.Date(contrat.getDateDeRetour().getTime()));
            statement.setFloat(3, contrat.getKmRetrait());
            statement.setFloat(4, contrat.getKmRetour());
            statement.setInt(5, contrat.getClient().getId());
            statement.setString(6, contrat.getVehicule().getImmatriculation());
            statement.setInt(7, contrat.getAgence().getId());
            statement.setInt(8,contrat.getId());
            int res = statement.executeUpdate();
            if( res ==1) {
                System.out.println("");
                System.out.println("Updated");
                System.out.println(contrat.getId() +" | Date De Retrait : "+ new java.sql.Date(contrat.getDateDeRetrait().getTime())+ " | Date De Retour : "+new java.sql.Date(contrat.getDateDeRetour().getDay())+" | Km Retrait : " + contrat.getKmRetrait()+ " | Km Retour : " + contrat.getKmRetour() + " | Client ID : " + contrat.getClient().getId()+" | Immaticulation : " +contrat.getVehicule().getImmatriculation() + " | Agence ID : "+ contrat.getAgence().getId());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Contrat contrat = (Contrat) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Contrat where idContrat = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, contrat.getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted.... ");
            System.out.println(contrat.getId() +" | Date De Retrait : "+ contrat.getDateDeRetrait()+ " | Date De Retour : "+contrat.getDateDeRetour()+" | Km Retrait : " + contrat.getKmRetrait()+ " | Km Retour : " + contrat.getKmRetour() + " | Client ID : " + contrat.getClient().getId()+" | Immaticulation : " +contrat.getVehicule().getImmatriculation() + " | Agence ID : "+ contrat.getAgence().getId());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
