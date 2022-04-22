import java.io.*;
import java.util.*;
import java.lang.Math.*;
import java.util.concurrent.ThreadLocalRandom;

public class PSolution implements Solution {
    public int [] P;
    public long [] arr;
    public static final Random random = new Random();

    // post-first solution construction
    public PSolution(long [] arr, int [] P) {
        this.arr = arr;
        this.P = P;
    }
    
    // first solution construction
    public PSolution(long [] arr) {
        this.arr = arr;
        int len = arr.length;
        this.P = new int[len];
        for(int i = 0; i < len; i++) {
            this.P[i] = random.nextInt(len);
        }
    }

    // same KK implementation as Partition (used for random neighbor method)
    public static long KK(long [] arr) {
        int n = arr.length;
        MaxHeap heap = new MaxHeap(arr);
        for(int i = 0; i < n-1; i++) {
            long x = heap.pop();
            long y = heap.pop();
            heap.push(x-y);
        }
        return heap.peek();
    }

    // get residue of a solution
    public long getResidue() {
        long [] arr2 = new long[this.arr.length];
        for (int i = 0; i < this.arr.length; i++) {
            arr2[this.P[i]] += arr[i];
        }
        return KK(arr2);
    }

    // generate random neighbor for a solution
    public Solution randomNeighbor() {
        int len = this.arr.length;
        int [] arr2 = this.P.clone();
        int i = random.nextInt(len);
        int j = this.P[i];
		while (j == this.P[i]) {
			j = random.nextInt(len);
		}
		arr2[i] = j;
        PSolution neighbor = new PSolution(this.arr, arr2);
		return neighbor;
    }
}
