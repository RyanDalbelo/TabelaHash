import java.util.Random;

public class TabelaHash {

    static class No {
        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    public static void main(String[] args) {

        No[] tabela = new No[100];

        int[] tamanho = new int[100];

        Random random = new Random(1090);

        int listasEncadeadas = 0;
        int posicoesVazias = 0;

        int ultimo1 = -1;
        int ultimo2 = -1;
        int ultimo3 = -1;

        // Inserção dos 1000 números
        for (int i = 0; i < 1000; i++) {

            int valor = random.nextInt(100001);
            int posicao = valor % 100;

            No novo = new No(valor);

            // Posição vazia
            if (tabela[posicao] == null) {

                tabela[posicao] = novo;
                tamanho[posicao] = 1;

            } else {

                // Ocorreu uma colisão
                if (tamanho[posicao] == 1) {
                    listasEncadeadas++;
                }

                No atual = tabela[posicao];

                while (atual.proximo != null) {
                    atual = atual.proximo;
                }

                atual.proximo = novo;
                tamanho[posicao]++;

                // Guarda os três últimos valores inseridos em listas
                ultimo1 = ultimo2;
                ultimo2 = ultimo3;
                ultimo3 = valor;
            }
        }

        // Conta as posições vazias e encontra a maior lista
        int maiorTamanho = 0;
        int posicaoMaior = -1;

        for (int i = 0; i < 100; i++) {

            if (tabela[i] == null) {
                posicoesVazias++;
            }

            if (tamanho[i] > maiorTamanho) {
                maiorTamanho = tamanho[i];
                posicaoMaior = i;
            }
        }

        // Imprime a maior lista encadeada
        System.out.println("Maior lista encadeada:");

        if (posicaoMaior != -1) {

            No atual = tabela[posicaoMaior];

            while (atual != null) {
                System.out.print(atual.valor + " -> ");
                atual = atual.proximo;
            }

            System.out.println("null");
            System.out.println("Tamanho: " + maiorTamanho);
            System.out.println("Posição: " + posicaoMaior);
        }

        // Resultados finais
        System.out.println("\n===== RESULTADOS =====");

        System.out.println(
                "Quantidade de listas encadeadas: "
                        + listasEncadeadas
        );

        System.out.println(
                "Posições vazias: " + posicoesVazias
        );

        System.out.println("Três últimos valores inseridos em listas:");

        if (ultimo1 != -1) {
            System.out.println(ultimo1);
        }

        if (ultimo2 != -1) {
            System.out.println(ultimo2);
        }

        if (ultimo3 != -1) {
            System.out.println(ultimo3);
        }
    }
}