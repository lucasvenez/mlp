require("RSNNS")

true  = 1
false = 0
xor   = 1
and   = 2
or    = 3

inputs = data.frame(p = c(true, true, false, false,
                          true, true, false, false,
                          true, true, false, false),
                    
                    q = c(true, false, true, false,
                          true, false, true, false,
                          true, false, true, false), 
                    
                    lo = c(and, and, and, and,
                           or, or, or, or,
                           xor, xor, xor, xor))
  
inputs <- inputs

outputs <- data.frame(target = 
          c(true, false, false, false,
            true, true, true, false,
            false, true, true, false))

outputs <- outputs

net <- mlp(x = inputs, y = outputs, size = 6, learnFunc = "Std_Backpropagation", 
           hiddenActFunc = "Act_Logistic", inputsTest = inputs, targetsTest = outputs, linOut = FALSE, maxit = 20000)

extractNetInfo(net)

w <- weightMatrix(net)
paste(w[w != 0], collapse = ", ")

paste(extractNetInfo(net)$unitDefinitions$unitBias, collapse = ",")
?mlp
