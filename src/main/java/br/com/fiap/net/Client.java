package br.com.fiap.net;

import br.com.fiap.crypto.RSA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;
        RSA rsa = new RSA();

        System.out.println("--- CLIENTE INICIADO ---");

        try (Socket socket = new Socket(hostname, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("\n[ETAPA 1: CONEXÃO]");
            System.out.println("Conectado ao servidor em " + hostname + ":" + port);

            String serverWelcome = in.readLine();
            System.out.println("\n[ETAPA 2: TROCA DE CHAVES]");
            System.out.println("Resposta do Servidor: " + serverWelcome);
            System.out.println("Chave Pública (E, N) sendo usada: (" + rsa.getPublicKeyE() + ", " + rsa.getModulusN() + ")");

            System.out.println("\n[ETAPA 3: COMUNICAÇÃO]");
            System.out.println("Inicie a conversa. (digite 'sair' para encerrar)");

            String userInput;
            while (true) {
                System.out.print("\nDigite sua mensagem: ");
                userInput = consoleReader.readLine();
                String encryptedMessage = rsa.encrypt(userInput);
                System.out.println("Mensagem Criptografada Enviada: " + encryptedMessage);
                out.println(encryptedMessage);

                if ("sair".equalsIgnoreCase(userInput)) {
                    break;
                }

                String encryptedResponse = in.readLine();
                System.out.println("Resposta Criptografada Recebida: " + encryptedResponse);
                String decryptedResponse = rsa.decrypt(encryptedResponse);
                System.out.println("Resposta Descriptografada: " + decryptedResponse);

                if ("sair".equalsIgnoreCase(decryptedResponse)) {
                    break;
                }
            }

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        } finally {
            System.out.println("\n[ETAPA 4: DESCONEXÃO]");
            System.out.println("Cliente encerrando a conexão.");
        }
    }
}
