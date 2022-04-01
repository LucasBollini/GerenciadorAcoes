package bdacoes.PkgGlobais;

import javax.swing.JButton;

public class Ferramentas {
    public static String FtoS(Float valor){
        
        String retorno = "" + valor + "0";
        int contador = 0;
        
        while(!retorno.substring(contador, contador+1).equals("."))
            contador++;

        return retorno.substring(0, contador + 3);
    }
    
    public static void btnReset(Object botao){
        ((JButton)botao).setEnabled(false);
        ((JButton)botao).setEnabled(true);
    }
}