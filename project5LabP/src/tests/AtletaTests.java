package tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import project.*;
import project.comparators.*;

public class AtletaTests {

    private final static Atleta zeNinguem;
    private final static Atleta mariaNinguem;
    private final static Atleta zeSilva;   
    private final static Atleta johnDoe;
    private static Atleta[] quatroAtletas; // contém os 4 atletas acima, em diferentes permutações
    private static Atleta[] filtrados;     // contém um subconjunto dos 4 atletas acima
    
    static {
        zeNinguem = new Atleta(6666, "José Ninguém", "POR (PT)", "40-44M");
        zeNinguem.setPosicaoFinalAbsoluta(3000);
        zeNinguem.setPosicaoFinalEscalao(50);
        zeNinguem.setTempoNaMeta(new int[] {46, 29, 59});
        
        mariaNinguem = new Atleta(5555, "Maria Ninguém", "POR (PT)", "40-44W");
        mariaNinguem.setPosicaoFinalAbsoluta(2000);
        mariaNinguem.setPosicaoFinalEscalao(10);
        mariaNinguem.setTempoNaMeta(new int[] {39, 12, 45});
        
        zeSilva = new Atleta(6000, "José Silva", "POR (PT)", "40-44M");
        zeSilva.setPosicaoFinalAbsoluta(1000);
        zeSilva.setPosicaoFinalEscalao(30);
        zeSilva.setTempoNaMeta(new int[] {23, 30, 34});
        
        johnDoe = new Atleta(1734, "John Doe", "NZL (NZ)", "55-59M");
        johnDoe.setPosicaoFinalAbsoluta(1001);
        johnDoe.setPosicaoFinalEscalao(11);
        johnDoe.setTempoNaMeta(new int[] {23, 30, 42});
    }

    @Test
    public void test_OrdenacaoNaturalUsandoCompareTo() { 
        quatroAtletas = new Atleta[] {zeNinguem, mariaNinguem, zeSilva, johnDoe};
        Atleta.ordena(quatroAtletas);
        assertEquals("John Doe", quatroAtletas[0].getNome());
        assertEquals("Maria Ninguém", quatroAtletas[1].getNome());
        assertEquals("José Silva", quatroAtletas[2].getNome());
        assertEquals("José Ninguém", quatroAtletas[3].getNome());
    }
    
    @Test
    public void test_OrdenacaoPorDorsalUsandoComparator() { 
        quatroAtletas = new Atleta[] {johnDoe, mariaNinguem, zeSilva, zeNinguem};
        Atleta.ordena(quatroAtletas, new ComparaAtletaDorsal());
        assertEquals("John Doe", quatroAtletas[0].getNome());
        assertEquals("Maria Ninguém", quatroAtletas[1].getNome());
        assertEquals("José Silva", quatroAtletas[2].getNome());
        assertEquals("José Ninguém", quatroAtletas[3].getNome());
    }
    
    @Test
    public void test_OrdenacaoPorNomeUsandoComparator() { 
        quatroAtletas = new Atleta[] {johnDoe, mariaNinguem, zeSilva, zeNinguem};
        Atleta.ordena(quatroAtletas, new ComparaAtletaNome());
        assertEquals("John Doe", quatroAtletas[0].getNome());
        assertEquals("José Ninguém", quatroAtletas[1].getNome());
        assertEquals("José Silva", quatroAtletas[2].getNome());
        assertEquals("Maria Ninguém", quatroAtletas[3].getNome());
    }
    
    @Test
    public void test_OrdenacaoPorPosFinalAbsUsandoComparator() { 
        quatroAtletas = new Atleta[] {johnDoe, zeNinguem, zeSilva, mariaNinguem};
        Atleta.ordena(quatroAtletas, new ComparaAtletaPosFinalAbsoluta());
        assertEquals("José Silva", quatroAtletas[0].getNome());
        assertEquals("John Doe", quatroAtletas[1].getNome());
        assertEquals("Maria Ninguém", quatroAtletas[2].getNome());
        assertEquals("José Ninguém", quatroAtletas[3].getNome());
    }
    
    @Test
    public void test_OrdenacaoPorTempoNaMetaUsandoComparator() { 
        quatroAtletas = new Atleta[] {zeSilva, johnDoe, mariaNinguem, zeNinguem};
        Atleta.ordena(quatroAtletas, new ComparaAtletaTempoNaMeta());
        assertEquals("José Silva", quatroAtletas[0].getNome());
        assertEquals("John Doe", quatroAtletas[1].getNome());
        assertEquals("Maria Ninguém", quatroAtletas[2].getNome());
        assertEquals("José Ninguém", quatroAtletas[3].getNome());
    }
    
    @Test
    public void test_OrdenacaoPorNacionalidadeUsandoComparator() { 
        quatroAtletas = new Atleta[] {zeSilva, johnDoe, mariaNinguem, zeNinguem};
        Atleta.ordena(quatroAtletas, new ComparaAtletaNacionalidade());
        assertEquals("John Doe", quatroAtletas[0].getNome());
        assertEquals("José Silva", quatroAtletas[1].getNome());
        assertEquals("Maria Ninguém", quatroAtletas[2].getNome());
        assertEquals("José Ninguém", quatroAtletas[3].getNome());
    }
    
