package project.comparators;

import java.util.Comparator;

import project.Atleta;

public class ComparaAtletaNacionalidade implements Comparator<Atleta> {
    @Override
    public int compare(Atleta a1, Atleta a2) {
        return a1.getNacionalidade().compareTo(a2.getNacionalidade());
    }
}
