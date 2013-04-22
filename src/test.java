
public class test {
	private boolean patate;
	private int frigo;

	public boolean isPatate() {
		return patate;
	}

	public void setPatate(boolean patate) {
		this.patate = patate;
	}

	public int getFrigo() {
		return frigo;
	}

	public void setFrigo(int frigo) {
		this.frigo = frigo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "test [patate=" + patate + ", frigo=" + frigo + "]";
	}
	
}
