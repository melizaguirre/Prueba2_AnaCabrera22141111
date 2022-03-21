
import java.io.IOException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BAC
 */
public class main {

     public static void main(String[] args) throws IOException{
          ITunes tunes = new ITunes();
          Scanner leer = new Scanner(System.in);
     
        int opcion = 0;
        do {
            System.out.println("\n Menu \n----------");
            System.out.println("1- Agregar Cancion");
            System.out.println("2- Reviews de canciones");
            System.out.println("3- Descargar Cancion");
            System.out.println("4- Canciones");
            System.out.println("5- Informacion de una cancion");
            System.out.println("6- Salir");


            try {
                opcion = leer.nextInt();
             
                switch (opcion) {
                    case 1:
                        
                        System.out.println("Ingrese el nombre de la cancion");
                        String nombre = leer.nextLine();
                        System.out.println("Ingrese el nombre del cantante");
                        String cantante = leer.nextLine();
                        System.out.println("Ingrese el precio de la cancion");
                        double precio = leer.nextDouble();
                        tunes.addSong(nombre, cantante, precio);
                        break;
                    case 2:
                        System.out.println("Ingrese el codigo de la cancion");
                        int code = leer.nextInt();
                        System.out.println("Ingrese la cntidad de estrellas que asignara a la cancion");
                        int stars = leer.nextInt();
                        tunes.reviewsSong(code, stars);
                        break;
                    case 3:   
                        System.out.println("Ingrese el codigo de la cancion");
                        int codSong = leer.nextInt();
                        System.out.println("Nombre del cliente");
                        String cliente =leer.nextLine();
                        tunes.downloadSong(codSong, cliente);
                        break;
                    case 4:
                        System.out.println("Ingrese el nombre de la cancion");
                         = leer.nextInt();
                        tunes.songs(canciones);
                        break;
                    case 5:
                        System.out.println("Ingrese el codigo de la cancion de la que desea informacion");
                        codSong = leer.nextInt();
                        tunes.infoSong(codSong);
                        break;
                   
                    case 6:
                        System.exit(0);
                        break;
                }
            } catch (IOException e) {

            }
        } while (true);
     
     }
}
