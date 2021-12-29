package Dao.Jdbc;

import Dao.DaoException;
import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class VehiculeDaoImpl extends JdbcDao{
    private MarqueDaoImpl marqueDao;
    private ModeleDaoImpl modeleDao;
    private CategorieDaoImpl categorieDao;
    private TypesDaoImpl typesDao;
    private AgenceDaoImpl agenceDao;

    public VehiculeDaoImpl(Connection connection) {
        super(connection);
        this.agenceDao = new AgenceDaoImpl(connection);
        this.typesDao = new TypesDaoImpl(connection);
        this.categorieDao = new CategorieDaoImpl(connection);
        this.modeleDao = new ModeleDaoImpl(connection);
        this.marqueDao = new MarqueDaoImpl(connection);
    }

    public Entity lastReturnedVehicle() throws  DaoException{
        Vehicule vehicule = null;
        String sqlReq = "SELECT * FROM vehicule v INNER  JOIN vehicule c ON c.immatriculation = v.immatriculation WHERE v.date=(SELECT MAX(date) FROM vehicule)";
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

    public Entity findById(String immatriculation) throws DaoException {
        Vehicule vehicule = null;
        String sqlReq = "SELECT * FROM Vehicule WHERE  immatriculation= ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, immatriculation);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                vehicule = new Vehicule();
                vehicule.setImmatriculation(resultSet.getString("immatriculation"));
                vehicule.setMiseEnCirculation(resultSet.getDate("dateMiseEnCirculation"));
                vehicule.setEtat(resultSet.getString("etat"));
                vehicule.setNbKilometres(resultSet.getInt("nbKilometres"));
                vehicule.setPrixParJourDeLocation(resultSet.getInt("prixParJourDeLocation"));
                Marque marque = (Marque) marqueDao.findById(resultSet.getInt("idMarque"));
                Modele modele = (Modele) modeleDao.findById(resultSet.getInt("idModele"));
                Categorie categorie = (Categorie) categorieDao.findById(resultSet.getInt("idCategorie"));
                Types type = (Types) typesDao.findById(resultSet.getInt("idType"));
                Agence agence = (Agence) agenceDao.findById(resultSet.getInt("idAgence"));
                vehicule.setMarque(marque);
                vehicule.setModele(modele);
                vehicule.setCategorie(categorie);
                vehicule.setType(type);
                vehicule.setAgence(agence);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return vehicule;
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
