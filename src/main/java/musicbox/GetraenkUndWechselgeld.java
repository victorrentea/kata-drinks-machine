package musicbox;

import java.util.List;

public class GetraenkUndWechselgeld {
	private final Getraenk getraenk;
	private final List<Muenze> muenzen;

	public GetraenkUndWechselgeld(Getraenk getraenk, List<Muenze> muenzen) {
		this.getraenk = getraenk;
		this.muenzen = muenzen;
	}

	public Getraenk getGetraenk() {
		return getraenk;
	}


	public List<Muenze> getMuenzen() {
		return muenzen;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getraenk == null) ? 0 : getraenk.hashCode());
		result = prime * result + ((muenzen == null) ? 0 : muenzen.hashCode());
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
		GetraenkUndWechselgeld other = (GetraenkUndWechselgeld) obj;
		if (getraenk == null) {
			if (other.getraenk != null)
				return false;
		} else if (!getraenk.equals(other.getraenk))
			return false;
		if (muenzen == null) {
			if (other.muenzen != null)
				return false;
		} else if (!muenzen.equals(other.muenzen))
			return false;
		return true;
	}

}
