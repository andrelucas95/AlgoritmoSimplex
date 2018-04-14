package algoritmosimplex;

import java.util.Scanner;

/**
 *
 * @author André Lucas
 */
public class AlgoritmoSimplex {

    public static void main(String[] args) {

        int qtdVariaveisDecisao = 0;
        int qtdRestricoes = 0;
        int variaveisBasicas[];
        //
        Scanner input = new Scanner(System.in);
        System.out.println("# 4LG0R1TM0 S1MPL3X #");
        System.out.println("Digite a quantidade de variáveis de decisão: ");
        qtdVariaveisDecisao = input.nextInt();
        System.out.println("Digite a quantidade de restricões: ");
        qtdRestricoes = input.nextInt();
        System.out.println(" ");
        Matriz matriz = new Matriz(qtdRestricoes, qtdVariaveisDecisao);
        matriz.inciarTabela();
        int linha = matriz.retornanumLinhas() - 1;
        int coluna = matriz.retornanumColunas() - 1;
        double valor = 0;

        for (int i = 0; i <= linha; i++) {
            for (int j = 0; j <= coluna; j++) {

                //Ultima linha, refere-se aos coeficientes da função objetiva
                /*if (matriz.ehFobjetiva(i, linha)) {
                    if (matriz.ehUltimaColuna(j, coluna)) {
                        matriz.inserirCoeficienteTabela(i, j, 0);
                    }
                    System.out.println("Para a função objetivo, digite o coeficiente de x" + (j + 1));
                    valor = input.nextDouble();
                    matriz.inserirCoeficienteTabela(linha, coluna, valor);
                }*/
                //Variaveeis de decisão
                if (matriz.ehVdecisao(j, qtdVariaveisDecisao)) {
                    System.out.println("Para a Função " + (i + 1) + " insiria o coeficiente de x" + (j + 1) + ": ");
                    valor = input.nextDouble();
                    matriz.inserirCoeficienteTabela(i, j, valor);
                }
                //Variaveis de folga
                //Lógica não correta
                // a linha que estou perc e  a col do momento se f obj = 0 // matriz[i][j+i] = 1;
                if (matriz.ehVfolga(j, qtdVariaveisDecisao, coluna)) {
                    //significa que esta na função objetiva logo apenas inserir 0 pra f1 f2 ... fn
                    if (i == linha) {
                        matriz.inserirCoeficienteTabela(i, j, 0);
                        // não quero que processe o segundo if, pois ja estamos na função objetivo.
                        break;
                    }
                    if (j + 1 < coluna) {
                        matriz.inserirCoeficienteTabela(i, j + i, 1);
                    }

                }
                //Ultima coluna, referente ao valor de 'b'
                if (matriz.ehUltimaColuna(j, coluna)) {
                    System.out.println("Para a Função " + (i + 1) + " insiria o valor de b");
                    valor = input.nextDouble();
                    matriz.inserirCoeficienteTabela(i, j, valor);
                    //O VALOR DE "B" NA FUNÇÃO OBJETIVO É SEMPRE 0.
                    if(matriz.ehFobjetiva(i, linha)){
                        matriz.inserirCoeficienteTabela(i, j, 0);
                    }

                }
            }
        }
        for (int i = 0; i <= linha; i++) {
            for (int j = 0; j <= coluna; j++) {
                System.out.println("Linha : " + i + " Coluna: " + j + " Valor: " + matriz.getCoeficinte(i, j));
                System.out.println(" ");
            }
        }
    }
}

/*System.out.println("Linha : " + i + " Coluna: " + j+" Valor: "+matriz.tabelaSimplex[i][j]); */
