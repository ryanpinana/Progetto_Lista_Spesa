/*
*Programma per la gestione e creazione di liste della spesa
*
* @author Ryan Pinana
* @version 14/1/2025
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Negozio> negozi = new ArrayList<>();
        ArrayList<Lista> liste = new ArrayList<>();

        // Creazione di negozi e prodotti
        Negozio supermercato = new Negozio("Supermercato ABC", "Via Roma 1");
        supermercato.aggiungiProdotto(new Prodotto("Latte", 1.50, 50));
        supermercato.aggiungiProdotto(new Prodotto("Pane", 2.00, 30));
        supermercato.aggiungiProdotto(new Prodotto("Pasta", 1.20, 40));

        Negozio negozioBio = new Negozio("Negozio Bio", "Via Verde 10");
        negozioBio.aggiungiProdotto(new Prodotto("Mela Bio", 0.80, 100));
        negozioBio.aggiungiProdotto(new Prodotto("Farina Integrale", 3.50, 20));
        negozioBio.aggiungiProdotto(new Prodotto("Yogurt Bio", 1.00, 25));

        negozi.add(supermercato);
        negozi.add(negozioBio);

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Crea una nuova lista");
            System.out.println("2. Visualizza una lista esistente");
            System.out.println("3. Visualizza il catalogo di un negozio");
            System.out.println("4. Esci");
            System.out.print("Scelta: ");
            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            //Switch da StackOverflaw, molto più efficente dell'if in questo caso
            switch (scelta) {
                case 1:
                    creaNuovaLista(negozi, liste);
                    break;
                case 2:
                    visualizzaListaDaFile();
                    break;
                case 3:
                    visualizzaCatalogoNegozio(negozi);
                    break;
                case 4:
                    System.out.println("Uscita dal programma.");
                    return;
                default:
                    System.out.println("Scelta non valida. Riprova.");
            }
        }
    }

    /*metodo per la creazione di liste
    * @param negozi Lista di negozi disponibili
    * @param liste Lista di liste già esistenti
     */
    private static void creaNuovaLista(ArrayList<Negozio> negozi, ArrayList<Lista> liste) {
        System.out.print("Inserisci il nome della lista: ");
        String nomeLista = scanner.nextLine();

        if (negozi.isEmpty()) {
            System.out.println("Nessun negozio disponibile. Creane uno prima di creare una lista.");
            return;
        }

        System.out.println("Scegli un negozio:");
        for (int i = 0; i < negozi.size(); i++) {
            System.out.println((i + 1) + ". " + negozi.get(i));
        }
        System.out.print("Scelta: ");
        int sceltaNegozio = scanner.nextInt();
        scanner.nextLine();

        if (sceltaNegozio < 1 || sceltaNegozio > negozi.size()) {
            System.out.println("Scelta non valida.");
            return;
        }

        Negozio negozioScelto = negozi.get(sceltaNegozio - 1);

        for (Negozio negozio : negozi) {
            if (negozio.getNome().equalsIgnoreCase(negozioScelto.getNome())) {
                System.out.println("Catalogo del negozio " + negozio.getNome() + ":");
                for (Prodotto prodotto : negozio.getCatalogo()) {
                    System.out.println(prodotto);
                }
            }
        }

        Lista nuovaLista = new Lista(nomeLista, negozioScelto);

        while (true) {
            System.out.print("Inserisci il nome del prodotto da aggiungere (o 'fine' per terminare): ");
            String nomeProdotto = scanner.nextLine();
            if (nomeProdotto.equalsIgnoreCase("fine")) break;
            //trovato su StackOverflaw, solo in un unico caso fa qualcosa ed ignora le maiuscole

            System.out.print("Inserisci la quantità: ");
            int quantita = scanner.nextInt();
            scanner.nextLine();

            nuovaLista.aggiungiProdotto(nomeProdotto, quantita);
        }

        nuovaLista.salvaListaInFile();
        liste.add(nuovaLista);
    }

    //metodo per visualizzare una lista esistente
    private static void visualizzaListaDaFile() {
        System.out.print("Inserisci il nome del file della lista da visualizzare (senza estensione): ");
        String nomeFile = scanner.nextLine();
        Path filePath = Path.of(nomeFile + ".txt");

        if (!filePath.toFile().exists()) {
            System.out.println("Il file non esiste.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
        }
    }

    //metodo per visualizzare un catalogo di un negozio
    //@param negozi La lista dei negozi disponibili
    private static void visualizzaCatalogoNegozio(ArrayList<Negozio> negozi) {
        if (negozi.isEmpty()) {
            System.out.println("Nessun negozio disponibile.");
            return;
        }

        System.out.print("Inserisci il nome del negozio: ");
        String nomeNegozio = scanner.nextLine();

        for (Negozio negozio : negozi) {
            if (negozio.getNome().equalsIgnoreCase(nomeNegozio)) {
                System.out.println("Catalogo del negozio " + negozio.getNome() + ":");
                for (Prodotto prodotto : negozio.getCatalogo()) {
                    System.out.println(prodotto);
                }
                return;
            }
        }

        System.out.println("Negozio non trovato.");
    }
}