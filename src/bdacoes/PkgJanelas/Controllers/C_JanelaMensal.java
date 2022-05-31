package bdacoes.PkgJanelas.Controllers;

import static bdacoes.PkgGlobais.Ferramentas.nomeMes;
import bdacoes.PkgJanelas.Models.M_JanelaMensal;
import bdacoes.PkgJanelas.Views.V_JanelaMensal;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class C_JanelaMensal{
    
    V_JanelaMensal view;
    M_JanelaMensal model;
    
    
    
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