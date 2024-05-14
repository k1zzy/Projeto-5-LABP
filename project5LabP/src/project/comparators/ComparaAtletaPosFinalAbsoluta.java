package project.comparators;

import java.util.Comparator;

import project.Atleta;

public class ComparaAtletaPosFinalAbsoluta implements Comparator<Atleta> {
    @Override
    public int compare(Atleta a1, Atleta a2) {
        return Integer.compare(a1.getPosicaoFinalAbsoluta(), a2.getPosicaoFinalAbsoluta());
    }
}
