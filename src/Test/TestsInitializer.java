package Test;

import Connections.Jdbc.PostgresConnection;
import Dao.DaoException;
import Dao.Jdbc.AgenceDaoImpl;
import Dao.Jdbc.ClientDaoImpl;
import Model.Agence;
import Model.Client;

import java.sql.Connection;
import java.util.*;

public class TestsInitializer {
    public static void main(String[] args) {
        Connection connection = PostgresConnection.getInstance();
        String response = "";
        Scanner scanner = new Scanner(System.in);
        while (!response.toLowerCase(Locale.ROOT).equals("d") && !response.toLowerCase(Locale.ROOT).equals("r")){
            System.out.println("");
            System.out.println("Voudriez Tester les DAO's ou les 10 Requêtes (r) ?");
            System.out.print("Pour les DAO's tapez (d) et pour les 10 Requêtes (r) : ");
            response = scanner.next();
        }

        String r = response.toLowerCase(Locale.ROOT);
        int n;
        boolean b = false;
        if(r.equals("d")){
            while (!b) {
                System.out.println("");
                System.out.println("Chosisez un nombre entre 1 et 9");
                System.out.println("1. DAO Agence");
                System.out.println("2. DAO Categorie");
                System.out.println("3. DAO Client");
                System.out.println("4. DAO Contrat");
                System.out.println("5. DAO Facture");
                System.out.println("6. DAO Marque");
                System.out.println("7. DAO Modele");
                System.out.println("8. DAO Type");
                System.out.println("9. DAO Vehicule");
                System.out.println("10. DAO Ville");
                response = scanner.next();
                try {
                    n = Integer.parseInt(response);
                    if (n > 10 || n <= 0) {
                        System.out.println("nombre entre 1 et 10");
                    } else {
                        switch (n){
                            case 1:
                                new AgenceDaoJdbcTest().allAgenceTests(connection);
                                break;
                            case 2:
                                new CategorieDaoJdbcTest().allCategoriesTests(connection);
                                break;
                            case 3:
                                new ClientDaoJdbcTest().allClientTests(connection);
                                break;
                            case 4:
                                new ContratDaoJdbcTest().allContratTests(connection);
                                break;
                            case 5:
                                new FactureDaoJdbcTest().allFactureTests(connection);
                                break;
                            case 6:
                                new MarqueDaoJdbcTest().allMarqueTests(connection);
                                break;
                            case 7:
                                new ModeleDaoJdbcTest().allModeleTests(connection);
                                break;
                            case 8:
                                new TypesDaoJdbcTest().allTypesTests(connection);
                                break;
                            case 9:
                                new VehiculeDaoJdbcTest().allVehiculeTests(connection);
                                break;
                            case 10:
                                new VilleDaoJdbcTest().allVilleTests(connection);
                                break;
                        }
                        System.out.println("");
                        System.out.println("Pour sortier faites : (q) pour continue (c)");
                        response = scanner.next();
                        if(response.toLowerCase(Locale.ROOT).equals("q"))
                            b = true;
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Um nombre pas des lettres");
                }
            }
        }
        b =false;
        if(r.equals("r")) {
            while (!b) {
                System.out.println("");
                System.out.println("Requête 1");
                System.out.println("Requête 2");
                System.out.println("Requête 3");
                System.out.println("Requête 4");
                System.out.println("Requête 5");
                System.out.println("Requête 6");
                System.out.println("Requête 7");
                System.out.println("Requête 8");
                System.out.println("Requête 9");
                System.out.println("Requête 10");
                response = scanner.next();
                try {
                    n = Integer.parseInt(response);
                    if (n > 10 || n <= 0) {
                        System.out.println("nombre entre 1 et 10");
                    } else {
                        switch (n) {

                            case 1:/*La location d’une voiture par un client donné, à une date et pour une durée données. L’agence
                                de retour devra être différente de l’agence de départ dans laquelle se situe le véhicule.*/
                                try {
                                    new VehiculeDaoJdbcTest().locationParClientDateDureeTest(connection,(Client) new ClientDaoImpl(connection).findById(1), (Date) new GregorianCalendar(2021, Calendar.NOVEMBER, 19).getTime(),31);
                                }catch (DaoException e){
                                    e.printStackTrace();
                                }
                                break;

                            case 2://Le retour du véhicule loué précédemment.
                                new VehiculeDaoJdbcTest().lastLocationTest(connection);
                                break;

                            case 3://L’établissement de la facture pour la location précédente
                                new AgenceDaoJdbcTest().lastLocationAgenceTest(connection);
                                break;

                            case 4://Le chiffre d’affaire d’une agence donnée pour un mois donné.
                                try {
                                    new AgenceDaoJdbcTest().chiffreAffairesPourUnMoisTest(connection, new AgenceDaoImpl(connection).findById(3),05);
                                } catch (DaoException e){
                                    e.printStackTrace();
                                }
                                break;

                            case 5://Le nombre de véhicules pour chaque marque
                                new VehiculeDaoJdbcTest().vehiculesParMarqueTest(connection);
                                break;

                            case 6://Le(s) client(s) ayant réalisé(s) le plus de locations pour une agence donnée et pour une année donnée.
                                try {
                                    new ClientDaoJdbcTest().clientPlusLocations(connection, (Agence) new AgenceDaoImpl(connection).findById(3), 2021);
                                }catch (DaoException e){
                                    e.printStackTrace();
                                }
                                break;

                            case 7://Le chiffre d’affaire par catégorie
                                new CategorieDaoJdbcTest().chiffreAffairesTest(connection);
                                break;

                            case 8://Le chiffre d’affaire par type
                                new TypesDaoJdbcTest().chiffreAffairesTest(connection);
                                break;

                            case 9://Le nombre de véhicule(s) de plus de 2 ans et de plus de 150 000 km pour chacune des agences
                                new AgenceDaoJdbcTest().vehicule2ans150KAgenceTest(connection);
                                break;

                            case 10://Le chiffre d’affaire pour une année donnée pour chacune des agences
                                new AgenceDaoJdbcTest().chiffreAffairesTest(connection, 2021);
                                break;
                        }
                        System.out.println("");
                        System.out.println("Pour sortier faites : (q) pour continue (c)");
                        response = scanner.next();
                        if(response.toLowerCase(Locale.ROOT).equals("q"))
                            b = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Um nombre pas des lettres");
                }
            }
        }
    }
}
