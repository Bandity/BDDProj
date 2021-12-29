package Dao.Jdbc;

import Dao.DaoException;
import Model.Entity;
import Model.Marque;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class MarqueDaoImpl extends JdbcDao{
    public MarqueDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> marques = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Marque");
            while (resultSet.next()) {
                Marque marque = new Marque();
                marque.setId(resultSet.getInt("idMarque"));
                marque.setNom(resultSet.getString("nomMarque"));
                marques.add(marque);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return marques;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Marque marque = null;
        String sqlReq = "SELECT * FROM Marque WHERE  idMarque= ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                marque = new Marque();
                marque.setId(resultSet.getInt("idMarque"));
                marque.setNom(resultSet.getString("nomMarque"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return marque;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Marque marque = (Marque) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into Marque(idMarque ,nomMarque) values (?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, marque.getId());
            statement.setString(2, marque.getNom());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Created");
            System.out.println(marque.getId() + " | Name : " + marque.getNom()+ " |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Marque marque = (Marque) entity;
        PreparedStatement statement = null;
        String sqlReq = "update Marque set nomMarque=? WHERE idMarque = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, marque.getNom());
            statement.setInt(2, marque.getId());
            int res = statement.executeUpdate();
            if( res ==1) {
                System.out.println("");
                System.out.println("Updated");
                System.out.println(marque.getId() + " | Name : " + marque.getNom()+ " |");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Marque marque = (Marque) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Marque where idMarque = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, marque.getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted.... ");
            System.out.println(marque.getId() + " | Name : " + marque.getNom()+ " |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
