# Sintaxe Básica

## Palavras Reservadas

### Controle de pacotes

__import:__ importa pacotes ou classes

__package:__ especifica a qual pacote a classe pertence

### Modificadores de acesso

__public:__ declara uma visibilidade pública (pode ser acessada de qualquer classe)

__protected:__ declara uma visibilidade protegida (pode ser acessada por classes do mesmo pacote ou subclasses)

__private:__ declara uma visibilidade privada (pode ser acessada apenas dentro da classe)

### Tipos primitivos

__boolean, byte, char, double, float, int, long, short, void__

### Modificadores de classes, variáveis ou métodos

__abstract:__ classe que não pode ser instanciada ou método que precisa ser implementado por uma subclasse não abstrata.

__class:__ especifica a classe.

__extends:__ indica a superclasse que a subclasse está estendendo.

__final:__ impossibilita que uma classe seja estendida, que uma método seja sobrescrito ou que uma variável seja reinicializada.

__implements:__ indica as interfaces que uma classe irá implementar.

__interface:__ especifica a interface.

__native:__ indica que um método está escrito em uma linguagem dependente da plataforma, como o C.

__new:__ instancia um novo objeto, chamando seu construtor.

__static:__ declara um método ou variável como pertencente da classe ao invés dos objetos.

__strictfp:__ usado em frente a um método ou classe para indicar que os números de ponto flutuante seguirão as regras de ponto flutuante em todas as expressões.

__synchronized:__ indica que um método só pode ser acessado por uma thread de cada vez. 

__transient:__ impede que uma variável seja salva.

__volatile:__ indica que uma variável pode ser alterada durante o uso de threads.


### Controle de fluxo dentro de um bloco de código

__break:__ sai do bloco de código que ele está.

__case:__ executa um bloco de código dependendo do teste do switch.

__continue:__ pula a execução do código que viria após essa linha e vai para a próxima passagem do loop.

__default:__ executa esse bloco de código caso nenhum dos teste de switch-case seja verdadeiro.

__do:__ executa um bloco de código uma vez, e então realiza um teste em conjunto com o __while__ para determinar se o bloco deverá ser executado novamente.

__else:__ executa um bloco de código alternativo caso o teste __if__ seja falso.

__for:__ usado para realizar um loop condicional de um bloco de código.

__if:__ usado para realizar um teste lógico de verdadeiro ou falso;

__instanceof:__ determina se um objeto é uma instância de determinada classe, superclasse ou interface.

__return:__ retorna um método sem executar qualquer código, que venha depois desta linha (também pode retornar uma variável).

__switch:__ indica a variável a ser comparada nas expressões __case__.

__while:__ executa um bloco de código repetidamente enquanto a condição for verdadeira.

### Tratamento de Erros

__assert:__ testa uma expressão condicional, para verificar uma suposição do programador.

__catch:__ declara o bloco de código usado para tratar uma exceção.

__finally:__ bloco de código, após um try-catch, que é executado independentemente do fluxo de programa seguido ao lidar com uma exceção.

__throw:__ usado para passar uma exceção para o método que o chamou.

__throws:__ indica que um método pode passar uma exceção para o método que o chamou.

__try:__ bloco de código que tentará ser executado, mas que pode causar uma exceção.

### Variáveis de Referência

__super:__ refere-se a superclasse imediata.
__this:__ refere-se a instância atual do objeto.

### Palavras reservadas não utilizadas

__const e goto.__

### Literais Reservados

__null, true e false.__
