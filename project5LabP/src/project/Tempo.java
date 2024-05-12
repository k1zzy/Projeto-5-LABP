package project;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Data-hora absoluta e tempo decorrido em prova, medido em minutos
 * 
 * A classe declara uma constante DATA_HORA_INICIAL que é a data-hora de início da corrida.
 */
public class Tempo {
    
    private static final LocalDateTime DATA_HORA_INICIAL = LocalDateTime.of(2023, 9, 1, 18, 0);
    private final LocalDateTime dataHora;
    private final int minutosEmProva; // = (dataHora - DATA_HORA_INICIAL) expresso em minutos
    
    public Tempo(int ano, int mes, int dia, int hora, int minuto, int segundo) {
        dataHora = LocalDateTime.of(ano, mes, dia, hora, minuto, segundo);
        minutosEmProva = calculaMinutosEmProva(dataHora);
    }
    
    private Tempo(LocalDateTime dataHora) {
        this.dataHora = dataHora;
        minutosEmProva = calculaMinutosEmProva(dataHora);
    }
    
    private static int calculaMinutosEmProva(LocalDateTime dataHora) {
        long minutosEmProvaLong = DATA_HORA_INICIAL.until(dataHora, ChronoUnit.MINUTES);
        return Math.toIntExact(minutosEmProvaLong);
    }

    public int getMinutosEmProva() {
        return minutosEmProva;
    }
    
    public static Tempo dataHoraAbsolutaPassagem(int tempoDePassagem) {
        return new Tempo(DATA_HORA_INICIAL.plusMinutes(tempoDePassagem));
    }

//    Comentado porque não queremos expor o tipo LocalDateTime.
    
//    public LocalDateTime getDataHoraAbsoluta() {
//        return dataHora;
//    }
    
//    public static LocalDateTime getDataHoraInicial() {
//        return DATA_HORA_INICIAL;
//    }
    
}
