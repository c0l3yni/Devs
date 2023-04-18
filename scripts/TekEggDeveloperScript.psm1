# Git Functions
function Deploy-GitCommit {
    param (
        [string]$SkipPrompts = 'n'
    )
    Set-FilePath
    $CurrentBranch = git branch --show-current
    git status
    Write-Host "This script will add, commit, and push" $CurrentBranch "branch to remote" 
    if ('y' -ne $SkipPrompts) {
        $Continue = Read-Host -Prompt "Continue? (y/n)"
        if ('y' -ne $Continue) {
            Write-Host "Nothing staged. Exiting script" -ForegroundColor Red
            break
        }
    }

    Write-Host ""
    git add .
    $FilesAddedCount = (git diff --name-only --cached).count
    git status
    Write-Host "Staged" $FilesAddedCount "file(s) to commit"
    $CommitMessage = Read-Host -Prompt "Enter commit message (no quotes) "
    git commit -m $CommitMessage

    Write-Host ""
    $AllBranches = git branch -a
    [bool]$IsBranchRemote = $false
    foreach ($Branch in $AllBranches) {
        if ($Branch.Contains("remotes/origin/${CurrentBranch}")) {
            $IsBranchRemote = $true
            break
        }
    }
    if ($IsBranchRemote) {
        git push
    }
    else {
        git push --set-upstream origin $CurrentBranch
    }
    
}
Set-Alias dpgc Deploy-GitCommit

function Merge-GitDevelop {
    param (
        [string]$SkipPrompts = 'n'
    )

    $CurrentBranch = git branch --show-current

    Write-Host "This script will checkout to develop and pull. Then Merge current branch with develop"
    if ('y' -ne $SkipPrompts) {
        $Continue = Read-Host -Prompt "Continue? (y/n)"
        if ('y' -ne $Continue) {
            Write-Host "No commands sent. Exiting script" -ForegroundColor Red
            break
        }
    }
    
    Write-Host ""
    Write-Host "Pulling develop into ${CurrentBranch}"
    git pull origin develop
    
    Write-Host ""
    Write-Host "YAY, no merge conflicts!"
    Write-Host ""
    
    git checkout develop
    git pull
    git merge $CurrentBranch
    git push
}
Set-Alias mgdv Merge-GitDevelop

function Invoke-GradleCleanDeployment {
    ./gradlew clean deployment
}
Set-Alias gcd Invoke-GradleCleanDeployment

function Invoke-GradleCleanDebug {
    ./gradlew clean debug
}
Set-Alias gcdb Invoke-GradleCleanDebug

function Invoke-GradleCleanRegression {  
    ./gradlew clean regression
}
Set-Alias gcr Invoke-GradleCleanDebug

function Invoke-GradleCleanSmoke { 
    ./gradlew clean smoke
}
Set-Alias gcsm Invoke-GradleCleanDebug

function Invoke-GradleCleanTest {  
    ./gradlew clean test
}
Set-Alias gct Invoke-GradleCleanDebug

function Remove-UnusedBranches {
    Write-Host "Pruning Remote..." -ForegroundColor Green
    git remote prune origin

    $AllBranches = git branch -a
    $BranchesToSave = New-Object Collections.Generic.List[String]
    $BranchesToRemove = New-Object Collections.Generic.List[String]

    foreach ($Branch in $AllBranches) {
        if ($Branch.Contains("remotes")) {
            $BranchEnd = $Branch.split("/")[-1]
            $BranchesToSave.Add($BranchEnd)
            Write-Host "Save ${Branch}" -ForegroundColor Blue
        }
    }

    foreach ($Branch in $AllBranches) {
        $BranchEnd = $Branch.split("/")[-1]
        if (!$Branch.Contains("remotes") -And !$BranchesToSave.Contains($BranchEnd)) {
            $BranchesToRemove.Add($Branch)
            Write-Host "Remove ${Branch}" -ForegroundColor Red
            
        }
    }

    $Continue = Read-Host -Prompt "Continue? (y/n)"
    if ('y' -ne $Continue) {
        Write-Host "No commands sent. Exiting script" -ForegroundColor Red
        break;
    }

    foreach ($Branch in $BranchesToRemove) {
        git branch -D $Branch.Trim()
    }
}
Set-Alias runb Read-Report

# Server Functions
function Test-ServerConnection {
    Param(
        [string]$port
    )
    $response = try { 
        (Invoke-WebRequest -Uri "http://localhost:${port}" -ErrorAction Stop).BaseResponse
    }
    catch [System.Net.WebException] { 
        Write-Verbose "An exception was caught: $($_.Exception.Message)"
        $_.Exception.Response 
    } 
    
    $HTTP_Status = [int]$response.StatusCode
    return $HTTP_Status -ne 0;
}
Set-Alias tsc Test-ServerConnection

function Stop-ServiceOnPort {
    param(
        $port
    )
    
    $FoundProcesses = netstat -ano | findstr :$port
    $ActivePortPattern = ":$port\s.+LISTENING\s+\d+$"
    $PidNumberPattern = "\d+$"

    if (-Not ($FoundProcesses | Select-String -Pattern $ActivePortPattern -Quiet)) {
        Write-Error "${port} does not exist"
    }

    $PortMatches = $FoundProcesses | Select-String -Pattern $ActivePortPattern
    $FirstMatch = $PortMatches.Matches.Get(0).Value
    $PidNumber = [regex]::match($firstMatch, $PidNumberPattern).Value

    taskkill /pid $PidNumber /f
}
Set-Alias ssop Stop-ServiceOnPort

