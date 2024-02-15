 package sd.akka.actor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private String url;
    private String user;
    private String mdp;
    private Connection con;

    //Constructeur sans paramètres
    public Connexion(){
     this.url = "jdbc:mysql://localhost:3306/projet?serverTimezone=Europe/Paris";
     this.user = "root";
     this.mdp = "";
    }

    public void chargerLePilote(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de charger le pilote jdbc:odbc");
            System.exit(99);
        }
    }

    //Fonction qui se connecte à la base de données
    public void connexionBDD(){
        try {
            this.con = DriverManager.getConnection(this.url, this.user, this.mdp);
        } catch (SQLException e) {
            System.err.println("Connection a la base de donnees impossible");
            System.exit(99);
        }
    } 
      public Connection getConnexion(){
        return this.con;
    }

}
