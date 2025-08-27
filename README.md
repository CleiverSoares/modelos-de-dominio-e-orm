# Modelo de Domínio e ORM

Este projeto é um exemplo de aplicação Spring Boot que utiliza JPA para mapeamento objeto-relacional (ORM) e banco de dados H2 em memória. O domínio envolve participantes, atividades, categorias e blocos de horários.

## Estrutura do Projeto

- **src/main/java/com/cursodevsuperior/desafio02/entities**: Entidades JPA (`Participante`, `Atividade`, `Categoria`, `Bloco`)
- **src/main/resources/import.sql**: Script SQL para popular o banco de dados com dados iniciais
- **src/main/resources/application.properties**: Configuração do perfil ativo e JPA
- **src/main/resources/application-test.properties**: Configuração do banco H2 e console web

## Como executar

1. **Pré-requisitos**:  
   - Java 21+
   - Maven 3.9+

2. **Build do projeto**  
   No terminal, execute:
   ```sh
   ./mvnw clean install
   ```

3. **Rodar a aplicação**  
   ```sh
   ./mvnw spring-boot:run
   ```

4. **Acessar o banco H2**  
   - URL: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - JDBC URL: `jdbc:h2:mem:testdb`
   - Usuário: `sa`
   - Senha: (em branco)

## Testes

Execute os testes com:
```sh
./mvnw test
```

## Entidades Principais

- **Participante**: nome, email, atividades (muitos para muitos)
- **Atividade**: nome, descrição, preço, categoria, blocos, participantes
- **Categoria**: descrição, atividades
- **Bloco**: início, fim, atividade

## Scripts e Dados

O arquivo [`src/main/resources/import.sql`](src/main/resources/import.sql) contém dados de exemplo para facilitar testes e demonstrações.

## Consultas SQL Úteis

Algumas consultas para explorar os dados do banco H2:

- **Listar participantes e suas atividades:**
    ```sql
    SELECT
        tp.nome AS nome_participante,
        ta.nome AS nome_atividade,
        ta.preco AS preco_atividade
    FROM
        tb_participante tp
    INNER JOIN
        tb_participante_atividade tpa ON tp.id = tpa.participante_id
    INNER JOIN
        tb_atividade ta ON tpa.atividade_id = ta.id;
    ```

- **Listar atividades e seus blocos de horários:**
    ```sql
    SELECT
        ta.nome AS nome_atividade,
        tb.inicio AS inicio_bloco,
        tb.fim AS fim_bloco
    FROM
        tb_atividade ta
    INNER JOIN
        tb_bloco tb ON ta.id = tb.atividade_id;
    ```

- **Listar participantes de uma atividade específica ("Oficina de Github"):**
    ```sql
    SELECT
        tp.nome AS nome_participante,
        tp.email AS email_participante
    FROM
        tb_participante tp
    INNER JOIN
        tb_participante_atividade tpa ON tp.id = tpa.participante_id
    INNER JOIN
        tb_atividade ta ON tpa.atividade_id = ta.id
    WHERE
        ta.nome = 'Oficina de Github';
    ```

- **Listar atividades, preços e categorias:**
    ```sql
    SELECT
        ta.nome AS nome_atividade,
        ta.preco,
        tc.descricao AS categoria
    FROM
        tb_atividade ta
    INNER JOIN
        tb_categoria tc ON ta.categoria_id = tc.id;
    ```

- **Valor total pago por um participante específico ("José Silva"):**
    ```sql
    SELECT
        tp.nome,
        SUM(ta.preco) AS valor_total_pago
    FROM
        tb_participante tp
    INNER JOIN
        tb_participante_atividade tpa ON tp.id = tpa.participante_id
    INNER JOIN
        tb_atividade ta ON tpa.atividade_id = ta.id
    WHERE
        tp.nome = 'José Silva'
    GROUP BY
        tp.nome;
    ```

---

Projeto para fins didáticos - Curso