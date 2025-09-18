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
}