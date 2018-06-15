package alunocadastro;
public class PosiçãoArrayList { //criamos essa classe para controlar a posição do arraylist
    private int posição;

    public PosiçãoArrayList(int posição) {
        this.posição = posição;
    }

    public int getPosição() {
        return posição ;
    }

    public void setPosição(int posição) {
        this.posição = posição;
    }

    @Override
    public String toString() { 
        return Integer.toString(posição);
    }
    
    
}

