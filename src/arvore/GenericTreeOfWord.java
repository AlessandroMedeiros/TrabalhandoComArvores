/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvore;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alessandro
 */
public class GenericTreeOfWord {

    private static final class Node {

        public Node father;
        public Node child;
        private final String element;
        private boolean ehPalavra = false;
        private final List<Node> lista;

        public Node(Node father, String element, boolean ehPalavra) {
            this.father = father;
            this.element = element;
            this.ehPalavra = ehPalavra;
            this.lista = new ArrayList<>();
        }
    }
    private List<String> listaDePalavras = new ArrayList<>();
    private int count; //contagem do número de nodos
    private Node root; //referência para o nodo raiz
    private String palavra;

    // Metodos
    public GenericTreeOfWord() {
        count = 0;
        root = null;
    }

    public boolean addRoot(String element) {
        //Implementar
        if (root != null) {
            return false;
        }

        Node node = new Node(null, element, false);
        root = node;
        count++;
        return true;
    }

    /**
     * -----------------CRIAR ÁRVORES----------------
     *
     * @param nome-
     */
    public void addWord(String nome) {  //Percorre o nome e chama o adicionar node. Recebe o nome/palavra por parâmetro
        Node aux = root;    //Variável auxiliar para receber o root
        boolean ehPalavra = false;  //Variável auxiliar para saber se é o fim da palavra.
        for (int i = 0; i < nome.length(); i++) {   //Percorre por caracter cada letra
            String letra = String.valueOf(nome.charAt(i));  //Armazena a letra

            if (i == nome.length() - 1) {   //Verifica se é a última letra
                ehPalavra = true;
            }
            aux = addChild(aux, letra, ehPalavra);  //Aux recebe o nome
        }
    }

    /**
     * -----------------CRIA OS FILHOS----------------
     *
     * @param pai-
     * @param filho
     * @param ehPalavra
     * @return
     */
    public Node addChild(Node pai, String filho, boolean ehPalavra) { //Adiciona um novo nodo com uma LETRA.
        Node n = searchNodeRef(filho, pai); //Adiciona o novo nodo na referência do pai.
        if (n != null) {    //Verifica se o nodo é diferente de null
            return n;   //Retorna o nodo
        }
        Node node = new Node(pai, filho, ehPalavra);    //Cria um novo nodo
        pai.lista.add(node);    //Adiciona novo nodo na lista de filhos
        node.father = pai;  //Faz a referência para o nodo Pai
        count++;    //Contadador
        return node;    //Retorna o nodo

    }

    /**
     * -----------------PROCURA PELO NODO-----------------
     */
    private Node searchNodeRef(String element, Node target) {//Percorre a arvore
        Node res = null;    //res recebe null
        if (target != null) {   //Verifica se target é diferente de null
            for (Node n : target.lista) {   //Percorre a lista de filhos
                if (n.element.equals(element)) {    //Percorrendo o elemento do nodo, com o passado por parâmetro
                    res = n;    //Res recebe o nodo
                }
            }
        }
        return res; //Retorna o nodo
    }

    /**
     * ----------------------------------BUSCAR AS
     * PALAVRAS---------------------------------
     *
     * @param caracteres-
     * @return
     */
    public List<String> listaDePalavras(String caracteres) {
        Node aux = root;

        for (int i = 0; i < caracteres.length(); i++) {
            String letra = String.valueOf(caracteres.charAt(i));

            aux = searchNodeRef(letra, aux);
        }

        listaDePalavras = buscarPalavra(aux); // Passa o último nodo para o método

        for (String s : listaDePalavras) {
            System.out.println(s);
        }

        return listaDePalavras;
    }

    /**
     * -----------------BUSCAR PALAVRAS----------------
     *
     * @param aux-
     * @return
     */
    public List<String> buscarPalavra(Node aux) { //Recebe um nodo por paramtero e busca a palavra.
        List<String> lista = new ArrayList<>();
        searchNodeWord(aux, lista);
        return lista;
    }

    /**
     * -----------------PROCURA PALAVRA-----------------
     */
    private void searchNodeWord(Node target, List<String> lista) { // Método responsavel por buscar a palavra e popular na lista
        //Node res = null;    
        if (target != null) {
            for (Node n : target.lista) {
                if (n.ehPalavra == true) {    //Encontra a variável ehPalavra.
                    lista.add(printWord(n));
                }
                searchNodeWord(n, lista); //Entra nos nodos dos filhos dos filhos, na lista de filhos.
            }
        }
    }

    /**
     * -----------------ESCREVE OS NODOS----------------
     *
     * @param node-
     * @return
     */
    public String printWord(Node node) {
        String aux;
        if (node.father != null) {
            aux = printWord(node.father) + node.element; // Chamo o métdo, para achar o NODO e DEPOIS ESCREVE RECURSIVAMENTE.
            return aux;
        }
        return "";
    }
}
