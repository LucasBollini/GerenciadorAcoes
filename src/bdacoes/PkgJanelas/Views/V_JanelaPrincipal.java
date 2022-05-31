package bdacoes.PkgJanelas.Views;

import bdacoes.PkgJanelas.Models.M_JanelaPrincipal;
import java.awt.Dimension;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class V_JanelaPrincipal extends JFrame{
    
    public V_JanelaPrincipal(M_JanelaPrincipal model){
        
        JPanel painelConteudo;
                
        painelConteudo = new JPanel();
        painelConteudo.setPreferredSize(new Dimension(1000,600));
        painelConteudo.setLayout(null);
        
        model.modelE.setColumnIdentifiers(new String [] {"Nome","Quantidade","Custo M"});
        model.tabelaE.getTableHeader().setReorderingAllowed(false);
        model.tabelaE.setAutoCreateRowSorter(true);
        JScrollPane scrollE = new JScrollPane(model.tabelaE);
        scrollE.setBounds(10, 40, 250, 540);
        painelConteudo.add(scrollE);

        model.modelD.setColumnIdentifiers(new String [] {"Data","Tipo","Qtd","Custo M","Valor","Corret","Emol","Compra T","Venda T","Lucro T","Lucro %","id"});
        model.tabelaD.getColumnModel().getColumn(0).setMinWidth(90);
        model.tabelaD.getColumnModel().getColumn(1).setMaxWidth(50);
        model.tabelaD.getColumnModel().getColumn(2).setMaxWidth(45);
        model.tabelaD.getColumnModel().getColumn(11).setMinWidth(0);
        model.tabelaD.getColumnModel().getColumn(11).setMaxWidth(0);
        model.tabelaD.getColumnModel().getColumn(11).setWidth(0);
        model.tabelaD.getTableHeader().setReorderingAllowed(false);
        model.tabelaD.setAutoCreateRowSorter(true);
        JScrollPane scrollD = new JScrollPane(model.tabelaD);
        scrollD.setBounds(300, 40, 690, 300);
        painelConteudo.add(scrollD);

        JLabel lblMes = new JLabel("Mês:");
        lblMes.setBounds(300, 525, 50, 25);
        painelConteudo.add(lblMes);

        model.comboMes.setSelectedIndex((LocalDate.now().getMonthValue() - 1));
        model.comboMes.setBounds(355, 525, 60, 25);
        painelConteudo.add(model.comboMes);

        JLabel lblAno = new JLabel("Ano:");
        lblAno.setBounds(300, 555, 50, 25);
        painelConteudo.add(lblAno);

        model.comboAno.setSelectedIndex(LocalDate.now().getYear() - 2020);
        model.comboAno.setBounds(355, 555, 60, 25);
        painelConteudo.add(model.comboAno);
        
        model.btnRmv.setBounds(890, 380, 100, 40);
        painelConteudo.add(model.btnRmv);
        
        model.btnAdd.setBounds(300, 380, 160, 40);
        painelConteudo.add(model.btnAdd);

        model.btnDesdobr.setBounds(480, 380, 160, 40);
        painelConteudo.add(model.btnDesdobr);
        
        model.btnMensal.setBounds(300, 480, 160, 40);
        painelConteudo.add(model.btnMensal);

        model.btnAnual.setBounds(480, 480, 160, 40);
        painelConteudo.add(model.btnAnual);
        
        model.btnMovs.setBounds(660, 480, 160, 40);
        painelConteudo.add(model.btnMovs);
            
        setTitle("Gerenciador Ações");
        setContentPane(painelConteudo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
    }
}