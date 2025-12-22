import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        int[] array = {1111,1,134,123,4,5};

        System.out.println(Arrays.toString(sort(array)));
    }

    private static int[] sort(int[] array) {
        int p = 0;
        int d = 0;
        int c = 0;
        int h = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                if (i != 0 && array[i + 1] < array[i - 1]) {
                    for (int j = i - 1; j < i; j++) {
                        if (array[j] > array[i - 1]) {
                            c = j;
                        } else {
                            break;
                        }
                    }
                    p = array[i + 1];
                    for (int j = c; j < i + 2; j++) {
                        h = array[j + 1];
                        array[j + 1] = array[j];
                        d = array[j + 2];
                        array[j + 2] = h;
                        System.out.println(Arrays.toString(array));
                    }
                    array[c] = p;
                    System.out.println(Arrays.toString(array));
                } else {
                    p = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = p;
                    System.out.println(Arrays.toString(array));
                }
            }
        }
        return array;
    }
}