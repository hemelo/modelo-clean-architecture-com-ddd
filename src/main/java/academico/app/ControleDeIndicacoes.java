package academico.app;

import academico.app.events.IndicacaoEvent;
import shared.app.services.EventDispatcher;
import academico.domain.models.Aluno;
import academico.domain.models.Indicacao;
import academico.app.projections.IndicacaoDto;
import academico.domain.repositories.AlunoRepository;
import academico.domain.repositories.IndicacaoRepository;

import javax.validation.Validator;

public class ControleDeIndicacoes {
    private AlunoRepository repositorioAlunos;
    private IndicacaoRepository repositorioIndicacoes;
    private EventDispatcher dispatcher;
    private Validator validator;

    public void indicar(IndicacaoDto dadosIndicacao){
        Aluno alunoQueIndicou = this.repositorioAlunos.buscaPorEmail(dadosIndicacao.getIndicador());

        if(alunoQueIndicou == null) alunoQueIndicou = this.repositorioAlunos.buscaPorMatricula(dadosIndicacao.getIndicador());

        if(alunoQueIndicou == null) throw new IllegalArgumentException();

        Indicacao indicacao = new Indicacao(alunoQueIndicou, dadosIndicacao.getEmailIndicado());
        repositorioIndicacoes.adicionar(indicacao);
        dispatcher.dispatch(new IndicacaoEvent(indicacao));
    }

    public static class Builder {

        private ControleDeIndicacoes controleDeIndicacoes;

        public Builder() {
            this.controleDeIndicacoes = new ControleDeIndicacoes();
        }

        public Builder alunoRepository(AlunoRepository repositorio){
            this.controleDeIndicacoes.repositorioAlunos = repositorio;
            return this;
        }

        public Builder dispatcher(EventDispatcher dispatcher){
            this.controleDeIndicacoes.dispatcher = dispatcher;
            return this;
        }

        public Builder indicacaoRepository(IndicacaoRepository repositorio){
            this.controleDeIndicacoes.repositorioIndicacoes = repositorio;
            return this;
        }

        public Builder validator(Validator validator){
            this.controleDeIndicacoes.validator = validator;
            return this;
        }

        public ControleDeIndicacoes criar() {
            return this.controleDeIndicacoes;
        }
    }
}
