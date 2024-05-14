package project.comparators;

import java.util.Comparator;

import project.Atleta;

public class ComparaAtletaPosFinalEscalao implements Comparator<Atleta> {
    @Override
    public int compare(Atleta a1, Atleta a2) {
        return Integer.compare(a1.getPosicaoFinalEscalao(), a2.getPosicaoFinalEscalao());
    }
}
