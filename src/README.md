# Chocolate Shop
This is a solution for Stride Consulting company code test. 

### Requirement
* Java 8
 
### Setup 
Unzip folder and navigate to the directory.

In your terminal, generate your IntelliJ project files:
```
gradlew clean
```
After opening IntelliJ, you click on the refresh button on the Gradle tab.

Enabling Lombok: 
```
Go to settings > Plugins > Search for Lombok plugin > Install plugin > Restart Intellij
```
```
Go to settings > Build > Compiler > Annotation Processors.
Enable Annotation Processing.
```

If you have issues with set up, try these:
- Try running gradlew clean
- Go to File -> Invalidate cache restart
- Update to latest version of Intellij

### Running application
Run main method in ChocolateShopController (Ctrl-Shift-F10 if using Intellij) 

The input.csv file has been added in the src/main/java/resources directory

The output.csv file that is produced should appear under the /out directory.


### Running tests
- cd into stride directory
- Run ./gradlew test

# Design Justifications

#### Domain driven design
I tried to design it so terms, concepts and behavior is driven by the domain and the models reflect the **domain language**. 

Since Chocolates and chocolate wrappers, have the same underlying information, I tried to name it more generically as **ChocolateProduct**.

I called the Chocolate type (milk, dark..) as **ChocolateFlavorType** to avoid confusion between differentiating between chocolates and chocoalate wrappers (they are different types of chocolate products), and chocolate flavor types.

 The **ChocolateFlavorType** is an enum that contains the types of chocolate flavors. Since each **promotion rule** is assigned to a flavor type, it made sense to put the promotion rules there. 


#### Loose coupling
I tried to apply this concept. E.g. if the promotion rules change, just the map of the actual chocolate flavor to counts would need to change and nothing else.  

Things **could be more generic** but I decided not to as it would be over engineering the current problem. 

#### State and behavior
**Shopper** is designed as such as I feel a **Shopper should know their order**, and **the number of Chocolates and Wrappers** they have. 

Noone is allowed to modify the state directly. The shopper's **state is encapsualted** and hidden. It **exposes methods to receive** data to modify it 's internal state.


##### Use of command pattern for adding chocolate

##### Use of enum for chocolate flavor type


##### Use of Lombok
I included the use of [Lombok](https://www.baeldung.com/intro-to-project-lombok)as I feel it makes the code more concise and easier to understand.
