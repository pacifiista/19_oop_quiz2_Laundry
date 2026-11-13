# OOP Practice Quiz 2: Laundry Service

🇮🇩 Versi Bahasa Indonesia: [README.id.md](README.id.md)

Working time: **120 minutes**. Maximum score: **100 points**, graded automatically every time you push.

## Case Study

You are building the core of a **Laundry Service** application. Every laundry order has a kind (**regular laundry** and **express laundry**), and each kind calculates its price by its own rule. Only one kind offers pickup and delivery. A laundry order is valid only when the weight is more than 0 and at most 20 kg. All laundry orders are kept in the **laundry desk**, then paid through the **receipt printer**.

This quiz covers **Meetings 9 to 11**: abstract classes and interfaces, polymorphism and `instanceof`, exceptions, `ArrayList` and `Map`, and the SOLID principles.

## How This Quiz Works

The quiz has **five stages of increasing difficulty**. The order follows the lectures, so start at stage 1.

| Stage | Topic | Points |
|---|---|---:|
| 1 | Warm-up: class and encapsulation | 15 |
| 2 | Abstract class and interface (Meeting 9) | 20 |
| 3 | Polymorphism and exceptions (Meeting 10) | 20 |
| 4 | Collections (Meeting 11) | 15 |
| 5 | SOLID (Meeting 11) | 30 |

- **Stages 1 to 3 are worth 55 points.** If you finish all three, you pass comfortably.
- **Each stage stands on its own.** The tests for stages 3 to 5 use classes made by the tests, not your own classes. If you are stuck on one stage, move on to the next: you can still earn its points.
- **All tests are visible.** Open the `src/test` folder to read what is checked. You may read the tests, but you must not change them.
- **A failing test gives a hint.** The failure message names the rule that was broken.

## Class Diagram

The class diagram is the **main specification**. Every class name, field, constructor, and method you need is there.

![Class Diagram](docs/class-diagram.png)

The colors tell you what to do:

| Color | Meaning |
|---|---|
| Gray (given) | Already complete, do not change it. |
| Yellow (complete the methods) | The class exists. Fill in the method bodies. |
| Green (create this class) | The class does not exist yet. Create it yourself. |

## Business Rules

| Topic | Rule |
|---|---|
| regular laundry (`RegularLaundry`) | price = weight x Rp 6,000 per kg |
| express laundry (`ExpressLaundry`) | price = weight x Rp 9,000 per kg. Offers pickup and delivery (`Deliverable`) for a fixed fee of Rp 5,000. |
| regular laundry | Does not offer pickup and delivery. |
| Invalid weight | Valid only when the weight is more than 0 and at most 20 kg. Otherwise, throw an exception with the message `Invalid weight: ` followed by the value, for example `Invalid weight: 25.0`. |
| Checkout | The laundry desk asks the receipt printer to print the code and the price. |

Use the type `double` for weight, price, and fees.

## Worked Example: Pet Shop

Before you start, look at one example that is already explained: a **pet shop** (a different problem from yours). Its diagram has the same shape as yours.

![Pet shop example](docs/worked-example.png)

The example goes through the stages one by one. Notice the pattern, then apply it to the Laundry Service.

| Stage | In the pet shop | In the Laundry Service |
|---|---|---|
| 1 | `Owner`: keep the owner's name, reject a blank name. | `Customer`: keep the customer's name, reject a blank name. |
| 2 | `Cat` and `Dog` extend `Pet`. Only `Dog` implements `Vaccinatable`. | `RegularLaundry` and `ExpressLaundry` extend `LaundryOrder`. Only `ExpressLaundry` implements `Deliverable`. |
| 3 | `CareCalculator` adds up the care cost of all pets in one loop, checks vaccines with `instanceof Vaccinatable`, and rejects a pet with an invalid age by throwing `InvalidAgeException`. | `OrderCalculator` does the same for laundry orders. |
| 4 | `PetShop` keeps pets in a `List` and finds them by code with a `Map`. | `LaundryDesk` does the same for laundry orders. |
| 5 | `PetShop` receives a `Receipt` from outside through its constructor. It does not create the printer itself, and it does not print itself. A new kind of pet works at once without changing `PetShop`. | `LaundryDesk` receives a `ReceiptPrinter` from outside. A new kind of laundry order works at once without changing `LaundryDesk`. |

## Stage 1: Warm-up (15 points)

**Goal:** complete `Customer` so that it stores data and keeps the name valid.

**What you do:**

1. Declare fields for the code and the name. Every field is `private`.
2. Fill in the constructor and both getters.
3. Fill in `setName`: a name that is `null`, empty, or only spaces is ignored, and the old name stays.

<details>
<summary>Hint</summary>

Fields are written at the top of the class, each starting with `private`. In the constructor, the parameter values are moved into the fields with the word `this`. For a name made only of spaces, remember that `String` has a method that removes spaces at the ends and a method that checks whether the text is empty.
</details>

