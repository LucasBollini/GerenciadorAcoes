package bdacoes.PkgGlobais;

import javax.swing.JButton;

public class Ferramentas {
    public static String FtoS(Float valor){
        
        String retorno = "" + valor + "0";
        int contador = 0;
        
        while(!retorno.substring(contador, contador+1).equals("."))
            contador++;
        
        retorno = retorno.replace('.', ',');
        
        return retorno.substring(0, contador + 3);
    }
    
    public static String nomeMes(String input){
        if(input.length() < 2)
            input = "0" + input;
        switch(input){
            case "01": return "Janeiro";
            case "02": return "Fevereiro";
            case "03": return "Março";
            case "04": return "Abril";
            case "05": return "Maio";
            case "06": return "Junho";
            case "07": return "Julho";
            case "08": return "Agosto";
            case "09": return "Setembro";
            case "10": return "Outubro";
            case "11": return "Novembro";
            case "12": return "Dezembro";
        }
        return "";
    }
    
    public static void btnReset(Object botao){
        ((JButton)botao).setEnabled(false);
        ((JButton)botao).setEnabled(true);
    }
}