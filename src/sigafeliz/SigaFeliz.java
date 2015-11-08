
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sigafeliz;

/* Programa de venda de passagens para a empresa Siga Feliz
   Desenvolvido por Arsiclaro Rodrigues para o projeto interdisciplinar da faculdade Anhembi Morumbi 
   1º semestre GTI - 2015
*/

/* Revisão e correção para atender as correções depois dos testes da Revisão 1
   revisado por Francisco Simões Filho
*/
//import java.util.InputMismatchException; 
import java.util.Scanner;

public class SigaFeliz {

    public static void main(String[] args) {
        //Declaracao de variaveis, vetores e scanner
        Scanner entradaopc = new Scanner(System.in);
        int contador, assento, i, k, QTPoltronasOcupadas;
        String opcao;
        char tipoassento;
        int QTPoltrona = 24;
        int janela[] = new int[QTPoltrona];
        int corredor[] = new int[QTPoltrona];
        //Inicialização das variaveis opcao e assento 
        opcao = "0";
        assento = 0;
        QTPoltronasOcupadas = 0;      
        //O programa vai rodar até o usuário escolher a OPÇÃO 3 do menu inicial
        // Revisão 1 - tratamento das opções de Menu como String para facilitar
        // erro de digitação de valores alfanuméricos.
        while ( !opcao.equals("3")) {            
            //Exibição do menu inicial
            System.out.println("----------------------------");
            System.out.println("MENU - VENDA DE PASSAGENS \n1. Vender passagem \n2. Mapa de ocupacao \n3. Encerrar ");
            System.out.println("----------------------------");
            opcao = entradaopc.next();
            //OPÇÃO 1 - VENDA DE PASSAGENS
            if (opcao.equals("1")) {
                // Revisão 1 - Verificar se todas as poltronas de corredor e janela foram vendidas
                QTPoltronasOcupadas = 0;
                for (k = 0; k < QTPoltrona; k++)   { 
                    // Esta somatoria quando for igual ao dobro do número total de poltronas, inidica que todas estão ocupadas.
                    QTPoltronasOcupadas = janela[k] + corredor[k] + QTPoltronasOcupadas; 
                }
                // Pela somatoria de todas as poltronas, verifica se foram vendidos todos os assentos.
                if (QTPoltronasOcupadas == QTPoltrona * 2) {
                    System.out.println("\n");
                    System.out.println("=====================================");                                        
                    System.out.println("ÔNIBUS LOTADO!");
                    System.out.println("=====================================");
                    System.out.println("\n");                    
                } else  {                   
                    System.out.println("Por favor digite o número da poltrona desejada");
                    //A variável assento foi iniciada como 0. Enquanto ela estiver nesse valor ou o usuário digitar uma opção de poltrona fora do range,
                    //o bloco continuará a ser executado. 
                    while (assento < 1 || assento > QTPoltrona) {
                        //assento = entradaopc.nextInt();
                        // Revisão 1 - Tratado erro quando usuário digita valores alfanuméricos
                        try {
                            // dentro de um bloco "Try", tenta converter o valor digitado para inteiro
                            // em caso de alfanumerico é apresentada a mensagem de erro diferenciada.
                            assento = Integer.parseInt(entradaopc.next().trim());
                        }
                        catch (NumberFormatException nfe) {  
                            System.out.println("*** Somente serão aceitos valores numéricos. ***");
                        }
                        //Valida a entrada do usuário. Se estiver dentro do range 1~24, continua o programa, pedindo o tipo do assento
                        if (assento >= 1 && assento <= QTPoltrona) {
                            System.out.println("Digite C para assento no corredor ou J para assento na janela");
                            tipoassento = entradaopc.next().charAt(0);
                            //Valida o tipo de poltrona. Caso seja inválido, será pedido que se insira novamente o tipo até ser inserido C ou J.
                            // Revisão 1 - acrescido opções de 'c' para corredor e 'j' janela
                            while (tipoassento != 'C' && tipoassento != 'J' && tipoassento != 'c' && tipoassento != 'j') {
                                System.out.println("Tipo de poltrona inválido. Digite C para assento no corredor ou J para assento na janela");
                                tipoassento = entradaopc.next().charAt(0);
                            }
                            //Bloco para verificar se foi escolhido janela ou corredor. Respectivamente verifica a disponibilidade dos assentos e 
                            //seta os valores 1 nos vetores caso a poltrona esteja livre. 
                            // Revisão 1 - tratamento diferenciado para suportar ambas opções para janela
                            if (tipoassento == 'J' || tipoassento == 'j') {
                                if (janela[assento - 1] == 1) {
                                    System.out.println("Poltrona ocupada");
                                } else {
                                    janela[assento - 1] = 1;
                                    System.out.println("Venda efetivada");
                                }
                            // Revisão 1 - tratamento diferenciado para suportar ambas opções para corredor                            
                            } else if (tipoassento == 'C' || tipoassento == 'c') {
                                if (corredor[assento - 1] == 1) {
                                    System.out.println("Poltrona ocupada");
                                } else {
                                    corredor[assento - 1] = 1;
                                    System.out.println("Venda efetivada");
                                }
                            }
   
                        } else {
                            System.out.println("Número da poltrona inválido. Por favor selecione um assento entre 1 e 24");
                        }
                    } 
                }// if verifica total de poltronas já ocupadas    
                //Assento foi setada novamente como 0 para que a próxima vez que esse bloco for executado, isso é, quando o usuário quiser 
                // comprar outra passagem, o programa não preencha os vetores com valores anteriores de assentos.
                assento = 0;
            //OPÇÃO 2 - MAPA DE ASSENTOS    
            } else if (opcao.equals("2")) {
                System.out.println("\n");
                System.out.println("================================");
                System.out.println("  JANELA               CORREDOR");
                for (i = 0; i < QTPoltrona; i++) {
                    //Variável de escopo local somente para output dos dados na mesma linha. As linhas comentadas funcionam, mas exibiriam
                    //os dois vetores um abaixo do outro e não um ao lado do outro como atualmente. 
                    String posicao1,posicao2="";
                    if (janela[i] == 1) {
                        //System.out.println((i + 1) + " - Ocupada");
                        posicao1="Ocupada";
                    } else {
                        posicao1="Livre";
                        //System.out.println((i + 1) + " - Livre");
                    }
                    if (corredor[i] == 1) {
                       posicao2="Ocupada";
                        // System.out.println((i + 1) + " - Ocupada");
                    } else {
                        posicao2="Livre";
                        //System.out.println((i + 1) + " - Livre");
                    }
                    System.out.println((i+1)+ " " + posicao1 + "              " + (i+1) + " " + posicao2);
                }
                System.out.println("================================");
                System.out.println("\n");
                //OPÇÃO 3 - FIM DO PROGRAMA
            } else if (opcao.equals("3")) {
                System.exit(0);
            } else {
                System.out.println("\n\n\n");
                System.out.println("--------------------------------------------");                
                System.out.println("OPÇÃO INCORRETA, POR FAVOR ESCOLHA NOVAMENTE");
                System.out.println("--------------------------------------------\n\n\n");
            }
        }
    }

    
}

