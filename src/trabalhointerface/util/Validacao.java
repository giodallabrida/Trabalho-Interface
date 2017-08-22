package trabalhointerface.util;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Validacao {

    public static boolean validaCampo(JTextField campo) {
        if (campo.getText().trim().isEmpty()) {
            Mensagens.msgErro(campo.getToolTipText());
            campo.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean validaString(JTextField campo) {
        if (campo.getText().length() >= 60) {
            Mensagens.msgAviso("Você só pode digitar 60 caracteres.");
            return false;
        }
        return true;
    }
    
    public static boolean validaPNG(String campo){
        if (campo.substring(campo.length() - 3, campo.length()).equals("png")){
            return true;
        }
        return false;
    }

    public static boolean validaSenha(JPasswordField campo) {
        if (String.copyValueOf(campo.getPassword()).trim().isEmpty()) {
            Mensagens.msgErro(campo.getToolTipText());
            campo.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean validaFloat(JTextField campo, int min, int max) {
        try {
            float valor = Float.valueOf(campo.getText());
            if (campo.getText().trim().isEmpty()) {
                Mensagens.msgErro(campo.getToolTipText());
                campo.requestFocus();
                return false;
            } else if (valor <= min || valor >= max) {
                Mensagens.msgErro("Preço inválido.");
                return false;
            } else {
                return true;
            }
        } catch (NumberFormatException e) {
            Mensagens.msgErro("Preço inválido " + e.getMessage());
            campo.requestFocus();
            return false;
        }
    }

    public static boolean validaIcone(JLabel caminho) {
        boolean aux = true;
        if (caminho.getText().isEmpty()) {
            Mensagens.msgErro(caminho.getToolTipText());
            aux = false;
        }
        return aux;
    }
}
