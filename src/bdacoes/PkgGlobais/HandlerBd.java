package bdacoes.PkgGlobais;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class HandlerBd {
    
    private static void escreverArquivo(String nome, ArrayList<ClasseAcao> listaAcoes){
        try{
            
            if(listaAcoes.isEmpty()){
                File arquivo = new File("arquivos/" + nome + ".txt");
                arquivo.delete();
                return;
            }
            
            FileWriter writer = new FileWriter("arquivos/" + nome + ".txt");
            BufferedWriter escritor = new BufferedWriter(writer);
            
            for(ClasseAcao alvo : listaAcoes)
                escritor.write(        alvo.getData() + 
                                "\n" + alvo.getQtd() + 
                                "\n" + alvo.getValor() + 
                                "\n" + alvo.getCorret() + 
                                "\n" + alvo.getEmol() + 
                                "\n" + alvo.getpMedio() + 
                                "\n" + alvo.getTipo() + 
                                "\n");
            
            escritor.close();
            writer.close();
        }catch(Exception e){}
    }
    
    public static ArrayList<ClasseAcao> lerArquivo(String nome){
        try{
            FileReader reader = new FileReader("arquivos/" + nome + ".txt");
            BufferedReader leitor = new BufferedReader(reader);
            ArrayList<ClasseAcao> listaAcoes = new ArrayList<>();

            String data;
            while((data = leitor.readLine()) != null)
                listaAcoes.add(new ClasseAcao(nome, data, leitor.readLine(), leitor.readLine(), leitor.readLine(), leitor.readLine(), leitor.readLine(), leitor.readLine()));
            
            leitor.close();
            reader.close();
            return listaAcoes;
        }catch(Exception e){return null;}
    }
    
    public static String[] procurarArquivos(){
        try{
            String[] arqs = new File("arquivos").list();
            for(int i = 0; i < arqs.length; i++)
                    arqs[i] = arqs[i].substring(0, (arqs[i].length() - 4));
            return arqs;
        }catch(Exception e){new File("arquivos").mkdir(); return null;}
    }
        
    public static float[] calcPMedio(String nome, int qtdCustom){
        
        float resultado[] = new float[]{0,0,0};

        List<ClasseAcao> acao = lerArquivo(nome);
        if(qtdCustom != 0)
            acao = acao.subList(0, qtdCustom);
        
        for(ClasseAcao alvo : acao){
            switch(alvo.getTipo()){
                case "D":   resultado[1] = alvo.getQtd();
                            break;
                case "C":   resultado[0] += (alvo.getQtd() * alvo.getValor()) + alvo.getCorret() + alvo.getEmol();
                            resultado[1] += alvo.getQtd();
                            break;
                case "V":   resultado[0] -= alvo.getQtd() * (resultado[0]/resultado[1]);
                            resultado[1] -= alvo.getQtd();
                            break;
                default:    break;
            }
        }
        
        if(resultado[1] != 0)
            resultado[2] = resultado[0]/resultado[1];
        
        return resultado;
    }
    
    private static void recalcularPMedio(String nome, ArrayList<ClasseAcao> acao){
        int tam = acao.size();
        for(int i = 0; i < tam; i++)
            if(acao.get(i).getTipo().equals("V"))
                acao.get(i).setpMedio(calcPMedio(nome, i)[2]);
    }
    
    private static void reorganizarPapel(ArrayList<ClasseAcao> acao){
        int tam = acao.size();
        if(tam > 0){
            long entradaNova = (acao.get((tam-1)).getData().getYear() * 1000) + acao.get((tam-1)).getData().getDayOfYear();

            for(int i = 0; i < tam; i++){
                if(entradaNova < ((acao.get(i).getData().getYear() * 1000) + acao.get(i).getData().getDayOfYear())){
                    acao.add(i, acao.get((tam-1)));
                    acao.remove(tam);
                    break;
                }
            }
        }
    }
    
    private static void processarPapel(String nome, ArrayList<ClasseAcao> acao){
        reorganizarPapel(acao);
        escreverArquivo(nome, acao);
        recalcularPMedio(nome, acao);
        escreverArquivo(nome, acao);
    }
    
    private static boolean validarMudanca(String nome, ArrayList<ClasseAcao> acao){
        processarPapel(nome, acao);
        float contador = 0;
        
        try{
            for(ClasseAcao obj : lerArquivo(nome))
                switch(obj.getTipo()){
                    case "D":   if((contador <= 0) || (obj.getQtd() <= contador))
                                    return true;
                                contador = obj.getQtd();
                                break;
                    case "C":   contador += obj.getQtd();
                                break;
                    case "V":   contador -= obj.getQtd();
                                if(contador < 0)
                                    return true;
                                break;
                    default:    break;
                }
        }catch(Exception e){}
        return false;        
    }
    
    public static boolean addAcao(String nome, String qtd, String valor, String tx1, String tx2, String data, String tipo){
        
        ArrayList<ClasseAcao> listaDef = lerArquivo(nome);
        ClasseAcao temp = null;
        
        if(listaDef == null)
            if(tipo.equals("C"))
                listaDef = new ArrayList<>();
            else
                return false;
        
        ArrayList<ClasseAcao> listaTemp = (ArrayList<ClasseAcao>) listaDef.clone();
        
        switch(tipo){
            case "D":   temp = new ClasseAcao(nome, data, qtd, "0", "0", "0", "0", "D");
                        break;
            case "C":   temp = new ClasseAcao(nome, data, qtd, valor, tx1, tx2, "0", "C");
                        break;
            case "V":   temp = new ClasseAcao(nome, data, qtd, valor, tx1, tx2, "" + calcPMedio(nome, 0)[2],"V");
                        break;
            default:    break;
        }
        
        listaTemp.add(temp);
        
        if(validarMudanca(nome, listaTemp)){
            processarPapel(nome, listaDef);
            return false;
        }
        
        return true;
    }
    
    public static void rmvAcao(String nome, int id){
        ArrayList<ClasseAcao> listaDef = lerArquivo(nome);
        ArrayList<ClasseAcao> listaTemp = (ArrayList<ClasseAcao>) listaDef.clone();
       
        listaTemp.remove(id);
        
        if(validarMudanca(nome, listaTemp))        
            processarPapel(nome, listaDef);
        
    }
}