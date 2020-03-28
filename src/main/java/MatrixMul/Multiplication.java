package MatrixMul;


import Jama.Matrix;

public class Multiplication {
    public double[][] threadMult(double[][] m1, double[][] m2, int threadCount) {
        int rows = m1.length;
        int colums = m2[0].length;
        double[][] res = new double[rows][colums];

        int cellsForThread = (rows * colums) / threadCount;
        int f_ind = 0;
        MatrixThread[] threads = new MatrixThread[threadCount];
        for (int threadIndex = threadCount - 1; threadIndex >= 0; --threadIndex) {
            int l_ind = f_ind + cellsForThread;
            if (threadIndex == 0) {
                l_ind = rows * colums;
            }
            threads[threadIndex] = new MatrixThread(m1, m2, res, f_ind, l_ind);
            threads[threadIndex].start();
            f_ind = l_ind;
        }
        // Ожидание завершения потоков.
        return res;
    }
}
