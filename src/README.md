# Chocolate Shop
This is a solution for Stride Consulting company code test. Please see bottom section for code test instructions and requirements.

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

### Running tests



# Design Justifications

### Domain driven design
- terms/concepts/behavior driven by domains, doesn't need translation
- models should reflect domain. 
- only behavior that is relevant to the problem is incorporated

### Object Design
##### Loose coupling
- Tried not to couple objects tightly
##### State and behavior
- state - ojbect's attributes and values at a given point
- behavior - modifies the state
- if we look at method of processing an order, we see there are many steps invovled. However rest of the world only needs to understand the order has been processed.  "processOrder" is the behavior that can be called on an shopper to process the shopper's order. 
- Noone should be able to set the interal state directly, instead we would expose the methods responsible for doing that to the outside world.
##### Unidirectional association
- Reusability is reduced when objects are associated with each other bidrectionaly. 
- E.g. Redemption doesn't need to know about shopper. 
- there are bidreictional relationships, but given the context, did not want to deal with it prematurely. 
- Using interaces so coupling isn't concrete?

##### Use of command pattern for adding chocolate

##### Use of enum for chocolate flavor type


##### Use of Lombok
I included the use of [Lombok](https://www.baeldung.com/intro-to-project-lombok)as I feel it makes the code more concise and easier to understand.
# Stride Code Test Details













### Requirements
 - You may complete the test in a language of your choice, though Java and Ruby are preferred.
 - Please refrain from using third party libraries in your solution, with one exception that you may use testing related
 libraries.
 - Please submit your solution in a zip file. We will not accept Github forks or links to code on Github or any similar
 host.
 - Please tell us what language you used to implement your solution.

## The Problem

There is a chocolates store that sells white, dark, milk and sugar free chocolates bars. When a shopper places an order for chocolates, store staff specify the price of the chocolates and the number of wrappers that must be returned in order to receive free chocolateProduct. The price of the chocolates and the number of wrappers required to receive a free chocolates changes with every order as the shop is still experimenting with how the promotion should work.

### Promotion Rules

- When a shopper trades the required number of:
  - `milk` wrappers they will receive one complimentary `milk` chocolates and one complimentary `sugar free` chocolates.
  - `white` wrappers they will receive one complimentary `white` chocolates and one complimentary `sugar free` chocolates.
  - `sugar free` wrappers they will receive one complimentary `sugar free` chocolates and one complimentary `dark`
  chocolates.
  - `dark` wrappers they will receive one complimentary `dark` chocolates.

### The Orders File

Orders placed by different shoppers can be found in the CSV file `input/orders.parser`. The first line of this file
contains CSVRecordHeader information, each subsequent line represents an order. The CSVRecordHeader format is:

    cash, price, wrappers needed, type

- Cash: the amount of cash the shopper has to spend on chocolates.

- Price: the price of a single chocolates.

- Wrappers needed: is the number of wrappers that must be traded in, in order to receive free chocolates. This number
applies to all chocolateProduct irrespective of its type.

- Type: the type of chocolates the shopper is buying in that order.

There are four orders in the `input/orders.parser` file. Every line in `input/orders.parser` is a separate order with a
different `price` and number of `wrappers needed`. Orders are independent of each other, the `cash`, `price` and
`wrappers needed` of one order does not affect any other order.

### Instructions

Write a program which reads the contents of `input/orders.parser`. Using the promotion rules, your program is to generate
output to a file named `output/redemptions.parser`. Each line in `input/orders.parser` is to result in a line in
`output/redemptions.parser`. Each line in the output file should have the format:

    milk N, dark N, white N, sugar free N

Where `N` is the number of chocolateProduct of that type they possess at the conclusion of all possible trades. You
are to assume that all shoppers trade everything they can and therefore redeem as much chocolates as they possibly can.

Given the data in `input/orders.parser`, a correct program will generate the following output:

    milk 7, dark 0, white 0, sugar free 1
    milk 0, dark 3, white 0, sugar free 0
    milk 0, dark 3, white 0, sugar free 5
    milk 0, dark 1, white 5, sugar free 3

### Example

#### input/orders.parser
    cash, price, wrappers needed, type

    14, 2, 6, 'milk'

Using the above order as an example, the shopper has $14. Each chocolates costs $2 so the shopper is able to buy 7
`milk` chocolateProduct ($14/$2). The promotion states that the shopper will receive a complimentary `milk` chocolates for
every 6 wrappers traded in (because `wrappers needed` is 6 for this order). The shopper has 7 wrappers, so can trade in
6 wrappers and receives a complimentary `milk` chocolates. Additionally, the shopper is given a complimentary
`sugar free` chocolates. The shopper will end up with 8 `milk` chocolateProduct and 1 `sugar free` chocolates.

In this example, the file `output/redemptions.parser` should contain the following:

#### output/redemptions.parser

    milk 8, dark 0, white 0, sugar free 1

### More examples

#### input/orders.parser

    Input: 12, 2, 5, 'milk'

#### output/redemptions.parser

    Output: milk 7, dark 0, white 0, sugar free 1

The shopper buys 6 `milk` chocolateProduct and trades in 5 wrappers to get 1 free `milk` chocolates. They also get one
`sugar free`.

-

#### input/orders.parser
    Input: 12, 4, 4, 'dark'

#### output/redemptions.parser
    Output: milk 0, dark 3, white 0, sugar free 0

The shopper buys 3 `dark` chocolateProduct, but since `wrappers needed` is 4 they can't trade any wrappers in.

-

#### input/orders.parser
    Input: 6, 2, 2, 'sugar free'

#### output/redemptions.parser
    Output: milk 0, dark 3, white 0, sugar free 5

The shopper can buy 3 `sugar free` chocolateProduct for $6. They can trade 2 of their 3 wrappers and get 1 complimentary
`sugar free` chocolates and 1 complimentary `dark` chocolates. The shopper now has 4 `sugar free` chocolateProduct, and one
`sugar free` wrapper left from the first trade. They use the leftover wrapper and the wrapper from the complimentary
`sugar free` chocolates to get another complimentary `sugar free` chocolates and a complimentary `dark` chocolates. At this
point the shopper has a total of 5 `sugar free` chocolateProduct. And since they got 2 `dark` chocolateProduct, they can trade both
of those in for an extra `dark`. Final total: 5 `sugar free` chocolateProduct, 3 `dark` chocolateProduct.

-

#### input/orders.parser
    Input: 6, 2, 2, 'white'

#### output/redemptions.parser
    Output:  milk 0, dark 1, white 5, sugar free 3

The shopper buys 3 `white` and trades in 2 `white` wrappers for 1 `white` and 1 `sugar` free. Now they can use the extra
`white` wrapper to get another `white` and another `sugar free`. Those 2 `sugar free` wrappers get another `sugar free`
and a `dark`.