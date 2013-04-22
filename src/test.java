
public class test {

	private boolean patattte;
	private int frigo;

	public boolean isPatate() {
		return patattte;
	}

	public void setPatate(boolean patate) {
		this.patattte = patate;
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
		return "test [patate=" + patattte + ", frigo=" + frigo + "]";
	}
	
}
