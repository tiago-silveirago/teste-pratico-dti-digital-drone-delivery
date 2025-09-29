# 📘 Sobre o Projeto

Este projeto é uma API de entrega de pedidos utilizando drones, desenvolvida como um teste prático para a DTI Digital. O sistema permite o cadastro de drones, pedidos e realiza o gerenciamento automatizado das entregas, priorizando pedidos conforme regras de negócio. O backend é construído com Java 21, Spring Boot 3, MongoDB e utiliza agendamento de tarefas para processar entregas periodicamente.

---

# 🚀 Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   ```
2. Instale as dependências do projeto (necessário Java 21 e Maven):
   ```bash
   mvn clean install
   ```
3. Execute o backend:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Suba o banco de dados MongoDB com o seguinte comando Docker:
   ```bash
   docker run --name mongodb -d -p 27017:27017 mongo
   ```

A aplicação estará disponível em `http://localhost:8080`.

---

# 📖 Documentação da API

A documentação completa da API está disponível no arquivo OpenAPI 3.0.3:

- **Swagger/OpenAPI**: `api-documentation.yml`

Você pode visualizar a documentação interativa usando:
- [Swagger Editor](https://editor.swagger.io/) - Cole o conteúdo do arquivo `api-documentation.yml`
- O arquivo se encontra na pasta /resources/swagger
- Qualquer ferramenta compatível com OpenAPI 3.0.3

---

# 💡 Oportunidades de Melhoria

- Melhorar a cobertura de logs e tratamento de erros.
- Implementar autenticação e autorização.
- Otimizar jobs criados para criação de processo de entregas
- Adicionar funcionaliades referente a bateria dos drones
- Otimização da rota de entrega
- Criação de relatórios mais completos
- Criação de exceções personalizadas
- Implementação de filas para uma arquitetura orientada a eventos
- Criação de feedbacks para clientes

---

# 📚 Endpoints da API

## Pedidos

- **Criar pedido**
  - `POST /v1/pedidos`
  - Exemplo de body:
    ```json
    {
      "cliente": "João Silva",
      "localizacaoDestino": [ -19.9208, -43.9378 ],
      "pesoPacote": 2.5,
      "nivelPrioridade": "ALTA"
    }
    ```

- **Buscar pedido por ID**
  - `GET /v1/pedidos/{id}`

- **Listar todos os pedidos**
  - `GET /v1/pedidos`

## Drones

- **Criar drone**
  - `POST /v1/drones`
  - Exemplo de body:
    ```json
    {
      "nome": "Drone XPTO",
      "capacidadePeso": 10.0,
      "capacidadeDeslocamento": 30.0
    }
    ```

- **Buscar drone por ID**
  - `GET /v1/drones/{id}`

- **Listar todos os drones**
  - `GET /v1/drones`

## Entregas

- **Buscar entrega por ID**
  - `GET /v1/entregas/{id}`

- **Listar todas as entregas**
  - `GET /v1/entregas`

---

# 🧠 Prompts Utilizados

Os prompts utilizados para gerar código, documentação e ideias estão localizados na pasta:

```
/ia/prompts
```

---