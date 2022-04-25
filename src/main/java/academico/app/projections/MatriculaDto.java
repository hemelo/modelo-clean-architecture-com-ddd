package academico.app.projections;

public class MatriculaDto {
    private String nome;
    private String cpf;
    private String email;

    public MatriculaDto(String nomeAluno, String cpfAluno, String emailAluno) {
        this.nome = nomeAluno;
        this.cpf = cpfAluno;
        this.email = emailAluno;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
}
