package Dao.Jdbc;

import Dao.DaoException;
import Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class FactureDaoImpl extends JdbcDao{
    private ContractDaoImpl contratDao;
    public FactureDaoImpl(Connection connection) {
        super(connection);
        this.contratDao = new ContractDaoImpl(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> factures = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Facture");
            while (resultSet.next()) {
                Facture facture = new Facture();
                facture.setId(resultSet.getInt("idFacture"));
                facture.setMontant(resultSet.getFloat("montant"));
                Contrat contrat = (Contrat) new ContractDaoImpl(connection).findById(resultSet.getInt("idContrat"));
                facture.setContrat(contrat);
                factures.add(facture);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return factures;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Facture facture = null;
        String sqlReq = "SELECT * FROM Facture WHERE  idFacture= ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                facture = new Facture();
                facture.setId(resultSet.getInt("idFacture"));
                facture.setMontant(resultSet.getFloat("montant"));
                Contrat contrat = (Contrat) contratDao.findById(resultSet.getInt("idContrat"));
                facture.setContrat(contrat);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return facture;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Facture facture = (Facture) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into Facture(idFacture ,montant, idContrat) values (?, ?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, facture.getId());
            statement.setFloat(2, facture.getMontant());
            statement.setInt(3,  facture.getContrat().getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Created");
            System.out.println(facture.getId() + " | Montant : " + facture.getMontant() + " | Contrat ID : " + facture.getContrat().getId() +" |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Facture facture = (Facture) entity;
        PreparedStatement statement = null;
        String sqlReq = "update Facture set montant=?, idContrat=? WHERE idFacture = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setFloat(1, facture.getMontant());
            statement.setInt(2,  facture.getContrat().getId());
            statement.setInt(3, facture.getId());
            int res = statement.executeUpdate();
            if( res ==1) {
                System.out.println("");
                System.out.println("Updated");
                System.out.println(facture.getId() + " | Montant : " + facture.getMontant() + " | Contrat ID : " + facture.getContrat().getId() +" |");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Facture facture = (Facture) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Facture where idFacture = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, facture.getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted.... ");
            System.out.println(facture.getId() + " | Montant : " + facture.getMontant() + " | Contrat ID : " + facture.getContrat().getId() +" |");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
