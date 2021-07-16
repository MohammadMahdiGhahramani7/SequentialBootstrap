/*
This java code implements the SequentiallyBootstrap method,
introduced on chapter 4(Sample Weights) of part 1(Data Analysis) of 
Advances in Financial Machine Learning by MARCOS LÓPEZ DE PRADO
*/

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;

public class SequentiallyBootstrap {
	
	public static void print_arraylist(ArrayList<Integer> phi) {
	/*
	      This function is just for printing purposes.
	      By calling this function, ArrayLists are printed
	      like Python lists.
	      
	      ArrayList<Integer> arrlst =  new ArrayList<Integer>();
	      arrlst.add(1);
	      arrlst.add(2);
	      arrlst.add(3);
	      
	      print_arraylists(arrlst); => [1, 2, 3]
	*/
		
		System.out.print("[");
		
		for(int i=0; i<phi.size(); i++) {
			
			if(i!=phi.size()-1)
				
				System.out.print(phi.get(i)+", ");
			
			else
				
				System.out.print(phi.get(i));
			
		}
		
		System.out.print("]");
		
	}
	
	public static void print_array(float[] array) {
	/*
	      This function is just for printing purposes.
	      By calling this function, java arrays are printed
	      like Python lists.
	      
	      flot[] arr = {1,2,3};
	      
	      print_array(arr); => [1, 2, 3]
	*/
		
		System.out.print("[");
		
		for(float k : array) 
			
			System.out.print(k+", ");
		
		System.out.print("]");
	}
	
	
	public static void print_matrix(float[][] matrix) {
	/*
	      This function is just for printing purposes.
	      By calling this function, java matrixes are printed
	      like its standard forms.
	      
	      float[][] matrix = {{0, 1, 0},
				  {1, 0, 1}};
				     		  
	      print_matrix(matrix); => 0.0 1.0 0.0 
                                       1.0 0.0 1.0 
	*/
		
		for(float[] v : matrix) 
			
		{
			for(float k : v) {
				
				System.out.print(k+" ");
				
			}
			
			System.out.println("");
		}
		
	}
	
	public static float [][] trans(float [][] A){
	/*
		Simply transposes a matrix by switching its rows with its columns
		
		float[][] mat = {{2,3,7},
				 {1,0,8}};
				         
		print_matrix(trans(mat)); => 2.0 1.0 
					     3.0 0.0 
 					     7.0 8.0 
	*/
		
		int row = A.length;
		int col = A[0].length;
		
		float [][] B = new float [col][row];
		
		for(int i=0; i<col;i++) {
			
			for(int j=0; j<row; j++) {
				
				B[i][j] = A[j][i];
			}
			
		}
		
		return B;
		
	}
	

