package project.comparators;

import java.util.Comparator;

import project.Atleta;

public class ComparaAtletaNome implements Comparator<Atleta> {
    @Override
    public int compare(Atleta a1, Atleta a2) {
        return a1.getNome().compareTo(a2.getNome());
    }
}
