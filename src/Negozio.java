import java.util.ArrayList;

//Classe per la gestione dei Negozi
public class Negozio {
    private String nome;
    private String indirizzo;
    private ArrayList<Prodotto> catalogo;

    public Negozio(String nome, String indirizzo) {
        this.nome = nome;
        this.indirizzo = indirizzo;
        this.catalogo = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public ArrayList<Prodotto> getCatalogo() {
        return catalogo;
    }

    public void aggiungiProdotto(Prodotto prodotto) {
        catalogo.add(prodotto);
    }

    public boolean rimuoviProdotto(String nomeProdotto) {
        return catalogo.removeIf(prodotto -> prodotto.getNome().equalsIgnoreCase(nomeProdotto));
        //Da StackOverflow, rimuove solo se trova un prodotto con lo stesso nome
    }

    @Override
    public String toString() {
        return nome + " - Indirizzo: " + indirizzo;
    }
}