	public static float sum_array(float[] arr) {
	/*
	      This function calculates the sum of elements belonging to an array.
	      
	      float[] arr = {1,2,3};
	   
	      sum_array(arr); => 6.0
	*/
		
		float sum = 0.0f;
		
		for(float value : arr) 
			
			sum += value;
		
		return sum;
		
	}
	
	
	public static float[] sum(float[][] matrix,int axis) {
	/*
	      This function calculates the column or row based sum over a matrix.
	      
	      float[][] matrix = {{0, 1, 0, 0, 1, 0, 1, 1, 0, 0},
				  {1, 0, 1, 1, 0, 1, 1, 0, 0, 0},
				  {0, 1, 1, 1, 1, 0, 0, 1, 1, 1},
				  {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
			          {1, 0, 0, 0, 0, 1, 0, 1, 1, 1},
				  {1, 0, 1, 1, 0, 1, 0, 0, 0, 1},
				  {0, 0, 0, 1, 1, 0, 1, 0, 1, 0},
				  {0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
				  {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
				  {0, 0, 1, 0, 1, 0, 0, 0, 1, 0}};
		
              print_array(sum(matrix,0)); => [4.0, 5.0, 7.0, 9.0, 5.0, 5.0, 4.0, 4.0, 1.0, 3.0]
	      print_array(sum(matrix,1)); => [4.0, 4.0, 5.0, 6.0, 5.0, 4.0, 4.0, 4.0, 6.0, 5.0]
	*/
		
		float [] c_t;
		float sum = 0.0f;
		
		if(axis == 0) {
			
			c_t = new float[matrix.length];
			
			Arrays.fill(c_t, 0);
			
		}
		
		else{
			
			c_t = new float[matrix[0].length];
			
			Arrays.fill(c_t, 0); 
			
			matrix = trans(matrix);
			
		}
							
			for(int i=0 ; i<c_t.length ; i++) {
				
				for(float value : matrix[i]) {
					
					sum += value;
					
				}
				
				c_t[i] = sum+0.0000001f;
				
				sum = 0;
				
				}
			
			return c_t;
			
	}
	
	
	public static float[][] delete_zeros(float[][] matrix){
	/*
		This function drops all zero rows of a matrix.
		
		float[][] mat = {{0, 0, 0, 0, 0, 0},
	     			 {0, 0, 1, 1, 0, 0},
	     		         {1, 0, 0, 1, 1, 1}};
	     				 
		print_matrix(delete_zeros(mat)); => 0, 0, 1, 1, 0, 0
	     				            1, 0, 0, 1, 1, 1
	*/ 
		
		int l=0;
		
                ArrayList<Integer> err = new ArrayList<Integer>();
        
		for(int i=0; i<matrix.length; i++) {
			
			if(sum_array(matrix[i]) == 0.0f)
				
				err.add(i);
			}
		
		float[][] new_matrix = new float[matrix.length-err.size()][matrix[0].length];
		
		for(int j=0; j<matrix.length; j++) {
			
			if(!err.contains(j)) 
				
				new_matrix[l++] = matrix[j];
			
		}
		
		return new_matrix;
		
	}
	
	
	public static float[] broadcast(float num, int size) {
	/*
		 Creates a java array of length size and assign each element to num.
		 
		 print_array(broadcast(2, 5)); => [2.0, 2.0, 2.0, 2.0, 2.0]
        */
		
		float[] result = new float[size];
		
		Arrays.fill(result, num);
		
		return result;
	}
	
	
	public static float[] div_arr(float[] arr1, float[] arr2) {
		
	/*
		 Divides each element of arr1 on its corresponding element in arr2.
		 
		 float[] arr1 = {1,2,3};
		 float[] arr2 = {2,1,3};
		 print_array(div_arr(arr1, arr2)); => [0.5, 2.0, 1.0]
	*/
    	
		for(int i=0; i<arr1.length ; i++) 
			
			arr1[i] /= arr2[i];
		
		return arr1;
		
	}
	
	
        public static float[][] div_matrix(float[][] matrix, float[] arr, int axis) {
    	/*
    	 	Divides each row/column of a matrix on its corresponding element in arr.
    	 
         	float[][] mat = {{1,2,3},
			         {4,5,6},
			         {7,8,9}};
		
	 	float[] arr = {1,2,3};
		
	 	print_matrix(div_matrix(mat, arr, 0)); => 1.0 2.0 3.0 
                                                          2.0 2.5 3.0 
                                                          2.3 2.6 3.0 
		
		
		
	 	print_matrix(div_matrix(mat, arr, 1)); => 1.0 1.0 1.0 
						          4.0 2.5 2.0 
						          7.0 4.0 3.0
    	*/
    	
    	boolean bool = false;
    	
    	if(axis==1) {
    		
    		matrix = trans(matrix);
    		
    		bool = true;
    		
    	}
    	
		for(int i=0; i<matrix.length; i++) {
			
			for(int j=0; j<matrix[0].length; j++) {
				
				matrix[i][j] /= arr[i];
			}
		}
		
		if(bool) 
			
			return trans(matrix);
		
		return matrix;
	}
    
    
	
