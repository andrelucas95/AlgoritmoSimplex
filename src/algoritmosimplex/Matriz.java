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

    double[][] tabelaSimplex;
    int restricoes;
    int variaveis;

    public Matriz(int restricoes, int variaveis ) {
        this.restricoes = restricoes;
        this.variaveis = variaveis;
    }

    //
    public void inciarTabela() {
        this.tabelaSimplex =  new double[this.retornanumLinhas()][this.retornanumColunas()];
    }
    
    int retornanumLinhas(){
        return this.restricoes + 1;
    }
    
    int retornanumColunas(){
        return this.variaveis + this.restricoes + 1;
    }

    public double getCoeficinte(int linha, int coluna) {

        return tabelaSimplex[linha][coluna];

    }

    public void inserirCoeficienteTabela(int linha, int coluna, double valor) {

        tabelaSimplex[linha][coluna] = valor;

    }

    public int retornaColunaQueEntra(int qtdRestricoes, int qtdVariaveis) {
        double maiorValor = 0;
        int colunaQueEntra = 0;
        int linha = qtdRestricoes + 1; // Ultima linha da tabela.
        for (int coluna = 0; coluna <= qtdVariaveis + qtdRestricoes + 1; coluna++) {
            if (tabelaSimplex[linha][coluna] > maiorValor) {
                //maiorValor = tabelaSimplex[linha][coluna];
                colunaQueEntra = coluna;
            }
        }
        return colunaQueEntra;
    }
    
    public int retornaLinhaQueSai(int qtdRestricoes, int qtdVariaveis, int colunaQueEntra){
        int linhaQueSai = 0;
        double menorDivisao = Double.MAX_VALUE;
        for(int linha = 0; linha < qtdVariaveis + 1; linha++){
            //Para ignorar linhas que possuam zero.
            if(tabelaSimplex[linha][colunaQueEntra] != 0){
               if((tabelaSimplex[linha][this.retornanumColunas()-1] / tabelaSimplex[linha][colunaQueEntra]) < menorDivisao) {
                menorDivisao = tabelaSimplex[linha][this.retornanumColunas()-1] / tabelaSimplex[linha][colunaQueEntra];
                linhaQueSai = linha;
            } 
            }
            
        }
        return linhaQueSai;
    }
    
    public void realizarLiLaj(int colunaQueEntra, int linhaQueSai, int linhaQueAltera){
        /* Li - (a)* Lj
          Li = linha que deseja modificar
          Lj = linha da variavel que entrou
          a = coeficiente de Li na coluna da variavel que entrou*/
        double coeficienteA = tabelaSimplex[linhaQueSai][colunaQueEntra];
        for(int j = 0; j <= this.retornanumColunas() - 1; j++){
            tabelaSimplex[linhaQueAltera][j] = tabelaSimplex[linhaQueAltera][j] - 
                    (coeficienteA * tabelaSimplex[linhaQueSai][j]);
        }
        
        
        
    
    }

    public boolean verificaMelhorSolucao(int qtdRestricoes, int qtdVariaveis) {
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
