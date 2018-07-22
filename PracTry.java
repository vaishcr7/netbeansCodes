package practry;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class PracTry {
    static class Reader {

        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') {
                    break;
                }
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg) {
                return -ret;
            }
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg) {
                return -ret;
            }
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg) {
                c = read();
            }

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg) {
                return -ret;
            }
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) {
                buffer[0] = -1;
            }
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) {
                fillBuffer();
            }
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null) {
                return;
            }
            din.close();
        }
    }

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
              //  System.out.println("sum new index= "+index);
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
		// Add current element to BIT
		BIT=updateBIT(BIT, maxElement, arr[i], 1);
	}

	return invcount;
}

// Driver program
public static void main(String []args) throws IOException
{
     Reader sc=new Reader();
        int t=sc.nextInt();
        while(t-->0)
        {
         int n=sc.nextInt();
         int []ar=new int[n];
         for (int i = 0; i < n; i++) {
                ar[i]=sc.nextInt();
         }
         System.out.println(getInvCount(ar,n));
        }
}
}
