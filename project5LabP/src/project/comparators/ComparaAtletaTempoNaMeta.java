package project.comparators;


/**
 * Classe que representa um comparador de atletas por tempo na meta.
 * 
 * @author Rodrigo Afonso (61839)
 * @version 1.0
 */

import java.util.Comparator;

import project.Atleta;

public class ComparaAtletaTempoNaMeta implements Comparator<Atleta> {
    /**
     * Compara dois atletas por o seu tempo na meta.
     * 
     * @param a1 atleta 1
     * @param a2 atleta 2
     * @return 0 se os tempos forem iguais, -1 se o tempo do atleta 1 for menor que o tempo do atleta 2,
     *         1 se o tempo do atleta 1 for maior que o tempo do atleta 2
     */
    @Override
    public int compare(Atleta a1, Atleta a2) {
        if (Integer.compare(a1.getTempoNaMeta()[0], a2.getTempoNaMeta()[0]) == 0) {
            if (Integer.compare(a1.getTempoNaMeta()[1], a2.getTempoNaMeta()[1]) == 0) {
                return Integer.compare(a1.getTempoNaMeta()[2], a2.getTempoNaMeta()[2]);
            }
            else {
                return Integer.compare(a1.getTempoNaMeta()[0], a2.getTempoNaMeta()[0]);
            }
        }
        else {
            return Integer.compare(a1.getTempoNaMeta()[0], a2.getTempoNaMeta()[0]);
        }
    }
}
