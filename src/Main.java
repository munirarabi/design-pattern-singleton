import java.util.ArrayList;
import java.util.List;

// Interface para definir estratégias de impressão
interface EstrategiaImpressao {
    void imprimirDocumento();
}

// Implementação da estratégia de impressão
class EstrategiaImpressaoPadrao implements EstrategiaImpressao {
    @Override
    public void imprimirDocumento() {
        // Implementação padrão da impressão de documento
        System.out.println("Imprimindo documento padrão...");
    }
}

// Implementação da estratégia de impressão rápida
class EstrategiaImpressaoRapida implements EstrategiaImpressao {
    @Override
    public void imprimirDocumento() {
        System.out.println("Imprimindo documento de forma rápida...");
    }
}

// Implementação da classe Fila
class Fila {
    // Instância única da fila (Singleton)
    private static Fila instanciaUnica = null;

    // Lista de documentos na fila
    private List<String> documentos;

    // Estratégia de impressão atual
    private EstrategiaImpressao estrategiaImpressao;

    // Construtor privado para garantir que apenas uma instância seja criada
    private Fila() {
        documentos = new ArrayList<>();
        estrategiaImpressao = new EstrategiaImpressaoPadrao();
    }

    // Método para obter a instância única da fila (Singleton)
    public static Fila getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Fila();
        }
        return instanciaUnica;
    }

    // Adicionar documento à fila
    public void adicionarDocumento(String documento) {
        documentos.add(documento);
    }

    // Definir a estratégia de impressão
    public void definirEstrategiaImpressao(EstrategiaImpressao estrategia) {
        estrategiaImpressao = estrategia;
    }

    // Imprimir o próximo documento na fila de acordo com a estratégia atual
    public void imprimirDocumento() {
        if (!documentos.isEmpty()) {
            estrategiaImpressao.imprimirDocumento();
            documentos.remove(0);
        } else {
            System.out.println("Nenhum documento na fila para impressão.");
        }
    }

    // Remover o próximo documento da fila
    public void removerDocumento() {
        if (!documentos.isEmpty()) {
            documentos.remove(0);
        } else {
            System.out.println("Nenhum documento na fila para remover.");
        }
    }

    // Remover todos os documentos da fila
    public void removerTodosDocumentos() {
        documentos.clear();
    }
}

public class Main {
    public static void main(String[] args) {
        Fila fila = Fila.getInstance();

        fila.adicionarDocumento("Documento 1");
        fila.adicionarDocumento("Documento 2");

        fila.definirEstrategiaImpressao(new EstrategiaImpressaoPadrao());
        fila.imprimirDocumento(); // Imprime o documento 1

        fila.definirEstrategiaImpressao(new EstrategiaImpressaoRapida());
        fila.imprimirDocumento(); // Imprime o documento 2

        fila.removerDocumento();
        fila.removerTodosDocumentos();
    }
}
