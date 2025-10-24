import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {

        /*
            Necesitamos crear un programa para almacenar las notas de 4 alumnos (llamados “Alumno
            1”, “Alumno 2”, etc.) y 5 asignaturas. El usuario introducirá las notas por teclado y luego el
            programa mostrará la nota mínima, máxima y media de cada alumno.
         */

        System.out.println("\u001B[36mEjercicio 4\u001B[0m\n");
        Scanner sc = new Scanner(System.in);

        double[][] alumnos;
        alumnos = new double[4][5];

        for (int i = 0; i < alumnos.length; i++) {
            for (int j = 0; j < alumnos[i].length; j++) {
                System.out.print("Introduce la nota de \u001B[33mAlumno " + (i+1) + "\u001B[0m en la asignatura " + asignatura(j) + ": ");
                alumnos[i][j] = sc.nextDouble();
            }
            System.out.println();
        }

        for (int i = 0; i < alumnos.length; i++) {
            System.out.println("\nPara el \u001B[33mAlumno " + (i+1) + "\u001B[0m: ");
            System.out.println("La mejor nota es " + maxNota(alumnos[i]));
            System.out.println("La peor nota es " + minNota(alumnos[i]));
            System.out.println("La nota media es " + mediaNota(alumnos[i]));
        }
    }
    public static String asignatura(int n) {
        String devuelta;
        switch (n) {
            case 0:
                devuelta = "Matematicas";
                break;
            case 1:
                devuelta = "Ingles";
                break;
            case 2:
                devuelta = "Fisica";
                break;
            case 3:
                devuelta = "Biologia";
                break;
            case 4:
                devuelta = "Lenguaje";
                break;
            default:
                devuelta = "";
                System.out.println("\u001B[31mError. La funcion no puede procesar el valor (\u001B[37m" + n + "\u001B[31m)\u001B[0m");
                return devuelta;
        }
        return devuelta;
    }
    public static double minNota(double[] alumno) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < alumno.length; i++) {
            if (alumno[i] < min) {
                min = alumno[i];
            }
        }
        return min;
    }
    public static double maxNota(double[] alumno) {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < alumno.length; i++) {
            if (alumno[i] > max) {
                max = alumno[i];
            }
        }
        return max;
    }
    public static double mediaNota(double[] alumno) {
        double media = 0;
        for (int i = 0; i < alumno.length; i++) {
            media += alumno[i];
        }
        media = media / alumno.length;
        return media;
    }
}
