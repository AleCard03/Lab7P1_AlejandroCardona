/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab7p1_alejandrocardona;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author jets
 */
public class Lab7P1_AlejandroCardona {
    static Scanner read = new Scanner(System.in);
    static Random rng = new Random();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean continuar = true;
        while ( continuar ){
            System.out.println("Ingrese una opcion");
            System.out.println("1. Ejercicio Práctico 1 - She shoot, She score");
            System.out.println("2. Ejercicio Práctico 2 - Pierda, papel o...");
            System.out.println("3. Salir");
            int opcion = read.nextInt();
            switch (opcion){
                case 1: {
                    int rows, columns, disparos;
                    do{
                        System.out.println("Ingrese filas (rows)");
                        rows = read.nextInt();
                        System.out.println("Ingrese columnas (columns)");
                        columns = read.nextInt();
                    } while ( rows * columns >= 88||(rows<2 || columns< 2));
                    do{
                        System.out.println("Ingrese la cantidad de disparos");
                        disparos = read.nextInt();
                    }while(disparos<=0||disparos>rows*columns/2);
                    int disparos1= disparos;
                    int disparos2 = disparos;
                    int [][] matriz = genmatriz(rows,columns);
                    int acum1 = 0;
                    int acum2 = 0;
                    System.out.println("---Tablero para Jugar---");
                    print(matriz);
                    do{
                        System.out.println("Elige que numero disparar Jugador 1");
                        int disparado1 = read.nextInt();
                        acum1+=sumaacum(matriz,disparado1);
                        System.out.println(acum1+"<-acum1");
                        matriz = shoot1(matriz,disparado1);
                        print(matriz);
                        System.out.println("Le quedan: "+(disparos-1)+" balas");
                        System.out.println("Elige que numero disparar Jugador 2");
                        int disparado2 = read.nextInt();
                        acum2+=sumaacum(matriz,disparado2);
                        System.out.println(acum2+"<-acum2");
                        matriz = shoot2(matriz,disparado2);
                        print(matriz);
                        System.out.println("Le quedan: "+(disparos-1)+" balas");
                        disparos--;
                    }while (disparos > 0);
                    
                    if ( acum1 > acum2){
                        System.out.println("Jugador 1 gana con: "+acum1);
                        System.out.println("Jugador 2 pierde con: "+acum2);
                    }
                    else if ( acum1<acum2){
                        System.out.println("Jugador 2 gana con: "+acum2);
                        System.out.println("Jugador 1 pierde con: "+acum1);
                    }
                    else{
                        System.out.println("Es un empate!");
                        System.out.println(acum1+"<-- Puntaje Jugador 1\n"+acum2+"<-- Puntaje Jugador 2");
                    }
                }//fin case 1
                break;
                case 2 : {
                    int arma;
                    System.out.println("Elija su arma (?)");
                    do{
                        System.out.println("0. Tijeras\n1. Papel\n2. Piedra\n3. Lizard\n4. Spock");
                        arma = read.nextInt();
                    }while(arma<0||arma>4);
                    switch (arma){
                        case 0: {
                            System.out.println("Eligió usar Tijeras");
                        }
                        break;
                        case 1: {
                            System.out.println("Eligió usar Papel");
                        }
                        break;
                        case 2: {
                            System.out.println("Eligió usar Piedra");
                        }
                        break;
                        case 3: {
                            System.out.println("Eligió usar Lizard");
                        }
                        break;
                        case 4: {
                            System.out.println("Eligió usar Spock");
                        }
                        break;
                    }
                    int armabot = rng.nextInt(5)+1;
                    switch (armabot){
                        case 1: {
                            System.out.println("La maquina eligió Tijeras");
                        }
                        break;
                        case 2: {
                            System.out.println("La maquina eligió Papel");
                        }
                        break;
                        case 3: {
                            System.out.println("La maquina eligió Piedra");
                        }
                        break;
                        case 4: {
                            System.out.println("La maquina eligió Lizard");
                        }
                        break;
                        case 5: {
                            System.out.println("La maquina eligió Spock");
                        }
                        break;
                    }//fin switch armabot
                    int resultado = casojuego(arma,armabot);
                    if (resultado ==0 ){
                        System.out.println("Empate!");
                    }
                    else if(resultado == 1){
                        System.out.println("¡Jugador Gana!");
                    }
                    else{
                        System.out.println("¡La máquina Gana!");
                    }
                }//fin case 2
                break;
                case 3: {
                    continuar = false;
                }//fin case 3
                break;
                default : {
                    System.out.println("El numero ingresado no es una opción válida");
                }//fin default
                break;
            }//fin switch opcion
        }//fin while continuar
        // TODO code application logic here
    }//fin method main
    static int [][] genmatriz (int rows, int columns){
        int [][] matriz = new int [rows][columns];
        boolean existe = false;
        for (int i = 0; i< matriz.length; i++){
            for (int j = 0; j < matriz[0].length; j++){
                do{
                    existe = false;
                    int rand = rng.nextInt(rows*columns)+1;
                    for ( int x = 0; x <matriz.length; x++){
                        for (int y = 0; y<matriz[0].length;y++){
                            if (rand == matriz[x][y]){
                                existe = true;
                                break;
                            }//fin if
                        }//fin for y
                    }//fin for x validaciones
                    if (existe == false){
                        matriz[i][j] = rand;
                    }//fin if añadir rand a matriz
                }while (existe == true);
            }//fin for j (añadir valores a matriz)
        }//fin for i
        return matriz;
    }//fin method genmatriz
    static void print ( int [][] matriz){
        for (int i = 0; i< matriz.length; i++){
            for (int j = 0; j < matriz[0].length; j++){
                if ( j == matriz[0].length-1){
                    System.out.println(" "+matriz[i][j]);
                }
                else if ( matriz[i][j]>=10){
                    System.out.print(" "+matriz[i][j]);
                }
                else{
                    System.out.print(" "+matriz[i][j]+" ");
                }
            }
        }
    }
    static int [][] shoot1 (int [][] matriz,int disparado){
        
        boolean hit = false;
        for (int i = 0; i<matriz.length;i++){
            for (int j = 0; j<matriz[0].length;j++){
                if (matriz[i][j] == disparado){
                    if(matriz[i][j] != 99 && matriz[i][j]!=88){
                        matriz [i][j] = 99;
                        hit = true;
                        break;
                    }    
                }
            }
        }
        if (hit){
            System.out.println("Tiro acertado!");
        }
        else{
            System.out.println("Fallaste!");
        }
        return matriz;
    }//fin method shoot1
    static int [][] shoot2 (int [][] matriz, int disparado){
        
        boolean hit = false;
        for (int i = 0; i<matriz.length;i++){
            for (int j = 0; j<matriz[0].length;j++){
                if (matriz[i][j] == disparado){
                    if(matriz[i][j] != 99 && matriz[i][j]!=88){
                        matriz [i][j] = 88;
                        hit = true;
                        break;
                    }   
                }
            }
        }
        if (hit){
            System.out.println("Tiro acertado!");
        }
        else if( hit ==false){
            System.out.println("Fallaste!");
        }
        return matriz;
    }//fin method shoot1
    static int sumaacum ( int [][] matriz, int disparado){
        boolean hit = false;
        int sumaacum = 0;
        for (int i = 0; i<matriz.length;i++){
            for (int j = 0; j<matriz[0].length;j++){
                if(matriz[i][j] == disparado){
                    if(matriz[i][j] != 99 && matriz[i][j]!=88){
                        hit = true;
                        break;
                    }  
                }
                
            }
        }
        if (hit){
            sumaacum= disparado;
        }
        
        return sumaacum;
    }
    static int casojuego ( int usuario , int maquina){
        int [][] matriz = new int [5][5];
        int outcome = 5;
        for (int i = 0; i<matriz.length;i++){
            for (int j = 0; j<matriz.length;j++){
                if (i==j){
                    matriz[i][j] = 0;
                }
                else if(i-j==1||j-i==2||i-j==3|j-i==4){
                    matriz[i][j] = 2;
                }
                else if(i-j == 2||j-i==1|j-i==3||i-j==4){
                    matriz[i][j] = 1;
                }
            }
        }
        for (int i = 0; i<matriz.length; i++){
            for (int j = 0; j<matriz.length; j++){
                if(i==usuario&&j==maquina-1){
                    outcome = matriz[i][j];
                }
            }
        }
        return outcome;
    }
}
