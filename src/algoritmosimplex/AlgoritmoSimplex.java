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
        int linha = matriz.retornanumLinhas();
        int coluna = matriz.retornanumColunas();
        double valor = 0;
        
        for(int i = 0; i < linha - 1; i++ ){
            for(int j = 0; j < coluna - 1; j++){
                if(j < qtdVariaveisDecisao){
                    System.out.println("Para a Função "+(i + 1)+" insiria o coeficiente de x"+(j + 1)+": ");
                    valor = input.nextDouble();
                    matriz.inserirCoeficienteTabela(i, j, valor);
                }
                if(j == coluna - 1){//NÃO ESTÁ ENTRANDO NA ULTIMA COLUNA
                    System.out.println("Para a Função "+(i + 1)+" insiria o valor de b");
                    valor = input.nextDouble();
                    matriz.inserirCoeficienteTabela(i, j, valor);
                }
                if((j > qtdVariaveisDecisao - 1) && (j < coluna - 1)){
                    // é variavel de folga
                    // resta condicao para inserir 0 ou 1
                    matriz.inserirCoeficienteTabela(i, j, 1);
                }
            }
        }
        for(int i = 0; i < matriz.retornanumLinhas(); i++ ){
            for(int j = 0; j < coluna; j++){
               System.out.println("Linha : " + i + " Coluna: " + j+" Valor: "+matriz.tabelaSimplex[i][j]);
            }
        }
        
    }

}
/*System.out.println("Linha : " + i + " Coluna: " + j+" Valor: "+matriz.tabelaSimplex[i][j]); */