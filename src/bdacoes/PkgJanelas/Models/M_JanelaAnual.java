package bdacoes.PkgJanelas.Models;

import static bdacoes.PkgGlobais.Ferramentas.nomeMes;
import static bdacoes.PkgGlobais.Ferramentas.FtoS;
import bdacoes.PkgGlobais.ClasseAcao;
import bdacoes.PkgGlobais.HandlerBd;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class M_JanelaAnual {
    
    private class acaoRelevante{
        public String nome;
        public int qtd;
        public float valor, custoMedio;
    }
    
    public  JTextArea   areaTxt = new JTextArea();
    
    public  JButton btnSelec = new JButton("Copiar Seleção"),
                    btnVoltar = new JButton("Voltar"),
                    btnCopiar = new JButton("Copiar Tudo");
    
    private ArrayList<acaoRelevante> listaAcoes = new ArrayList();
    
    private float resultadosMensais[][][] = new float[2][2][12];
    
    private void calcularCustodia(int ano){
        
        acaoRelevante entrada;
        
        for (String listaNome : HandlerBd.procurarArquivos()){
            entrada = new acaoRelevante();
            entrada.nome = listaNome;
            
            for(ClasseAcao selec : HandlerBd.lerArquivo(listaNome))
                if(selec.getData().getYear() <= ano)
                    switch(selec.getTipo()){
                        case "D":   entrada.qtd = selec.getQtd();
                                    break;
                        case "V":   entrada.valor -= selec.getQtd() * (entrada.valor / entrada.qtd);
                                    entrada.qtd -= selec.getQtd();
                                    break;
                        case "C":   entrada.qtd += selec.getQtd();
                                    entrada.valor += (selec.getQtd() * selec.getValor()) + selec.getCorret() + selec.getEmol();
                                    break;
                        default:    break;
                    }
            
            if(entrada.qtd > 0){
                entrada.custoMedio = entrada.valor / entrada.qtd;
                listaAcoes.add(entrada);
            }
            
        }
    }
    
    public void calcularMensais(int ano){
        int mes, tipoOp = 0;
        float resultTemp;
        for (String listaNome : HandlerBd.procurarArquivos())
            for (ClasseAcao selec : HandlerBd.lerArquivo(listaNome))
                for(mes = 1; mes < 13; mes++)
                    if((selec.getData().getYear() == ano) && (selec.getData().getMonthValue() == mes))
                        if(selec.getTipo().equals("V")){
                            tipoOp = 0;
                            resultTemp = selec.getQtd() * (selec.getValor() - selec.getpMedio());
                            for (ClasseAcao verif : HandlerBd.lerArquivo(listaNome))
                                if(verif.getTipo().equals("C"))
                                    if(verif.getData().equals(selec.getData()))
                                        tipoOp = 1;
                            if(resultTemp > 0)
                                resultadosMensais[tipoOp][0][(mes - 1)] += resultTemp;
                            else
                                resultadosMensais[tipoOp][1][(mes - 1)] += resultTemp;
                        }
    }
    
    public void escreverResumo(int ano){
        calcularMensais(ano);
        areaTxt.append("Normais\t\t\t\tDaytrade\n");
        areaTxt.append("\tLucro\tPrejuízo\t\t\tLucro\tPrejuízo\n");
        for(int mes = 0; mes < 12; mes++){
            areaTxt.append(nomeMes("" + (mes + 1)) + "\t" + FtoS(resultadosMensais[0][0][mes]) + "\t" + FtoS(resultadosMensais[0][1][mes]));
            areaTxt.append("\t\t");
            areaTxt.append(nomeMes("" + (mes + 1)) + "\t" + FtoS(resultadosMensais[1][0][mes]) + "\t" + FtoS(resultadosMensais[1][1][mes]) + "\n");
        }
        
        areaTxt.append("\n\n");
        
        calcularCustodia(ano);
        for(acaoRelevante papel : listaAcoes){
            areaTxt.append(papel.nome + ": " + papel.qtd + " unidades a um preço médio de R$" + FtoS(papel.custoMedio) + "\n");
            areaTxt.append("Situação final: R$" + FtoS(papel.valor) + "\n\n");
        }
    }
    
    
    
    
    
    /*public void escreverResumo(int ano){
        try{
            String tempTxt, tempExcel;
            float vetorTotais[][] = new float[2][4];
            for(short i = 0; i < 2; i++)
                for(short j = 0; j < 4; j++)
                        vetorTotais[i][j] = 0;

            for (String listaNome : HandlerBd.procurarArquivos()) {                
                for(short j = 0; j < 4; j++)
                    vetorTotais[0][j] = 0;
                
                tempTxt = tempExcel = "";
                
                for(ClasseAcao selec : HandlerBd.lerArquivo(listaNome))
                    if(selec.getData().getYear() <= ano)
                        switch(selec.getTipo()){
                            case "D":   vetorTotais[0][0] = selec.getQtd();
                                        if(selec.getData().getYear() == ano){
                                            tempTxt += "        - D - " + selec.getData().toString() + " - Quantidade: " + selec.getQtd() + "\n";
                                            tempExcel += "\tD\t" + selec.getData().toString() + "\t" + selec.getQtd() + "\n";
                                        }
                                        break;
                            case "V":   vetorTotais[0][0] -= selec.getQtd();
                                        vetorTotais[0][1] -= selec.getpMedio() * selec.getQtd();
                                        vetorTotais[0][2] = selec.getCorret();
                                        vetorTotais[0][3] = selec.getEmol();
                                        if(selec.getData().getYear() == ano){
                                            tempTxt += "        - V - " + selec.getData().toString() + " - Quantidade: " + selec.getQtd() +  " - Valor: R$" + selec.getValor() + " - Custo Médio: R$" + selec.getpMedio() + " - Corretagem: R$" + selec.getCorret() + " - Emolumentos: R$" + selec.getEmol() + " - Lucro: R$" + (selec.getQtd() * (selec.getValor() - ((selec.getCorret() + selec.getEmol())/selec.getQtd()) - selec.getpMedio())) + "\n";
                                            tempExcel += "\tV\t" + selec.getData().toString() + "\t" + selec.getQtd() +  "\t" + String.valueOf(selec.getValor()).replace(".", ",") + "\t" + String.valueOf(selec.getpMedio()).replace(".", ",") + "\t" + String.valueOf(selec.getCorret()).replace(".", ",") + "\t" + String.valueOf(selec.getEmol()).replace(".", ",") + "\t" + String.valueOf((selec.getQtd() * (selec.getValor() - ((selec.getCorret() + selec.getEmol())/selec.getQtd()) - selec.getpMedio()))).replace(".", ",") + "\n";
                                        }
                                        break;
                            case "C":   vetorTotais[0][0] += selec.getQtd();
                                        vetorTotais[0][1] += (selec.getQtd() * selec.getValor()) + selec.getCorret() + selec.getEmol();
                                        if(selec.getData().getYear() == ano){
                                            tempTxt += "        - C - " + selec.getData().toString() + " - Quantidade: " + selec.getQtd() +  " - Valor: R$" + selec.getValor() + " - Corretagem: R$" + selec.getCorret() + " - Emolumentos: R$" + selec.getEmol() + "\n";
                                            tempExcel += "\tC\t" + selec.getData().toString() + "\t" + selec.getQtd() +  "\t" + String.valueOf(selec.getValor()).replace(".", ",") + "\t-" + "\t" + String.valueOf(selec.getCorret()).replace(".", ",") + "\t" + String.valueOf(selec.getEmol()).replace(".", ",") + "\t-\n";
                                        }
                                        break;
                            default:    break;
                        }
                
                if(vetorTotais[0][0] > 0 || !tempTxt.isEmpty()){
                    areaTxt.append("" + listaNome + ":\n");
                    excelHolder.append(listaNome + "\tOperação\tData\tQuantidade\tValor\tCusto Médio\tCorretagem\tEmolumentos\tLucro\n");
                    areaTxt.append(tempTxt);
                    excelHolder.append(tempExcel);
                    if(vetorTotais[0][0] == 0)
                        vetorTotais[0][1] = 0;
                    for(int j = 0; j < 4; j++)
                        vetorTotais[1][j] += vetorTotais[0][j];
                    areaTxt.append("    • Qtd Final: " + vetorTotais[0][0] + "   Valor Final: R$" + vetorTotais[0][1] + "\n\n");
                    excelHolder.append("\tQtd Final\t" + vetorTotais[0][0] + "\n\tValor Final\t" + String.valueOf(vetorTotais[0][1]).replace(".", ",") + "\n\n\n");
                }
                
                
            }

            areaTxt.append("\n\nTotal Final de Dezembro:\n    • Valor Final: R$" + vetorTotais[1][1] + "\n\n");
            excelHolder.append("\n\nTotal Dezembro:\tR$" + String.valueOf(vetorTotais[1][1]).replace(".", ","));
        }catch(Exception e){}
    }*/
}