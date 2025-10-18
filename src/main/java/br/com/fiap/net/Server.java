package br.com.fiap.net;

import br.com.fiap.crypto.RSA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int port = 12345;
        RSA rsa = new RSA();

        System.out.println("--- SERVIDOR INICIADO ---");
        System.out.println("Aguardando conexão de um cliente na porta " + port + "...");

        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("\n[ETAPA 1: CONEXÃO]");
            System.out.println("Cliente conectado: " + clientSocket.getInetAddress().getHostAddress());

            System.out.println("\n[ETAPA 2: GERAÇÃO E TROCA DE CHAVES]");
            System.out.println("As chaves foram definidas com base na planilha.");
            System.out.println("Chave Pública (E, N): (" + rsa.getPublicKeyE() + ", " + rsa.getModulusN() + ")");
            System.out.println("Enviando chave pública para o cliente...");
            out.println("Bem-vindo! Chave pública recebida.");

            System.out.println("\n[ETAPA 3: COMUNICAÇÃO]");
            System.out.println("Aguardando mensagens do cliente... (digite 'sair' para encerrar)");

            String encryptedInputLine;
            while ((encryptedInputLine = in.readLine()) != null) {
                System.out.println("\n---");
                System.out.println("Mensagem Criptografada Recebida: " + encryptedInputLine);
                String decryptedMessage = rsa.decrypt(encryptedInputLine);
                System.out.println("Mensagem Descriptografada: " + decryptedMessage);

                if ("sair".equalsIgnoreCase(decryptedMessage)) {
                    break;
                }

                System.out.print("Digite sua resposta: ");
                String response = consoleReader.readLine();
                String encryptedResponse = rsa.encrypt(response);
                out.println(encryptedResponse);
                System.out.println("Resposta criptografada enviada.");

                if ("sair".equalsIgnoreCase(response)) {
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("\n[ETAPA 4: DESCONEXÃO]");
            System.out.println("Servidor encerrando a sessão.");
        }
    }
}
