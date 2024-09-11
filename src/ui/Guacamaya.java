package ui;

import java.util.Scanner;

public class Guacamaya {

    public static Scanner reader;
    public static double[] precios;
    public static int[] unidades;

    public static void main(String[] args) {

        inicializarGlobales();
        menu();
    }

    /**
     * Descripcion: Este metodo se encarga de iniciar las variables globales
     * pre: El Scanner reader debe estar declarado
     * pos: El Scanner reader queda inicializado
    */
    public static void inicializarGlobales() {

        reader = new Scanner(System.in);

    }

    /**
     * Descripcion: Este metodo se encarga de desplegar el menu al usuario y mostrar los mensajes de resultado para cada funcionalidad
     * pre: El Scanner reader debe estar inicializado
     * pre: El arreglo precios debe estar inicializado
    */
    public static void menu() {

        System.out.println("Bienvenido a Guacamaya!");

        establecerCantidadVendida();

        boolean salir = false;

        do {

            System.out.println("\nMenu Principal:");
            System.out.println("1. Solicitar precios ($) y cantidades de cada referencia de producto vendida en el dia");
            System.out.println("2. Calcular la cantidad total de unidades vendidas en el dia");
            System.out.println("3. Calcular el precio promedio de las referencias de producto vendidas en el dia");
            System.out.println("4. Calcular las ventas totales (dinero recaudado) durante el dia");
            System.out.println("5. Consultar el numero de referencias de productos que en el dia han superado un limite minimo de ventas");
            System.out.println("6. Salir");
            System.out.println("\nDigite la opcion a ejecutar");
            int opcion = reader.nextInt();

            switch (opcion) {
                case 1:
                    solicitarDatos();
                    break;
                case 2:
                    System.out.println("\nLa cantidad total de unidades vendidas en el dia fue de: "+calcularTotalUnidadesVendidas());
                    break;
                case 3:
                    System.out.println("\nEl precio promedio de las referencias de producto vendidas en el dia fue de: "+calcularPrecioPromedio());
                    break;
                case 4:
                    System.out.println("\nLas ventas totales (dinero recaudado) durante el dia fueron: "+calcularVentasTotales());
                    break;
                case 5:
                    System.out.println("\nDigite el limite minimo de ventas a analizar");
                    double limite = reader.nextDouble();
                    System.out.println("\nDe las "+precios.length+" referencias vendidas en el dia, "+consultarReferenciasSobreLimite(limite)+" superaron el limite minimo de ventas de "+limite);
                    break;
                case 6:
                    System.out.println("\nGracias por usar nuestros servicios!");
                    salir = true;
                    reader.close();
                    break;

                default:
                    break;
            }

        } while (!salir);

    }

    /**
     * Descripcion: Este metodo se encarga de preguntar al usuario el numero de referencias de producto diferentes 
     * vendidas en el dia e inicializa con ese valor los arreglos encargados de almacenar precios y cantidades
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados
     * pos: Los arreglos precios y unidades quedan inicializados
     */
    public static void establecerCantidadVendida() {

        System.out.println("\nDigite el numero de referencias de producto diferentes vendidas en el dia ");
        int referencias = reader.nextInt();

        precios = new double[referencias];
        unidades = new int[referencias];

    }
     /**
     * Descripcion: Este metodo se encarga de preguntar al usuario la cantidad y el precio de cada referencia para asignarlos en sus 
     * arreglos respectivos 
     * pre: El Scanner reader debe estar inicializado
     * pre: Los arreglos precios y unidades deben estar declarados e inicializados con sus respectivos espacios
     * pos: Los arreglos precios y unidades quedan con los datos digitados por el usuario
     */
    public static void solicitarDatos(){
        for(int i=0;i<precios.length;i++){
        System.out.println("Digite la cantidad de ventas de la referencia:  ["+(i+1)+"]");
        unidades[i]=reader.nextInt();
        System.out.println("Digite el precio de la referencia:  ["+(i+1)+"]");
        System.out.print("$");
        precios[i]=reader.nextDouble();
        }
     
    }
    /**
     * Descripcion: Este metodo se encarga de recorrer todo el arreglo de unidades para sumar cada valor interno 
     * en cada esapcio y agrergar esta suma a un contador para luego retornar
     * pre: Los arreglos precios y unidades deben estar inicializados con sus respectivos espacios y datos
     * @return int sumaUnidades guarda la suma de unidades de cada posicion del arreglo unidades
     */
    public static int calcularTotalUnidadesVendidas(){
        int sumaUnidades = 0;
        for (int i = 0; i < unidades.length; i++) {
            sumaUnidades += unidades[i];
        }

        return sumaUnidades;

    }
    /**
     * Descripcion: Este metodo se encarga de recorrer todo el arreglo de precios y realiza una suma de todos sus datos para luego
     * hacer una divion entre esta suma y la longitud del arreglo para asi obtener el promedio de precios
     * pre: Los arreglos precios y unidades deben estar inicializados con sus respectivos espacios y datos
     * @return double promedioPrecio guarda la division de la suma de los precios entre la longitud del arreglo
     */
    public static double calcularPrecioPromedio(){
        int suma = 0, promedioPrecio = 0;
        for(int i=0;i<precios.length;i++){
            suma+=precios[i];
        }
        promedioPrecio= suma/precios.length;
        return promedioPrecio;

    }
    /**
     * Descripcion: Este metodo se encarga de calcular las ventas totales de cada una de las referencias y sumarlas para el total
     * pre: Los arreglos precios y unidades deben estar inicializados con sus respectivos espacios y datos
     * @return double sumaVentasTotales en esta variable se almacena todas ventas de cada referencia
     */
    public static double calcularVentasTotales(){
        double sumaVentasTotales = 0;
        for(int i=0;i<precios.length;i++){
            sumaVentasTotales+=precios[i]*unidades[i];
        }

        return sumaVentasTotales;

    }
/**
 *Descripcion: este metodo identifica si las ventas de una referencias estan por encima de un limite digitado por el usuario
 pre: Los arreglos precios y unidades deben estar inicializados con sus respectivos espacios y datos
 * @param double limite esta variable es un precio minimo digitado por el usuario 
 * @return int upLimite esta variable devuelve el numero de referencias que tienen sus ventas por encima del limite
 */
    public static int consultarReferenciasSobreLimite(double limite){
        int upLimite = 0;

        for(int i=0;i<precios.length;i++){
            if((precios[i]*unidades[i])>limite){
                upLimite++;
            }
        }
        return upLimite;

    }

}
