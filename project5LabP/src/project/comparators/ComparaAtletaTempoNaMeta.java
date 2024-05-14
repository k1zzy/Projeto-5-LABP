package project.comparators;

import java.util.Comparator;

import project.Atleta;

public class ComparaAtletaTempoNaMeta implements Comparator<Atleta> {
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
