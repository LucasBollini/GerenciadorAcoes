package bdacoes.PkgJanelas.Controllers;

import bdacoes.PkgJanelas.Models.M_JanelaMensal;
import bdacoes.PkgJanelas.Views.V_JanelaMensal;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class C_JanelaMensal{
    
    V_JanelaMensal view;
    M_JanelaMensal model;
    
    private String nomeMes(String input){
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
    
    public C_JanelaMensal(C_JanelaPrincipal controllerPrincipal, String mes, String ano){
        model = new M_JanelaMensal();
        view = new V_JanelaMensal((nomeMes(mes) + " | " + ano), model);
        
        view.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                controllerPrincipal.view.revalidate();
                controllerPrincipal.view.repaint();
                controllerPrincipal.view.setVisible(true);
                view.dispose();
            }
        });
        
        model.preencherTudo((ano + "-" + mes));
        
    }
}