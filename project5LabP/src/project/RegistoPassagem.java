package project;

/**
 * Registo simples de passagem de um atleta por um posto de controlo
 *
 * Se o atleta não passou neste ponto de passagem, tempo de passagem é um valor arbitrariamente grande,
 * muito maior que o tempo total permitido para a corida.
 */
public class RegistoPassagem {
    
    private final int dorsal;
    private final int tempoPassagem;
    
    /**
     * @param dorsal o número de dorsal do atletas
     * @param tempoPassagem medido em minutos totais após a partida
     * @requires dorsal > 0
     * @requires tempoPassagem > 0
     */
    public RegistoPassagem(int dorsal, int tempoPassagem) {
        this.dorsal = dorsal;
        this.tempoPassagem = tempoPassagem;
    }

    public int getDorsal() {
        return dorsal;
    }

    public int getTempoPassagem() {
        return tempoPassagem;
    }

    /**
     * Procura um registo de passagem num array, por número de dorsal
     * 
     * @param vec
     * @param dorsal o número de dorsal do atleta a pesquisar
     * @return o índice do registo no array, se encontrado o dorsal; -1 se não encontrado
     */
    public static int indiceRegistoPassagem(RegistoPassagem[] vec, int dorsal) {
        int resultado = -1;

        for (int i = 0; i < vec.length && resultado == -1; i++) {
            if (vec[i].dorsal == dorsal) {
                resultado = i;
            }
        }
        
        return resultado;
    }
}
