package sd.akka.actor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.*;

public class Requete {
    private Connexion connexion;
    //Constructeur
    public Requete(Connexion con) {
        this.connexion = con;
    }

    // solde du client
    
    public int SoldeClient(int idClient){
       int solde=0;
         try{
             String sql = "SELECT solde FROM client WHERE Idclient = ?";
             PreparedStatement  preparedStatement = this.connexion.getConnexion().prepareStatement(sql);
             preparedStatement.setInt(1, idClient);

            // Exécuter la requête SQL
          ResultSet  resultSet = preparedStatement.executeQuery();

            // Vérifier si des résultats ont été trouvés
            if (resultSet.next()) {
                // Récupérer le solde du client depuis le résultat de la requête
                solde = resultSet.getInt("solde");
              
            } 
            preparedStatement.close();
            resultSet.close();
         }
        catch(SQLException e){
             System.out.println(e);
         }
      
         return solde;
    }
    
   
    //Liste des clients
    public Hashtable<Integer,String>getClients(){
        Hashtable<Integer,String>ListeClient=new Hashtable<>();
            try( Statement stmt = this.connexion.getConnexion().createStatement();){
            
              // exécution de la requête
                 ResultSet res = stmt.executeQuery("SELECT * FROM client");
                //recuperation du resultat de la requête
                 while(res.next()){
                       String nomClient = res.getString(2);
                       int id=res.getInt(1);
                       ListeClient.put(id, nomClient);
                      
                 }
                 stmt.close();
                 res.close();
        }
        catch(SQLException e){
               System.out.println(e);
        }
        return  ListeClient;
        
    }
    
    //liste des clients avec leur banquier
   
    
    public void Operation(int idclient,String Libele, int montant){
        int s=SoldeClient(idclient);
        
        Date d= new Date();
         try{   
                //depot
                if(Libele.equals("depot")){
                      String sql = "INSERT INTO operation (Idclient, Libele, montant, DateOperation) VALUES (?, ?, ?, ?)";
                      PreparedStatement  preparedStatement = this.connexion.getConnexion().prepareStatement(sql);
                      preparedStatement.setInt(1,idclient );
                      preparedStatement.setString(2,"depot");
                      preparedStatement.setInt(3, montant);
                      preparedStatement.setDate(4, new java.sql.Date(d.getTime()));
                      preparedStatement.executeUpdate(); 
                       System.out.println("depot exécuté, votre nouveau solde est de "+(s+montant));
                       preparedStatement.close();
                }
                if(Libele.equals("retrait")){
                        if((s-montant)>=10){
                                String sql = "INSERT INTO operation (Idclient, Libele, montant, DateOperation) VALUES (?, ?, ?, ?)";
                                 PreparedStatement  preparedStatement = this.connexion.getConnexion().prepareStatement(sql);
                                 preparedStatement.setInt(1,idclient );
                                 preparedStatement.setString(2,"retrait");
                                 preparedStatement.setInt(3, montant);
                                 preparedStatement.setDate(4, new java.sql.Date(d.getTime()));
                                 preparedStatement.executeUpdate(); 
                                 System.out.println("retrait exécuté, votre nouveau solde est de "+SoldeClient(idclient));
                                 preparedStatement.close();
                        }
                        else{
                            System.out.println("retrait impossible solde insufisant");
                        }
                }
         }
        catch(SQLException e){
             System.out.println(e);
         }
    }
      public void Creation(String nom){
       
           String sql="INSERT INTO Client(nom,solde) VALUES(?, ?)";
           
            int id=this.getClients().size()+1;
                    try{
               try ( //insertion client
                       PreparedStatement preparedStatement = this.connexion.getConnexion().prepareStatement(sql)) {
                   preparedStatement.setString(1,nom);
                   preparedStatement.setInt(2, 10);
                   preparedStatement.executeUpdate();
               }
                        //insertion Banquier_client
                         System.out.println("votre compte a été créer avec succès avec un solde de 10£ et votre id est "+id);
                    }
                    catch(SQLException e){
                    }
    }
    
}
