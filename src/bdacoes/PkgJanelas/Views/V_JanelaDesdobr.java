package bdacoes.PkgJanelas.Views;

import bdacoes.PkgJanelas.Models.M_JanelaDesdobr;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class V_JanelaDesdobr extends JFrame{
    
    public V_JanelaDesdobr(M_JanelaDesdobr model){
        
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(null);
        painelConteudo.setPreferredSize(new Dimension(275, 180));
        
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 10, 70, 15);
        painelConteudo.add(lblNome);
        
        model.txtNome.setBounds(90, 5, 105, 25);
        painelConteudo.add(model.txtNome);
        
        JLabel lblQtd = new JLabel("Nova qtd:");
        lblQtd.setBounds(10, 40, 70, 15);
        painelConteudo.add(lblQtd);
        
        model.txtQtd.setBounds(90, 35, 105, 25);
        painelConteudo.add(model.txtQtd);
        
        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(10, 70, 70, 15);
        painelConteudo.add(lblData);
        
        model.objCombosData.comboDia.setBounds(90, 65, 50, 25);
        painelConteudo.add(model.objCombosData.comboDia);
        
        model.objCombosData.comboMes.setBounds(145, 65, 50, 25);
        painelConteudo.add(model.objCombosData.comboMes);
        
        model.objCombosData.comboAno.setBounds(200, 65, 65, 25);
        painelConteudo.add(model.objCombosData.comboAno);
        
        model.btnConfirmar.setBounds(78,110,120,25);
        painelConteudo.add(model.btnConfirmar);
        
        model.lblErro.setBounds(10, 145, 255, 25);
        painelConteudo.add(model.lblErro);
        
        add(painelConteudo);
        pack();
        setTitle("Desdobramento");
        setLayout(null);
        setResizable(false);
        setLocation(550, 200);
        setVisible(true);
    }
}