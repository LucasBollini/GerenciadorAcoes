package bdacoes.PkgJanelas.Models;

import bdacoes.PkgGlobais.ClasseAcao;
import static bdacoes.PkgGlobais.Ferramentas.FtoS;
import bdacoes.PkgGlobais.HandlerBd;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class M_JanelaMensal {
    
    public  DefaultTableModel   modelE = new DefaultTableModel(0,7){
                                    @Override
                                        public boolean isCellEditable(int row, int column){return false;}
                                },
                                modelD = new DefaultTableModel(0,10){
                                    @Override
                                        public boolean isCellEditable(int row, int column){return false;}
                                };
    
    public  JTable  tabelaE = new JTable(modelE),
                    tabelaD = new JTable(modelD);
    
    public  ArrayList<ClasseAcao>   listaAcoesC = new ArrayList<>(),
                                    listaAcoesV = new ArrayList<>();
    
    public  JLabel  lblTCompras = new JLabel(),
                    lblTVendas= new JLabel(),
                    lblTLucro = new JLabel(),
                    lblTPrejuizo = new JLabel(),
                    lblTResultado = new JLabel(),
                    lblTDarf = new JLabel();
    
    public  float   totalL = 0,
                    totalP = 0,
                    totalC = 0,
                    totalV = 0,
                    holderTotal = 0;
    
    private void lerAcoes(String data){
        for(String nome : HandlerBd.procurarArquivos())
            for(ClasseAcao alvo : HandlerBd.lerArquivo(nome))
                if(alvo.getData().toString().substring(0, 7).equals(data))
                    switch(alvo.getTipo()){
                        case "C": listaAcoesC.add(alvo); break;
                        case "V": listaAcoesV.add(alvo); break;
                        default: break;
                    }
    }
    
    private void preencherTabelas(){
        listaAcoesC.forEach(acao -> modelE.addRow(new String [] {acao.getNome(),"" + acao.getData(),"" + acao.getQtd(), FtoS(acao.getValor()), FtoS(acao.getCorret()), FtoS(acao.getEmol()), FtoS((acao.getQtd() * acao.getValor()) + (acao.getCorret() + acao.getEmol()))}));
        listaAcoesV.forEach(acao -> modelD.addRow(new String [] {acao.getNome(),"" + acao.getData(),"" + acao.getQtd(), FtoS(acao.getpMedio()), FtoS(acao.getValor()), FtoS(acao.getCorret()), FtoS(acao.getEmol()), FtoS(acao.getQtd() * acao.getpMedio()), FtoS((acao.getQtd() * acao.getValor()) - (acao.getCorret() + acao.getEmol())), FtoS(((acao.getQtd() * acao.getValor()) - (acao.getCorret() + acao.getEmol())) - (acao.getQtd() * acao.getpMedio()))}));
    }
    
    private void calcTotais(ClasseAcao acao){
        holderTotal = ((acao.getQtd() * acao.getValor()) - (acao.getCorret() + acao.getEmol())) - (acao.getQtd() * acao.getpMedio());
        if(holderTotal >= 0)
            totalL += holderTotal;
        else
            totalP += holderTotal;
        totalV += acao.getQtd() * acao.getValor();
    }
    
    private void preencherLblSimples(){
        totalC = totalV = totalL = totalP = holderTotal = 0;
        
        listaAcoesV.forEach(acao -> calcTotais(acao));
        
        listaAcoesC.forEach(acao -> totalC += ((acao.getQtd() * acao.getValor()) + (acao.getCorret() + acao.getEmol())));
        
        lblTCompras.setText("Total Compras = R$" + FtoS(totalC));
        lblTVendas.setText("Total Vendas = R$" + FtoS(totalV));
        lblTLucro.setText(      "Total Lucro     = R$" + FtoS(totalL));
        lblTPrejuizo.setText(   "Total Prejuízo = R$" + FtoS(totalP));
        lblTResultado.setText(  "Total Líquido  = R$" + FtoS(totalL + totalP));
        
    }
    
    private void preencherDarf(){
        float acumulador = 0;
        
        for(ClasseAcao venda : listaAcoesV)
            for(ClasseAcao compra : listaAcoesC)
                if((venda.getData() == compra.getData()) && venda.getNome().equals(compra.getNome()))
                    acumulador += ((venda.getQtd() * venda.getValor()) - (venda.getCorret() + venda.getEmol() + (venda.getQtd() * venda.getpMedio()))) * 0.2;
                else
                    if(totalV > 20000)
                        acumulador += ((venda.getQtd() * venda.getValor()) - (venda.getCorret() + venda.getEmol() + (venda.getQtd() * venda.getpMedio()))) * 0.15;
        
        lblTDarf.setText("Total DARF = R$" + FtoS(acumulador));
    }
    
    public void preencherTudo(String data){
        try{
            lerAcoes(data);
            preencherTabelas();
            preencherLblSimples();
            preencherDarf();
        }catch(Exception e){}
    }
}