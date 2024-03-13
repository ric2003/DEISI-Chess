## Diagrama UML
![](UML_DeisiChess.png?raw=true "Diagrama UML")

## Vídeo que demonstra o funcionamento do jogo:
https://www.youtube.com/watch?v=7RVynN1Koyo&ab_channel=DanielNascimento  

## Personalização:
![](imgNovo.png?raw=true "Personalização")
- O rei é o “Clash Royale King”.
- A rainha é a “Clash Royale Archer Queen”.
- O Pónei Mágico é o “Clash Royale Baby Dragon”.
- O Padre da Vila é o “Clash Royale Wizard”.
- A torre horizontal é o “Log do Clash Royale” na posição vertical.
- A torre vertical é o “Log do Clash Royale” na posição horizontal.
- O Homer é o “Homer Simpson”, que pode estar a dormir ou não, dependendo do estado da peça.
- O Joker alterna entre as imagens da rainha até ao Homer.  
- Por último, temos a nossa peça criativa chamada “Quantum Leaper”, que é o “Electro Wizard do Clash Royale”. O “Quantum Leaper” é uma peça que se move como um rei, mas se clicar duas vezes nela (ou seja, colocar as coordenadas de onde ela está e para onde está a tentar ir no mesmo lugar), ela irá teleportar-se para um lugar vazio no tabuleiro de forma aleatória. Devido à natureza aleatória dos teletransportes do “Quantum Leaper”, tivemos que adicionar uma maneira especial de guardar os movimentos desta peça para a funcionalidade de guardar e carregar o jogo. Além disso, a peça “Quantum Leaper” vale 10 pontos no jogo. No getHints também mostra a opção de selecionar a mesma coordenada para ir a uma coordenada aleatória para os jogadores saberem que e válido e ela teleporta.
  
As peças da equipa variam de mais escuras e mais claras, as mais claras são da equipa branca e as mais escuras são da equipa contrária.

Personalizei as cores dos quadrados e coloquei um iPad como fundo, para que pareça que o jogo está a ser jogado num dispositivo móvel, tal como seria o Clash Royale.


## Comentários sobre escolhas de modelação

Conforme solicitado pelo professor, completamos o projeto orientado a objetos em Java. Temos uma classe GameManager que possui todos os métodos solicitados, além de outras funções para auxiliar os métodos obrigatórios e para clareza do código. Dentro do GameManager, também temos uma variável board e chessGameRecorder que é usada para armazenar informações sobre o jogo.  

Em seguida, temos uma classe board que armazena todas as informações sobre o tabuleiro. O chessGameRecorder registra todos os tabuleiros criados ao longo dos movimentos e os próprios movimentos. Temos uma classe ChessPiece que é abstrata e tem como filhos todas as peças(Rei, Rainha, ect...). As variáveis que compartilham estão na classe abstrata chesspiece e não possuem variáveis únicas. Temos métodos em chesspiece que são abstratos para que possam ser modificados em cada peça, por exemplo, o método canMoveTo(Board chessboard, int x0, int y0, int x1, int y1) que indica se é um movimento válido ou não, dependendo da peça de xadrez.  

Temos a InvalidGameInputException que é uma exceção lançada quando o ficheiro a ser lido tem o formato errado. Temos ValidMovePoint que é uma classe que estende Comparable para poder ordenar as dicas dadas pelo método getHints() em gamemanager. Também temos um Statistics.kt que está em Kotlin e recebe um gamemanager e retorna estatísticas sobre o jogo sem usar ciclos. Por fim, temos uma classe chamada ImagePannel que contém uma foto do Shrek que dá zoom e tem o nosso nome dentro do nariz dele.




