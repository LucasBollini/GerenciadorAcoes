package bdacoes.PkgJanelas.Controllers;

import static bdacoes.PkgGlobais.Ferramentas.btnReset;
import bdacoes.PkgJanelas.Models.M_JanelaDesdobr;
import bdacoes.PkgJanelas.Views.V_JanelaDesdobr;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class C_JanelaDesdobr{
    
    V_JanelaDesdobr view;
    M_JanelaDesdobr model;
    C_JanelaPrincipal controllerPrincipal;
    
    public void fecharJanela(){
        controllerPrincipal.view.revalidate();
        controllerPrincipal.view.repaint();
        controllerPrincipal.view.setVisible(true);
        view.dispose();
    }
    
    public C_JanelaDesdobr(C_JanelaPrincipal controllerPrincipal){
        
        model = new M_JanelaDesdobr();
        view = new V_JanelaDesdobr(model);
        this.controllerPrincipal = controllerPrincipal;
        
        model.lblErro.setForeground(Color.red);
        
        view.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                fecharJanela();
            }
        });
        
        model.btnConfirmar.addMouseListener(new MouseAdapter(){
            @Override
                public void mousePressed(MouseEvent e) {
                    btnReset(model.btnConfirmar);
                    try{
                        model.acaoDesdobrar();
                        controllerPrincipal.model.reselect(model.txtNome.getText().toUpperCase());
                        fecharJanela();
                    }catch(Exception ex){
                        model.lblErro.setText(ex.getMessage());
                    }
                }
        });
    }
}