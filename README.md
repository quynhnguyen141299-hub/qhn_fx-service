# qhn_fx-service
# FX Rate & Currency Conversion Microservice

A Java Spring Boot microservice that provides real-time FX rates and currency conversion, with SQL persistence for market data and trade-like audit trails.

## What It Does

- **Live FX Rates**: Fetches real-time exchange rates for 160+ currency pairs from ExchangeRate API
- **Currency Conversion**: Converts any amount between supported currencies using live market rates
- **SQL Persistence**: Stores all fetched rates and conversion requests in a relational database, modelling a trade blotter / audit log
- **Error Handling & Logging**: Global exception handling and SLF4J structured logging (that exposes an API which logging backends can use to emit structured data such as key–value pairs or JSON.)

## Tech Stack

- Java 17+, Spring Boot 4.x
- Spring Data JPA / Hibernate
- H2 in-memory database
- Maven
- ExchangeRate API (external market data)

## API Endpoints

### Get Rates
