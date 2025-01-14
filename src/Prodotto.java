//Classe per la gestione dei prodotti
public class Prodotto {
    private String nome;
    private double prezzo;
    private int quantita;

    public Prodotto(String nome, double prezzo, int quantita) {
        this.nome = nome;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    @Override
    public String toString() {
        return nome + " - Prezzo: " + prezzo + " €, Quantità: " + quantita;
    }
}
