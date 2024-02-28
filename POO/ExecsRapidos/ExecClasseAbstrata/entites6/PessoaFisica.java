package entites6;

public class PessoaFisica extends Pessoa {
	private Double despesasSaude;

	public PessoaFisica(String nome, Double rendaAnual, Double despesasSaude) {
		super(nome, rendaAnual);
		this.despesasSaude = despesasSaude;
	}

	public Double getDespesasSaude() {
		return despesasSaude;
	}

	@Override
	public double imposto() {
		double imposto;
		
		if (getRendaAnual() < 20000.0) {
			imposto = getRendaAnual()*0.15;
		}
		else {
			imposto = getRendaAnual()*0.25;
		}
		
		return imposto-getDespesasSaude()*0.5;
	}

}
