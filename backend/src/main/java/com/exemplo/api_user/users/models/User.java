package com.exemplo.api_user.users.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity // Normalmente, uma entidade representa uma tabela em um banco de dados relacional,
// e cada instância de entidade corresponde a uma linha nessa tabela.
@Table(name = "user") // Define o nome da tabela no banco de dados, caso deseje especificar um nome diferente
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable { // Marcar a classe como "serializável", permitindo que suas instâncias
    // possam ser convertidas em uma sequência de bytes.
    // Implementá-la nas entidades é uma boa prática, especialmente para aplicações corporativas onde o cache,
    // a serialização e o clustering de sessões são comuns.
    @Serial
    private static final long serialVersionUID = 1L; // defini um campo serialVersionUID para controlar a versão de serialização

    @Id // Define o campo como a chave primária.
    @GeneratedValue(strategy = GenerationType.UUID) // Configura a geração automática do valor do identificador
    private UUID id;

    @NotBlank // Garante que o campo não seja vazio ou nulo no nível de validação de dados antes de ser persistido no banco de dados
    @Size(min = 3, max = 100) // Comprimento máximo e mínimo no nível de validação de dados antes de ser persistido no banco de dados
    @Column(nullable = false, // Define que o campo não pode ser nulo no banco de dados,
            // atua no nível de persistência, influenciando o esquema do banco de dados
            unique = true, // Valor único no banco de dados, atua no nível de persistência
            length = 100 // Comprimento máximo no banco de dados, atua no nível de persistência
    )
    private String username;

    @NotBlank
    @Size(min = 6, max = 100)
    @JsonIgnore // evita incluir o campo password no DTO de saída
    @Column(nullable = false, columnDefinition = "BINARY(60)") // para armazenar senhas encriptadas
    private String password;

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(nullable = false)
    private String role;
}
