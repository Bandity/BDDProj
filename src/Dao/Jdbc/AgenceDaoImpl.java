package Dao.Jdbc;

import Dao.DaoException;
import Model.Agence;
import Model.Entity;
import Model.Ville;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class AgenceDaoImpl extends JdbcDao{
    private VilleDaoImpl villeDao;

    public AgenceDaoImpl(Connection connection) {
        super(connection);
        villeDao = new VilleDaoImpl(connection);
    }

    @Override
    public Collection<Entity> findAll() throws DaoException {
        Collection<Entity> agences = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Agence");
            while (resultSet.next()) {
                Agence agence = new Agence();
                agence.setId(resultSet.getInt("idAgence"));
                agence.setNbEmployes(resultSet.getInt("nbEmployes"));
                Ville ville = (Ville) villeDao.findById(resultSet.getInt("idagence"));
                agence.setVille(ville);
                agences.add(agence);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return agences;
    }

    @Override
    public Entity findById(int id) throws DaoException {
        Agence agence = null;
        String sqlReq = "SELECT * FROM Agence WHERE  idAgence = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                agence = new Agence();
                agence.setId(resultSet.getInt("idAgence"));
                agence.setNbEmployes(resultSet.getInt("nbEmployes"));
                Ville ville = (Ville) villeDao.findById(resultSet.getInt("idVille"));
                agence.setVille(ville);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return agence;
    }

    @Override
    public void create(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String sqlReq = "insert into Agence(idAgence,nbEmployes, idVille) values (?,?, ?)";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, agence.getId());
            statement.setInt(2, agence.getNbEmployes());
            statement.setInt(3, agence.getVille().getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Created");
            System.out.println(agence.getId() +" | Nombre Employées :" +agence.getNbEmployes() +" | Ville ID :"+agence.getVille().getId()+" | " + "Ville Name :"+agence.getVille().getNom()+" | "+"Ville Nb Habitants :"+agence.getVille().getNombreHabitants()+" |");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String sqlReq = "update Agence set nbEmployes = ?,idVille = ? WHERE idAgence = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, agence.getNbEmployes());
            statement.setInt(2, agence.getVille().getId());
            statement.setInt(3, agence.getId());
            int res = statement.executeUpdate();
            if( res ==1) {
                System.out.println("");
                System.out.println("Updated");
                System.out.println(agence.getId() + " | Nombre Employées :" + agence.getNbEmployes() + " | Ville ID :" + agence.getVille().getId() + " | " + "Ville Name :" + agence.getVille().getNom() + " | " + "Ville Nb Habitants :" + agence.getVille().getNombreHabitants() + " |");

            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Entity entity) throws DaoException {
        Agence agence = (Agence) entity;
        PreparedStatement statement = null;
        String sqlReq = "delete from Agence where idAgence = ?";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1, agence.getId());
            int res = statement.executeUpdate();
            System.out.println("");
            System.out.println("Deleted.... ");
            System.out.println(agence.getId() +" | Nombre Employées :" +agence.getNbEmployes() +" | Ville ID :"+agence.getVille().getId()+" | " + "Ville Name :"+agence.getVille().getNom()+" | "+"Ville Nb Habitants :"+agence.getVille().getNombreHabitants()+" |");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void chiffreAffairesPourUnMois(Entity entity, int mois) throws DaoException{
        Agence agenceRequete = (Agence) entity;
        //Collection<Entity> agences = new ArrayList<>();
        PreparedStatement statement = null;
        String sqlReq = "SELECT  a.idAgence, a.idVille, a.nbEmployes,c.dateDeRetrait, sum(f.montant) as ChiffreAffairs FROM Contrat as c\n" +
                "INNER JOIN Agence a on a.idAgence = c.idAgenceDeRetour\n" +
                "INNER JOIN Facture f on c.idContrat = f.idContrat\n" +
                "WHERE EXTRACT(MONTH FROM c.datederetrait) = ? AND a.idAgence =?\n" +
                "GROUP BY  a.idAgence, c.dateDeRetrait, a.idVille, a.nbEmployes\n" +
                "ORDER BY ChiffreAffairs DESC ;";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setInt(1,mois);
            statement.setInt(2,agenceRequete.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){

                System.out.println("Agence ID : " + resultSet.getInt("idAgence") + " | Nombre Employes " + resultSet.getInt("nbEmployes")+" | Ville ID : " + resultSet.getInt("idVille")+ " | Date de Retrait du Contrat : " + new java.sql.Date(resultSet.getDate("datederetrait").getTime()) +" | Chiffre d'affaires : " + resultSet.getFloat("ChiffreAffairs"));
                /*
                Agence agence = new Agence();
                agence.setId(resultSet.getInt("idAgence"));
                agence.setNbEmployes(resultSet.getInt("nbEmployes"));
                Ville ville = (Ville) villeDao.findById(resultSet.getInt("idagence"));
                agence.setVille(ville);
                agences.add(agence);*/
            }
        }catch (SQLException e ){
            throw new DaoException(e);
        }
    }

    public void chiffreAffairesAnnee(int annee) throws DaoException{
        String datedebSTR = annee+"-01-01";
        String datefinSTR = annee+"-12-31";
        java.sql.Date datedeb= java.sql.Date.valueOf(datedebSTR);
        java.sql.Date datefin= java.sql.Date.valueOf(datefinSTR);

        PreparedStatement statement = null;
        String sqlReq = "SELECT  a.idAgence, c.dateDeRetrait,c.dateDeRetour, SUM(f.montant) as Chiffre_Affaires FROM Facture as f\n" +
                "INNER JOIN contrat c on f.idContrat = c.idcontrat\n" +
                "INNER JOIN Agence a on a.idAgence = c.idAgenceDeRetour\n" +
                "WHERE c.datederetrait BETWEEN ? AND ?\n" +
                "GROUP BY  a.idAgence, c.dateDeRetrait,c.dateDeRetour\n" +
                "ORDER BY Chiffre_Affaires DESC ;";
        try {
            statement = connection.prepareStatement(sqlReq);
            statement.setDate(1,datedeb);
            statement.setDate(2,datefin);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){

                System.out.println("Agence ID : " + resultSet.getInt("idAgence") + " | Date de Retrait du Contrat : "+new java.sql.Date(resultSet.getDate("dateDeRetrait").getTime())+" | Date de Retour du Contrat : "+new java.sql.Date(resultSet.getDate("dateDeRetour").getTime())+" | Chiffre d'affaires : " +resultSet.getFloat("Chiffre_Affaires") +" |");            }
        }catch (SQLException e ){
            throw new DaoException(e);
        }
    }
    public void nombreVehiculesPlus2A15000Agence() throws DaoException {
        PreparedStatement statement=null;
        String sqlReq = "SELECT DISTINCT COUNT(v) as Nombre_De_Vehicules , a.idAgence FROM Agence as a\n" +
                "INNER JOIN Vehicule v on a.idAgence = v.idAgence\n" +
                "WHERE v.dateMiseEnCirculation < '2020-01-10' AND v.nbKilometres > 150000.00\n" +
                "GROUP BY a.idAgence\n" +
                "ORDER BY   Nombre_De_Vehicules DESC ;";
        try {
            statement = connection.prepareStatement(sqlReq);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println( "Agence ID : "+ resultSet.getInt("idAgence")+ " | " + resultSet.getInt("Nombre_De_Vehicules"));
            }
        }catch (SQLException e){
            throw  new DaoException(e);
        }
    }
    public void lastLocation() throws DaoException{
        PreparedStatement statement = null;
        String sqlReq = "SELECT a.idAgence, c.dateDeRetrait FROM Facture as f\n" +
                "INNER JOIN contrat c on f.idContrat = c.idcontrat\n" +
                "INNER JOIN Agence a on a.idAgence = c.idagencederetour\n" +
                "ORDER BY c.dateDeRetrait DESC LIMIT 1;";
        try {
            statement = connection.prepareStatement(sqlReq);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("idAgence")+" | " + new java.sql.Date(resultSet.getDate("dateDeRetrait").getTime()));
            }
        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
