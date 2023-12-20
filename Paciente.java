package Classes;

import java.time.LocalDate;

public class Paciente {
    private final String cpf;
    private final String nome;
    private final LocalDate dtNascimento;
    private final String planoSaude;

    public Paciente(String cpf, String nome, LocalDate dtNascimento, String planoSaude) {
        /* Validação da data: sendo um LocalDate ou é uma data válida ou é NULL.  */
        if (dtNascimento == null) {
            throw new EValorInvalidoException("Data de nascimento inválida!");
        }

        /* Validação opcional: sendo uma data de nascimento, não deve ser depois da data de hoje */
        if (dtNascimento.isAfter(LocalDate.now())) {
            throw new EValorInvalidoException("Data de nascimento superior a data de hoje!");
        }
        this.dtNascimento = dtNascimento;
        this.cpf = cpf;
        this.nome = nome;
        this.planoSaude = planoSaude;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public String getPlanoSaude() {
        return planoSaude;
    }
}