function Start-WebServer {
    $ServerPath = ".\application\web\source\"
    $ServerPort = "3000"
    Start-LocalServer $ServerPath $ServerPort
}
Set-Alias sws Start-WebServer

function Start-UserService {
    $ServerPath = ".\service\user\source"
    $ServerPort = "8000"
    Start-LocalServer $ServerPath $ServerPort
}
Set-Alias sus Start-UserService

function Start-LocalServer {
    param(
        $ServerPath,
        $ServerPort
    )
    if (Test-ServerConnection $ServerPort) {
        Stop-ServiceOnPort $ServerPort
    }
    Set-FilePath
    Set-Location $ServerPath
    npm start
}
Set-Alias sls Start-LocalServer

function Start-Servers() {
    $UserServicePort = 3000
    $WebServerPort = 8000

    if (Test-ServerConnection $UserServicePort) {
        Stop-ServiceOnPort $UserServicePort
    }
    if (Test-ServerConnection $WebServerPort) {
        Stop-ServiceOnPort $WebServerPort
    }

    Start-Process powershell -ArgumentList "Start-WebServer"
    Start-Process powershell -ArgumentList "Start-UserService"

    while (-Not (Test-ServerConnection $UserServicePort -And Test-ServerConnection $WebServerPort)) {
        Write-Host "Waiting for servers to start..."
        Start-Sleep -Seconds 1
    }
}
Set-Alias ss Start-Servers

# Integration Test Functions
function Read-Report {
    Param (
        $Report = "all"
    )
    
    $AllReportsPath = ".\application\web\test\build\reports\tests\", ".\service\stripe\test\build\reports\tests", ".\service\user\test\build\reports\tests"

    function Open-Report {
        param (
            $Path
        )
        Set-FilePath
        $ReportSuite = Get-ChildItem $Path | Select-Object -First 1
        try { Start-Process "${Path}\${ReportSuite}\index.html" | Write-Host "Report Found ${Path}\${ReportSuite}" -ForegroundColor Green }
        catch { Write-Error -Message "No Report found in ${Path}\${ReportSuite}\index.html" -Category ResourceUnavailable }
    }

    if ("all" -like $Report) {
        foreach ($ReportPath in $AllReportsPath) {
            Open-Report $ReportPath
        }
    }
    elseif ("webapp" -like $Report) {
        Open-Report $AllReportsPath[0]
    }
    elseif ("stripe" -like $Report) {
        Open-Report $AllReportsPath[1]
    }  
    elseif ("user" -like $Report) {
        Open-Report $AllReportsPath[2]
    }
    else {
        Write-Error -Message "Error: No Report found for ${Report}" -Category InvalidArgument
    }
}
Set-Alias rdrep Read-Report

function Invoke-IntegrationTests {
    Param(
        [string]$TestSuite = "deployment",
        [string]$StartServers = ''
    )
    $AllTestPaths = ".\application\web\test\", ".\service\stripe\test\" , ".\service\user\test\"
    Set-FilePath
    
    if ('y' -eq $StartServers) {
        Start-Servers
    }
    elseif ('' -eq $StartServers) {
        $StartServers = Read-Host -Prompt "Would you like to start all servers? (y/n)"
        if ('y' -eq $StartServers) {
            Start-Servers
        }
    }
    
    foreach ($TestPath in $AllTestPaths) {
        Write-Host ""
        Write-Host "Running ${TestSuite} tests in ${TestPath}"
        Set-Location $TestPath
        .\gradlew clean $TestSuite
        Set-FilePath
    }
}
Set-Alias iit Invoke-IntegrationTests

# Helper Functions
function Update-DevsScript {
    Set-FilePath
    $CurrentLocation = Get-Location
    $ProfileLength = $Profile.Length
    $ProfilePath = $Profile.Substring(0, $ProfileLength - 32)
    $ScriptFilePath = "${ProfilePath}Modules\TekEggDeveloperScript"
    $CopyPath = "${CurrentLocation}\scripts\TekEggDeveloperScript.psm1"
    try {
        Copy-Item $CopyPath -Destination $ScriptFilePath -Force
        Import-Module -Name "TekEggDeveloperScript.psm1" -Force -WarningAction SilentlyContinue
        Write-Host "Script has been updated and reloaded." -ForegroundColor Green
    }
    catch {
        "Script failed to update an error has occurred"
    }
}
Set-Alias uds Update-DevsScript

function Install-DeveloperApplicatons {
    $ChocoInstalled = Test-Path -Path "$env:ProgramData\Chocolatey"
    if (!$ChocoInstalled) {
        try {
            [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; Invoke-Expression ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
        }
        catch {
            Write-Warning "An error has occured please ensure your Execution Policy is set to 'RemoteSigned'."
        }
    }
    else {
        Write-Warning "Chocolatey already installed."
    }
    
    $Packages = 'poshgit', 'openjdk17', 'nodejs', 'mysql', 'python', 'gradle', 'intellijidea-community', 'vscode', 'visualstudio2019community', 'postman'
    
    ForEach ($PackageName in $Packages) {
        choco install $PackageName -y
    }
    
    
}
Set-Alias isdvapp Install-DeveloperApplicatons

function Set-FilePath {
    [string]$CurrentLocation = Get-Location
    $EndIndex = $CurrentLocation.IndexOf("tekegg");
    if ($EndIndex -eq -1) {
        Write-Host "Unable to run script not in tekegg directory." -ForegroundColor Red
        break
    }
    $NewPath = $CurrentLocation.Substring(0, $EndIndex + "tekegg".Length)
    Set-Location "${NewPath}"
}
Set-Alias sfp Set-FilePath


Export-ModuleMember -Function * -Alias *