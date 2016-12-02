#SpreadSheet

This exercise aims creating a basic spreadsheet. The goal is not to develop the user interface, but the code that implements the data structures and performs the operations. The spreadsheet follows Excel's conventions. The spreadsheet implements three operations: `set()`, `get()` and `evaluate()`. Set and get assign and return cell's values. Evaluate takes the cell's content and returns the result (e.g.: a cell containing “=1+1” evaluates to 2).

The spreadsheet should handle correctly formatted integer numbers both signed and unsigned, so that when a cell containing an integer number is evaluated, the result of the evaluation shall be the number itself.
When a number does not follow the integer format (e.g.: contains a decimal point, special symbols, characters, etc.), the evaluation shall return the message `#Error`.

The spreadsheet shall be able to store and return strings entered between single quotes. When a cell containing a string is evaluated, the result of the evaluation shall be the same string without quotes. 
Therefore, when a string does not have heading or trailing quotes, the evaluation shall return the message #Error.

The spreadsheet evaluates simple formulas (e.g., without operators or cell references). A formula starts with '=' followed by a string or integer number, the evaluation of that cell shall return the corresponding string or integer.
When a cell contains a '=' sign, followed by a wrong string or integer number the evaluation of that cell shall return the message #Error.

A formula can contain a reference to a cell (following Excel convention, e.g.: A5). In such case the evaluation shall be recursive, i.e.: the referenced cell is evaluated, and the result is returned by the formula.
When the value contained in a cell referenced by a formula is incorrect, the evaluation shall return the  message #Error.

There could be cases in which the formula contain circular references (e.g.: A5 contains a formula referencing A1, and A1's formula references A5). In this case the evaluation shall return the message #Circular.

The spreadsheet performs the integer addition, subtraction, multiplication, division, and module when the operators `+ - * / %` are present in a formula. In such cases the formulas are always evaluated from left to right (e.g., usual operator precendence does not apply). When an operation cannot be performed because (1) the operators are incorrect integer numbers or (2) there is a division by zero, the evaluation shall return the message #Error.  
Moreover, a formula containing `&` performs the concatenation of strings.
A concatenation cannot be performed when the strings are wrongly formatted. In this case the evaluation shall return the message #Error.

A formula can contain parentheses, if that's the case the part within the parentheses shall be evaluated first (e.g., Formula "=1+(1\*2)" 
evaluates to "3"). If the parentheses in a formula are unbalanced, it shall return the message #Error.

Finally, Formulas can contain arbitrary spaces which are to be ignored (e.g., Formula  "=1+ (1 \* 2 )" evaluates to "3")
