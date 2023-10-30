# YouTube Video Automation

This Java project uses Selenium WebDriver and TestNG to automate actions on YouTube, such as searching for a video, playing it, and skipping ads.

## Prerequisites

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [TestNG](https://testng.org/doc/download.html)
- [Chrome WebDriver](https://sites.google.com/chromium.org/driver/)

## Getting Started

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/your-username/YouTubeVideoAutomation.git

2. Install the required dependencies using Maven:
    ```bash
    cd YouTubeVideoAutomation
    
    mvn clean install

3. Configure WebDriver
   Download the appropriate version of Chrome WebDriver and place it in your project directory or provide its path in your code. Make sure the WebDriver version matches your Chrome browser version.

4. Configure Test Data
  Update the test data and properties in the src/test/resources/config.properties file.

5. Run the tests
   ``` bash
    mvn test

## Test Execution
- The tests automate the following steps:
- Launch the Chrome browser.
- Navigate to the YouTube homepage.
- Search for a video based on the provided search term.
- Click on the video to be played (index specified in data.properties).
- Skip any ad that appears before the video (if a "Skip Ad" button is available).
- Monitor the video's time display and expected duration.
- Close the browser once the video reaches its expected duration.

## Test Modification
- You can also modify the script by changing the the 'Test Data' present in the 'data.properties' located in the 'src/main/resources directory'.

## Test Configuration
- Test configuration and properties can be found in the 'data.properties' file located in the 'src/main/resources directory'. You can customize test data and configurations there.
You can change the ``` Search Query and Video Index

## Test Reporting
- TestNG generates test reports in the 'test-output' directory after test execution. You can view test results and logs there.

