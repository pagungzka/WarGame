package main_war;

public class oraculo {
    private String nome;
    private int numSecreto;
    private guerreiro warrior;
    private boolean deuVidaExtra = false;

    public oraculo(guerreiro warrior) {
        this.warrior = warrior;
        numSecreto = 1 + (int) (Math.random() * 100);
    }

    public void definirNome(String nome) {
        this.nome = nome;
    }

    public String prologoIntroducao() {
        return "Ouça, mortal! Do alto do Monte Olimpo, entre relâmpagos de Zeus "
                + "e o mar revolto de Poseidon, eu falo por vontade divina.\n\n"
                + "Sou " + this.nome + ", Oráculo dos Doze, voz dos deuses entre os homens.\n"
                + "Diante de mim se ajoelha " + warrior.getNome()
                + ", escolhido pelo próprio Olimpo para provar seu valor.\n\n"
                + "Três provas separam-te da glória eterna. Terás coragem para "
                + "incumbir-se destes desafios?\n\n"
                + "Que os deuses tenham piedade... a Jornada do Olimpo começa agora!";
    }

    // Mais de 5 palavras = o oraculo se compadece e concede UMA vida extra
    public boolean decidirVidaExtra(String frase) {
        if (frase == null || frase.trim().isEmpty()) {
            return false;
        }
        boolean aceitou = frase.trim().split("\\s+").length > 5;
        if (aceitou) {
            deuVidaExtra = true;
        }
        return aceitou;
    }

    public String RelatorioFimGame() {
        return "Guerreiro: " + warrior.getNome()
                + "\nVidas restantes: " + warrior.getQntVidas()
                + "\nAjudas escolhidas: " + warrior.getMyBolsa().contarEquipados()
                + "\nVida extra concedida: " + (deuVidaExtra ? "sim" : "não");
    }

    public void prologoPerdedor() {
        InOut.MsgSemIcone(this.nome, "O trovão de Zeus silencia... as Moiras cortam o último fio"
                + " com suas tesouras de bronze.\n" + warrior.getNome() + ", lutaste com a bravura de"
                + " um mortal comum, mas os desígnios do Olimpo eram outros...");
        InOut.MsgSemIcone(this.nome, "Eu, " + this.nome + ", levarei tua história ao Hades com honra,"
                + " onde descansarás entre os guerreiros esquecidos.\n"
                + "Talvez, num outro ciclo, os deuses te concedam nova chance.");
        InOut.MsgDeErro("Fim", "Fim de jogo!");
    }

    public void prologoVencedor() {
        InOut.MsgComIcone(this.nome, "Os céus se abrem! Zeus ergue seu raio em saudação,"
                + " e um coro de louvores ecoa pelo Olimpo!\n" + warrior.getNome()
                + ", tu passaste por todas as provações com absoluto êxito.", "/recursos/oraculo.png");
        InOut.MsgDeInformacao(this.nome, "Restam " + warrior.getQntVidas() + " vidas em teu corpo mortal,"
                + " marca de um herói verdadeiro.");
        InOut.MsgDeInformacao(this.nome, "Eu, " + this.nome + ", por vontade dos Doze, te elevo ao posto de"
                + " Semideus, digno de sentar-se ao banquete do Olimpo.\n"
                + "Que teu nome seja gravado entre as constelações!");
    }

    public boolean loadLevel01(guerreiro warrior) {
        
        int palpite = InOut.leInt("Diga o seu palpite:");
        
        if (palpite == numSecreto){
            InOut.MsgDeInformacao("Parabens", "Tal desafio não foi nada perante a tua astucia!");
            return true;       
        }
        else{
            if (palpite > numSecreto){
                InOut.MsgDeInformacao("Incorreto", "O algarismo de apollo e menor que sua "
                        + "tola aposta.\n Vidas restantes: " + warrior.getQntVidas());
            }
            else{
                InOut.MsgDeInformacao("Incorreto", "O algarismo de apollo e maior que sua "
                        + "tola aposta.\n Vidas restantes: " + warrior.getQntVidas());
                
            }
            return false;
        }
    }
    
