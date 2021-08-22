/**
 * This class is the solution of maman14.
 * 
 * @author Yarin Hazan 
 * @version 20/05/2021
 */

public class Ex14
{
    /**
     * Returns the maximum fall between two numbers (not necessarily in consecutive cells).
     * The biggest number is before the lowest number in the array. 
     * @param a The array.
     * @return The maximalDrop - The big remainder between two numbers.
     * 
     * The time complexity is 3+n = O(n).
     * The space complexity is O(1) - you only working on the same array.
     */

    public static int maximalDrop(int[] a)
    {
        int c=0,d=1,l=a.length,maxDrop=0; //Initialize variables

        //Loop type do while will only run one time and run as long as you have not reached the end

        do{
            if(d<l-1 && a[c]<=a[d])          //If the value in place j is greater than the value in place i and j is greater than the limits of the array will move the index  j to i                       
            {
                c=d;                       //Moves the j index to i
                d++;
            }
            if(maxDrop<a[c]-a[d])                    //Checks maximum fall between two numbers and inserts it to maxDrop  
                maxDrop=a[c]-a[d];

            d++;                                                               
        }
        while(d<l);

        return maxDrop;                           //returns the maximum value drop
    }
    // The time complexity is 3+n = O(n).
    // The space complexity is O(1) - work on the same array.      

    /**
     * Returns if a sink is existing in the matrix.
     * "sink" - all the value on the row are 0 , and in the column all the value are 1 except the the same row that the value in the column in the same row is 0
     * @param mat The array.
     * @return If there is sink return the row ,or if there is no sink return -1
     *  
     * The time complexity is n+2n = O(n).
     * The space complexity is O(1) - work on the same array.
     */
    public static int isSink (int [][] mat)
    {
        int r = 0, c = 0 , sum1 = 0 ,sum2=0; ; //initialize variables
        while(r < mat.length && c <mat.length) //If I'm in the array
        {
            if(mat [r][c] == 0) //Check if mat place r,c is 0 move on to the next column otherwise check the following line 
                c++;
            else
                r++;
        }

        if(r == mat.length) //Checks if there is a row that has all values 1
            return -1;
        else          //Otherwise move the value r to c and check the line c (the value inside c is the line)
            c=r; 

        for(int i = 0;i<mat.length;i++){ //A loop that sums the values in the row and the values in the column 
            sum1+=mat[r][i]; 
            sum2+=mat[i][c];
        }  

        if(sum1!=0 || sum2!=mat.length-1 ) //If the condition is not met it is ignorant (A condition for a single row of all values 0 and the column all values 1 except the same row so its sum is mat.length-1)
            return -1;
        else
            return c;
    } 

    /**
     * Return the size of the stain containing this cell. If the cell is not part of a stain returned 0.
     * "stain" - It is a sequence of solid squares with a common side or a common vertex. 
     * The size of the stain is the number of squares that make up the stain. There may be some stains on the board.
     * @param mat The array.
     * @param x represents a row in the array.
     * @param y represents a row in the array.
     * @return The size of the stain containing this cell. If the cell is not part of a stain it will be returned 0
     */
    public static int size (boolean[][] mat, int x, int y)
    {
        int[][] sign = new int[mat.length][mat[0].length]; //Creates an array with the same boundaries as mat array and full of 0
        return size(mat,x,y,sign);
    }

    private static int size (boolean[][] mat, int x, int y,int[][] sign) //overloading
    {

        if (x<0 || y<0|| x>=mat.length || y>=mat[0].length) //End conditions for exiting matrix boundaries, and the values X and Y are positive
        {
            return 0;
        }
        if (!mat[x][y] || sign[x][y] == 1) //Checking if we were here or not at the place
        {
            sign[x][y] = 1;  //marker we were here
            return 0;
        }

        sign[x][y] = 1; //else if we didnt been here
        return 1 + size(mat,x,y-1,sign)
        + size(mat,x,y+1,sign) 
        + size(mat,x+1,y,sign) 
        + size(mat,x+1,y+1,sign)                  
        + size(mat,x+1,y-1,sign)
        + size(mat,x-1,y,sign) 
        + size(mat,x-1,y-1,sign)
        + size(mat,x-1,y+1,sign);
    }

    /**
     * Return true if the arrays contain the exact same organs.
     * But they may appear in a different order.
     * @param a The array to check.
     * @param b The array to check.
     * @return true if they constitute a permutation of each other 
     */
    public static boolean isPermutation (int [] a, int [] b){

        if (a.length == 0 && b.length == 0) return true; //If the arrays are necessarily empty they are the same
        if (a.length != b.length) return false; //If their length is different then they are necessarily different
        boolean[] sign = new boolean[a.length]; //Creates an array with the same boundaries as a array and full of false boolean value

        return isPermutation(a,b,0,0,sign);

    }

    private static boolean isPermutation (int [] a, int [] b, int i, int j,boolean sign[]){ //overloading
        if (i == a.length) 
            return true; //Checking if I reached the last organ in the array
        if (j == a.length) 
            return false; //Checking if I reached the last organ in the array
        if (a[i] == b[j] && sign[j] !=true){ //Checking if the number is the same and I was not here
            sign[j]= true; //To mark that I was in this index
            return isPermutation(a,b,i+1,0,sign);
        }else 
            return isPermutation(a,b,i,j+1,sign);

    }
}//end of class Ex14