**How to test:** `mvn test -Dtest="CustomerConstructorGetterTest,CustomerEncapsulationTest"`

## Stage 2: Abstract Class and Interface (20 points)

**Goal:** create two subclasses of `LaundryOrder`, and give an extra ability to only one of them.

**What you do:**

1. Create the class **`RegularLaundry`** that `extends LaundryOrder`. The constructor takes three parameters as in the diagram and passes them to `super`. Fill in `calculatePrice()`: weight x Rp 6,000 per kg.
2. Create the class **`ExpressLaundry`** that `extends LaundryOrder` and `implements Deliverable`. Fill in `calculatePrice()`: weight x Rp 9,000 per kg. Fill in `deliveryFee()`: Rp 5,000.
3. `RegularLaundry` must **not** implement `Deliverable`.

<details>
<summary>Hint</summary>

Create the new class in the same folder as the other classes. A subclass constructor takes three parameters like the abstract class constructor, then calls `super` on its first line. The `@Override` mark helps the compiler confirm that you really fill in the abstract method. An interface is used with the word `implements`, and a class that uses it must fill in every method of that interface.
</details>

**How to test:** `mvn test -Dtest="SubclassStructureTest,RegularLaundryPriceTest,ExpressLaundryPriceTest,AbilityInterfaceTest,AbstractAndInterfaceRulesTest"`

## Stage 3: Polymorphism and Exceptions (20 points)

**Goal:** complete `OrderCalculator` so that it processes many laundry orders through the `LaundryOrder` type, and rejects invalid data with an exception.

**What you do:**

1. `totalPrice`: add up the price of all laundry orders in one loop. An empty list gives 0.
2. `totalDelivery`: add up the pickup and delivery fee of the laundry orders that have that ability. Check with `instanceof Deliverable`, not with a subclass name.
3. `validate`: throw `InvalidWeightException` when the weight is invalid, with the message `Invalid weight: ` followed by the value.
4. `isAcceptable`: call `validate` inside `try`/`catch`. Return `true` when valid and `false` when the exception is thrown.

<details>
<summary>Hint</summary>

One loop is enough. Inside it, call the same method on each object and let each object answer by its own rule: that is polymorphism. For the extra ability, ask whether the object is a `Deliverable` (not whether it is a `ExpressLaundry`), so that other kinds with the same ability are counted too. The `validate` method uses `throw` to throw a new exception. The `isAcceptable` method catches it with `catch` and returns a boolean, so the exception does not leave that method.
</details>

**How to test:** `mvn test -Dtest="TotalPriceTest,TotalAbilityTest,ValidateTest,IsAcceptableTest"`

## Stage 4: Collections (15 points)

**Goal:** complete `LaundryDesk` so that it stores laundry orders and finds them quickly.

**What you do:**

1. Fill in the constructor: store the receipt printer it is given, and prepare empty collections.
2. `add`: store the laundry order in a `List` **and** in a `Map` (the key is the laundry order code).
3. `findById`: look it up through the `Map`. If the code does not exist, return `null`.
4. `count` and `getOrders`: the number and the list in the order they were added.

Do not use arrays.

<details>
<summary>Hint</summary>

Two collections work side by side: the `List` remembers the order, the `Map` finds by key. The `add` method fills both. A `Map` returns `null` for a key that does not exist, so `findById` needs no loop at all. Make every field `private`.
</details>

**How to test:** `mvn test -Dtest="DeskAddCountTest,DeskFindByIdTest,DeskFindMissingTest,DeskOrderTest,DeskCollectionsTest"`

## Stage 5: SOLID (30 points)

**Goal:** apply the SOLID principles in `LaundryDesk` through one method, `checkout`.

**What you do:**

1. Keep the receipt printer from the constructor in a field of the **interface** type `ReceiptPrinter`.
2. `checkout`: find the laundry order by its code, then ask that receipt printer to print the code and the price. If the code does not exist, do nothing.
3. `LaundryDesk` must **not** print anything itself, and must **not** create a `ConsoleReceiptPrinter` inside itself.
4. Do not check subclass names. A new kind of laundry order must work at once without changing `LaundryDesk` or `OrderCalculator`.

The tests of this stage create a new kind of laundry order **inside the tests** to prove that your code does not need to change.

| Principle | How the tests check it |
|---|---|
| Dependency Inversion (DIP) | `LaundryDesk` holds a `ReceiptPrinter` (the interface), not the concrete class. A fake printer from the tests must receive the call. |
| Single Responsibility (SRP) | `LaundryDesk` does not use `System.out`. Printing is the receipt printer's job. |
| Open/Closed (OCP) | A new kind made by the tests is accepted and counted without any change to your code. |
| Liskov Substitution (LSP) | Every subclass of `LaundryOrder`, including one whose price is 0, is treated the same way. |

<details>
<summary>Hint</summary>

