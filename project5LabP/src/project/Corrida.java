package project;

/**
 * Classe que representa uma corrida.
 * 
 * @author Rodrigo Afonso (61839)
 * @version 1.0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.jfree.data.xy.XYDataset;

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
    
    /**
     * Construtor de uma corrida a partir de ficheiros de texto com os dados dos atletas, das passagens nos postos de controlo
     * e das classificações finais.
     * 
     * @param ficheiroListaAtletas nome do ficheiro com a lista de atletas
     * @param ficheiroClassificacoes nome do ficheiro com as classificações finais
     * @param ficheiroRegistoPassagens nome do ficheiro com os registos de passagem nos postos de controlo
     * @throws FileNotFoundException se algum dos ficheiros não for encontrado
     */
    public Corrida(String ficheiroListaAtletas, String ficheiroClassificacoes, String ficheiroRegistoPassagens)
    throws FileNotFoundException {
        this.atletas = lerFicheiroListaAtletas(ficheiroListaAtletas);
        this.registosPassagem = lerFicheiroRegistoPassagens(ficheiroRegistoPassagens);
        ordenaPorPassagemDeTempo(this.registosPassagem, new ComparaRegistoPassagemTempo());
        lerFicheiroClassificacoes(ficheiroClassificacoes);
    }
    
    /**
     * Ordena os registos de passagem de cada posto de controlo por ordem crescente de tempo de passagem.
     * 
     * @param vec matriz de registos de passagem, onde cada linha corresponde a um posto de controlo
     * @param comparador comparador de registos de passagem por tempo
     */
    public static void ordenaPorPassagemDeTempo(RegistoPassagem[][] vec, ComparaRegistoPassagemTempo comparador) {
        for (RegistoPassagem[] posto : vec) {
            Arrays.sort(posto, comparador);
        }
    }
    
    /**
     * Lê um ficheiro de texto com a lista de atletas e cria um array de atletas com os dados lidos.
     * 
     * @param ficheiroListaAtletas nome do ficheiro com a lista de atletas
     * @return array de atletas com os dados lidos
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
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
    
    /**
     * Lê um ficheiro de texto com as classificações finais e atualiza os dados dos atletas com essas classificações.
     * 
     * @param ficheiroClassificacoes nome do ficheiro com as classificações finais
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private void lerFicheiroClassificacoes(String ficheiroClassificacoes)
    throws FileNotFoundException {
        Scanner sc = new Scanner(new File(ficheiroClassificacoes));
        boolean found = false;
        
        sc.nextLine(); //
        sc.nextLine(); // passa o cabeçalho
        sc.nextLine(); //
        
        int indexAtletas = 0;
        while (sc.hasNextLine()) {
            String[] campos = sc.nextLine().split(";");
            do {
                if (atletas[indexAtletas].getDorsal() == Integer.parseInt(campos[1])) {
                    atletas[indexAtletas].setPosicaoFinalAbsoluta(Integer.parseInt(campos[0]));
                    atletas[indexAtletas].setPosicaoFinalEscalao(Integer.parseInt(campos[5]));
                    String[] tempoNaMeta = campos[6].split(":");
                    atletas[indexAtletas].setTempoNaMeta(new int[] {Integer.parseInt(tempoNaMeta[0]),
                                                                         Integer.parseInt(tempoNaMeta[1]),
                                                                         Integer.parseInt(tempoNaMeta[2])});
                    indexAtletas++;
                    found = true;
                }
                else {
                    atletas[indexAtletas].setPosicaoFinalAbsoluta(POSICAO_DE_QUEM_NAO_CHEGOU_A_META);
                    atletas[indexAtletas].setPosicaoFinalEscalao(POSICAO_DE_QUEM_NAO_CHEGOU_A_META);
                    atletas[indexAtletas].setTempoNaMeta(TEMPO_DE_QUEM_NAO_CHEGOU_A_META);
                    indexAtletas++;
                    found = false;
                }
            } while (!found);
        }
        
        while (indexAtletas < atletas.length) {
            atletas[indexAtletas].setPosicaoFinalAbsoluta(POSICAO_DE_QUEM_NAO_CHEGOU_A_META);
            atletas[indexAtletas].setPosicaoFinalEscalao(POSICAO_DE_QUEM_NAO_CHEGOU_A_META);
            atletas[indexAtletas].setTempoNaMeta(TEMPO_DE_QUEM_NAO_CHEGOU_A_META);
            indexAtletas++;
        }
        
        sc.close();
    }
    
    /**
     * Lê um ficheiro de texto com os registos de passagem nos postos de controlo e cria uma matriz de
     * registos de passagem com os dados lidos.
     * 
     * @param ficheiroRegistoPassagens nome do ficheiro com os registos de passagem nos postos de controlo
     * @return matriz de registos de passagem com os dados lidos
     * @throws FileNotFoundException se o ficheiro não for encontrado
     */
    private RegistoPassagem[][] lerFicheiroRegistoPassagens(String ficheiroRegistoPassagens)
    throws FileNotFoundException {
                Scanner sc = new Scanner(new File(ficheiroRegistoPassagens));
                
                // ver quantos postos existem na corrida
                String[] campos = null;
                try {
                    campos = sc.nextLine().split(";");
                }
                catch (NoSuchElementException e) {
                    sc.close();
                    throw new NoSuchElementException();
                }
                int nrPostos = campos.length - 3; // -3 pq e o que nao sao postos
                int[] temposPassagem = new int[nrPostos];
                RegistoPassagem[][] registoPassagem = new RegistoPassagem[nrPostos][atletas.length];
                
                Tempo tempo = null;
                int atletaIndex = 0;
                while (sc.hasNextLine()) {
                    campos = sc.nextLine().split(";");
                    for (int i = 0; i < nrPostos; i++) {
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
                    }
                    atletas[atletaIndex++].setTemposPassagem(Arrays.copyOf(temposPassagem, temposPassagem.length));
                }

                sc.close();

                return Arrays.copyOf(registoPassagem, registoPassagem.length);
            }
    
    /**
     * Retorna o array de atletas da corrida atual.
     * 
     * @return o array de atletas da corrida atual
     */
    public Atleta[] getAtletas() {
        return Arrays.copyOf(atletas, atletas.length);
    }
    
    /**
     * Retorna o atleta com um dado índice da array de atletas.
     * 
     * @param indice o índice do atleta a procurar
     * @return o atleta com o índice dado
     */
    public Atleta getAtletaPorIndice(int indice) {
        return atletas[indice];
    }
    
    /**
     * Retorna a array de registos de passagem da corrida atual.
     * 
     * @return a array de registos de passagem da corrida atual
     */
    public RegistoPassagem[][] getRegistosPassagem() {
        return Arrays.copyOf(registosPassagem, registosPassagem.length);
    }
    
    /**
     * Retorna o número de atletas que participaram na corrida.
     * 
     * @return o número de atletas que participaram na corrida
     */
    public int getNumeroDeAtletas() {
        return atletas.length;
    }
    
    /**
     * Retorna o número de postos de controlo da corrida.
     * 
     * @return o número de postos de controlo da corrida
     */
    public int getNumeroPostosControlo() {
        return registosPassagem.length;
    }
    
    /**
     * Calcula as posiºões de um atleta com dada dorsal em todos os postos de controlo.
     * 
     * @param dorsal o dorsal do atleta
     * @return um array com as posições do atleta em todos os postos de controlo
     */
    public int[] calculaPosicoesPostos(int dorsal) {
        int[] posicoes = new int[getNumeroPostosControlo()];
        for (int posto = 0; posto < getNumeroPostosControlo(); posto++) {
            posicoes[posto] = RegistoPassagem.indiceRegistoPassagem(this.registosPassagem[posto], dorsal) + 1;
        }
        return Arrays.copyOf(posicoes, posicoes.length);
    }
    
    /**
     * Cria um gráfico comparativo com as posições de todos os atletas dentro da array em todos os postos de controlo.
     * 
     * @param vec a array de atletas a comparar
     */
    public void plotPosicoesPostos(Atleta[] vec) {
        double[] postos = new double[getNumeroPostosControlo()];
        XYDataset[] dataset = new XYDataset[vec.length];
        double[] posicoesDataset = new double[getNumeroPostosControlo()];

        for (int i  = 0; i < getNumeroPostosControlo(); i++) {
            postos[i] = i+1;
        }
        
        for (int j = 0; j < vec.length; j++) {
            int[] posicoesPostos = calculaPosicoesPostos(vec[j].getDorsal());
            
            for (int k = 0; k < getNumeroPostosControlo(); k++) {
                posicoesDataset[k] = posicoesPostos[k];
            }
            
            dataset[j] = XYPlotter.createDataset("Atleta " + vec[j].getDorsal(), Arrays.copyOf(postos, postos.length), Arrays.copyOf(posicoesDataset, posicoesDataset.length));
        }
        
        XYPlotter.showXYPlot("Comparação de atletas", "Postos", "Posições", dataset);
    }

    /**
     * Cria um gráfico comparativo com as posições de todos os atletas com dadas dorsais dentro da array em todos os postos de controlo.
     * 
     * @param dorsais a array de dorsais a comparar
     */
    public void plotPosicoesPostos(int[] dorsais) {
        Atleta[] vec= new Atleta[dorsais.length];
        double[] postos = new double[getNumeroPostosControlo()];
        XYDataset[] dataset = new XYDataset[vec.length];
        double[] posicoesDataset = new double[getNumeroPostosControlo()];
        
        
        for (int i  = 0; i < getNumeroPostosControlo(); i++) {
            postos[i] = i+1;
        }
        
        for (int j = 0; j < vec.length; j++) {
            Atleta atletaDorsalToComp = new Atleta(dorsais[j], "name", "pt", "0");
            vec[j] = atletas[Arrays.binarySearch(atletas, atletaDorsalToComp, new ComparaAtletaDorsal())];
            int[] posicoesPostos = calculaPosicoesPostos(vec[j].getDorsal());
            
            for (int k = 0; k < getNumeroPostosControlo(); k++) {
                posicoesDataset[k] = posicoesPostos[k];
            }
            
            dataset[j] = XYPlotter.createDataset("Atleta " + vec[j].getDorsal(), Arrays.copyOf(postos, postos.length), Arrays.copyOf(posicoesDataset, posicoesDataset.length));
        }
        
        XYPlotter.showXYPlot("Comparação de atletas", "Postos", "Posições", dataset);
    }
}
