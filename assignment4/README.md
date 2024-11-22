# Random Sentence Generator

This project is a Random Sentence Generator, a program that loads grammar rules from JSON files and generates random sentences based on the selected grammar. It prompts the user to choose a grammar file and allows generating multiple sentences from the chosen file.

## Entry Point

The entry point of the program is the `Main` class, located in the `hw4` package. To start the program, run the `Main` class, which contains the `main` method.

## Third-Party Libraries

The project uses the following third-party library:

- **Gson** (from Google): Gson is used to parse JSON files containing grammar rules. It provides easy-to-use APIs to convert JSON into Java objects and vice versa, which is essential for efficiently loading grammar rules from structured JSON files. Using Gson simplifies parsing and reduces the code required for handling JSON.
  - **Repository**: [Gson on GitHub](https://github.com/google/gson)
  - **Gradle Dependency**: To add Gson, include the following dependency:
   ```gradle
   implementation 'com.google.code.gson:gson:2.11.0'
   ```
    
## Key Classes and Methods

- **Main**: Entry point for the application. Responsible for loading grammar files and managing the main program flow.
    - `main(String[] args)`: Loads grammar files from a specified directory, allows the user to select a grammar, and initiates sentence generation.

- **FileReader**: Handles loading grammar files from a specified directory.
    - `loadGrammarFiles(String[] args)`: Loads all JSON files in a specified directory and stores them for later use.
    - `printGrammarTitles()`: Displays the titles of all loaded grammar files.

- **SentenceGenerator**: Generates random sentences from a selected grammar file.
    - `generateSentence(File file)`: Loads grammar from the specified file and generates sentences based on it.

- **GrammarLoader**: Loads and parses grammar rules from JSON files.
    - `loadGrammar()`: Parses the JSON file into grammar rules.
    - `getGrammarTitle()` and `getGrammarDesc()`: Retrieve the title and description of the grammar.

- **GrammarExpander**: Expands grammar rules to generate sentences by interpreting terminal and non-terminal expressions.
    - `expand(String rule)`: Starts expanding the sentence from a given rule, usually "start".

- **UserInputHandler**: Manages user input for choosing files and confirming actions.
    - `getFileChoice(int maxIndex)`: Prompts the user to select a grammar file.
    - `getYesOrNo()`: Prompts for a "yes" or "no" answer.

## Assumptions

- **Grammar Structure**: It is assumed that all JSON files in the specified directory follow a consistent grammar structure and include keys such as `grammarTitle` and `grammarDesc`.
- **File Format**: JSON files are structured as grammar rules where non-terminals are represented with angle brackets (e.g., `<subject>`).
- **Directory Argument**: The program assumes that a directory path containing JSON grammar files is provided as an argument when running the program.

## Steps for Ensuring Correctness

1. **Error Handling**: Exceptions are caught for file I/O issues, undefined non-terminals, and invalid user inputs.
2. **Testing with Various Grammar Files**: Tested with multiple JSON grammar files to ensure consistent sentence generation.
3. **Regex Validation**: Regular expressions are used to validate integer inputs and to identify non-terminal patterns in the grammar.
4. **Boundary Checks**: User inputs for file selection and yes/no questions are validated to prevent out-of-bounds errors.

## Running the Program

### From the Command Line

1. Compile the project:
   ```bash
   javac -d out src/hw4/*.java
   
2. Run the program, specifying the directory with grammar files as an argument:
   ```bash
   java -cp out hw4.Main path/to/grammar/files

### From IntelliJ

1. Open the project in IntelliJ.
2. Navigate to `Run > Edit Configurations`.
3. Under `Program Arguments`, enter the path to the directory containing your grammar JSON files.
4. Click `Run` to start the program.

## Example Sentences

Here are some interesting sentences generated during testing:

- "May sixty-eight times thirty-three fleas find your feet suddenly delectable ."
- "May a swarm of locusts find refuge in your bedroom!"
- "With the power of Thor's belch , may a thousand biting wolverines find your buttocks suddenly delectable ."

These sentences demonstrate the program's capability to create varied and imaginative phrases based on different grammar rules.

---

Enjoy generating creative sentences with the Random Sentence Generator!