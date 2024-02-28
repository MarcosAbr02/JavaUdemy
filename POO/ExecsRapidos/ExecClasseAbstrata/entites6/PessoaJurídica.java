package entites6;

public class PessoaJurídica extends Pessoa {
	private int numeroFuncionarios;

	public PessoaJurídica(String nome, Double rendaAnual, int numeroFuncionarios) {
		super(nome, rendaAnual);
		this.numeroFuncionarios = numeroFuncionarios;
	}

	public int getNumeroFuncionarios() {
		return numeroFuncionarios;
	}

	@Override
	public double imposto() {
		if(getNumeroFuncionarios() < 10) {
			return getRendaAnual()*0.16;
		}
		else {
			return getRendaAnual()*0.14;
		}
	}
}
