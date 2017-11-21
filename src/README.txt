Joshua Wolkoff, Tyler Knight, and Daniel Cancelmo
Project 03 - Uncertain Inference
CSC 242 - Professor Ferguson
November 9, 2017
ReadMe file

Collaborators for this assignment:
Joshua Wolkoff (jwolkoff), Tyler Knight (tknight2), and Daniel Cancelmo (dcancelm)
Acknowledgement to Professor George Ferguson for his starter sample code.


To compile the java code for part 1 use the following command when in the directory:

	javac MyBNInferencer.java

To begin running the program use the following format:

	java MyBNIInferencer filename query evidence_variable_and_value_pairs

An example command:

	java MyBNInferencer aima-alarm.xml B J true M true

To compile the java code for part 2 use the following command:
	
	javac MyBNApproxInferencer.java

To begin running the program use the following format:

	java MyBNApproxInferencer sample_number filename query evidence_variable_and_value_pairs

An example command:

	java MyBNApproxInferencer 1000 aima-alarm.xml B J true M true

MyBNIInferencer prints the filename, assignments, and the result for exact inference.
MyBNApproxInferencer prints the filename, assignments, the result for rejection sampling, and then the result for likelihood weighting.