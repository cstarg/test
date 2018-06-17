
## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

Clone the project
git clone https://github.com/cstarg/test.git

### Prerequisites

What things you need to install the software and how to install them

Install Homebrew
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

Install maven
brew install maven

Install postgres
brew install postgres

Start postgres
brew services start postgres

### Build and run the project

mvn package

mvn spring-boot:run

### Create or update consumer example on localhost

curl -H "Content-Type: application/json" --data '{"name":"name1", "email":"name1@email.com"}' http://localhost:8080/customer

### Create or update consumer example on heroku server

curl -H "Content-Type: application/json" --data '{"name":"name1", "email":"name1@email.com"}' https://pacific-fjord-85516.herokuapp.com/customer