# **HW5: OULAD Dataset Processor**

## **Overview**
This project processes the Open University Learning Analytics Dataset (OULAD) to generate insights such as aggregated click data and high-activity days for courses. It includes both sequential and concurrent implementations.

---

## **Dataset**
You can download the dataset from the following link:

- **OULAD Dataset Download**: [https://analyse.kmi.open.ac.uk/open_dataset](https://analyse.kmi.open.ac.uk/open_dataset)

Ensure that the dataset files (`courses.csv` and `studentVle.csv`) are placed in the specified directories before running the program.

---

## **Project Structure**
The project contains two implementations:
1. **Sequential Solution**: Processes the dataset sequentially (single-threaded).
2. **Concurrent Solution**: Processes the dataset concurrently using multithreading.

---

## **How to Run**

### **1. Sequential Solution**
The sequential solution processes the dataset in a single thread. Follow the steps below to run it:

#### **Steps to Run**
1. Open the project in IntelliJ IDEA.
2. Navigate to the `sequentialSolution` package.
3. Open the `SequentialOuladProcessor.java` file.
4. Run the file with the required program arguments.

#### **Arguments**
The program expects **three arguments**:
1. Path to the `courses.csv` file.
2. Path to the `studentVle.csv` file.
3. Path to the output directory.

#### **Example Arguments**
```plaintext
./anonymisedData/courses.csv ./anonymisedData/studentVle.csv ./output/
```

#### **Running in IntelliJ**
1. Go to `Run > Edit Configurations`.
2. Under the `Program Arguments` section, enter the arguments:
   ```plaintext
   ./anonymisedData/courses.csv ./anonymisedData/studentVle.csv ./output/
   ```
3. Click **Apply**, then **Run** the program.

---

### **2. Concurrent Solution**
The concurrent solution uses multiple threads to process the dataset efficiently. It also includes a feature to filter high-activity days (`Part 3`).

#### **Steps to Run**
1. Open the project in IntelliJ IDEA.
2. Navigate to the `concurrentSolution` package.
3. Open the `ConcurrentOuladProcessor.java` file.
4. Run the file with the required program arguments.

#### **Arguments**
The program expects **three or four arguments**:
1. Path to the `courses.csv` file.
2. Path to the `studentVle.csv` file.
3. Path to the output directory.
4. *(Optional)* A numeric threshold for identifying high-activity days (for `Part 3`).

#### **Example Arguments**
Without threshold (default behavior):
```plaintext
./anonymisedData/courses.csv ./anonymisedData/studentVle.csv ./output/
```

With threshold (to enable `Part 3`):
```plaintext
./anonymisedData/courses.csv ./anonymisedData/studentVle.csv ./output/ 10000
```

#### **Running in IntelliJ**
1. Go to `Run > Edit Configurations`.
2. Under the `Program Arguments` section, enter the arguments:
   ```plaintext
   ./anonymisedData/courses.csv ./anonymisedData/studentVle.csv ./output/ 10000
   ```
3. Click **Apply**, then **Run** the program.

---

## **Output**
- The program generates output files for each course in the specified `output` directory.
- For the concurrent solution with a threshold, an additional file named `activity-<threshold>.csv` is generated, listing high-activity days.

---

## **Notes**
- Ensure that Java and IntelliJ IDEA are properly set up.
- Make sure the dataset files are accessible at the specified paths before running the program.
- The `output/` directory will be created automatically if it doesn't exist.
