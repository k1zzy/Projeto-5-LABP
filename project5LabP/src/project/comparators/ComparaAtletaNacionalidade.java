package project.comparators;

/**
 * Classe que representa um comparador de atletas por a sua nacionalidade.
 * 
 * @author Rodrigo Afonso (61839)
 * @version 1.0
 */

import java.util.Comparator;

import project.Atleta;

public class ComparaAtletaNacionalidade implements Comparator<Atleta> {
    /**
     * Compara dois atletas por a sua nacionalidade.
     * 
     * @param a1 Atleta a ser comparado.
     * @param a2 Atleta a ser comparado.
     * @return A diferença entre as nacionalidades dos atletas.
     */
    @Override
    public int compare(Atleta a1, Atleta a2) {
        return a1.getNacionalidade().compareTo(a2.getNacionalidade());
    }
}
