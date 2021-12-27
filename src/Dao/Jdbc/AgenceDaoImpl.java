package Dao.Jdbc;

import Dao.DaoException;
import Model.Agence;
import Model.Entity;
import Model.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AgenceDaoImpl extends JdbcDao{
    private VilleDaoImpl villeDao;

    public AgenceDaoImpl(Connection connection) {
        super(connection);
        villeDao = new VilleDaoImpl(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> agences = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Agence");
            while (resultSet.next()) {
                Agence agence = new Agence();
                agence.setId(resultSet.getInt("idAgence"));
                agence.setNbEmployes(resultSet.getInt("nbEmployes"));
                Ville ville = (Ville) villeDao.findById(resultSet.getInt("idagence"));
                agence.setVille(ville);
                agences.add(agence);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return agences;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Agence agence = null;
        String sqlReq = "SELECT * FROM Agence WHERE  idAgence = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                agence = new Agence();
                agence.setId(resultSet.getInt("idAgence"));
                agence.setNbEmployes(resultSet.getInt("nbEmployes"));
                Ville ville = (Ville) villeDao.findById(resultSet.getInt("idVille"));
                agence.setVille(ville);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return agence;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into Agence(idAgence,nbEmployes, idVille) values (?,?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, agence.getId());
            statement.setInt(2, agence.getNbEmployes());
            statement.setInt(3, agence.getVille().getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Created");
            System.out.println(agence.getId() +" | Nombre Employées :" +agence.getNbEmployes() +" | Ville ID :"+agence.getVille().getId()+" | " + "Ville Name :"+agence.getVille().getNom()+" | "+"Ville Nb Habitants :"+agence.getVille().getNombreHabitants()+" |");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String sqlReq = "update Agence set nbEmployes = ?,idVille = ? WHERE idAgence = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, agence.getNbEmployes());
            statement.setInt(2, agence.getVille().getId());
            statement.setInt(3, agence.getId());
            int res = statement.executeUpdate();
            if( res ==1) {
                System.out.println("");
                System.out.println("Updated");
                System.out.println(agence.getId() + " | Nombre Employées :" + agence.getNbEmployes() + " | Ville ID :" + agence.getVille().getId() + " | " + "Ville Name :" + agence.getVille().getNom() + " | " + "Ville Nb Habitants :" + agence.getVille().getNombreHabitants() + " |");

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Agence where idAgence = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, agence.getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted.... ");
            System.out.println(agence.getId() +" | Nombre Employées :" +agence.getNbEmployes() +" | Ville ID :"+agence.getVille().getId()+" | " + "Ville Name :"+agence.getVille().getNom()+" | "+"Ville Nb Habitants :"+agence.getVille().getNombreHabitants()+" |");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
