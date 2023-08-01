# Pet Store API Testing

In the test case, I used the POM model for the dynamic project. I have endpoints, payloads (pojo classes),test, and utils packages. I used testNG framework for assertions. To create pet and user dynamically and fast ı used Faker class and dependency. I used extend report to see the test result clearly and explain it easily. I used log4j2 to see what happens in every step generally and save it in the log package.

## Installation

You need some dependencies. You need to create testng.xml file for operate your test cases for your need. For example if you want to run only some methods change methods, if you want to class or suite just change the testng.xml file. But the xml file is not compulsory for the run test cases.

```bash
io.rest-assured : rest-assured
org.testng : testng
io.rest-assured : json-schema-validator
com.github.javafaker : javafaker
com.aventstack : extendreports
org.apache.logging.log4j : log4j-api
org.apache.logging.log4j : log4j-core
org.apache.poi : poi
```

## Usage

```python
# testng.xml
```
![PetStore XML file](https://github.com/OrhanAyyildizSDET/PetStore-SwaggerTests./assets/100473852/234746e1-24ca-4c80-b6be-1ca7f754ab23)

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

https://github.com/OrhanAyyildizSDET/
