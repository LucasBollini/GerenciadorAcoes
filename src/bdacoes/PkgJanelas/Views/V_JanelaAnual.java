package bdacoes.PkgJanelas.Views;

import bdacoes.PkgJanelas.Models.M_JanelaAnual;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class V_JanelaAnual extends JFrame{   
    
    public V_JanelaAnual(String ano, M_JanelaAnual model){
        
        JPanel painelConteudo = new JPanel();
        painelConteudo.setPreferredSize(new Dimension(1000,600));
        painelConteudo.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane(model.areaTxt);
        scrollPane.setBounds(10, 10, 980, 520);
        painelConteudo.add(scrollPane);
        
        model.btnSelec.setBounds(80, 550, 150, 30);
        painelConteudo.add(model.btnSelec);
        
        model.btnVoltar.setBounds(800, 550, 100, 30);
        painelConteudo.add(model.btnVoltar);
        
        model.btnCopiar.setBounds(250, 550, 160, 30);
        painelConteudo.add(model.btnCopiar);
        
        add(painelConteudo);
        setTitle("Resumo anual: " + ano);
        setSize(1000,600);
        setResizable(false);
        pack();
        setVisible(true);
    }
}