	public static float[] getAvgUniqueness(float[][] indM) {
	/*
		Java code for (SNIPPET 4.4 : COMPUTE AVERAGE UNIQUENESS - Page 65) of 
		Advances in Financial Machine Learning by MARCOS LÓPEZ DE PRADO
	*/
		
		float[] count = sum(indM, 1);
		float[] c_t = sum(indM, 0);
		float[][] u = div_matrix(indM, c_t, 0);
		
		float[] sum_u_col = sum(u, 1);
		float[] avgU = div_arr(sum_u_col, count);
		return avgU;
	}
	
	
	public static int choice(int[] list, float[] p) {
	/*
		Since there is no java function for np.random.choice(In Python),
		this function performs this task.
		
		Python : np.random.choice(list, probs)
		Java : choice(list, probs)
	*/
		
		Random random = new Random();
		
		float threshold = random.nextFloat();
		
		float sum_p = 0.0f;
		
                int i = 0;		
        
		for(i=0; i<list.length; i++) {
			
			sum_p += p[i];
			
			if(sum_p >= threshold) 
				break;
		}
		
		return list[i];
		
	}
	
	
	public static ArrayList<Integer> seqBootstrap(float[][] indM, Integer sLength) {
	/*
		Java code for (SNIPPET 4.5 : RETURN SAMPLE FROM SEQUENTIAL BOOTSTRAP - Page 65) of 
		Advances in Financial Machine Learning by MARCOS LÓPEZ DE PRADO
	*/
		
		int cols = indM[0].length;
		
		int[] col_names = new int[cols];
		
		for(int u=0; u<cols; u++) 
			
			col_names[u] = u;
		
		sLength = sLength!= null ? sLength : cols;
		
		ArrayList<Integer> phi =  new ArrayList<Integer>();
		
		float[][] indM_ =  new float[sLength][sLength];
		
		float[] avgU =  new float[cols];
		
		int c = 0;
		
		for(int j=0; j<sLength; j++) {
			
			for(int i=0; i<cols; i++) {
				
				phi.add(i);
				
				for(int k : phi) {
				
					indM_[c++] = (trans(indM)[k]);
				}
				
				phi.remove(phi.size()-1);
				
				float[][] x = trans(delete_zeros(indM_));
				
				c = 0;		
				
				avgU[i] = (getAvgUniqueness(x)[x[0].length -1]);
				
			}
						
			float[] prob = div_arr(avgU, broadcast(sum_array(avgU),avgU.length));
			
			phi.add(choice(col_names,prob));
			
			}
		
			return phi;
		}
		
	public static void main(String[] args) {
		
		// TEST ONE
		
		float[][] matrix1 = {{0, 1, 0, 0, 1, 0, 1, 1, 0, 0},
				     {1, 0, 1, 1, 0, 1, 1, 0, 0, 0},
				     {0, 1, 1, 1, 1, 0, 0, 1, 1, 1},
				     {1, 1, 1, 1, 1, 0, 1, 1, 1, 1},
				     {1, 0, 0, 0, 0, 1, 0, 1, 1, 1},
				     {1, 0, 1, 1, 0, 1, 0, 0, 0, 1},
				     {0, 0, 0, 1, 1, 0, 1, 0, 1, 0},
				     {0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
				     {0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
				     {0, 0, 1, 0, 1, 0, 0, 0, 1, 0}};
		
		long startTime1 = System.nanoTime();
		
		print_arraylist(seqBootstrap(matrix1, null));  
		
		long endTime1 = System.nanoTime();
		
		System.out.println("\nTEST ONE took "+(endTime1 - startTime1)/1000000 + " miliseconds"); 
		
		// TEST TWO
		
		float[][] matrix2 = new float[100][100];
		
		for(int row=0; row<matrix2.length; row++) {
			
			for(int col=0; col<matrix2[0].length; col++) {
				
				matrix2[row][col] = choice(new int[] {0,1}, new float[] {0.5f, 0.5f}); 
			}
		}
				
		long startTime2 = System.nanoTime();
		
		print_arraylist(seqBootstrap(matrix2, null));  
		
		long endTime2 = System.nanoTime();
		
		System.out.println("\nTEST TWO took "+(endTime2 - startTime2)/1000000 + " miliseconds"); 
		
		/*
		 [9, 4, 3, 6, 1, 7, 1, 8, 9, 6]
                 TEST ONE took 3 miliseconds
         
                 [39, 57, 40, 6, 61, 89, 92, 67, 17, 67, 27, 33, 94, 41, 89, 35, 98, 18,
                  19, 18, 10, 69, 98, 93, 3, 25, 23, 67, 63, 6, 30, 96, 66, 17, 63, 46,
                  51, 63, 90, 10, 69, 78, 95, 92, 39, 58, 80, 95, 22, 72, 60, 74, 74, 73,
                  24, 59, 25, 38, 84, 54, 96, 84, 20, 55, 32, 88, 1, 82, 2, 70, 78, 6,
                  33, 61, 53, 10, 92, 45, 96, 45, 31, 87, 54, 65, 70, 63, 23, 2, 72, 24,
                  95, 66, 30, 5, 33, 16, 82, 63, 26, 47]
                 TEST TWO took 8025 miliseconds
		*/
	}

}
