package tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import project.*;
import project.comparators.*;

public class CorridaTests {

    private static Corrida corrida;

    static {
        try {
            corrida = new Corrida("input/UTMB_2023-starters.csv", "input/UTMB_2023-final.csv",
                    "input/UTMB_2023-progress.csv");
        }
        catch (FileNotFoundException e) {
            System.err.println("Pelo menos 1 ficheiro de inicialização não foi encontrado.");
            corrida = null;
        }
        catch (Exception e) {
            System.err.println("Algo correu mal na inicialização.");
            corrida = null;
        }
    }
    
    @Test
    public void test_NumeroAtletas() { 
        assertEquals(2687, corrida.getNumeroDeAtletas());     
    }
    
    @Test
    public void test_NumeroPostos() { 
        assertEquals(24, corrida.getNumeroPostosControlo());     
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_1() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);   
        assertEquals(1101, daniele.getDorsal());
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_2() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals("Daniele ROATTINO", daniele.getNome());
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_3() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals("ITA (IT)", daniele.getNacionalidade());
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_4() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals("45-49M", daniele.getEscalao());
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_5() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(667, daniele.getPosicaoFinalAbsoluta());
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_6() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(105, daniele.getPosicaoFinalEscalao());
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_7() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(38, daniele.getTempoNaMeta()[0]);
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_8() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(16, daniele.getTempoNaMeta()[1]);
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_9() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(55, daniele.getTempoNaMeta()[2]);
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_10() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(0, daniele.getTemposPassagem()[0]);
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_11() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(511, daniele.getTemposPassagem()[6]);
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_12() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(1265, daniele.getTemposPassagem()[15]);
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_13() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(1591, daniele.getTemposPassagem()[17]);
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_14() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(1902, daniele.getTemposPassagem()[20]);
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_15() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(2296, daniele.getTemposPassagem()[23]);
    }
    
    @Test
    public void test_AtletaComIndiceCorrectoEDadosBemPreenchidos_16() {
        Atleta daniele = corrida.getAtletaPorIndice(1000);
        assertEquals(24, daniele.getTemposPassagem().length);
    }
    
    @Test
    public void test_TabelaRegistosPassagemBemConstruida_1() {
        RegistoPassagem[][] registos = corrida.getRegistosPassagem();  
        assertEquals(3, registos[corrida.getNumeroPostosControlo() - 1][0].getDorsal());
        assertEquals(26, registos[corrida.getNumeroPostosControlo() - 1][1].getDorsal());
        assertEquals(18, registos[corrida.getNumeroPostosControlo() - 1][2].getDorsal());
        assertEquals(9, registos[corrida.getNumeroPostosControlo() - 1][3].getDorsal());
        assertEquals(39, registos[corrida.getNumeroPostosControlo() - 1][4].getDorsal());
    }
    
    @Test
    public void test_TabelaRegistosPassagemBemConstruida_2() {
        RegistoPassagem[][] registos = corrida.getRegistosPassagem();  
        assertEquals(1235, registos[11][1000].getDorsal());
        assertEquals(1575, registos[11][1001].getDorsal());
        assertEquals(2130, registos[11][1002].getDorsal());
        assertEquals(2362, registos[11][1003].getDorsal());
        assertEquals(915, registos[11][1004].getDorsal());
    }
    
    @Test
    public void test_OrdenacaoPorPosFinalComAtletasReais() {
        Atleta[] todosOsAtletas = corrida.getAtletas().clone(); // não clona os atletas; só a estrutura
        Atleta.ordena(todosOsAtletas, new ComparaAtletaPosFinalAbsoluta());
        assertEquals(3, todosOsAtletas[0].getDorsal());
        assertEquals(26, todosOsAtletas[1].getDorsal());
        assertEquals(18, todosOsAtletas[2].getDorsal());
        assertEquals(9, todosOsAtletas[3].getDorsal());
        assertEquals(39, todosOsAtletas[4].getDorsal());
        assertEquals(7, todosOsAtletas[5].getDorsal());
        assertEquals(29, todosOsAtletas[6].getDorsal());
        assertEquals(8, todosOsAtletas[7].getDorsal());
        assertEquals(63, todosOsAtletas[8].getDorsal());
        assertEquals(38, todosOsAtletas[9].getDorsal());
    }
    
    @Test
    public void test_calculaPosicoesPostos() {
        int[] posicoesAtleta700 = corrida.calculaPosicoesPostos(700);
        assertEquals(623, posicoesAtleta700[0]);
        assertEquals(1425, posicoesAtleta700[6]);
        assertEquals(1343, posicoesAtleta700[10]);
        assertEquals(1096, posicoesAtleta700[16]);
        assertEquals(723, posicoesAtleta700[23]);     
    }
    
    @Test
    public void test_FiltraSoEscalao() { 
        Atleta[] todosOsAtletas = corrida.getAtletas().clone();
        Atleta.ordena(todosOsAtletas, new ComparaAtletaPosFinalAbsoluta());
        Atleta[] mulheres40a44anos = Atleta.seleccionaEscalaoEouNacionalidade(todosOsAtletas, "40-44W", "todas");
        assertEquals(63, mulheres40a44anos.length);
        assertEquals("Maite MAIORA ELIZONDO", mulheres40a44anos[0].getNome());
        assertEquals("Cristina PRATS VIDAL", mulheres40a44anos[20].getNome());
    }
    
    @Test
    public void test_FiltraSoNacionalidade() { 
        Atleta[] todosOsAtletas = corrida.getAtletas().clone();
        Atleta.ordena(todosOsAtletas, new ComparaAtletaPosFinalAbsoluta());
        Atleta[] coreanos = Atleta.seleccionaEscalaoEouNacionalidade(todosOsAtletas, "todos", "KOR (KR)");
        assertEquals(27, coreanos.length);
        assertEquals("Jisu KIM", coreanos[0].getNome());
        assertEquals("Youngok CHA", coreanos[8].getNome());
    }
    
    @Test
    public void test_FiltraEscalaoNacionalidade() { 
        Atleta[] todosOsAtletas = corrida.getAtletas().clone();
        Atleta.ordena(todosOsAtletas, new ComparaAtletaPosFinalAbsoluta());
        Atleta[] francesas40a44anos = Atleta.seleccionaEscalaoEouNacionalidade(todosOsAtletas, "40-44W", "FRA (FR)");
        assertEquals(8, francesas40a44anos.length);
        assertEquals("Helene LEGER", francesas40a44anos[0].getNome());
        assertEquals("Vanessa RONCHAUD", francesas40a44anos[4].getNome());
    }
    
    @Test
    public void test_ValorDefaultTempoNaMeta() {
        Atleta amandine = corrida.getAtletaPorIndice(195);
        assertEquals("Amandine GINOUVES", amandine.getNome());
        assertEquals(100, amandine.getTempoNaMeta()[0]);
        assertEquals(0, amandine.getTempoNaMeta()[1]);
        assertEquals(0, amandine.getTempoNaMeta()[2]);
    }
    
    @Test
    public void test_ValorDefaultPosicaoFinalAbsoluta() {
        Atleta amandine = corrida.getAtletaPorIndice(195);
        assertEquals("Amandine GINOUVES", amandine.getNome());
        assertEquals(9999, amandine.getPosicaoFinalAbsoluta());
    }
    
    @Test
    public void test_ValorDefaultPosicaoFinalEscalao() {
        Atleta amandine = corrida.getAtletaPorIndice(195);
        assertEquals("Amandine GINOUVES", amandine.getNome());
        assertEquals(9999, amandine.getPosicaoFinalEscalao());
    }
    
    @Test
    public void test_ValorDefaultTempoPassagemNaoPassou() {
        Atleta amandine = corrida.getAtletaPorIndice(195);
        assertEquals("Amandine GINOUVES", amandine.getNome());
        assertEquals(1238, amandine.getTemposPassagem()[17]);
        assertEquals(6000, amandine.getTemposPassagem()[18]);
        assertEquals(6000, amandine.getTemposPassagem()[23]);
    }
    
}