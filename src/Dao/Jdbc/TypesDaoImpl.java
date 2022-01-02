package Dao.Jdbc;

import Dao.DaoException;
import Model.Entity;
import Model.Types;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TypesDaoImpl extends JdbcDao{
    public TypesDaoImpl(Connection connection) {
        super(connection);
    }
    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> types = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Types");
            while (resultSet.next()) {
                Types type = new Types();
                type.setId(resultSet.getInt("idType"));
                type.setLibelle(resultSet.getString("libelleType"));
                types.add(type);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return types;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Types type = null;
        String sqlReq = "SELECT * FROM Types WHERE  idType= ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                type = new Types();
                type.setId(resultSet.getInt("idType"));
                type.setLibelle(resultSet.getString("libelleType"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return type;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Types type = (Types) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into Types(idType ,libelleType) values (?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, type.getId());
            statement.setString(2, type.getLibelle());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Created");
            System.out.println(type.getId() + " | Libelle : " + type.getLibelle()+ " |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Types type = (Types) entity;
        PreparedStatement statement = null;
        String sqlReq = "update Types set libelleType=? WHERE idType = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setString(1, type.getLibelle());
            statement.setInt(2, type.getId());
            int res = statement.executeUpdate();
            if( res ==1) {
                System.out.println("");
                System.out.println("Updated");
                System.out.println(type.getId() + " | Libelle : " + type.getLibelle()+ " |");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Types type = (Types) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Types where idType = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, type.getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted.... ");
            System.out.println(type.getId() + " | Libelle : " + type.getLibelle()+ " |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void chiffreAffaires() throws DaoException{
        PreparedStatement statement = null;
        String sqlReq = "SELECT t.libelleType, max(f.montant) as Chiffre_Affaires FROM Facture as f\n" +
                "INNER JOIN Contrat cont on cont.idContrat = f.idContrat\n" +
                "INNER JOIN Vehicule v on v.immatriculation = cont.immatriculation\n" +
                "INNER JOIN Types t on v.idType = t.idType\n" +
                "GROUP BY t.libelleType\n" +
                "ORDER BY Chiffre_Affaires DESC;";
        try {
            statement = connection.prepareStatement(sqlReq);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("libelleType") + " | Chiffre Affaires : " + resultSet.getFloat("Chiffre_Affaires") + " |");
            }
        }catch (SQLException e ){
            throw new DaoException(e);
        }
    }
}
