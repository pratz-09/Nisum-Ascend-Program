
class MatrixMultiplier extends Thread {
    int[][] a, b, result;
    int row;

    MatrixMultiplier(int[][] a, int[][] b, int[][] result, int row) {
        this.a = a; this.b = b; this.result = result; this.row = row;
    }

    public void run() {
        for (int j = 0; j < b[0].length; j++) {
            result[row][j] = 0;
            for (int k = 0; k < b.length; k++) {
                result[row][j] += a[row][k] * b[k][j];
            }
        }
    }
}

public class Q27_MatrixMultiplication {
    public static void main(String[] args) throws InterruptedException {
        int[][] a = {{1, 2}, {3, 4}};
        int[][] b = {{5, 6}, {7, 8}};
        int[][] result = new int[2][2];

        Thread[] threads = new Thread[2];
        for (int i = 0; i < 2; i++) {
            threads[i] = new MatrixMultiplier(a, b, result, i);
            threads[i].start();
        }
        for (Thread t : threads) t.join();

        for (int[] row : result)
            System.out.println(java.util.Arrays.toString(row));
    }
}
