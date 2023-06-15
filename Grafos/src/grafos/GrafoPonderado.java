package grafos;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GrafoPonderado {
    private Map<Integer, Map<Integer, Integer>> matrizAdjacencia;

    public GrafoPonderado() {
        matrizAdjacencia = new HashMap<>();
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        matrizAdjacencia.computeIfAbsent(origem, k -> new HashMap<>()).put(destino, peso);
        matrizAdjacencia.computeIfAbsent(destino, k -> new HashMap<>()).put(origem, peso);
    }

    public void removerAresta(int origem, int destino) {
        Map<Integer, Integer> vizinhosOrigem = matrizAdjacencia.get(origem);
        if (vizinhosOrigem != null) {
            vizinhosOrigem.remove(destino);
        }

        Map<Integer, Integer> vizinhosDestino = matrizAdjacencia.get(destino);
        if (vizinhosDestino != null) {
            vizinhosDestino.remove(origem);
        }
    }

    public void adicionarVertice(int vertice) {
        if (!matrizAdjacencia.containsKey(vertice)) {
            matrizAdjacencia.put(vertice, new HashMap<>());
        }
    }

    public void removerVertice(int vertice) {
        matrizAdjacencia.remove(vertice);
        matrizAdjacencia.values().forEach(vizinhos -> vizinhos.remove(vertice));
    }

    public void imprimirGrafo() {
        System.out.println("Grafo Ponderado - Matriz de Adjacência:");
        System.out.print("   ");

        for (Integer vertice : matrizAdjacencia.keySet()) {
            System.out.print(vertice + " ");
        }
        System.out.println();

        for (Integer origem : matrizAdjacencia.keySet()) {
            System.out.print(origem + "  ");
            Map<Integer, Integer> vizinhos = matrizAdjacencia.get(origem);

            for (Integer destino : matrizAdjacencia.keySet()) {
                int peso = vizinhos.getOrDefault(destino, 0);
                System.out.print(peso + " ");
            }
            System.out.println();
        }

        System.out.println("\nGrafo Ponderado - Representação visual:");
        for (Integer origem : matrizAdjacencia.keySet()) {
            System.out.print(origem + " -> ");
            Map<Integer, Integer> vizinhos = matrizAdjacencia.get(origem);

            for (Integer destino : vizinhos.keySet()) {
                int peso = vizinhos.get(destino);
                System.out.print(destino + "(" + peso + ") ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        GrafoPonderado grafo = new GrafoPonderado();

        boolean executando = true;
        while (executando) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Adicionar vértice");
            System.out.println("2. Remover vértice");
            System.out.println("3. Adicionar aresta");
            System.out.println("4. Remover aresta");
            System.out.println("5. Imprimir grafo");
            System.out.println("0. Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o número do vértice: ");
                    int vertice = scanner.nextInt();
                    grafo.adicionarVertice(vertice);
                    break;
                case 2:
                    System.out.print("Digite o número do vértice a ser removido: ");
                    int verticeRemovido = scanner.nextInt();
                    grafo.removerVertice(verticeRemovido);
                    break;
                case 3:
                    System.out.print("Digite o número do vértice de origem: ");
                    int origem = scanner.nextInt();
                    System.out.print("Digite o número do vértice de destino: ");
                    int destino = scanner.nextInt();
                    System.out.print("Digite o peso da aresta: ");
                    int peso = scanner.nextInt();
                    grafo.adicionarAresta(origem, destino, peso);
                    break;
                case 4:
                    System.out.print("Digite o número do vértice de origem: ");
                    int origemRemover = scanner.nextInt();
                    System.out.print("Digite o número do vértice de destino: ");
                    int destinoRemover = scanner.nextInt();
                    grafo.removerAresta(origemRemover, destinoRemover);
                    break;
                case 5:
                    grafo.imprimirGrafo();
                    break;
                case 0:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }

        scanner.close();
    }
}
