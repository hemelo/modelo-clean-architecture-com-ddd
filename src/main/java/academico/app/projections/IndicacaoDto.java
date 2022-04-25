package academico.app.projections;


public class IndicacaoDto {
    private String indicado;
    private String emailIndicador;

    public IndicacaoDto(String emailIndicado, String emailIndicador) {
        this.indicado = emailIndicado;
        this.emailIndicador = emailIndicador;
    }

    public String getEmailIndicado() {
        return indicado;
    }

    public String getIndicador() {
        return emailIndicador;
    }
}
