# Prometheus
demo project

## Installation

### Prometheus docker installation
```bash
docker run --name prometheus -d -p 9090:9090 -v /Users/maxim/Work/education/projects/prometheus/src/main/resources/prometheus.yaml:/etc/prometheus/prometheus.yml prom/prometheus
```
path to the custom prometheus configuration file - **/Users/maxim/Work/education/projects/prometheus/src/main/resources/prometheus.yaml**

after that you can access prometheus on http://localhost:9090

### Grafana docker installation
```bash
docker run -d --name=grafana -p 3000:3000 grafana/grafana-enterprise:11.1.0-ubuntu
```
