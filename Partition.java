import java.io.*;
import java.util.*;
import java.lang.Math.*;
import java.util.concurrent.ThreadLocalRandom;

public class Partition {
	public static final int iter = 25000;
    public static final Random random = new Random();

    // KK algorithm implementation using a max-heap
    public static long KK(long [] arr) {
        int n = arr.length;
        MaxHeap heap = new MaxHeap(arr);
        for(int i = 0; i < n-1; i++) {
            long x = heap.pop();
            long y = heap.pop();
            heap.push(x-y);
        }
        return heap.pop();
    }

    // repeated random heuristic implementation
    public static long repeatedRandomS(long [] arr) {
        Solution optimal = new SSolution(arr);
        long optimalResidue = optimal.getResidue();
        for(int i = 0; i < iter; i++) {
            Solution tempSolution = new SSolution(arr);
            long tempResidue = tempSolution.getResidue();
            if(tempResidue < optimalResidue) {
                optimalResidue = tempResidue;
            }
        }
        return optimalResidue;
    }

    // (prepartitioned) repeated random heuristic implementation
    public static long repeatedRandomP(long [] arr) {
        Solution optimal = new PSolution(arr);
        long optimalResidue = optimal.getResidue();
        for(int i = 0; i < iter; i++) {
            Solution tempSolution = new PSolution(arr);
            long tempResidue = tempSolution.getResidue();
            if(tempResidue < optimalResidue) {
                optimalResidue = tempResidue;
            }
        }
        return optimalResidue;
    }

    // hill climbing heuristic implementation
    public static long hillClimbingS(long [] arr) {
        Solution optimal = new SSolution(arr);
        long optimalResidue = optimal.getResidue();
        for(int i = 0; i < iter; i++) {
            Solution tempSolution = optimal.randomNeighbor();
            long tempResidue = tempSolution.getResidue();
            if(tempResidue < optimalResidue) {
                optimalResidue = tempResidue;
                optimal = tempSolution;
            }
        }
        return optimalResidue;
    }

    // (prepartitioned) hill climbing heuristic implementation
    public static long hillClimbingP(long [] arr) {
        Solution optimal = new PSolution(arr);
        long optimalResidue = optimal.getResidue();
        for(int i = 0; i < iter; i++) {
            Solution tempSolution = optimal.randomNeighbor();
            long tempResidue = tempSolution.getResidue();
            if(tempResidue < optimalResidue) {
                optimalResidue = tempResidue;
                optimal = tempSolution;
            }
        }
        return optimalResidue;
    }

    // T function implementation
	private static double t(int i) {
		double num = Math.pow(10, 10);
		num *= Math.pow(0.8, i/300);
		return num;
	}

    // simulated annealing heuristic
    public static long simulatedAnnealingS(long [] arr) {
        Solution optimal = new SSolution(arr);
        long optimalResidue = optimal.getResidue();
        Solution current = optimal;
        long currentResidue = optimalResidue;
        for(int i = 0; i < iter; i++) {
            Solution tempSolution = current.randomNeighbor();
            long tempResidue = tempSolution.getResidue();
            if(tempResidue < currentResidue) {
                currentResidue = tempResidue;
                current = tempSolution;
            }
            else {
                double p = Math.exp(-(tempResidue - currentResidue)/t(i));
				if(p > random.nextDouble()) {
					current = tempSolution;
					currentResidue = tempResidue;
				}
            }
            if(currentResidue < optimalResidue) {
                optimalResidue = currentResidue;
                optimal = current;
            }
        }
        return optimalResidue;
    }

    // (prepartitioned) simulated annealing heuristic
    public static long simulatedAnnealingP(long [] arr) {
        Solution optimal = new PSolution(arr);
        long optimalResidue = optimal.getResidue();
        Solution current = optimal;
        long currentResidue = optimalResidue;
        for(int i = 0; i < iter; i++) {
            Solution tempSolution = current.randomNeighbor();
            long tempResidue = tempSolution.getResidue();
            if(tempResidue < currentResidue) {
                currentResidue = tempResidue;
                current = tempSolution;
            }
            else {
                double p = Math.exp(-(tempResidue - currentResidue)/t(i));
				if(p > random.nextDouble()) {
					current = tempSolution;
					currentResidue = tempResidue;
				}
            }
            if(currentResidue < optimalResidue) {
                optimalResidue = currentResidue;
                optimal = current;
            }
        }
        return optimalResidue;
    }

    // equivalent of makefile
    public static void main(String[] args) throws FileNotFoundException {
        long[] arr = new long[100];
        int flag = 0;
		int algorithm = 0;
		String inputfile = "";
        flag = Integer.parseInt(args[0]);
        algorithm = Integer.parseInt(args[1]);
        inputfile = args[2];
		Scanner console = new Scanner(new File(inputfile));
        
		for (int i = 0; i < 100; i++) {
			arr[i] = console.nextLong();
		}

        if(algorithm == 0) {
            System.out.println(KK(arr));
        }
        else if(algorithm == 1) {
            System.out.println(repeatedRandomS(arr));

        }
        else if (algorithm == 2) {
            System.out.println(hillClimbingS(arr));
        }
        else if (algorithm == 3) {
            System.out.println(simulatedAnnealingS(arr));
        }
        else if(algorithm == 11) {
            System.out.println(repeatedRandomP(arr));

        }
        else if (algorithm == 12) {
            System.out.println(hillClimbingP(arr));
        }
        else if (algorithm == 13) {
            System.out.println(simulatedAnnealingP(arr));
        }
    }
    
}
