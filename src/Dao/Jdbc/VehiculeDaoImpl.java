package Dao.Jdbc;

import Dao.DaoException;
import Model.Entity;
import Model.Vehicule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class VehiculeDaoImpl extends JdbcDao{
    public VehiculeDaoImpl(Connection connection) {
        super(connection);
    }

    public Entity lastReturnedVehicle() throws  DaoException{
        Vehicule vehicule = null;
        String sqlReq = "SELECT * FROM vehicule v INNER  JOIN contrat c ON c.immatriculation = v.immatriculation WHERE v.date=(SELECT MAX(date) FROM vehicule)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);

        }
        catch (SQLException e){
            throw new DaoException(e);
        }
        return null;
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        return null;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        return null;
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
