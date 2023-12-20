package Classes;

/* Classe de exceção NÃO MONITORADA (herdeira de RuntimeException) */
public class EValorInvalidoException extends RuntimeException {
    /* Redefinição do construtor da classe para atualização da mensagem */
    public EValorInvalidoException (String mensagem) {
        super(mensagem);
    }
}
