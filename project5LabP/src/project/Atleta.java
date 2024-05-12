package project;

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
    


}
