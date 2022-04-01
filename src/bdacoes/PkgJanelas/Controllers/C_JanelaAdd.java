package bdacoes.PkgJanelas.Controllers;

import static bdacoes.PkgGlobais.Ferramentas.btnReset;
import bdacoes.PkgJanelas.Models.M_JanelaAdd;
import bdacoes.PkgJanelas.Views.V_JanelaAdd;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class C_JanelaAdd{
    
    V_JanelaAdd view;
    M_JanelaAdd model;
    C_JanelaPrincipal controllerPrincipal;
            
    public void fecharJanela(){
        controllerPrincipal.view.revalidate();
        controllerPrincipal.view.repaint();
        controllerPrincipal.view.setVisible(true);
        view.dispose();
    }
    
    public C_JanelaAdd(C_JanelaPrincipal controllerPrincipal){
        
        model = new M_JanelaAdd();
        view = new V_JanelaAdd(model);
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
                    model.acaoAdicionar();
                    controllerPrincipal.model.reselect(model.txtNome.getText().toUpperCase());
                    fecharJanela();
                }catch(Exception ex){
                    model.lblErro.setText(ex.getMessage());
                }
            }
        });
    }
}