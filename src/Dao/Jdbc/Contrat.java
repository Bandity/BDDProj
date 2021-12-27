package Dao.Jdbc;

import Dao.DaoException;
import Model.Entity;

import java.sql.*;
import java.util.Collection;
import java.util.Date;

public class Contrat extends JdbcDao{
    public Contrat(Connection connection) {
        super(connection);
    }
    public Entity locationDeVoitureClient(int id, Date date) throws  DaoException{
        Contrat contrat = null;
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
