package List.ControleEstoque;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControleEstoque {

    public static int atualizarEstoque(List <String> loteChegada, List<String> prateleira) {

        if(prateleira == null) {
            return 0;

        }
        /*
         * O sistema deve remover os itens que começar com OBS, significa OBSoleto da prateleira
         *
         * Adicionar os itens da chegada na prateleira, mas se tiver algum item que ja 
         * existe na prateleira, esse item do loteChegada não deve ir para a Prateleira
         * somente adicionar os itens novos que nao tem na prateleira (Sem modificar a loteChegada)
         * 
         * o item que tiver codigo PROMO deve ser o PRIMEIRO item da prateleira, [INDICE 0], mesmo ja estando 
         * ou vindo do loteChegada.
         * 
         * No fim, o metodo deve retornar a quantidade de item que ficou na prateleira ao final do processo.
        */


        //Remover item que começa com OBS
        prateleira.removeIf(item -> item.startsWith("OBS-") && item != null);


        /*
         * Sempre criar um condicionamento para verificar se a loteChegada tem item ou pelo menos um item, se tiver, podemos 
         * criar uma copia da lista para manipular.
        */

        if(loteChegada != null && !loteChegada.isEmpty()) {

            /* Criei uma lista copia de loteChegada, porque a lista original é imutável, então, sempre que manipular
             * sempre criar uma lista copia para manipular
            */

            List<String> itensNovos = new ArrayList<>(loteChegada);

            /*
             * Remove da cópia tudo o que já existe na prateleira (diferença de conjuntos)
             * Aqui vai ser o seguinte, na lista loteChegada vai ter os itens, e em prateleira os itens ("1,2,3"), o itensNovos.removeAll()
             * eu escolho a lista que eu quero retirar os itens, no caso o itensNovos, quero remover dele o que ja tem em prateleira.
             * 
             * então o metodo remove de itensNovos (cópia de loteChegada), os itens que tem em prateleira, ou seja
             * ele pega os itens de prateleira como parametro e compara se dentro da lista loteChegada se tem os itens de prateleira e remove
             * de itensNovos (copia de lote chegada)
             */

            itensNovos.removeAll(prateleira); // aqui so tem itens que não tem na prateleira e removeu os que ja tinha

            prateleira.addAll(itensNovos); //Aqui adicionar os itens novos que ficou em itensNovos na prateleira.

        }

        //Aqui ele remove primeiro o PROMO-999 porque ele precisa sair da lista para ser reposto na primeira posiçaõ do INDICE 0
        //Posteriormente usando o set(0, "PROMO-999"), restabelece a sua posição da lista como primeiro de tudo, independente de qualquer coisa

        if(prateleira.remove("PROMO-999")) {
            prateleira.add(0, "PROMO-999");
        }

        //Como o metodo é inteiro, ele retorna um valor inteiro representando a quantidade de itens que tem na prateleira
        return prateleira.size();


    }

    public static void main(String[] args) {

        List<String> prateleira = new ArrayList<>(Arrays.asList(
            "SKU-10", "OBS-01", "SKU-20", "OBS-02"
        ));

        List<String> loteChegada = List.of(
            "SKU-20", "SKU-30", "PROMO-999", "SKU-40"
        );

        int totalFinal = atualizarEstoque(loteChegada, prateleira);

        System.out.println("Prateleira atualizada: " + prateleira);
        // Saída: [PROMO-999, SKU-10, SKU-20, SKU-30, SKU-40]

        System.out.println("Total de itens: " + totalFinal);
        // Saída: 5
    }

}
