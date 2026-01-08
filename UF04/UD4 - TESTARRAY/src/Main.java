import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome!");

        int[] array = {1111, 1, 134, 123, 4, 5};
        System.out.println("Sorted by me: " + Arrays.toString(sort(array)));

        int[] array3 = {232,108,625,2,1001,2000,17508,-708};
        System.out.println("Sorted by me: " + Arrays.toString(sort(array3)));

        int[] array4 = new int[100];
        for (int i = 0; i < array4.length; i++) {
            array4[i] = (int) Math.ceil(Math.random()*100 - 50);
        }
        System.out.println("Without being sorted: " + Arrays.toString(array4) + "\nIt sums " + arraySum(array4));
        System.out.println("Sorted by me: " + Arrays.toString(sort(array4))  + "\nIt sums " + arraySum(array4));

        Arrays.sort(array4);


        int[] array2 = {1111, 1, 134, 123, 4, 5};
        System.out.println("Without being sorted: " + Arrays.toString(array2));
    }

    private static int[] sort(int[] array) {
        int minIndex;
        int placeholder;

        for (int start = 0; start < array.length; start++) {
            minIndex = start;

            for (int i = start; i < array.length; i++) {

                if (array[i] < array[minIndex]) {
                    minIndex = i;
                }

            }

            placeholder = array[start];
            array[start] = array[minIndex];
            array[minIndex] = placeholder;
        }

        return array;
    }

    public static int arraySum(int[] array) {
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        return sum;
    }
}