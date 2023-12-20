package Classes;

import java.time.LocalDate;

/* Checukp É uma CONSULTA, possui todos os recursos de CONSULTA. Assim sendo HERDA CONSULTA*/
public class Checkup extends Consulta {
    private final double valorAdicional;

    public Checkup(Paciente paciente, LocalDate dtAtendimento, String localAtendimento, double valor, double valorAdicional) {
        /* Chama o construtor herdado para atualizar as informações da CONSULTA*/
        super(paciente, dtAtendimento, localAtendimento, valor);

        if (valorAdicional < 0) {
            throw new EValorInvalidoException("Valor adicional inválido!");
        }

        this.valorAdicional = valorAdicional;
    }


    public double getValorAdicional() {
        return valorAdicional;
    }

    /* Método polimorfico que retorna o valor da consulta acrescentando o valor adicional
       e concedendo desconto quando o plano for PlanSenai*/
    @Override
    public double getValor() {

        /* Verifica o plano do PACIENTE.
           Como esse é atributo é PRIVATE, deve-se utilizar o método getPaciente para ter acesso */
        if (getPaciente().getPlanoSaude().equalsIgnoreCase("PlanSenai")) {
            /* Concede 15% de desconto no valor adicional */
            return super.getValor() + valorAdicional * 0.85;
        }
        return super.getValor() + valorAdicional;
    }
}