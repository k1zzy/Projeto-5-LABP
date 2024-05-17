package project.comparators;


/**
 * Classe que representa um comparador de tempos de passagem de atletas.
 * 
 * @author Rodrigo Afonso (61839)
 * @version 1.0
 */

import java.util.Comparator;

import project.Atleta;
import project.RegistoPassagem;

public class ComparaRegistoPassagemTempo implements Comparator<RegistoPassagem> {
    /**
     * Compara dois tempos de passagem de atletas.
     * 
     * @param a1 tempo de passagem do primeiro atleta
     * @param a2 tempo de passagem do segundo atleta
     * @return a diferença entre os tempos de passagem
     */
    @Override
    public int compare(RegistoPassagem a1, RegistoPassagem a2) {        
        return Integer.compare(a1.getTempoPassagem(), a2.getTempoPassagem());
    }
}
