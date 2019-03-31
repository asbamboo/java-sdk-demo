FROM java:8

WORKDIR /app

COPY . /app

EXPOSE 8080

CMD ["/app/gradlew", "bootRun"]