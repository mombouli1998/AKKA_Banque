package sd.akka.actor;

import akka.actor.AbstractActor;
import akka.actor.Props;


public class Banquier extends AbstractActor {
    private Connexion connexion;

    private Banquier(Connexion connexion){
        this.connexion = connexion;
    }

  // Méthode servant à déterminer le comportement de l'acteur lorsqu'il reçoit un message
	@Override
	public Receive createReceive() {
		return receiveBuilder()
				.match(Depot.class, message -> depot(message))
				.match(Retrait.class, message -> retrait(message))
                                .match(Creation.class, message -> creation(message))
				.build();
	}

	private void depot(final Depot message) {
                 int montant=message.montant;
                 int id=message.id;
                 
                 Requete requette = new Requete(this.connexion);
                 requette.Operation(id,"depot", montant);
	}

	private void retrait(final Retrait message) {
		  int montant=message.montant;
                 int id=message.id;
                 Requete requette = new Requete(this.connexion);
                 requette.Operation(id,"retrait", montant);
	}
        private void creation(final Creation message) {
		 
                 String name=message.name;
                 Requete requette = new Requete(this.connexion);
                 requette.Creation(name);
	}


	// Méthode servant à la création d'un acteur
	public static Props props(Connexion c) {
		return Props.create(Banquier.class,c);
	}

	// Définition des messages en inner classes
	public interface Message {}
	
	public static class Retrait implements Message {
               public final int montant;
                public int id;
		public Retrait(int id,int montant) {
                         this.id=id;
                        this.montant=montant;}
	}	
	
	public static class Depot implements Message {
                public final int montant;
                public int id;
		public Depot(int id,int montant) {
                        this.id=id;
                        this.montant=montant;
		}
	}
        public static class Creation implements Message {
                
                public String name;
		public Creation(String name) {
                     
                        this.name=name;
		}
	}
}
