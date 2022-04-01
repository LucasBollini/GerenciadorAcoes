package bdacoes.PkgJanelas.Controllers;

import static bdacoes.PkgGlobais.Ferramentas.btnReset;
import bdacoes.PkgJanelas.Models.M_JanelaPrincipal;
import bdacoes.PkgJanelas.Views.V_JanelaPrincipal;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;

public class C_JanelaPrincipal{
    
    V_JanelaPrincipal view;
    M_JanelaPrincipal model;
    C_JanelaPrincipal esteController = this;
    
    public C_JanelaPrincipal(){
        
        model = new M_JanelaPrincipal();
        
        for(int i = 2020; i <= LocalDate.now().getYear(); i++)
                    model.comboAno.addItem(i);
        
        view = new V_JanelaPrincipal(model);
        
        model.preencherTabelaE();
        
        
        model.tabelaE.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                model.preencherTabelaD();
            }
        });
        
        model.btnRmv.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                btnReset(model.btnRmv);
                model.acaoRemover();
            }
        });
        
        model.btnAdd.addMouseListener(new abridorDeJanelas("add", model.btnAdd));
        
        model.btnDesdobr.addMouseListener(new abridorDeJanelas("desdobramento", model.btnDesdobr));
        
        model.btnMensal.addMouseListener(new abridorDeJanelas("mensal", model.btnMensal));
        
        model.btnAnual.addMouseListener(new abridorDeJanelas("anual", model.btnAnual));
        
    }
    
    class abridorDeJanelas extends MouseAdapter{
        String alvo;
        Object btn;
        
        public abridorDeJanelas(String alvo, Object btn){
            this.alvo = alvo;
            this.btn = btn;
        }
        
        @Override
        public void mousePressed(MouseEvent e) {
            btnReset(btn);
            switch(alvo){
                case "add": new C_JanelaAdd(esteController); break;
                case "desdobramento": new C_JanelaDesdobr(esteController); break;
                case "mensal": new C_JanelaMensal(esteController, model.comboMes.getSelectedItem().toString(), model.comboAno.getSelectedItem().toString()); break;
                case "anual": new C_JanelaAnual(esteController, model.comboAno.getSelectedItem().toString()); break;
                default: break;
            }
            view.setVisible(false);
        }
    }
}