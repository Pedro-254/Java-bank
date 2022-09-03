package banco;

import java.io.BufferedWriter;
import java.util.concurrent.ThreadLocalRandom;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class Menu {

    public void print() {
        limpar();
        System.out.println("_______MENU_______");
        System.out.println("1 - Criar conta");
        System.out.println("2 - Depositar");
        System.out.println("3 - Sacar");
        System.out.println("4 - Extrato");
        System.out.println("5 - Deletar conta");
        System.out.println("0 - Sair");
        System.out.println("__________________");
    }

    public static boolean opt(int p) throws IOException {
        limpar();
        switch (p) {
            case 0:
                return false;
            case 1:
                criar_conta();
                return true;
            case 2:
                depositar_sacar(1);
                return true;
            case 3:
                depositar_sacar(-1);
                return true;
            case 4:
                gerar_extrato();
                return true;
            case 5:
                deletar_conta();
                return true;
            default:
                throw new AssertionError();
        }
    }

//  Variaveis globais
    public static String cpf = "";
    public static String nome = "";
    public static Scanner s = new Scanner("");

//  Gerar novo id
//    public static String gerar_id() {
//        int rand_int = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
//
//        id = Integer.toString(rand_int);
//
//        return id;
//    }
//  Função para criar uma nova conta
    public static void criar_conta() throws IOException {

        System.out.println("Digite seu CPF:");
        s = new Scanner(System.in);
        cpf = s.next().replace(".", "").replace("-", "");

        System.out.println("Digite seu nome:");
        s = new Scanner(System.in);
        nome = s.next();

        System.out.println("Digite sua senha:");
        s = new Scanner(System.in);
        String senha = s.next();

        File arquivo = new File("./Usuarios/" + cpf + ".txt");
        arquivo.createNewFile();

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter(arquivo, true));
        writer.append("Cpf: " + cpf + "\n");
        writer.append("Nome: " + nome + "\n");
        writer.append("Senha: " + senha + "\n");
        writer.append("Saldo: R$ 0.00\n");
        writer.close();
    }

    public static boolean validar_cpf(String cpf) throws IOException {

        if (cpf.length() == 11) {
            String[] part = cpf.split("");
            int num = 0;
            for (int i = 10; i > 1; i--) {
                for (int j = 0; j < 9; j++) {
                    num += Integer.parseInt(part[j]) * i;
                }
            }
            num *= 10;
            num %= 11;

            if (num == Integer.parseInt(part[9])) {
                return true;
            }
        }
        return false;
    }
    //  Função que identifica e verifica os dados fornecidos pelo cliente

    public static boolean verificar_dados() throws IOException {
        limpar();

//      Verificar cpf
        System.out.println("Digite seu Cpf:");
        s = new Scanner(System.in);
        cpf = s.next().replace(".", "").replace("-", "");

        File f = new File("./Usuarios/" + cpf + ".txt");
        if (f.exists() == false) {
            System.out.println("Cpf não encontrados.");
            return false;
        }
//      Verificar nome
        System.out.println("Digite seu nome:");
        s = new Scanner(System.in);
        nome = s.next();
        Scanner in = new Scanner(new FileReader(f));
        String arq_nome = in.nextLine();
        arq_nome = in.nextLine();
        arq_nome = arq_nome.substring(6, arq_nome.length());
        if (nome.equals(arq_nome) == false) {
            System.err.println(arq_nome);
            System.out.println("Nome invalido.");
            return false;
        }

//      Verificar senha
        in = new Scanner(new FileReader(f));
        String line = "";
        for (int i = 0; i < 3; i++) {
            line = in.nextLine();
        }

        String senha_file = line.substring(7, line.length());
        System.out.println("Digite sua senha:");
        s = new Scanner(System.in);
        String senha = s.next();
        if (senha.equals(senha_file) == false) {
            System.out.println("Senha incorreta.");
            return false;
        }

        return true;
    }

//  Função para depositar uma quantia
    public static void depositar_sacar(int type) throws IOException {
        s = new Scanner("");
        if (verificar_dados()) {
            if (type == 1) {
                System.out.println("Digite o valor para deposito:");
            } else {
                System.out.println("Digite o valor para saque:");
            }
            s = new Scanner(System.in);
            double valor = s.nextDouble() * type;

            File f = new File("./Usuarios/" + cpf + ".txt");
            Scanner in = new Scanner(new FileReader(f));
            String txt = "";
            for (int i = 0; i < 3; i++) {
                txt += in.nextLine() + "\n";
            }

            String txt_valor = in.nextLine();
            txt_valor = txt_valor.substring(9, txt_valor.length());
            valor = Double.parseDouble(txt_valor) + valor;
            if (valor >= 0) {
                String new_txt_valor = "Saldo: R$ " + valor + "\n";
                txt += new_txt_valor;

                BufferedWriter writer;
                writer = new BufferedWriter(new FileWriter(f));
                writer.write(txt);
                writer.close();
            } else {
                limpar();
                System.out.println("Valor para saque invalido.");
                System.out.println("0 - Menu \n1 - Tentar novamente");
                s = new Scanner(System.in);
                int opt = s.nextInt();
                switch (opt) {
                    case 0:
                        Menu menu = new Menu();
                        menu.print();
                        break;
                    case 1:
                        verificar_dados();
                        break;
                    default:
                        throw new AssertionError();
                }
            }

        } else {
            limpar();
            System.out.println("0 - Menu \n1 - Tentar novamente");
            s = new Scanner(System.in);
            int opt = s.nextInt();
            switch (opt) {
                case 0:
                    Menu menu = new Menu();
                    menu.print();
                    break;
                case 1:
                    verificar_dados();
                    break;
                default:
                    throw new AssertionError();
            }
        }

    }

    public static void removerArquivos(File f) {
        // Se o arquivo passado for um diretório
        if (f.isDirectory()) {
            /* Lista todos os arquivos do diretório em um array 
                   de objetos File */
            File[] files = f.listFiles();
            // Identa a lista (foreach) e deleta um por um
            for (File file : files) {
                file.delete();
            }
        }
    }

    public static void gerar_extrato() throws IOException {
        if (verificar_dados()) {
            limpar();
            File f = new File("./Usuarios/" + cpf + ".txt");
            Scanner in = new Scanner(new FileReader(f));
            String txt = "";
            for (int i = 0; i < 4; i++) {
                txt += in.nextLine() + "\n";
            }
            System.out.println("__________________");
            System.out.print(txt);
            System.out.println("__________________");
            System.out.println("Digite 0 para voltar ao menu.");
            s = new Scanner(System.in);
            String pausa = s.next();
        }

    }

    public static void deletar_conta() throws IOException {
        if (verificar_dados()) {
            File f = new File("./Usuarios/" + cpf + ".txt");
            System.out.println(cpf + ".txt");
//            Path path = Paths.get("../Usuarios/" + nome + "_" + id + ".txt");
            System.out.println("Sua conta sera deletada. \n 1 - Confirmar \n 2 - Negar");
            s = new Scanner(System.in);
            if (s.nextInt() == 1) {
//                Files.delete(path);
//                f.delete();
                removerArquivos(f);
            }

        }

    }

    public static void limpar() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

}
