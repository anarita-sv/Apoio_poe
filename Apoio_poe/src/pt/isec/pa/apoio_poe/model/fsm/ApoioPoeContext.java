
/**
*
*  @author AnaVideira 
*
*  Classe que comunica com a user interface
*
*/


package pt.isec.pa.apoio_poe.model.fsm;
import pt.isec.pa.apoio_poe.model.data.ApoioPoeData;
import pt.isec.pa.apoio_poe.model.data.Enum.Fase;
import pt.isec.pa.apoio_poe.model.fsm.states.ApoioPoeState;

public class ApoioPoeContext {
    private ApoioPoeIState state; 
    private ApoioPoeData data; 

    public ApoioPoeContext(){
        data = new ApoioPoeData();
        state = ApoioPoeState.AGUARDA_CONFIGURACAO.createState(this, data); 
         //referencia para o estado atual que poderá ser iniciado no seu construtor 
    }


    // Método package-private que permita alterar o estado atual
    void changeState( ApoioPoeIState newState ){
        state = newState; 
    }



    // Método público que permita obter o estado atual
    public ApoioPoeState getState(){
        if( state == null )
            return null;
            
        return state.getState(); 
    }

    public Fase getFase(){
        return state.getFase();
    }





    // Métodos que reencaminhem as ações/eventos para o estado ativo

    public boolean alteraModoConfiguracao( int op){
        return state.alteraModoConfiguracao(op);
    }

   public void avanca(){
       state.avanca();
   }


    public boolean avancaFechandoFase(){
        return avancaFechandoFase();
    }

 

// TO DO 




    


    // Conjunto de métodos que permita obter os dados necessários à 
    // interação com o utilizador ou com os restantes módulos do programa




            // TO DO 


}
