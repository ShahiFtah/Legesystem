# Legesystem

Dette prosjektet implementerer et system for håndtering av legemidler, resepter og leger som en del av obligatoriske oppgaver i IN1010. Systemet er bygget i Java og omfatter flere klasser som representerer de forskjellige enhetene i helsevesenet.

## Funksjonalitet

Prosjektet består av flere moduler som simulerer et enkelt legesystem:

### Del A: Legemidler
- **Legemiddel** er en abstrakt klasse med subklasser som representerer forskjellige typer legemidler:
  - **Narkotisk Legemiddel**: Har en styrke som indikerer hvor sterkt narkotisk legemidlet er.
  - **Vanedannende Legemiddel**: Har en styrke som angir hvor vanedannende legemidlet er.
  - **Vanlig Legemiddel**: Har ingen spesielle egenskaper utover de grunnleggende attributtene (navn, pris, virkestoff).
  
  Hver legemiddelklasse har attributter som pris (i hele kroner), virkestoff (i mg), og en unik ID som settes automatisk ved opprettelse av objektene.

### Del B: Resepter
- **Resept** er en abstrakt klasse som har flere subklasser:
  - **Hvit Resept**: Standard resept.
  - **Militærresept**: Gir 100% rabatt på legemidler for vernepliktige og inneholder alltid 3 reit.
  - **P-resept**: Gir en fast rabatt på 108 kr på prevensjonsmidler, og sikrer at prisen aldri går under 0.
  - **Blå Resept**: Gir 75% rabatt på legemidler, og pasienten betaler kun 25% av prisen.

  Reseptklassene har metoder for å beregne pris og forvaltning av reit (bruken av resepten).

### Del C: Leger
- **Lege**: Representerer en lege som kan skrive ut resepter.
- **Spesialist**: Arver fra Lege og implementerer et interface som gir spesialister muligheten til å skrive ut resepter på narkotiske legemidler. Hver spesialist har en kontrollkode som kan hentes ut.

### Del D: Integrasjonstest
- Hovedprogrammet integrerer alle delene av systemet (legemidler, resepter, leger) og tester hvordan de fungerer sammen. Flere objekter opprettes og relevant informasjon om objektene skrives ut for å bekrefte at systemet fungerer som forventet.

### Del E: Datastruktur
- En datastrukturtegning viser hvordan objektene er knyttet sammen i systemet. Dette inkluderer relasjonene mellom legemidler, leger og resepter.

## Lisens
Dette prosjektet er laget som en del av obligatoriske oppgaver for faget IN1010 og er ikke ment for kommersiell bruk.
