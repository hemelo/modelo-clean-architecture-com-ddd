package academico.domain.models;

import academico.domain.contracts.HasEmail;
import academico.domain.contracts.validators.Telephone;
import org.hibernate.validator.constraints.br.CPF;
import shared.domain.models.User;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Aluno extends User implements HasEmail {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	@CPF
	@NotNull
	@Column(unique=true)
	private String cpf;

	@Email(message = "Email should be valid")
	private String email;

	@ElementCollection(fetch = FetchType.LAZY)
	private final List<String> telefones = new ArrayList<>();

	private final LocalDateTime dataIndicacao;

	public Aluno() {
		this.dataIndicacao = LocalDateTime.now(ZoneId.of("UTC-3"));
	}

	private Aluno(String nome, String cpf){
		this.nome = nome;
		this.cpf = cpf;
		this.dataIndicacao = LocalDateTime.now(ZoneId.of("UTC-3"));
	}

	public Aluno(String cpf, String nome, String email) {
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.dataIndicacao = LocalDateTime.now(ZoneId.of("UTC-3"));
	}

	public void adicionarTelefone(@Telephone String phone) {
		this.telefones.add(phone);
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public List<@Pattern(regexp="") String> getTelefones() {
		return telefones;
	}

	public static class Builder {

		private Aluno aluno;

		public Builder(String nome, String cpf) {
			this.aluno = new Aluno(nome, cpf);
		}

		public Builder telefone(String phone) {
			this.aluno.adicionarTelefone(phone);
			return this;
		}

		public Builder email(String email){
			this.aluno.setEmail(email);
			return this;
		}

		public Builder username(String username){
			this.aluno.setUsername(username);
			return this;
		}

		public Builder password(String password){
			this.aluno.setPassword(password);
			return this;
		}

		public Aluno criar() {
			return this.aluno;
		}
	}
}






