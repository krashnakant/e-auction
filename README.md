# e-Auction

[![Build Status](https://travis-ci.org/jaydeep-punjani/e-auction.svg?branch=master)](https://travis-ci.org/jaydeep-punjani/e-auction)
[![Dependency Status](https://david-dm.org/jaydeep-punjani/e-auction.svg?theme=shields.io)](https://david-dm.org/jaydeep-punjani/e-auction)
[![sonar-quality-gate][sonar-quality-gate]][sonar-url]
[![sonar-coverage][sonar-coverage]][sonar-url]
[![sonar-bugs][sonar-bugs]][sonar-url]
[![sonar-vulnerabilities][sonar-vulnerabilities]][sonar-url]

This is a demo project.

### Setup in Development Environment
```
Install Node.js
Install YARN
git clone https://github.com/jaydeep-punjani/e-auction.git
Run yarn install
Run ./mvnw
```

### For Testing

```
./mvnw clean test
./mvnw gatling:execute
./mvnw clean test sonar:sonar
```

### Build for Production

```
./mvnw -Pprod package

```

[sonar-url]: https://sonarcloud.io/dashboard?id=org.eauction%3Aeauction
[sonar-quality-gate]: https://sonarcloud.io/api/badges/gate?key=org.eauction%3Aeauction
[sonar-coverage]: https://sonarcloud.io/api/badges/measure?key=org.eauction%3Aeauction&metric=coverage
[sonar-bugs]: https://sonarcloud.io/api/badges/measure?key=org.eauction%3Aeauction&metric=bugs
[sonar-vulnerabilities]: https://sonarcloud.io/api/badges/measure?key=org.eauction%3Aeauction&metric=vulnerabilities
