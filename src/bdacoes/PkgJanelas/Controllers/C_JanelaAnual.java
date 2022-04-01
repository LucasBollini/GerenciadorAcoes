package bdacoes.PkgJanelas.Controllers;

import static bdacoes.PkgGlobais.Ferramentas.btnReset;
import bdacoes.PkgJanelas.Models.M_JanelaAnual;
import bdacoes.PkgJanelas.Views.V_JanelaAnual;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class C_JanelaAnual{
    
    M_JanelaAnual model;
    V_JanelaAnual view;
    
    private void fecharJanela(C_JanelaPrincipal controllerPrincipal){
        controllerPrincipal.view.revalidate();
        controllerPrincipal.view.repaint();
        controllerPrincipal.view.setVisible(true);
        view.dispose();
    }
    
    public C_JanelaAnual(final C_JanelaPrincipal controllerPrincipal, String ano){
        model = new M_JanelaAnual();
        view = new V_JanelaAnual(ano, model);
        
        view.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                fecharJanela(controllerPrincipal);
            }
        });
        
        model.btnVoltar.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                btnReset(model.btnVoltar);
                fecharJanela(controllerPrincipal);
            }
        });
        
        model.btnExcel.addMouseListener(new MouseAdapter(){
            @Override
                public void mousePressed(MouseEvent e) {
                    btnReset(model.btnExcel);
                    model.excelHolder.selectAll();
                    model.excelHolder.copy();
                    model.excelHolder.select(0, 0);
                }
        });
        
        model.btnCopiar.addMouseListener(new MouseAdapter(){
            @Override
                public void mousePressed(MouseEvent e) {
                    btnReset(model.btnCopiar);
                    model.areaTxt.selectAll();
                    model.areaTxt.copy();
                    model.areaTxt.select(0, 0);
                }
        });
        
        model.escreverResumo(Integer.parseInt(ano));
    }
}