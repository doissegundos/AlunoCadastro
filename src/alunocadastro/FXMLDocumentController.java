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

    ///aqui são declaradas as caixas de textos, butões, comboBox que foram criadas no javaFX
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

    private List<String> listaDeCursos = new ArrayList<String>(); /// nessa linha é criado um vetor que vai armazenar a lista dos cursos no ComboBox
    private ObservableList<String> observableListCursos; /// nessa linha é criado um observablelist que vai armazenar o vetor arraylist
    private List<Aluno> dadosAluno = new ArrayList<Aluno>();///nessa linha criamos um arraylist de Alunos
    private int posiçãoVetor; /// aqui criamos uma variavel que vai controlar a posição do arraylist de Aluno
    private int maiorPosição; ///essa variavel vai receber a maior posição do arraylist de Aluno
    private int posiçãoVet;
    private String nome; ///essa variavel vai receber a String do nomeTextFiel
    private String sobrenome; ///essa variavel vai receber a String do sobrenomeTextFiel
    private String email; ///essa variavel vai receber a String do emailTextFiel
    private String dia; ///essa variavel vai receber a String do diaTextFiel
    private String mes; ///essa variavel vai receber a String do mesTextFiel
    private String ano; ///essa variavel vai receber a String do anoTextFiel
    private int matricula; ///essa variavel vai receber a String do matriculaTextFiel
    private float nota1; ///essa variavel vai receber a String do nota1TextFiel
    private float nota2; ///essa variavel vai receber a String do nota2TextFiel
    private float nota3; ///essa variavel vai receber a String do nota3TextFiel
    private String curso; /// essa variavel vai receber a string que foi selecionada no comboBox
    private int idadeVeridica = 0; ///essa variavel vai fazer um teste para saber se a variavel é verdadeira ou não

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxCurso(); // chamamos o metodo comboBoxCurso() para assim os cursos serem adicionados no combobox
        tituloLabel.setText(titulo()); ///aqui o texto do Label é alterado de acordo com o retorno no metodo titulo()
    }

    public void comboBoxCurso() { // esse metodo vai adicionar os cursos ao combobox

        ///primeiramente cada variavel vai receber uma string
        String cienciasEconomicas = "Ciências Econômicas";
        String engComputação = "Engenharia da Computação";
        String engEletrica = "Engenharia Elétrica";
        String finanças = "Finanças";
        String medicina = "Medicina";
        String musica = "Música";
        String odontologia = "Odontologia";
        String psicologia = "Psicologia";

        ///logo depois cada uma é adicionada no ArrayList
        listaDeCursos.add(cienciasEconomicas);
        listaDeCursos.add(engComputação);
        listaDeCursos.add(engEletrica);
        listaDeCursos.add(finanças);
        listaDeCursos.add(medicina);
        listaDeCursos.add(musica);
        listaDeCursos.add(odontologia);
        listaDeCursos.add(psicologia);

        ///agora listaDeCursos é adionada em uma observablelist
        observableListCursos = FXCollections.observableArrayList(listaDeCursos);

        ///os itens do comboBox são alterados para a lista da observablelist
        cursoComboBox.setItems(observableListCursos);

    }

    @FXML
    public void cadastarAluno() { //esse metodo vai ser chamado pelo Button "cadastrar" e vai inserir os dados dos alunos no arraylist
        try { // utilizamos o controlador de exceções para se caso der algum erro podermos tratar essas exceções

            ///na declaração dessas variaveis tem a explicação de cada uma delas
            posiçãoVet = dadosAluno.size(); ///posição do vetor está recebendo o valor da posição do vetor
            nome = nomeTextField.getText();
            sobrenome = sobrenomeTextField.getText();
            email = emailTextField.getText();
            dia = diaTextField.getText();
            mes = mesTextField.getText();
            ano = anoTextField.getText();
            matricula = Integer.parseInt(matriculaTextField.getText()); ///nesse caso, como matricula é um numero inteiro, e o que tem no matriculaTextField é uma string, então utilizamos Integer.parseInt para converter de string para inteiro
            nota1 = Float.parseFloat(nota1TextField.getText()); ///nesse caso, está sendo convertido de string para float
            nota2 = Float.parseFloat(nota2TextField.getText());
            nota3 = Float.parseFloat(nota3TextField.getText());
            curso = cursoComboBox.getSelectionModel().getSelectedItem(); /// aqui curso recebe a string que foi selecionada no comboBox

            PosiçãoArrayList posição = new PosiçãoArrayList(posiçãoVet); ///aqui é criado um objeto do tipo PosiçãoArrayList que vai ajudar a controlar a posição do arraylist de alunos
            Aluno aluno = new Aluno(curso, matricula, nota1, nota2, nota3, nome, sobrenome, email, dia, mes, ano); /// aqui a classe Aluno vai receber todos os dados de aluno

            if (aluno.idade() == -1) { /// na classe Pessoa tem um metodo chamado idade(), se esse metodo retornar -1, então a data informada no cadastro é invalida, logo o programa dará erro e terá um tratamento de exceção
                idadeVeridica = 1 / 0;
            }
            dadosAluno.add(aluno); ///aqui os dados são inserido no arraylist dadosAluno
            tituloLabel.setText(titulo()); ///aqui o texto do Label é alterado de acordo com o retorno no metodo titulo()
            posiçãoVetor = Integer.parseInt(posição.toString()); ///posição do vetor vai receber a posição retornada pelo toString da classe PosiçãoArrayList
            exibiçãoTextArea.setText("pos: " + (posiçãoVetor + 1) + "\n" + dadosAluno.get(posiçãoVetor).toString());  /// A textArea vai exibir o a string retornada pelo toString da classe Aluno          

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

        } catch (Exception erro) { /// caso ocorra algum erro dentro do try, então ele vai chamar o metodo dadosErrados que exibe uma mensagem dizendo que os dados estão errados
            dadosErrados();
        }

    }

    @FXML
    public void excluirbutton() { //esse metodo vai controlar o button "excluir" e vai ser chamado no action on
        ///utilizamos try e catch para fazer o controle de exceções
        try {
            dadosAluno.remove(posiçãoVetor); //nessa linha el exclui os dados que estão na "posiçãoVetor" do arraylist dadosAluno
            tituloLabel.setText(titulo());
            maiorPosição = dadosAluno.size() - 1; ///"maiorPosição" recebe o valor da maior posição do arraylist dadosAluno

            ///aqui ele vai fazer o controle da exclusão dos dados
            if (posiçãoVetor > 0) {
                posiçãoVetor--;
                exibiçãoTextArea.setText("pos: " + (posiçãoVetor + 1) + "\n" + dadosAluno.get(posiçãoVetor).toString());
            } else {
                ///vamos utilizar o try para controlar uma exceção, ou seja, caso não tenha mais nenhum dado cadastrado depois da exclusão de uma posição de dadosAluno, então a textArea vai receber um valor vazio, ou seja, não vai exibir nada
                try {
                    posiçãoVetor = maiorPosição;
                    exibiçãoTextArea.setText("pos: " + (posiçãoVetor + 1) + "\n" + dadosAluno.get(posiçãoVetor).toString());
                } catch (Exception erro) {
                    exibiçãoTextArea.setText(null); ///textArea recebendo um valor vazio
                }

            }
        } catch (Exception erro) { //caso ocorra algum erro no try ele vai chamar o metodo listavazia() que diz que o arraylist está vazio, ou seja, que não existe dados cadastrados, portando não se pode excluir nada
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
        } catch (Exception erro) { ///caso  aconteça algum erro no botão proximo, então ele não vai fazer nada

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

