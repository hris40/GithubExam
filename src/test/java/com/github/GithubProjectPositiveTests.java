package com.github;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class GithubProjectPositiveTests extends BaseTest {

    private void login() {
        driver.get("https://github.com");

        WebElement signInButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign in")));
        signInButton.click();

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("login_field")));
        emailField.sendKeys("bepokok692@jadsys.com");

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        passwordField.sendKeys("kdoqwkoASdk123");

        passwordField.submit();

        driver.get("https://github.com");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("global-create-menu-anchor")));
    }

    private void createRepository(String name) {
        WebElement createRepositoryAnchorButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("global-create-menu-anchor")));
        createRepositoryAnchorButton.click();

        WebElement createRepositoryButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("New repository")));
        createRepositoryButton.click();

        WebElement nameLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label//*[contains(text(), 'Repository name')]/ancestor::label")));
        WebElement nameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(nameLabel.getAttribute("for"))));
        nameInput.sendKeys(name);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("RepoNameInput-is-available")));
        nameInput.submit();
        wait.until(ExpectedConditions.urlToBe("https://github.com/existing-username/" + name));
    }

    private void deleteRepository(String name) {
        WebElement settingsButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Settings")));
        settingsButton.click();

        WebElement deletePromptButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dialog-show-repo-delete-menu-dialog")));
        deletePromptButton.click();

        WebElement deleteButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("repo-delete-proceed-button")));
        deleteButton.click();

        WebElement proceedButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("repo-delete-proceed-button")));
        proceedButton.click();

        WebElement verificationField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("verification_field")));
        verificationField.sendKeys("existing-username/"+name);
        verificationField.submit();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(), 'Your repository \"existing-username/" + name + "\" was successfully deleted.')]")));
    }

    private void renameRepository(String newName) {
        WebElement settingsButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Settings")));
        settingsButton.click();

        WebElement repoNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("rename-field")));
        repoNameField.clear();
        repoNameField.sendKeys(newName);
        repoNameField.submit();

        wait.until(ExpectedConditions.urlToBe("https://github.com/existing-username/" + newName));
    }

    @Test(description = "GitHub Project Create - Positive Test")
    public void testGitHubProjectCreatePositive() {
        login();
        createRepository("test123");
        deleteRepository("test123");
    }

    @Test(description = "GitHub Project Rename - Positive Test")
    public void testGitHubProjectRenamePositive() {
        login();
        createRepository("test123");
        renameRepository("test123456");
        deleteRepository("test123456");
    }
}