## Bet Settlement Application
This app handles the bets coming over api endpoint and send it to kafka topic.
Kafka processes the event and create bet settlement message to send to rocketMQ for processing settling the bets.

## Initial Data
The codebase has `import.sql` file which populates the database with initial data at runtime.

## How to run the app
You need to have docker running on the local.
The application has docker-compose.yml file
1. Go to Terminal
2. Navigate to project's root folder
3. Up the infrastructure - `docker-compose up -d`
4. Create Kafka topic - `docker exec -it kafka kafka-topics --bootstrap-server localhost:9092 --create --topic event-outcomes --partitions 1 --replication-factor 1`
5. Create RocketMQ topic - `docker exec -it rmq-broker sh mqadmin updateTopic -t bet-settlements -c DefaultCluster -n rmq-namesrv:9876`
6. Run the application from `BetSettlementApplication` class


