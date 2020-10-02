# SeleniumProject

This repo contains just simple UI test which naigates through some page to chekc if webpage works as expected.

1. Check if Login Button there and tries to login with 2 dummy users
2. Clicks forget password and tries to enter email id and submits request.
3. Checks if title "Contact us" exists in main pageBelow are steps to run and find results of test project
4. Check if search icon exists

## How to Run

1. Clone the repo.
```
git clone https://github.com/Aneesa666/SeleniumProject.git
```

2. Run maven command to clean if old package exists
```
mvn clean
```

3. Run Tests
```
mvn install
```

You can get logs from logs path and testng HTMl reports from reports folder. If any of test fails screenshot will be captured and stored in reports folder.
