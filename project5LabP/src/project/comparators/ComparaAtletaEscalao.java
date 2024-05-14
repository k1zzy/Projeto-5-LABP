package project.comparators;

import java.util.Comparator;

import project.Atleta;

public class ComparaAtletaEscalao implements Comparator<Atleta> {
    @Override
    public int compare(Atleta a1, Atleta a2) {
        return a1.getEscalao().compareTo(a2.getEscalao());
    }
}
