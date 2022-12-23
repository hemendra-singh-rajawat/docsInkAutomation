package com.baseFramework.Lib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import dev.failsafe.Timeout;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class AppLibrary {

	public final long GLOBALTIMEOUT = 75;
	private static WebDriver driver;
	private static Configuration config;
	public String baseUrl;
	public String browser;
	public String device;
	private String currentTestName;
	private String currentSessionID;

	public AppLibrary(String testName) {
		this.currentTestName = testName;
		config = new Configuration();
	}

	public static Configuration getConfig() {
		if (config == null) {
			config = new Configuration();
		}

		return config;
	}

	public WebDriver getDriverInstance() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		String browserVersion, os, browserStackOSVersion, remoteGridUrl, environment;

		this.browser = config.getBrowserName();
		baseUrl = config.getURL();
		environment = config.getExecutionEnvironment();

		switch (environment) {

		case "browserstack":
			browserStackOSVersion = config.getBrowserStackOSVersion();
			browserVersion = config.getBrowserVersion();
			os = config.getOS();

			if (config.getBrowserName().equalsIgnoreCase("IE")) {
				caps.setCapability("browser", "IE");
			} else if (config.getBrowserName().equalsIgnoreCase("GCH")
					|| config.getBrowserName().equalsIgnoreCase("chrome")) {
				caps.setCapability("browser", "Chrome");
			} else if (config.getBrowserName().equalsIgnoreCase("safari")) {
				caps.setCapability("browser", "Safari");
			} else {
				caps.setCapability("browser", "Firefox");
			}

			if (browserVersion != null && !browserVersion.equals("") && !browserVersion.equals("latest")) {
				caps.setCapability("browser_version", browserVersion);
			}

			if (browserStackOSVersion != null) {
				caps.setCapability("os", os);
				if (os.toLowerCase().startsWith("win")) {
					caps.setCapability("os", "Windows");
				} else if (os.toLowerCase().startsWith("mac-") || os.toLowerCase().startsWith("os x-")) {
					caps.setCapability("os", "OS X");
				}

				if (os.equalsIgnoreCase("win7")) {
					browserStackOSVersion = "7";
				} else if (os.equalsIgnoreCase("win8")) {
					browserStackOSVersion = "8";
				} else if (os.equalsIgnoreCase("win8.1") || os.equalsIgnoreCase("win8_1")) {
					browserStackOSVersion = "8.1";
				} else if (os.toLowerCase().startsWith("mac-") || os.toLowerCase().startsWith("os x-")) {
					browserStackOSVersion = os.split("-")[1];
				}
				caps.setCapability("os_version", browserStackOSVersion);
			}
			caps.setCapability("resolution", "1920x1080");
			caps.setCapability("browserstack.debug", "true");
			caps.setCapability("build", System.getProperty("Build"));
			caps.setCapability("project", System.getProperty("Suite"));
			caps.setCapability("name", currentTestName);

			try {
				driver = new RemoteWebDriver(new URL("http://" + config.getBrowserStackUserName() + ":"
						+ config.getBrowserStackAuthKey() + "@hub.browserstack.com/wd/hub"), caps);
				((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
			} catch (Exception e) {
				autoLogger("Issue creating new driver instance due to following error: " + e.getMessage() + "\n"
						+ e.getStackTrace());
				throw e;
			}

			break;

		case "seleniumgrid":
			autoLogger("Remote execution set up on URL: " + config.getRemoteGridUrl());
			remoteGridUrl = config.getRemoteGridUrl();
			caps.setBrowserName("chrome");
			caps.setPlatform(Platform.LINUX);
			String url = "http://" + remoteGridUrl + ":4444/wd/hub";
			autoLogger("===================================" + "\n" + "URL:" + url);
			driver = new RemoteWebDriver(new URL(url), caps);
			((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
			break;

		case "local":

			if (config.getBrowserName().equalsIgnoreCase("GCH") || config.getBrowserName().equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--test-type");
				options.addArguments("--disable-extensions");
				options.addArguments("--start-maximized");
				driver = new ChromeDriver(options);
			} else if (config.getBrowserName().equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
//				System.setProperty("webdriver.firefox.profile", "default");
				driver = new FirefoxDriver();
			}

			else {
				WebDriverManager.safaridriver().setup();
				driver = new SafariDriver();

			}
			break;
		}

		// driver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GLOBALTIMEOUT));
		driver.manage().window().maximize();
		return driver;
	}

	public void launchApp() {
		// Delete cookies and Launch the Application
		driver.manage().deleteAllCookies();
		baseUrl = config.getURL();
		driver.get(baseUrl);

		// Maximize the browser
		driver.manage().window().maximize();
		waitForPageToLoad();
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void launchApp(String url) {
		// Delete cookies and Launch the Application
		driver.manage().deleteAllCookies();

		driver.get(url);
		waitForPageToLoad();

		// Maximize the browser
		driver.manage().window().maximize();
	}

	public WebDriver getCurrentDriverInstance() {
		return driver;
	}

	public void closeBrowser() {
		if (driver != null)
			driver.quit();
	}

	public By getLocatorBy(String locator) {
		By locatorBy = null;
		String string = locator;
		System.out.println(string);
		String[] parts = string.split(":-:");
		String type = "xpath"; // 004
		String object = parts[1];

		if (type.equals("id")) {
			locatorBy = By.id(object);
		} else if (type.equals("name")) {
			locatorBy = By.name(object);
		} else if (type.equals("class")) {
			locatorBy = By.className(object);
		} else if (type.equals("link")) {
			locatorBy = By.linkText(object);
		} else if (type.equals("partiallink")) {
			locatorBy = By.partialLinkText(object);
		} else if (type.equals("css")) {
			locatorBy = By.cssSelector(object);
		} else if (type.equals("xpath")) {
			locatorBy = By.xpath(object);
		} else {
			autoLogger("Please provide correct element locating strategy" + locator);
			throw new RuntimeException("Please provide correct element locating strategy" + locator);
		}
		return locatorBy;
	}

	public WebElement getElement(String locatorString) {
		By locatorBy = null;
		int counter = 0;
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		autoLogger("Finding element using: " + locatorString);
		locatorBy = getLocatorBy(locatorString);

		for (; counter < 20; counter++) {
			try {
				autoLogger("Finding element, try" + counter + " with locator:" + locatorString);
				return driver.findElement(locatorBy);
			} catch (Exception e) {
				System.out.println(e);
				sleep(2000);
			}
		}

		throw new RuntimeException("Element not found: " + locatorString);

	}

	public WebElement findElement(String locatorString) {
		WebElement element = getElement(locatorString);
		return element;
	}

	public WebElement getElementByXpath(String locatorString) {

		int counter = 0;
		// driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		autoLogger("Finding element using: xpath:" + locatorString);

		for (; counter < 20; counter++) {
			try {
				autoLogger("Finding element, try" + counter + " with locator: xpath:" + locatorString);
				return driver.findElement(By.xpath(locatorString));
			} catch (Exception e) {
				sleep(2000);
			}
		}

		throw new RuntimeException("Element not found: xpath:" + locatorString);

	}

	public WebElement findElementByXpath(String locatorString) {
		WebElement element = getElementByXpath(locatorString);
		return element;
	}

	public List<WebElement> findElements(String locatorString) {
		By locatorBy = null;
		List<WebElement> elements = null;
		locatorBy = getLocatorBy(locatorString);
		elements = driver.findElements(locatorBy);
		return elements;
	}

	public void selectElement(WebElement element, String option) throws Exception {
		Select select = new Select(element);
		select.selectByVisibleText(option);
	}

	public boolean syncProgress() throws Exception {

		int loadCounter = 10;

		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		while (loadCounter > 0) {

			try {
				driver.findElement(
						By.xpath("//div[@id='MainContent_panelUpdateProgress'][contains(@style, 'display: none')]"));
				// driver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GLOBALTIMEOUT));
				return true;
			} catch (Exception e) {
				loadCounter--;
			}

		}

		// driver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GLOBALTIMEOUT));
		throw new Exception("Progress was not completed withing specified time");

	}

	public void selectByPartOfVisibleText(WebElement element, String value) {
		boolean flag = true;
		List<WebElement> optionElements = element.findElements(By.tagName("option"));
		Select select = new Select(element);
		for (WebElement optionElement : optionElements) {
			if (optionElement.getText().contains(value)) {
				String optionIndex = optionElement.getAttribute("index");
				select.selectByIndex(Integer.parseInt(optionIndex));
				flag = false;
				break;
			}
		}
		if (flag) {
			Assert.assertTrue("Option " + value + " was not found in the select", false);
		}
	}

	public void verifyElement(String locatorString, boolean checkVisibility, long timeOutInSeconds) throws Exception {
		if (checkVisibility) {
			boolean isDisplayed = (findElement(locatorString).isDisplayed());
			if (isDisplayed == false) {
				throw new Exception("Element Not Visible Exception");
			}
		} else {
			// driver.manage().timeouts().implicitlyWait(timeOutInSeconds,
			// TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GLOBALTIMEOUT));
			findElement(locatorString);
		}
		// driver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GLOBALTIMEOUT));
	}

	public void sleep(long milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getCurrentSessionID() {
		return currentSessionID;
	}

	public void waitForPageToLoad() {
		new WebDriverWait(driver, Duration.ofSeconds(GLOBALTIMEOUT)).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));

	}

	public void selectDeselectCheckBox(String locator, boolean selectCheckBox) {

		if (selectCheckBox) {
			if (!findElement(locator).isSelected())
				findElement(locator).click();
		} else if (findElement(locator).isSelected())
			findElement(locator).click();
	}

	public void clickElement(String locator) throws Exception {

		int i = 0;
		do {
			try {
				findElement(locator).click();
				break;
			} catch (Exception e) {
				sleep(1000);
				i++;
				continue;
			}

		} while (i < 2);

		if (i >= 2) {
			throw new Exception("Failed to click element, Locator: " + locator);
		}
	}

	public void clickElementWithJs(String locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		By by = getLocatorBy(locator);
		WebElement element = driver.findElement(by);
		js.executeScript("arguments[0].click();", element);
	}

	public void enterText(String locator, String text) throws Exception {
		WebElement element = findElement(locator);
		element.click();
		element.clear();
		element.sendKeys(text);
	}

	public void enterImage(String locator, String text) throws Exception {
		WebElement element = findElement(locator);
		// element.click();
		// element.clear();
		// Thread.sleep(5000);
		element.sendKeys(text);

		sleep(5000);
	}

	public boolean verifyCheckBox(String locator) {
		return findElement(locator).isSelected();
	}

	public void waitForNavigation(String url) {
		int counter = 10;
		for (; counter > 0; counter--) {
			if (driver.getCurrentUrl().contains(url)) {
				break;
			} else {
				sleep(10000);
			}
		}
	}

	public void switchToWindow(int windowNo) {
		Set<String> set = driver.getWindowHandles();
		String windowHandle = null;
		autoLogger("Current no. of windows are: " + set.size());
		if (windowNo <= set.size()) {
			ArrayList<String> windows = new ArrayList<String>(set);
			windowHandle = windows.get(windowNo - 1);
		}

		if (windowHandle != null) {
			driver.switchTo().window(windowHandle);
		} else {
			throw new RuntimeException("Specified window not available");
		}
	}

	public String getFormattedDate() {
		return getDate().replaceAll("/", "_").replaceAll(":", "_").replaceAll(" ", "_");
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
		Date date = new Date();
		autoLogger(dateFormat.format(date));
		return dateFormat.format(date);
	}

	public boolean waitTillElementLoaded(String locator) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		int counter = 10;
		do {
			try {
				if (findElement(locator) != null) {
					return true;
				} else {
					sleep(1000);
					counter--;
				}
			} catch (Exception e) {
				sleep(3000);
				counter--;
				continue;
			}
		} while (counter > 0);
		// driver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GLOBALTIMEOUT));
		throw new RuntimeException("element was not loaded:" + locator);
	}

	public boolean waitTillElementClickable(String locator) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		int counter = 10;
		do {
			try {
				if (findElement(locator) != null) {
					WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
					wait.until(ExpectedConditions.elementToBeClickable(findElement(locator)));		
					return true;
					}else {
						sleep(1000);
						counter--;
				}
			}
		catch (Exception e) {
				sleep(3000);
				counter--;
				continue;
			}
		} while (counter > 0);
		// driver.manage().timeouts().implicitlyWait(GLOBALTIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GLOBALTIMEOUT));
		throw new RuntimeException("element was not loaded:" + locator);
	}
	public static void autoLogger(String message) {
		Reporter.log(message, true);
	}

	public void getScreenshot(String name) throws IOException {
		driver = getCurrentDriverInstance();
		String path = "screenshots/" + name;
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(path));
		autoLogger("screenshot at :" + path);
		autoLogger("screenshot for " + name + " available at :" + path);
	}

	public String[][] readExcel(String excelFilePath, int sheetNo) throws BiffException, IOException {
		File file = new File(excelFilePath);
		String inputData[][] = null;
		Workbook w;

		try {
			w = Workbook.getWorkbook(file);

			// Get the sheet
			Sheet sheet = w.getSheet(sheetNo);

			int colcount = sheet.getColumns();
			int rowcount = sheet.getRows();
			int countYes = 0;

			for (int i = 0; i < rowcount; i++) {
				if (sheet.getCell(colcount - 1, i).getContents().equalsIgnoreCase("Yes")) {
					countYes = countYes + 1;

				}
			}

			inputData = new String[countYes][colcount];
			int k = 0;
			for (int i = 0; i < rowcount; i++) {
				if (sheet.getCell(colcount - 1, i).getContents().equalsIgnoreCase("Yes")) {

					for (int j = 0; j < colcount; j++) {
						Cell cell = sheet.getCell(j, i);
						inputData[k][j] = cell.getContents().trim();

					}
					k = k + 1;
				}

			}

		} catch (BiffException e) {
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return inputData;
	}

	public void scroll(String locator) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		By by = getLocatorBy(locator);
		WebElement element = driver.findElement(by);
		// This will scroll the page till the element is found
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		System.out.println("Scrolled into View");
	}

	public void scrollTop() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(document.body.scrollHeight,400)");
	}

	public void scrollIframe() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(400,400)");
	}

	public void switchToFrame(String locatorString) {
		driver.switchTo().frame(locatorString);
	}

	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}

	public void handleAlertBox() throws Exception {
		driver.switchTo().alert().accept();
	}

	public void dismissAlertBox() throws Exception {
		driver.switchTo().alert().dismiss();
	}

	public void createFile(String path, String filename) throws IOException {
		try {
			File neo = new File(path + File.separator + filename);
			System.out.println(path + File.separator + filename);
			neo.createNewFile();
			neo = null;
		} catch (IOException e) {
			System.out.println("===========File creation failed, Path: " + path + File.separator + filename);
			e.printStackTrace();
			throw e;
		}
	}

	public void writeToFile(String data, String path, String fileName) throws Exception {

		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			File neo = new File(path + File.separator + fileName);
			fw = new FileWriter(neo.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(data);
			bw.newLine();
			bw.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("===========Exception in writing data to file");
			e.printStackTrace();
			throw e;
		} finally {
			if (bw != null) {
				bw.close();
			}
			if (fw != null) {
				fw.close();
			}
		}
	}

	public static void cleanDirectory(String path) {

		try {
			FileUtils.cleanDirectory(new File(path));
		} catch (IOException e) {
			System.out.println("===========Exception in cleaning directory");
			e.printStackTrace();
		}

	}

	public Object[][] readText(String filePath) throws Exception {

		String inputData[][] = null;
		Scanner myReader = null;

		try {
			File myObj = new File(filePath);
			myReader = new Scanner(myObj);
			List<String> fileData = new ArrayList<>();
			String data = null;
			int row = 0;
			int col = 0;

			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
				if (!data.equalsIgnoreCase("")) {
					row++;
					fileData.add(data);
				}
			}

			col = data.split("\\|\\|").length;

			inputData = new String[row][col];
			row = 0;
			for (String string : fileData) {

				String[] lineData = string.split("\\|\\|");
				if (col == lineData.length) {

					for (int j = 0; j < lineData.length; j++) {
						inputData[row][j] = lineData[j];
					}

				} else {
					System.out.println("========column count does not match text file data");
					throw new Exception("column count does not match text file data");
				}

				row++;
			}

			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			myReader.close();
		}

		return inputData;

	}

	public void SwitchWindow() {
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();
			driver.switchTo().window(child_window);
		}
	}

	public void dropDown() {
		List<WebElement> ll = driver.findElements(By.xpath("//ul[@id='select2-rpm_items-results']"));
		System.out.println(ll.size());

		for (int i = 0; i < ll.size(); i++) {

			String s = ll.get(i).getText();
			System.out.println(s);
		}

	}

	public void selectOption(String xpath, int index) {
		WebElement element = driver.findElement(By.xpath(xpath));
		Select objSelect = new Select(element);
		objSelect.selectByIndex(index);
	}

	public void verification(String locator, String expectedString) {
		String actualString = findElement(locator).getText();
		Assert.assertEquals(actualString, expectedString);
	}
}