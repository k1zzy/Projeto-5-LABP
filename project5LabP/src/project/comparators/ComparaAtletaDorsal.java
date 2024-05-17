package project.comparators;

/**
 * Classe que representa um comparador de atletas por a sua dorsal.
 * 
 * @author Rodrigo Afonso (61839)
 * @version 1.0
 */

import java.util.Comparator;

import project.Atleta;

public class ComparaAtletaDorsal implements Comparator<Atleta> {
    /**
     * Compara dois atletas por a sua dorsal.
     * 
     * @param a1 Atleta a ser comparado.
     * @param a2 Atleta a ser comparado.
     * @return A diferença entre as dorsais dos atletas.
     */
    @Override
    public int compare(Atleta a1, Atleta a2) {
        return Integer.compare(a1.getDorsal(), a2.getDorsal());
    }
}
