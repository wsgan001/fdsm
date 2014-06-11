This program contains the following measures:
Co-occurrence, Cosine, Covariance, Hypergeometric Coefficient, Jaccard, Pearson Correlation, levFDSM, SLevFDSM, PValue, z-scores, Presorted z-scores, 

All measures are in package "algo"

-------------------------------
How to use the Programm: 

You can find a small data example in "Example" folder

Step 1: 

Configure the program in "info.setting.java"

Step 2: 

run the program: 

<measure_name>.run() calculate the measure and put the results in 
Output/<measure_name>/  

or 

just run the file: execute.RunAll.java

----------------------------------------------------

1. Setting:
info.setting.java

2. Input File Formats:
#numberOfSamples = ??, from = ?, to = ?, numberOfPrimaryIds = ?, sumOfCardinarity = ?
#WorkComputerSecondaryId:ComputerSecondaryId:Cardinality:ComputerPrimaryIds

3. Output:

Output/<measure name>/

or

Output/<measure name>/<numberOfSampleGraphs>/


4. Output file:
<measure name>_GL.txt : Global List
<measure name>_LL.txt : Local List

5. PrimaryId: The objects which relationships will be measured.
   SecondaryId: The objects which recommend the Primary Objects.
   
6. If you have any problem about the program, please contact me: connygy@googlemail.com

