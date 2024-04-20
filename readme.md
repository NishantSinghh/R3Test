# Command to run the test through maven

```mvn test -PPricing -Denv=<env name>```

**env name must be qa or dev**

### Regarding resources folder items

**resources->templates** All the templates are saved under this so that we are decoupling the test with the payload and
in case we need similar payload we can use the same template

**resources->data** Data related to test e.g request body can be used.

**resources->config** can be used to run test in different environment.

### Regarding Java class folder structure

**base->BaseTest** can be used to add anything common across test e.g generation and saving of access token and saving
the current env details, etc.

**config->ConfigManager** used to get the current env from resources->config files

**model->payloads** It's a POJO package Payload class used to make a POJO of request payload from the templates and data

**properties** To create ENUM which can be used in tests

**test** to store all our tests here

**utils** Will have different types of helper methods, Constants, etc.

### Regarding logs

Log4j package used and currently setup the log level to display all info and above level logs on console and debug and
above level logs will be saved under **project root folder->logs** folder



