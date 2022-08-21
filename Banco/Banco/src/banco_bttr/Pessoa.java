package banco_bttr;

public class Pessoa {
    String nome;
    int id;
    double saldo;
    
    public void setDados(String s, int n, double v){
        this.nome = s;
        this.id = n;
        this.saldo = v;
        
    }
    
    public String getNome(){
        return nome;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public int getId(){
        return id;
    }
    
    public void depositar(double valor){
        this.saldo += valor;
    }
    
}
