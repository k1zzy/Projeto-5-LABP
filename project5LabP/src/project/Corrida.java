package project;

//import org.jfree.data.xy.XYDataset;

import project.comparators.*;

/**
 * Guarda todos os dados de uma corrida e fornece métodos para analisar esses dados
 */
public class Corrida {
    
    /**
     * Tempo de passagem de um atleta que não passou num posto, medido em minutos desde a partida
     * 
     * É um valor arbitrário que corresponde a 100 horas, muito superior ao tempo limite.
     * Aplica-se também a um atleta que não tenha chegado à meta, através da constante
     * TEMPO_DE_QUEM_NAO_CHEGOU_A_META.
     */
    public static final int MINUTOS_DE_QUEM_NAO_PASSOU = 6000;
    
    /**
     * Tempo de chegada de um atleta que não chegou à meta, expresso em {horas, minutos, segundos} desde a partida.
     * 
     * É um valor arbitrário consistente com MINUTOS_DE_QUEM_NAO_PASSOU.
     */
    public static final int[] TEMPO_DE_QUEM_NAO_CHEGOU_A_META = {MINUTOS_DE_QUEM_NAO_PASSOU / 60,
            MINUTOS_DE_QUEM_NAO_PASSOU % 60, 0};
    
    /**
     * Posição final de um atleta que não chegou à meta.
     * 
     * É um valor arbitrário, muito superior ao número de atletas em prova.
     * Serve para a posição absoluta e para a posição por escalão.
     */
    public static final int POSICAO_DE_QUEM_NAO_CHEGOU_A_META = 9999;
    
    // Todos os atletas que se apresentaram à partida, por ordem crescente de número de dorsal
    private final Atleta[] atletas;
    
    // Cada linha de registosPassagem corresponde a um posto de controlo.
    // Após leitura dos dados de ficheiroRegistoPassagens e processamento desses dados, cada linha deve ficar ordenada
    // por ordem crescente de tempo de passagem.
    private final RegistoPassagem[][] registosPassagem;
    

}
