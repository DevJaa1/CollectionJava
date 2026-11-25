package List.FiltroTransacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Auditoria Financeira
public class List1 {

    public static List<Double> processarLote(List<Double> transacao) {

        /*
         * 
         * regras de verificação:
         * - Ver se a lista é nula
         * 
         * 
         * 1 - remover as transações que são menores que 10 sem criar outra lista
         * 
         * 2 - se a lista tiver 5 ou mais elementos da lista apos limpeza, devo criar
         * uma sublista com os ultimos 3 elementos e zerar o valor do primeiro elementos
         * da subslista e essa mudança deve
         * alterar a lista original
         * 
         * 3 - trazer as modificações e a lista original
         */

        if (transacao == null) {

            return List.of();

        }

        /*
         * usar o metodo removeIf() consigo remover usando metodo lamba, pra mais
         * eficiencia
         * aqui remove valores que são menores que 10 conforme
         * critério da atividade
         */

        transacao.removeIf(valor -> valor < 10.0 && valor != null);

        /*
         * Criação da sublista de elementos
         * segundo o critério é pra colocar apenas os 3 ultimos elementos após a
         * verificação
         * caso a lista tenha mais de 5 elementos
         * e setar 0 para o primeiro da sublista
         * 
         */

        int tamanho = transacao.size();
        if (tamanho >= 5) {
            // Devo criar uma sublista da Lista original com:
            List<Double> ultimosTres = transacao.subList(tamanho - 3, tamanho);

            /*
             * Aqui vou setar o primeiro elemento como 0, como manda a atividade
             * neste metodo o primeiro parametro é quem voce quer estabelecer e o segundo é
             * o valor que
             * voce deseja alterar.
             */

            ultimosTres.set(0, 0.0);

            // Retorna a lista solicitada

            /*
             * retorna a lista imutavel
             * com o metodo CopyOf()
             * Cria uma nova lista imutavel e desvinculada da lista original
             * Depois de manipular a lista original
             */

        }

        return List.copyOf(transacao);
    }

    public static void main(String[] args) {

        // Usamos new ArrayList<>(...) porque Arrays.asList() tem tamanho fixo e não
        // suporta removeIf
        List<Double> entrada = new ArrayList<>(Arrays.asList(5.0, 120.5, 8.9, 300.0, 45.0, 2.0, 90.0, 15.0));
        System.out.println("Lista Original : " + entrada);

        List<Double> resultado = processarLote(entrada);

        System.out.println("Lista original modificada: " + entrada);
        // Saída: [120.5, 300.0, 0.0, 90.0, 15.0]

        System.out.println("Lista retornada: " + resultado);
        // Saída: [120.5, 300.0, 0.0, 90.0, 15.0]

        // Prova de que 'resultado' é uma cópia independente e imutável:
        entrada.add(999.0); // Altera apenas 'entrada', não afeta 'resultado'
        
        System.out.println("Original após nova adição: " + entrada);
        System.out.println("Retornada permanece intacta: " + resultado);
    }

}
