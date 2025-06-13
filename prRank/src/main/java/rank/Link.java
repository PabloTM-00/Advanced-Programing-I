package rank;

import java.util.Objects;

public class Link {
	
	private String origin;
	private String linked;
	
	public Link(String org, String lnk) {
		super();
		this.origin = org;
		this.linked = lnk;
	}
	
	
	
	public String getOrigin() {
		return origin;
	}



	public String getLinked() {
		return linked;
	}

	@Override
	public int hashCode() {
	    return Objects.hash(origin.toLowerCase(), linked.toLowerCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		return linked.equalsIgnoreCase(other.linked) && origin.equalsIgnoreCase(other.origin);
	}

	@Override
	public String toString() {
		return origin + "->" + linked;
	}

}
