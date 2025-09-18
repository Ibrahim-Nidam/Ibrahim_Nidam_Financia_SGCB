package business.accounts;

import business.operations.Retrait;

public class CompteEpargne extends Compte {
    private double tauxInteret;
    
    public CompteEpargne(String code, double soldeInitial) {
        super(code, soldeInitial);
        this.tauxInteret = 0.02;
    }
    
    @Override
    public boolean retirer(double montant, String destination) {
        if (solde >= montant) {
            solde -= montant;
            addOperation(new Retrait(montant, destination));
            return true;
        }
        return false;
    }
    
    @Override
    public double calculerInteret() {
        return solde * tauxInteret;
    }
    
    @Override
    public void afficherDetails() {
        System.out.println("=== COMPTE EPARGNE ===");
        System.out.println("Code: " + code);
        System.out.printf("Solde: %.2fMAD%n", solde);
        System.out.printf("Taux d'intérêt: %.2f%%%n", tauxInteret * 100);
        System.out.printf("Intérêts annuels: %.2fMAD%n", calculerInteret());
        System.out.println("Nombre d'opérations: " + listeOperations.size());
    }
    
    public double getTauxInteret() { 
        return tauxInteret; 
    }
}