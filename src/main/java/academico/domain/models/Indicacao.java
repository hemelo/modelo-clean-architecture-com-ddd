package academico.domain.models;

import academico.domain.contracts.HasEmail;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
public class Indicacao implements HasEmail {
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Email(message = "Email should be valid")
	private String indicado;

	@ManyToOne
	@JoinColumn(name = "indicante_id")
	private Aluno indicante;

	private final LocalDateTime dataIndicacao;

	public Indicacao() {
		this.dataIndicacao = LocalDateTime.now(ZoneId.of("UTC-3"));
	}

	public Indicacao(Aluno indicante, String indicado) {
		this.indicado = indicado;
		this.indicante = indicante;
		this.dataIndicacao = LocalDateTime.now(ZoneId.of("UTC-3"));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setIndicado(String indicado) {
		this.indicado = indicado;
	}

	public void setIndicante(Aluno indicante) {
		this.indicante = indicante;
	}

	public String getIndicado() {
		return indicado;
	}

	public Aluno getIndicante() {
		return indicante;
	}

	public LocalDateTime getDataIndicacao() {
		return dataIndicacao;
	}

	@Override
	public String getEmail() {
		return this.indicado;
	}

	@Override
	public String getNome() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}
}
