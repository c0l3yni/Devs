# TekkEgg Developer Module
This module contains several automations to ease development. 

## Install Instructions
To install the module in your user profile follow the instructions below  
1. Run `$profile` command in powershell
2. navigate to the provided filepath
3. Create a new folder called `Modules`
4. Inside the `Modules` folder create a new folder called `TekEggDeveloperScript`
5. Lastly copy `TekEggDeveloperScript.psm1` into `TekEggDeveloperScript` folder

# Commands
Below is a list of the commands with the alias and description.

## Git Methods

### `Deploy-GitCommit`
#### Alias: `dpgc`
This script will automate staging all files, commiting them with user inputed commit message, and pushing to current branch.

### `Merge-GitDevelop`
#### Alias: `mgdv`
This script will automate the proccess of pulling develop into your current branch to check for merge conflicts. Then it will checkout to develop and merge your current branch.

### `Remove-UnusedBranches`
#### Alias: `runb`
This script will prune all remote branches then show the user a list of which branches will be saved and removed. Next it will remove all branches highlighted in red.

### Gradle Shortcuts

### `Invoke-GradleCleanDeployment`
#### Alias: `gcd`
Runs ./gradlew clean deployment

### `Invoke-GradleCleanDebug`
#### Alias: `gcdb`
Runs ./gradlew clean debug

### `Invoke-GradleCleanRegression`
#### Alias: `gcr`
Runs ./gradlew clean regression

### `Invoke-GradleCleanSmoke`
#### Alias: `gcsm`
Runs ./gradlew clean smoke

### `Invoke-GradleCleanTest`
#### Alias: `gct`
Runs ./gradlew clean test

## Server Functions

### `Test-ServerConnection`
#### Alias: `tsc`
#### Params: Port e.g. `3000`
This script will take a given port create a web request and return true if a status code is returned.

### `Stop-ServiceOnPort`
#### Alias: `ssop`
#### Params: Port e.g. `3000`
This script will take a given port and search for it. If found the scripts will kill the port by pid.

### `Start-WebServer`
#### Alias: `sws`
This script will navigate to the web server and run npm start in the same window. If the port is already open it will kill the process then run the service.

### `Start-UserService`
#### Alias: `sus`
This script will navigate to the user service and run npm start in the same window. If the port is already open it will kill the process then run the service.

### `Start-LocalServer`
#### Alias: `sls`
#### Params: ServerPath e.g. `.\service\user\source` | ServerPort e.g. `3000`
This script will navigate to the given path and run npm start in the same window. If the port is already open it will kill the process then run the service.

### `Start-Servers`
#### Alias: `ss`
This script will run the UserService and WebServer in new powershell windows. If either is already running it will kill the process and start the server.

## Integration Test Functions

### `Read-Report`
#### Alias: `rdrep`
#### Params: Report = `webapp`, `stripe`, `user`, `all`
This script will open reports for software gauntlet.

### `Invoke-IntegrationTests`
#### Alias: `iit`
#### Params: TestSuite e.g. `deployment` | Skip Prompts = `y`, `n`
This script will iterate through an array of paths and run a gradle test suite depending on the parameter provided. It will also prompt to see if the user would like to run the web server in another powershell window.

## Helper Functions

### `Install-DeveloperApplicatons`
#### Alias: `isdvapp`
The New Team Member scripts installs the following applications

* Chocolatey
* PoshGit
* Git
* Open JDK 17
* NodeJS
* MySQL
* Python 3
* Gradle
* Intellij Community
* Visual Studio Code
* Visual Studio 2019 Community
* Postman

### `Update-DevsScript`
#### Alias: `uds`
This script will update the users TekEggDeveloperScript module to what is in the local repository and reload its import.

### `Set-FilePath`
#### Alias: `sfp`
This Script sets the users file path to the root of tekegg. 