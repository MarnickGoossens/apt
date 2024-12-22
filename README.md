
# Documentatie Project Advanced Programming Topics

Marnick Goossens<br/>
Sebastiaan Daniëls

# Abstract

Deze documentatie biedt een overzicht van de implementatie en oplevering van een microservices-backend, uitgevoerd binnen de basiseisen van het project. Het project is ontwikkeld volgens de volgende richtlijnen:

- 4 microservices, met een zelfgekozen thema
- Gebruik van zowel MongoDB als SQL-databases, met een focus op logische API-structuren en endpoints (GET, POST, PUT, DELETE).
- Beveiliging via OAuth2-authenticatie en deployment met Docker Compose ondersteund door GitHub Actions.

De documentatie beschrijft het gekozen thema, de architectuur van de backend en de interacties tussen de services. Er wordt speciale aandacht besteed aan:

- **Technische structuur**: Deployment-schemas, databankkeuzes en motivatie.
- **Endpoints**: Functionele beschrijvingen en voorbeelden van interacties, inclusief een demonstratie van service-communicatie via postman

# Beschrijving van het gekozen thema

We hebben gekozen om in dit project een management api te maken voor festivals.
Als stagemanagers is het enorm belangrijk om een goed overzicht te houden van je podia. Je moet weten wie er draait, en ook wanneer die persoon draait.

Je weet echter niet al deze zaken op het zelfde moment, soms weet je al dat een artiest op je festival zal draaien, maar weet je nog niet waar. En andere keren weet je al vrij vroeg dat er een nieuw podium komt, maar je weet nog niet wie daar komt spelen.

Daarom kan je in ons systeem deze dingen beheren zonder dat ze van elkaar afhankelijk zijn.
Allereerst is het mogelijk de podia te beheren. 
Ook moet je uiteraard een artiest boeken met de booking-company vooraleer je ze kan inzetten op festivals.

Daarom is het de bedoeling dat wanneer je een artiest geboekt hebt voor je festival, je hem al in je systeem kan opslaan zonder dat je al weet op welk podium hij zal draaien.
Wanneer je dit wel weet, kan je een request sturen om een artiest op een bepaalde stage in te plannen op een bepaald tijdstip.

Het systeem zal daarna kijken of:
- De artiest wel geboekt is.
- Het podium bestaat

Indien er aan deze drie voorwaarden voldaan wordt, zal het systeem dit opslaan. Indien dit niet het geval is, zal het systeem een error terugsturen.

Ten laatste kan men ook tickets opvragen of toevoegen.

# Microservices en andere componenten
## Schema-weergave

![Schema van de services](./img/schema.png)

## Lijst-weergave

- API Gateway
- timeslot-service
    - MySQL-timeslot
- stage-service
    - MySQL-stages
- artist-service
    - mongo-artists
- ticket-service
    - mongo-tickets

## Beschrijving

Centraal in het systeem staat de **API Gateway**, die dient als toegangspoort voor alle requests. De API Gateway stuurt deze requests door naar de juiste microservices en maakt gebruik van OAuth2 voor authenticatie. Hierdoor wordt ervoor gezorgd dat alleen geautoriseerde gebruikers toegang hebben tot bepaalde functionaliteiten. (Dit zijn de endpoints op het schema waar AUTH achter staat, de andere endpoints zijn voor iedereen beschikbaar).

De **Artist Service** beheert de gegevens van artiesten. Via deze service kun je artiesten toevoegen, verwijderen of opvragen. Dit kan al direct na het boeken van een artiest, zonder dat ze aan een podium of tijdslot gekoppeld hoeven te zijn. De gegevens van artiesten worden opgeslagen in een MongoDB-database.

Voor het beheer van podia is er de **Stage Service**. Hiermee kun je podia toevoegen, wijzigen of verwijderen. Elk podium heeft een unieke naam en wordt opgeslagen in een MySQL-database. Zodra een podium is aangemaakt, kan het later worden gekoppeld aan tijdslots met artiesten.

De **Timeslot Service** brengt het inplannen van artiesten op podia. Wanneer een verzoek wordt gedaan om een artiest aan een podium te koppelen, controleert het systeem of de artiest daadwerkelijk geboekt is en of het podium bestaat. Als aan beide voorwaarden wordt voldaan, wordt de planning opgeslagen. Zo niet, dan stuurt het systeem een foutmelding terug.

Tot slot is er de **Ticket Service**, waarmee tickets kunnen worden toegevoegd of opgevraagd. Dit maakt het mogelijk om overzicht te houden van de ticketverkoop, bijvoorbeeld nadat artiesten en podia aan tijdslots zijn gekoppeld. Ook deze gegevens worden opgeslagen in een MongoDB-database.

# Endpoints

> TODO: Add postman screenshots

### **Stage Service**

- **GET** `/stage/all` - Haal alle podia op.
![Een Endpoint](./img/img03.png)
- **GET** `/stage` - Haal een specifiek podium op (op basis van naam).
- **POST** `/stage` (AUTH) - Voeg een nieuw podium toe.
![Een Endpoint](./img/img02.png)
- **PUT** `/stage` (AUTH) - Wijzig een bestaand podium.
![Een Endpoint](./img/img04.png)
![Een Endpoint](./img/img05.png)
- **DELETE** `/stage` (AUTH) - Verwijder een podium.
![Een Endpoint](./img/img06.png)
![Een Endpoint](./img/img07.png)

### **Artist Service**

- **GET** `/artists/all` - Haal alle artiesten op.
![Een Endpoint](./img/img12.png)
- **GET** `/artists` - Haal een specifieke artiest op met een queryparameter (bijvoorbeeld naam of ID).
![Een Endpoint](./img/img19.png)
- **POST** `/artists` (AUTH) - Voeg een nieuwe artiest toe.
![Een Endpoint](./img/img20.png)
![Een Endpoint](./img/img21.png)
- **DELETE** `/artists` (AUTH) - Verwijder een artiest.
![Een Endpoint](./img/img23.png)
![Een Endpoint](./img/img24.png)



### **Timeslot Service**

- **GET** `/timeslots` - Haal alle tijdslots op.
![Een Endpoint](./img/img08.png)
- **POST** `/timeslots` (AUTH) - Voeg een nieuw tijdslot toe.
Hier voegen we een artiest toe die niet bestaat, we krijgen een error
![Een Endpoint](./img/img09.png)
Hier voegen we een bestaande artiest toe
![Een Endpoint](./img/img10.png)
Proberen we dit nogmaals, zien we dat het podium vol is.
![Een Endpoint](./img/img11.png)
![Een Endpoint](./img/img13.png)
- **DELETE** `/timeslots` (AUTH) - Verwijder een bestaand tijdslot.
![Een Endpoint](./img/img14.png)
![Een Endpoint](./img/img15.png)

### **Ticket Service**

- **GET** `/tickets` (AUTH) - Haal alle tickets op.
![Een Endpoint](./img/img16.png)
- **POST** `/tickets` (AUTH) - Voeg een nieuw ticket toe.
![Een Endpoint](./img/img17.png)
![Een Endpoint](./img/img18.png)

# Links

**GitHub:** https://github.com/MarnickGoossens/apt <br/>
**DockerHub:** ?Missing Link?