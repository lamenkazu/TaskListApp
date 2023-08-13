# Task List App In Jetpack Compose

## O Jetpack Compose é realmente uma forma incrível de produzir aplicações.

Apesar de, 
em um primeiro momento, 
parecer que o torna o código mais verborrágico (-uma das principais reclamações ao java-),
ele organiza o código melhor, 
e você é forçado a utilizar um padrão de projetos para evitar que um arquivo fique muito grande.

Ele possui uma similaridade muito grande com o SwiftUI em questão de 
- como a linguagem deve ser utilizada;
- como o código deve ser escrito;
- em questão de estrutura do arquivo .kt;

Sua forma de usar e lembrar os Estados do sistema é mais similar ao React Native, 
apesar de ainda ter explorado pouco essa funcionalidade, 
não funciona da mesma forma que no Swift, mas causa o mesmo efeito de desejar um MVVM

O Jetpack Compose aproxima muito mais o desenvolvedor da sensação de produzir PARA o mobile.

## Task List
Esse projeto abordou mais o foco no desenvolvimento Front-End da aplicação e seus testes,
porém também foi implementado um Banco de Dados Local em SQLite para a aplicação,
a qual necessita de muito pouco espaço do dispositivo para armazenamento.

### Projeto
A Main Activity se tornou um tipo de Router para as telas do aplicativo.
Nela é declarada um navController, a qual devo dedicar mais estudos à esse 
componente que parece muito poderoso.

#### As Views 
foram criadas então para ficar o verdadeiro código das telas onde se implementa os componentes.

#### Apenas três componentes
foram criados para serem reutilizados, mas cada um aborda diferentes utilizações, vizando o estudo das funcionalidades do Compose.

#### Os modelos de dados
foram colocados na model, onde a Classe **Task** tem o modelo de uma Tarefa e 
**TaskDAO** possui o código SQL que lida com as operações no banco de dados.

## Melhorias por vir
- Ordenação da lista de afazeres por prioridade,
- Qualidade da interface do usuário
- Mais funcionalidades básicas para utilização mais moderna,
- Sistema de notificações,
- inserção de datas e/ou tempo limite para realização de tarefas (não obrigatorio),
- lista de tarefas realizadas em separado da lista de a realizar (nova tela) adjunto de botão de completar tarefa
- animações