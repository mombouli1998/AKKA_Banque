package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;



import java.util.Hashtable;


public class Banque extends AbstractActor {

    Hashtable<Integer, String> ListeClient;
    private Connexion connexion;

    private Banque(Connexion connexion){
        //Initialisation des hashtables et de la connexion
        
        this.ListeClient= new Hashtable<Integer,String>();

        this.connexion = connexion;

        //Création des acteurs enfants
        creationActeursClient();
     
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                                .match(Depot.class, message -> depot(message))
				.match(Retrait.class, message -> retrait(message))
				.build();
    }

    //Récupération de tous les clients dans la base de données
    public void creationActeursClient() {
        Requete requeteBD = new Requete(this.connexion);
        this.ListeClient = requeteBD.getClients();
    }

    //Creation des acteurs pour tous les banquiers dans la base de données
    

    public static Props props(Connexion connexion) {
        return Props.create(Banque.class, connexion);
    }
        private void depot(final Depot message) {
                     String name = message.name;
		   int id  = message.id;
                   int montant = message.montant;
                    
                  
                      
                     if(!Correspondance(this.ListeClient,id, name)){
                         
                             ActorRef banquier;
                             banquier = getContext().actorOf(Banquier.props(this.connexion));
                             banquier.tell(new Banquier.Creation(name), ActorRef.noSender());
                     }
                     else if(!Correspondance(this.ListeClient,id, name)){
  
                            ActorRef banquier;
                             banquier = getContext().actorOf(Banquier.props(this.connexion));
                             banquier.tell(new Banquier.Depot(id, montant), ActorRef.noSender());
                        }
                      
                 
                  
	}

	private void retrait(final Retrait message) {
		  String name = message.name;
		   int id  = message.id;
                   int montant = message.montant;
                     
                  
                      
                   if(Correspondance(this.ListeClient,id, name)){
                       
                           ActorRef banquier;
                           banquier = getContext().actorOf(Banquier.props(this.connexion));
                           banquier.tell(new Banquier.Creation(name), ActorRef.noSender());
                   }
                   else if(Correspondance(this.ListeClient,id, name)){

                          ActorRef banquier;
                           banquier = getContext().actorOf(Banquier.props(this.connexion));
                           banquier.tell(new Banquier.Retrait(id, montant), ActorRef.noSender());
                      }
                     
                        
   }
	
     
	

	// Définition des messages en inner classes
	public interface Message {}
	
	  public static class Retrait implements Message {
		public final String name;
		public final int montant;
		public final int id;
		public Retrait(int id,String name,int montant) {
			this.name = name;
			this.montant=montant;
			this.id=id;
                }
	}
          
	public static class Depot implements Message {
		public final String name;
		public final int montant;
		public final int id;
		public Depot(int id,String name,int montant) {
			this.name = name;
			this.montant=montant;
			this.id=id;
                }
	}
    public static boolean Correspondance(Hashtable<Integer,String> ls, int t, String v) {
        // Vérifie si la clé 't' est présente dans la Hashtable
        if (ls.containsKey(t)) {
            // Récupère la valeur associée à la clé 't'
            String valeur = ls.get(t);
            // Vérifie si la valeur est égale à 'v'
            return valeur.equals(v);
        } else {
            return false;
        }
    }
     
     
      
}
