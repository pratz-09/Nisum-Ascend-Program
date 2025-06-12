
import java.util.Arrays;

class SortThread extends Thread {
    int[] arr;
    int start, end;

    SortThread(int[] arr, int start, int end) {
        this.arr = arr; this.start = start; this.end = end;
    }

    public void run() {
        Arrays.sort(arr, start, end);
    }
}

public class Q26_MultiSort {
    public static void main(String[] args) throws InterruptedException {
        int[] arr = {9, 4, 1, 7, 6, 3, 2, 8};
        int mid = arr.length / 2;

        SortThread t1 = new SortThread(arr, 0, mid);
        SortThread t2 = new SortThread(arr, mid, arr.length);
        t1.start(); t2.start();
        t1.join(); t2.join();

        int[] result = new int[arr.length];
        System.arraycopy(arr, 0, result, 0, arr.length);
        Arrays.sort(result); // simple merge

        System.out.println(Arrays.toString(result));
    }
}
