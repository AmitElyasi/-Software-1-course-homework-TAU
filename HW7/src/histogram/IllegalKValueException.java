package il.ac.tau.cs.sw1.ex7.histogram;

public class IllegalKValueException extends Exception {

	private static final long serialVersionUID = 1L;

	public IllegalKValueException(int k){
		super("Illegal k value: " + k);
	}
}
