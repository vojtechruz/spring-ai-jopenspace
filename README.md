# Spring AI Demo project for JOpenspace 2024
This is demo repository for [presentation about Spring AI](https://docs.google.com/presentation/d/1V0S-XLpO79DJUz9FeOLH2YzY7D5eFlNiZSiq4mEJaJA/edit?usp=sharing) for [JOpenspace](https://www.jopenspace.cz/) 2024 conference.

## Setup

### Build and Run
The application is SpringBoot app with Maven. You can build the app using Maven as usual with no extra parameters required.

```
mvn clean install
```

To run the app simply execute `SpringAiJopenspaceApplication` no extra params or profiles required.

### API Keys
This application requires connection to OpenAI API to function. Therefore internet connectivity is required.

You will need to obtain OpenAI API key and have some funds allocated to your OpenAI account (couple of dollars should be more than enough).

To obtain the API Key, we first need to [create an OpenAI account](https://platform.openai.com/signup). After that, go to [API Keys](https://platform.openai.com/api-keys) page and generate a new key.

Once you have your API key, put it to `application.properties`:

```
spring.ai.openai.api-key=<YOUR API KEY>
```

## Usage
Once your API key is set up and your app is running, you can interact with its REST API running on

```
localhost:8080
```

You can check `http-requests.http` for sample requests and directly execute them in IntelliJ IDEA using its integrated HTTP client.