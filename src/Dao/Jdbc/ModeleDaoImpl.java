package Dao.Jdbc;

import Dao.DaoException;
import Model.Entity;
import Model.Modele;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ModeleDaoImpl extends JdbcDao{
    public ModeleDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> modeles = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Modele");
            while (resultSet.next()) {
                Modele modele = new Modele();
                modele.setId(resultSet.getInt("idModele"));
                modele.setDeterminations(resultSet.getString("determinations"));
                modele.setPuissacenFiscale(resultSet.getInt("puissanceFiscale"));
                modeles.add(modele);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return modeles;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Modele modele = null;
        String sqlReq = "SELECT * FROM Modele WHERE  idModele= ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                modele = new Modele();
                modele.setId(resultSet.getInt("idModele"));
                modele.setDeterminations(resultSet.getString("determinations"));
                modele.setPuissacenFiscale(resultSet.getInt("puissanceFiscale"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return modele;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Modele modele = (Modele) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into Modele(idModele ,determinations, puissanceFiscale) values (?, ?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, modele.getId());
            statement.setString(2, modele.getDeterminations());
            statement.setInt(3, modele.getPuissanceFiscale());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Created");
            System.out.println(modele.getId() + " | Determinations : " + modele.getDeterminations()+ " | Puissance Fiscale : " + modele.getPuissanceFiscale() +" |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Modele modele = (Modele) entity;
        PreparedStatement statement = null;
        String sqlReq = "update Modele set determinations=?, puissanceFiscale = ? WHERE idModele = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, modele.getDeterminations());
            statement.setInt(2, modele.getPuissanceFiscale());
            statement.setInt(3, modele.getId());
            int res = statement.executeUpdate();
            if( res ==1) {
                System.out.println("");
                System.out.println("Updated");
                System.out.println(modele.getId() + " | Determinations : " + modele.getDeterminations()+ " | Puissance Fiscale : " + modele.getPuissanceFiscale() +" |");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Modele modele = (Modele) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Modele where idModele = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, modele.getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted.... ");
            System.out.println(modele.getId() + " | Determinations : " + modele.getDeterminations()+ " | Puissance Fiscale : " + modele.getPuissanceFiscale() +" |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
