package alunocadastro;

public class Aluno extends Pessoa {

    private String curso;
    private int numMatricula;
    private float nota1;
    private float nota2;
    private float nota3;

    public Aluno(String curso, int numMatricula, float nota1, float nota2, float nota3, String nome, String Sobrenome, String email, String dia, String mes, String ano) {
        super(nome, Sobrenome, email, dia, mes, ano);
        this.curso = curso;
        this.numMatricula = numMatricula;
        this.nota1 = nota1;
        this.nota2 = nota2;
        this.nota3 = nota3;

    }

    public int getNumMatricula() {
        return numMatricula;
    }

    public void setNumMatricula(int numMatricula) {
        this.numMatricula = numMatricula;
    }

    public float getNota1() {
        return nota1;
    }

    public void setNota1(float nota1) {
        this.nota1 = nota1;
    }

    public float getNota2() {
        return nota2;
    }

    public void setNota2(float nota2) {
        this.nota2 = nota2;
    }

    public float getNota3() {
        return nota3;
    }

    public void setNota3(float nota3) {
        this.nota3 = nota3;
    }

    public float media() {
        float media = (nota1 + nota2 + nota3) / 3;
        return media;
    }

    public String situação() { ///esse metodo vai retornar a situação do aluno
        String situação;
        if (media() >= 7) {
            situação = "APROVADO POR MÉDIA";
        } else {
            if (media() >= 4) {
                situação = "NA AF";
            } else {
                situação = "REPROVADO";
            }
        }
        return situação;
    }

    @Override
    public String toString() { //o toString vai retornar todos os dados dos alunos
        return "Nome: " + nomeCompleto() + "\n" 
                + "E-mail: " + getEmail() + "\n"
                + "Idade: " + idade() + "\n"
                + "Data de nascimento: " + dataDeNascimento() + "\n"
                + "Curso: " + curso + "\n"
                + "Matricula: " + numMatricula + "\n"
                + "Notas: " + nota1 + "," + nota2 + "," + nota3 + "\n"
                + "Média: " + media() + "\n"
                + "Situação: " + situação();

    }

}
