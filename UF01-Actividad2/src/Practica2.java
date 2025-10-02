import java.util.Scanner;

public class Practica2 {
    public static void main(String[] args) {
        //Ejercicio 1

        System.out.println("Ejercicio 1");//Damos el nombre al ejercicio
        Scanner sc = new Scanner(System.in);//Definimos la funcion Scanner para todo el codigo

        System.out.println("Por favor, escribe tu edad: ");//Introducimons una peticion por comando
        int edad = sc.nextInt();//Se recoge la respuesta

        if (edad >= 18) {
            System.out.println("Eres mayor de edad");//Se determina por un If si la condicioon es correcta
        }
        else {
            System.out.println("No eres mayor de edad"); //Si no es mayor de edad, definimos otra respuesta para no dejar en blanco
        }

        //Ejercicio 2
        System.out.println("\nEjercicio 2");//Introducimos el segundo ejercicio con una \n para salto de linea
        sc = new Scanner(System.in);//Redefinimos el escaner cuando se necesite

        System.out.println("Por favor, escribe tu edad: ");//Introducimons una peticion por comando
        edad = sc.nextInt();//Se recoge la respuesta, reusando la variable edad

        if (edad >= 18) {
            System.out.println("Eres mayor de edad");//Se sigue la misma logica de antes
        }
        else {
            System.out.println("Eres menor de edad");//Si no lo es, solo puede ser la otra alternativa, else
        }

        //Ejercicio 3

        System.out.println("\nEjercicio 3");//Se define ejercicio 3 con un salto de linea como formatoo

        for (int i = 1; i<21;i++) {
            if (i==1){
                System.out.print(i);//Definimos el primer numero sin la coma para poder poner el resto con coma de buena manera
            }
            else {
                System.out.print(", " + i); //Evaluamos imprimir una variable creada en un bucle i unas 20 veces para los num naturales
            }
        }

        //Ejercicio 4

        System.out.println("\n\nEjercicio 4");//Se declara el ejercicio de nuevo, doble linea requerida

        //Definimos primero un contador, que vaya de 2 en 2. Por eso, tendremos que contar de dos desde 2.
        for (int i = 2; i<201;i+=2) {
            if(i%2==0) {//No se necesita el If ya que sabemos de antemano que todos van a ser pares, pero para comprobarlo, nos sirve
                if (i==2){
                    System.out.print(i);//El dos, el primer par, es marcado sin comas para buen formato
                }
                else {
                    System.out.print(", " + i);//Se imprimen el resto, solo los numeros pares
                }
            }
        }

        //Ejercicio 5

        System.out.println("\n\nEjercicio 5");//Se llama al ejercicio, doble linea requerida

        //Para poder contarlos de 1 en 1, podemos usar el mismo código que antes
        for (int i = 1; i<201;i++) {
            if(i%2==0) {//En este caso, si necesitamos el If
                if (i==2){
                    System.out.print(i);//El dos, el primer par, es marcado sin comas para buen formato
                }
                else {
                    System.out.print(", " + i);//Se imprimen solo los numeros pares, de todas formas, los impares no
                }
            }
        }

        //Ejercicio 6

        System.out.println("\n\nEjercicio 6");//Llamando al ejercicio, doble linea requerida
        sc = new Scanner(System.in);//Redefinimos el escaner cuando se necesite

        System.out.println("Por favor, escribe un numero N hasta el que contar: ");//El dato es pedido
        int n = sc.nextInt();//Se define un entero N al que vamos a contar

        for (int i = 1; i<=n; i++) {//Cuenta de 1 en 1 hasta un entero N
            if (i==1) {
                System.out.print(i);//Se imprime el 1 sin coma
            }
            else {
                System.out.print(", " + i);//Se imprimen el resto de numeros con coma
            }
        }

        //Ejercicio 7

        System.out.println("\n\nEjercicio 7");//Se formatea el ejercicio 7, doble linea requerida
        sc = new Scanner(System.in);//Redefinimos el escaner cuando se necesite

        System.out.println("Por favor, escribe una califcacion: ");//Se pregunta el dato requerido
        double nota = sc.nextDouble();//Se toma la nota, como un numero racional/real, ya que pueden tener decimales

        if (nota < 0 || nota > 10) {
            System.err.println("La nota debe ser entre 0 y 10");//Definimos un error si no esta en el rango requerido
        }

        if (nota >= 0 &&  nota < 3) {
            System.out.println("La nota es un muy Deficiente");
        }
        if (nota >= 3 && nota < 5) {
            System.out.println("La nota es un Insuficiente");
        }
        if (nota >= 5 && nota < 6) {
            System.out.println("La nota es un Bien");
        }
        if (nota >= 6 && nota < 9) {
            System.out.println("La nota es un Notable");
        }
        if (nota >= 9 && nota <= 10) {
            System.out.println("La nota es un Sobresaliente");
        }
        //Este ejercicio define todo con ifs y no elses, ya que va a funcionar de igual manera, y queda todo mas limpio, aunque se podria usar un switch

        //Ejercicio 8

        System.out.println("\nEjercicio 8");//Se llama el ejercicio
        sc = new Scanner(System.in);//Redefinimos el escaner cuando se necesite

        System.out.println("Escribe el numero que se quiera calcular factorial");//Se pide el dato
        n =  sc.nextInt();//Se recoje el int pedido, usando n definido antes

        if (n < 0) {
            System.err.println("El factorial no puede ser de un numero negativp");//Se da un error para numeros negativos
        }
        else {//Solo si es positivo
            int resultado = 1;//Definido, el factorial de cualquier numero empieza por el 0
            for (int i = 1; i<=n; i++) {
                resultado *= i;
                //Vemos que para 0 no pasa por el for, y para otros numeros multiplica hasta el factorial
            }
            System.out.println("El factorial es " + n + "! = " + resultado);
        }

        //Ejercicio 9

        System.out.println("\nEjercicio 9");//Se define el ejercicio
        sc = new Scanner(System.in);//Redefinimos el escaner cuando se necesite

        System.out.println("Introduce una fecha XX:XX:XX, de horas:minutos:segundos, empieza solo por las horas: ");//
        int hora = sc.nextInt();//Se lee el numero pedido
        System.out.print(":");//Se crean dos puntos para permitir los minutos
        int minutos = sc.nextInt();//Se lee el siguiente numero
        System.out.print(":");//Se ponen dos puntos para separarlos
        int segundos = sc.nextInt();//Se lee el ultimo dato

        //Creamos un programa para que este puesto en la version correcta de horas y de minutos
        if (segundos >= 60) {
            segundos -= 60;
            minutos += 1;
        }
        if (minutos >= 60) {
            minutos -= 60;
            hora += 1;
        }
        if (hora >= 24) {
            hora -= 24;
        }

        //Se suma ahora 1 segundo
        segundos += 1;

        //Se verifica ahora que todo este adaptado
        if (segundos >= 60) {
            segundos -= 60;
            minutos += 1;
        }
        if (minutos >= 60) {
            minutos -= 60;
            hora += 1;
        }
        if (hora >= 24) {
            hora -= 24;
        }

        //Se imprime la hora completa, por ultimo, per veamos que tengan un zero a la izquierda
        String horaFinal="";
        if (hora / 10 == 0) {
            horaFinal= "0" + hora + ":";
        }
        else {
            horaFinal= hora + ":";
        }
        if (minutos / 10 == 0) {
            horaFinal+= "0" + minutos + ":";
        }
        else {
            horaFinal+= minutos + ":";
        }
        if (segundos / 10 == 0) {
            horaFinal+= "0" + segundos;
        }
        else {
            horaFinal+= segundos;
        }
        System.out.println("La hora es " + horaFinal);

        //Ejercicio 10

        System.out.println("\nEjercicio 10");//Se llama al ejercicio
        sc = new Scanner(System.in);//Se limpia la consola

        int[] misResultados = new int[10];//Se define una matriz de 10 valores y una columna
        for (int i = 0; i<10; i++) {
            System.out.println("Introduce un numero no nulo hasta 10, " + (10-i) + " restantes: ");
            misResultados[i] = sc.nextInt();
            while (misResultados[i] == 0) {
                System.out.println("Numero nulo, repite este numero: ");
                misResultados[i] = sc.nextInt();
            }
        }
        //Se definen las variables a contar
        int negativos = 0;
        for (int i = 0; i<10; i++) {
            if (misResultados[i] < 0) {
                negativos++;
            }
        }
        //Se mantiene una clasificacion de cuantos son negativos para su longitud

        System.out.println("En los numeros dados hay " + negativos + " numeros negativos");//Se imprime

        //Ejercicio 11

        System.out.println("\nEjercicio 11");//Se llama al ejercicio
        sc = new Scanner(System.in);//Se limpia el scanner

        misResultados = new int[10];//Se define una matriz de 10 valores y una columna
        for (int i = 0; i<10; i++) {
            System.out.println("Introduce un numero no nulo hasta 10, " + (10-i) + " restantes: ");
            misResultados[i] = sc.nextInt();
            while (misResultados[i] == 0) {
                System.out.println("Numero nulo, repite este numero: ");
                misResultados[i] = sc.nextInt();
            }
        }
        //Se definen las variables a contar
        negativos = 0;//Los negativos soon reseteados a 0
        int positivos = 0;
        for (int i = 0; i<10; i++) {
            if (misResultados[i] < 0) {
                negativos++;
            }
            if (misResultados[i] > 0) {
                positivos++;
            }
        }
        //Se mantiene una clasificacion de cuantos son positivos y cuantos son negativos para su longitud

        System.out.println("En los numeros dados hay " + negativos + " numeros negativos y " + positivos + " numeros positivos");//Se imprime

        //Ejercicio 12

        System.out.println("\nEjercicio 12");//Se llama el ejercicio
        sc = new Scanner(System.in);//Se Limpia la consola

        int valor;//Se define una matriz de un valor
        positivos = 0;//Se resetean positivos
        negativos = 0;//Se resetean negativos
        while (true) {
            System.out.println("Introduce un numero hasta que uno sea nulo: ");
            valor = sc.nextInt();
            if (valor == 0) {
                break;
            }
            if (valor > 0) {
                positivos++;
            }
            if (valor < 0) {
                negativos++;
            }
        }
        if (negativos == 0) {
            System.out.println("Hay un total de " + positivos + " numeros positivos, y no hay negativos");
        }
        else {
            System.out.println("Hay un total de " + positivos + " positivos y " + negativos + " negativos");
        }
        //Se imprimen basado en si hay negativos o no

        //Ejercicio 13

        System.out.println("\nEjercicio 13");//Llamas al Ejercicio 13
        sc = new Scanner(System.in);//Se limpia el Scanner

        int suma = 0;
        int multiplicacion = 1;
        //Definiendo las dos variables a calcular

        for  (int i = 1; i<=10; i++) {
            //Los numeros naturales empiezan en el 0
            suma += i;
            multiplicacion *= i;
        }

        //Se imprime el resultado
        System.out.println("La suma es " + suma + " y la multiplicacion es " + multiplicacion + " de los 10 primeros numeros naturales");

        //Ejercicio 14

        System.out.println("\nEjercicio 14");//Se llama el Ejercicio
        sc = new Scanner(System.in);//Se limpia el Scanner

        System.out.println("Introduce tu nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce tus horas trabajadas: ");
        double horasTrabajadas = sc.nextDouble();
        System.out.println("Introduce tu tarifa por hora: ");
        double tarifaNormal = sc.nextDouble();
        double salarioBruto = 0;
        String tasas = "";
        double salarioNeto = 0;
        //Se definen las horas, las tasas y el Salario que se va a obtener

        //Se hace el calculo del salario
        if (horasTrabajadas <= 35) {
            salarioBruto = tarifaNormal*horasTrabajadas;
        }
        if (35 < horasTrabajadas) {
            salarioBruto = (tarifaNormal*35) + (tarifaNormal*(horasTrabajadas-35)*1.5);
        }

        //Ahora, se calcula cual es la tasa de impuestos
        if (salarioBruto <= 500) {
            salarioNeto = salarioBruto;
            tasas = "cotiza por debajo de 500 euros, por lo que no hay impuestos";
        }
        if (salarioBruto > 500 &&  salarioBruto <= 900) {
            salarioNeto = 500 +  ((salarioBruto - 500)*0.75);
            tasas = "cotiza entre 500 y 900 euros, por lo que tras los primeros 500, hay 25% impuestos";
        }
        if (salarioBruto > 900) {
            salarioNeto = 500 +  (400*0.75) + ((salarioBruto - 900)*0.55);
            tasas = "cotiza a mas de 900 euros, por lo que tras los primeros 500, \nhay 25% impuestos en los siguientes" +
                    " 400 euros, y cualquier tasa mayor es a 45% de impuestos";
        }

        //Se evalua la salida
        System.out.println("El salario de " + nombre + " es de un valor bruto de " + salarioBruto + " euros y tras unas " +
                "\ntasas por las que " + tasas + ", \ntenemos que el salario neto sale a " + salarioNeto + " euros");

        sc.close();
        //Se cierra el escaner para que no salgan errores
    }
}