package project;

import java.util.Arrays;
import java.util.Comparator;

public class Atleta implements Comparable<Atleta> {
    
    private final int dorsal; // usado na comparação de Atletas por omissão através do compareTo()
    private final String nome;
    private final String nacionalidade;
    private final String escalao;
    private int posicaoFinalAbsoluta;
    private int posicaoFinalEscalao;
    private int[] tempoNaMeta; // {horas, minutos, segundos}
    private int[] temposPassagem;  // tempos de passagem nos postos de controlo, em minutos desde a partida
    
    public Atleta(int dorsal, String nome, String nacionalidade, String escalao) {
        this.dorsal = dorsal;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.escalao = escalao;
    }
    
    public int getDorsal() {
        return dorsal;
    }
    
    public String getNome() {
        return nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }
    
    public String getEscalao() {
        return escalao;
    }
    
    public int getPosicaoFinalAbsoluta() {
        return posicaoFinalAbsoluta;
    }
    
    public int getPosicaoFinalEscalao() {
        return posicaoFinalEscalao;
    }
    
    public int[] getTempoNaMeta() {
        return tempoNaMeta;
    }
    
    public int[] getTemposPassagem() {
        return temposPassagem;
    }
    
    public void setPosicaoFinalAbsoluta(int posicaoFinalAbsoluta) {
        this.posicaoFinalAbsoluta = posicaoFinalAbsoluta;
    }
    
    public void setPosicaoFinalEscalao(int posicaoFinalEscalao) {
        this.posicaoFinalEscalao = posicaoFinalEscalao;
    }
    
    public void setTempoNaMeta(int[] tempoNaMeta) {
        this.tempoNaMeta = Arrays.copyOf(tempoNaMeta, tempoNaMeta.length);
    }
    
    public void setTemposPassagem(int[] temposPassagem) {
        this.temposPassagem = Arrays.copyOf(temposPassagem, temposPassagem.length);
    }
    
    public int compareTo(Atleta outro) {
        return 0;
    }
    
    public int hashCode() {
        
    }
    
    public boolean equals(Object obj) {
        
    }
    
    public static void ordena(Atleta[] vec) {
        
    }
    
    public static void ordena(Atleta[] vec, Comparator<Atleta> comparador) {

    }
    
    public static int indiceAtletaPorNome(Atleta[] vec, String nome) {
        
    }
    
    public static int indiceAtletaPorNomeArrayOrdenado(Atleta[] vec, String nome) {
        
    }
    
    public static Atleta[] seleccionaEscalaoEouNacionalidade(Atleta[] vec, String escalao, String nacionalidade) {
       
    }
    
    public String toString() {
        
    }
}
