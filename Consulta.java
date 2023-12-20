package Classes;

import java.time.LocalDate;

public class Consulta {

    /* Toda consulta POSSUI UM PACIENTE*/
    private final Paciente paciente;
    private final LocalDate dtAtendimento;
    private final String localAtendimento;
    protected final double valor;

    public Consulta(Paciente paciente, LocalDate dtAtendimento, String localAtendimento, double valor) {

        /* Validação da data: sendo um LocalDate ou é uma data válida ou é NULL.  */
        if (dtAtendimento == null) {
            throw new EValorInvalidoException("Data de atendimento inválida!");
        }

        if (valor < 0) {
            throw new EValorInvalidoException("Valor inválido!");
        }

        this.paciente = paciente;
        this.dtAtendimento = dtAtendimento;
        this.localAtendimento = localAtendimento;
        this.valor = valor;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public LocalDate getDtAtendimento() {
        return dtAtendimento;
    }

    public String getLocalAtendimento() {
        return localAtendimento;
    }

    public double getValor() {
        return valor;
    }
}
