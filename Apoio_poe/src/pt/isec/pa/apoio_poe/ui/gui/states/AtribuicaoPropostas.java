package pt.isec.pa.apoio_poe.ui.gui.states;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.fsm.states.ApoioPoeState;

public class AtribuicaoPropostas extends BorderPane {
    ModelManager manager;


    Button btnVoltar, btnAvancar;

    VBox vboxOpcoes;
    


    public AtribuicaoPropostas(ModelManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();

    }

    private void createViews() {
      

        btnAvancar = new Button("Avançar");
        btnAvancar.setMinSize(200, 60);

        btnVoltar = new Button("Voltar");
        btnVoltar.setMinSize(200, 60);

        
        vboxOpcoes = new VBox();
        vboxOpcoes.getChildren().addAll(btnAvancar, btnVoltar);
        this.setRight(vboxOpcoes);
        vboxOpcoes.setPadding(new Insets(40));
        vboxOpcoes.setSpacing(20);
        vboxOpcoes.setAlignment(Pos.CENTER);


        if (manager.getSituacaoEstado() == false ) {   // se o estado está fechado
            btnVoltar.setDisable(true);
        }
       
;


        

    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> {   
            update();
        });



        btnAvancar.setOnAction(event -> {
            manager.avancaEstado();
        });
        btnVoltar.setOnAction(event -> {
            manager.volta();
        });
 
      
  

    }

    private void update() {
        if (manager.getState() != ApoioPoeState.ATRIBUICAO_PROPOSTAS) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

    }

}