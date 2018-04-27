package musicbox;

//Stupid Value Object that does NOOOOOT need to be tested.
// Value Object - immutable, hashcode/equals.
public class Getraenk {
	private final String name; // TODO? join the name + liter in a single String name
	private final double liter;
	private final Double preis;
	
	public Getraenk(String name, double liter, Double preis) {
		this.name = name;
		this.liter = liter;
		this.preis = preis;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(liter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((preis == null) ? 0 : preis.hashCode());
		return result;
	}

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
		if (preis == null) {
			if (other.preis != null)
				return false;
		} else if (!preis.equals(other.preis))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}
	public double getLiter() {
		return liter;
	}
	public Double getPreis() {
		return preis;
	}



}
