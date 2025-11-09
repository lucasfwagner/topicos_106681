# Tutorial: Expondo uma API REST na Biblioteca Digital (Quarkus + JAX-RS)

## Objetivo
Neste tutorial você vai aprender, passo a passo, como **adicionar um endpoint REST** ao projeto *Sistema de Biblioteca Digital* — reutilizando o `BibliotecaService` já existente no código. Isso permite que aplicativos móveis ou clientes externos consumam os dados dos livros em JSON.

## Por que melhorar o projeto?
Adicionar uma API REST torna o sistema acessível por clientes diferentes do JSF (por exemplo mobile, single-page apps, integrações). É uma extensão prática e de baixo risco que reaproveita a lógica de negócios já implementada.

---

## Pré-requisitos
- Projeto "Sistema de Biblioteca Digital" clonado e funcional.
- Java 11+ (ou a versão usada pelo projeto).
- Maven (ou `./mvnw`) instalado.
- Dependência `quarkus-resteasy-reactive-jackson` adicionada ao `pom.xml` (ver seção abaixo).
- Código existente contém `BibliotecaService` que fornece métodos como `listarTodosLivros()` e `buscarLivroPorId(Long id)`.

---

## 1) Adicionando dependência (pom.xml)
No `pom.xml` do projeto, adicione (ou verifique se já existe) a dependência para RESTEasy Reactive com suporte a JSON:

```xml
<dependency>
  <groupId>io.quarkus</groupId>
  <artifactId>quarkus-resteasy-reactive-jackson</artifactId>
</dependency>
```

Também garanta que você tenha o plugin do Quarkus para desenvolvimento:
```xml
<plugin>
  <groupId>io.quarkus</groupId>
  <artifactId>quarkus-maven-plugin</artifactId>
  <version>${quarkus.version}</version>
  <executions>
    <execution>
      <goals>
        <goal>build</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

---

## 2) Estrutura que vamos criar
Vamos criar um recurso JAX-RS:

`src/main/java/br/upf/biblioteca/rest/BibliotecaResource.java`

Ele terá dois endpoints principais:
- `GET /api/livros` — lista todos os livros (JSON).
- `GET /api/livros/{id}` — busca um livro por id (JSON) ou retorna 404.

O recurso **reutiliza** o `BibliotecaService` do projeto para manter a lógica centralizada.

---

## 3) Código do recurso REST
Crie o arquivo `BibliotecaResource.java` com o conteúdo abaixo (ajuste packages e nomes se necessário para refletir seu projeto):

```java
package br.upf.biblioteca.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import br.upf.biblioteca.service.BibliotecaService;
import br.upf.biblioteca.model.Livro;

@Path("/api/livros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BibliotecaResource {

    @Inject
    BibliotecaService bibliotecaService;

    @GET
    public Response listarTodos() {
        List<Livro> livros = bibliotecaService.listarTodosLivros();
        return Response.ok(livros).build();
    }

    @GET
    @Path("{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        Livro livro = bibliotecaService.buscarLivroPorId(id);
        if (livro == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(livro).build();
    }
}
```

> Observações:
> - Ajuste os nomes dos pacotes (`br.upf.biblioteca.service` e `br.upf.biblioteca.model`) caso sejam diferentes no seu projeto.
> - O Quarkus irá serializar automaticamente as entidades `Livro` em JSON usando Jackson, desde que as classes sejam POJOs (getters/setters) ou Panache entities.

---

## 4) Considerações sobre as Entidades e DTOs
- Em projetos reais, é recomendado usar DTOs (Data Transfer Objects) para evitar expor toda estrutura interna da entidade (ex.: lazy-loading, relações bidirecionais).
- Para começar rápido, você pode expor diretamente a entidade `Livro`. Se ocorrerem problemas de serialização (ex.: referências circulares), considere:
  - Anotar relações com `@JsonIgnore` ou usar DTOs.
  - Usar `@JsonbTransient` / `@JsonIgnoreProperties` ou configurar Jackson.

---

## 5) Rodando e testando
1. Execute a aplicação em modo dev:
```bash
./mvnw quarkus:dev
# ou
mvn quarkus:dev
```

2. Teste os endpoints com curl ou Postman:

- Listar:
```bash
curl -sS http://localhost:8080/api/livros | jq
```

- Buscar por id (ex: 1):
```bash
curl -i http://localhost:8080/api/livros/1
```

Você deve receber `200 OK` com JSON ou `404 Not Found` se o id não existir.

---

## 6) Verificação (checklist)
- [ ] Dependência `quarkus-resteasy-reactive-jackson` adicionada.
- [ ] Classe `BibliotecaResource` criada e compilando.
- [ ] Endpoints respondem em `/api/livros` e `/api/livros/{id}`.
- [ ] `BibliotecaService` foi reutilizado (injeção funcionando).

---

## 7) Referências (pesquisa externa)
- Quarkus - JAX-RS (RESTEasy Reactive) — https://quarkus.io/guides/resteasy-reactive
- Quarkus - Jackson JSON — https://quarkus.io/guides/rest-json
- Jakarta RESTful Web Services — https://jakarta.ee/specifications/restful-ws/

---

## Entregáveis incluídos neste pacote
- `TUTORIAL.md` — este tutorial.
- `src/main/java/br/upf/biblioteca/rest/BibliotecaResource.java` — recurso JAX-RS de exemplo.
- `pom-requirements.txt` — trecho sugerido de dependência a ser adicionado ao `pom.xml`.

Boa implementação! Se quiser, posso também **converter as entidades para DTOs** ou **criar endpoints POST/PUT/DELETE** para CRUD completo.
