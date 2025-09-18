package ui;

import service.BankService;
import business.accounts.Compte;
import java.util.Scanner;

public class ConsoleUI {
    private BankService bankService;
    private Scanner scanner;
    
    public ConsoleUI() {
        this.bankService = new BankService();
        this.scanner = new Scanner(System.in);
    }
    
    public void demarrer() {
        System.out.println("=================================");
        System.out.println("   SYSTEME DE GESTION BANCAIRE   ");
        System.out.println("=================================");
        
        while (true) {
            afficherMenu();
            int choix = lireChoixUtilisateur();
            
            switch (choix) {
                case 1:
                    creerCompte();
                    break;
                case 2:
                    effectuerVersement();
                    break;
                case 3:
                    effectuerRetrait();
                    break;
                case 4:
                    effectuerVirement();
                    break;
                case 5:
                    consulterSolde();
                    break;
                case 6:
                    consulterOperations();
                    break;
                case 7:
                    afficherTousLesComptes();
                    break;
                case 0:
                    System.out.println("Merci d'avoir utilisé notre système bancaire. Au revoir!");
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
            }
        }
    }
    
    private void afficherMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Créer un compte");
        System.out.println("2. Effectuer un versement");
        System.out.println("3. Effectuer un retrait");
        System.out.println("4. Effectuer un virement");
        System.out.println("5. Consulter le solde");
        System.out.println("6. Consulter les opérations");
        System.out.println("7. Afficher tous les comptes");
        System.out.println("0. Quitter");
        System.out.print("Votre choix: ");
    }
    
    private int lireChoixUtilisateur() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String lireCodeCompte() {
        System.out.print("Code du compte (CPT-");
        String codeNumerique = scanner.nextLine();
        return "CPT-" + codeNumerique;
    }
    
    private void creerCompte() {
        System.out.println("\n=== CREATION DE COMPTE ===");
        System.out.println("1. Compte Courant (découvert par défaut: 400MAD)");
        System.out.println("2. Compte Epargne (taux par défaut: 2%)");
        System.out.print("Type de compte: ");
        
        int typeCompte = lireChoixUtilisateur();
        
        String code = lireCodeCompte();
        
        System.out.print("Solde initial: ");
        double soldeInitial;
        try {
            soldeInitial = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Montant invalide.");
            return;
        }
        
        boolean succes = false;
        
        if (typeCompte == 1) {
            succes = bankService.creerCompteCourant(code, soldeInitial);
        } else if (typeCompte == 2) {
            succes = bankService.creerCompteEpargne(code, soldeInitial);
        } else {
            System.out.println("Type de compte invalide.");
            return;
        }
        
        if (succes) {
            System.out.println("Compte créé avec succès!");
        }
    }
    
    private void effectuerVersement() {
        System.out.println("\n=== VERSEMENT ===");
        String code = lireCodeCompte();
        
        System.out.print("Montant à verser: ");
        double montant;
        try {
            montant = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Montant invalide.");
            return;
        }
        
        System.out.print("Source du versement: ");
        String source = scanner.nextLine();
        
        if (bankService.effectuerVersement(code, montant, source)) {
            System.out.println("Versement effectué avec succès!");
        }
    }
    
    private void effectuerRetrait() {
        System.out.println("\n=== RETRAIT ===");
        String code = lireCodeCompte();
        
        System.out.print("Montant à retirer: ");
        double montant;
        try {
            montant = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Montant invalide.");
            return;
        }
        
        System.out.print("Destination du retrait: ");
        String destination = scanner.nextLine();
        
        if (bankService.effectuerRetrait(code, montant, destination)) {
            System.out.println("Retrait effectué avec succès!");
        }
    }
    
    private void consulterSolde() {
        System.out.println("\n=== CONSULTATION SOLDE ===");
        String code = lireCodeCompte();
        
        Compte compte = bankService.consulterCompte(code);
        if (compte != null) {
            compte.afficherDetails();
        } else {
            System.out.println("Compte non trouvé.");
        }
    }
}