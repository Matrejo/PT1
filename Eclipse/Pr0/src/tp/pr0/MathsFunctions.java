package tp.pr0;

public class MathsFunctions {
	public static int factorial(int n) {
		int result = -1;
		
		if (n < 0) {
			result = 0;
		}
		
		else if (n == 0) {
			result = 1;
		}
		
		else {
			result = 1;
			
			for (int i = 1; i <= n; i++)
				result = result * i;
		}
		
		return result;
	}
	
	public static int combinatorial(int n, int k) {
		int result = 0, difference;
		
		if (k <= n) {
			difference = n - k;
			result = factorial(n) / (factorial(k) * factorial(difference));
		}
		
		return result;
	}
	
	public static void main(String args[]) {
		for (int i = 0; i < 6; ++i) {
			  for (int j = 0; j <= i; ++j) {
			    System.out.print(MathsFunctions.combinatorial(i,j) + " ");
			  }
			  System.out.println();
			}
	   }
}