package business.operations;

import utils.DateUtils;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Operation {
    protected String numero;
    protected LocalDateTime date;
    protected double montant;
    
    public Operation(double montant) {
        this.numero = UUID.randomUUID().toString();
        this.date = DateUtils.getCurrentDateTime();
        this.montant = montant;
    }
    
    public String getNumero() { 
        return numero; 
    }
    public LocalDateTime getDate() { 
        return date; 
    }
    public double getMontant() { 
        return montant; 
    }
    
    @Override
    public String toString() {
        return String.format("Operation: %s | Date: %s | Montant: %.2fMAD", 
            numero.substring(0, 8), DateUtils.formatDateTime(date), montant);
    }
}