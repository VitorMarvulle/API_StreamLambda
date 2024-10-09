package br.edu.fatecpg;

import java.io.IOException;
import java.util.Set;
import br.edu.fatecpg.view.ImprimeEmails;
import br.edu.fatecpg.service.ConsomeAPI;


public class Main {
    public static void main(String[] args) {

        try {
            // coleta os e-mails usando o consomeAPI em um SET e nao List, para evitar duplicados!!
            Set<String> emails = ConsomeAPI.coletarEmails();

            // Exibe os e-mails usando a view
            ImprimeEmails.imprimeEmails(emails);
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao coletar ou exibir e-mails: " + e.getMessage());
        }
    }
}
