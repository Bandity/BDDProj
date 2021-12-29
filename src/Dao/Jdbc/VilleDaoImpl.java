package Dao.Jdbc;

import Dao.DaoException;
import Model.Entity;
import Model.Ville;
import Model.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class VilleDaoImpl extends JdbcDao{
    public VilleDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> villes = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Ville");
            while (resultSet.next()) {
                Model.Ville ville = new Ville();
                ville.setId(resultSet.getInt("idVille"));
                ville.setNom(resultSet.getString("nomVille"));
                ville.setNombreHabitants(resultSet.getInt("nombreHabitants"));
                villes.add(ville);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return villes;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Ville ville = null;
        String sqlReq = "SELECT * FROM Ville WHERE  idVille = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                ville = new Ville();
                ville.setId(resultSet.getInt("idVille"));
                ville.setNom(resultSet.getString("nomVille"));
                ville.setNombreHabitants(resultSet.getInt("nombreHabitants"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ville;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Ville ville = (Ville) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into Ville(idVille ,nomVille, nombreHabitants) values (?, ?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, ville.getId());
            statement.setString(2, ville.getNom());
            statement.setInt(3, ville.getNombreHabitants());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Created");
            System.out.println(ville.getId() + " | Libelle : " + ville.getNom()+ " | Nombre d'habitants : " + ville.getNombreHabitants()+" |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Ville ville = (Ville) entity;
        PreparedStatement statement = null;
        String sqlReq = "update Ville set nomVille=?, nombreHabitants=? WHERE idVille = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, ville.getNom());
            statement.setInt(2, ville.getNombreHabitants());
            statement.setInt(3, ville.getId());
            int res = statement.executeUpdate();
            if( res ==1) {
                System.out.println("");
                System.out.println("Updated");
                System.out.println(ville.getId() + " | Libelle : " + ville.getNom()+ " | Nombre d'habitants : " + ville.getNombreHabitants()+" |");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Ville ville = (Ville) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Ville where idVille = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, ville.getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted.... ");
            System.out.println(ville.getId() + " | Libelle : " + ville.getNom()+ " | Nombre d'habitants : " + ville.getNombreHabitants()+" |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
