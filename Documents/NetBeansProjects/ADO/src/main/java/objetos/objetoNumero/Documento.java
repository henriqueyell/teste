/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos.objetoNumero;



import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Documento extends PlainDocument {

    private boolean numero;

    public Documento(boolean isNumber) {
        super();
        numero = isNumber;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet attribute)
            throws BadLocationException {
        if (numero == true) {
//verifica se o caracter inserido é um numero, caso não seja o mesmo não é inserido no campo
            for (char c : str.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return;
                }
            }
        }
//caso ele chegue até aqui significa que o caracteres é um digito (0-9), então o mesmo é inserido no campo.
        super.insertString(offs, str.toUpperCase(), attribute);

    }
}
