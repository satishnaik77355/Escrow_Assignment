# Escrow Stack -- Selenium Automation Assignment

This project contains the Selenium automation script developed as part
of the **QA Assessment for Escrow Stack Pvt. Ltd.**

The script automates a Flipkart product workflow, applies filters,
handles multiple scenarios, captures screenshots, and validates product
information using Selenium WebDriver.



##  Automation Tasks Covered

-   ✔ Navigate to Flipkart\
-   ✔ Close login popup\
-   ✔ Search for **Bluetooth Speakers**\
-   ✔ Apply filters: boAt + 4★ & above\
-   ✔ Sort → Price: Low to High\
-   ✔ Open first product in new tab\
-   ✔ Validate "Available offers" count\
-   ✔ Add-to-cart & negative scenario handling\
-   ✔ Screenshot capture


## Scenario Handling

### **Scenario 1 -- Product Available**

-   Add to Cart present & enabled\
-   Click Add to Cart\
-   Navigate to Cart\
-   Screenshot → `cart_result.png`

### **Scenario 2 -- Product Not Available**

Conditions: - Add to Cart missing\
- OR disabled\
- OR Out of Stock

Then: - Print message\
- Screenshot → `result.png`



## Tech Stack

  Component    Version / Tool
  ------------ --------------------
  Language     Java 8+
  Automation   Selenium WebDriver
  Browser      Chrome
  Driver       ChromeDriver
  Build Tool   JAR files (manual)
  IDE          Eclipse



##  How to Run

1.  Install ChromeDriver\
2.  Add Selenium JARs to Build Path\
3.  Update driver path in script\
4.  Run Java class as Application


##  Screenshots

-   `cart_result.png`\
-   `result.png`


