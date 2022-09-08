/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mercado;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

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
        setDinheiro(dinheiro + valor);
    }

    public void abrir_caixa() throws IOException{
        File f = new File("caixa_de_dinheiro.txt");
        if (f.exists() == false) {
            boolean success = f.createNewFile();
            FileWriter w = new FileWriter("caixa_de_dinheiro.txt");
            String str = Double.toString(0.0);
            w.write(str);
            setDinheiro(0.0);
        }
        else {
            Scanner read = new Scanner(new FileReader(f));
            String valor_str = read.nextLine();
            Double valor = Double.parseDouble(valor_str);
            setDinheiro(valor);
        }
    }

    public void fechar_caixa() throws IOException {
        FileWriter w = new FileWriter("caixa_de_dinheiro.txt");
        String str = Double.toString(getDinheiro());
        w.write(str);
        w.close();
    }
}
