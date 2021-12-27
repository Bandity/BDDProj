package Dao.Jdbc;

import Dao.DaoException;
import Model.Entity;
import Model.Ville;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class VilleDaoImpl extends JdbcDao{
    public VilleDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        return null;
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

    }

    @Override
    public void update(Entity entity) throws DaoException {

    }

    @Override
    public void delete(Entity entity) throws DaoException {

    }
}
