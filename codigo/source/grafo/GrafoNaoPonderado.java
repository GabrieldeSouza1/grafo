package source.grafo;

import source.Lista;
import source.Arquivo;
import java.io.IOException;
public class GrafoNaoPonderado extends GrafoMutavel {
    //#region Contrutor

    public GrafoNaoPonderado(String nome) {
        super(nome);
    }
    //#endregion

    //#region boolean addAresta

    /**
     * Adiciona uma aresta entre dois vértices do grafo.
     * Não verifica se os vértices pertencem ao grafo.
     * 
     * @param origem  Vértice de origem
     * @param destino Vértice de destino
     */
    @Override
    public boolean addAresta(int origem, int destino) {
        return addAresta(origem, destino, 0);
    }
    //#endregion

    //#region subgrafo

    @Override
    public GrafoNaoPonderado subGrafo(Lista<Vertice> vertices) {
        return null;
    }
    //#endregion

    //#region Manipular Arquivo

    @Override
    public void carregar(String nomeArquivo) throws IOException {
        Arquivo arq = new Arquivo("codigo/app/files/", nomeArquivo, "read");
       
        while (arq.ready()) {
            String line = arq.readLine();
            line = line.replaceAll("\\n", "");
            String vertices[] = line.split(";");

            int verticeOrigem = Integer.parseInt(vertices[0]);
            
            for (String vertice : vertices) {
                int verticeInt = Integer.parseInt(vertice);
                this.addVertice(verticeInt);

                if (verticeInt != verticeOrigem) {
                    this.addAresta(verticeOrigem, verticeInt);
                }
            }
        }
  
        arq.close();
    }

    @Override
    public void salvar(String nomeArquivo) throws IOException {
        Arquivo arq = new Arquivo("codigo/app/files/", nomeArquivo, "save");

        for (Vertice vertice : this.getAllVertices()) {
            arq.write(vertice.getId());
            Aresta arestas[] = vertice.getAllArestas();

            for (Aresta aresta : arestas) {
                arq.write(";" + aresta.getDestino());
            }

            arq.write("\n");
        }
    
        arq.close();
    }

    public void salvar() throws IOException {
        salvar(this.nome);
    }
    //#endregion
}