# AIAD-FEUP

## Notas para o desenvolvimento

call for purposals

Numa primeira fase os carteiros fazem uma oferta fixa, por exemplo, cada serviço custa 5 unidades monetárias e o carteiro tem uma capacidade máxima de 10 serviços.

Numa segunda fase pretende-se que os carteiros sejam mais competitivos. Logo, incluiremos outras variáveis como o tempo que demoram a ir buscar e entregar um pacote, um preço por km, entre outros. Tudo isto começa com um valor fixo, por exemplo, 1 unidade monetária por km.

Um outro exemplo seria um carteiro que aceitou um serviço e agora só aceitará as entregas que ficarem a x distância do primeiro serviço. 

Uma outra opção seria um carteiro que já aceitou um serviço e agora para um novo serviço na mesma zona, apresentará um preço mais baixo tornando-se mais competitivo.


## Comunicação


Postman                   	|       |  PostOffice
:-------------------------:|:----:|:-------------------------:|
[Subscribe] *Handshake* | > | Guarda ID e nome do sender |
Guarda Posição do PostOffice | < | [Confirm] *Handshake* |
Espera uma order |   | Gera uma order |
Calcula a sua oferta |  < | [Inform] *Order* |
[Propose] Proposal | > | Guarda o proposta |
Aguarda resposta |   | Calcula a melhor proposta |
Analisa resposta | < | [ACCEPT\_PROPOSAL] *Proposal* <br> ou <br> [REJECT\_PROPOSAL] *Proposal*