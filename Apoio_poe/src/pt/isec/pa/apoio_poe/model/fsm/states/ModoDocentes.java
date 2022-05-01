package pt.isec.pa.apoio_poe.model.fsm.states;

import pt.isec.pa.apoio_poe.model.data.ApoioPoeData;
import pt.isec.pa.apoio_poe.model.data.Docente;
import pt.isec.pa.apoio_poe.model.fsm.ApoioPoeContext;
import pt.isec.pa.apoio_poe.model.fsm.ApoioPoeStateAdapter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class ModoDocentes extends ApoioPoeStateAdapter {

     ModoDocentes(ApoioPoeContext context, ApoioPoeData data) {
        super(context, data);
    }


    @Override
    public String exportaCVS(String nomeFicheiro) {
        // TODO
        return " nao implementado ainda";
    }

    @Override
    public String mostraListas() {
        StringBuilder str= new StringBuilder();
        for ( Docente a : data.getListaDocentes().values()) {
            str.append(a.DocentesToString());
        }

        
        if(str.isEmpty())
            str.append("\n> SEM IMFORMACAO !");

        return str.toString();
    }


    @Override
    public String importaCVS(String nomeFicheiro) {
        StringBuilder sb = new StringBuilder();
        String nome, mail, linha;
        FileReader fr = null;
        BufferedReader br = null;
        Scanner sc = null;

        try{
            fr = new FileReader(nomeFicheiro + ".csv");
            br = new BufferedReader(fr);

            while ((linha = br.readLine()) != null) {
                sc = new Scanner(linha);
                sc.useDelimiter(",");


                //Nome
                if (sc.hasNext()) {

                    String snString = sc.next();
                    nome = snString;
                } else {
                    sb.append("ATENCAO! Nome nao encontrado");
                    break;
                }

                //Mail 
                if (sc.hasNext()) {
                    
                    mail = sc.next();

                    if (data.docenteExiste(mail)) {
                        sb.append("ATENCAO! Docente com  " + mail + " ja existe\n");
                        break;
                    }
                } else {
                    sb.append("ATENCAO! Mail do docente nao encontrado");
                    break;
                }

                //Adicionar Docente
                if(!sc.hasNext())
                    data.adicionaDocente(nome, mail);
                else
                    sb.append("ATENCAO! Atribuitos a mais \n");

            }

            if(sc!=null) sc.close();
            br.close();
            fr.close();
        }catch (FileNotFoundException e){
            sb.append("O ficheiro nao foi encontrado\n");
        }catch (NumberFormatException e){
            sb.append("Argumento deverá ser um numero\n");
        }catch (IOException e){
            sb.append("Houve um erro (IOException)\n");
        }

        return sb.toString();
    }


    @Override
    public ApoioPoeState getState() {
        return ApoioPoeState.MODO_DOCENTES;
    }


    @Override
    public void avanca() {
        changeState(ApoioPoeState.AGUARDA_CONFIGURACAO);
    }




    
}
