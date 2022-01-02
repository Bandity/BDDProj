package Dao.Jdbc;

import Dao.DaoException;
import Model.Agence;
import Model.Categorie;
import Model.Entity;
import Model.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class CategorieDaoImpl extends JdbcDao{
    public CategorieDaoImpl(Connection connection) {
        super(connection);
    }
    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> categories = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Categorie");
            while (resultSet.next()) {
                Categorie categorie = new Categorie();
                categorie.setId(resultSet.getInt("idCategorie"));
                categorie.setLibelle(resultSet.getString("libelleCategorie"));
                categories.add(categorie);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return categories;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Categorie categorie = null;
        String sqlReq = "SELECT * FROM Categorie WHERE  idCategorie = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                categorie = new Categorie();
                categorie.setId(resultSet.getInt("idCategorie"));
                categorie.setLibelle(resultSet.getString("libelleCategorie"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categorie;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Categorie categorie = (Categorie) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into Categorie(idCategorie,libelleCategorie) values (?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, categorie.getId());
            statement.setString(2, categorie.getLibelle());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Created");
            System.out.println( categorie.getId() + " | " + categorie.getLibelle() + " |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Categorie categorie = (Categorie) entity;
        PreparedStatement statement = null;
        String sqlReq = "update Categorie set libelleCategorie = ? WHERE idCategorie = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, categorie.getLibelle());
            statement.setInt(2, categorie.getId());
            int res = statement.executeUpdate();
            if( res ==1) {
                System.out.println("");
                System.out.println("Updated");
                System.out.println( categorie.getId() + " | " + categorie.getLibelle() + " |");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Categorie categorie = (Categorie) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Categorie where idCategorie = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, categorie.getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted.... ");
            System.out.println( categorie.getId() + " | " + categorie.getLibelle() + " |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void chiffreAffaires() throws DaoException{
        PreparedStatement statement = null;
        String sqlReq = "SELECT c.libelleCategorie, max(f.montant) as Chiffre_Affaires FROM Facture as f\n" +
                "INNER JOIN Contrat cont on cont.idContrat = f.idContrat\n" +
                "INNER JOIN Vehicule v on v.immatriculation = cont.immatriculation\n" +
                "INNER JOIN Categorie c on v.idCategorie = c.idCategorie\n" +
                "GROUP BY c.libelleCategorie\n" +
                "ORDER BY Chiffre_Affaires DESC;";
        try {
            statement = connection.prepareStatement(sqlReq);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("libelleCategorie") + " | Chiffre Affaires : " + resultSet.getFloat("Chiffre_Affaires") + " |");
            }
        }catch (SQLException e ){
            throw new DaoException(e);
        }
    }
}
