package main_war;

public class guerreiro {
    public String nome;
    private int qntVidas;
    private bolsa myBolsa;

    public guerreiro() {
        this.myBolsa = new bolsa();
    }

    public void definirNome(String nome) {
        this.nome = nome;
    }

    public int sortearVidas() {
        qntVidas = (int) (Math.random() * 4) + 9; // entre 9 e 12
        InOut.MsgSemIcone("Sorteio", "Desta vez, você possuirá " + qntVidas
                + " vidas!");
        return qntVidas;
    }

    public void perderVidas() {
        this.qntVidas--;
    }

    // Pede misericordia ao oraculo (frase digitada pelo jogador)
    public String vidaExtra() {
        return InOut.leString("Suas vidas acabaram! Implore por misericórdia ao Oráculo\n"
                + "(escreva uma frase com mais de 5 palavras):");
    }

    public int getQntVidas() {
        return qntVidas;
    }

    public void setQntVidas(int qntVidas) {
        this.qntVidas = qntVidas;
    }

    public String getNome() {
        return this.nome;
    }

    public bolsa getMyBolsa() {
        return myBolsa;
    }
}
