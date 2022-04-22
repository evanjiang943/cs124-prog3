import java.io.*;
import java.util.*;
import java.lang.Math.*;
import java.util.concurrent.ThreadLocalRandom;

public class SSolution implements Solution {
    public int [] S;
    public long [] arr;
    public static final Random random = new Random();

    // post-first solution construction
    public SSolution(long [] arr, int [] S) {
        this.arr = arr;
        this.S = S;
    }

    // first solution construction
    public SSolution(long [] arr) {
        this.arr = arr;
        int len = arr.length;
        this.S = new int[len];
        for(int i = 0; i < len; i++) {
            double x = random.nextDouble();
            if(x > 0.5) {
                this.S[i] = 1;
            }
            else {
                this.S[i] = -1;
            }
        }
    }

    // get residue of a solution
    public long getResidue() {
        long residue = 0;
        for(int i = 0; i < this.arr.length; i++) {
            residue += this.S[i] * arr[i];
        }
        return Math.abs(residue);
    }

    // generate random neighbor for a solution
    public Solution randomNeighbor() {
        int [] newS = this.S.clone();
        int len = this.arr.length;
        int i = random.nextInt(len);
        newS[i] *= -1;
        
        double x = random.nextDouble();
        if(x > 0.5) {
            int j = i;
            while(j == i) {
                j = random.nextInt(len);
            }
            newS[j] *= -1;
        }
        SSolution res = new SSolution(arr, newS);
        return res;
    }    
}
