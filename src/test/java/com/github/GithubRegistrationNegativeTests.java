package com.github;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

public class GithubRegistrationNegativeTests extends BaseTest {

    @Test(description = "GitHub Registration Email Validation - Negative Test")
    public void testGitHubRegistrationEmailValidationNegative() {
        driver.get("https://github.com");

        WebElement signUpButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign up")));
        signUpButton.click();

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        emailField.sendKeys("invalidEmail");

        WebElement validationError = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email-err")));
        wait.until(ExpectedConditions.textToBePresentInElement(validationError, "Email is invalid or already taken"));
    }


    @Test(description = "GitHub Registration Password Validation - Negative Test")
    public void testGitHubRegistrationPasswordValidationNegative() {
        driver.get("https://github.com");

        WebElement signUpButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign up")));
        signUpButton.click();

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        emailField.sendKeys("some-super-random-emailaddress4433123@example.com");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-continue-to=password-container]")));
        continueButton.click();

        String[] passwords = {
            "abdkawie",
            "0001231123122",
            "a",
            "aaaaaaaaaa".replace("a", "bbbbbbbbbb"),
        };

        String[] errors = {
            "Password needs a number and lowercase letter",
            "Password needs a number and lowercase letter",
            "Password is too short",
            "Password is too long",
        };

        for (int i = 0; i < passwords.length; i++) {
            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
            passwordField.sendKeys(passwords[i]);

            WebElement validationError = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("password-err")));
            wait.until(ExpectedConditions.textToBePresentInElement(validationError, errors[i]));
            passwordField.clear();
        }
    }

    @Test(description = "GitHub Registration Username Validation - Negative Test")
    public void testGitHubRegistrationUsernameValidationNegative() {
        driver.get("https://github.com");

        WebElement signUpButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Sign up")));
        signUpButton.click();

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
        emailField.sendKeys("some-super-random-emailaddress4433123@example.com");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-continue-to=password-container]")));
        continueButton.click();

        WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
        passwordField.sendKeys("SomeSuperStrongPassword123!");

        continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-continue-to=username-container]")));
        continueButton.click();

        WebElement usernameField = wait.until(ExpectedConditions.elementToBeClickable(By.id("login")));
        usernameField.sendKeys("1234");

        WebElement validationError = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login-err")));
        wait.until(ExpectedConditions.textToBePresentInElement(validationError, "Username 1234 is not available"));
    }
}