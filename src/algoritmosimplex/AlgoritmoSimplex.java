package algoritmosimplex;
import java.util.Scanner;

/**
 *
 * @author André Lucas
 */
public class AlgoritmoSimplex {


    public static void main(String[] args) {
        
        Matriz matriz = new Matriz();
        int qtdVariaveisDecisao = 0;
        int qtdRestricoes = 0;
        
        Scanner input = new Scanner(System.in);
        System.out.println("# 4LG0R1TM0 S1MPL3X #");
        System.out.println("Digite a quantidade de variáveis de decisão: ");
        qtdVariaveisDecisao = input.nextInt();
        System.out.println("Digite a quantidade de restricões: ");
        qtdRestricoes = input.nextInt();
        System.out.println(" ");
        matriz.inciarTabela(qtdRestricoes, qtdRestricoes);
        
        
    }

}
