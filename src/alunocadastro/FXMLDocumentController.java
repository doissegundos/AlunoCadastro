package alunocadastro;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField nomeTextField;
    @FXML
    private TextField sobrenomeTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField diaTextField;
    @FXML
    private TextField mesTextField;
    @FXML
    private TextField anoTextField;
    @FXML
    private TextField matriculaTextField;
    @FXML
    private ComboBox<String> cursoComboBox;
    @FXML
    private Button cadastarButton;
    @FXML
    private TextField nota2TextField;
    @FXML
    private TextField nota3TextField;
    @FXML
    private TextField nota1TextField;
    @FXML
    private Button anteriorButton;
    @FXML
    private Button proximoButton;
    @FXML
    private TextArea exibiçãoTextArea;
    @FXML
    private Button excluirButton;
    @FXML
    private TitledPane controleTItledPane;
    @FXML
    private Label tituloLabel;

    private List<String> listaDeCursos = new ArrayList<String>(); // nessa linha é criado um vetor que vai armazenar a lista dos cursos no ComboBox
    private ObservableList<String> observableListCursos; // nessa linha é criado um observablelist que vai armazenar o vetor arraylist
    private List<Aluno> dadosAluno = new ArrayList<Aluno>();//nessa linha criamos um arraylist de Alunos
    private int posiçãoVetor; // aqui criamos uma variavel que vai controlar a posição do arraylist
    private int maiorPosição; //essa variavel vai receber a maior posição do arraylist
    private int posiçãoVet;
    private String nome;
    private String sobrenome;
    private String email;
    private String dia;
    private String mes;
    private String ano;
    private int matricula;
    private float nota1;
    private float nota2;
    private float nota3;
    private String curso;
    private int idade = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxCurso(); // chamamos o metodo comboBoxCurso() para assim os cursos serem adicionados no combobox
        tituloLabel.setText(titulo());
    }

    public void comboBoxCurso() { // esse metodo vai adicionar os cursos ao combobox

        String cienciasEconomicas = "Ciências Econômicas";
        String engComputação = "Engenharia da Computação";
        String engEletrica = "Engenharia Elétrica";
        String finanças = "Finanças";
        String medicina = "Medicina";
        String musica = "Música";
        String odontologia = "Odontologia";
        String psicologia = "Psicologia";

        //adicionando objetos ao ArrayList
        listaDeCursos.add(cienciasEconomicas);
        listaDeCursos.add(engComputação);
        listaDeCursos.add(engEletrica);
        listaDeCursos.add(finanças);
        listaDeCursos.add(medicina);
        listaDeCursos.add(musica);
        listaDeCursos.add(odontologia);
        listaDeCursos.add(psicologia);

        observableListCursos = FXCollections.observableArrayList(listaDeCursos);

        cursoComboBox.setItems(observableListCursos);

    }

    @FXML
    public void cadastarAluno() { //esse metodo vai ser chamado pelo Button "cadastrar" e vai inserir os dados dos alunos no arraylist
        try { // utilizamos o controlador de exceções para se caso der algum erro, ou algum usuario inserir algum dado invalido
            posiçãoVet = dadosAluno.size();
            nome = nomeTextField.getText();
            sobrenome = sobrenomeTextField.getText();
            email = emailTextField.getText();
            dia = diaTextField.getText();
            mes = mesTextField.getText();
            ano = anoTextField.getText();
            matricula = Integer.parseInt(matriculaTextField.getText());
            nota1 = Float.parseFloat(nota1TextField.getText());
            nota2 = Float.parseFloat(nota2TextField.getText());
            nota3 = Float.parseFloat(nota3TextField.getText());
            curso = cursoComboBox.getSelectionModel().getSelectedItem();
            
            PosiçãoArrayList posição = new PosiçãoArrayList(posiçãoVet);
            Aluno aluno = new Aluno(curso, matricula, nota1, nota2, nota3, nome, sobrenome, email, dia, mes, ano);
            
            if(aluno.idade()==-1){
                idade = 1/0;
            }
            dadosAluno.add(aluno);
            tituloLabel.setText(titulo());
            posiçãoVetor = Integer.parseInt(posição.toString());
            exibiçãoTextArea.setText("pos: " + (posiçãoVetor + 1) + "\n" + dadosAluno.get(posiçãoVetor).toString());
            
            
            //aqui todos os campos de TextField ficam vazios
            nomeTextField.setText(null);
            sobrenomeTextField.setText(null);
            emailTextField.setText(null);
            diaTextField.setText(null);
            mesTextField.setText(null);
            anoTextField.setText(null);
            matriculaTextField.setText(null);
            nota1TextField.setText(null);
            nota2TextField.setText(null);
            nota3TextField.setText(null);       
            
        } catch (Exception erro) {
            dadosErrados();
        }
        
    }
    @FXML
    public void excluirbutton() { //esse metodo vai controlar o button "excluir" e vai ser chamado no action on
        try {
            dadosAluno.remove(posiçãoVetor);
            tituloLabel.setText(titulo());
            maiorPosição = dadosAluno.size() - 1;
            if (posiçãoVetor > 0) {
                posiçãoVetor--;
                exibiçãoTextArea.setText("pos: " + (posiçãoVetor + 1) + "\n" + dadosAluno.get(posiçãoVetor).toString());
            } else {
                try {
                    posiçãoVetor = maiorPosição;
                    exibiçãoTextArea.setText("pos: " + (posiçãoVetor + 1) + "\n" + dadosAluno.get(posiçãoVetor).toString());
                } catch (Exception erro) {
                    exibiçãoTextArea.setText(null);
                }

            }
        } catch (Exception erro) { //caso ocorra algum erro no try ele vai chamar o metodo lestavazia()
            listaVazia();
        }
    }

    @FXML
    public void proximoButton() { // esse metodo vai controlar o button "proximo"
        try {
            maiorPosição = dadosAluno.size() - 1;
            if (posiçãoVetor < maiorPosição) {
                posiçãoVetor++;
                exibiçãoTextArea.setText("pos: " + (posiçãoVetor + 1) + "\n" + dadosAluno.get(posiçãoVetor).toString());
            } else {
                posiçãoVetor = 0;
                exibiçãoTextArea.setText("pos: " + (posiçãoVetor + 1) + "\n" + dadosAluno.get(posiçãoVetor).toString());
            }
        } catch (Exception erro) {

        }
    }

    @FXML
    public void anteriorButtor() { //esse metodo vai controlar o button "anterior"
        try {
            maiorPosição = dadosAluno.size() - 1; // a variavel maiorPosição vai receber a maior posição do arraylist
            if (posiçãoVetor > 0) { //se a posição do arraylist for maior que zero, então ele vai entrar e subtrair um da posição do vetor e logo depois exibir o que tiver guardado nela
                posiçãoVetor--;
                exibiçãoTextArea.setText("pos: " + (posiçãoVetor + 1) + "\n" + dadosAluno.get(posiçãoVetor).toString());
            } else {
                posiçãoVetor = maiorPosição;
                exibiçãoTextArea.setText("pos: " + (posiçãoVetor + 1) + "\n" + dadosAluno.get(posiçãoVetor).toString());
            }

        } catch (Exception erro) {

        }
    }

    public void listaVazia() { //esse metodo vai abrir uma janela de erro
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Lista vazia");
        alert.showAndWait();
    }

    public void dadosErrados() { //esse metodo vai abrir uma janela de erro
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Dado(s) inválidos. Confira os dados fornecidos.");
        alert.showAndWait();
    }

    public String titulo() { //esse metodo retornar o numero de alunos cadastrados no sistema
        int pos = dadosAluno.size();
        return "Controle de alunos: " + (pos) + " alunos cadastrados";
    }

}
