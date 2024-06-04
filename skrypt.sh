#!/bin/bash

for i in {1..4}; do
    cat ./tests/test$i.txt > input.txt
    cat ./tests/test$i.txt
    python3 siedelP.py
    java Main.java
    
done
