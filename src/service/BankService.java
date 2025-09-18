package service;

import business.accounts.*;
import business.operations.Operation;
import utils.ValidationUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class BankService {
    private Map<String, Compte> comptes;
    
    public BankService() {
        this.comptes = new HashMap<>();
    }
    
    public boolean creerCompteCourant(String code, double soldeInitial) {
        try {
            if (!ValidationUtils.isValidAccountCode(code)) {
                throw new IllegalArgumentException("Format de code invalide. Utilisez 5 chiffres Max");
            }
            
            if (comptes.containsKey(code)) {
                throw new IllegalArgumentException("Un compte avec ce code existe déjà");
            }
            
            if (soldeInitial < 0) {
                throw new IllegalArgumentException("Le solde initial ne peut pas être négatif");
            }
            
            Compte compte = new CompteCourant(code, soldeInitial);
            comptes.put(code, compte);
            return true;
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du compte: " + e.getMessage());
            return false;
        }
    }
    
    public boolean creerCompteEpargne(String code, double soldeInitial) {
        try {
            if (!ValidationUtils.isValidAccountCode(code)) {
                throw new IllegalArgumentException("Format de code invalide. Utilisez 5 chiffres Max");
            }
            
            if (comptes.containsKey(code)) {
                throw new IllegalArgumentException("Un compte avec ce code existe déjà");
            }
            
            if (soldeInitial < 0) {
                throw new IllegalArgumentException("Le solde initial ne peut pas être négatif");
            }
            
            Compte compte = new CompteEpargne(code, soldeInitial);
            comptes.put(code, compte);
            return true;
            
        } catch (Exception e) {
            System.err.println("Erreur lors de la création du compte: " + e.getMessage());
            return false;
        }
    }
    
    public boolean effectuerVersement(String codeCompte, double montant, String source) {
        try {
            if (!ValidationUtils.isValidAmount(montant)) {
                throw new IllegalArgumentException("Le montant doit être positif");
            }
            
            Compte compte = comptes.get(codeCompte);
            if (compte == null) {
                throw new IllegalArgumentException("Compte non trouvé");
            }
            
            compte.verser(montant, source);
            return true;
            
        } catch (Exception e) {
            System.err.println("Erreur lors du versement: " + e.getMessage());
            return false;
        }
    }
}