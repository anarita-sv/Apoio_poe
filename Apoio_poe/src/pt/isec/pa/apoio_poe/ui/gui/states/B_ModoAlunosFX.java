package pt.isec.pa.apoio_poe.ui.gui.states;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import pt.isec.pa.apoio_poe.model.ModelManager;
import pt.isec.pa.apoio_poe.model.data.Aluno;
import pt.isec.pa.apoio_poe.model.fsm.states.ApoioPoeState;
import pt.isec.pa.apoio_poe.ui.gui.InputWindow;

public class B_ModoAlunosFX extends BorderPane {
    ModelManager manager;

    Button btnImportar, btnExportar;
    Button btnVoltar, btnAvancar;

    VBox vboxOpcoes;
    VBox vboxTable;

    public B_ModoAlunosFX(ModelManager manager) {
        this.manager = manager;

        createViews();
        registerHandlers();
        update();

    }

    private void createViews() {
        btnImportar = new Button("Importar Lista de Alunos");
        btnImportar.setMinSize(150, 60);

        btnExportar = new Button("Exportar Lista Alunos");
        btnExportar.setMinSize(150, 60);

        btnAvancar = new Button("Avançar");
        btnAvancar.setMinSize(150, 60);

        btnVoltar = new Button("Voltar");
        btnVoltar.setMinSize(150, 60);

        // TO DO : Desencostar os botoes da borda direita um bocadinho
        vboxOpcoes = new VBox();
        vboxOpcoes.getChildren().addAll(btnImportar, btnExportar, btnAvancar, btnVoltar);
        this.setRight(vboxOpcoes);
        vboxOpcoes.setAlignment(Pos.CENTER);

        // TO DO : Lista Editável do lado esquerdo  
     

      //  vboxTable.getChildren().addAll(table);

    }

    private void registerHandlers() {
        manager.addPropertyChangeListener(evt -> {
            
            update();
        });

        btnImportar.setOnAction(event -> {
            String str = InputWindow.display("Importar Lista de Alunos", "Insira o nome do ficheiro a importar: ");
            System.out.println("nome do ficheiro importar " + str);
            manager.importaCVS(str);
        });

        btnExportar.setOnAction(event -> {
            String str = InputWindow.display("Ixportar Lista de Alunos",
                    "Insira o nome do ficheiro para exportação:  ");
            System.out.println("nome ficheiro exportar " + str);
            manager.exportaCVS(str, manager.listaAlunos());
        });

        btnAvancar.setOnAction(event -> {
            manager.avancaEstado();
        });
        btnVoltar.setOnAction(event -> {
            manager.volta();
        });

    }

    private void update() {
        if (manager.getState() != ApoioPoeState.MODO_ALUNOS) {
            this.setVisible(false);
            return;
        }
        this.setVisible(true);

    }






    
}
