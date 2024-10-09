package br.edu.fatecpg.view;
import java.util.Set;

public class ImprimeEmails {

    public static void imprimeEmails(Set<String> emails) {
        System.out.println("E-mails coletados: ");
        emails.forEach(System.out::println); // Exibe cada e-mail
    }
}
