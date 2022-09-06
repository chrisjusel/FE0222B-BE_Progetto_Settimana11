# FE0222B-BE_progetto_settimana11

Questo è un progetto per prendere dimestichezza con le tecnologie REST fornite da Spring Boot e Spring Security.
E'presente lo strato di persistenza su database in memory H2.
E' presente un sistema di autenticazione che restituisce un token generato con lo standard JWT.
Tutti i metodi che l'API espone sono documentati con Swagger la quale documentazione è visibile all'URL /swagger-ui.html.
Nell'intero progetto è stato adottato un sistema di gestione delle eccezioni.

# Contenuti presenti
- [Quadro generale](#Quadro-generale)
- [Funzionalità principali](#Funzionalità-principali)
- [Utilizzo API - Libri](#Utilizzo-API-Libri)
- [Utilizzo API - Autori](#Utilizzo-API-Autori)
- [Utilizzo API - Categorie](#Utilizzo-API-Categorie)
- [Utilizzo API - Login e Registrazione](#Utilizzo-API-Login-e-Registrazione)
- [Risorse Aggiuntive](#Risorse-Aggiuntive)

## Quadro generale
L'applicazione espone metodi REST all'utente per la gestione di libri, autori e categorie

## Funzionalità principali
### Libri
- Inserimento di un libro
- Modifica di un libro
- Cancellazione di un libro
- Recupero di tutti i libri
- Recupero di uno specifico regista
- Recupero dei libri di un autore
- Recupero dei libri di una categoria

### Autori
- Inserimento di un autore
- Modifica di un autore
- Cancellazione di un autore
- Recupero di tutti gli autori
- Recupero di uno specifico autore

### Cateogire
- Inserimento di una categoria
- Modifica di una categoria
- Cancellazione di una ctegoria
- Recupero di tutte le categorie
- Recupero di una specifica categoria

Ciascuna di queste chiamate, rende persistenti, modifica, cancella o recupera dati da e sul database H2 sottostante che è stato impostato per droppare e ricreare tutte le tabelle ad ogni avvio del server, questo comportamento è modificabile nel fiel application.properties sotto la voce "spring.jpa.hibernate.ddl-auto"

## Utilizzo API Libri
### Metodi GET
- **Recupero tramite id**:  
  - /api/books/{id_libro}
- **Recupero di tutti i libri**:   
  - /api/books/findall
- **Recupero tramite l'autore**:
  - /api/books/findbyauthor?author={id_autore}
- **Recupero tramite la categoria**:
  - /api/books/findbycategory?category={nome_categoria}

### Metodi POST
- **Inserimento**:
  - /api/books/save
```
//request body
{
    "title":"Alla ricerca del tempo perduto",
    "publicationYear":"1899",
    "price":12.99,
    "authors":[
        {
            "id" : 2
        },
        {
            "id" : 3
        }
    ],
    "categories":[
        {
            "id" : 4
        },
        {
            "id" : 5
        }
    ]
}
```
### Metodi DELETE
- **Cancellazione**:
  - /api/books/delete/{id_libro}

### Metodi PUT
- **Modifica**:
  - /api/books/update
```
 //request body
{
    "id": 1,
    "title": "Alla ricerca del tempo perduto",
    "price": 24.99
}
```

## Utilizzo API Autori
### Metodi GET
- **Recupero tramite id**:  
  - /api/authors/{id_autore}
- **Recupero di tutti gli autori**:
  - /api/authors/findall

### Metodi POST
- **Inserimento**:
  - /api/authors/save
```
//request body
{
    "name":"Marcel",
    "surname":"Proust"
}
```
### Metodi DELETE
- **Cancellazione**:
  - /api/authors/delete/{id_autore}

### Metodi PUT
- **Modifica**:
  - /api/authors/update
```
 //request body
{
    "id": 1,
    "name":"Gabriele",
    "surname":"D'Annunzio"
}
```

## Utilizzo API Autori
### Metodi GET
- **Recupero tramite id**:  
  - /api/categories/{id_categoria}
- **Recupero di tutte le categorie**:
  - /api/categories/findall

### Metodi POST
- **Inserimento**:
  - /api/categories/save
```
//request body
{
    "name" : "Romanzo"
}
```
### Metodi DELETE
- **Cancellazione**:
  - /api/categories/delete/{id_categoria}

### Metodi PUT
- **Modifica**:
  - /api/categories/update
```
 //request body
{
    "id" : 2,
    "name" : "Giallo"
}
```

## Utilizzo API - Login e Registrazione

## Registrazione
- /auth/register
```
 //request body
{
    "username" : "admin",
    "password" : "admin",
    "email" : "admin@admin.it",
    "roles": ["ADMIN"]
}
```

## Login
- /auth/login
```
 //request body
{
    "username" : "admin",
    "password" : "admin"
}
```
 
## Risorse Aggiuntive
E' fornito il file di esportazione di Postman contentente già la configurazione corretta dei metodi per poterli testare
### URL per database h2
- /h2-console

### URL per documentazione swagger
Per alcuni metodi, è prevista l'autorizzazione attraverso bearer token
- /swagger-ui.html

