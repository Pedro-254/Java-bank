/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package teste;

import java.util.Random;

/**
 *
 * @author Pedro
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random gerador = new Random();

        int numero = gerador.nextInt();
        System.out.println(numero);

        String teste = Integer.toString(numero);
        System.out.println(teste);
    }

}
