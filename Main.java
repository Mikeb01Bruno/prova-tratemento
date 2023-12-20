package Aplicação;

import java.nio.channels.ScatteringByteChannel;
import java.time.LocalDate;
import java.util.Scanner;
import Classes.*;

public class Main {

    public static Scanner teclado = new Scanner(System.in);
    public static  Clinica clinica;

    /* Obtem a data de hoje */
    public static LocalDate dtHoje = LocalDate.now();

    public static String localAtendimento;

    public static void main(String[] args) {
            int opcao;
            int escolha;
            double novoValor;

            /* Instancia a CLINICA  com os dados informados */
            clinica = new Clinica("Senai Saúde", "Av Dendezeiros, 188, Bonfim", "123-123-123-45", 75, 150);

            /* Obtem a data de hoje */
            dtHoje = LocalDate.now();

            /* Define o local do atendimento (poderia ser lido também)*/
            localAtendimento = "Unidade Dendezeiros";

            /* Exibe o menu de opções */

            do {
                System.out.println("\n\n" + clinica.getNome() + "\n");
                System.out.println("1 - Realizar atendimento");
                System.out.println("2 - Consultar média");
                System.out.println("3 - Alterar valores da consulta / checkup");
                System.out.println("4 - Encerrrar");
                System.out.println("Digite a opção desejada: ");
                opcao = teclado.nextInt();

                switch (opcao) {
                    case 1:
                        try {
                            realizarAtendimento();
                        }catch (EAtendimentoNaoRegistradoException e){
                            System.out.println("O atendimento não foi registrado. Deve ser selecionado um tipo de atendimento válido! ");
                        }catch (EValorInvalidoException e){
                            System.out.println("Verifique os valores informados. " + e.getMessage());
                        }catch (Exception e){
                            System.out.println("Ocorreu uma exceção: "+e.getMessage());
                        }
                        break;

                    case 2:

                        /* Verifica se tem atendimentos realizados para evitar a divisão por zero*/
                        if (clinica.getAtendimentosRealizados().isEmpty()){
                            System.out.println("Nenhum atendimento foi realizado!");
                        }else {
                            /* Obtem o valor total dos atendimentos feitos e divide pela quantidade de atendimentos realizados*/
                            double media = clinica.getValorTotal() / clinica.getAtendimentosRealizados().size();
                            System.out.println("Media dos valores dos atendimentos realizados: " + media);
                        }
                        break;

                    case 3:

                        /* Solicita os dados para alteração dos valores */
                        try{
                            System.out.println("Valor atual da consulta: R$ " + clinica.getValorConsulta());
                            System.out.println("Valor atual do adicional para checkup: R$ " + clinica.getValorAdicional());

                            System.out.println("Digite 1 para alterar o valor da consulta e 2 para alterar o valor do adicional para exames");
                            escolha = teclado.nextInt();

                            if (escolha != 1 && escolha != 2) {
                                System.out.println("Opção inválida!");
                            }else {

                                System.out.println("Digite o novo valor");
                                novoValor = teclado.nextFloat();

                                if (escolha == 1) {
                                    clinica.setValorConsulta(novoValor);
                                } else {
                                    clinica.setValorAdicional(novoValor);
                                }
                                System.out.println("Valor alterado com sucesso!");
                            }
                        }catch (EValorInvalidoException e){
                                System.out.println("Valor inválido !");
                        }catch (Exception e){
                                System.out.println("Ocorreu uma exceção: "+e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("Encerrando...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                        break;
                }
            } while (opcao != 4);
        }

        public static void realizarAtendimento() throws EAtendimentoNaoRegistradoException{

            int tipoAtendimento;

            /* Obtem os dados do paciente */
            System.out.println("Digite os dados do paciente");

            /* Limpando o buffer do teclado */
            if (teclado.hasNextLine()) {
                teclado.nextLine();
            }

            System.out.println("CPF: ");
            String cpf = teclado.nextLine();

            System.out.println("Nome: ");
            String nome = teclado.nextLine();

            System.out.println("Data de nascimento");
            System.out.println("Dia: ");
            int dia = teclado.nextInt();
            System.out.println("Mês: ");
            int mes = teclado.nextInt();
            System.out.println("Dia: ");
            int ano = teclado.nextInt();
            LocalDate dtNascimentoPaciente = LocalDate.of(ano, mes, dia);

            /* Limpando o buffer do teclado */
            if (teclado.hasNextLine()) {
                teclado.nextLine();
            }

            System.out.println("Plano de saúde: ");
            String planoSaude = teclado.nextLine();

            /* Instancia PACIENTE */
            Paciente paciente = new Paciente(cpf, nome, dtNascimentoPaciente, planoSaude);

            System.out.println("Qual o tipo de atendimento? Digite 1 para consulta ou 2 para checkup");
            tipoAtendimento = teclado.nextInt();

            double valorAtendimento = clinica.realizarAtendimento(tipoAtendimento, paciente, dtHoje, localAtendimento);
            System.out.println("Valor do atendimento R$: "+valorAtendimento);

        }



}