
public class Strings {
	public static void main(String[] args) {
		
		String str1 = args[0].toLowerCase();
	    String str2 = args[1].toLowerCase();
	    String origStr3 = args[2];
		String str3 = origStr3.toLowerCase();
		
		
		if (str3.length()==str1.length()+str2.length()+1) {
			if (str3.startsWith(str1)) {
				if(str3.startsWith(str2, str1.length()+1)){
					System.out.println(origStr3+" is a concatenation.");
				}
			}
			else if (str3.startsWith(str2)) {
				if(str3.startsWith(str1, str2.length()+1)){
					System.out.println(origStr3+" is a concatenation.");
				}
		    }
			else{
			System.out.println(origStr3+" is not a concatenation.");
			}
		}
		else{
			System.out.println(origStr3+" is not a concatenation.");	
		}
	}
}
