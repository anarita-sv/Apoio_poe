package pt.isec.pa.apoio_poe.model.data;



public class AutoProposta extends Proposta{
    protected Long numEstudante;


    public AutoProposta( String codigoProp, String titulo, Long numEstudante) {
        this.tipo = TipoProposta.T3;
        this.codigoProp = codigoProp;
        this.titulo = titulo;
        this.numEstudante = numEstudante;
        this.orientador = null; 

    }

    public String propostasToString(){
        StringBuilder s = new StringBuilder();

        s.append("\n[" + tipo + "] " + " [" + codigoProp + "] ");
   
        s.append( titulo + " - " + numEstudante );
        
        if (this.orientador != null)
         s.append( " - " + orientador );

        return s.toString();

    }
    

    public TipoProposta getTipo(){
        return tipo; 
    }


}