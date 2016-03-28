package br.calebe.ticketmachine.core;

import br.calebe.ticketmachine.core.Troco.TrocoIterator;
import br.calebe.ticketmachine.exception.PapelMoedaInvalidaException;
import br.calebe.ticketmachine.exception.SaldoInsuficienteException;
import java.util.Iterator;

/**
 *
 * @author Calebe de Paula Bianchini
 */
public class TicketMachine {

    protected int valor;
    protected int saldo;
    protected int[] papelMoeda = {2, 5, 10, 20, 50, 100};
    protected Troco troco;

    public TicketMachine(int valor) {
        this.valor = valor;
        this.saldo = 0;
    }

    public void inserir(int quantia) throws PapelMoedaInvalidaException {
        boolean achou = false;
        for (int i = 0; i < papelMoeda.length && !achou; i++) {
            if (papelMoeda[i] == quantia) {
                this.saldo += quantia;
            }
        }
        if (!achou) {
            throw new PapelMoedaInvalidaException();
        }

    }

    public int getSaldo() {
        return saldo;
    }

    public Iterator<PapelMoeda> getTroco() {
        return this.troco.getIterator();
    }

    public String imprimir() throws SaldoInsuficienteException {
        if (saldo < valor) {
            throw new SaldoInsuficienteException();
        } else {
            saldo-= valor;
            this.troco = new Troco(saldo);
            String result = "*****************\n";
            result += "*** R$ " + saldo + ",00 ****\n";
            result += "*****************\n";
            return result;
        }
    }
}
