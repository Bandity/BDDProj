# BDDProj
## Mise en place de la base

```
  Pour faire la base tout d'abord j'ai commencé par faire le script sql pour ne pas avoir besoin qu'à chaque fois 
  que je veux modifier un truc je doit me connecter et comme ça je pouvais lancer de script et faire le requêtes 
  de dans pour voir dans IntelliJ le rendu de la requête.

```

## Organisation du code

```
  Pour être plus organisé mon code, j'ai décidé de faire plusieurs dossiers/packages qui m'ont permis de mieux voir 
  la structure et avoir un projet qui est bien organisé et où je puisse me retrouver facilement. Notamment avec des
  Dossiers JDBC pour les DaoImpl, Model(Entities), Tests, Connections, Dao qui inclut le JDBC. Pour bien séparer les
  rendus j'ai aussi fait plusieurs classes de tests et un test initializer pour pouvoir tester tous les tests.

```

## Contraintes JDBC

```
  Pour les contraintes du JDBC j'ai fait des entités pour chaque une table dans la base SQL avec des attributs privées 
  et j'ai fait des des get et des set pour pouvoir les modifier les données dans la base pour accéder à la base de 
  donnéesj'ai utilisé la classe PostgresConnection.java.
  
```

## Contraintes DAO
```
  Pour les contraintes du DAO j'ai implémenter une interface Dao qui va permettre de définir les méthodes create, update
  delete, findById, findAll. Pour ensuite les utiliser grâce à extends dans toutes les classes **DaoImpl.java ces 
  classes vont permettre de faire la liaison de Java avec postgreSQL et faires des modifications ou chercher les infos 
  dans la base pour bien respecter les procédures j'ai toujours accéder la base avec les PreparedStatement mais aussi 
  avec les classes Entités créées dans le dossier Model et retourné des collections d'objets que on pourra par la suite 
  utiliser dans Java.

```

## Pour le lancement du projet
```
  En ce qui concerne le lancement du projet j'ai fait une classe TestInitialize.java dans le dossier src/Test qui elle 
  permet de parcourir les ensembles de tests par de questions dans le terminal. Quand on lace cette classe elles nous 
  prose de faire des tests globaux où on va tester chaque CRUD avec aussi les requêtes préciser dans le projet. Mais 
  aussi quand on lance TestInitialize.java elle permet que de faire appell isoler à requête demander dans le sujet de 
  projet Néanmoins à aucun moment on peux faires des tests personnalisées chaque test est déjà prédéfinie n'importe 
  dans quelle classe de test donc pour faires des tests personnaliser il faut modifier directement les appeles dans la 
  classe TestInitialize.java ou dans les classes individuelles de test.

```

## Organisation des tests
```
  Pour les tests j'ai commencé par tester tous les CRUD's en faisant des classes **DaoJdbcTest.java dans le repertoire 
  src/Test ensuite j'ai continuer par faire des fonctions pour les requêtes demandée dans le sujet du projet dans chaque
  classe de tests j'ai fait une méthode   all**Tests(Connection connection) pour quand on lance le main() on puisse
  avoir toutes les requêtes faites dans la classe DaoImpl et aussi pour moi au niveau du développement pour voir 
  quelles requêtes avait déjà été faites dans chaque classe de tests. Chaque test est prédéfinie dans cette méthode 
  afin de éviter que vous perdiez du temps à chercher dans les inserts (data) de valeurs qui puissent donner la bonne 
  réponse en fait pour facilité de travail comme j'ai déjà montionné j'ai crée la class TestInitialize.java pour être 
  plus facile d' évaluer les requêtes et aussi les test, pour pas avoir à lancer les 10 classes de tests mais aussi 
  pour accès à des requêtes indépendamment

```
