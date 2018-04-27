package musicbox;

import java.util.List;

public class GetraenkUndWechselgeld {
	private Getraenk getraenk;
	private List<Muenze> muenzen;

	public GetraenkUndWechselgeld() {
	}

	public GetraenkUndWechselgeld(Getraenk getraenk, List<Muenze> muenzen) {
		this.getraenk = getraenk;
		this.muenzen = muenzen;
	}

	public Getraenk getGetraenk() {
		return getraenk;
	}

	public void setGetraenk(Getraenk getraenk) {
		this.getraenk = getraenk;
	}

	public List<Muenze> getMuenzen() {
		return muenzen;
	}

	public void setMuenzen(List<Muenze> muenzen) {
		this.muenzen = muenzen;
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
