/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mercado;

import java.io.IOException;

/**
 *
 * @author Pedro
 */
public class Mercado {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        Caixa caixa = new Caixa();
        caixa.abrir_caixa();
        
        caixa.depositar(1000);
        caixa.fechar_caixa();
    }
    
}
