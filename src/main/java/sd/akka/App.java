
package sd.akka;

import sd.akka.actor.*;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import java.time.Duration;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class App {

	public static void main(String[] args) throws InterruptedException {

        Calendar calendar = Calendar.getInstance(); // Récupère la date du jour
        Date laDate = calendar.getTime(); // Récupère la date du jour au format date

    

        //Chargement des pilotes et ouverture de la base de données
        Connexion connexion = new Connexion();
        connexion.chargerLePilote();
        connexion.connexionBDD();
        //création d'acteur
        ActorSystem actorSystem = ActorSystem.create();
        ActorRef Banques = actorSystem.actorOf(Banque.props(connexion));
        //retrait de 10euro de mombouli avec un id qui n'est pas le siens
         Banques.tell(new Banque.Retrait(2,"MOMBOULI", 10), ActorRef.noSender());
         //Retrait de 10 euro par Dupont
         Banques.tell(new Banque.Retrait(2,"Dupont", 10), ActorRef.noSender());
       // Depot de 10 euro par le client Junior n'existant pas dans la base de données
       Banques.tell(new Banque.Depot(3,"junior", 10), ActorRef.noSender());
      
        
        actorSystem.terminate();
	}
}
