package project.comparators;

import java.util.Comparator;

import project.Atleta;
import project.RegistoPassagem;

public class ComparaRegistoPassagemTempo implements Comparator<RegistoPassagem> {
    @Override
    public int compare(RegistoPassagem a1, RegistoPassagem a2) {
        return Integer.compare(a1.getTempoPassagem(), a2.getTempoPassagem());
    }
}
