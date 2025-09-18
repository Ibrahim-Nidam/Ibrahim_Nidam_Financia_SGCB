package business.accounts;

import business.operations.Retrait;

public class CompteCourant extends Compte {
    private double decouvert;
    
    public CompteCourant(String code, double soldeInitial) {
        super(code, soldeInitial);
        this.decouvert = 400;
    }
    
    @Override
    public boolean retirer(double montant, String destination) {
        if (solde - montant >= -decouvert) {
            solde -= montant;
            return true;
        }
        return false;
    }
    
    @Override
    public double calculerInteret() {
        return 0;
    }
    
    @Override
    public void afficherDetails() {
        System.out.println("=== COMPTE COURANT ===");
        System.out.println("Code: " + code);
        System.out.printf("Solde: %.2fMAD%n", solde);
        System.out.printf("Découvert autorisé: %.2fMAD%n", decouvert);
        System.out.println("Nombre d'opérations: " + listeOperations.size());
    }
    
    public double getDecouvert() { 
        return decouvert; 
    }
}