    @Test
    public void test_OrdenacaoPorEscalaoUsandoComparator() { 
        quatroAtletas = new Atleta[] {johnDoe, zeSilva, mariaNinguem, zeNinguem};
        Atleta.ordena(quatroAtletas, new ComparaAtletaEscalao());
        assertEquals("José Silva", quatroAtletas[0].getNome());
        assertEquals("José Ninguém", quatroAtletas[1].getNome());
        assertEquals("Maria Ninguém", quatroAtletas[2].getNome());
        assertEquals("John Doe", quatroAtletas[3].getNome());
    }
    
    @Test
    public void test_OrdenacaoPorPosFinalEscalaoUsandoComparator() { 
        quatroAtletas = new Atleta[] {zeSilva, zeNinguem, mariaNinguem, johnDoe};
        Atleta.ordena(quatroAtletas, new ComparaAtletaPosFinalEscalao());
        assertEquals("Maria Ninguém", quatroAtletas[0].getNome());
        assertEquals("John Doe", quatroAtletas[1].getNome());
        assertEquals("José Silva", quatroAtletas[2].getNome());
        assertEquals("José Ninguém", quatroAtletas[3].getNome());
    }
    
    @Test
    public void test_PesquisaPorNomeArrayNaoOrdenado() { 
        quatroAtletas = new Atleta[] {mariaNinguem, johnDoe, zeSilva, zeNinguem};
        assertEquals(0, Atleta.indiceAtletaPorNome(quatroAtletas, "Maria Ninguém"));
        assertEquals(1, Atleta.indiceAtletaPorNome(quatroAtletas, "John Doe"));
        assertEquals(2, Atleta.indiceAtletaPorNome(quatroAtletas, "José Silva"));
        assertEquals(3, Atleta.indiceAtletaPorNome(quatroAtletas, "José Ninguém"));
    }
    
    @Test
    public void test_PesquisaPorNomeArrayOrdenado() { 
        quatroAtletas = new Atleta[] {johnDoe, zeNinguem, zeSilva, mariaNinguem};
        assertEquals(0, Atleta.indiceAtletaPorNome(quatroAtletas, "John Doe"));
        assertEquals(1, Atleta.indiceAtletaPorNome(quatroAtletas, "José Ninguém"));
        assertEquals(2, Atleta.indiceAtletaPorNome(quatroAtletas, "José Silva"));
        assertEquals(3, Atleta.indiceAtletaPorNome(quatroAtletas, "Maria Ninguém"));
    }
    
    @Test
    public void test_FiltraNada() { 
        quatroAtletas = new Atleta[] {zeSilva, johnDoe, mariaNinguem, zeNinguem};
        filtrados = Atleta.seleccionaEscalaoEouNacionalidade(quatroAtletas, "todos", "todas");
        assertEquals(4, filtrados.length);
        assertEquals("José Silva", filtrados[0].getNome());
        assertEquals("John Doe", filtrados[1].getNome());
        assertEquals("Maria Ninguém", filtrados[2].getNome());
        assertEquals("José Ninguém", filtrados[3].getNome());
    }
    
    @Test
    public void test_FiltraSoEscalao() { 
        quatroAtletas = new Atleta[] {zeSilva, johnDoe, mariaNinguem, zeNinguem};
        filtrados = Atleta.seleccionaEscalaoEouNacionalidade(quatroAtletas, "40-44M", "todas");
        assertEquals(2, filtrados.length);
        assertEquals("José Silva", filtrados[0].getNome());
        assertEquals("José Ninguém", filtrados[1].getNome());
    }
    
    @Test
    public void test_FiltraSoNacionalidade() { 
        quatroAtletas = new Atleta[] {zeSilva, johnDoe, mariaNinguem, zeNinguem};
        filtrados = Atleta.seleccionaEscalaoEouNacionalidade(quatroAtletas, "todos", "POR (PT)");
        assertEquals(3, filtrados.length);
        assertEquals("José Silva", filtrados[0].getNome());
        assertEquals("Maria Ninguém", filtrados[1].getNome());
        assertEquals("José Ninguém", filtrados[2].getNome());
    }
    
    @Test
    public void test_FiltraSoNacionalidadeNaoOcorre() { 
        quatroAtletas = new Atleta[] {zeSilva, johnDoe, mariaNinguem, zeNinguem};
        filtrados = Atleta.seleccionaEscalaoEouNacionalidade(quatroAtletas, "todos", "SUI (CH)");
        assertEquals(0, filtrados.length);
    }
    
    @Test
    public void test_FiltraEscalaoNacionalidade() { 
        quatroAtletas = new Atleta[] {zeSilva, johnDoe, mariaNinguem, zeNinguem};
        filtrados = Atleta.seleccionaEscalaoEouNacionalidade(quatroAtletas, "40-44W", "POR (PT)");
        assertEquals(1, filtrados.length);
        assertEquals("Maria Ninguém", filtrados[0].getNome());
    }
    
    @Test
    public void test_toString() { 
        assertEquals("5555 - Maria Ninguém", mariaNinguem.toString());
    }

}
