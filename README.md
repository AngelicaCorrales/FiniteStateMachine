# FiniteStateMachine
## About the project🚀
This program generates from any finite state automaton (either Mealy's or Moore's), its respective related and minimum equivalent automaton.

Our implementation is based on the following pseudocode of the finite state automata minimization algorithm:
* **Step 1**: Obtain the equivalent connected automaton, eliminating all states that are not accessible from the initial state.
* **Step 2**: Perform the partitioning algorithm on the equivalent connected automaton produced from the previous step.
  * **Step 2a**: Form an initial partition P1 of Q. Grouping states that are 1-equivalent, i.e., states that produce identical outputs for each input symbol.
    * **For a Mealy automaton:** states q and q' are in the same block of P1 if, and only if, for each s ∈ S, g(q,s)=g(q',s).
    * **For a Moore automaton:** the states q and q' are in the same block of P1 if, and only if, for each s ∈ S, h(q) = h(q').
  * **Step 2b**: Obtain Pk+1 from Pk as follows: the states q and q' are in the same block of Pk+1 if, and only if,
    * They are in the same block of Pk
    * For each s ∈ S its successors s f(q,s) and f(q',s) are in the same block of Pk.
  * **Step 2c**: Repeat step 2b until Pm+1 = Pm for some m. We call Pm the final partition of Q.
* **Step 3**: Each of the blocks of the final partition Pf, produced by the previous step, will correspond to a state of the minimal equivalent automaton. The initial state of this new automaton will be the block containing the initial state of the original automaton. From these new states, its corresponding state table is obtained by applying the following rules:
   * To find the successor s of the state q' in M' select any state in the block of the partition Pf corresponding to q' and find the block containing its successor s; the state corresponding to M' is the successor s of q'.
   * The output for a transition s of the state q' of M' is the output for a transition s for any state in the block corresponding to q'.
    
## Installation and execution🔧💻
* To run the program you need a minimum JRE of: "jre1.8.0_281".
* Our program is portable:<br>
 1️⃣ You can clone this repository and then compile it in your console,<br>
 2️⃣ You can download a .ZIP file of our project and then unzip this folder to compile it in your console, or <br> 
 3️⃣ You can look for the runnable .jar file, following this steps:<br>
      * Browse to the directory in which you unzipped the .ZIP file or FiniteStateMachine folder if you cloned our repository.<br> 
      * Browse to the executable folder.<br> 
      * Double clic on FiniteStateMachine.jar<br>
    
## Build with 🛠️
* [Eclipse](https://www.eclipse.org/downloads/) - IDE utilized.
* [Java](https://www.oracle.com/co/java/technologies/javase/javase-jdk8-downloads.html) - Programming language implemented.
* [Scene Builder](https://gluonhq.com/products/scene-builder/) - Design tool used in the construction of the graphical interface.

## How to use the program? 📌
You can find an **instructions manual** [here](https://github.com/AngelicaCorrales/FiniteStateMachine/blob/441385d130336a2143eb23338b13025093d262a1/docs/Instructions.pdf)!!

## Authors ✒️
* **Angélica Corrales Quevedo** - [AngelicaCorrales](https://github.com/AngelicaCorrales).
* **Keren López Córdoba** - [KerenLopez](https://github.com/KerenLopez).
