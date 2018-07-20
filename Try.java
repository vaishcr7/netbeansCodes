package pkgtry;

public class Try {

  // Returns sum of arr[0..index]. This function assumes
// that the array is preprocessed and partial sums of
// array elements are stored in BITree[].
public static int getSum(int BITree[], int index)
{
	int sum = 0; // Initialize result

	// Traverse ancestors of BITree[index]
	while (index > 0)
	{
		// Add current element of BITree to sum 
		sum += BITree[index];

		// Move index to parent node in getSum View
		index -= index & (-index);
	}
	return sum;
}

// Updates a node in Binary Index Tree (BITree) at given index
// in BITree. The given value 'val' is added to BITree[i] and
// all of its ancestors in tree.
public static int[] updateBIT(int BITree[], int n, int index, int val)
{
	// Traverse all ancestors and add 'val'
	while (index <= n)
	{
	// Add 'val' to current node of BI Tree
	BITree[index] += val;

	// Update index to that of parent in update View
	index += index & (-index);
            System.out.println("new index= "+index);
	}
        return BITree;
}

// Returns inversion count arr[0..n-1]
public static int getInvCount(int arr[], int n)
{
	int invcount = 0; // Initialize result

	// Find maximum element in arr[]
	int maxElement = 0;
	for (int i=0; i<n; i++)
		if (maxElement < arr[i])
			maxElement = arr[i];

	// Create a BIT with size equal to maxElement+1 (Extra
	// one is used so that elements can be directly be
	// used as index)
	int []BIT=new int[maxElement+1];
	for (int i=1; i<=maxElement; i++)
		BIT[i] = 0;

	// Traverse all elements from right.
	for (int i=n-1; i>=0; i--)
	{
		// Get count of elements smaller than arr[i]
		invcount += getSum(BIT, arr[i]-1);
                System.out.println("invcount for "+arr[i]+" is "+invcount);
		// Add current element to BIT
		BIT=updateBIT(BIT, maxElement, arr[i], 1);
                for (int j = 0; j < BIT.length; j++) {
                    System.out.print(BIT[j]+" ");
            }
                System.out.println("");
	}

	return invcount;
}

// Driver program
public static void main(String []args)
{
	int arr[] = {2,3,8,6,5,1};
	int n = arr.length;
	System.out.println("Number of inversions are : "+getInvCount(arr,n));
}
}
