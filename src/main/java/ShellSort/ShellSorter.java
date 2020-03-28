package ShellSort;

public class ShellSorter {
    float[] array;
    int thread_count;

    public ShellSorter (float[] array, int thr_count){
        this.array = array;
        this.thread_count = thr_count;
    }

    private float[] merge(float[][] arr){
        int curr = 0;
        for(int i = 0; i < arr.length; i++){
            for (float element: arr[i]) {
                array[curr] = element;
                curr++;
            }
        }
        ShellThread thr = new ShellThread(array);
        thr.start();
        return array;
    }

    public void sort() {
        float[][] multiArray = new float[thread_count][];
        for(int i = 0; i < thread_count; i++){
            if(i < thread_count - 1)
            multiArray[i] = new float[array.length/thread_count];
            else multiArray[i] = new float[(array.length/thread_count)+(array.length%thread_count)];
        }
        for(int i = 0; i < array.length; i++){
            if(i/(multiArray[0].length) < thread_count - 1) {
                multiArray[i / (multiArray[0].length)][i % (multiArray[0].length)] = array[i];
            }
            else {
                multiArray[thread_count - 1][(((array.length/thread_count)+(array.length%thread_count)) - array.length%i)] = array[i];
            }
        }
        ShellThread[] threads = new ShellThread[thread_count];
        for(int i = 0; i < thread_count; i++){
            threads[i] = new ShellThread(multiArray[i]);
            threads[i].start();
        }
        try{
            for(int i = 0; i < thread_count; i++){
                threads[i].join();
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        array = merge(multiArray);
    }

}