    public boolean loadLevel02(guerreiro warrior){
         InOut.MsgDeInformacao("LEVEL 2:"," Hora da charada de ATENAS! \n"
                 + "Responda as charadas corretamente");
         boolean acertouResposta01=false;
          boolean acertouResposta02=false;
           boolean acertouResposta03=false;
         String resposta01;
         String resposta02;
         String resposta03;
         while(!acertouResposta01){
              resposta01 = InOut.leString("Charada 1 de 3:\n É mais leve que uma pluma,\n mas nem o"
                      + " homem mais forte do universo "
                     + "é capaz de segurá-la por mais de 5 minutos\n, o que é?\n"
                      +"numero de vidas: "+ warrior.getQntVidas());
           if (resposta01.equalsIgnoreCase("Respiracao")){
                acertouResposta01=true;
                InOut.MsgDeInformacao("Acertou", "Resposta correta ");
              }else {
                  warrior.perderVidas();
                   InOut.MsgDeInformacao("errou ", "atenas está decepcionada!\n" + "quantidade de vidas restante: "+warrior.getQntVidas());
                     if (warrior.getQntVidas()<=0){
                     return false;
                     }
                   }
         
         }
         while(!acertouResposta02){
         resposta02 = InOut.leString("Charada 2 de 3: \nO que é que tem cidades,mas não tem casas,\n tem montanhas, mas não tem árvores\n"
                 + " tem água, mas não tem peixe? "+"numero de vidas: "+warrior.getQntVidas());
             if(resposta02.equalsIgnoreCase("mapa")){
                 acertouResposta02=true;
                 InOut.MsgDeInformacao("Acertou", "Resposta correta ");
             } else {
                warrior.perderVidas();
                InOut.MsgDeInformacao("errou ", "atenas está decepcionada!\n "+" quantidade de vidas restante: "+warrior.getQntVidas());
                 if(warrior.getQntVidas()<=0){
                 return false;
                 }
             }
         
         }
         while(!acertouResposta03){
             resposta03=InOut.leString("Charada 3 de 3:\n Qual o nome da fruta que tem sete letras, mas tirando cinco fica onze?\n"+"numero de vidas: "+warrior.getQntVidas());
             if(resposta03.equalsIgnoreCase("abacaXI")){
                 acertouResposta03=true;
                   InOut.MsgDeInformacao("Magnifico", "Tal sabedoria é fora da compreemsão!");
             }else{
                warrior.perderVidas();
                InOut.MsgDeInformacao("errou ", "atenas está decepcionada!\n "+" quantidade de vidas restante: "+warrior.getQntVidas());
                if(warrior.getQntVidas()<=0){
                return false;
                }
             }
         }
         
    return true; 
    }
    
    public boolean loadLevel03(guerreiro warrior) {
        String[] jokenpo = {"Pedra", "Papel", "Tesoura"};
        InOut.MsgDeInformacao("LEVEL 3",
                "Minigame final: Pedra, Papel ou Tesoura contra o Oraculo!Venca ao menos 2 das 3 rodadas.");

        int vitoriasGuerreiro = 0;
        for (int rodada = 1; rodada <= 3; rodada++) {
            if (warrior.getQntVidas() <= 0) {
                return false;
            }

            String respostaGuerreiro = InOut.leString("Rodada " + rodada + " - Escolha: PEDRA, PAPEL ou TESOURA"
                    + "(Vidas: " + warrior.getQntVidas() + ")");
            int escolhaOraculo = (int) (Math.random() * 3);

            int validarRespostaGuerreiro = -14;
            for (int k = 0; k < jokenpo.length; k++) {
                if (jokenpo[k].equalsIgnoreCase(respostaGuerreiro)) {
                    validarRespostaGuerreiro = k;
                }
            }

            if (validarRespostaGuerreiro == -14) {
                InOut.MsgDeErro("Invalido", "Escolha invalida! O Oraculo vence essa rodada por padrao.");
                warrior.perderVidas();
                continue;
            }

            InOut.MsgSemIcone("Resultado da rodada",
                    "Voce: " + respostaGuerreiro + " |  Oraculo: " + jokenpo[escolhaOraculo]);

            if (validarRespostaGuerreiro == escolhaOraculo) {
                InOut.MsgSemIcone("Empate", "Rodada empatada! Ninguem perde vida.");
            } else if ((validarRespostaGuerreiro == 0 && escolhaOraculo == 2)
                    || (validarRespostaGuerreiro == 1 && escolhaOraculo == 0)
                    || (validarRespostaGuerreiro == 2 && escolhaOraculo == 1)) {
                vitoriasGuerreiro++;
                InOut.MsgDeInformacao("Voce venceu!", "Voce venceu essa rodada!");
            } else {
                warrior.perderVidas();
                InOut.MsgDeAviso("Oraculo venceu", "O Oraculo venceu essa rodada! Voce perdeu uma vida.");
            }
        }
        return vitoriasGuerreiro >= 2 && warrior.getQntVidas() > 0;
    }
    
    
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public guerreiro getWarrior() {
        return warrior;
    }
}
