package List.RankingEcommerce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Produto {

    private String nome;
    private double preco;
    private double avaliacao;
    private String categoria;
    private int estoque;

    public Produto(String nome, String categoria, double preco, double avaliacao, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.avaliacao = avaliacao;
        this.categoria = categoria;
        this.estoque = estoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - R$ %.2f | ★ %.1f | Est: %d",
                nome, categoria, preco, avaliacao, estoque);

    }

    public static class MotorVitrine {

        List<Produto> produtos = new ArrayList<>();

        public static List<Produto> gerarTopVitrine(List<Produto> produtos) {

            /*
             * - remover os itens da lista original que estiverem com o estoque abaixo de 0
             * ou seja NULL
             * - para o restante dos produtos, aplicar desconto de 10% para os da categoria
             * ELETRONICOS
             * - usar ignoreCase para pegar a categoria pelo nome e estabelecer padrão de
             * filtro e aplicar desconto
             * - Caso a lista resultante tenha mais de 3 produtos, devo retornar os 3
             * maiores e a lista deve ser imutavel
             * caso contratio retorna a lsita do jeito que ta sendo imutavel tambem
             */

            // remover item da lista original que tiver estoque menor igual a 0 = null

            if (produtos == null) {
                return List.of();
            }

            // remove da lista o que tem estoque menor igual a 0
            produtos.removeIf(x -> x == null || x.getEstoque() <= 0);

            // da desconto para produtos que pertencem a categoria eletronico
            double fatorDesconto = 0.90;
            for (Produto x : produtos) {
                if ("eletronicos".equalsIgnoreCase(x.getCategoria())) {
                    x.setPreco(x.getPreco() * fatorDesconto);
                }
            }

            // Ordenar lista por avaliação
            produtos.sort(
                    Comparator.comparingDouble(Produto::getAvaliacao).reversed().thenComparingDouble(Produto::getPreco)
                            .thenComparing(Produto::getNome, String.CASE_INSENSITIVE_ORDER));

                            
                            int limite = Math.min(3, produtos.size());
                            return List.copyOf(produtos.subList(0,limite));
        }
        
        // Critério de limite de produto

    }

    public static void main(String[] args) {
        List<Produto> catalogo = new ArrayList<>(Arrays.asList(
            new Produto("Mouse Gamer", "Eletronicos", 200.0, 4.8, 10),     // Cai para 180.0
            new Produto("Teclado Mecânico", "ELETRONICOS", 200.0, 4.8, 5), // Cai para 180.0 (empate com Mouse -> ordem alfabética)
            new Produto("Cadeira Ergonomica", "Moveis", 1200.0, 4.9, 0),   // Removido (estoque 0)
            new Produto("Monitor 27", "Eletronicos", 1000.0, 4.9, 3),      // Cai para 900.0 (1º lugar: nota 4.9)
            new Produto("Livro Clean Code", "Livros", 90.0, 4.8, 15),      // Preço 90.0 (2º lugar: nota 4.8, mais barato que 180)
            new Produto("Cafeteira", "Eletro", 300.0, 4.5, 8)              // Fica fora do Top 3
        ));

        List<Produto> top3 = MotorVitrine.gerarTopVitrine(catalogo);
        top3.forEach(System.out::println);
    }

}

