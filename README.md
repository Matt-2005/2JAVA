# 2JAVA

Avant de lancer l'application, assurez-vous d'avoir les éléments suivants installés sur votre machine :

- Java (JDK 17 ou supérieur)
- MySQL (ou MariaDB) installé en local

Ensuite, il faut créer la base de données **iStore** et insérer le contenu de **InsertData.sql** situé dans `com.iStore.config`.

Enfin, veillez à modifier si nécessaire cette partie du code dans **DatabaseConfig.java** avec les bonnes informations :

```java
final String URL = "jdbc:mysql://localhost:8889/iStore";
final String USER = "root";
final String PASSWORD = "root";
```

Voici les informations de connection:

**Administrateur :** 
- email : matthew.le@supinfo.com
- mdp : Matt2005

**Employés :** 
- email : employe1@supinfo.com
- mdp : employe1

- email : employe1@supinfo.com
- mdp : employe2

- email : employe3@supinfo.com
- mdp : employe3