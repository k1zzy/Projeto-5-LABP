package project;

/**
 * Classe que representa um atleta de uma corrida.
 * 
 * @author Rodrigo Afonso (61839)
 * @version 1.0
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import project.comparators.ComparaAtletaNome;
import project.comparators.ComparaRegistoPassagemTempo;

public class Atleta implements Comparable<Atleta> {
    
    private final int dorsal; // usado na comparação de Atletas por omissão através do compareTo()
    private final String nome;
    private final String nacionalidade;
    private final String escalao;
    private int posicaoFinalAbsoluta;
    private int posicaoFinalEscalao;
    private int[] tempoNaMeta; // {horas, minutos, segundos}
    private int[] temposPassagem;  // tempos de passagem nos postos de controlo, em minutos desde a partida
    
    /**
     * Construtor de um atleta com uma dorsal, nome, nacionalidade e escalão.
     * 
     * @param dorsal numero identificador do atleta
     * @param nome nome do atleta
     * @param nacionalidade nacionalidade do atleta
     * @param escalao escalão do atleta
     */
    public Atleta(int dorsal, String nome, String nacionalidade, String escalao) {
        this.dorsal = dorsal;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.escalao = escalao;
    }
    
    /**
     * Retorna o dorsal do atleta.
     * 
     * @return dorsal numero identificador do atleta
     */
    public int getDorsal() {
        return dorsal;
    }
    
    /**
     * Retorna o nome do atleta.
     * 
     * @return nome numero identificador do atleta
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a nacionalidade do atleta.
     * 
     * @return nacionalidade do atleta
     */
    public String getNacionalidade() {
        return nacionalidade;
    }
    
    /**
     * Retorna o escalão do atleta.
     * 
     * @return escalão do atleta
     */
    public String getEscalao() {
        return escalao;
    }
    
    /**
     * Retorna a posição final em toda a corrida do atleta.
     * 
     * @return a posição final do atleta
     */
    public int getPosicaoFinalAbsoluta() {
        return posicaoFinalAbsoluta;
    }
    
    /**
     * Retorna a posição final por escalão do atleta.
     * 
     * @return a posição final do atleta
     */
    public int getPosicaoFinalEscalao() {
        return posicaoFinalEscalao;
    }
    
    /**
     * Retorna um array de horas, minutos e segundos que o atleta demorou a chegar à meta.
     * 
     * @return a posição final do atleta
     */
    public int[] getTempoNaMeta() {
        return tempoNaMeta;
    }
    
    /**
     * Retorna um array com os tempos de passagem do atleta nos postos de controlo.
     * 
     * @return os tempos de passagem do atleta
     */
    public int[] getTemposPassagem() {
        return temposPassagem;
    }
    
    /**
     * Define a posição final absoluta do atleta.
     * @param posicaoFinalAbsoluta
     */
    public void setPosicaoFinalAbsoluta(int posicaoFinalAbsoluta) {
        this.posicaoFinalAbsoluta = posicaoFinalAbsoluta;
    }
    
    /**
     * Define a posição final por escalão do atleta.
     * @param posicaoFinalEscalao
     */
    public void setPosicaoFinalEscalao(int posicaoFinalEscalao) {
        this.posicaoFinalEscalao = posicaoFinalEscalao;
    }
    
    /**
     * Define o tempo que o atleta demorou a chegar à meta.
     * @param tempoNaMeta
     */
    public void setTempoNaMeta(int[] tempoNaMeta) {
        this.tempoNaMeta = Arrays.copyOf(tempoNaMeta, tempoNaMeta.length);
    }
    
    /**
     * Define os tempos de passagem do atleta nos postos de controlo.
     * @param temposPassagem
     */
    public void setTemposPassagem(int[] temposPassagem) {
        this.temposPassagem = Arrays.copyOf(temposPassagem, temposPassagem.length);
    }
    
    /**
     * Comparador de atletas por dorsal.
     * 
     * @return a diferença entre os dorsais dos atletas
     */
    public int compareTo(Atleta outro) {
        return this.dorsal - outro.dorsal;
    }
    
    /**
     * HashCode de um atleta.
     * 
     * @return o hashCode do atleta
     */
    public int hashCode() {
        return Objects.hashCode(this);
    }
    
    /**
     * Verifica se dois atletas são iguais.
     * 
     * @return true se os atletas são iguais, false caso contrário
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
     }
        if (obj == null || !(this.getClass().equals(obj.getClass()))) {
            return false;
        }
        Atleta outro = (Atleta) obj;
        return this.dorsal == outro.dorsal;
    }
    
    /**
     * Ordena um array de atletas por dorsal (default).
     * 
     * @param vec array de atletas a ordenar
     */
    public static void ordena(Atleta[] vec) {
        Arrays.sort(vec);
    }
    
    /**
     * Ordena um array de atletas por um comparador.
     * 
     * @param vec array de atletas a ordenar
     * @param comparador comparador a usar na ordenação
     */
    public static void ordena(Atleta[] vec, Comparator<Atleta> comparador) {
        Arrays.sort(vec, comparador);
    }
    
    /**
     * Busca o índice de um atleta num array de atletas.
     * 
     * @param vec array de atletas
     * @param comparador comparador a usar na busca
     */
    public static int indiceAtletaPorNome(Atleta[] vec, String nome) {
        for (int i = 0; i < vec.length; i++) {
            if (nome.equals(vec[i].nome)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Busca o índice de um atleta num array de atletas ordenado.
     * 
     * @param vec array de atletas
     * @param nome nome do atleta a procurar
     */
    public static int indiceAtletaPorNomeArrayOrdenado(Atleta[] vec, String nome) {
        // atleta com o nome "nome" para ser usado na pesquisa binaria
        Atleta nomeCerto = new Atleta(0, nome, "pt", "0");
        return Arrays.binarySearch(vec, nomeCerto, new ComparaAtletaNome());
    }
    
    /**
     * Filtra um array de atletas por escalão e/ou nacionalidade.
     * 
     * @param vec array de atletas
     * @param escalao escalão a filtrar
     * @param nacionalidade nacionalidade a filtrar
     * @return array de atletas filtrado
     */
    public static Atleta[] seleccionaEscalaoEouNacionalidade(Atleta[] vec, String escalao, String nacionalidade) {
        Atleta[] array = new Atleta[vec.length];
        int index = 0;

        if (escalao.equals("todos") && nacionalidade.equals("todas")) {
            return Arrays.copyOf(vec, vec.length);
        }

        for (Atleta atleta : vec) {
            if ((escalao.equals("todos") || atleta.getEscalao().equals(escalao)) &&
                (nacionalidade.equals("todas") || atleta.getNacionalidade().equals(nacionalidade))) {
                array[index++] = atleta;
            }
        }

        return Arrays.copyOf(array, index);
    }
    
    /**
     * Transforma o atleta numa representação textual.
     * "dorsal - nome"
     */
    public String toString() {
        return dorsal + " - " + nome;
    }
}
