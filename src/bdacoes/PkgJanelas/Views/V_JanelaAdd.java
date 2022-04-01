package bdacoes.PkgJanelas.Views;

import bdacoes.PkgJanelas.Models.M_JanelaAdd;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class V_JanelaAdd extends JFrame{
    
    public V_JanelaAdd(M_JanelaAdd model){
        
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(null);
        painelConteudo.setPreferredSize(new Dimension(275, 310));
        
        JLabel  lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 10, 50, 15);
        painelConteudo.add(lblNome);
        
        model.txtNome.setBounds(90, 5, 105, 25);
        painelConteudo.add(model.txtNome);
        
        JLabel lblQtd = new JLabel("Qtd:");
        lblQtd.setBounds(10, 40, 50, 15);
        painelConteudo.add(lblQtd);
        
        model.txtQtd.setBounds(90, 35, 105, 25);
        painelConteudo.add(model.txtQtd);
        
        JLabel lblValor = new JLabel("Valor:");
        lblValor.setBounds(10, 70, 50, 15);
        painelConteudo.add(lblValor);
        
        model.txtValor.setBounds(90, 65, 105, 25);
        painelConteudo.add(model.txtValor);
        
        JLabel lblCorret = new JLabel("Corret:"); 
        lblCorret.setBounds(10, 100, 50, 15);
        painelConteudo.add(lblCorret);
        
        model.txtCorret.setBounds(90, 95, 105, 25);
        painelConteudo.add(model.txtCorret);
        
        JLabel lblEmol = new JLabel("Emol:");
        lblEmol.setBounds(10, 130, 50, 15);
        painelConteudo.add(lblEmol);
        
        model.txtEmol.setBounds(90, 125, 105, 25);
        painelConteudo.add(model.txtEmol);
        
        JLabel lblData = new JLabel("Data:");
        lblData.setBounds(10, 165, 50, 15);
        painelConteudo.add(lblData);
        
        model.objCombosData.comboDia.setBounds(90, 160, 50, 25);
        painelConteudo.add(model.objCombosData.comboDia);
        
        model.objCombosData.comboMes.setBounds(145, 160, 50, 25);
        painelConteudo.add(model.objCombosData.comboMes);
        
        model.objCombosData.comboAno.setBounds(200, 160, 65, 25);
        painelConteudo.add(model.objCombosData.comboAno);
        
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(10, 195, 60, 15);
        painelConteudo.add(lblTipo);
        
        model.comboTipo.setBounds(90, 190, 79, 25);
        painelConteudo.add(model.comboTipo);
        
        model.btnConfirmar.setBounds(78,240,115,25);
        painelConteudo.add(model.btnConfirmar);
        
        model.lblErro.setBounds(10, 270, 255, 25);
        painelConteudo.add(model.lblErro);
        
        add(painelConteudo);
        pack();
        setTitle("Adicionar");
        setResizable(false);
        setLocation(550, 200);
        setVisible(true);
    } 
}