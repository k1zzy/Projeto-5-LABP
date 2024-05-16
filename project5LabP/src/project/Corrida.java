package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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
    
    public Corrida(String ficheiroListaAtletas, String ficheiroClassificacoes, String ficheiroRegistoPassagens)
    throws FileNotFoundException {
        this.atletas = lerFicheiroListaAtletas(ficheiroListaAtletas);
        this.registosPassagem = lerFicheiroRegistoPassagens(ficheiroRegistoPassagens);
        lerFicheiroClassificacoes(ficheiroClassificacoes);
    }
    
    private static Atleta[] lerFicheiroListaAtletas(String ficheiroListaAtletas)
    throws FileNotFoundException {
        Scanner sc = new Scanner(new File(ficheiroListaAtletas));
        ArrayList<Atleta> atletasList = new ArrayList<>();

        sc.nextLine(); //
        sc.nextLine(); // passa o cabeçalho
        sc.nextLine(); //

        while (sc.hasNextLine()) {
            String[] campos = sc.nextLine().split(";");
            atletasList.add(new Atleta(Integer.parseInt(campos[0]), campos[1], campos[4], campos[3]));
        }

        sc.close();

        Atleta[] atletas = new Atleta[atletasList.size()];
        return atletasList.toArray(atletas);
    }
    
    private void lerFicheiroClassificacoes(String ficheiroClassificacoes)
    throws FileNotFoundException {
        Scanner sc = new Scanner(new File(ficheiroClassificacoes));
        int indexContaAtletas = 0;
        boolean found = false;
        
        sc.nextLine(); //
        sc.nextLine(); // passa o cabeçalho
        sc.nextLine(); //

        while (sc.hasNextLine()) {
            String[] campos = sc.nextLine().split(";");
            do {
                if (atletas[indexContaAtletas].getDorsal() == Integer.parseInt(campos[1])) {
                    atletas[indexContaAtletas].setPosicaoFinalAbsoluta(Integer.parseInt(campos[0]));
                    atletas[indexContaAtletas].setPosicaoFinalEscalao(Integer.parseInt(campos[5]));
                    String[] tempoNaMeta = campos[6].split(":");
                    atletas[indexContaAtletas].setTempoNaMeta(new int[] {Integer.parseInt(tempoNaMeta[0]),
                                                                         Integer.parseInt(tempoNaMeta[1]),
                                                                         Integer.parseInt(tempoNaMeta[2])});
                    indexContaAtletas++;
                    found = true;
                }
                else {
                    atletas[indexContaAtletas].setPosicaoFinalAbsoluta(POSICAO_DE_QUEM_NAO_CHEGOU_A_META);
                    atletas[indexContaAtletas].setPosicaoFinalEscalao(POSICAO_DE_QUEM_NAO_CHEGOU_A_META);
                    atletas[indexContaAtletas].setTempoNaMeta(TEMPO_DE_QUEM_NAO_CHEGOU_A_META);
                    indexContaAtletas++;
                    found = false;
                }
            } while (!found);
        }
        sc.close();
    }
    
    private RegistoPassagem[][] lerFicheiroRegistoPassagens(String ficheiroRegistoPassagens)
    throws FileNotFoundException {
                Scanner sc = new Scanner(new File(ficheiroRegistoPassagens));
                RegistoPassagem[][] registoPassagem = new RegistoPassagem[24][atletas.length];

                sc.nextLine(); // passa o cabeçalho
                
                Tempo tempo = null;
                int[] temposPassagem = new int[24];
                int j = 0;
                int atletaIndex = 0;
                while (sc.hasNextLine()) {
                    String[] campos = sc.nextLine().split(";");
                    for (int i = 0; i < 24; i++) {
                        if (!campos[i+3].equals(".")) {
                            String[] diaEHora = campos[i+3].split(" ");
                            String[] horaEMinuto = diaEHora[1].split(":");
                            
                            switch(diaEHora[0]) {
                            case "Fri.":
                                tempo = new Tempo(2023, 9, 1, Integer.parseInt(horaEMinuto[0]), Integer.parseInt(horaEMinuto[1]), 0);
                                break;
                            case "Sat.":
                                tempo = new Tempo(2023, 9, 2, Integer.parseInt(horaEMinuto[0]), Integer.parseInt(horaEMinuto[1]), 0);
                                break;
                            case "Sun.":
                                tempo = new Tempo(2023, 9, 3, Integer.parseInt(horaEMinuto[0]), Integer.parseInt(horaEMinuto[1]), 0);
                                break;
                            }
                            registoPassagem[i][atletaIndex] = new RegistoPassagem(Integer.parseInt(campos[0]), tempo.getMinutosEmProva());
                        }
                        else {
                            registoPassagem[i][atletaIndex] = new RegistoPassagem(Integer.parseInt(campos[0]), MINUTOS_DE_QUEM_NAO_PASSOU);
                        }
                        temposPassagem[i] = registoPassagem[i][atletaIndex].getTempoPassagem();
                        j++;
                    }
                    atletas[atletaIndex++].setTemposPassagem(temposPassagem);
                    j = 0;
                }

                sc.close();

                return Arrays.copyOf(registoPassagem, registoPassagem.length);
            }
    
    public Atleta[] getAtletas() {
        return Arrays.copyOf(atletas, atletas.length);
    }
    
    public Atleta getAtletaPorIndice(int indice) {
        return atletas[indice];
    }
    
    public RegistoPassagem[][] getRegistosPassagem() {
        return Arrays.copyOf(registosPassagem, registosPassagem.length);
    }
    
    public int getNumeroDeAtletas() {
        return atletas.length;
    }
    
    public int getNumeroPostosControlo() {
        return registosPassagem.length;
    }
    
    // NMAO SEI PQ EQ AS VEZES E MAIS 1 E AS VEZES MENOS 1 AHHHHHH
    public int[] calculaPosicoesPostos(int dorsal) {
        int indiceAtleta = indiceAtletaPorDorsalArrayOrdenado(atletas, dorsal);
        int[] tempos = Arrays.copyOf(atletas[indiceAtleta].getTemposPassagem(), atletas[indiceAtleta].getTemposPassagem().length);
        
        tempos[0] = indiceAtleta + 1;
        
        for (int j = 1; j < tempos.length; j++) {
            int posicao = 1;
            
            for (int i = 0; i < registosPassagem[0].length; i++) {
                if (tempos[j] > registosPassagem[j][i].getTempoPassagem()) {
                    posicao++;
                }
            }
            tempos[j] = posicao;
        }
        return tempos;
    }
    
    public static int indiceAtletaPorDorsalArrayOrdenado(Atleta[] vec, int dorsal) {
        Atleta nomeCerto = new Atleta(dorsal, "nome", "pt", "0");
        return Arrays.binarySearch(vec, nomeCerto, new ComparaAtletaDorsal());
    }
    
    public void plotPosicoesPostos(Atleta[] vec) {

    }

    public void plotPosicoesPostos(int[] dorsais) {
        
    }
}
