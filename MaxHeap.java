public class MaxHeap {
	private long [] arr;
	private int size;
    private int numElements;

    // constructors
	public MaxHeap(int size) {
		this.size = size;
		this.arr = new long[size];
		this.numElements = 0;
	}

	public MaxHeap(long[] arr) {
		this.arr = arr.clone();
		this.numElements = this.arr.length;
		this.size = this.arr.length;

		for(int i = size/2; i >= 0; i--) {
			this.maxHeapify(i);
		}
	}

    // access-related operations
	private static int parent(int i) {
		return (i - 1) / 2;
	}

    private static int left(int i) {
		return 2 * i + 1;
	}

	private static int right(int i) {
		return 2 * i + 2;
	}

    // size checking
	public boolean isFull() {
		return numElements == size;
	}

	public boolean isEmpty() {
		return numElements == 0;
	}

    // heap operations
    private static void swap(long[] arr, int i, int j) {
		long temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public long peek() {
		return arr[0];
	}

	public long pop() {
		long temp = arr[0];
		arr[0] = arr[--numElements];
		maxHeapify(0);
		return temp;
	}

	public boolean push(long i) {
		if (this.isFull())
			return false;
		int k = numElements;
		arr[numElements++] = i;
		while (k > 0 && arr[k] > arr[parent(k)]) {
			swap(arr, k, parent(k));
			k = parent(k);
		}
		return true;
	}

	private void maxHeapify(int i) {
		int max = i;
		if (left(i) < numElements && arr[left(i)] > arr[i])
			max = left(i);
		if (right(i) < numElements && arr[right(i)] > arr[max])
			max = right(i);
		if (max != i) {
			swap(arr, i, max);
			maxHeapify(max);
		}
	}
}