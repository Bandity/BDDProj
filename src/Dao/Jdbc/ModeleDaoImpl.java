package Dao.Jdbc;

import Dao.DaoException;
import Model.Entity;

import java.sql.Connection;
import java.util.Collection;

public class ModeleDaoImpl extends JdbcDao{
    public ModeleDaoImpl(Connection connection) {
        super(connection);
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
