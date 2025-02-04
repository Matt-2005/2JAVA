# 2JAVA

Avant de lancer l'application, assurez-vous d'avoir les éléments suivants installés sur votre machine :

Java (JDK 17 ou supérieur)
MySQL (ou MariaDB) installé en local

Ensuite il faut créer la base de données iStore.

Enfin, veillez a modifier si necessaire cette partie du code dans DatabaseConfig.java avec les bonnes informations : 

final String URL = "jdbc:mysql://localhost:8889/iStore";
final String USER = "root";
final String PASSWORD = "root";
