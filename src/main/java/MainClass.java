import Bank.ClientGenerator;
import Bank.Money;
import Bank.Operationist;
import Jama.Matrix;
import MatrixMul.Multiplication;
import ShellSort.ShellSorter;

import java.util.Random;

public class MainClass {
    public static void main(String[] args) {
        /*
        Random random = new Random();
        double[][] mat1 = new double[500][500];
        for(int i = 0; i < 500; i++){
            for(int j = 0; j < 500; j++){
                mat1[i][j] = random.nextDouble();
            }
        }
        double[][] mat2 = new double[500][500];
        for(int i = 0; i < 500; i++){
            for(int j = 0; j < 500; j++){
                mat2[i][j] = random.nextDouble();
            }
        }

        Matrix m1 = new Matrix(mat1);
        Matrix m2 = new Matrix(mat2);
        long start = System.nanoTime();
        Multiplication multiplication = new Multiplication();
        double[][] res = multiplication.threadMult(mat1, mat2, Runtime.getRuntime().availableProcessors());
        long end = System.nanoTime();
        System.out.println("multithread is " + (end-start));
        for(int i = 0; i < res.length; i++){
            for (int j = 0; j < res[0].length; j++){
                //System.out.println(res[i][j]);
            }
        }

        System.out.println("++++++++++++++++++++++++++++++++++");
        start = System.nanoTime();
        Matrix m33 = m1.times(m2);
        end = System.nanoTime();
        System.out.println("one thread is " + (end-start));
        for(int i = 0; i < m33.getRowDimension(); i++){
            for (int j = 0; j < m33.getColumnDimension(); j++){
                //System.out.println(m33.get(i, j));
            }
        }


        float[] array = new float[123456];
        for(int i = 0; i < 123456; i++){
            array[i] = random.nextFloat();
        }
        float[] forOneThread = array.clone();
        ShellSorter sorter = new ShellSorter(array, Runtime.getRuntime().availableProcessors());
        sorter.sort();


         */

        Money money = new Money(1000);
        Operationist [] operationists = new Operationist[4];
        for(int i  = 0; i < 4; i++){
            operationists[i] = new Operationist(i+1);
        }
        ClientGenerator generator = new ClientGenerator(operationists, 500);
        generator.start();

    }
}
