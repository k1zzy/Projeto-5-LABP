package project.comparators;


/**
 * Classe que representa um comparador de atletas por posição final de escalão.
 * 
 * @author Rodrigo Afonso (61839)
 * @version 1.0
 */

import java.util.Comparator;

import project.Atleta;

public class ComparaAtletaPosFinalEscalao implements Comparator<Atleta> {
    /**
     * Compara dois atletas por a sua posição final de escalão.
     * 
     * @param a1 Atleta a ser comparado.
     * @param a2 Atleta a ser comparado.
     * @return A diferença entre as posições finais de escalão dos atletas.
     */
    @Override
    public int compare(Atleta a1, Atleta a2) {
        return Integer.compare(a1.getPosicaoFinalEscalao(), a2.getPosicaoFinalEscalao());
    }
}
