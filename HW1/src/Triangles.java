public class Triangles {
	public static void main(String[] args) {
		
	int x = Integer.parseInt(args[0]);
    int y = Integer.parseInt(args[1]);
	int z = Integer.parseInt(args[2]);
	
	String str="("+x+","+y+","+z+")";
	
	if  (x<=0 || y<=0 || z<=0) {
		System.out.println("Invalid input!");
	}
	
	else if (x*x+y*y==z*z || z*z+x*x==y*y || z*z+y*y==x*x) {
		System.out.println("The input "+str+" defines a right triangle!");
		}
	
	else if (x==y || z==x || z==y) {
		System.out.println("The input "+str+" defines a isosceles triangle!");
		}
	
	else {
		System.out.println("The input "+str+" does not define an isosceles or a right triangle!");
		}
	}
}
