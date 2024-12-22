
# Documentatie Project Advanced Programming Topics

<hr>

Marnick Goossens
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

Je weet echter niet al deze zaken op het zelfde moment, soms weet je al dat een artiest op je festival zal draaien, maar weet je nog niet waar en wanneer. En andere keren weet je al vrij vroeg dat er een nieuw podium komt, maar je weet nog niet wie daar komt spelen.

Daarom kan je in ons systeem deze dingen beheren zonder dat ze van elkaar afhankelijk zijn.
Allereerst is het mogelijk de podia te beheren. 
Ook moet je uiteraard een artiest boeken met de booking-company vooraleer je ze kan inzetten op festivals.

Daarom is het de bedoeling dat wanneer je een artiest geboekt hebt voor je festival, je hem al in je systeem kan opslaan zonder dat je al weet op welk podium hij zal draaien.
Wanneer je dit wel weet, kan je een request sturen om een artiest op een bepaalde stage in te plannen op een bepaald tijdstip.

Het systeem zal daarna kijken of:
- De artiest wel geboekt is.
- Het podium bestaat
- Er nog niemand op dat podium met dat tijdstip speelt

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


# Endpoints

|postman|

# Links

**GitHub:** https://github.com/MarnickGoossens/apt
**DockerHub:** ?Missing Link?