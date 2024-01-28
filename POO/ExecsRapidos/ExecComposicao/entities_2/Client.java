package entities_2;

import java.time.LocalDate;

public class Client {
	private String name;
	private String email;
	private LocalDate date;
	
	public Client(String name, String email, LocalDate date) {
		this.name = name;
		this.email = email;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public LocalDate getDate() {
		return date;
	}
	
	@Override
	public String toString() {
		return name+" ("+date+") - "+email;
	}
	
}
