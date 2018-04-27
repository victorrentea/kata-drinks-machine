package main.java;

public class Getraenk implements Comparable<Getraenk> {
	private String name;
	private double liter;

	public Getraenk(String name, double menge) {
		this.name = name;
		this.liter = menge;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getLiter() {
		return liter;
	}

	public void setLiter(double liter) {
		this.liter = liter;
	}

	public String toString() {
		return this.name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(liter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Getraenk other = (Getraenk) obj;
		if (Double.doubleToLongBits(liter) != Double.doubleToLongBits(other.liter))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Getraenk getraenk) {
		return this.getName().compareTo(getraenk.getName());
	}

}
