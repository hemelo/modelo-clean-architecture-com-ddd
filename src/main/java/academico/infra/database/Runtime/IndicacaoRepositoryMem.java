package academico.infra.database.Runtime;

import academico.domain.models.Aluno;
import academico.domain.models.Indicacao;
import academico.domain.repositories.IndicacaoRepository;

import java.util.ArrayList;
import java.util.List;

public class IndicacaoRepositoryMem implements IndicacaoRepository {

    private List<Indicacao> indicacoes = new ArrayList<>();

    @Override
    public void adicionar(Indicacao indicacao) {
        this.indicacoes.add(indicacao);
    }
}
