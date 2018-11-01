# AIAD-FEUP

## Index

1. [Descrição](#1-descrição)
2. [Estratégias](#2-estratégias)
     * Estratégia 1
     * Estratégia 2
3. [Comunicação](#3-comunicação)
     * Mensagens
     
           *  Handshake
           * Order
           * Proposal
           * Decision
           * Alive
     * Handlers
           - ...
4. [Developers](#4-developers)
     		 

## 1. Descrição
Pretende-se implementar um sistema de distribuição de correio no qual vários carteiros negociam com o posto de correios que correspondência vão entregar, tendo estes um limite de correspondência que podem transportar. Os carteiros recebem um valor por cada entrega feita. Assim os Carteiros tentam receber o máximo possível, tentando minimizar as despesas em viagens a locais mais distantes. O Posto de Correio pretende que as entregas sejam feitas com a menor possibilidade de atraso possível e com o menor preço possível. Há portanto carteiros que irão assegurar uma probabilidade maior de entrega sem atrasos do que outros e carteiros que por serem mais competentes irão cobrar mais ao Posto de Correios.

## 2. Estratégias

Numa primeira fase os carteiros fazem uma oferta fixa, por exemplo, cada serviço custa 5 unidades monetárias e o carteiro tem uma capacidade máxima de 10 serviços.

Numa segunda fase pretende-se que os carteiros sejam mais competitivos. Logo, incluiremos outras variáveis como o tempo que demoram a ir buscar e entregar um pacote, um preço por km, entre outros. Tudo isto começa com um valor fixo, por exemplo, 1 unidade monetária por km.

Um outro exemplo seria um carteiro que aceitou um serviço e agora só aceitará as entregas que ficarem a x distância do primeiro serviço.

Uma outra opção seria um carteiro que já aceitou um serviço e agora para um novo serviço na mesma zona, apresentará um preço mais baixo tornando-se mais competitivo.


## 3. Comunicação


Postman                   	|       |  PostOffice
:-------------------------:|:----:|:-------------------------:|
[Subscribe] *Handshake* | > | Guarda ID e nome do sender |
Guarda Posição do PostOffice | < | [Confirm] *Handshake* |
Espera uma order |   | Gera uma order |
Calcula a sua oferta |  < | [Propagate] *Order* |
[Propose] Proposal | > | Guarda o proposta |
Aguarda resposta |   | Calcula a melhor proposta |
Analisa resposta | < | [ACCEPT\_PROPOSAL] *Decision* <br> ou <br> [REJECT\_PROPOSAL] * Decision* |
...|   |  ...
A cada segundo | < | [QUERY_IF] *Alive* |
[Confirm] *Alive* | > | Verifica a atividade dos postmen 



### 3.1 Mensagens

Enviar mensagem(exemplo):

```java
	send(new HandshakeMessage(name, postOffice).toACL());
```

Handler mensagem:

```java
	ACLMessage reply = Handler.parse(msg, instance);
```
#### 3.1.1. Handshake
##### 3.1.1.1 Handshake(PostMan)
```javascript
	ACLMessage{
		performative: subscribe;
		sender: PostMan;
		receiver: Postoffice;
		ontology: Handshake;
		reply-with: Handshake;
		content: null
	}
```

##### 3.1.1.2 Handshake(PostOffice)
```javascript
	ACLMessage{
		performative: confirm;
		sender: PostOffice;
		receiver: PostMan;
		ontology: Handshake;
		reply-with: null;
		content: position-x position-y
	}
```

#### 3.1.2. Order
```javascript
	ACLMessage{
		performative: propagate;
		sender: PostOffice;
		receiver: PostMen;
		ontology: Order;
		reply-with: Proposal;
		content: position-x position-y estimated-time
	}
```

#### 3.1.3. Proposal
```javascript
	ACLMessage{
		performative: propose;
		sender: PostMan;
		receiver: PostOffice;
		ontology: Proposal;
		reply-with: Decision;
		content: position-x position-y estimated-time
	}
```

#### 3.1.4. Decision
##### 3.1.4.1 Decision (accept)
```javascript
	ACLMessage{
		performative: accept_proposal;
		sender: PostOffice;
		receiver: PostMan;
		ontology: Decision;
		reply-with: null;
		content: null
	}
```

##### 3.1.4.2 Decision (reject)
```javascript
	ACLMessage{
		performative: reject_proposal;
		sender: PostOffice;
		receiver: PostMan;
		ontology: Decision;
		reply-with: null;
		content: null
	}
```

#### 3.1.5. Alive
##### 3.1.5.1 Alive (PostOffice)
```javascript
	ACLMessage{
		performative: query_if;
		sender: PostOffice;
		receiver: PostMen;
		ontology: Alive;
		reply-with: Alive;
		content: null
	}
```

##### 3.1.5.2 Alive (PostMan)
```javascript
	ACLMessage{
		performative: confirm;
		sender: PostMan;
		receiver: PostOffice;
		ontology: Alive;
		reply-with: null;
		content: null
	}
```

### 3.2 Handlers

## 4. Developers
- David Falcão [@davidrsfalcao](https://github.com/davidrsfalcao)
- Pedro Miranda [@pmiranda07](https://github.com/pmiranda07)
- Verónica Fradique [@veronicafradique](https://github.com/veronicafradique)
