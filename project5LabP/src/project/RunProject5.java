package project;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;

import project.comparators.*;

public class RunProject5 {
    
    public static final String EOL = System.lineSeparator();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter("./output/output.txt")) {
            
            // testes unitários sobre o tipo Atleta e respectivos comparadores
            
            // criação de 4 atletas fictícios
            
            // os tempos de passagem não são usados nos testes com os atletas fictícios,
            // pelo que não se invoca setTemposPassagem()
            
            Atleta zeNinguem = new Atleta(6666, "José Ninguém", "POR (PT)", "40-44M");
            zeNinguem.setPosicaoFinalAbsoluta(3000);
            zeNinguem.setPosicaoFinalEscalao(50);
            zeNinguem.setTempoNaMeta(new int[] {46, 29, 59});
            
            Atleta mariaNinguem = new Atleta(5555, "Maria Ninguém", "POR (PT)", "40-44W");
            mariaNinguem.setPosicaoFinalAbsoluta(2000);
            mariaNinguem.setPosicaoFinalEscalao(10);
            mariaNinguem.setTempoNaMeta(new int[] {39, 12, 45});
            
            Atleta zeSilva = new Atleta(6000, "José Silva", "POR (PT)", "40-44M");
            zeSilva.setPosicaoFinalAbsoluta(1000);
            zeSilva.setPosicaoFinalEscalao(30);
            zeSilva.setTempoNaMeta(new int[] {23, 30, 34});
            
            Atleta johnDoe = new Atleta(1734, "John Doe", "NZL (NZ)", "55-59M");
            johnDoe.setPosicaoFinalAbsoluta(1001);
            johnDoe.setPosicaoFinalEscalao(11);
            johnDoe.setTempoNaMeta(new int[] {23, 30, 42});
            
            Atleta[] quatroAtletas = new Atleta[] {zeNinguem, mariaNinguem, zeSilva, johnDoe};
            writer.println("Array de atletas fictícios inicial:");
            writer.println(arrayAtletaToString(quatroAtletas));
            
            // ordenação e comparadores
            
            writer.println("Ordenação deste array de atletas por vários critérios:");
            writer.println();
            
            Atleta.ordena(quatroAtletas); // ordenação natural é por dorsal
            writer.println(arrayAtletaToString(quatroAtletas));
                       
            Object[] comparators = new Object[] {new ComparaAtletaDorsal(), new ComparaAtletaNome(),
                    new ComparaAtletaPosFinalAbsoluta(), new ComparaAtletaTempoNaMeta(),
                    new ComparaAtletaNacionalidade(), new ComparaAtletaEscalao(),
                    new ComparaAtletaPosFinalEscalao()};
            
            for (Object comparator: comparators) {
                Atleta.ordena(quatroAtletas, (Comparator<Atleta>) comparator);
                writer.println(arrayAtletaToString(quatroAtletas));
            }
            
            // pesquisas por nome
            
            writer.println("Pesquisas por nome:");
            writer.println(Atleta.indiceAtletaPorNome(quatroAtletas, "José Silva")); // 2
            Atleta.ordena(quatroAtletas, new ComparaAtletaNome());
            writer.println(Atleta.indiceAtletaPorNomeArrayOrdenado(quatroAtletas, "Maria Ninguém")); // 3
            writer.println();
            
            // filtros
            
            writer.println("Aplicação de filtros:");
            writer.println();
            Atleta.ordena(quatroAtletas, new ComparaAtletaPosFinalAbsoluta());
            writer.println(arrayAtletaToString(Atleta.seleccionaEscalaoEouNacionalidade(quatroAtletas,
                    "todos", "todas")));
            writer.println(arrayAtletaToString(Atleta.seleccionaEscalaoEouNacionalidade(quatroAtletas,
                    "40-44M", "todas")));
            writer.println(arrayAtletaToString(Atleta.seleccionaEscalaoEouNacionalidade(quatroAtletas,
                    "todos", "POR (PT)")));
            writer.println(Atleta.seleccionaEscalaoEouNacionalidade(quatroAtletas,
                    "todos", "SUI (CH)").length); // 0
            writer.println();
            writer.println(arrayAtletaToString(Atleta.seleccionaEscalaoEouNacionalidade(quatroAtletas,
                    "40-44W", "POR (PT)")));
            
            // testes de integração sobre o tipo Corrida
            
            // construtor
            
            Corrida corrida = new Corrida("input/UTMB_2023-starters.csv", "input/UTMB_2023-final.csv",
                    "input/UTMB_2023-progress.csv");
            
            // atletas e postos todos lidos?
            
            writer.println("Número de atletas: " + corrida.getNumeroDeAtletas());
            writer.println("Número de postos de controlo: " + corrida.getNumeroPostosControlo());
            
            // dados dos atletas bem preenchidos?
            
            Atleta daniele = corrida.getAtletaPorIndice(1000);
            writer.println();
            writer.println("Atleta com o índice 1000 na lista de atletas: " + daniele);
            writer.println("  Dorsal: " + daniele.getDorsal());
            writer.println("  Nome: " + daniele.getNome());
            writer.println("  Nacionalidade: " + daniele.getNacionalidade());
            writer.println("  Escalão: " + daniele.getEscalao());
            writer.println("  Posiçao final absoluta: " + daniele.getPosicaoFinalAbsoluta());
            writer.println("  Posição final no escalão: " + daniele.getPosicaoFinalEscalao());
            writer.println("  Tempo na meta: " + arrayIntToString(daniele.getTempoNaMeta()));
            writer.println("  Tempos de passagem nos postos: " + arrayIntToString(daniele.getTemposPassagem()));
            
            // testando a correcção da tabela de registos de passagem
            
            RegistoPassagem[][] registos = corrida.getRegistosPassagem();
            writer.println();
            writer.println("Primeiros 5 atletas na meta, de acordo com o Registo de Passagem: ");
            for (int i = 0; i < 5; i++) {
                writer.println(registos[corrida.getNumeroPostosControlo() - 1][i].getDorsal());
            }
            writer.println("Atletas a passar em Courmayeur (posto 12) nas posicoes 1001 a 1005," +
                           "de acordo com o Registo de Passagem: ");
            for (int i = 1000; i < 1005; i++) {
                writer.println(registos[11][i].getDorsal());
            }
            
            // testando ordenação por posição final com atletas reais
            
            Atleta[] todosOsAtletas = corrida.getAtletas().clone(); // não clona os atletas; só a estrutura
            Atleta.ordena(todosOsAtletas, new ComparaAtletaPosFinalAbsoluta());
            writer.println();
            writer.println("Primeiros 10 atletas na meta, de acordo com o atributo posição final: ");
            printDetalhesAtletas(todosOsAtletas, writer, 10);
            
            // testando calculaPosicoesPostos()
            
            int[] posicoesAtleta700 = corrida.calculaPosicoesPostos(700);
            writer.println();
            writer.println("Posições do atleta com dorsal 700, nos postos sucessivos: ");
            writer.println(arrayIntToString(posicoesAtleta700));
            
            // testando plotPosicoesPostos()
            
            // passando os dorsais dos atletas
            corrida.plotPosicoesPostos(new int[] {700, 1000});
            // passando os atletas
            Atleta atleta700 = corrida.getAtletaPorIndice(622);
            Atleta atleta1000 = corrida.getAtletaPorIndice(902);
            corrida.plotPosicoesPostos(new Atleta[] {atleta700, atleta1000});
            
            // testando os filtros com atletas reais
            
            Atleta[] mulheres40a44anos = Atleta.seleccionaEscalaoEouNacionalidade(todosOsAtletas,
                    "40-44W", "todas");
            Atleta[] coreanos = Atleta.seleccionaEscalaoEouNacionalidade(todosOsAtletas,
                    "todos", "KOR (KR)");
            Atleta[] francesas40a44anos = Atleta.seleccionaEscalaoEouNacionalidade(todosOsAtletas,
                    "40-44W", "FRA (FR)");
            writer.println();
            writer.println("Todas as mulheres de 40 a 44 anos, de qualquer nacionalidade: ");
            printDetalhesAtletas(mulheres40a44anos, writer, 5000);
            writer.println();
            writer.println("Todos os atletas coreanos, de qualquer escalão: ");
            printDetalhesAtletas(coreanos, writer, 5000);
            writer.println();
            writer.println("Todas as mulheres francesas de 40 a 44 anos: ");
            printDetalhesAtletas(francesas40a44anos, writer, 5000);
        }       
    }
    
    private static String arrayAtletaToString(Atleta[] vec) {
        StringBuilder sb = new StringBuilder();
        for (Atleta atleta : vec) {
            sb.append(atleta + EOL);
        }
        return sb.toString();
    }
    
    private static String arrayIntToString(int[] vec) {
        StringBuilder sb = new StringBuilder();
        for (int k : vec) {
            sb.append(k + " ");
        }
        return sb.toString();
    }
    
    // imprime, no início de cada linha, o número de ordem do atleta em vec
    private static void printDetalhesAtletas(Atleta[] vec, PrintWriter writer, int quantos) {
        if (quantos > vec.length) {
            quantos = vec.length;
        }
        writer.println(String.format("%3s  %5s %25s  %9s  %7s  %8s %6s %6s", "   ",
                "Dorsal", "Nome", "TempoMeta", "Escalão", "País", "PosAbs", "PosEsc"));
        for (int i = 0; i < quantos; i++) {
            writer.println(String.format("%4d   %4d %25s  %3d:%2d:%2d  %7s  %8s %6d %6d", i + 1,
                    vec[i].getDorsal(), vec[i].getNome(),
                    vec[i].getTempoNaMeta()[0],
                    vec[i].getTempoNaMeta()[1],
                    vec[i].getTempoNaMeta()[2],
                    vec[i].getEscalao(),
                    vec[i].getNacionalidade(),
                    vec[i].getPosicaoFinalAbsoluta(),
                    vec[i].getPosicaoFinalEscalao()));
        }   
    }
}
