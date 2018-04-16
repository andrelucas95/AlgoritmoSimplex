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

    public Matriz(int restricoes, int variaveis) {
        this.restricoes = restricoes;
        this.variaveis = variaveis;
    }

    //
    public void inciarTabela() {
        this.tabelaSimplex = new double[this.retornanumLinhas()][this.retornanumColunas()];
    }

    int retornanumLinhas() {
        return this.restricoes + 1;
    }

    int retornanumColunas() {
        return this.variaveis + this.restricoes + 1;
    }

    public double getCoeficinte(int linha, int coluna) {

        return tabelaSimplex[linha][coluna];

    }

    public void inserirCoeficienteTabela(int linha, int coluna, double valor) {

        tabelaSimplex[linha][coluna] = valor;

    }

    public boolean ehVdecisao(int coluna, int qtdVariaveis) {
        return coluna < qtdVariaveis;
    }

    public boolean ehColunaVfolga(int colunaAtual, int qtdVariaveis) {
        return colunaAtual == qtdVariaveis;
    }

    public boolean ehFobjetiva(int linhaAtual, int numLinhas) {
        return linhaAtual == numLinhas;
    }

    public boolean ehUltimaColuna(int colunaAtual, int numColunas) {
        return colunaAtual == numColunas;
    }

    public int retornaColunaQueEntra() {
        double maiorValor = 0;
        int colunaQueEntra = 0;
        int linha = this.retornanumLinhas() - 1; // Ultima linha da tabela, partindo de 0.
        int numColunas = this.retornanumColunas() - 1;//Numero total de colunas partindo de 0.
        for (int coluna = 0; coluna <= numColunas; coluna++) {
            if (tabelaSimplex[linha][coluna] > maiorValor) {
                maiorValor = tabelaSimplex[linha][coluna];
                colunaQueEntra = coluna;
            }
        }
        return colunaQueEntra;
    }

    public int retornaLinhaQueSai(int colunaQueEntra) {
        int linhaQueSai = 0;
        double menorDivisao = Double.MAX_VALUE;
        int numLinhas = this.retornanumLinhas() - 1;
        for (int linha = 0; linha <= numLinhas; linha++) {
            //Para ignorar linhas que possuam zero.
            if (tabelaSimplex[linha][colunaQueEntra] != 0) {
                if (tabelaSimplex[linha][this.retornanumColunas() - 1] != 0) {
                    if ((tabelaSimplex[linha][this.retornanumColunas() - 1] / tabelaSimplex[linha][colunaQueEntra]) < menorDivisao) {
                        menorDivisao = tabelaSimplex[linha][this.retornanumColunas() - 1] / tabelaSimplex[linha][colunaQueEntra];
                        linhaQueSai = linha;
                    }
                }
            }

        }
        return linhaQueSai;
    }

    public void realizarLiLaj(int colunaQueEntra, int linhaQueSai, int linhaQueAltera) {
        /* Li - (a)* Lj
          Li = linha que deseja modificar
          Lj = linha da variavel que entrou
          a = coeficiente de Li na coluna da variavel que entrou*/
        double coeficienteA = tabelaSimplex[linhaQueAltera][colunaQueEntra];
        for (int j = 0; j <= this.retornanumColunas() - 1; j++) {
            tabelaSimplex[linhaQueAltera][j] = tabelaSimplex[linhaQueAltera][j] - (coeficienteA * tabelaSimplex[linhaQueSai][j]);
        }
    }

    //Função para transformar o coeficiente em um 1 caso ele seja != de 1.
    public void dividirLinhaInteira(int linhaQsai, int colunaQentra) {
        double valor = tabelaSimplex[linhaQsai][colunaQentra];
        for (int j = 0; j <= this.retornanumColunas() - 1; j++) {
            tabelaSimplex[linhaQsai][j] = (tabelaSimplex[linhaQsai][j] / valor);
        }
    }

    //Verifica as linhas da coluna que entra para validar como uma variavel basica.
    public void verificaVariaveisBasicas(int colunaQentra, int linhaQsai) {
        for (int i = 0; i <= this.retornanumLinhas() - 1; i++) {
            if (i != linhaQsai) {
                if (tabelaSimplex[i][colunaQentra] != 0) {
                    this.realizarLiLaj(colunaQentra, linhaQsai, i);
                }
            }
        }
    }

    public boolean verificaMelhorSolucao() {
        boolean terminou = true;
        int linha = this.retornanumLinhas() - 1;
        for (int coluna = 0; coluna <= this.retornanumColunas() - 1; coluna++) {
            if (tabelaSimplex[linha][coluna] > 0) {
                terminou = false;
                break;
            }
        }
        return terminou;
    }
}
