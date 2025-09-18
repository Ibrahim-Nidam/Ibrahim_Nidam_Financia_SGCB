package business.accounts;

import business.operations.Operation;
import java.util.ArrayList;
import java.util.List;

public abstract class Compte {
    protected String code;
    protected double solde;
    protected List<Operation> listeOperations;
    
    public Compte(String code, double soldeInitial) {
        this.code = code;
        this.solde = soldeInitial;
        this.listeOperations = new ArrayList<>();
    }
    
    public abstract boolean retirer(double montant, String destination);
    public abstract double calculerInteret();
    public abstract void afficherDetails();
    
    public String getCode() { 
        return code;
    }
    public double getSolde() { 
        return solde; 
    }
    public List<Operation> getListeOperations() { 
        return new ArrayList<>(listeOperations); 
    }
    
    protected void addOperation(Operation operation) {
        this.listeOperations.add(operation);
    }

    public void verser(double montant, String source) {
        this.solde += montant;
        this.listeOperations.add(new business.operations.Versement(montant, source));
    }
}