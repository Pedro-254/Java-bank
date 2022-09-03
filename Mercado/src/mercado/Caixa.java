/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mercado;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Pedro
 */
public class Caixa {

    private double dinheiro;

    public void setDinheiro(double dinheiro) {
        this.dinheiro = dinheiro;
    }

    public double getDinheiro() {
        return dinheiro;
    }

    public void depositar(double valor) throws IOException {
        setDinheiro(valor);
    }

    public void abrir_caixa() throws FileNotFoundException, IOException {
        File f = new File("./data/caixa_de_dinheiro.txt");
        if (f.exists() == false) {
            f.createNewFile();
        } else {
            Scanner read = new Scanner(new FileReader(f));
            String valor_str = read.nextLine();
            Double valor = Double.parseDouble(valor_str);
            setDinheiro(valor);
        }
    }

    public void fechar_caixa() {

    }
}
