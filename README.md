<div id="top"></div>

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![License][license-shield]][license-url]

# TooManyChoco

[Report Bug](https://github.com/shiipou/simplon-2024-brief-06-TooManyChoco/issues)
·
[Request Feature](https://github.com/shiipou/simplon-2024-brief-06-TooManyChoco/issues)

## Project Overview

**Objective**: Development of an Application for Managing our Pastry Deliveries

TooManyChoco is an application designed to manage pastry deliveries for breakfasts at Nocturlab. The goal is to streamline the organization and reservation of time slots for bringing pastries, providing a visual calendar of availability and reservations.

### Key Features

- **Slot Reservation**: Team members can reserve a day to bring pastries, with an option to anonymize names for those who prefer privacy.
- **Visual Calendar**: Clearly displays available days and existing reservations.
- **Pastry Choices**: Specify the type of pastries to bring for each reserved slot.
- **Pastry Poll**: Feature to choose from multiple pastry options.
- **User Management**: Secure account creation with email verification and password recovery.

### Technologies Used

- **Backend**: API developed in Java.
- **Database**: PostgreSQL to store reservation and user information.
- **Frontend**: React-based client application served by a web server.

We have two weeks to complete this project, following an agile methodology with a KanBan board for task management.

## Getting Started

### Prerequisites

Install TooManyChoco using one of the following options.

### Installation Steps

1. **Download the API**: Go to the [release tab](https://github.com/shiipou/simplon-2024-brief-06-TooManyChoco/releases) of the GitHub repository and download the latest API release.
2. **Download the React Client**: Similarly, go to the [release tab](https://github.com/shiipou/simplon-2024-brief-06-TooManyChoco/releases) of the GitHub repository and download the latest React client release.

### How to Start

1. **Run the API**: 
   ```sh
   java -jar TooManyChoco-api.jar
   ```

2. **Run the Client**: Use a web server that can serve static files.

### Environment Variables

You can change settings by defining the following environment variables:

```
JDBC_URL        : jdbc:postgresql://localhost:5432/TooManyChoco
JDBC_USERNAME   : postgres
JDBC_PASSWORD   : postgres
```

## Contributing

### Quickstart

Want to start developing on the project the quickest way? 

Use this button to open a coder workspace with everything already configured.

[![Open in Coder](https://code.nocturlab.fr/open-in-coder.svg)](https://code.nocturlab.fr/templates/dev-container/workspace?mode=manual&param.repo=custom&param.custom_repo=https%3A%2F%2Fgithub.com%2Fshiipou%2Fsimplon-2024-brief-06-TooManyChoco&param.devcontainer_dir=.devcontainer&param.cpu=2&param.memory=4&param.home_disk_size=10)

### Dependencies

If you choose to not use the previous button, you'll need to install the following:

- Java (JDK) >= 17
- PostgreSQL >= 13
    - Start the devcontainer PostgreSQL services: 
      ```sh
      docker compose -f .devcontainer/services.yml up -d
      ```

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/shiipou/simplon-2024-brief-06-TooManyChoco.svg?style=for-the-badge
[contributors-url]: https://github.com/shiipou/simplon-2024-brief-06-TooManyChoco/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/shiipou/simplon-2024-brief-06-TooManyChoco.svg?style=for-the-badge
[forks-url]: https://github.com/shiipou/simplon-2024-brief-06-TooManyChoco/network/members
[stars-shield]: https://img.shields.io/github/stars/shiipou/simplon-2024-brief-06-TooManyChoco.svg?style=for-the-badge
[stars-url]: https://github.com/shiipou/simplon-2024-brief-06-TooManyChoco/stargazers
[issues-shield]: https://img.shields.io/github/issues/shiipou/simplon-2024-brief-06-TooManyChoco.svg?style=for-the-badge
[issues-url]: https://github.com/shiipou/simplon-2024-brief-06-TooManyChoco/issues
[license-shield]: https://img.shields.io/github/license/shiipou/simplon-2024-brief-06-TooManyChoco.svg?style=for-the-badge
[license-url]: https://github.com/shiipou/simplon-2024-brief-06-TooManyChoco/blob/master/LICENSE.txt
