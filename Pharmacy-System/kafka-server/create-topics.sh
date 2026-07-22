#!/bin/bash

/opt/kafka/bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--create \
--topic medicine-stock-events \
--partitions 3 \
--replication-factor 1

/opt/kafka/bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--create \
--topic medicine-price-updates \
--partitions 1 \
--replication-factor 1

/opt/kafka/bin/kafka-topics.sh \
--bootstrap-server localhost:9092 \
--create \
--topic pharmacy-notifications \
--partitions 2 \
--replication-factor 1