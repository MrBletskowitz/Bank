package MatrixMul;

import Jama.Matrix;

public class MatrixThread extends Thread {
    double[][] m1;
    double[][] m2;
    double[][] result;
    int f_ind;
    int l_ind;
    int s_len;

    MatrixThread(double[][] m1, double[][] m2, double[][] result, int f_ind, int l_ind) {
        this.m1 = m1;
        this.m2 = m2;
        this.result = result;
        this.f_ind = f_ind;
        this.l_ind = l_ind;
        this.s_len = m2.length;
    }

    private void cellValue(final int row, final int col)
    {
        double sum = 0;
        for (int i = 0; i < s_len; ++i)
            sum += m1[row][i] * m2[i][col];
        result[row][col]= sum;
    }

    @Override
    public void run()
    {
        final int colCount = m2[0].length;  // Число столбцов результирующей матрицы.
        for (int index = f_ind; index < l_ind; ++index)
            cellValue(index / colCount, index % colCount);
    }

}
