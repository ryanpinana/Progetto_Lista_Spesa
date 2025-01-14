import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//Classe utilizzata per aggiungere dei prodotti ad una lista e a salvare una lista
public class Lista {
    private String nome;
    private Negozio negozio;
    private ArrayList<Prodotto> prodotti;

    public Lista(String nome, Negozio negozio) {
        this.nome = nome;
        this.negozio = negozio;
        this.prodotti = new ArrayList<>();
    }

    /*Metodo per aggiungere un prodotto alla lista
    * @param nomeProdotto Nome del prodotto che verrà aggiunto alla lista
    * @param quantita Quantità del prodotto che verrà aggiunto alla lista
     */
    public void aggiungiProdotto(String nomeProdotto, int quantita) {
        for (Prodotto prodotto : negozio.getCatalogo()) {
            if (prodotto.getNome().equalsIgnoreCase(nomeProdotto)) {  // IgnoreCase da StackOverflow, ignora maiuscole e minuscole
                prodotti.add(new Prodotto(prodotto.getNome(), prodotto.getPrezzo(), quantita));
                System.out.println("Prodotto aggiunto alla lista.");
                return;
            }
        }
        System.out.println("Prodotto non trovato nel catalogo del negozio.");
    }

    /*
    * BufferWriter e FileWriter grazie a Chat GPT,
    * metodo di lettura e scrittura molto efficente ed unico che funziona
    * Con Files e Paths non so perchè non funzionava.
    * Metodo per salvatre la lista in un file txt
     */
    public void salvaListaInFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nome + ".txt"))) {
            writer.write("Lista della spesa: " + nome + "\n");
            writer.write("Negozio: " + negozio.getNome() + "\n\n");
            for (Prodotto prodotto : prodotti) {
                writer.write(prodotto.toString() + "\n");
            }
            System.out.println("Lista salvata nel file " + nome + ".txt");
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio della lista: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista della spesa: ").append(nome).append("\n");
        sb.append("Negozio: ").append(negozio.getNome()).append("\n\n");
        for (Prodotto prodotto : prodotti) {
            sb.append(prodotto).append("\n");
        }
        return sb.toString();
    }
}