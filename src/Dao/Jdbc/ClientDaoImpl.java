package Dao.Jdbc;

import Dao.DaoException;
import Model.Agence;
import Model.Client;
import Model.Entity;
import Model.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ClientDaoImpl extends JdbcDao{
    private VilleDaoImpl villeDao;

    public ClientDaoImpl(Connection connection) {
        super(connection);
        villeDao = new VilleDaoImpl(connection);
    }
    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Client");
            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getInt("idClient"));
                client.setNom(resultSet.getString("nomClient"));
                client.setAddress(resultSet.getString("addressClient"));
                client.setCodePostal(resultSet.getInt("codePostalClient"));
                Ville ville = (Ville) new VilleDaoImpl(connection).findById(resultSet.getInt("idVille"));
                client.setVille(ville);
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return clients;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Client client = null;
        String sqlReq = "SELECT * FROM Client WHERE  idClient= ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getInt("idClient"));
                client.setNom(resultSet.getString("nomClient"));
                client.setAddress(resultSet.getString("addressClient"));
                client.setCodePostal(resultSet.getInt("codePostalClient"));
                Ville ville = (Ville) villeDao.findById(resultSet.getInt("idVille"));
                client.setVille(ville);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return client;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into Client(idClient ,nomClient, addressClient,codePostalClient,idVille) values (?, ?,?,?,?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, client.getId());
            statement.setString(2, client.getNom());
            statement.setString(3, client.getAddress());
            statement.setInt(4, client.getCodePostal());
            statement.setInt(5, client.getVille().getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Created");
            System.out.println( client.getId() + " | Name : " + client.getNom()+ " | Address : " + client.getAddress() + " | Zip Code : " + client.getCodePostal()+" | Ville Name : " + client.getVille().getNom()+" |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement statement = null;
        String sqlReq = "update Client set nomClient=?, addressClient=?,codePostalClient = ?,idVille = ? WHERE idClient = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, client.getNom());
            statement.setString(2, client.getAddress());
            statement.setInt(3, client.getCodePostal());
            statement.setInt(4, client.getVille().getId());
            statement.setInt(5, client.getId());
            int res = statement.executeUpdate();
            if( res ==1) {
                System.out.println("");
                System.out.println("Updated");
                System.out.println( client.getId() + " | Name : " + client.getNom()+ " | Address : " + client.getAddress() + " | Zip Code : " + client.getCodePostal()+" | Ville Name : " + client.getVille().getNom()+" |");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Client where idClient = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, client.getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted.... ");
            System.out.println( client.getId() + " | Name : " + client.getNom()+ " | Address : " + client.getAddress() + " | Zip Code : " + client.getCodePostal()+" | Ville Name : " + client.getVille().getNom()+" |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }}
