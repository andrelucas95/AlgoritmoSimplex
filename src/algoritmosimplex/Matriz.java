/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosimplex;

/**
 *
 * @author André Lucas
 */
public class Matriz {

    int[][] tabelaSimplex;

    public Matriz() {
    }
    
    

    public int inciarTabela(int qtdRestricoes, int qtdVariaveis) {
        int[][] tabelaSimplex = new int[qtdRestricoes + 1][qtdVariaveis + qtdRestricoes + 1];
        return 0;
    }

    public int getCoeficinte(int linha, int coluna) {

        return tabelaSimplex[linha][coluna];

    }

    public int retornaMaiorCoeficiente(int qtdRestricoes, int qtdVariaveis) {
        int maiorValor = 0;
        int linha = qtdRestricoes + 1; // Ultima linha da tabela.
        for (int coluna = 0; coluna <= qtdVariaveis + qtdRestricoes + 1; coluna++) {
            if (tabelaSimplex[linha][coluna] > maiorValor) {
                maiorValor = tabelaSimplex[linha][coluna];
            }
        }
         return maiorValor;
    }
    

    public boolean verificaUltimaLinha(int qtdRestricoes, int qtdVariaveis) {
        boolean terminou = true;
        int linha = qtdRestricoes + 1;
        for (int coluna = 0; coluna <= qtdVariaveis + qtdRestricoes + 1; coluna++) {
            if (tabelaSimplex[linha][coluna] > 0) {
                terminou = false;
            }
        }
        return terminou;
    }
}

