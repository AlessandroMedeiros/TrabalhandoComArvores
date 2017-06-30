package arvore;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static arvore.Arquivos.NOMES;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alessandro
 */
public class Palavra {

    private static GenericTreeOfWord arvore = new GenericTreeOfWord();
    private static List<String> listaNomes = new ArrayList<>();

    private static Map<String, String> nome = new HashMap();

    public static void main(String[] args) throws IOException {

        Palavra palavra = new Palavra();
        palavra.lerArquivo();
        palavra.fazAmagicaAcontecer();
        criaMenuJoptionPane(palavra);

    }

    /**
     * -----------------CRIA O MENU-----------------
     */
    private static void criaMenuJoptionPane(Palavra palavra) {
        String caracter = JOptionPane.showInputDialog("Digite os caracteres para pesquisar a palavra");
        String valor = "";
        if (caracter.length() < 2 || caracter.length() > 3) {
            JOptionPane.showMessageDialog(null, "Você digitou o caracter inválido! \nDigite entre 2 a 3 caracteres.");
        } else {
            String significado;
            listaNomes = arvore.listaDePalavras(caracter.toLowerCase());
            if (listaNomes.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome " + caracter + " não existe na lista.");
            } else {
                valor = JOptionPane.showInputDialog("Digite a palavra para buscar o significado\n" + listaNomes);
                significado = nome.get(valor.toLowerCase());
                if (significado != null) {
                    JOptionPane.showMessageDialog(null, valor.toUpperCase() + ", " + significado.toUpperCase());
                    System.out.println(valor.toUpperCase() + ", " + significado.toUpperCase());
                } else {
                    JOptionPane.showMessageDialog(null, "Nome " + valor + " não existe na lista.");
                }
            }
        }
    }

    /**
     * -----------------LER ARQUIVOS E ADICIONA NO HASHMAP-----------------
     */
    private void lerArquivo() throws IOException {
        BufferedReader brNomes = new BufferedReader(new FileReader(NOMES));
        System.out.println("Carregando o arquivo: " + NOMES);
        while (brNomes.ready()) {
            String string = brNomes.readLine().toLowerCase();   //Pega a primeira linha do excel (as duas colunas).
            int aux = string.indexOf(";");  //Divide as colunas por ponto e virgula
            String chave = string.substring(0, aux);    //Armazena na String chave o nome
            String valor = string.substring(aux + 1, string.length()); //armazena o significado da palavra.
            nome.put(chave, valor);  //Poe no HashMap o nome e o significado.
        }
        brNomes.close();    //Fecha o arquivo.
    }

    /**
     * -----------------FAZ A MAGICA ACONTECER-----------------
     */
    private void fazAmagicaAcontecer() {  //Método responsável por criar a estrutura da árvore, adiciona as letras na arvore
        arvore.addRoot("Pai");  //adiciona um Pai para a árvore.
        for (String key : nome.keySet()) {    //Percorre a lista de nomes
            arvore.addWord(key);    //Adiciona a palavra na árvore.
        }
    }
}
