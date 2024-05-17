package project.comparators;

/**
 * Classe que representa um comparador de atletas por nome.
 * 
 * @author Rodrigo Afonso (61839)
 * @version 1.0
 */

import java.util.Comparator;

import project.Atleta;

public class ComparaAtletaNome implements Comparator<Atleta> {
    /**
     * Compara dois atletas por nome.
     * 
     * @param a1 atleta 1
     * @param a2 atleta 2
     * @return a diferença entre os nomes dos atletas
     */
    @Override
    public int compare(Atleta a1, Atleta a2) {
        return a1.getNome().compareTo(a2.getNome());
    }
}