The constructor takes an object of an interface type and keeps it in a field of the interface type. In `checkout`, just find the laundry order and hand its code and price to the printer. You do not need to know whether the printer prints to the screen, to a file, or elsewhere: that is the printer's business. For the price, call `calculatePrice()` on the object, whatever its kind.
</details>

**How to test:** `mvn test -Dtest="DeskInterfaceTest,CheckoutUsesPrinterTest,DeskPrintsNothingTest,NewSubclassNoChangeTest,SubstitutionTest,NoConcretePrinterTest"`

## Try It Manually

`Main.java` is a free playground for trying your program (not graded). After stages 2 to 5 are done, you can write an experiment there. As a reference, the correct results for 5 kg are:

- regular laundry: price `30000.0`.
- express laundry: price `45000.0` and pickup and delivery fee `5000.0`.
- The sum of both prices: `75000.0`.
- Checkout of the laundry order numbered `L1` (regular laundry) produces the line: `Receipt L1: Rp 30000.0`.

Run it from NetBeans (**Run Project**), VS Code (**Run** above `main`), or the terminal:

```bash
mvn -q compile exec:java
```

## Getting Started

This repository is a **template**:

1. Click the green **Use this template** button, then **Create a new repository**. Set the visibility to **Private**, and name it, for example, `oop-quiz2-laundry-<your-name>`.
2. Clone **your** new repository:
   ```bash
   git clone git@github.com:<your-username>/oop-quiz2-laundry-<your-name>.git
   ```
3. Open the project in your editor, work stage by stage, then commit and push.
4. Every push runs the autograder. Open the **Actions** tab of your repository to see your score. If GitHub asks you to enable workflows, click **I understand my workflows, enable them**.

## Opening the Project

This is a standard Maven project, with no extra configuration.

**NetBeans**
1. **File, Open Project...**
2. Select the `oop-quiz2-laundry-...` folder (NetBeans recognizes it as a Maven project), then **Open Project**.
3. Right-click the project, then **Test** to run all tests. A single test runs from the `Test Packages` node.

**Visual Studio Code**
1. Install the **Extension Pack for Java** (Microsoft).
2. **File, Open Folder...** and select the cloned folder.
3. Run the tests from the **Testing** sidebar (flask icon), or from the terminal: `mvn test`.

## Reading the Test Results

Run `mvn test` in the terminal, or look at the **Actions** tab. Every failing test writes one sentence that names the **rule that was broken**. Fix one test group at a time, then run that group again. If you see the error `Not implemented yet`, that method is still empty.

## Grading

Total **100 points**, split across 22 test groups. A group earns its points when every test inside it passes. Run everything with `mvn test`, or one group with `mvn test -Dtest=TestClassName`.

| # | Test group | Stage | Points |
|---|---|---|---:|
| 1 | Customer Constructor and Getter Test | 1 | 5 |
| 2 | Customer Encapsulation Test | 1 | 10 |
| 3 | Subclass Structure Test | 2 | 4 |
| 4 | RegularLaundry Price Test | 2 | 4 |
| 5 | ExpressLaundry Price Test | 2 | 4 |
| 6 | Deliverable Interface Test | 2 | 4 |
| 7 | Abstract Class and Interface Rules Test | 2 | 4 |
| 8 | OrderCalculator Total Price (Polymorphism) Test | 3 | 5 |
| 9 | OrderCalculator Deliverable Total (instanceof) Test | 3 | 5 |
| 10 | OrderCalculator Validate (Exception) Test | 3 | 5 |
| 11 | OrderCalculator isAcceptable (try/catch) Test | 3 | 5 |
| 12 | LaundryDesk Add and Count Test | 4 | 3 |
| 13 | LaundryDesk Find By Id Test | 4 | 3 |
| 14 | LaundryDesk Find Missing Returns Null Test | 4 | 3 |
| 15 | LaundryDesk Keeps Insertion Order Test | 4 | 3 |
| 16 | LaundryDesk Uses List and Map Test | 4 | 3 |
| 17 | LaundryDesk Depends on ReceiptPrinter Interface Test (DIP) | 5 | 5 |
| 18 | Checkout Uses Injected ReceiptPrinter Test (DIP) | 5 | 7 |
| 19 | LaundryDesk Prints Nothing Itself Test (SRP) | 5 | 4 |
| 20 | New Subclass Needs No Change Test (OCP) | 5 | 8 |
| 21 | Any Subclass Substitutes Test (LSP) | 5 | 3 |
| 22 | No Concrete ReceiptPrinter Inside LaundryDesk Test (DIP) | 5 | 3 |
| | **Total** | | **100** |

## Rules

- **Do not modify** any file under `src/test/**` or `.github/**`.
- Follow the class diagram exactly: class names, fields, methods, and signatures are used by the autograder.
- All fields must be `private`.
- In `LaundryDesk`, use `List` and `Map`. Do not use arrays.
- Gray classes are already complete and must not be changed.
- Work on your own. Similar repositories will be checked.
