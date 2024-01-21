package entites;

import java.util.ArrayList;

import entites.enums.WorkerLevel;

public class Worker {
	private String name;
	private WorkerLevel level;
	private Double baseSalary = 1200.0;
	private Department department;

	private ArrayList<HourContract> contracts = new ArrayList();

	public Worker() {
	}

	public Worker(String name, WorkerLevel level, Double baseSalary, Department department) {
		this.name = name;
		this.level = level;
		this.baseSalary = baseSalary;
		this.department = department;
	}
	
	

	public ArrayList<HourContract> getContracts() {
		return contracts;
	}

	public void addContract(HourContract contract) {
		contracts.add(contract);
	}

	public void removeContract(HourContract contract) {
		contracts.remove(contract);
	}

	public void showContracts() {
		for(HourContract contract : contracts) {
			System.out.println("\n"+contract);
		}
	}
	public double income(int year, int month) {
		double sum = baseSalary;

		for (HourContract contract : contracts) {
			if (contract.getDate().getDayOfMonth() == month && contract.getDate().getYear() == month) {
				sum += contract.totalValue();
			}
		}
		return sum;
	}

	@Override
	public String toString() {
		return "Nome :" + name + "\nDepartamento: " + department +
				"\nNível: "+level+"\nSalário Base: "+baseSalary;
	}
}
