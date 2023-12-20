package Classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Clinica {
    private String nome;
    private String endereco;
    private String cnpj;
    private double valorConsulta;
    private double valorAdicional;

    /* Lista de CONSULTA que pode ser simples ou Checkup*/
    private ArrayList<Consulta> atendimentosRealizados;

    public Clinica(String nome, String endereco, String cnpj, double valorConsulta, double valorAdicional) {

        if (valorConsulta < 0 || valorAdicional < 0) {
            throw new EValorInvalidoException("Valor da consulta/checkup inválido!");
        }

        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.valorConsulta = valorConsulta;
        this.valorAdicional = valorAdicional;
        this.atendimentosRealizados = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public double getValorConsulta() {
        return valorConsulta;
    }

    public double getValorAdicional() {
        return valorAdicional;
    }

    public ArrayList<Consulta> getAtendimentosRealizados() {
        return atendimentosRealizados;
    }

    public double getValorTotal() {
        double valorTotalArrecadado = 0.0;

        /* Soma o valor de todas os atendimentos feitos independetemente se for consulta ou checkup*/
        for (Consulta consulta : this.atendimentosRealizados) {
            valorTotalArrecadado += consulta.getValor();
        }
        return valorTotalArrecadado;
    }

    public void setValorConsulta(double valorConsulta) {
        if (valorConsulta < 0) {
            throw new EValorInvalidoException("Valor da consulta inválido!");
        }
        this.valorConsulta = valorConsulta;
    }

    public void setValorAdicional(double valorAdicional) {
        if (valorAdicional < 0) {
            throw new EValorInvalidoException("Valor adicional inválido!");
        }
        this.valorAdicional = valorAdicional;
    }

    public double realizarAtendimento(int tipoAtendimento, Paciente paciente, LocalDate dtAtendimento, String localAtendimento) throws EAtendimentoNaoRegistradoException {

        Consulta atendimento;

        if ( tipoAtendimento != 1 && tipoAtendimento != 2) {
            /* Se o tipo de atendimento for diferente de 1 e de 2é um tipo inválido por isso gera exceção monitorada EAtendimentoNaoRegistradoException */
            throw new EAtendimentoNaoRegistradoException();
        }

        if (tipoAtendimento == 1) {
            /* Se o tipo de atendimento 1 instancia um objeto CONSULTA */
            atendimento = new Consulta(paciente, dtAtendimento, localAtendimento, valorConsulta);
        }
        else {
            /* Tipo de atendimento 2, instancia um objeto CHECKUP */
            atendimento = new Checkup(paciente, dtAtendimento, localAtendimento, valorConsulta, valorAdicional);
        }
        /* adiciona na lista e retorna o valor do atendimento*/
        atendimentosRealizados.add(atendimento);

        return atendimento.getValor();
    }

}
