# Agenda Salão 🌟

Este projeto foi desenvolvido utilizando o poderoso framework Spring, aproveitando diversas de suas funcionalidades para proporcionar um sistema de agendamento completo e eficiente para salões de beleza.

## Tecnologias Utilizadas 🛠️
- Spring Data: Para integração com o banco de dados MySQL e gerenciamento de entidades.
- Spring Validation: Utilizado para validar os dados de entrada e garantir a consistência dos agendamentos.
- Spring Web: Para construção da API RESTful e comunicação com os clientes.

## Banco de Dados 📊
O banco de dados utilizado é o MySQL, escolhido pela sua confiabilidade e ampla adoção na comunidade.

## Design de Software 🎨
Foi adotado o padrão de design Strategy para realizar as validações necessárias durante o processo de agendamento. Isso garante que as restrições de horário, dia e expediente do salão sejam devidamente respeitadas, proporcionando uma experiência segura e satisfatória para os usuários.

## Query Methods do Spring Data 📝
Para simplificar a criação de consultas personalizadas, utilizamos os Query Methods disponibilizados pelo Spring Data. Essa funcionalidade nos permite definir métodos nos repositórios usando uma convenção de nomenclatura específica, facilitando a interação com o banco de dados sem a necessidade de escrever consultas JPQL ou SQL manualmente.

## Documentação da API com Swagger 📑

A documentação da API foi elaborada utilizando o Swagger, uma ferramenta poderosa para descrever, consumir e visualizar APIs RESTful. Com o Swagger, é possível fornecer uma documentação interativa e amigável para os desenvolvedores, facilitando a compreensão dos endpoints, parâmetros e respostas da API.

Para acessar a documentação da API, basta iniciar o servidor e acessar o endpoint este endpotin ```http://localhost:8080/swagger-ui/index.html#/'```



