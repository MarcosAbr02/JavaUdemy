package listas;

public class Employer {
	private Integer id;
	private String name;
	private Double salary;

	public Employer(int id, String name, double salario) {
		this.id = id;
		this.name = name;
		this.salary = salario;
	}

	public int getId() {
		return id;
	}

	public void increaseSalary(double percent) {
		salary += salary*percent/100;
	}

	@Override
	public String toString() {
		return id + ", " + name + ", " + String.format("%.2f", salary);
	}
}
