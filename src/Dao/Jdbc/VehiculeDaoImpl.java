package Dao.Jdbc;

import Dao.DaoException;
import Model.*;
import Model.Types;

import java.sql.*;
import java.util.ArrayList;
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
        Collection<Entity> vehicules = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Vehicule");
            while (resultSet.next()) {
                Vehicule vehicule = new Vehicule();
                vehicule.setImmatriculation(resultSet.getString("immatriculation"));
                vehicule.setMiseEnCirculation(resultSet.getDate("dateMiseEnCirculation"));
                vehicule.setEtat(resultSet.getString("etat"));
                vehicule.setNbKilometres(resultSet.getFloat("nbKilometres"));
                vehicule.setPrixParJourDeLocation(resultSet.getFloat("prixParJourDeLocation"));
                Marque marque = (Marque) new MarqueDaoImpl(connection).findById(resultSet.getInt("idMarque"));
                Modele modele = (Modele) new ModeleDaoImpl(connection).findById(resultSet.getInt("idModele"));
                Categorie categorie = (Categorie) new CategorieDaoImpl(connection).findById(resultSet.getInt("idCategorie"));
                Types type = (Types) new TypesDaoImpl(connection).findById(resultSet.getInt("idType"));
                Agence agence = (Agence) new AgenceDaoImpl(connection).findById(resultSet.getInt("idAgence"));
                vehicule.setMarque(marque);
                vehicule.setModele(modele);
                vehicule.setCategorie(categorie);
                vehicule.setType(type);
                vehicule.setAgence(agence);
                vehicules.add(vehicule);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return vehicules;
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
        Vehicule vehicule = (Vehicule) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into Vehicule(immatriculation, dateMiseEnCirculation, etat, nbKilometres, prixParJourDeLocation, idMarque, idModele, idCategorie, idType, idAgence) values (?, ?,?,?,?,?,?,?,?,?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, vehicule.getImmatriculation());
            statement.setDate(2,   new java.sql.Date(vehicule.getMiseEnCirculation().getTime()));
            statement.setString(3,  vehicule.getEtat());
            statement.setFloat(4, vehicule.getNbKilometres());
            statement.setFloat(5, vehicule.getPrixParJourDeLocation());
            statement.setInt(6, vehicule.getMarque().getId());
            statement.setInt(7, vehicule.getModele().getId());
            statement.setInt(8, vehicule.getCategorie().getId());
            statement.setInt(9, vehicule.getType().getId());
            statement.setInt(10, vehicule.getAgence().getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Created");
            System.out.println(vehicule.getImmatriculation() + " | Date Mise En Circulation : "+ new java.sql.Date(vehicule.getMiseEnCirculation().getTime()) + " | État : "+vehicule.getEtat()+" | Kilometres : " + vehicule.getNbKilometres()+ " | Prix Par Jour De Location : "+vehicule.getPrixParJourDeLocation() +" | Marque ID : " + vehicule.getMarque().getId() + " | Modele ID : " + vehicule.getModele().getId() + " | Categorie ID : "+vehicule.getCategorie().getId()+" | Type ID : " + vehicule.getType().getId() + " | Agence ID : " + vehicule.getAgence().getId() +" |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Vehicule vehicule = (Vehicule) entity;
        PreparedStatement statement = null;
        String sqlReq = "update Vehicule set dateMiseEnCirculation =?, etat=?, nbKilometres=?, prixParJourDeLocation=?, idMarque=?, idModele=?, idCategorie=?, idType=?, idAgence=? WHERE immatriculation = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setDate(1,   new java.sql.Date(vehicule.getMiseEnCirculation().getTime()));
            statement.setString(2,  vehicule.getEtat());
            statement.setFloat(3, vehicule.getNbKilometres());
            statement.setFloat(4, vehicule.getPrixParJourDeLocation());
            statement.setInt(5, vehicule.getMarque().getId());
            statement.setInt(6, vehicule.getModele().getId());
            statement.setInt(7, vehicule.getCategorie().getId());
            statement.setInt(8, vehicule.getType().getId());
            statement.setInt(9, vehicule.getAgence().getId());
            statement.setString(10, vehicule.getImmatriculation());
            int res = statement.executeUpdate();
            if( res ==1) {
                System.out.println("");
                System.out.println("Updated");
                System.out.println(vehicule.getImmatriculation() + " | Date Mise En Circulation : "+ new java.sql.Date(vehicule.getMiseEnCirculation().getTime()) + " | État : "+vehicule.getEtat()+" | Kilometres : " + vehicule.getNbKilometres()+ " | Prix Par Jour De Location : "+vehicule.getPrixParJourDeLocation() +" | Marque ID : " + vehicule.getMarque().getId() + " | Modele ID : " + vehicule.getModele().getId() + " | Categorie ID : "+vehicule.getCategorie().getId()+" | Type ID : " + vehicule.getType().getId() + " | Agence ID : " + vehicule.getAgence().getId() +" |");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Vehicule vehicule = (Vehicule) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Vehicule where immatriculation = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, vehicule.getImmatriculation());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted.... ");
            System.out.println(vehicule.getImmatriculation() + " | Date Mise En Circulation : "+ new java.sql.Date(vehicule.getMiseEnCirculation().getTime()) + " | État : "+vehicule.getEtat()+" | Kilometres : " + vehicule.getNbKilometres()+ " | Prix Par Jour De Location : "+vehicule.getPrixParJourDeLocation() +" | Marque ID : " + vehicule.getMarque().getId() + " | Modele ID : " + vehicule.getModele().getId() + " | Categorie ID : "+vehicule.getCategorie().getId()+" | Type ID : " + vehicule.getType().getId() + " | Agence ID : " + vehicule.getAgence().getId() +" |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void vehiculesParMarque() throws DaoException{
        PreparedStatement statement = null;
        String sqlReq = "SELECT DISTINCT count(*) as NombreVehicules, m.nomMarque FROM Vehicule as v\n" +
                "INNER JOIN Marque m on v.idMarque = m.idMarque\n" +
                "GROUP BY m.nomMarque\n" +
                "ORDER BY NombreVehicules DESC ;\n";
        try {
            statement = connection.prepareStatement(sqlReq);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("NombreVehicules")+" | " + resultSet.getString("nomMarque"));
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void lastLocation() throws DaoException{
        PreparedStatement statement = null;
        String sqlReq = "SELECT v.immatriculation, c.dateDeRetrait FROM Contrat as c\n" +
                "INNER JOIN Vehicule v on c.immatriculation = v.immatriculation\n" +
                "ORDER BY c.dateDeRetrait DESC LIMIT 1;";
        try {
            statement = connection.prepareStatement(sqlReq);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("immatriculation")+" | " + new java.sql.Date(resultSet.getDate("dateDeRetrait").getTime()));
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }

}
