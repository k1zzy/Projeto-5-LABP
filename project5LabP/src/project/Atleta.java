package project;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import project.comparators.ComparaAtletaNome;

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
        return this.dorsal - outro.dorsal;
    }
    
    public int hashCode() {
        return Objects.hashCode(this);
    }
    
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
    
    public static void ordena(Atleta[] vec) {
        Arrays.sort(vec);
    }
    
    public static void ordena(Atleta[] vec, Comparator<Atleta> comparador) {
        Arrays.sort(vec, comparador);
    }
    
    public static int indiceAtletaPorNome(Atleta[] vec, String nome) {
        for (int i = 0; i < vec.length; i++) {
            if (nome.equals(vec[i].nome)) {
                return i;
            }
        }
        return -1;
    }
    
    public static int indiceAtletaPorNomeArrayOrdenado(Atleta[] vec, String nome) {
        // atleta com o nome "nome" para ser usado na pesquisa binaria
        Atleta nomeCerto = new Atleta(0, nome, "pt", "0");
        return Arrays.binarySearch(vec, nomeCerto, new ComparaAtletaNome());
    }
    
    public static Atleta[] seleccionaEscalaoEouNacionalidade(Atleta[] vec, String escalao, String nacionalidade) {
        Atleta[] array = new Atleta[vec.length];
        int index = 0;
        if (escalao.equals("todos") && nacionalidade.equals("todas")) {
            return Arrays.copyOf(vec, vec.length);
        }
        else if (!(escalao.equals("todos")) && nacionalidade.equals("todas")) {
            for (Atleta atleta : vec) {
                if (atleta.getEscalao().equals(escalao)) {
                   array[index++] = atleta;
                }
            }
        }
        else if (escalao.equals("todos") && !(nacionalidade.equals("todas"))) {
            for (Atleta atleta : vec) {
                if (atleta.getNacionalidade().equals(nacionalidade)) {
                   array[index++] = atleta;
                }
            }
        }
        else {
            for (Atleta atleta : vec) {
                if (atleta.getNacionalidade().equals(nacionalidade) && atleta.getEscalao().equals(escalao)) {
                   array[index++] = atleta;
                }
            }
        }
        return Arrays.copyOf(array, index);
    }
    
    public String toString() {
        return dorsal + " - " + nome;
    }
}
