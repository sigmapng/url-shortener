# URL Shortener System

## University Coursework Project

This project is a URL shortening web application developed as coursework for university. It provides functionality to create shortened URLs that redirect to original long URLs, making link sharing more convenient.

## Project Overview

The URL Shortener System is a Java-based web application that allows users to:
- Create shortened URLs for long web addresses
- Access original URLs through shortened links
- View and manage created short URLs

## Technologies Used

- **Java EE 8**: Core programming language and enterprise framework
- **Maven**: Project management and build tool
- **WildFly 26**: Application server
- **PostgreSQL**: Database for storing URL mappings
- **Hibernate**: JPA implementation for database interactions
- **JSP/JSTL**: View technologies for rendering web pages
- **Bootstrap 5**: Frontend framework for responsive design

## Project Structure

```
url-shortener-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/urlshortener/
│   │   │       ├── dao/            # Data Access Objects
│   │   │       ├── entity/         # JPA Entities
│   │   │       ├── service/        # Business Logic
│   │   │       └── servlet/        # Servlet Controllers
│   │   ├── resources/
│   │   │   └── META-INF/           # Configuration files
│   │   └── webapp/
│   │       ├── WEB-INF/            # Web application configuration
│   │       └── index.jsp           # Main application page
├── pom.xml                         # Maven configuration
└── README.md                       # Project documentation
```

## Features

- **URL Shortening**: Convert long URLs to short, easy-to-share links
- **Redirection**: Automatically redirect users from short URLs to original destinations
- **URL Management**: View and manage previously created short URLs
- **Responsive Design**: Mobile-friendly interface using Bootstrap

## Setup and Deployment

### Prerequisites

- JDK 11 or higher
- Maven 3.6 or higher
- PostgreSQL database
- WildFly 26 application server

### Database Setup

1. Create a PostgreSQL database for the application
2. Update the database connection parameters in `src/main/resources/META-INF/persistence.xml`

### Building the Application

```bash
mvn clean package
```

This will generate a WAR file in the `target/` directory.

### Deployment

1. Start WildFly server:
   ```bash
   ./wildfly-26.1.3.Final/bin/standalone.sh
   ```
   (or `standalone.bat` on Windows)

2. Deploy the WAR file to WildFly:
   - Copy the generated WAR to WildFly's deployment directory
   - Or use the WildFly management console/CLI for deployment

3. Access the application at:
   ```
   http://localhost:8080/url-shortener
   ```

## Usage

1. **Creating a Short URL**:
   - Enter the original URL in the input field
   - Click "Create Short URL"
   - Copy the generated short URL

2. **Using a Short URL**:
   - Share the short URL with others
   - When accessed, users will be automatically redirected to the original URL

3. **Managing URLs**:
   - View a list of all created URLs

## Future Enhancements

- User authentication system
- Custom alias selection for URLs
- API endpoints for programmatic access

## Author

Andrii Opanasenko ISD-32

## License

This project was created for educational purposes as part of university coursework.

## Acknowledgments

- University professors and teaching assistants
- The open-source community for providing the tools and frameworks used in this project
